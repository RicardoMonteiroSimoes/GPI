/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.model.util.CreationHelper;
import java.util.LinkedList;

/**
 *
 * @author Ricardo
 */
public class AND extends LogicBlock {

    public AND(LinkedList<ConnectionPoint> inputs, ConnectionPoint output) {
        super("AND", inputs, output);
    }

    public AND () {
        super("AND", CreationHelper.createConnectionPointList(ConnectionPoint.ConnectionType.INPUT, Boolean.class, 4), 
                CreationHelper.createConnectionPoint(ConnectionPoint.ConnectionType.OUTPUT, Boolean.class));
    }

    @Override
    protected void Logic() {
        int amountOfTrue = 0;
        for (ConnectionPoint in : getInputs()) {
            if ((Boolean) in.getValue()) {
                amountOfTrue++;
            }
        }
        setOutput(amountOfTrue == getInputs().size());
    }

}
