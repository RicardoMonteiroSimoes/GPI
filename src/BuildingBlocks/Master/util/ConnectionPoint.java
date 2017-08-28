/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master.util;

import java.util.Observable;

/**
 *
 * @author Ricardo
 */
public class ConnectionPoint extends Observable{
    
    private String sName;
    private boolean bStatus;

    public ConnectionPoint(String sName){
        this.sName = sName;
    }
    
    public boolean getStatus(){
        return this.bStatus;
    }
    
    public void setStatus(boolean bStatus){
        this.bStatus = bStatus;
        notifyObservers();
    }
    
    
    
    
}
