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
public class RS extends LogicBlock {

    /**
     * alInputs.get(0) ist SET alInputs.get(1) ist RESET RS bedeutet RESET-SET,
     * d.h. RESET ist höher gestellt als SET
     */
    public RS () {
        super("RS", "reset-set", CreationUtil.createInputList(new String[]{"Set", "Reset"}, Datatype.BOOLEAN), false, CreationUtil.createOutput(Datatype.BOOLEAN));
    }

    @Override
    protected void Logic () {
        if((boolean)getInputs().get(1).getInput()){
            setOutput(false);
        } else if ((boolean) getInputs().get(0).getInput()){
            setOutput(true);
        }
    }

}
