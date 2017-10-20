/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old.BuildingBlocks.Master;

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
public class Connection implements Observer {

    private Point2D startPoint;
    private Point2D endPoint;
    private String sValue;
    private boolean bValue;
    private CubicCurve cubicConnection = new CubicCurve();
    private final double CONTROL_ADDITION = 10;
    private final double CONTROL_MULTIPLICATION = 15.0;

    public Connection(Point2D startPoint, Output obsOutput, Point2D endPoint, Input obsInput) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        obsOutput.addObserver(obsInput);
        obsOutput.addObserver(this);
        generateLine();
    }
    
    public void updateInputPoint(Point2D endPoint){
        this.endPoint = endPoint;
        generateLine();
    }
    
    public void updateOutputPoint(Point2D startPoint){
        this.startPoint = startPoint;
        generateLine();
    }

    private void generateLine() {
        double sideBarWidth = 0.0;
        //double startX = startPoint.getX()- sideBarWidth;
        double startX = startPoint.getX();
        double startY = startPoint.getY();
        //double endX = endPoint.getX()- sideBarWidth;
        double endX = endPoint.getX();
        double endY = endPoint.getY();
        double halfX = (startX + endX) / 2;
        double halfY = (startY + endY) / 2;
        double differenceY = startY - endY;
        double curvatureAddition = CONTROL_ADDITION * CONTROL_MULTIPLICATION;

        cubicConnection.setStartX(startX);
        cubicConnection.setStartY(startY);
        cubicConnection.setEndX(endX);
        cubicConnection.setEndY(endY);

        //start new algorithm stuff
        if (differenceY > -20 && differenceY < 20) {
            cubicConnection.setControlX1(startX);
            cubicConnection.setControlX2(endX);
            cubicConnection.setControlY1(startY);
            cubicConnection.setControlY2(endY);
        } else if ((differenceY < -20 || differenceY > 20) && endX > startX) {
            cubicConnection.setControlX1(startX + curvatureAddition*((endX-startX)/100));
            cubicConnection.setControlX2(endX - curvatureAddition*((endX-startX)/100));
            cubicConnection.setControlY1(startY);
            cubicConnection.setControlY2(endY);
        } else {
            double differenceBetweenX = startX - endX;
            cubicConnection.setControlX1(startX + curvatureAddition + (differenceBetweenX / 2));
            cubicConnection.setControlX2(endX - curvatureAddition - (differenceBetweenX / 2));
            cubicConnection.setControlY1(halfY);
            cubicConnection.setControlY2(halfY);
        }

        cubicConnection.setFill(Color.TRANSPARENT);

        cubicConnection.setStrokeWidth(2);

        cubicConnection.setStroke(Color.BLACK);

        //END old ALGORITHM
    }

    public CubicCurve getCurve() {
        return this.cubicConnection;
    }

    @Override
    public void update(Observable o, Object arg) {
        Output out = (Output) o;
        try {
            boolean output = (boolean) out.getOutput();
            if (output) {
                cubicConnection.setStroke(Color.RED);
            } else {
                cubicConnection.setStroke(Color.BLACK);
            }
        } catch (Exception e) {
            try {
                String output = (String) out.getOutput();
                if (output.equals("empty")) {
                    cubicConnection.setStroke(Color.BLACK);
                } else {
                    cubicConnection.setStroke(Color.RED);
                }
            } catch (Exception en) {
                try{
                    float output = (float) out.getOutput();
                    if(output < 0.01){
                       cubicConnection.setStroke(Color.BLACK);
                    } else {
                        cubicConnection.setStroke(Color.RED);
                    }
                } catch ( Exception enn){
                    System.out.println("No value set yet.");
                }
            }
        }
    }

}
