/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPI;

import ch.rs.logiceditor.model.blocks.AND;
import ch.rs.logiceditor.model.blocks.CreateServerPacket;
import ch.rs.logiceditor.model.blocks.OffDelay;
import ch.rs.logiceditor.model.blocks.TCPIn;
import ch.rs.logiceditor.model.blocks.TCPOut;
import ch.rs.logiceditor.model.controller.LogicBlockAdapter;
import ch.rs.logiceditor.model.controller.LogicHolder;
import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.LogicBlock;
import ch.rs.logiceditor.model.master.LogicPanel;
import ch.rs.logiceditor.model.util.ClassData;
import ch.rs.logiceditor.model.util.ClassDataSerializer;
import ch.rs.logiceditor.view.master.guiHolder;
import ch.rs.reflectorgrid.ReflectorGrid;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * @author Ricardo
 */
public class Main extends Application{

    static LogicHolder holder = new LogicHolder();
    static GridPane grid = new GridPane();
    static ReflectorGrid rgrid = new ReflectorGrid();
//    static Variable var = new Variable("Zeitkonstante", true);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
    //    loadFile();
        createFile();
        launch(args);

    }

    public static void createFile() {
        LogicHolder holder = new LogicHolder();
        LogicPanel panel = new LogicPanel();
        panel.setName("testPanel");
        holder.addPanel(panel);
        OffDelay delay = new OffDelay();
        TCPIn tcpIn = new TCPIn();
        TCPOut tcpOut = new TCPOut();
        tcpIn.setPort(5250);
        tcpIn.setTurnOffMessage("false");
        tcpIn.setTurnOnMessage("true");
        CreateServerPacket csp = new CreateServerPacket();
        csp.setServerPacket("on", "127.0.0.1", 15000);
        holder.addLogicBlockToPane(csp, panel);
        holder.addLogicBlockToPane(tcpIn, panel);
        holder.addLogicBlockToPane(tcpOut, panel);
        holder.addLogicBlockToPane(delay, panel);
        panel.addConnection(tcpIn, 0, csp, 0);
        panel.addConnection(csp, 0, tcpOut, 0);
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(ClassData.class,
                new ClassDataSerializer()
        );
        gsonBuilder.registerTypeAdapter(LogicBlock.class, new LogicBlockAdapter());
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        //holder.startLogicHolder();
        //System.out.println(gson.toJson(holder));
        holder.startLogicHolder();
        grid = rgrid.turnObjectIntoGrid(tcpIn);
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
  //      holder.startLogicHolder();
        guiHolder gui = new guiHolder();
        gui.start(primaryStage);
        gui.setGridPane(grid);
    }

}
