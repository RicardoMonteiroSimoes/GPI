/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocksMaster;

import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public abstract class LogicBlock extends GUI_Block{
    
    private ArrayList<Input> alInputs = new ArrayList();
    private ArrayList<Output> alOutputs = new ArrayList();
    
    public LogicBlock(String sName, Input in, Output out){
        super(sName, in, out);
        alInputs.add(new Input(in));
        alOutputs.add(new Output(out));
    }
    
    public LogicBlock(String sName, ArrayList<Input> alInputs, Output out){
        super(sName, alInputs, out);
        this.alInputs = new ArrayList(alInputs);
        alOutputs.add(new Output(out));
    }
    
    public LogicBlock(String sName, ArrayList<Input> alInputs, ArrayList<Output> alOutputs){
        super(sName, alInputs, alOutputs);
        this.alInputs = new ArrayList(alInputs);
        this.alOutputs = new ArrayList(alOutputs);
    }
    
    protected abstract void Logic();
    
    public void setInput(int iInput, boolean bInputStatus){
        alInputs.get(iInput).setStatus(bInputStatus);
        Logic();
    }
    
    protected ArrayList<Input> getInputs(){
        return this.alInputs;
    }
    
    protected ArrayList<Output> getOutputs(){
        return this.alOutputs;
    }
    
    public int getInputCount(){
        return this.alInputs.size();
    }
    
    public int getOutputCount(){
        return this.alOutputs.size();
    }

    public void setOutputStatus (boolean bStatus) {
       alOutputs.get(0).setOutput(bStatus);
    }
    
}

    
        
    

