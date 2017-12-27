/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.view.master;

import ch.rs.logiceditor.model.controller.LogicHolder;
import ch.rs.logiceditor.model.master.LogicBlock;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ch.rs.logiceditor.view.master.controller.GuiController;
import ch.rs.logiceditor.view.master.controller.LoadingScreenController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ricardo
 */
public class GuiHolder extends Application {

    private Stage primaryStage;
    private AnchorPane masterPane = new AnchorPane();
    private AnchorPane loadingPane = new AnchorPane();
    private Scene scene;
    private Scene loadingScene;
    private FXMLLoader loader = new FXMLLoader();
    private GuiController guiController;
    private LoadingScreenController loadingController;
    private LinkedList<GraphicBlock> blocks = new LinkedList<>();
    private LogicHolder logicHolder;

    public GuiHolder(LogicHolder logicHolder){
        this.logicHolder = logicHolder;
        guiController = new GuiController(logicHolder);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initLoadingScreen();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Logic Editor");

        Stage loadingStage = new Stage();
        loadingStage.setScene(loadingScene);
        //initGuiMaster();
        
        //primaryStage.setOnCloseRequest(event -> System.exit(0));

        //primaryStage.setScene(scene);
        loadingController.advanceProgressBar(0.2);
        loadingController.setImage(new Image(getClass().getResource("/images/LoadingScreen.jpg").toString()));
        loadingStage.initStyle(StageStyle.UNDECORATED);
        loadingStage.show();

    }

    private void initLoadingScreen(){
        try{
            FXMLLoader loaderloader = new FXMLLoader(getClass()
                    .getResource("/fxml/LoadingScreen.fxml"));
            loadingPane = (AnchorPane) loaderloader.load();
            loadingController = loaderloader.getController();

            loadingScene = new Scene(loadingPane);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void initializeBlockList(List<Class<? extends LogicBlock>> blocks){
        for(Class<? extends LogicBlock> block : blocks){
            try{
            this.blocks.add(new GraphicBlock(block.newInstance()));
                guiController.addBlock(this.blocks.getLast());
            } catch (Exception e){
                System.err.println("GUI " + e.getMessage());
            }
        }
    }

    public void initGuiMaster() {
        try {
            // Load root layout from fxml file.
            loader.setLocation(getClass().getResource("/fxml/GuiMaster.fxml"));
            masterPane = (AnchorPane) loader.load();
            guiController = loader.getController();

            scene = new Scene(masterPane);
        } catch (IOException e) {
            System.out.println("Error at initGuiMaster: " + e.getMessage());
            System.out.println(e.getLocalizedMessage());

        }
    }

    public void setGridPane(GridPane grid) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                controller.setDetailPaneGrid(grid);
            }
        });
    }

}
