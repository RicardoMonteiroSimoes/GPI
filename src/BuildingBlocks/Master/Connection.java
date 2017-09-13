/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import sc_pgi.SC_PGI;

/**
 *
 * @author Ricardo
 */
public class Connection implements Observer{
    
    private Point2D startPoint;
    private Point2D endPoint;
    private String sValue;
    private boolean bValue;
    private Polyline plyConnection;
    
    public Connection(Point2D startPoint, Output obsOutput, Point2D endPoint, Input obsInput){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        obsOutput.addObserver(obsInput);
        obsOutput.addObserver(this);
        generateLine();
    }
    
    
    private void generateLine(){
        double sideBarWidth = SC_PGI.GUI.getSideBarWidth();
        plyConnection = new Polyline();
        plyConnection.setStrokeWidth(2);
        plyConnection.getPoints().addAll(new Double[]{
                                            startPoint.getX() - sideBarWidth, startPoint.getY(),
                                            endPoint.getX() - sideBarWidth, endPoint.getY()});
    }
    
    public Polyline getLine(){
        return this.plyConnection;
    }

    @Override
    public void update(Observable o, Object arg) {
        Output out = (Output) o;
        if(out.getBooleanOutput()){
            plyConnection.setStroke(Color.RED);
        } else {
            plyConnection.setStroke(Color.BLACK);
        }
    }

    
}
