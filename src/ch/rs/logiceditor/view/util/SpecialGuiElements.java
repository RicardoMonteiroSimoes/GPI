/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.view.util;

import java.util.Optional;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Ricardo
 */
public class SpecialGuiElements {

    public static Tab newLogicTab() {
        Rectangle rectangle = new Rectangle();
        Tab tab = new Tab("new Tab");
        rectangle.setWidth(2000);
        rectangle.setHeight(2000);
        rectangle.setFill(Color.ANTIQUEWHITE);
        rectangle.setOpacity(0.5);
        tab.setContent(rectangle);
        tab.setContextMenu(getTabContextMenu());
        return tab;
    }

    private static ContextMenu getTabContextMenu() {
        ContextMenu cMenu = new ContextMenu();
        MenuItem rename = new MenuItem("Rename");
        cMenu.getItems().add(rename);
        return cMenu;
    }

    public static String stringInputDialog(String context) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input String");
        dialog.setHeaderText(context);
        Optional<String> result = dialog.showAndWait();
        
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

}
