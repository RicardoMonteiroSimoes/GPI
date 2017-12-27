/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.master;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Ricardo
 */
public class Connections {
    
    @Expose
    private String startID;
    @Expose
    private String endID;
    
    public Connections(String startID, String endID){
        this.startID = startID;
        this.endID = endID;
    }
    
    public String getStartID(){
        return startID;
    }
    
    public String getEndID(){
        return endID;
    }
    
    @Override
    public String toString(){
        return ("StartID: " + startID + " EndID: " + endID);
    }
    
}
