/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc_pgi;

import BuildingBlocks.Master.util.CreationUtil;
import BuildingBlocks.Blocks.*;
import BuildingBlocks.Master.BlockGraphic;
import BuildingBlocks.Master.Connection;
import BuildingBlocks.Master.ConnectionHandler;
import BuildingBlocks.Master.util.*;
import BuildingBlocks.Master.util.Dialogs;
import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Output;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GUI extends Application {

    private ConnectionHandler connectionHandler = new ConnectionHandler();
    private boolean bNewBlockReady = false;
    private boolean bNewConnectionReady = false;
    private double startX = 0.0;
    private double startY = 0.0;
    private Scene scene;
    private long currentTime;
    private long lastTime;
    private Group grpLayout = new Group();
    private boolean bDoubleClick = false;
    private Group scrollPaneGroup = new Group();
    private ScrollPane spItems = new ScrollPane();
    private ScrollPane spLayout = new ScrollPane();
    private Rectangle scrollPaneRectangle = new Rectangle();

    @Override
    public void start (Stage primaryStage) {

        HBox root = new HBox();
        VBox vBox = new VBox();
        scrollPaneRectangle.setWidth(4000);
        scrollPaneRectangle.setHeight(4000);
        scrollPaneRectangle.setFill(Color.BEIGE);
        scrollPaneRectangle.setOpacity(0.3);
        spLayout.setMaxSize(2000, 2000);
        spLayout.setMinSize(1000, 1000);
        spLayout.setPrefSize(800, 800);
        spItems.setMaxHeight(800);
        spItems.setPrefWidth(150);
        spItems.setMinWidth(120);
        spItems.setBackground(Background.EMPTY);
        spItems.setBorder(Border.EMPTY);

        vBox.setSpacing(25);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().add(new HBox());
        
        for (BlockGraphic bg : SC_PGI.alBlocks) {
            bg.deactivateEvents(true);
            bg.getBlockGraphic().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent t) {
                    long diff = 0;
                    currentTime = System.currentTimeMillis();
                    if (lastTime != 0 && currentTime != 0) {
                        diff = currentTime - lastTime;
                        if (diff <= 215) {
                            bDoubleClick = true;
                        } else {
                            bDoubleClick = false;
                        }
                    }
                    lastTime = currentTime;
                    if (bDoubleClick) {
                        bNewBlockReady = true;
                        readyNewBlock(bg.getName());
                    }
                
            }
        });
            vBox.getChildren().add(bg.getBlockGraphic());
        }

        spItems.setContent(vBox);
        scrollPaneGroup.getChildren().add(scrollPaneRectangle);
        spLayout.setContent(scrollPaneGroup);
        root.getChildren().add(spItems);
        root.getChildren().add(spLayout);

        scene = new Scene(root, 300, 250);

        primaryStage.setScene(scene);

        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle (WindowEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run () {
                        System.exit(0);
                    }
                });
            }
        });
        
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);

        primaryStage.show();
    }

    private void readyNewBlock (String sName) {
        scene.setCursor(new ImageCursor(new Image("BuildingBlocks/Master/util/icons8-Hinzufügen-64.png")));
        scrollPaneRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent t) {
                if (bNewBlockReady) {
                    scene.setCursor(Cursor.DEFAULT);
                    bNewBlockReady = false;
                    addNewBlock(sName, t.getX(), t.getY());
                }
            }
        });
    }

    private void addNewBlock (String sName, double x, double y) {
        switch (sName) {
            case "NOT":
                newNOTBlock(x, y);
                break;
            case "OR":
                newORBlock(x, y);
                break;
            case "XOR":
                newXORBlock(x, y);
                break;
            case "AND":
                newANDBlock(x, y);
                break;
            case "OnDelay":
                newOnDelayBlock(x, y);
                break;
            case "OffDelay":
                newOffDelayBlock(x, y);
                break;
            case "Button":
                newMouseButtonBlock(x, y);              
                break;
            case "Doppelklick":
                newDoubleClickBlock(x, y);
                break;
            case "SR":
                newSRBlock(x,y);
                break;
            case "RS":
                newRSBlock(x,y);
                break;
            case "Schrittschalter":
                newSchrittschalterBlock(x,y);
                break;
            default:
                System.out.println("No case for " + sName);
        }
    }    
    
    private void newSRBlock (double x, double y) {
        SR notTemp = new SR();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
    }
    
    private void newRSBlock (double x, double y) {
        RS notTemp = new RS();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
    }
    
    private void newSchrittschalterBlock (double x, double y) {
        STEPRELAY notTemp = new STEPRELAY();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
    }
    
    private void newMouseButtonBlock (double x, double y) {
        MouseInputButton notTemp = new MouseInputButton();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
    }

    private void newNOTBlock (double x, double y) {
        NOT notTemp = new NOT();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
    }

    private void newANDBlock (double x, double y) {
        AND notTemp = new AND();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
    }
    
    private void newDoubleClickBlock (double x, double y) {
        DoubleClick notTemp = new DoubleClick();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
    }
    
    public void notifyInput(String sMessage){
        System.out.println(sMessage);
    }

    private void newORBlock (double x, double y) {
        OR notTemp = new OR();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
    }

    private void newXORBlock (double x, double y) {
        XOR notTemp = new XOR();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
    }

    private void newOnDelayBlock (double x, double y) {
        OnDelay notTemp = new OnDelay();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
    }

    private void newOffDelayBlock (double x, double y) {
        OffDelay notTemp = new OffDelay();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
    }
    
    public void addLine (Connection connection){
        scrollPaneGroup.getChildren().add(connection.getLine());
    }
    
    public double getSideBarWidth(){
        return spItems.getWidth();
    }
}
