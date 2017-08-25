/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.TimerBlock;
import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.Output;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Ricardo
 */
public class OnDelay extends TimerBlock{
    
    public OnDelay(){
        super("OnDelay");
    }
    
    @Override
    protected void Logic () {
        if(super.getInputs().get(0).getStatus()){
            super.setTime(super.getTimeInput());
            startTimer();
        } else {
            super.setOutputStatus(false);
            super.cancelTimer();
        }
    }
    
    @Override
    protected void startTimer(){
        TimerTask timTask = new TimerTask() {
            @Override
            public void run () {
                setOutputAfterTimer(true);
            }
        };
        super.scheduleTimer(timTask);
    }
    
    
    @Override
    protected void setOutputAfterTimer(boolean bOutput){
        super.setOutputStatus(bOutput);
    }
}
