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
import BuildingBlocks.Master.util.Dialogs;
import java.util.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ricardo
 */
public class MouseButton extends BlockGraphic {

    private boolean isImpuls = true;

    public MouseButton() {
        super("Button", "Click for impulse", CreationUtil.createOutput(ContactPoint.Datatype.BOOLEAN), Type.VARIABLE);
        createPressModeChangeDialog();
    }

    @Override
    protected void setOnMousePressedEvent() {
        if (isImpuls) {
            getOutputs().get(0).setBooleanOutput(true);
        } else {
            getOutputs().get(0).setBooleanOutput(!getOutputs().get(0).getBooleanOutput());
        }
    }

    @Override
    protected void setOnMouseReleasedEvent() {
        if (isImpuls) {
            getOutputs().get(0).setBooleanOutput(false);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private void createPressModeChangeDialog() {
        Group modeChangeGroup = new Group();
        Label pressMode = new Label("isImpuls(true or false)");
        TextArea pressModeText = new TextArea(String.valueOf(isImpuls));
        pressModeText.setEditable(true);
        pressModeText.setWrapText(true);
        
        Button changeMode = new Button("Apply Change");
        changeMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    isImpuls = Boolean.valueOf(pressModeText.getText());
                } catch (Exception e) {
                    Dialogs.alertDialog("Fehler","Eingabefehler","isImpuls kann nur true oder false sein und nicht " + pressModeText.getText());
                    isImpuls = false;
                }
            }
        });
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(pressMode, 0, 0);
        grid.add(pressModeText, 0, 1);
        grid.add(changeMode, 1, 1);
        modeChangeGroup.getChildren().add(grid);
        addDialogFunction(modeChangeGroup);
    }

}
