/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocksMaster;

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
    private boolean bValue = false;
    private boolean bIsBoolean = true;
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
        this.sName = new String(out.getName());
        createEllipse();
    }

    public boolean getStatus () {
        return this.bValue;
    }

    public void setStatus (boolean bValue) {
        this.bValue = bValue;
        notifyObservers();
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
        Tooltip t = new Tooltip(String.valueOf(getStatus()));
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
