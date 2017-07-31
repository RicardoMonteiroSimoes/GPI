/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocksMaster;

import javafx.scene.effect.Light.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

/**
 *
 * @author Ricardo
 */
public class Connection {
    
    private Point startPoint;
    private Point endPoint;
    private String sValue;
    private boolean bValue;
    private Polyline plyConnection;
    
    public Connection(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        generateLine();
    }
    
    private void generateLine(){
        plyConnection = new Polyline();
        if(sValue != null || bValue == true){
            plyConnection.setStroke(Color.RED);
        } else {
            plyConnection.setStroke(Color.BLUE);
        }
        plyConnection.setStrokeWidth(4);
        plyConnection.getPoints().addAll(new Double[]{
                                            startPoint.getX(), startPoint.getY(),
                                            endPoint.getX(), endPoint.getY()});
    }
    
    public Polyline getLine(){
        return this.plyConnection;
    }
    
}
