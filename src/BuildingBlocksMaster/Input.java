/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocksMaster;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author Ricardo
 */
public class Input implements Observer {

    private String sName;
    private boolean bStatus = false;
    private Ellipse elpsInput;
    private final double CONNECTION_POINT_RADIUS = 4.0;

    public Input (String sName) {
        this.sName = sName;
        createEllipse();
    }

    public Input () {
        this.sName = "Input";
        createEllipse();
    }

    public Input (Input in) {
        this.sName = new String(in.getName());
        createEllipse();
    }

    public void setStatus (boolean bStatus) {
        this.bStatus = bStatus;
        setEllipseTooltip();
    }

    public boolean getStatus () {
        return this.bStatus;
    }

    public String getName () {
        return this.sName;
    }

    public void setObserver (Observable obsOutput) {
        this.setObserver(obsOutput);
    }

    @Override
    public void update (Observable o, Object arg) {
        Output outTemp = (Output) o;
        setStatus(outTemp.getStatus());
    }

    public Ellipse getEllipse () {
        return this.elpsInput;
    }

    private void setEllipseTooltip () {
        Tooltip t = new Tooltip(String.valueOf(getStatus()));
        Tooltip.install(elpsInput, t);
    }

    private void createEllipse () {
        elpsInput = new Ellipse(0.0, 0.0, CONNECTION_POINT_RADIUS, CONNECTION_POINT_RADIUS);
        setEllipseTooltip();
    }

    public void setPointXY (double x, double y) {
        elpsInput.setCenterX(x);
        elpsInput.setCenterY(y);
    }
}
