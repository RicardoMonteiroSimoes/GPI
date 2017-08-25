/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.Output;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class SR extends LogicBlock{
    
    /**
     * alInputs.get(0) ist SET
     * alInputs.get(1) ist RESET
     * RS bedeutet SET-RESET, d.h. SET ist höher gestellt als SET
     */
    public SR(){
        super("SR", new ArrayList<Input>(getSRInputs()), new Output("Output"), Type.LOGIC);
    }
    
    @Override
    protected void Logic(){
        if(super.getInputs().get(0).getStatus()){
            super.setOutputStatus(true);
        } else if (super.getInputs().get(1).getStatus()){
            super.setOutputStatus(false);
        }
    }
    
    private static ArrayList<Input> getSRInputs () {
        ArrayList<Input> alTemp = new ArrayList();
        alTemp.add(new Input("Set"));
        alTemp.add(new Input("Reset"));
        return alTemp;
    }

    
}
