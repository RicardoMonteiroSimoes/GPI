/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.ConnectionPoint.ConnectionType;
import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.model.util.CreationHelper;
import java.util.LinkedList;


/**
 *
 * @author Ricardo
 */
public class NOT extends LogicBlock{
    
    public NOT(){
        super("NOT", CreationHelper.createConnectionPoint(ConnectionType.INPUT, Boolean.class), 
                CreationHelper.createConnectionPoint(ConnectionType.OUTPUT, Boolean.class));
    }
    
    @Override
    protected void Logic(){
        setOutput(! (Boolean) getInput());
    }
}
