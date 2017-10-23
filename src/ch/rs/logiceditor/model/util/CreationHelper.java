/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.util;

import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.ConnectionPoint.ConnectionType;
import ch.rs.logiceditor.model.util.network.ServerPacket;
import java.util.LinkedList;

/**
 *
 * @author Ricardo
 */
public class CreationHelper {
    
    
    public static ConnectionPoint createConnectionPoint(ConnectionType type, Class t){
        ConnectionPoint point = new ConnectionPoint(type, getClassData(t));
        return point;
    }
    
    public static LinkedList<ConnectionPoint> createConnectionPointList(ConnectionType type, Class t, int amount){
        LinkedList<ConnectionPoint> linkedList = new LinkedList<>();
        for(int x = 0; x < amount; x++){
            linkedList.add(createConnectionPoint(type, t));
        }
        return linkedList;
    }
    
    private static ClassData getClassData(Class t){
        System.out.println(t.getName());
        switch(t.getName()){
            case "java.lang.Boolean":
                return new ClassData("Boolean", Boolean.class);
            case "ch.rs.logiceditor.model.util.network.ServerPacket":
                return new ClassData("ServerPacket", ServerPacket.class);
            default:
                return null;
        }
    }
}
