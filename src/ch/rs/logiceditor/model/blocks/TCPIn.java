/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.master.ConnectionPoint.ConnectionType;
import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.model.util.CreationHelper;
import ch.rs.logiceditor.model.util.network.ServerSocketReceive;
import com.google.gson.annotations.Expose;
import ch.rs.reflectorgrid.TransferGrid;

/**
 *
 * @author Ricardo
 */
public class TCPIn extends LogicBlock {

    @Expose
    @TransferGrid
    private int port = 0;
    
    @Expose
    @TransferGrid
    private String turnOnMessage = null;
    @Expose
    @TransferGrid
    private String turnOffMessage = null;
    
    @TransferGrid(editable = false)
    private boolean isRunning = false;
    
    @TransferGrid
    private float floatport = 0.0f;
    
    @TransferGrid
    private double floatdouble = 0.0;
    
    @TransferGrid
    private boolean boooool = true;
    
    @TransferGrid(options = {"full", "half", "none"})
    private String runtimeMode = "full";
    
    
    public TCPIn () {
        super("TCP In", CreationHelper.createConnectionPoint(ConnectionType.OUTPUT, Boolean.class), BlockType.NETWORK);
    }

    public void setPort (int port) {
        this.port = port;
        if (!isRunning) {
            startServer();
        }
    }

    public int getPort () {
        return port;
    }

    public void setTurnOnMessage (String message) {
        turnOnMessage = message;
        if (!isRunning) {
            startServer();
        }
    }

    public String getTurnOnMessage () {
        return turnOnMessage;
    }

    public void setTurnOffMessage (String message) {
        turnOffMessage = message;
        if (!isRunning) {
            startServer();
        }
    }

    public String getTurnOffMessage () {
        return turnOffMessage;
    }

    @Override
    protected void Logic () {
    }
    
    @Override
    protected void startServer () {
        if (port == 0 || turnOffMessage == null || turnOnMessage == null) {

        } else {
            try {
                ServerSocketReceive ssR = new ServerSocketReceive(port) {
                    @Override
                    public void getPacket () {
                        Logic(this.getMessage());
                    }
                };
                new Thread(ssR).start();
            } catch (NumberFormatException nfe) {
            }
        }
    }

    private void Logic (String message) {
        if(message.equals(turnOnMessage)){
            setOutput(true);
        } else if (message.equals(turnOffMessage)){
            setOutput(false);
        }
    }
}
