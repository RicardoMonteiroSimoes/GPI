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
import javafx.scene.shape.CubicCurve;
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
    private CubicCurve cubicConnection;
    private final double CONTROL_X_ADDITION = 30.0;
    private final double CONTROL_Y_ADDITION = 30.0;
    
    public Connection(Point2D startPoint, Output obsOutput, Point2D endPoint, Input obsInput){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        obsOutput.addObserver(obsInput);
        obsOutput.addObserver(this);
        generateLine();
    }
    
    
    private void generateLine(){
        double sideBarWidth = SC_PGI.GUI.getSideBarWidth();
        double startX = startPoint.getX()- sideBarWidth;
        double startY = startPoint.getY();
        double endX = endPoint.getX()- sideBarWidth;
        double endY = endPoint.getY();
        double halfX = (startX + endX)/2;
        double halfY = (startY + endY)/2;
        plyConnection = new Polyline();
        plyConnection.setStrokeWidth(2);
        plyConnection.getPoints().addAll(new Double[]{
                                            startX, startY,
                                            halfX, startY,
                                            halfX, endY,
                                            endX, endY});
        
        cubicConnection = new CubicCurve();
        cubicConnection.setStartX(startX);
        cubicConnection.setStartY(startY);
        cubicConnection.setControlX1(halfX+CONTROL_X_ADDITION);
        cubicConnection.setControlY1(startY+CONTROL_Y_ADDITION);
        cubicConnection.setControlX2(endX-CONTROL_X_ADDITION);
        cubicConnection.setControlY2(endY+CONTROL_Y_ADDITION);
        cubicConnection.setEndX(endX);
        cubicConnection.setEndY(endY);
        cubicConnection.setFill(Color.TRANSPARENT);
        cubicConnection.setStrokeWidth(2);
        cubicConnection.setStroke(Color.BLACK);
        
        //TODO
        //ALGORITHM TO DEFINE THE CONTROL POINTS DEPENDING ON START AND END X && Y
                
    }
    
    public Polyline getLine(){
        return this.plyConnection;
    }
    
    public CubicCurve getCurve(){
        return this.cubicConnection;
    }

    @Override
    public void update(Observable o, Object arg) {
        Output out = (Output) o;
        if(out.getBooleanOutput()){
            plyConnection.setStroke(Color.RED);
            cubicConnection.setStroke(Color.RED);
        } else {
            plyConnection.setStroke(Color.BLACK);
            cubicConnection.setStroke(Color.BLACK);
        }
    }

    
}
