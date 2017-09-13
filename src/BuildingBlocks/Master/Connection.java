/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.effect.Light.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

/**
 *
 * @author Ricardo
 */
public class Connection implements Observer{
    
    private Point startPoint;
    private Point endPoint;
    private String sValue;
    private boolean bValue;
    private Polyline plyConnection;
    
    public Connection(Point startPoint, Output obsOutput, Point endPoint, Input obsInput){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        obsOutput.addObserver(obsInput);
        obsOutput.addObserver(this);
        generateLine();
    }
    
    
    private void generateLine(){
        plyConnection = new Polyline();
        plyConnection.setStrokeWidth(4);
        plyConnection.getPoints().addAll(new Double[]{
                                            startPoint.getX(), startPoint.getY(),
                                            endPoint.getX(), endPoint.getY()});
    }
    
    public Polyline getLine(){
        return this.plyConnection;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("i was called");
        Output out = (Output) o;
        if(out.getBooleanOutput()){
            System.out.println("Set Line true");
            plyConnection.setStroke(Color.RED);
        } else {
            System.out.println("set Line false");
            plyConnection.setStroke(Color.BLACK);
        }
    }

    
}
