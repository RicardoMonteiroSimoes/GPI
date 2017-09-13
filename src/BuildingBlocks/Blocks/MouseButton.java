/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.BlockGraphic;
import BuildingBlocks.Master.ContactPoint;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Ricardo
 */
public class MouseButton extends BlockGraphic {

    public MouseButton() {
        super("Button", "Click for impulse", CreationUtil.createOutput(ContactPoint.Datatype.BOOLEAN), Type.VARIABLE);
    }

    @Override
    protected void setOnMousePressedEvent() {
        getOutputs().get(0).setBooleanOutput(true);
        System.out.println("Output set to True");
    }

    @Override
    protected void setOnMouseReleasedEvent() {
        getOutputs().get(0).setBooleanOutput(false);
        System.out.println("Output set to False");
    }

}
