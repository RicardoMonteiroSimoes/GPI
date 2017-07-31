/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc_pgi;

import BuildingBlocks.Blocks.*;
import BuildingBlocksMaster.Input;
import BuildingBlocksMaster.LogicBlock;
import BuildingBlocksMaster.util.CreationUtil;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Ricardo
 */
public class SC_PGI extends Application{
    
    
    static ArrayList<LogicBlock> alBlocks = new ArrayList();
    static NOT not = new NOT();
    static OffDelay offD = new OffDelay();
    static OnDelay onD = new OnDelay();
    static GUI GUI = new GUI();
    static AND and;
    static Variable var = new Variable("Zeitkonstante", true);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        and = new AND(CreationUtil.createStandardInputList());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        alBlocks.add(not);
        alBlocks.add(offD);
        alBlocks.add(onD);
        alBlocks.add(and);
        GUI.start(primaryStage);
    }
    
}
