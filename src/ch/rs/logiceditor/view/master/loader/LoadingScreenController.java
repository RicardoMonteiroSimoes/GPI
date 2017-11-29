package ch.rs.logiceditor.view.master.loader;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadingScreenController {

    @FXML
    private Text loadingText = new Text("Loading...");
    @FXML
    private ProgressBar progressBar = new ProgressBar();

    private boolean succesfull = true;


    public LoadingScreenController(Stage primaryStage){

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
