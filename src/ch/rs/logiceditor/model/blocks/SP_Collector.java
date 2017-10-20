/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.model.util.CreationHelper;
import ch.rs.logiceditor.model.util.network.ServerPacket;
import java.util.LinkedList;

/**
 *
 * @author Ricardo
 */
public class SP_Collector extends LogicBlock {

    public SP_Collector() {
        super("SP Collector", CreationHelper.createConnectionPointList(ConnectionPoint.ConnectionType.INPUT, ServerPacket.class, 2), 
                CreationHelper.createConnectionPoint(ConnectionPoint.ConnectionType.OUTPUT, ServerPacket.class));
    }

    @Override
    protected void Logic () {
        for(ConnectionPoint in : getInputs()){
            if(in.getValue() != null){
                pulseOutput(in.getValue());
            }
        }
    }

    @Override
    public <T> void pulseOutput (T value) {
        getOutputs().get(0).setValue(value);
        getOutputs().get(0).setValue(null);
    }
    
}
