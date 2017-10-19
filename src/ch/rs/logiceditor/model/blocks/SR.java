/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import old.BuildingBlocks.Master.ContactPoint.Datatype;
import old.BuildingBlocks.Master.LogicBlock;
import old.BuildingBlocks.Master.Input;
import old.BuildingBlocks.Master.Output;
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
        super("SR", "set-reset", CreationUtil.createInputList(new String[]{"Set", "Reset"}, Datatype.BOOLEAN), false, CreationUtil.createOutput(Datatype.BOOLEAN));
    }
    
    @Override
    protected void Logic(){
        if((boolean) getInputs().get(0).getInput()){
            setOutput(true);
        } else if ((boolean) getInputs().get(1).getInput()){
            setOutput(false);
        }
    }


    
}
