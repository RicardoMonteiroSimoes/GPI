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
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polyline;
import sc_pgi.SC_PGI;

/**
 *
 * @author Ricardo
 */
public class ConnectionHandler implements Observer{
    
    Output temporaryOutput;
    MouseEvent mouseEventOutput;
    MouseEvent mouseEventInput;
    Input temporaryInput;

    
    @Override
    public void update (Observable o, Object arg) {
        try{
            temporaryOutput = (Output) o;
            mouseEventOutput = (MouseEvent) arg;
        } catch (Exception e){
            System.out.println("This is not an Output...");
        }
        try{
            temporaryInput = (Input) o;
            mouseEventInput = (MouseEvent) arg;
        } catch (Exception e){
            System.out.println("This is not an Input...");
        }
        if(mouseEventOutput != null && mouseEventInput != null){
            System.out.println("starting connection procedure");
            temporaryInput.addObserver(temporaryOutput);
            Polyline temporaryLine = new Polyline();
            temporaryLine.getPoints().add(mouseEventInput.getSceneX());
            temporaryLine.getPoints().add(mouseEventInput.getSceneY());
            temporaryLine.getPoints().add(mouseEventOutput.getSceneX());
            temporaryLine.getPoints().add(mouseEventOutput.getSceneY());
            SC_PGI.GUI.addLine(temporaryLine);
        }
    }
    
}
