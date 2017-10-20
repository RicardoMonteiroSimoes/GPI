/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.controller;

import ch.rs.logiceditor.model.master.LogicBlock;
import java.util.LinkedList;


/**
 *
 * @author Ricardo
 */
public class LogicHolder {
    
    private LinkedList<LogicBlock> logicBlocks = new LinkedList <>();
    
    public void addLogicBlock(LogicBlock block){
        logicBlocks.add(block);
    }
    
}
