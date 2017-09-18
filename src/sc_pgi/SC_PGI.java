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
    static OR or = new OR();
    static XOR xor = new XOR();
    static OffDelay offD = new OffDelay();
    static OnDelay onD = new OnDelay();
    public static GUI GUI = new GUI();
    static SR sr = new SR();
    static RS rs = new RS();
    static AND and = new AND();
    static DoubleClick doubleClick = new DoubleClick();
    static STEPRELAY step = new STEPRELAY();
//    static KNXServerIn knxServerIn = new KNXServerIn("KNX Server IN", "Receives KNX Data");
    static MouseButton msbttn = new MouseButton();
    
//    static Variable var = new Variable("Zeitkonstante", true);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        alBlocks.add(not.getBlockObject());
        alBlocks.add(offD.getBlockObject());
        alBlocks.add(onD.getBlockObject());
        alBlocks.add(and.getBlockObject());
        alBlocks.add(doubleClick.getBlockObject());
        alBlocks.add(step.getBlockObject());
        alBlocks.add(rs.getBlockObject());
        alBlocks.add(sr.getBlockObject());
        alBlocks.add(or.getBlockObject());
        alBlocks.add(xor.getBlockObject());
//        alBlocks.add(var.getBlockObject());
//        alBlocks.add(knxServerIn.getBlockObject());
        alBlocks.add(msbttn.getBlockObject());
        GUI.start(primaryStage);
    }
    
}
