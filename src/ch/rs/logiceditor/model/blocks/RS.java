/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.model.util.CreationHelper;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class RS extends LogicBlock {

    /**
     * alInputs.get(0) ist SET alInputs.get(1) ist RESET RS bedeutet RESET-SET,
     * d.h. RESET ist höher gestellt als SET
     */
    public RS () {
    
    }

    @Override
    protected void Logic () {

    }

}
