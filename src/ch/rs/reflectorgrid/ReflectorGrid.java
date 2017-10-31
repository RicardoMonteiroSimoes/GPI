/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.reflectorgrid;

import ch.rs.reflectorgrid.Transfergrid.Fieldtype;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ricardo
 */
public class ReflectorGrid {

    private GridPane grid = new GridPane();
    private Object gridObject = new Object();

    public GridPane turnObjectIntoGrid (Object object) {
        gridObject = object;
        ArrayList<Label> labels = new ArrayList();
        ArrayList<Node> nodes = new ArrayList();
        ArrayList<Field> fields = new ArrayList();

        fields.addAll(Arrays.asList(object.getClass().getDeclaredFields()));

        Class objClass = object.getClass().getSuperclass();

        while (hasSuperClass(objClass)) {
            fields.addAll(Arrays.asList(objClass.getDeclaredFields()));
            objClass = objClass.getSuperclass();
        }

        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation anot : annotations) {
                if (anot.annotationType().getSimpleName().equals("Transfergrid")) {
                    Label temporaryLabel = new Label(field.getName());
                    labels.add(temporaryLabel);
                    try {
                        field.setAccessible(true);
                        if (hasOptions(anot)) {
                            ComboBox fieldNode = new ComboBox();
                            fieldNode.getItems().addAll(((Transfergrid) anot).options());
                            selectCurrentValue(field, fieldNode);
                            setValueChangerFunction(field, fieldNode);
                            nodes.add(fieldNode);
                        } else {
                            if (isTextField(anot)) {
                                TextField fieldNode = new TextField(getText(field));
                                fieldNode.setEditable(((Transfergrid) anot).editable());
                                fieldNode.setMouseTransparent(!fieldNode.isEditable());
                                fieldNode.setFocusTraversable(fieldNode.isEditable());
                                if (fieldNode.isEditable()) {
                                    setValueChangerFunction(field, fieldNode);
                                }
                                nodes.add(fieldNode);
                            } else {
                                TextArea fieldNode = new TextArea(getText(field));
                                fieldNode.setEditable(((Transfergrid) anot).editable());
                                fieldNode.setMouseTransparent(!fieldNode.isEditable());
                                fieldNode.setFocusTraversable(fieldNode.isEditable());
                                if (fieldNode.isEditable()) {
                                    setValueChangerFunction(field, fieldNode);
                                }
                                nodes.add(fieldNode);
                            }

                        }

                        field.setAccessible(false);
                    } catch (Exception enp) {
                        System.out.println(enp.getMessage());
                        field.setAccessible(false);
                    }
                }

            }
        }
        return generateGrid(labels, nodes);
    }

    private boolean isTextField (Annotation anot) {
        return (((Transfergrid) anot).fieldtype() == Fieldtype.TEXTFIELD);
    }

    private void selectCurrentValue (Field field, ComboBox combo) throws IllegalArgumentException, IllegalAccessException {
        combo.getSelectionModel().select(field.get(gridObject));
    }

    private boolean hasOptions (Annotation anot) {
        return !(((Transfergrid) anot).options().length == 0);
    }

    private String getText (Field field) throws IllegalArgumentException, IllegalAccessException {
        if (field.get(gridObject) == null) {
            return "";
        }
        return String.valueOf(field.get(gridObject));
    }

    private boolean hasSuperClass (Class object) {
        System.out.println(object.getName());
        return (object.getSuperclass() != null);
    }

    private GridPane generateGrid (ArrayList<Label> labels, ArrayList<Node> nodes) {
        grid.getChildren().clear();
        int y = 0;
        for (Label lbl : labels) {
            grid.setHalignment(lbl, HPos.LEFT);
            grid.setValignment(lbl, VPos.TOP);
            grid.add(lbl, 0, y);
            grid.add(nodes.get(y), 1, y);
            y++;
        }
        return grid;
    }

    private void setValueChangerFunction (Field field, TextField tempfield) {
        tempfield.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle (KeyEvent event) {
                if (event.getCode().toString().equals("ENTER")) {

                    field.setAccessible(true);
                    setObjectToField(field, tempfield);

                    field.setAccessible(false);
                }
            }

        });

    }

    private void setValueChangerFunction (Field field, ComboBox tempfield) {
        tempfield.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed (ObservableValue ov, String t, String t1) {
                field.setAccessible(true);
                try {
                    setObjectToField(field, tempfield);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(ReflectorGrid.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ReflectorGrid.class.getName()).log(Level.SEVERE, null, ex);
                }
                field.setAccessible(false);
            }
        });

    }

    private void setValueChangerFunction (Field field, TextArea tempfield) {
        tempfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed (ObservableValue<? extends String> observableValue, String s, String s2) {
                field.setAccessible(true);
                try {
                    setObjectToField(field, tempfield);

                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(ReflectorGrid.class
                            .getName()).log(Level.SEVERE, null, ex);

                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ReflectorGrid.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

                field.setAccessible(false);
            }
        });
    }

    private void setObjectToField (Field field, TextField tempfield) {
        String text = tempfield.getText();
        try {
            switch (field.getType().getName()) {
                case "int":
                    field.set(gridObject, Integer.parseInt(text));
                    break;
                case "java.lang.String":
                    field.set(gridObject, text);
                    break;
                case "float":
                    field.set(gridObject, Float.parseFloat(text));
                    break;
                case "boolean":
                    field.set(gridObject, Boolean.parseBoolean(text));
                    break;
                case "double":
                    field.set(gridObject, Double.parseDouble(text));
                    break;
                default:
                    System.out.println("no case for " + field.getType().getName());
            }
            System.out.println("field has " + field.get(gridObject));
            field.setAccessible(false);
        } catch (Exception iae) {
            try {
                System.out.println(iae.getMessage());
                tempfield.setText(String.valueOf(field.get(gridObject)));
                field.setAccessible(false);
            } catch (Exception e) {

            }

        }
    }

    private void setObjectToOption (Field field, String option) throws IllegalArgumentException, IllegalAccessException {
        field.set(gridObject, option);
        System.out.println("field has " + field.get(gridObject));
    }

    private void setObjectToField (Field field, ComboBox combo) throws IllegalArgumentException, IllegalAccessException {
        String text = combo.getSelectionModel().getSelectedItem().toString();
        setObjectToOption(field, text);
        System.out.println("field has " + field.get(gridObject));
    }

    private void setObjectToField (Field field, TextArea area) throws IllegalArgumentException, IllegalAccessException {
        field.set(gridObject, area.getText());
        System.out.println("field has " + field.get(gridObject));
    }

}
