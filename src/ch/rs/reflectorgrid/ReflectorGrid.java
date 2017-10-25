/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.reflectorgrid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Ricardo
 */
public class ReflectorGrid {

    public static GridPane turnObjectIntoGrid (Object object) {
        ArrayList<Label> labels = new ArrayList();
        ArrayList<TextField> txtFields = new ArrayList();
        ArrayList<Field> fields = new ArrayList();
        fields.addAll(Arrays.asList(object.getClass().getDeclaredFields()));
        Class objClass = object.getClass().getSuperclass();
        while (hasSuperClass(objClass)) {
            fields.addAll(Arrays.asList(objClass.getSuperclass().getDeclaredFields()));
            objClass = objClass.getSuperclass();
        }
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation anot : annotations) {
                if (anot.annotationType().getSimpleName().equals("TransferGridEditable")) {
                    labels.add(new Label(field.getName()));
                    try {
                        field.setAccessible(true);
                        TextField tempfield = new TextField(String.valueOf(field.get(object)));
                        tempfield.setEditable(true);
                        txtFields.add(tempfield);
                    } catch (IllegalAccessException enp) {
                        System.out.println(enp.getMessage());
                    }
                } else if (anot.annotationType().getSimpleName().equals("TransferGridUneditable")) {
                    labels.add(new Label(field.getName()));
                    try {
                        field.setAccessible(true);
                        TextField tempfield = new TextField(String.valueOf(field.get(object)));
                        tempfield.setEditable(false);
                        tempfield.setMouseTransparent(true);
                        tempfield.setFocusTraversable(false);
                        txtFields.add(tempfield);
                    } catch (IllegalAccessException enp) {
                        System.out.println(enp.getMessage());
                    }
                }
                field.setAccessible(false);
            }
        }
        return generateGrid(labels, txtFields);
    }

    private static boolean hasSuperClass (Class object) {
        return (object.getSuperclass() != null);
    }
    
    private static GridPane generateGrid(ArrayList<Label> labels, ArrayList<TextField> txtFields){
        GridPane grid = new GridPane();
        int y = 0;
        for(Label lbl : labels){
            System.out.println("doing " + lbl.getText());
            grid.add(lbl, 0, y);
            grid.add(txtFields.get(y), 1, y);
            y++;
        }
        return grid;
    }

}
