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

    public LogicBlock(String sName, String blockSubName, Input in, boolean canChangeInput, Output out) {
        super(sName, blockSubName, in, canChangeInput, out, Type.LOGIC);
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
    
    protected void setOutput(boolean value){
        getOutputs().get(0).setBooleanOutput(value);
    }
    
}
