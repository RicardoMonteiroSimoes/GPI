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
public abstract class FilterBlock extends LogicBlock{
    
    public FilterBlock(String sName, String blockSubName, Input in, Output out){
        super(sName, blockSubName, in, false, out, Type.FILTER);
    }
    
}

    
        
    

