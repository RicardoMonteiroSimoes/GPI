/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.ContactPoint.Datatype;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.util.CreationUtil;
import BuildingBlocks.Master.util.Dialogs;

/**
 *
 * @author Ricardo
 */
public class DoubleClick extends LogicBlock {

    private long currentTime;
    private final long MAX_TIME_LIMIT = 220;
    private long lastTime;
    private boolean isDoubleClick = false;

    public DoubleClick() {
        super("Doppelklick", "Doppel klicken!", CreationUtil.createInput(Datatype.BOOLEAN), false, CreationUtil.createOutput(Datatype.BOOLEAN));
    }

    @Override
    protected void Logic() {
        long diff = 0;
        if (getInputs().get(0).getBooleanInput()) {
            currentTime = System.currentTimeMillis();
            if (lastTime != 0 && currentTime != 0) {
                diff = currentTime - lastTime;
                if (diff <= MAX_TIME_LIMIT) {
                    isDoubleClick = true;
                } else {
                    isDoubleClick = false;
                }
            }
            lastTime = currentTime;
            if (isDoubleClick) {
                pulseOutput(true);
            }
        }

    }

}
