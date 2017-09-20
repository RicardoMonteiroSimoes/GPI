/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import BuildingBlocks.Master.ContactPoint.Datatype;
import BuildingBlocks.Master.util.Dialogs;
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

    private double amountOfOutputs = 0;

    private Circle inputCircle = new Circle();
    private final double CONNECTION_POINT_RADIUS = 4.0;
    private final double STROKE_WIDTH = 0.0;
    private Datatype datatype;

    public Input(String sName) {
        this.sName = sName;
        createCircle();
    }

    public Input() {
        this.sName = "Input";
        createCircle();
    }

    public Input(Input in) {
        this.sName = in.getName();
        createCircle();
    }

    public Boolean getBooleanInput() {
        return booleanValue;
    }

    public void setBooleanInput(boolean booleanValue) {
        integerValue = null;
        doubleValue = null;
        this.booleanValue = booleanValue;
        floatValue = null;
        stringValue = null;
    }

    public String getStringInput() {
        return stringValue;
    }

    public void setStringInput(String stringValue) {
        integerValue = null;
        doubleValue = null;
        booleanValue = null;
        floatValue = null;
        this.stringValue = stringValue;
    }

    public Double getDoubleInput() {
        return doubleValue;
    }

    public void setDoubleInput(double doubleValue) {
        integerValue = null;
        this.doubleValue = doubleValue;
        booleanValue = null;
        floatValue = null;
        stringValue = null;
    }

    public Float getFloatInput() {
        return floatValue;
    }

    public void setFloatInput(Float floatValue) {
        integerValue = null;
        doubleValue = null;
        booleanValue = null;
        this.floatValue = floatValue;
        stringValue = null;
    }

    public Integer getIntegerInput() {
        return integerValue;
    }

    public void setIntegerInput(int integerValue) {
        this.integerValue = integerValue;
        doubleValue = null;
        booleanValue = null;
        floatValue = null;
        stringValue = null;
    }

    public void setInputDataType(Datatype datatype) {
        this.datatype = datatype;
    }

    public void removeObservers() {
        deleteObservers();
    }

    public String getName() {
        return this.sName;
    }

    public Circle getCircle() {
        return this.inputCircle;
    }

    private void setCircleTooltip() {
        Tooltip t = new Tooltip("what to put in here?");
        Tooltip.install(inputCircle, t);
    }

    private void createCircle() {
        inputCircle.setRadius(CONNECTION_POINT_RADIUS);
        inputCircle.setStrokeWidth(STROKE_WIDTH);
        inputCircle.setFill(Color.BLACK);
        inputCircle.setStroke(Color.BLACK);
        createCircleFunctionality();
        setCircleTooltip();
    }

    private void createCircleFunctionality() {
        inputCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                setChanged();
                notifyObservers(t);
            }
        });
    }

    public void setPointXY(double x, double y) {
        inputCircle.setCenterX(x);
        inputCircle.setCenterY(y);
    }

    public void setDataType(Datatype datatype) {
        this.datatype = datatype;
        switch (datatype) {
            case BOOLEAN:
                setBooleanInput(false);
                break;
            case STRING:
                setStringInput("empty");
                break;
            case FLOAT:
                setFloatInput(0.0f);
                break;
            case DOUBLE:
                setDoubleInput(0.0);
                break;
            case INTEGER:
                setIntegerInput(0);
                break;
            default:

        }
    }

    private boolean checkOutput(Output out) {
        switch (out.getDatatype()) {
            case BOOLEAN:
                setBooleanInput(out.getBooleanOutput());
                return true;
            case STRING:
                setStringInput(out.getStringOutput());
                return true;
            case FLOAT:
                setFloatInput(out.getFloatOutput());
                return true;
            case DOUBLE:
                setDoubleInput(out.getDoubleOutput());
                return true;
            case INTEGER:
                setIntegerInput(out.getIntegerOutput());
                return true;
            default:
                System.out.println("Something goddamn terrible happened @ check Output");
                return false;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Output out = (Output) o;
        checkOutput(out);
        setChanged();
        notifyObservers();
    }

    public void addOutputToListenTo(Output out) {
        if (!checkOutput(out)) {
            throw new IllegalAccessError("You're trying to Connect a "
                    + datatype + " input to an " + out.getDatatype() + "output");
        } else if (amountOfOutputs == 1) {
            Dialogs.alertDialog("Fehler", "Verknüpfungsfehler", "Dieser Input hat schon einen Output!");
            throw new IllegalArgumentException("Dieser Input ist schon belegt!");
        } else {
            out.addObserver(this);
            amountOfOutputs++;
            setChanged();
            notifyObservers();
        }
    }

}
