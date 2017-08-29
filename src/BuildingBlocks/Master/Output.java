/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.Observable;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author Ricardo
 */
public class Output extends Observable {

    private String sName;

    //Output variables
    Integer integerValue = null;
    Double doubleValue = null;
    Boolean booleanValue = null;
    Float floatValue = null;
    String stringValue = null;

    private String sOutput;
    private Ellipse elpsOutput;
    private final double CONNECTION_POINT_RADIUS = 4.0;

    public Output (String sName) {
        this.sName = sName;
        createEllipse();
    }

    public Output () {
        this.sName = "Output";
        createEllipse();
    }

    public Output (Output out) {
        this.sName = out.getName();
        createEllipse();
    }

    public Boolean getBooleanOutput () {
        return booleanValue;
    }

    public void setBooleanOutput (boolean booleanValue) {
        integerValue = null;
        doubleValue = null;
        this.booleanValue = booleanValue;
        floatValue = null;
        stringValue = null;
    }

    public String getStringOutput () {
        return stringValue;
    }

    public void setStringOutput (String stringValue) {
        integerValue = null;
        doubleValue = null;
        booleanValue = null;
        floatValue = null;
        this.stringValue = stringValue;
    }

    public Double getDoubleOutput () {
        return doubleValue;
    }

    public void setDoubleOutput (double doubleValue) {
        integerValue = null;
        this.doubleValue = doubleValue;
        booleanValue = null;
        floatValue = null;
        stringValue = null;
    }

    public Float getFloatOutput () {
        return floatValue;
    }

    public void setFloatOutput (Float floatValue) {
        integerValue = null;
        doubleValue = null;
        booleanValue = null;
        this.floatValue = floatValue;
        stringValue = null;
    }

    public Integer getIntegerOutput () {
        return integerValue;
    }

    public void setIntegerOutput (int integerValue) {
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

    public Ellipse getEllipse () {
        return this.elpsOutput;
    }

    private void setEllipseTooltip () {
        Tooltip t = new Tooltip("what to put in here?");
        Tooltip.install(elpsOutput, t);
    }

    private void createEllipse () {
        elpsOutput = new Ellipse(0.0, 0.0, CONNECTION_POINT_RADIUS, CONNECTION_POINT_RADIUS);
        setEllipseTooltip();
    }

    public void setPointXY (double x, double y) {
        elpsOutput.setCenterX(x);
        elpsOutput.setCenterY(y);
    }
}
