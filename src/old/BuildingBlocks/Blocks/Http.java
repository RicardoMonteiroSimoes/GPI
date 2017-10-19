/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old.BuildingBlocks.Blocks;

import old.BuildingBlocks.Master.ContactPoint;
import old.BuildingBlocks.Master.ContactPoint.Datatype;
import old.BuildingBlocks.Master.LogicBlock;
import ch.rs.logiceditor.model.util.ServerPacket;
import BuildingBlocks.Master.Network.ServerSend;
import BuildingBlocks.Master.util.CreationUtil;
import BuildingBlocks.Master.util.Dialogs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.layout.GridPane;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author Ricardo
 */
public class Http extends LogicBlock {

    private String urlLogin = "";
    private String urlOn = "";
    private String urlOff = "";
    private String httpMethod = "GET";
    private boolean needsLogin = false;
    HttpsURLConnection httpConnection;

    public Http() {
        super("HTTP Get", "Visits a HTTP site", CreationUtil.createInput("Input", Datatype.BOOLEAN), Type.NETWORK);
        createHttpGetDialog();
    }

    @Override
    protected void Logic() {
        try {
            if (getInput()) {
                if (needsLogin) {
                    login();
                }
                httpConnection = (HttpsURLConnection) new URL(urlOn).openConnection();
                httpConnection.setRequestMethod(httpMethod);
                System.out.println(httpConnection.getResponseMessage());
                httpConnection.disconnect();
                System.out.println("true done");
            } else {
                if (needsLogin) {
                    login();
                }
                httpConnection = (HttpsURLConnection) new URL(urlOff).openConnection();
                httpConnection.setRequestMethod(httpMethod);
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
            httpConnection = (HttpsURLConnection) new URL(urlLogin).openConnection();
            httpConnection.setRequestMethod(httpMethod);
            System.out.println(httpConnection.getResponseMessage());
            httpConnection.disconnect();
        } catch (Exception e) {

        }
    }

    private void createHttpGetDialog() {
        Group packetGroup = new Group();
        Label httpGetSettings = new Label("HTTP Einstellungen");
        Label visitURLOn = new Label("URL bei Ein");
        TextField visitURLOnField = new TextField();
        Label needsLoginLabel = new Label("Braucht login?");
        CheckBox needsLoginCheckBox = new CheckBox();
        Label visitURLOff = new Label("URL bei Aus");
        TextField visitURLOffField = new TextField();
        Label loginURL = new Label("Login URL");
        TextField loginURLField = new TextField();
        Label httpType = new Label("HTTP Methode");

        ComboBox httpMethodeBox = new ComboBox<String>();
        httpMethodeBox.getItems().add("GET");
        httpMethodeBox.getItems().add("POST");
        httpMethodeBox.getSelectionModel().select(httpMethod);

        needsLoginCheckBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                needsLogin = needsLoginCheckBox.isSelected();
            }
        });

        Button changeMode = new Button("Übernehmen");
        changeMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    urlOn = visitURLOnField.getText();
                    urlOff = visitURLOffField.getText();
                    urlLogin = loginURLField.getText();
                    httpMethod = (String) httpMethodeBox.getSelectionModel().getSelectedItem();
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
        grid.add(needsLoginLabel, 0, 3);
        grid.add(needsLoginCheckBox, 1, 3);
        grid.add(loginURL, 0, 4);
        grid.add(loginURLField, 1, 4);
        grid.add(changeMode, 0, 5);

        packetGroup.getChildren().add(grid);
        addDialogFunction(packetGroup);
    }

}
