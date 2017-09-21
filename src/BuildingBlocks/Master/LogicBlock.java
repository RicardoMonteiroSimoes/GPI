/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Ricardo
 */
public abstract class LogicBlock extends BlockGraphic {
    
    private ArrayList<Input> inputs;

    public LogicBlock(String sName, String blockSubName, Input in, boolean canChangeInput, Output out) {
        super(sName, blockSubName, in, canChangeInput, out, Type.LOGIC);
    }
    
    public LogicBlock(String sName, String blockSubName, Input in, boolean canChangeInput, Output out, Type type) {
        super(sName, blockSubName, in, canChangeInput, out, type);
    }

    public LogicBlock(String sName, String blockSubName, ArrayList<Input> alInputs, boolean canChangeInput, Output out) {
        super(sName, blockSubName, alInputs, canChangeInput, out, Type.LOGIC);
    }

    @Override
    public void update(Observable o, Object arg) {
        try{
            Logic();
        } catch ( NullPointerException npe){
            System.out.println("Something went wrong @ Logic " + npe.getMessage());
        }
    }

    protected abstract void Logic();

    protected void pulseOutput(boolean value){
        setOutput(value);
        setOutput(!value);
    }
    
    protected <T> void setOutput(T value){
        getOutputs().get(0).setOutput(value);
    }
    
    protected <T> T getInput(){
        return (T) getInputs().get(0).getInput();
    }
    
}
