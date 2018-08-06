package ch.rs.logiceditor.view.master.controller;

import ch.rs.logiceditor.model.controller.LogicHolder;
import ch.rs.logiceditor.view.master.GraphicBlock;
import ch.rs.logiceditor.view.util.SpecialGuiElements;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;



public class GuiController extends GuiVariableDeclarations {

    private LogicHolder logicHolder;

    public GuiController(){

    }

    public GuiController(LogicHolder logicHolder){
        this.logicHolder = logicHolder;
    }

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

    public void saveAs() {
        System.out.println("saveAs");
    }

}
