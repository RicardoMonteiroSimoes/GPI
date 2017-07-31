/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocksMaster.Input;
import BuildingBlocksMaster.LogicBlock;
import BuildingBlocksMaster.Output;

/**
 *
 * @author Ricardo
 */
public class NOT extends LogicBlock{
    
    public NOT(){
        super("NOT", new Input("Input"), new Output("Output"));
    }
    
    @Override
    protected void Logic(){
        super.setOutputStatus(!super.getInputs().get(0).getStatus());
    }
}
