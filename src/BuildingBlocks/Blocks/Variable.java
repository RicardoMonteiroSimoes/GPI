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
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

/**
 *
 * @author Ricardo
 */
public class Variable{

    private String sName;
    ArrayList<Output> alOutputs = new ArrayList();
    private final double OPACITY_VALUE = 0.6;
    private final double ARROW_HEIGHT = 20.0;
    private final double ARROW_WIDTH = 50.0;
    private final double ARROW_POINT_WIDTH = 5.0;
    private String sVariable;
    private boolean bValue;
    private Group grpBlock = new Group();

    public Variable (String sVariable) {
        this.sVariable = sVariable;
        setOutput();
    }

    public Variable (boolean bValue) {
        this.bValue = bValue;
        setOutput();
    }
    
    public Variable (String sName, String sVariable) {
        setName(sName);
        this.sVariable = sVariable;
        setOutput();
    }

    public Variable (String sName, boolean bValue) {
        setName(sName);
        this.bValue = bValue;
        setOutput();
    }
    
    public void setName(String sName){
        this.sName = sName;
        createBlock();
    }
    
    public String getName(){
        return this.sName;
    }

    private void setOutput () {
        alOutputs.add(new Output("Output"));
        if (sVariable == null) {
            alOutputs.get(0).setOutput(this.bValue);
        } else {
            alOutputs.get(0).setOutput(this.sVariable);
        }
    }

    private void createBlock () {
        Polyline plyArrow = new Polyline();
        Label lblName = new Label(sName);
        plyArrow.getPoints().addAll(new Double[]{
                                            0.0, 0.0,
                                            ARROW_WIDTH, 0.0,
                                            ARROW_WIDTH+ARROW_POINT_WIDTH, ARROW_HEIGHT/2,
                                            ARROW_WIDTH, ARROW_HEIGHT,
                                            0.0, ARROW_HEIGHT,
                                            0.0, 0.0});
        plyArrow.setStroke(Color.BLACK);
        plyArrow.setFill(Color.RED);
        plyArrow.setOpacity(OPACITY_VALUE);
        grpBlock.getChildren().add(plyArrow);
        grpBlock.getChildren().add(lblName);
    }
    
    public Group getBlock(){
        return this.grpBlock;
    }

}
