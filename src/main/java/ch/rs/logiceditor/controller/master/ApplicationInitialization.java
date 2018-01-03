package ch.rs.logiceditor.controller.master;

import ch.rs.logiceditor.controller.BlocksLoader;
import ch.rs.logiceditor.controller.PropertiesLoader;
import ch.rs.logiceditor.controller.interfaces.LoadingScreenInterface;
import ch.rs.logiceditor.model.controller.LogicHolder;
import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.view.master.GuiHolder;
import ch.rs.logiceditor.view.master.LoadingScreen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ApplicationInitialization {

    private PropertiesLoader properties;
    private LoadingScreenInterface loadingScreen;
    private BlocksLoader blockLoader;
    private GuiHolder gui;

    public void initialize(Stage primaryStage) throws IOException{
        properties = new PropertiesLoader();
        blockLoader = new BlocksLoader();
        initializeView();
        loadingScreen.setLoadingText("TEST");
        startLoadingProperties();
        List<Class<? extends LogicBlock>> list = startLoadingBlocks();
        loadingScreen.setLoadingText("Found " + list.size() + " blocks...");
        loadingScreen.setLoadingText("Initializing GUI...");
        startGUI(list);
    }

    public  void initializeView(){
        loadingScreen = new LoadingScreen();
        gui = new GuiHolder(new LogicHolder());
    }

    public void startGUI(List<Class<? extends LogicBlock>> list){
        gui.initGuiMaster();
        loadingScreen.setLoadingText("Initializing Blocks...");
        gui.initializeBlockList(list);
        try { gui.start(new Stage()); } catch(Exception e){
            loadingScreen.createDialog("Error", "Couldnt start GUI " + e.getMessage(), "ERROR");
        }
        loadingScreen.closeLoadingScreen();
    }

    private List<Class<? extends LogicBlock>> startLoadingBlocks(){
        try {
            loadingScreen.setLoadingText("Looking for Blocks...");
            return blockLoader.loadBlocks(properties.getPropertie("pathToBlocks"));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void startLoadingProperties() throws IOException{
        loadingScreen.initLoadingScreen();
        loadingScreen.setLoadingText("Loading properties...");
        properties.startLoading(loadingScreen);
    }

    public void initializeModel(){

    }

    public String getPropertie(String propertieName){
        return properties.getPropertie(propertieName);
    }
}
