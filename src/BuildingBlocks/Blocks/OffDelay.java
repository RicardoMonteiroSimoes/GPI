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
public class OffDelay extends TimerBlock{
    
    public OffDelay(){
        super("OffDelay");
    }
    
    @Override
    protected void Logic () {
        if(super.getInputs().get(0).getStatus()){
            //super.setOutputStatus(true);
            super.cancelTimer();
        } else {
            super.setTime(super.getTimeInput());
            startTimer();
        }
    }
    
    @Override
    protected void startTimer(){
        TimerTask timTask = new TimerTask() {
            @Override
            public void run () {
                setOutputAfterTimer(false);
            }
        };
        super.scheduleTimer(timTask);
    }
    
    
    @Override
    protected void setOutputAfterTimer(boolean bOutput){
        //super.setOutputStatus(bOutput);
    }
}
