/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocksMaster.GUI_Block;
import BuildingBlocksMaster.Output;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polyline;

/**
 *
 * @author Ricardo
 */
public class Variable {

    private String sName = "Variable";
    private String sTooltip = "Not set";
    ArrayList<Output> alOutputs = new ArrayList();
    private final double OPACITY_VALUE = 0.6;
    private final double ARROW_HEIGHT = 20.0;
    private final double ARROW_WIDTH = 50.0;
    private final double ARROW_POINT_WIDTH = 5.0;
    private final double CONNECTION_POINT_RADIUS = 4.0;
    private boolean bValue;
    private Group grpBlock = new Group();

    public Variable (boolean bValue) {
        this.bValue = bValue;
        createOutput();
        setOutput();
    }

    public Variable (String sTooltip, boolean bValue) {
        this.sTooltip = sTooltip;
        this.bValue = bValue;
        createOutput();
        setOutput();
        createBlock();
    }
    
    private void createOutput(){
        alOutputs.add(new Output("Output"));
    }
    
    public void setTooltip(String sTooltip){
        this.sTooltip = sTooltip;
    }
    
    public String getTooltip(){
        return this.sTooltip;
    }

    public String getName () {
        return this.sName;
    }

    private void setOutput () {
        alOutputs.get(0).setStatus(this.bValue);
    }

    private void createBlock () {
        Polyline plyArrow = new Polyline();
        Label lblName = new Label(sName);
        plyArrow.getPoints().addAll(new Double[]{
            0.0, 0.0,
            ARROW_WIDTH, 0.0,
            ARROW_WIDTH + ARROW_POINT_WIDTH, ARROW_HEIGHT / 2,
            ARROW_WIDTH, ARROW_HEIGHT,
            0.0, ARROW_HEIGHT,
            0.0, 0.0});
        plyArrow.setStroke(Color.BLACK);
        plyArrow.setFill(Color.RED);
        plyArrow.setOpacity(OPACITY_VALUE);
        Ellipse elpsTemp = new Ellipse(ARROW_WIDTH + ARROW_POINT_WIDTH, ARROW_HEIGHT/2, CONNECTION_POINT_RADIUS, CONNECTION_POINT_RADIUS); 
        Tooltip t = new Tooltip("Output");
        Tooltip.install(elpsTemp, t);
        t = new Tooltip(sTooltip + " is " + bValue);
        Tooltip.install(plyArrow, t);
        Tooltip.install(lblName, t);
        
        grpBlock.getChildren().add(plyArrow);
        grpBlock.getChildren().add(lblName);
        
        
        

        grpBlock.getChildren().add(elpsTemp);
    }

    public Group getBlock () {
        return this.grpBlock;
    }

}
