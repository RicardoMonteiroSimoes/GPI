/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocksMaster.LogicBlock;
import BuildingBlocksMaster.Input;
import BuildingBlocksMaster.Output;
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
        super("RS", new ArrayList<Input>(getRSInputs()), new Output("Output"));
    }

    @Override
    protected void Logic () {
        if (super.getInputs().get(0).getStatus() && !super.getInputs().get(1).getStatus()) {
            super.setOutputStatus(true);
        } else if (super.getInputs().get(1).getStatus()) {
            super.setOutputStatus(false);
        }
    }

    private static ArrayList<Input> getRSInputs () {
        ArrayList<Input> alTemp = new ArrayList();
        alTemp.add(new Input("Set"));
        alTemp.add(new Input("Reset"));
        return alTemp;
    }

}
