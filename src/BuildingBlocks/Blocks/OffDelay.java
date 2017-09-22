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
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Ricardo
 */
public class OffDelay extends TimerBlock {

    public OffDelay() {
        super("OffDelay", "Turn Off Delay");
    }

    @Override
    protected void Logic() {
        if ((boolean) getInput()) {
            setOutput(true);
        } else {
            startTimer();
        }
    }

    @Override
    protected void setOutputAfterTimer() {
        setOutput(getInput());
    }

}
