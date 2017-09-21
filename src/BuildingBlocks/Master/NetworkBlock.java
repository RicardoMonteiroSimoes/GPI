/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.ArrayList;
import BuildingBlocks.Master.BlockGraphic.Type;
import BuildingBlocks.Master.ContactPoint.Datatype;
import BuildingBlocks.Master.util.CreationUtil;

/**
 *
 * @author Ricardo
 */
public abstract class NetworkBlock extends LogicBlock{
    
    
    public NetworkBlock(){
        super("Server In", "Receives Commands on a Port", CreationUtil.createOutput(Datatype.STRING), Type.NETWORK);
    }

    
}

    
        
    

