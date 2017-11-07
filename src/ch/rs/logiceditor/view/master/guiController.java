/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.view.master;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ricardo
 */
public class guiController {

    @FXML
    private ScrollPane detailPane;
    @FXML
    private GridPane detailGrid;
    @FXML 
    private Group gridGroup;


    public void setDetailPaneGrid(GridPane grid){
        detailPane.setContent(grid);
    }
    

}
