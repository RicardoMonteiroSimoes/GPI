/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.master;

import ch.rs.logiceditor.model.master.ConnectionPoint.ConnectionType;
import ch.rs.logiceditor.model.util.CreationHelper;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Ricardo
 */
public abstract class TimerBlock extends LogicBlock {

    private double delayTime = 1.0;
    private ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    public TimerBlock(String blockName) {
        super(blockName, CreationHelper.createConnectionPoint(ConnectionType.INPUT, Boolean.class), 
                CreationHelper.createConnectionPoint(ConnectionType.OUTPUT, Boolean.class), BlockType.TIMER);
    }

    protected abstract void setOutputAfterTimer();

    protected void setTime(double dTime) {
        this.delayTime = dTime;
    }

    protected double getTime() {
        return delayTime;
    }

    protected void startTimer() {
        startTimerFunction();
    }

    /**
     *This function starts the Timer. It also automaticly resets the Timer.
     */
    private void startTimerFunction() {
        ses = Executors.newSingleThreadScheduledExecutor();
        ses.schedule(new Runnable() {
            @Override
            public void run() {
                setOutputAfterTimer();
            }
        }, (long) delayTime, TimeUnit.SECONDS);
    }

}
