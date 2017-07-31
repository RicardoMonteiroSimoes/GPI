/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocksMaster.LogicBlock;
import BuildingBlocksMaster.Input;
import BuildingBlocksMaster.Output;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class OR extends LogicBlock{
    
    public OR(ArrayList<Input> alInputs){
        super("OR", alInputs, new Output("Output"));
    }
    
    protected void Logic(){
        boolean bTemp = false;
        for(Input inp : super.getInputs()){
            if(inp.getStatus()){
                bTemp = true;
                break;
            }
        }
        super.setOutputStatus(bTemp); 
    }
    
}
