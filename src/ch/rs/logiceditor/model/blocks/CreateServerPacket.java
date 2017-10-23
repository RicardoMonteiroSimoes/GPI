/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.util.network.ServerPacket;
import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.model.util.CreationHelper;

/**
 *
 * @author Ricardo
 */
public class CreateServerPacket extends LogicBlock {

    
    private ServerPacket serverPacket;

    
    public CreateServerPacket () {
        super("Packet Creator", CreationHelper.createConnectionPoint(ConnectionPoint.ConnectionType.INPUT, Boolean.class),
                CreationHelper.createConnectionPoint(ConnectionPoint.ConnectionType.OUTPUT, ServerPacket.class), BlockType.NETWORK);
    }
    
    public void setServerPacket(String message, String IP, int port){
        serverPacket = new ServerPacket(message, IP, port);
    }
    
    public ServerPacket getServerPacket(){
        return serverPacket;
    }

    @Override
    protected void Logic () {
        if (getInput()) {
            pulseOutput(serverPacket);
        }
    }

    @Override
    public <T> void pulseOutput (T value) {
        getOutputs().get(0).setValue(value);
        getOutputs().get(0).setValue(null);
    }
}
