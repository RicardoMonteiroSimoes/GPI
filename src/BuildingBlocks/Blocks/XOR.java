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
        super("XOR", "exklusiv oder", alInput, true, new Output("Output"));
    }
    
    @Override
    protected void Logic(){

    }
    
}
