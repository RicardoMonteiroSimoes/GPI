/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.TimerBlock;
import java.util.Observable;
import java.util.TimerTask;

/**
 *
 * @author Ricardo
 */
public class OnDelay extends TimerBlock{
    
    public OnDelay(){
        super("OnDelay", "Output On Delay");
    }
    
    @Override
    protected void Logic () {
        if(getInput()){
            startTimer();
        } else {
            
            setOutput(false);
        }
    }

    @Override
    protected void setOutputAfterTimer() {
        setOutput(getInput());
    }

}
