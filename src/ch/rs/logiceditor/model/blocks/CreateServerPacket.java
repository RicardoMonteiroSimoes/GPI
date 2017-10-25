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
import com.google.gson.annotations.Expose;
import ch.rs.reflectorgrid.TransferGridUneditable;

/**
 *
 * @author Ricardo
 */
public class CreateServerPacket extends LogicBlock {

    
    @Expose
    private String message;
    @Expose
    private String IP;
    @Expose
    private int port;
    

    
    public CreateServerPacket () {
        super("Packet Creator", CreationHelper.createConnectionPoint(ConnectionPoint.ConnectionType.INPUT, Boolean.class),
                CreationHelper.createConnectionPoint(ConnectionPoint.ConnectionType.OUTPUT, ServerPacket.class), BlockType.NETWORK);
    }
    
    public void setServerPacket(String message, String IP, int port){
        this.message = message;
        this.IP = IP;
        this.port = port;
    }
    
    public ServerPacket getServerPacket(){
        return new ServerPacket(message, IP, port);
    }

    @Override
    protected void Logic () {
        if (getInput()) {
            pulseOutput(new ServerPacket(message, IP, port));
        }
    }

    @Override
    public <T> void pulseOutput (T value) {
        getOutputs().get(0).setValue(value);
        getOutputs().get(0).setValue(null);
    }
}
