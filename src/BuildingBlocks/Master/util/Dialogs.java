/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master.util;

import BuildingBlocks.Master.BlockGraphic;
import BuildingBlocks.Master.BlockGraphic.Type;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.util.Pair;

/**
 *
 * @author Ricardo
 */
public class Dialogs {

    public static String nameInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Name input");
        dialog.setHeaderText("Change name of Block");
        dialog.setContentText("Please enter a new Name for the Block");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return "Something went wrong.";
    }

    public static void informationWindow(BlockGraphic block) {
        Dialog<ButtonType> informationWindow = new Dialog<>();
        informationWindow.setTitle("Infofenster");
        informationWindow.setHeaderText("Look, a Custom Login Dialog");
        ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
        ButtonType cancelButton = ButtonType.CANCEL;

        informationWindow.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField blockName = new TextField();
        blockName.setText(block.getName());
        TextField blockType = new TextField();
        blockType.setText(block.getType().toString());

        /**
        ComboBox comboBox = new ComboBox<Type>();
        comboBox.setMinWidth(20);
        for (Type t : Type.values()) {
            comboBox.getItems().add(t);
        }
        comboBox.getSelectionModel().select(block.getType());
        */

        TextArea blockNotes = new TextArea(block.getNotes().getText());
        blockNotes.setEditable(true);
        blockNotes.setWrapText(true);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(blockName, 1, 0);
        grid.add(new Label("Type:"), 0, 1);
        grid.add(new Label(block.getType().toString()), 1, 1);
        grid.add(new Label("Notes:"), 0, 2);
        grid.add(blockNotes, 1, 2);

        informationWindow.getDialogPane().setContent(grid);

        informationWindow.initModality(Modality.APPLICATION_MODAL);

        Optional<ButtonType> result = informationWindow.showAndWait();

        if (result.isPresent()) {
            if (result.get().equals(okButton)) {
                block.setName(blockName.getText());
                block.setNote(new Text(blockNotes.getText()));
            }
        }

    }
}
