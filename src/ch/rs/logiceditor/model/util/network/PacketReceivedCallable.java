/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.util.network;

import java.util.concurrent.Callable;

/**
 *
 * @author Ricardo
 */
public class PacketReceivedCallable implements Callable {
    
    private String message;   
    
    public void setMessage(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }

    @Override
    public Object call () throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
