/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

/**
 *
 * @author Ricardo
 */
public class ServerSocketReceive extends Observable {

    private int port;
    private ServerSocket receiveServerSocket;
    private Socket receiveSocket;

    public ServerSocketReceive (int Port) {
        this.port = port;
        try{
            receiveServerSocket = new ServerSocket(port);
            System.out.println("new serversocket done");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Runnable run () {
        try {
            System.out.println("run");
            receiveSocket = receiveServerSocket.accept();
            System.out.println("accept");
            BufferedReader brInput = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
            String socketMessage = brInput.readLine();
            brInput.close();
            receiveSocket.close();
            notifyObservers(socketMessage);
        } catch (Exception e) {
            System.out.println("couldnt launch socket");
        }
        return null;
    }

}
