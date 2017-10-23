/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.view.master;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ricardo
 */
public class guiHolder extends Application {

    private Stage primaryStage;
    private AnchorPane masterPane = new AnchorPane();
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Logic Editor");
        initGuiMaster();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void initGuiMaster() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(guiHolder.class.getResource("guiMaster.fxml"));
            masterPane = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            scene = new Scene(masterPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
