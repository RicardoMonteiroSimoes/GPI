/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.view.master;

import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.view.util.SpecialGuiElements;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Ricardo
 */
public class GuiVariableDeclarations {

    @FXML
    protected ScrollPane detailPane;
    @FXML
    protected GridPane detailGrid;
    @FXML
    protected Group gridGroup;
    @FXML
    protected TabPane tabPane;
    @FXML
    protected Tab extenderTab;
    @FXML
    protected ListView listView;
    @FXML
    protected VBox blockContainer;
    @FXML
    protected MenuItem newProject;
    @FXML
    protected MenuItem openProject;
    @FXML
    protected MenuItem save;
    @FXML
    protected MenuItem saveAs;
    @FXML
    protected MenuItem close;


}
