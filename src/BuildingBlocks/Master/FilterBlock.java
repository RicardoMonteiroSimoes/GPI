/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public abstract class FilterBlock extends BlockGraphic{
    
    private ArrayList<Input> alInputs = new ArrayList();
    private ArrayList<Output> alOutputs = new ArrayList();
    
    public FilterBlock(String sName, Input in, Output out, Type type){
        super(sName, in, out, type);
        alInputs.add(new Input(in));
        alOutputs.add(new Output(out));
    }
    
    public FilterBlock(String sName, ArrayList<Input> alInputs, Output out, Type type){
        super(sName, alInputs, out, type);
        this.alInputs = new ArrayList(alInputs);
        alOutputs.add(new Output(out));
    }
    
    public FilterBlock(String sName, ArrayList<Input> alInputs, ArrayList<Output> alOutputs, Type type){
        super(sName, alInputs, alOutputs, type);
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

    
}

    
        
    

