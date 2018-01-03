package ch.rs.logiceditor.view.master;

import ch.rs.logiceditor.controller.interfaces.LoadingScreenInterface;
import ch.rs.logiceditor.view.master.controller.LoadingScreenController;
import ch.rs.logiceditor.view.master.utilities.DialogCollection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoadingScreen implements LoadingScreenInterface {

    private LoadingScreenController loadingController = new LoadingScreenController();
    private Scene loadingScene;
    private Stage loadingStage;


    public LoadingScreen(){
        loadingStage = new Stage();
        try{
            FXMLLoader loaderloader = new FXMLLoader(getClass()
                    .getResource("/fxml/LoadingScreen.fxml"));
            AnchorPane loadingPane = (AnchorPane) loaderloader.load();
            loadingController = loaderloader.getController();

            loadingScene = new Scene(loadingPane);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        loadingController.setImage(new Image(getClass().getResource("/images/LoadingScreen.jpg").toString()));
    }

    public void initLoadingScreen(){
        loadingStage.setScene(loadingScene);
        loadingStage.initStyle(StageStyle.UNDECORATED);
        loadingStage.show();
    }

    @Override
    public void setLoadingText(String text) {
        System.out.println(text);
        loadingController.setLoadingText(text);
    }

    @Override
    public void addToProgressBar(double value) {
        loadingController.advanceProgressBar(value);
    }

    @Override
    public void closeLoadingScreen() {
        loadingStage.close();
    }

    @Override
    public String createDialog(String title, String message, String propertieName) {
        switch(propertieName){
            case "pathToBlocks":
                return DialogCollection.getPathDialog(title, message);
            case "username":
                return DialogCollection.stringInputDialog(title, message);
            case "ERROR":
                DialogCollection.errorDialog(title, message);
                return null;
            default:
                System.out.println("Error, no case for " + propertieName);
        }
        return null;
    }
}
