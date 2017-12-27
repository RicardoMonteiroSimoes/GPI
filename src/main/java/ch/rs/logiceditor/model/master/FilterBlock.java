/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.master;

import java.util.LinkedList;

/**
 *
 * @author Ricardo
 */
public abstract class FilterBlock extends LogicBlock{
    
    public FilterBlock(String blockName, LinkedList<ConnectionPoint> inputs, LinkedList<ConnectionPoint> outputs) {
        super(blockName, inputs, outputs, BlockType.FILTER);
    }
    
    
    
}
