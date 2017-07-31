/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocksMaster;

import java.util.Optional;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Ricardo
 */
public class Dialogs {
    
    
    static String nameInputDialog () {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Name input");
        dialog.setHeaderText("Change name of Block");
        dialog.setContentText("Please enter a new Name for the Block");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals("")) {
                return "ERROR";
            } else {
                return result.get();
            }
        }
        return "ERROR";
    }
}
