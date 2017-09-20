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
    private CubicCurve cubicConnection;
    private final double CONTROL_ADDITION = 10.0;
    private final double CONTROL_MULTIPLICATION = 10.0;
    
    public Connection(Point2D startPoint, Output obsOutput, Point2D endPoint, Input obsInput){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        obsOutput.addObserver(obsInput);
        obsOutput.addObserver(this);
        generateLine();
    }
    
    
    private void generateLine(){
        double sideBarWidth = SC_PGI.GUI.getSideBarWidth();
        //double startX = startPoint.getX()- sideBarWidth;
        double startX = startPoint.getX();
        double startY = startPoint.getY();
        //double endX = endPoint.getX()- sideBarWidth;
        double endX = endPoint.getX();
        double endY = endPoint.getY();
        double halfX = (startX + endX)/2;
        double halfY = (startY + endY)/2;

        
        cubicConnection = new CubicCurve();
        cubicConnection.setStartX(startX);
        cubicConnection.setStartY(startY);
        cubicConnection.setEndX(endX);
        cubicConnection.setEndY(endY);
        
        //Start algorithm stuff
        if(startX > endX){
            cubicConnection.setControlX1(halfX+CONTROL_ADDITION);
            cubicConnection.setControlX2(halfX-CONTROL_ADDITION);
        } else {
            cubicConnection.setControlX1(halfX-CONTROL_ADDITION);
            cubicConnection.setControlX2(halfX+CONTROL_ADDITION);
        }
        if(startY > endY){
            cubicConnection.setControlY1(startY-CONTROL_MULTIPLICATION*CONTROL_ADDITION);
            cubicConnection.setControlY2(endY+CONTROL_MULTIPLICATION*CONTROL_ADDITION);
        } else {
            cubicConnection.setControlY1(startY+CONTROL_MULTIPLICATION*CONTROL_ADDITION);
            cubicConnection.setControlY2(endY-CONTROL_MULTIPLICATION*CONTROL_ADDITION);
        }

        
        cubicConnection.setFill(Color.TRANSPARENT);
        cubicConnection.setStrokeWidth(2);
        cubicConnection.setStroke(Color.BLACK);
        
        //END ALGORITHM
                
    }
    
    public CubicCurve getCurve(){
        return this.cubicConnection;
    }

    @Override
    public void update(Observable o, Object arg) {
        Output out = (Output) o;
        if(out.getBooleanOutput()){
            cubicConnection.setStroke(Color.RED);
        } else {
            cubicConnection.setStroke(Color.BLACK);
        }
    }

    
}
