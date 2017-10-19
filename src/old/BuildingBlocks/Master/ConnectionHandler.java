/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old.BuildingBlocks.Master;

import java.util.Observable;
import java.util.Observer;
import old.BuildingBlocks.Master.Output;
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
    public void update(Observable o, Object arg) {
        if (!(arg == null)) {
            try {
                temporaryOutput = (Output) o;
                temporaryOutput.setSelected(true);
                mouseEventOutput = (MouseEvent) arg;
            } catch (Exception e) {
            }
            try {
                temporaryInput = (Input) o;
                temporaryInput.setSelected(true);
                if (temporaryInput.countObservers() > 2) {
                } else {
                    mouseEventInput = (MouseEvent) arg;
                }
            } catch (Exception e) {
            }
            if (mouseEventOutput != null && mouseEventInput != null) {
                if (temporaryOutput.getCircle().getParent().equals(temporaryInput.getCircle().getParent())) {
                } else {
                    try {
                        temporaryInput.addOutputToListenTo(temporaryOutput);

                        Bounds startBounds = temporaryOutput.getCircle().getParent().localToParent(temporaryOutput.getCircle().getBoundsInParent());

                        Bounds endBounds = temporaryInput.getCircle().getParent().localToParent(temporaryInput.getCircle().getBoundsInLocal());

                        double startX = (startBounds.getMinX() + startBounds.getMaxX()) / 2;
                        double startY = (startBounds.getMinY() + startBounds.getMaxY()) / 2;
                        double endX = (endBounds.getMinX() + endBounds.getMaxX()) / 2;
                        double endY = (endBounds.getMinY() + endBounds.getMaxY()) / 2;
                        Connection connection = new Connection(new Point2D(startX, startY),
                                temporaryOutput, new Point2D(endX, endY), temporaryInput);
                        temporaryInput.addConnection(connection);
                        temporaryOutput.addConnection(connection);
                        SC_PGI.GUI.addCurve(connection);
                        setPointsNull();
                    } catch (Exception e) {
                        setPointsNull();
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private void setPointsNull() {
        temporaryInput.setSelected(false);
        temporaryOutput.setSelected(false);
        temporaryOutput = null;
        temporaryInput = null;
        mouseEventInput = null;
        mouseEventOutput = null;
    }

}
