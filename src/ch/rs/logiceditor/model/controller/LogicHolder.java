/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.controller;

import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.model.master.LogicPanel;
import ch.rs.logiceditor.view.util.ApplicationProperties;
import com.google.gson.annotations.Expose;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import ch.rs.logiceditor.view.util.ApplicationErrorEnum;

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

    private ApplicationProperties appProps = new ApplicationProperties();

    public LogicHolder(){
        switch(appProps.getProperties()){
            case OK:
                break;
            case NO_FILE:
                System.out.println("TCouldn't find the file, you need to recreate it");
                break;
            case NEW_FILE:
                System.out.println("A new file has been created, please fill it up");
                break;
            case WRONG_DATA:
                System.out.println("The File contains wrong data or unreadable data. recreate a new file");
                break;
            case ERROR:
                System.out.println("Disastrour Error, delete the file and restart the application.");
                break;
            default:
                System.out.println("Got something unexpected @ LogicHolder Constructor");
        }
    }

    
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
        addID(temporaryId);
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
    
    private void addID(int uniqueID){
        totalIds.add(uniqueID);
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
    
    /**
     * This method does not generate a uniqueID but takes it from the savefile.
     * It still checks to see if the ID is already set and blocks the execution if it is.
     * @param block
     * @param uniqueId
     * @param panel 
     */
    public void addLogicBlockToPane(LogicBlock block, int uniqueId, LogicPanel panel){
        if(isUnique(uniqueId)){
            block.setUniqueID(String.valueOf(uniqueId));
            panel.addBlock(block);
        }
    }

    public void loadSaveFile(){

    }

}
