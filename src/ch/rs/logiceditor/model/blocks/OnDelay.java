/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.master.TimerBlock;
import java.util.Observable;
import java.util.TimerTask;

/**
 *
 * @author Ricardo
 */
public class OnDelay extends TimerBlock{

    public OnDelay (String blockName) {
        super(blockName);
    }
    

    
    @Override
    protected void Logic () {
    }

    @Override
    protected void setOutputAfterTimer() {
    }

}
