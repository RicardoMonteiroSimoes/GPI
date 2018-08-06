package ch.rs.logiceditor.view.master.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadingScreenController {

    @FXML
    private Text loadingText;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ImageView appImage;

    private boolean succesfull = true;


    public void setLoadingText(String loadingText){
        this.loadingText.setText(loadingText);
    }

    public void advanceProgressBar(double value){
        progressBar.setProgress(progressBar.getProgress()+value);
    }

    public boolean wasSuccesfull(){
        return succesfull;
    }

    public void setImage(Image image){
        appImage.setImage(image);
    }
}
