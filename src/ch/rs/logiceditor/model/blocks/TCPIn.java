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

/**
 *
 * @author Ricardo
 */
public class TCPIn extends LogicBlock {

    private int port = 0;
    private String turnOnMessage = null;
    private String turnOffMessage = null;
    private boolean isRunning = false;

    public TCPIn () {
        super("TCP Out", CreationHelper.createConnectionPoint(ConnectionType.OUTPUT, Boolean.class), BlockType.NETWORK);
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

    private void startServer () {
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
