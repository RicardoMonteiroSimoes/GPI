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
                    setPointsNull();
                } else {
                    try{
                        temporaryInput.addOutputToListenTo(temporaryOutput);
                        SC_PGI.GUI.addLine(new Connection(new Point2D(mouseEventInput.getSceneX(), mouseEventInput.getSceneY()),
                        temporaryOutput, new Point2D(mouseEventOutput.getSceneX(), mouseEventOutput.getSceneY()), temporaryInput));
                    } catch (IllegalAccessError ae) {
                        System.out.println(ae.getMessage());
                    }
                    
                    setPointsNull();
                }
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
