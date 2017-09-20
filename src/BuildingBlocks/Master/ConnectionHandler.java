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
                        //Bounds startBounds = temporaryOutput.getCircle().getParent().sceneToLocal(temporaryInput.getCircle().getParent().localToScene(temporaryInput.getCircle().getParent().getParent().getBoundsInLocal()));
                        //Bounds endBounds = temporaryInput.getCircle().sceneToLocal(temporaryOutput.getCircle().localToScene(temporaryOutput.getCircle().getParent().getParent().getBoundsInLocal()));
                        Bounds startBounds = temporaryOutput.getCircle().getParent().localToParent(temporaryOutput.getCircle().getBoundsInParent());
                        System.out.println(startBounds);
                        
                        Bounds endBounds = temporaryInput.getCircle().getParent().localToParent(temporaryInput.getCircle().getBoundsInLocal());
                        System.out.println(endBounds);
                        double startX = (startBounds.getMinX() + startBounds.getMaxX())/2;
                        double startY = (startBounds.getMinY() + startBounds.getMaxY())/2;
                        double endX = (endBounds.getMinX() + endBounds.getMaxX())/2;
                        double endY = (endBounds.getMinY() + endBounds.getMaxY())/2;
                        System.out.println("startXY " + startX + " " + startY);
                        System.out.println("endXY " + endX + " " + endY);
                        
                        /**
                        line.setStartX(startBounds.getMinX() + startBounds.getWidth() / 2);
                        line.setStartY(startBounds.getMinY() + startBounds.getHeight() / 2);
                        line.setEndX(endBounds.getMinX() + endBounds.getWidth() / 2);
                        line.setEndY(endBounds.getMinY() + endBounds.getHeight() / 2);
                         */
                        SC_PGI.GUI.addCurve(new Connection(new Point2D(startX, startY),
                        temporaryOutput, new Point2D(endX, endY), temporaryInput));
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
