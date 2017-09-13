/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.ContactPoint;
import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;

/**
 *
 * @author Ricardo
 */
public class NOT extends LogicBlock{
    
    public NOT(){
        super("NOT", "not", CreationUtil.createInput(ContactPoint.Datatype.BOOLEAN),
                false, CreationUtil.createOutput(ContactPoint.Datatype.BOOLEAN));
    }
    
    @Override
    protected void Logic(){
        try{
            getOutputs().get(0).setBooleanOutput(!getInputs().get(0).getBooleanOutput());
        } catch ( NullPointerException ne ){
            getOutputs().get(0).setBooleanOutput(true);
        }
    }
}
