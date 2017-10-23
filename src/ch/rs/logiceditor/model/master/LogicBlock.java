/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.master;

import ch.rs.logiceditor.model.master.ConnectionPoint.ConnectionType;
import com.google.gson.annotations.Expose;
import old.BuildingBlocks.Master.Output;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 *
 * @author Ricardo
 */
public abstract class LogicBlock implements Observer{
    
    
    @Expose
    private String blockName, blockDescription, blockNotes, uniqueID;
    @Expose
    private BlockType blockType;
    @Expose
    private LinkedList<ConnectionPoint> inputs = new LinkedList();
    @Expose
    private LinkedList<ConnectionPoint> outputs = new LinkedList();

    /**
     * This constructor is needed for the GSON Library to be able to call the Observer constructor.
     * Without this the Observer class wont build and you will get a NPE every time you try to add something to observe.
     */
    public LogicBlock(){
        super();
    }

    
    public enum BlockType {
        LOGIC, FILTER, NETWORK, TIMER
    }
    
    public LogicBlock(String blockName, LinkedList<ConnectionPoint> inputs, LinkedList<ConnectionPoint> outputs, BlockType blockType){
        this.blockName = blockName;
        this.inputs = inputs;
        this.outputs = outputs;
        this.blockType = blockType;
        constructBlock();
    }
    
    public LogicBlock(String blockName, LinkedList<ConnectionPoint> inputs, LinkedList<ConnectionPoint> outputs){
        this.blockName = blockName;
        this.inputs = inputs;
        this.outputs = outputs;
        this.blockType = BlockType.LOGIC;
        constructBlock();
    }
    
    public LogicBlock(String blockName, ConnectionPoint input, ConnectionPoint output){
        this.blockName = blockName;
        this.inputs.add(input);
        this.outputs.add(output);
        this.blockType = BlockType.LOGIC;
        constructBlock();
    }
    
    public LogicBlock(String blockName, ConnectionPoint input, ConnectionPoint output, BlockType blockType){
        this.blockName = blockName;
        this.inputs.add(input);
        this.outputs.add(output);
        this.blockType = blockType;
        constructBlock();
    }
    
    public LogicBlock(String blockName, ConnectionPoint point, BlockType blockType){
        this.blockName = blockName;
        if(point.getConnectionType() == ConnectionType.INPUT){
            inputs.add(point);
        } else {
            outputs.add(point);
        }
        this.blockType = blockType;
        constructBlock();
    }
    
    public LogicBlock(String blockName, LinkedList<ConnectionPoint> inputs, ConnectionPoint output){
        this.blockName = blockName;
        this.inputs = inputs;
        this.outputs.add(output);
        this.blockType = BlockType.LOGIC;
        constructBlock();
    }
    
        
    public void startBlockFunctions(){
        startServer();
    }
    
    protected void startServer(){}
    
    protected abstract void Logic();
    
    
    private void constructBlock(){
        handleInputObservation();
    }
    
    private void handleInputObservation(){
        for(ConnectionPoint in : inputs){
            in.addObserver(this);
        }
    }
    
    public LinkedList<ConnectionPoint> getInputs(){
        return inputs;
    }
    
    public LinkedList<ConnectionPoint> getOutputs(){
        return outputs;
    }
    
    public BlockType getBlockType(){
        return blockType;
    }
    
    @Override
    public void update (Observable o, Object arg) {
        Logic();
    }
    
    public <T> T getInput(){
        return (T) inputs.get(0).getValue();
    }
    
    public <T> void setOutput(T value){
        outputs.get(0).setValue(value);
    }
    
    public <T> void pulseOutput(T value){
        outputs.get(0).pulseValue(value);
    }
    
    public void setUniqueID(String uniqueID){
        this.uniqueID = uniqueID;
    }
    
    public String getUniqueID(){
        return uniqueID;
    }
    
    public String getName(){
        return blockName;
    }
    
    
    
}
