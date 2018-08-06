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

public class LoadingScreenController extends LoadingScreenVariables{




    private boolean finished = false;

    public void setLoadingText(String loadingText){
        if(this.loadingText == null){
            System.out.println("text is null");
            return;
        }
        this.loadingText.setText(loadingText);
    }

    public void advanceProgressBar(double value){
        if(progressBar == null){
            System.out.println("bar is null");
            return;
        }
        progressBar.setProgress(progressBar.getProgress()+value);
        if (progressBar.getProgress() == 1.0) {
            finished = true;
        }
    }

    public boolean isFinished(){
        return finished;
    }

}
