/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.master;

import ch.rs.logiceditor.model.util.Tools;
import com.google.gson.annotations.Expose;
import java.util.LinkedList;
import javax.management.modelmbean.InvalidTargetObjectTypeException;

/**
 *
 * @author Ricardo
 */
public class LogicPanel implements Runnable{

    @Expose
    private String name, creator, lastEdit, lastEditor;
    @Expose
    private boolean isEditable = true;
    @Expose
    private LinkedList<LogicBlock> blocks = new LinkedList<>();
    @Expose
    private LinkedList<Connections> connections = new LinkedList<>();
    @Expose
    private int listSpot;

    
    public int getListSpot(){
        return listSpot;
    }
    
    public void setListSpot(int listSpot){
        this.listSpot = listSpot;
    }
    
    public String getName () {
        return name;
    }

    public void setName (String name) {
        if (isEditable) {
            this.name = name;
        } else {

        }
    }

    public boolean isEditable () {
        return isEditable;
    }

    public void setCreator (String creator) {
        if (creator == null) {
            this.creator = creator;
        } else {

        }
    }

    public void setEditable (boolean isEditable) {
        this.isEditable = isEditable;
    }
    
    /**
     * call this whenever you save the logicPanel.
     */
    public void setLastEdit(){
        lastEdit = Tools.getCurrentTime();
    }
    
    /**
     * set this whenever someone other than the original creator
     * edits this pane.
     * @param name name of the editor.
     */
    public void setLastEditor(String name){
        lastEditor = name;
    }
    
    public LinkedList<LogicBlock> getBlocks(){
        return blocks;
    }
    
    public LinkedList<Connections> getConnections(){
        return connections;
    }
    
    public void addBlock(LogicBlock block){
        blocks.add(block);
    }

    @Override
    public void run () {
    }
    
    public void addConnection(LogicBlock source, int sourceOutput, 
            LogicBlock destination, int destinationInput){
        try{
            source.getOutputs().get(sourceOutput).addObserver(destination.getInputs().get(destinationInput));
        } catch (Exception e) {
            System.out.println("error @ " + e.getMessage());
        }
        connections.add(new Connections(source.getUniqueID() + "." + sourceOutput, destination.getUniqueID() + "." + destinationInput));
    }
    
    
    
    private String getUniqueId(String string){
        return string.split(".")[0];
    }
    
    private int getConnectionId(String string){
        return Integer.parseInt(string.split(".")[1]);
    }

}
