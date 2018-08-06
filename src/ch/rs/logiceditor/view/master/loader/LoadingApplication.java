package ch.rs.logiceditor.view.master.loader;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoadingApplication extends Application {

    private Parent loadingPane;
    private FXMLLoader loader;
    private LoadingScreenController lsc;
    private Stage stage;

    private double incrementationAmount;


    public LoadingApplication(int amountOfLoading) throws IOException{

        calculateProgressSteps(amountOfLoading);

    }

    private void calculateProgressSteps(int amountOfLoading){
        incrementationAmount = 1.0 / amountOfLoading;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        lsc = new LoadingScreenController();
        loader = new FXMLLoader();
        System.out.println("Setup stuff");

        loader.setLocation(LoadingApplication.class.getResource("LoadingScreen.fxml"));
        try {
            loadingPane = (AnchorPane) loader.load();

            lsc = loader.getController();

            System.out.println("done loading");

            stage.setTitle("Loading Logic Editor...");
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.centerOnScreen();

            stage.setScene(new Scene(loadingPane));

            stage.show();
        }
        catch (IOException e){
            System.out.println("Error in run of loading screen " + e.getMessage());
        }
    }

    public void incrementProgress(){
        Platform.runLater(() -> lsc.advanceProgressBar(incrementationAmount));

    }

    public void setLoadingText(String text){
        Platform.runLater(() ->lsc.setLoadingText(text));
    }

    public boolean isFinished(){
        return lsc.isFinished();
    }
}
