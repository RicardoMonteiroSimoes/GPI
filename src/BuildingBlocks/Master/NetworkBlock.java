/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.ArrayList;
import BuildingBlocks.Master.BlockGraphic.Type;

/**
 *
 * @author Ricardo
 */
public abstract class NetworkBlock extends BlockGraphic{
    
    
    public NetworkBlock(String blockName, String blockSubName, Input in, boolean canChangeInputs){
        super(blockName, blockSubName, in, canChangeInputs, Type.NETWORK);
    }
    
    public NetworkBlock(String blockName, String blockSubName, Output out, boolean canChangeInputs){
        super(blockName, blockSubName, out, Type.NETWORK);
    }
    
    protected abstract void Logic();
    
    public void setInput(int iInput, boolean bInputStatus){
        
        Logic();
    }
    
    

    
}

    
        
    

