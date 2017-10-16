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
import BuildingBlocks.Blocks.NetworkIn;
import BuildingBlocks.Blocks.NetworkOut;
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
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
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
    private SplitPane root = new SplitPane();
    private BlockGraphic selectedBlock;

    @Override
    public void start (Stage primaryStage) {

       ScrollPane infoPane = new ScrollPane();
        VBox vBox = new VBox();
        scrollPaneRectangle.setWidth(4000);
        scrollPaneRectangle.setHeight(4000);
        scrollPaneRectangle.setFill(Color.BEIGE);
        scrollPaneRectangle.setOpacity(0.3);
        spLayout.setMaxSize(2000, 2000);
        spLayout.setPrefSize(800, 800);
        spItems.setMinWidth(160);
        spItems.setBackground(Background.EMPTY);
        spItems.setBorder(Border.EMPTY);

        vBox.setSpacing(25);
        vBox.setAlignment(Pos.TOP_CENTER);
        
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
        spItems.setMaxWidth(300);
        spItems.setMinWidth(150);
        root.getItems().add(spItems);
        root.getItems().add(spLayout);
        root.getItems().add(infoPane);
    
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
            case "Server In":
                newServerInBlock(x,y);
                break;
            case "StringComparator":
                newStringComparatorBlock(x,y);
                break;
            case "Server Out":
                createServerOutBlock(x,y);
                break;
            case "Create Packet":
                createCreatePacketBlock(x,y);
                break;
            case "HTTP Get":
                createHTTPGetBlock(x,y);
                break;
            default:
                System.out.println("No case for " + sName);
        }
    }  
    
    private void createHTTPGetBlock (double x, double y) {
        Http notTemp = new Http();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }
    
    private void createServerOutBlock (double x, double y) {
        NetworkOut notTemp = new NetworkOut();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }
    
    private void createCreatePacketBlock (double x, double y) {
        CreateServerPacket notTemp = new CreateServerPacket();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }
    
    private void newServerInBlock (double x, double y) {
        NetworkIn notTemp = new NetworkIn();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }
    
    private void newStringComparatorBlock (double x, double y) {
        StringComparator notTemp = new StringComparator();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }
    
    private void newSRBlock (double x, double y) {
        SR notTemp = new SR();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }
    
    private void newRSBlock (double x, double y) {
        RS notTemp = new RS();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }
    
    private void newSchrittschalterBlock (double x, double y) {
        STEPRELAY notTemp = new STEPRELAY();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }
    
    private void newMouseButtonBlock (double x, double y) {
        MouseInputButton notTemp = new MouseInputButton();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }

    private void newNOTBlock (double x, double y) {
        NOT notTemp = new NOT();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }

    private void newANDBlock (double x, double y) {
        AND notTemp = new AND();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }
    
    private void newDoubleClickBlock (double x, double y) {
        DoubleClick notTemp = new DoubleClick();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }
    
    public void notifyInput(String sMessage){
        System.out.println(sMessage);
    }

    private void newORBlock (double x, double y) {
        OR notTemp = new OR();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }

    private void newXORBlock (double x, double y) {
        XOR notTemp = new XOR();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }

    private void newOnDelayBlock (double x, double y) {
        OnDelay notTemp = new OnDelay();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }

    private void newOffDelayBlock (double x, double y) {
        OffDelay notTemp = new OffDelay();
        notTemp.setLayoutXY(x, y);
        scrollPaneGroup.getChildren().add(notTemp.getBlockGraphic());
        notTemp.addConnectionWatcher(connectionHandler);
        setBlockSelectFunction(notTemp);
    }
    
    public double getSideBarWidth(){
        return spItems.getWidth();
    }

    public void addCurve (Connection connection) {
        scrollPaneGroup.getChildren().add(1, connection.getCurve());
    }
    
    private void setBlockSelectFunction(BlockGraphic block){
        block.getBlockGraphic().setOnMouseClicked(new EventHandler<MouseEvent> () {
            @Override
            public void handle (MouseEvent t) {
                if (t.getButton().equals(MouseButton.PRIMARY)) {
                    try{
                        selectedBlock.unselect();
                    } catch (NullPointerException e){
                        
                    }
                    selectedBlock = block;
                    selectedBlock.select();
                }
            }
        });
    }
}
