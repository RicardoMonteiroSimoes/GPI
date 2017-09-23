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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ricardo
 */
public class StringComparator extends LogicBlock {
    
    private String stringToCompareTo = "empty";
    private boolean hasToBeEqual = true;

    public StringComparator(){
        super("StringComparator", "Compares Strings", CreationUtil.createInput(Datatype.STRING), false, CreationUtil.createOutput(Datatype.BOOLEAN));
        createComparisonDialog();
    }
    
    @Override
    protected void Logic () {
        String stringToCompare = (String) getInput();
        System.out.println("Comparing " + stringToCompare + " to " + stringToCompareTo);
        if(stringToCompareTo.equals(stringToCompare)){
            setOutput(hasToBeEqual);
        } else {
            setOutput(!hasToBeEqual);
        }
        
    }
    
    private void createComparisonDialog(){
        Group comparisonGroup = new Group();
        Label comparisonMode = new Label("Vergleichsmodus");
        Label compareTo = new Label("String zum Vergleichen");
        TextField comparatorString = new TextField();
        comparatorString.setText(stringToCompareTo);    
        ComboBox comboBox = new ComboBox<Type>();
        comboBox.getItems().add("Gleich");
        comboBox.getItems().add("Ungleich");
        if (hasToBeEqual) {
            comboBox.getSelectionModel().select(0);
        } else {
            comboBox.getSelectionModel().select(1);
        }

        Button changeMode = new Button("Übernehmen");
        changeMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                try {
                    switch ((String) comboBox.getSelectionModel().getSelectedItem()) {
                        case "Gleich":
                            hasToBeEqual = true;
                            break;
                        case "Ungleich":
                            hasToBeEqual = false;
                            break;
                        default:
                            Dialogs.alertDialog("Fehler", "Eingabefehler", "Keine Funktion für folgenden Wert gefunden: " + (String) comboBox.getSelectionModel().getSelectedItem());
                    }

                } catch (Exception e) {
                    hasToBeEqual = true;
                }
                stringToCompareTo = comparatorString.getText();
            }
        });
        
        comboBox.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                comboBox.requestFocus();
            }
        });
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(comparisonMode, 0, 0);
        grid.add(comboBox, 0, 1);
        grid.add(changeMode, 1, 1);
        grid.add(compareTo, 0, 2);
        grid.add(comparatorString, 1, 2);
        comparisonGroup.getChildren().add(grid);
        addDialogFunction(comparisonGroup);
    }
    
}
