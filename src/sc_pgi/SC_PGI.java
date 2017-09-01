/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc_pgi;

import BuildingBlocks.Blocks.*;
import BuildingBlocks.Master.BlockGraphic;
import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.util.CreationUtil;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Ricardo
 */
public class SC_PGI extends Application{
    
    
    static ArrayList<BlockGraphic> alBlocks = new ArrayList();
    static NOT not = new NOT();
    static OffDelay offD = new OffDelay();
    static OnDelay onD = new OnDelay();
    public static GUI GUI = new GUI();
    static AND and;
    static STEPRELAY step = new STEPRELAY();
    
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
        alBlocks.add(not.getBlockObject());
        alBlocks.add(offD.getBlockObject());
        alBlocks.add(onD.getBlockObject());
        alBlocks.add(and.getBlockObject());
        alBlocks.add(step.getBlockObject());
        alBlocks.add(var.getBlockObject());
        GUI.start(primaryStage);
    }
    
}
