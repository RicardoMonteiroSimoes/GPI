/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old.BuildingBlocks.Blocks;

import old.BuildingBlocks.Master.ContactPoint.Datatype;
import old.BuildingBlocks.Master.LogicBlock;
import old.BuildingBlocks.Master.Input;
import old.BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class XOR extends LogicBlock {

    public XOR() {
        super("XOR", "exklusiv oder", CreationUtil.createStandardInputList(2, Datatype.BOOLEAN), true, CreationUtil.createOutput(Datatype.BOOLEAN));
    }

    @Override
    protected void Logic() {
        int count = 0;
        for (Input in : getInputs()) {
            if ((boolean) in.getInput()) {
                count++;
                if(count > 1){
                    break;
                }
            }
        }
        setOutput(count == 1);
    }

}
