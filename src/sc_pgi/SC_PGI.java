/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc_pgi;

import ch.rs.logiceditor.model.blocks.AND;
import ch.rs.logiceditor.model.blocks.CreateServerPacket;
import ch.rs.logiceditor.model.blocks.TCPIn;
import ch.rs.logiceditor.model.blocks.TCPOut;
import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.util.CreationHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.management.modelmbean.InvalidTargetObjectTypeException;


/**
 *
 * @author Ricardo
 */
public class SC_PGI {
    
    
    
    
//    static Variable var = new Variable("Zeitkonstante", true);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Gson gson = new GsonBuilder().create();
//        System.out.println(gson.toJson(new AND(CreationHelper.createConnectionPointList(ConnectionPoint.ConnectionType.INPUT, Boolean.class, 4),
//                            CreationHelper.createConnectionPoint(ConnectionPoint.ConnectionType.OUTPUT, Boolean.class))));
//        System.out.println(gson.toJson(new TCPIn()));
          TCPIn tcpIn = new TCPIn();
          TCPOut tcpOut = new TCPOut();
          tcpIn.setPort(5250);
          tcpIn.setTurnOffMessage("false");
          tcpIn.setTurnOnMessage("true");
          CreateServerPacket csp = new CreateServerPacket();
          csp.setServerPacket("on", "127.0.0.1", 15000);
//          tcpIn.getOutputs().get(0).addObserver(csp.getInputs().get(0));
          try{
          tcpOut.getInputs().get(0).addObservable(tcpIn.getOutputs().get(0));
          } catch (InvalidTargetObjectTypeException e){
              System.out.println(e.getMessage());
          }
//          csp.getOutputs().get(0).addObserver(tcpOut.getInputs().get(0));
    
    }

    
    
}
