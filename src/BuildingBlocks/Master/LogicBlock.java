/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public abstract class LogicBlock extends BlockGraphic{
    
    
    public LogicBlock(String sName, String blockSubName, Input in, boolean canChangeInput, Output out){
        super(sName, blockSubName, in, canChangeInput, out, Type.LOGIC);
    }
    
    public LogicBlock(String sName, String blockSubName, ArrayList<Input> alInputs, boolean canChangeInput, Output out){
        super(sName, blockSubName, alInputs, canChangeInput, out,  Type.LOGIC);
    }
    
    protected abstract void Logic();
    
}

    
        
    

