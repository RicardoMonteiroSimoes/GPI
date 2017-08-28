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
    
<<<<<<< HEAD
    public AND(ArrayList<Input> Inputs){
        super("AND", Inputs, new Output("Output"), Type.LOGIC);
    }
    
    public AND(ArrayList<Input> Inputs, ArrayList<Output> Outputs){
        super("AND", Inputs, Outputs, Type.LOGIC);
=======
    public AND(ArrayList<Input> alInputs){
        super("AND", alInputs, new Output("Output"), Type.LOGIC);
    }
    
    public AND(ArrayList<Input> alInputs, ArrayList<Output> alOutputs){
        super("AND", alInputs, alOutputs, Type.LOGIC);
>>>>>>> c54cb6906ca0f727d4e34e02ae6a983b8b69cb21
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
