/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPI;

import ch.rs.logiceditor.controller.master.ApplicationInitialization;
import ch.rs.logiceditor.model.controller.LogicBlockAdapter;
import ch.rs.logiceditor.model.controller.LogicHolder;
import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.model.master.LogicPanel;
import ch.rs.logiceditor.model.util.ClassData;
import ch.rs.logiceditor.model.util.ClassDataSerializer;
import ch.rs.logiceditor.view.master.GuiHolder;
import ch.rs.reflectorgrid.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Ricardo
 */
public class Main extends Application {

    static LogicHolder holder = new LogicHolder();
    static GridPane grid = new GridPane();
    static ReflectorGrid rgrid = new ReflectorGrid();
//    static Variable var = new Variable("Zeitkonstante", true);
    static List<Class<? extends LogicBlock>> classes = new LinkedList<>();
    static GuiHolder gui;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        launch(args);
    }

    public static void createFile(){


        LogicHolder holder = new LogicHolder();
        LogicPanel panel = new LogicPanel();
        panel.setName("testPanel");
        holder.addPanel(panel);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ClassData.class,
                new ClassDataSerializer()
        );
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        System.out.println(gson.toJson(holder));
        holder.startLogicHolder();
        rgrid.setNodeWidthLimit(100);
//        grid = rgrid.transfromIntoGrid(tcpIn);
    }

    public static void loadFile() throws FileNotFoundException {

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(ClassData.class,
                new ClassDataSerializer()
        );
        gsonBuilder.registerTypeAdapter(LogicBlock.class, new LogicBlockAdapter());
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ricardo\\Documents\\GitHub\\SC_PGI\\src\\GPI\\savefile.json"));
        holder = gson.fromJson(reader, LogicHolder.class);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        new ApplicationInitialization().initialize(primaryStage);
    }

}
