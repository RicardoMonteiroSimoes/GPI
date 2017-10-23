/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPI;

import ch.rs.logiceditor.model.blocks.AND;
import ch.rs.logiceditor.model.blocks.CreateServerPacket;
import ch.rs.logiceditor.model.blocks.TCPIn;
import ch.rs.logiceditor.model.blocks.TCPOut;
import ch.rs.logiceditor.model.controller.LogicHolder;
import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.LogicPanel;
import ch.rs.logiceditor.model.util.ClassData;
import ch.rs.logiceditor.model.util.CreationHelper;
import ch.rs.logiceditor.model.util.ClassDataSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.management.modelmbean.InvalidTargetObjectTypeException;

/**
 *
 * @author Ricardo
 */
public class Main {

//    static Variable var = new Variable("Zeitkonstante", true);
    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) {
//        Gson gson = new GsonBuilder().create();
//        System.out.println(gson.toJson(new AND(CreationHelper.createConnectionPointList(ConnectionPoint.ConnectionType.INPUT, Boolean.class, 4),
//                            CreationHelper.createConnectionPoint(ConnectionPoint.ConnectionType.OUTPUT, Boolean.class))));
//        System.out.println(gson.toJson(new TCPIn()));
        LogicHolder holder = new LogicHolder();
        LogicPanel panel = new LogicPanel();
        panel.setName("testPanel");
        holder.addPanel(panel);
        TCPIn tcpIn = new TCPIn();
        TCPOut tcpOut = new TCPOut();
        tcpIn.setPort(5250);
        tcpIn.setTurnOffMessage("false");
        tcpIn.setTurnOnMessage("true");
        CreateServerPacket csp = new CreateServerPacket();
        csp.setServerPacket("on", "127.0.0.1", 15000);
        holder.addLogicBlockToPane(csp, panel);
       holder.addLogicBlockToPane(tcpIn, panel);
        holder.addLogicBlockToPane(tcpOut, panel);
        panel.addConnection(tcpIn, 0, csp, 0);
        panel.addConnection(csp, 0, tcpOut, 0);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ClassData.class, new ClassDataSerializer());
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        holder.startLogicHolder();
        System.out.println(gson.toJson(holder));

    }

}
