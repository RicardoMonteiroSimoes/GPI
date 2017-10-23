/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.controller;

import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.model.master.LogicPanel;
import com.google.gson.annotations.Expose;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents the main Structure of the whole Logic Stuff.
 * It manages all unqiue IDs, aswell as all the LogicPanels that you have.
 * 
 * @author Ricardo
 */
public class LogicHolder {
    @Expose
    private LinkedList<Integer> totalIds = new LinkedList<Integer>();
    @Expose
    private LinkedList<LogicPanel> panels = new LinkedList<>();

    
    /**
     * This function creates a Unique ID between 10000 and 20000 for blocks.
     * If, for whatever fucking reason, you have more than 10000 blocks...
     * Tell me what youre doing with this.
     * @return 
     */
    public String getUniqueID () {
        boolean isSafe = false;
        int temporaryId = 0;
        while (!isSafe) {
            temporaryId = ThreadLocalRandom.current().nextInt(10000, 20000 + 1);
            isSafe = isUnique(temporaryId);
        }
        totalIds.add(temporaryId);
        return String.valueOf(temporaryId);
    }
    
    private boolean isUnique(int idToCheck){
        for(Integer in : totalIds){
            if(in == idToCheck){
                return false;
            }
        }
        return true;
    }
    
    public void addPanel(LogicPanel panel){
        panels.add(panel);
    }
    
    public void startLogicHolder(){
        panels.forEach((panel) -> {
            new Thread(panel).start();
        });
    }
    
    public void addLogicBlockToPane(LogicBlock block, LogicPanel panel){
        block.setUniqueID(getUniqueID());
        panel.addBlock(block);
    }

}
