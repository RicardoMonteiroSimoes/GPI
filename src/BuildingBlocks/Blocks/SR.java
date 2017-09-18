/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.ContactPoint.Datatype;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;
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
        super("SR", "set-reset", CreationUtil.createInputList(new String[]{"Set", "Reset"}), false, CreationUtil.createOutput(Datatype.BOOLEAN));
    }
    
    @Override
    protected void Logic(){
        if(getInputs().get(0).getBooleanInput()){
            setOutput(true);
        } else if (getInputs().get(1).getBooleanInput()){
            setOutput(false);
        }
    }


    
}
