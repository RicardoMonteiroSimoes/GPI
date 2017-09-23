/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.ContactPoint;
import BuildingBlocks.Master.ContactPoint.Datatype;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Network.ServerPacket;
import BuildingBlocks.Master.Network.ServerSend;
import BuildingBlocks.Master.util.CreationUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ricardo
 */
public class HttpGet extends LogicBlock {

    private String urlLogin = "https://192.168.0.100:8080/json/system/login?user=dssadmin\\&password=dssadmin";
    private String urlOn = "";
    private String urlOff = "";

    public HttpGet() {
        super("HTTP Get", "Visits a HTTP site", CreationUtil.createInput("Input", Datatype.BOOLEAN), Type.NETWORK);
        createHttpGetDialog();
    }

    @Override
    protected void Logic() {
        try {
            if (getInput()) {
                login();
                HttpURLConnection httpConnection = (HttpURLConnection) new URL(urlOn).openConnection();
                httpConnection.setRequestMethod("GET");
                System.out.println(httpConnection.getResponseMessage());
                httpConnection.disconnect();
                System.out.println("true done");
            } else {
                login();
                HttpURLConnection httpConnection = (HttpURLConnection) new URL(urlOff).openConnection();
                httpConnection.setRequestMethod("GET");
                System.out.println(httpConnection.getResponseMessage());
                httpConnection.disconnect();
                System.out.println("false done");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong @ httpGet " + e.getMessage());
        }
    }

    private void login() {
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(urlLogin).openConnection();
            httpConnection.setRequestMethod("GET");
            System.out.println(httpConnection.getResponseMessage());
            httpConnection.disconnect();
        } catch (Exception e) {

        }
    }

    private void createHttpGetDialog() {
        Group packetGroup = new Group();
        Label httpGetSettings = new Label("HTTP-GET Einstellungen");
        Label visitURLOn = new Label("URL bei Ein");
        TextField visitURLOnField = new TextField();
        Label visitURLOff = new Label("URL bei Aus");
        TextField visitURLOffField = new TextField();

        Button changeMode = new Button("Übernehmen");
        changeMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    urlOn = visitURLOnField.getText();
                    urlOff = visitURLOffField.getText();
                } catch (Exception e) {

                }
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(httpGetSettings, 0, 0);
        grid.add(visitURLOn, 0, 1);
        grid.add(visitURLOnField, 1, 1);
        grid.add(visitURLOff, 0, 2);
        grid.add(visitURLOffField, 1, 2);
        grid.add(changeMode, 0, 5);

        packetGroup.getChildren().add(grid);
        addDialogFunction(packetGroup);
    }

}
