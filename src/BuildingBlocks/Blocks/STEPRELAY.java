/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.ContactPoint.Datatype;
import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;

/**
 *
 * @author Ricardo
 */
public class STEPRELAY extends LogicBlock{
   
    private boolean bOldInputStatus = false;
    
    public STEPRELAY(){
        super("Schrittschalter", "step-relay", CreationUtil.createInput(Datatype.BOOLEAN), false, CreationUtil.createOutput(Datatype.BOOLEAN));
    }

    @Override
    protected void Logic () {
        if((boolean) getInputs().get(0).getInput()){
            setOutput(!(boolean)getOutputs().get(0).getOutput());
        }
    }
}
