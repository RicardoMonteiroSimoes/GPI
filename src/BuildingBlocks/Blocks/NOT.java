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
public class NOT extends LogicBlock{
    
    public NOT(){
        super("NOT", new Input("Input"), false, new Output("Output"), Type.LOGIC);
    }
    
    @Override
    protected void Logic(){
        //super.setOutputStatus(!super.getInputs().get(0).getStatus());
    }
}
