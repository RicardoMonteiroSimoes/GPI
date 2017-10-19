package ch.rs.logiceditor.model.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Ricardo
 */
public class ServerSend implements Runnable {

    private ServerPacket serverPacket;

    public ServerSend(ServerPacket serverPacket) {
        this.serverPacket = serverPacket;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(serverPacket.getIP(), serverPacket.getPort());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(serverPacket.getMessage());
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("couldnt send out packet " + e.getMessage());
        }
    }

}
