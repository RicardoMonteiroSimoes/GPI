/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.view.util;

import ch.rs.logiceditor.view.master.GraphicBlock;
import ch.rs.reflectorgrid.ReflectorGrid;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author Ricardo
 */
public class SelectionHandler {

    private Ellipse selectedConnectionPoint;
    private GraphicBlock selectedBlock;
    private final Color SELECTION_COLOR = Color.RED;
    private final Color UNSELECTED_COLOR = Color.BLACK;
    private ReflectorGrid reflector;
    
    public SelectionHandler(ReflectorGrid reflector){
        this.reflector = reflector;
    }

    public void setSelectedConnectionPoint(Ellipse selected) {
        if (selectedConnectionPoint == null) {
            selectedConnectionPoint = selected;
            selectedConnectionPoint.setFill(SELECTION_COLOR);
        } else {
            selectedConnectionPoint.setFill(UNSELECTED_COLOR);
            selectedConnectionPoint = selected;
            selectedConnectionPoint.setFill(SELECTION_COLOR);
        }
        displayBlockDetails();
    }
    
    public void setSelectedBlock(GraphicBlock block){
        if (selectedBlock == null) {
            selectedBlock = block;
            selectedBlock.setBlockStrokeColor(SELECTION_COLOR);
        } else {
            selectedBlock.setBlockStrokeColor(UNSELECTED_COLOR);
            selectedBlock = block;
            selectedBlock.setBlockStrokeColor(SELECTION_COLOR);
        }
        displayBlockDetails();
    }
    
    private void displayBlockDetails(){
        reflector.transfromIntoGrid(selectedBlock);
    }

}
