/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.Observable;
import java.util.Observer;
import BuildingBlocks.Master.Output;
import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;
import sc_pgi.SC_PGI;

/**
 *
 * @author Ricardo
 */
public class ConnectionHandler implements Observer {

    Output temporaryOutput;
    MouseEvent mouseEventOutput;
    MouseEvent mouseEventInput;
    Input temporaryInput;

    @Override
    public void update (Observable o, Object arg) {
        if (!(arg == null)) {
            try {
                temporaryOutput = (Output) o;
                mouseEventOutput = (MouseEvent) arg;
            } catch (Exception e) {
            }
            try {
                temporaryInput = (Input) o;
                if(temporaryInput.countObservers() >= 3){
                    setPointsNull();
                } else {
                    mouseEventInput = (MouseEvent) arg;
                }
            } catch (Exception e) {
            }
            if (mouseEventOutput != null && mouseEventInput != null) {
                if (temporaryOutput.getCircle().getParent().equals(temporaryInput.getCircle().getParent())) {
                } else {
                    try{
                        temporaryInput.addOutputToListenTo(temporaryOutput);
                        Bounds bounds = temporaryOutput.getCircle().sceneToLocal(temporaryInput.getCircle().localToScene(temporaryInput.getCircle().getBoundsInLocal()));
                        System.out.println(bounds);
                        SC_PGI.GUI.addCurve(new Connection(new Point2D(bounds.getMaxX(), bounds.getMaxY()),
                        temporaryOutput, new Point2D(bounds.getMinY(), bounds.getMinX()), temporaryInput));
                    } catch (IllegalAccessError ae) {
                        System.out.println(ae.getMessage());
                    }
                    
                    setPointsNull();
                }
                setPointsNull();
            }
        }
    }

    private void setPointsNull () {
        temporaryOutput = null;
        temporaryInput = null;
        mouseEventInput = null;
        mouseEventOutput = null;
    }

}
