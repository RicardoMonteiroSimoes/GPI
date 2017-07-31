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
public class AND extends LogicBlock{
    
    public AND(ArrayList<Input> alInputs){
        super("AND", alInputs, new Output("Output"));
    }
    
    public AND(ArrayList<Input> alInputs, ArrayList<Output> alOutputs){
        super("AND", alInputs, alOutputs);
    }
    
    @Override
    protected void Logic(){
        boolean bTemp = true;
        for(Input inp : super.getInputs()){
            if(!inp.getStatus()){
                bTemp = false;
                break;
            }
        }
        super.setOutputStatus(bTemp);
    }
    
}
