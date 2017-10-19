/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master.Network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

/**
 *
 * @author Ricardo
 */
public class ServerSocketReceive extends Observable implements Runnable {

    private int port;
    private ServerSocket receiveServerSocket;
    private Socket receiveSocket;

    public ServerSocketReceive(int Port) {
        this.port = Port;
        try {
            receiveServerSocket = new ServerSocket(port);
            System.out.println("new serversocket done");
            System.out.println("send strings to IP " + Inet4Address.getLocalHost().getHostAddress() + " Port " + port);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("run");
                receiveSocket = receiveServerSocket.accept();
                System.out.println("accept");
                BufferedReader brInput = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
                String socketMessage = brInput.readLine();
                brInput.close();
                receiveSocket.close();
                setChanged();
                notifyObservers(socketMessage);
            } catch (Exception e) {
                System.out.println("couldnt launch socket");
            }
        }
    }

}
