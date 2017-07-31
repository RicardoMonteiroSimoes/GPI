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
public class XOR extends LogicBlock{
    
    public XOR(ArrayList<Input> alInput){
        super("XOR", alInput, new Output("Output"));
    }
    
    @Override
    protected void Logic(){
        int iTrueCount = 0;
        for(Input inp : super.getInputs()){
            if(!inp.getStatus()){
                iTrueCount++;
                if(iTrueCount >= 2){
                    super.setOutputStatus(false);
                    break;
                }
            }
        }
        if(iTrueCount == 1){
            super.setOutputStatus(true);
        }
    }
    
}
