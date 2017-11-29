package ch.rs.logiceditor.view.master.loader;

import ch.rs.logiceditor.view.master.GuiHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadingScreenController {

    @FXML
    private Text loadingText = new Text("Loading...");
    @FXML
    private ProgressBar progressBar = new ProgressBar();

    private boolean succesfull = true;
    private FXMLLoader loader = new FXMLLoader();


    public LoadingScreenController(Stage primaryStage){


        loader.setLocation(GuiHolder.class.getResource("LoadingScreen.fxml"));
        try {
            Parent masterPane = (AnchorPane) loader.load();
            primaryStage.setScene(new Scene(masterPane));
            primaryStage.show();
        } catch (IOException e){}
        //set succesfull to false if something doesnt work the right way


    }

    public void setLoadingText(String loadingText){
        this.loadingText.setText(loadingText);
    }

    public void advanceProgressBar(double value){
        progressBar.setProgress(progressBar.getProgress()+value);
    }

    public boolean wasSuccesfull(){
        return succesfull;
    }
}
