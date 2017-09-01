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
public class OR extends LogicBlock{
    
    public OR(ArrayList<Input> alInputs){
        super("OR", "oder", alInputs, true, new Output("Output"), Type.LOGIC);
    }
    
    protected void Logic(){
        boolean bTemp = false;
        for(Input inp : super.getInputs()){
//            if(inp.getStatus()){
//                bTemp = true;
//                break;
//            }
        }
        //super.setOutputStatus(bTemp); 
    }
    
}
