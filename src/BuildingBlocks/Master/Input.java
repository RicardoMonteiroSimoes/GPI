/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import BuildingBlocks.Master.ContactPoint.Datatype;
import BuildingBlocks.Master.util.Dialogs;
import com.sun.xml.internal.ws.handler.HandlerException;
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
public class Input<T> extends Observable implements Observer {

    private String sName;

    //Input variables
    private T inputValue;

    private double amountOfOutputs = 0;
    private Datatype datatype;

    private Circle inputCircle = new Circle();
    private final double CONNECTION_POINT_RADIUS = 4.0;
    private final double STROKE_WIDTH = 0.0;

    public Input(String sName, Datatype datatype) {
        this.sName = sName;
        setDataType(datatype);
        createCircle();
    }

    public Input(Input in) {
        this.sName = in.getName();
        setDataType(in.getDatatype());
        createCircle();
    }

    public Datatype getDatatype() {
        return this.datatype;
    }

    public void setInput(T value) {
        inputValue = value;
    }

    public T getInput() {
        return inputValue;
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
        //setCircleTooltip();
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

    private void setDataType(Datatype datatype) {
        this.datatype = datatype;
        switch (this.datatype) {
            case BOOLEAN:
                setInput((T) Boolean.FALSE);
                break;
            case FLOAT:
                setInput((T) Float.valueOf(0.0f));
                break;
            case INTEGER:
                setInput((T) Integer.valueOf(0));
                break;
            case DOUBLE:
                setInput((T) Double.valueOf(0.0));
                break;
            case STRING:
                setInput((T) "empty");
                break;
            default:
                System.out.println("ups");
        }
    }

    private boolean checkOutput(Output out) {
        if (out.getDatatype().equals(datatype)) {
            setInput((T) out.getOutput());
            return true;
        }
        return false;
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
