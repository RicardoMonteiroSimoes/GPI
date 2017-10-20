/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old.BuildingBlocks.Blocks;

import old.BuildingBlocks.Master.BlockGraphic.Type;
import old.BuildingBlocks.Master.ContactPoint.Datatype;
import old.BuildingBlocks.Master.LogicBlock;
import ch.rs.logiceditor.model.util.network.ServerPacket;
import BuildingBlocks.Master.util.CreationUtil;
import BuildingBlocks.Master.util.Dialogs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ricardo
 */
public class CreateServerPacket extends LogicBlock {

    private ServerPacket serverPacketOn;
    private ServerPacket serverPacketOff;

    public CreateServerPacket() {
        super("Create Packet", "Creates a Packet to send", CreationUtil.createInput("Input", Datatype.BOOLEAN), false,
                CreationUtil.createOutput(Datatype.SERVERPACKET), Type.NETWORK);
        createPacketDialog();
    }

    @Override
    protected void Logic() {
        if (getInput()) {
            setOutput(serverPacketOn);
        } else {
            setOutput(serverPacketOff);
        }
    }

    private void createPacketDialog() {
        Group packetGroup = new Group();
        Label packetSettings = new Label("Packeteinstellungen");
        Label receiveIP = new Label("Empfangs-IP");
        TextField receiveIPField = new TextField();
        Label receivePort = new Label("Empfangs-Port");
        TextField receivePortField = new TextField();
        Label messageOn = new Label("Nachricht wenn Ein");
        TextField messageOnField = new TextField();
        Label messageOff = new Label("Nachricht wenn Aus");
        TextField messageOffField = new TextField();
        Label packetInfo = new Label("Wenn Nachricht wenn Aus leer ist wird beim ausschalten nichts gesendet!");

        Button changeMode = new Button("Übernehmen");
        changeMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    serverPacketOn = new ServerPacket(messageOnField.getText(), receiveIPField.getText(), Integer.valueOf(receivePortField.getText()));
                    if (!messageOffField.getText().equals("")) {
                        serverPacketOff = new ServerPacket(messageOffField.getText(), receiveIPField.getText(), Integer.valueOf(receivePortField.getText()));
                    } else {
                        serverPacketOff = ServerPacket.getEmptyPacket();
                    }

                } catch (Exception e) {
                }
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(packetSettings, 0, 0);
        grid.add(receiveIP, 0, 1);
        grid.add(receiveIPField, 1, 1);
        grid.add(receivePort, 0, 2);
        grid.add(receivePortField, 1, 2);
        grid.add(messageOn, 0, 3);
        grid.add(messageOnField, 1, 3);
        grid.add(messageOff, 0, 4);
        grid.add(messageOffField, 1, 4);
        grid.add(packetInfo, 0, 5);
        grid.add(changeMode, 1, 5);

        packetGroup.getChildren().add(grid);
        addDialogFunction(packetGroup);
    }
}
