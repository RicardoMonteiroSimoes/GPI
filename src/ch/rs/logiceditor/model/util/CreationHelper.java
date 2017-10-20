/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.util;

import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.ConnectionPoint.ConnectionType;
import java.util.LinkedList;

/**
 *
 * @author Ricardo
 */
public class CreationHelper {
    
    
    public static ConnectionPoint createConnectionPoint(ConnectionType type, Class t){
        ConnectionPoint point = new ConnectionPoint(type, t);
        return new ConnectionPoint(type, t);
    }
    
    public static LinkedList<ConnectionPoint> createConnectionPointList(ConnectionType type, Class t, int amount){
        LinkedList<ConnectionPoint> linkedList = new LinkedList<>();
        for(int x = 0; x < amount; x++){
            linkedList.add(createConnectionPoint(type, t));
        }
        return linkedList;
    }
}
