/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.master.ConnectionPoint.ConnectionType;
import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.model.util.CreationHelper;
import ch.rs.logiceditor.model.util.ServerPacket;
import ch.rs.logiceditor.model.util.ServerSend;
import com.sun.media.sound.InvalidDataException;
import java.util.LinkedList;

/**
 *
 * @author Ricardo
 */
public class TCPOut extends LogicBlock{
    
    private ServerPacket serverPacketOn;
    private ServerPacket serverPacketOff;
    
    public TCPOut(){
        super("TCP Out", CreationHelper.createConnectionPoint(ConnectionType.INPUT, Boolean.class), BlockType.NETWORK);
    }
    
    protected void sendOut(ServerPacket packet){
        new Thread(new ServerSend(packet)).start();
    }

    public void setOnMessage(String message, String IP, int port){
        serverPacketOn = new ServerPacket(message, IP, port);
    }
    
    public void setOffMessage(String message, String IP, int port){
        serverPacketOff = new ServerPacket(message, IP, port);
    }
    


    @Override
    protected void Logic() {
        if((Boolean) getInput()){
            sendOut(serverPacketOn);
        } else {
            sendOut(serverPacketOff);
        }
    }
}
