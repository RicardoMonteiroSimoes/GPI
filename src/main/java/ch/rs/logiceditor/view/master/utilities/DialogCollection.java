package ch.rs.logiceditor.view.master.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.StageStyle;

import java.util.Optional;

public class DialogCollection {

    public static String stringInputDialog(String title, String message){
        TextInputDialog dialog = new TextInputDialog();
        dialog.initStyle(StageStyle.UTILITY);

        dialog.setTitle(title);
        dialog.setContentText(message);


        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public static String getPathDialog(String title, String message){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setContentText(message);

        ButtonType buttonTypeOne = new ButtonType("Chose Path...");

        alert.getButtonTypes().setAll(buttonTypeOne);

        DirectoryChooser dc = new DirectoryChooser();


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            return dc.showDialog(alert.getOwner()).toString();
        } else {
            return null;
        }
    }
}
