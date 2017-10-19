/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.TimerBlock;
import java.util.LinkedList;


/**
 *
 * @author Ricardo
 */
public class OffDelay extends TimerBlock {

    public OffDelay() {
        super("OffDelay");
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
