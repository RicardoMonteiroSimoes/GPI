/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import BuildingBlocks.Master.ContactPoint.Datatype;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Ricardo
 */
public class Output <T> extends Observable{

    private String sName;

    //Output variables
    private T outputValue;

    private Circle outputCircle = new Circle();
    private final double CONNECTION_POINT_RADIUS = 4.0;
    private final double STROKE_WIDTH = 0.0;
    private ArrayList<Connection> connections = new ArrayList();
    private Datatype datatype;

    public Output (String sName, Datatype datatype) {
        this.sName = sName;
        setDataType(datatype);
        createCircle();
    }


    public Output (Output out) {
        this.sName = out.getName();
        createCircle();
        setDataType(out.getDatatype());
    }
    
    public void addConnection (Connection connection) {
        connections.add(connection);
    }

    public void updateOutput () {
        Bounds bounds = getCircle().getParent().localToParent(getCircle().getBoundsInParent());
        double endX = (bounds.getMinX() + bounds.getMaxX()) / 2;
        double endY = (bounds.getMinY() + bounds.getMaxY()) / 2;
        for (Connection connection : connections) {
            connection.updateOutputPoint(new Point2D(endX, endY));
        }
    }

    public void setOutput(T value){
        outputValue = value;
        notifyOfUpdate();
    }

    
    
    public T getOutput(){
        return outputValue;
    }

    public Observable getObservable () {
        return this.getObservable();
    }

    public String getName () {
        return this.sName;
    }
    
    public void setSelected(boolean value){
        if(value){
            setCircleColor(Color.RED);
        } else {
            setCircleColor(Color.BLACK);
        }
    }
    
    private void setCircleColor(Color color){
        outputCircle.setStroke(color);
        outputCircle.setFill(color);
    }

    public Circle getCircle () {
        return this.outputCircle;
    }

    private void setCircleTooltip () {
        Tooltip t = new Tooltip("what to put in here?");
        Tooltip.install(outputCircle, t);
    }

    private void createCircle () {
        outputCircle.setRadius(CONNECTION_POINT_RADIUS);
        outputCircle.setStrokeWidth(STROKE_WIDTH);
        outputCircle.setFill(Color.BLACK);
        outputCircle.setStroke(Color.BLACK);
        createCircleFunctionality();
        //setCircleTooltip();
    }

    private void createCircleFunctionality () {
        outputCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent t) {
                setChanged();
                notifyObservers(t);
            }
        });
    }

    public void setPointXY (double x, double y) {
        outputCircle.setCenterX(x);
        outputCircle.setCenterY(y);
    }

    private void setDataType (Datatype datatype) {
        this.datatype = datatype;
    }
    
    private void notifyOfUpdate(){
        setChanged();
        notifyObservers();
    }

    public Datatype getDatatype () {
        return datatype;
    }
}
