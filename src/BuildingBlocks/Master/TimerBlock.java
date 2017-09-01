/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import BuildingBlocks.Master.util.CreationUtil;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Ricardo
 */
public abstract class TimerBlock extends LogicBlock{
    
    private double dTime;
    private Timer timTemp = new Timer();
    
    public TimerBlock(String sName){
        super(sName, "Zeitblock", CreationUtil.getTimerInputs(), false, new Output("Output"), Type.TIMER);
    }    
   
    
    protected abstract void startTimer();
    
    protected abstract void setOutputAfterTimer(boolean bOutput);
    
    
    protected void setTime(double dTime){
        this.dTime = dTime;
    }
    
    protected Timer getTimer(){
        return this.timTemp;
    }
    
    protected double getTime(){
        return dTime;
    }
    
    protected double getTimeInput(){
        
       return -1.1; 
    }

    protected void scheduleTimer (TimerTask timTask) {
        timTemp.schedule(timTask, (long) dTime);
    }

    protected void cancelTimer () {
        timTemp.cancel();
    }
}

    
        
    

