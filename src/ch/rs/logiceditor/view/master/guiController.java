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
public class guiController {

    @FXML
    private ScrollPane detailPane;
    @FXML
    private GridPane detailGrid;
    @FXML
    private Group gridGroup;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab extenderTab;
    @FXML
    private ListView listView;
    @FXML
    private VBox blockContainer;

    public void setDetailPaneGrid(GridPane grid) {
        detailPane.setContent(grid);
    }

    public void addAdditionalTab() {
        Tab newTab = SpecialGuiElements.newLogicTab();
        tabPane.getTabs().add(tabPane.getTabs().size() - 1, newTab);
        tabPane.getSelectionModel().select(newTab);
        setTabFunctions(newTab);

    }

    private void setTabFunctions(Tab tab) {
        tab.getContextMenu().getItems().get(0).setOnAction(event
                -> tab.setText(SpecialGuiElements.stringInputDialog("Rename Tab")));
        tab.getContextMenu().getItems().get(1).setOnAction(event
                -> tabPane.getTabs().remove(tab));
    }

    public void addBlock(GraphicBlock block) {
        blockContainer.getChildren().add(block.getBlockGraphic());
    }

}
