/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.view.master;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Ricardo
 */
public class guiHolder extends Application {

    private Stage primaryStage;
    private AnchorPane masterPane = new AnchorPane();
    private Scene scene;
    private FXMLLoader loader = new FXMLLoader();
    private guiController controller = new guiController();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Logic Editor");
        initGuiMaster();
        
        primaryStage.setOnCloseRequest(event -> System.exit(0));

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void initGuiMaster() {
        try {
            // Load root layout from fxml file.
            loader.setLocation(guiHolder.class.getResource("guiMaster.fxml"));
            masterPane = (AnchorPane) loader.load();
            controller = loader.getController();
            scene = new Scene(masterPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setGridPane(GridPane grid) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.setDetailPaneGrid(grid);
            }
        });
    }

}
