/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Output;

/**
 *
 * @author Ricardo
 */
public class STEPRELAY extends LogicBlock{
   
    private boolean bOldInputStatus = false;
    
    public STEPRELAY(){
        super("Schrittschalter", new Input("Input"), new Output("Output"), Type.LOGIC);
    }

    @Override
    protected void Logic () {
        for(Input in : super.getInputs()){
            if(in.getStatus() && !bOldInputStatus){
                //super.setOutputStatus(!super.getOutputs().get(0).getStatus());
            } else {
                bOldInputStatus = super.getInputs().get(0).getStatus();
            }
        }
    }
}
