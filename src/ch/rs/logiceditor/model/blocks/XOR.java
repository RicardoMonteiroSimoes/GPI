/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.LogicBlock;
import java.util.LinkedList;

/**
 *
 * @author Ricardo
 */
public class XOR extends LogicBlock {

    public XOR(LinkedList<ConnectionPoint> inputs, LinkedList<ConnectionPoint> outputs) {
        super("XOR", inputs, outputs);
    }

    @Override
    protected void Logic() {
        int amountOfTrue = 0;
        for(ConnectionPoint in : getInputs()){
            if((Boolean) in.getValue()){
                amountOfTrue++;
                if(amountOfTrue > 1){
                    break;
                }
            }
        }
        setOutput(amountOfTrue == 1);
    }
    
}
