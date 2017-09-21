/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.ContactPoint;
import BuildingBlocks.Master.NetworkBlock;
import BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;
import java.util.Observable;

/**
 *
 * @author Ricardo
 */
public class ServerIn extends NetworkBlock{
    
    
    public ServerIn (String blockName, String blockSubName) {
        super(blockName, blockSubName, CreationUtil.createOutput(ContactPoint.Datatype.STRING), false);
    }

    @Override
    protected void Logic() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
