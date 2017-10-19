/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.util;

import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.ConnectionPoint.ConnectionType;

/**
 *
 * @author Ricardo
 */
public class CreationHelper {
    
    
    public static ConnectionPoint createConnectionPoint(ConnectionType type, Class t){
        return new ConnectionPoint(type, t);
    }
}
