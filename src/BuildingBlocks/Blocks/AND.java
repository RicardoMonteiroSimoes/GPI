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
public class AND extends LogicBlock{
    
    public AND(ArrayList<Input> alInputs){
        super("AND", alInputs, new Output("Output"), Type.LOGIC);
    }
    
    public AND(ArrayList<Input> alInputs, ArrayList<Output> alOutputs){
        super("AND", alInputs, alOutputs, Type.LOGIC);
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
