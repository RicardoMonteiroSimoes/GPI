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
public class TCPIn extends LogicBlock{
    
    private int port;
    private String turnOnMessage;
    private String turnOffMessage;
    
    public TCPIn(){
        super("TCP Out", CreationHelper.createConnectionPoint(ConnectionType.INPUT, Boolean.class), BlockType.NETWORK);
    }
    
    protected void sendOut(ServerPacket packet){
        new Thread(new ServerSend(packet)).start();
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
