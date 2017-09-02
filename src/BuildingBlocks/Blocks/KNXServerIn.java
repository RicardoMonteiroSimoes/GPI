/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.NetworkBlock;
import BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;

/**
 *
 * @author Ricardo
 */
public class KNXServerIn extends NetworkBlock{
    
    
    public KNXServerIn (String blockName, String blockSubName) {
        super(blockName, blockSubName, new Output("Output"), false);
    }

    @Override
    protected void Logic() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
