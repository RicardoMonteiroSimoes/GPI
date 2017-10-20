/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.util.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.concurrent.Callable;

/**
 *
 * @author Ricardo
 */
public abstract class ServerSocketReceive extends Observable implements Runnable {

    private int port;
    private ServerSocket receiveServerSocket;
    private Socket receiveSocket;
    private String message;
    private boolean isRunning = true;

    public ServerSocketReceive (int Port) {
        this.port = Port;
        try {
            receiveServerSocket = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void setMessage(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
    
    

    @Override
    public void run () {
        while (isRunning) {
            try {
                receiveSocket = receiveServerSocket.accept();
                BufferedReader brInput = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
                setMessage(brInput.readLine());
                brInput.close();
                getPacket();
                receiveSocket.close();
            } catch (Exception e) {
                System.out.println("couldnt launch socket");
                isRunning = false;
            }
        }
    }
    
    public abstract void getPacket();
}
