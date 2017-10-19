/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import old.BuildingBlocks.Master.ContactPoint.Datatype;
import old.BuildingBlocks.Master.Input;
import old.BuildingBlocks.Master.LogicBlock;
import old.BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;

/**
 *
 * @author Ricardo
 */
public class STEPRELAY extends LogicBlock {

    private boolean bOldInputStatus = false;

    public STEPRELAY () {
        super("Schrittschalter", "step-relay", CreationUtil.createInput(Datatype.BOOLEAN), false, CreationUtil.createOutput(Datatype.BOOLEAN));
    }

    @Override
    protected void Logic () {
        if ((boolean) getInput()) {
            try {
                setOutput(!(boolean) getOutputs().get(0).getOutput());
            } catch (NullPointerException npe) {
                setOutput(true);
            }
        }
    }
}
