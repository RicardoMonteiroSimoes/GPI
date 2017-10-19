/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc_pgi;

import old.BuildingBlocks.Blocks.AND;
import old.BuildingBlocks.Blocks.OR;
import old.BuildingBlocks.Blocks.CreateServerPacket;
import old.BuildingBlocks.Blocks.MouseInputButton;
import old.BuildingBlocks.Blocks.StringComparator;
import old.BuildingBlocks.Blocks.Http;
import old.BuildingBlocks.Blocks.XOR;
import ch.rs.logiceditor.model.blocks.OffDelay;
import ch.rs.logiceditor.model.blocks.OnDelay;
import ch.rs.logiceditor.model.blocks.STEPRELAY;
import ch.rs.logiceditor.model.blocks.RS;
import ch.rs.logiceditor.model.blocks.SR;
import ch.rs.logiceditor.model.blocks.NOT;
import ch.rs.logiceditor.model.blocks.DoubleClick;
import old.BuildingBlocks.Master.BlockGraphic;
import old.BuildingBlocks.Master.Input;
import old.BuildingBlocks.Master.LogicBlock;
import old.BuildingBlocks.Blocks.NetworkIn;
import old.BuildingBlocks.Blocks.NetworkOut;
import BuildingBlocks.Master.util.CreationUtil;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
    static NetworkIn nwin = new NetworkIn(true);
    static StringComparator scmp = new StringComparator();
//    static KNXServerIn knxServerIn = new KNXServerIn("KNX Server IN", "Receives KNX Data");
    static MouseInputButton msbttn = new MouseInputButton();
    static NetworkOut nwout = new NetworkOut();
    static CreateServerPacket srvPktCrtr = new CreateServerPacket();
    static Http httpget = new Http();
    
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
        alBlocks.add(nwin.getBlockObject());
        alBlocks.add(scmp.getBlockObject());
        alBlocks.add(msbttn.getBlockObject());
        alBlocks.add(nwout.getBlockObject());
        alBlocks.add(srvPktCrtr.getBlockObject());
        alBlocks.add(httpget.getBlockObject());
        GUI.start(primaryStage);
    }
    
}
