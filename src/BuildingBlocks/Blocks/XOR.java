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
public class XOR extends LogicBlock{
    
    public XOR(ArrayList<Input> alInput){
        super("XOR", alInput, true, new Output("Output"), Type.LOGIC);
    }
    
    @Override
    protected void Logic(){
        int iTrueCount = 0;
        for(Input inp : super.getInputs()){
//            if(!inp.getStatus()){
//                iTrueCount++;
//                if(iTrueCount >= 2){
//                    //super.setOutputStatus(false);
//                    break;
//                }
//            }
        }
        if(iTrueCount == 1){
            //super.setOutputStatus(true);
        }
    }
    
}
