/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.LogicBlock;
import java.util.LinkedList;



/**
 *
 * @author Ricardo
 */
public class DoubleClick extends LogicBlock {

    private long currentTime;
    private long timeLimit = 220;
    private long lastTime;
    private boolean isDoubleClick = false;

    public DoubleClick(LinkedList<ConnectionPoint> inputs, LinkedList<ConnectionPoint> outputs) {
        super("DoubleClick", inputs, outputs);
    }

    @Override
    protected void Logic() {
        long diff = 0;
                
        if ((boolean) getInput()) {
            currentTime = System.currentTimeMillis();
            if (lastTime != 0 && currentTime != 0) {
                diff = currentTime - lastTime;
                if (diff <= timeLimit) {
                    isDoubleClick = true;
                } else {
                    isDoubleClick = false;
                }
            }
            lastTime = currentTime;
            if (isDoubleClick) {
                pulseOutput(true);
            }
        }

    }
    
    public void changeTimeLimit(long timeLimit){
        this.timeLimit = timeLimit;
    }
    
    public long getTimeLimit(){
        return timeLimit;
    }

}
