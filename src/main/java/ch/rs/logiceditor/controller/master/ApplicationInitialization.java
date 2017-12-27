package ch.rs.logiceditor.controller.master;

import ch.rs.logiceditor.controller.BlocksLoader;
import ch.rs.logiceditor.controller.PropertiesLoader;
import ch.rs.logiceditor.controller.interfaces.LoadingScreenInterface;
import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.view.master.LoadingScreen;
import ch.rs.logiceditor.view.master.controller.LoadingScreenController;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ApplicationInitialization {

    private PropertiesLoader properties;
    private LoadingScreenInterface loadingScreen;
    private BlocksLoader blockLoader;

    public void initialize(Stage primaryStage) throws IOException{
        properties = new PropertiesLoader();
        blockLoader = new BlocksLoader();
        initializeView();
        startLoadingProperties();
        startLoadingBlocks();
    }

    public void initializeView(){
        loadingScreen = new LoadingScreen();
    }

    private void startLoadingBlocks(){
        try {
            blockLoader.loadBlocks(properties.getPropertie("pathToBlocks"), loadingScreen);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void startLoadingProperties() throws IOException{
        properties.startLoading(loadingScreen);
    }

    public void initializeModel(){

    }

    public String getPropertie(String propertieName){
        return properties.getPropertie(propertieName);
    }
}
