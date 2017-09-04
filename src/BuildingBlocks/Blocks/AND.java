/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.ContactPoint;
import BuildingBlocks.Master.ContactPoint.Datatype;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class AND extends LogicBlock{
    

    public AND(){
        super("AND", "AND-Block", CreationUtil.createStandardInputList(Datatype.BOOLEAN), true, CreationUtil.createOutput(Datatype.BOOLEAN));
    }
    
    @Override
    protected void Logic(){
    }
    
}
