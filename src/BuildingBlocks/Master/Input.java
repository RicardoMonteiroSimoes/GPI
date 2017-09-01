/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import BuildingBlocks.Master.ContactPoint.Datatype;
import java.util.Observable;
import java.util.Observer;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Ricardo
 */
public class Input extends Observable implements Observer {

    private String sName;

    //Output variables
    Integer integerValue = null;
    Double doubleValue = null;
    Boolean booleanValue = null;
    Float floatValue = null;
    String stringValue = null;

    private String sOutput;
    private Circle outputCircle = new Circle();
    private final double CONNECTION_POINT_RADIUS = 4.0;
    private final double STROKE_WIDTH = 0.0;
    private Datatype datatype;

    public Input (String sName) {
        this.sName = sName;
        createCircle();
    }

    public Input () {
        this.sName = "Output";
        createCircle();
    }

    public Input (Input in) {
        this.sName = in.getName();
        createCircle();
    }

    public Boolean getBooleanOutput () {
        return booleanValue;
    }

    public void setBooleanInput (boolean booleanValue) {
        integerValue = null;
        doubleValue = null;
        this.booleanValue = booleanValue;
        floatValue = null;
        stringValue = null;
    }

    public String getStringInput () {
        return stringValue;
    }

    public void setStringInput (String stringValue) {
        integerValue = null;
        doubleValue = null;
        booleanValue = null;
        floatValue = null;
        this.stringValue = stringValue;
    }

    public Double getDoubleInput () {
        return doubleValue;
    }

    public void setDoubleInput (double doubleValue) {
        integerValue = null;
        this.doubleValue = doubleValue;
        booleanValue = null;
        floatValue = null;
        stringValue = null;
    }

    public Float getFloatInput () {
        return floatValue;
    }

    public void setFloatInput (Float floatValue) {
        integerValue = null;
        doubleValue = null;
        booleanValue = null;
        this.floatValue = floatValue;
        stringValue = null;
    }

    public Integer getIntegerInput () {
        return integerValue;
    }

    public void setIntegerInput (int integerValue) {
        this.integerValue = integerValue;
        doubleValue = null;
        booleanValue = null;
        floatValue = null;
        stringValue = null;
    }

    public Observable getObservable () {
        return this.getObservable();
    }

    public String getName () {
        return this.sName;
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
        setCircleTooltip();
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

    public void setDataType (Datatype datatype) {
        this.datatype = datatype;
    }

    @Override
    public void update (Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
