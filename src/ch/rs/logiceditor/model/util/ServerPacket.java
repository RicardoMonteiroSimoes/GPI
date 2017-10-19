/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.util;

/**
 *
 * @author Ricardo
 */
public class ServerPacket {
    
    private String message;
    private String IP;
    private int port;
    
    public ServerPacket(String message, String IP, int port){
        this.message = message;
        this.IP = IP;
        this.port = port;
    }
    
    public String getMessage(){
        return message;
    }
    
    public String getIP(){
        return IP;
    }
    
    public int getPort(){
        return port;
    }
    
    public boolean isEmpty(){
        if(message.equals("empty")){
            return true;
        } 
        return false;
    }
    
    public static ServerPacket getEmptyPacket(){
        return new ServerPacket("empty", "null", 0);
    }
}
