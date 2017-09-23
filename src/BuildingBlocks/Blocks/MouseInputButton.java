/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.BlockGraphic;
import javafx.scene.control.Button;
import BuildingBlocks.Master.ContactPoint;
import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;
import BuildingBlocks.Master.util.Dialogs;
import java.util.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseButton;


/**
 *
 * @author Ricardo
 */
public class MouseInputButton extends BlockGraphic {

    private boolean isImpuls = true;

    public MouseInputButton () {
        super("Button", "Click for impulse", CreationUtil.createOutput(ContactPoint.Datatype.BOOLEAN), Type.VARIABLE);
        addContextMenuItems();
    }

    @Override
    protected void setOnMousePressedEvent (MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (isImpuls) {
                getOutputs().get(0).setOutput(true);
            } else {
                try{
                    getOutputs().get(0).setOutput(!(boolean)getOutputs().get(0).getOutput());
                } catch(Exception e){
                    getOutputs().get(0).setOutput(false);
                }
            }
        }
    }

    @Override
    protected void setOnMouseReleasedEvent (MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (isImpuls) {
                getOutputs().get(0).setOutput(false);
            }
        }
    }

    @Override
    public void update (Observable o, Object arg) {

    }
    
    private void setImpuls(boolean isImpuls){
        this.isImpuls = isImpuls;
        if(isImpuls){
            getOutputs().get(0).setOutput(false);
        }
    }
    
    private void addContextMenuItems(){
        Menu parentMenu = new Menu("Modus");
        RadioMenuItem radioMenuItem1 = new RadioMenuItem("Impuls");
        RadioMenuItem radioMenuItem2 = new RadioMenuItem("Umschaltung");
        ToggleGroup group = new ToggleGroup();
 
        radioMenuItem1.setToggleGroup(group);
        radioMenuItem2.setToggleGroup(group);
        
        parentMenu.getItems().addAll(radioMenuItem1, radioMenuItem2);
        if(isImpuls){
            radioMenuItem1.setSelected(isImpuls);
        } else {
            radioMenuItem2.setSelected(true);
        }
        
        radioMenuItem1.setOnAction(new EventHandler <ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setImpuls(radioMenuItem1.isSelected());
            }
        });
        
        radioMenuItem2.setOnAction(new EventHandler <ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setImpuls(!radioMenuItem2.isSelected());
            }
        });
        
        addToContextMenu(parentMenu);
    }

}
