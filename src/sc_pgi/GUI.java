/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc_pgi;

import BuildingBlocks.Blocks.*;
import BuildingBlocksMaster.util.*;
import BuildingBlocks.Master.Dialogs;
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
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GUI extends Application {

    private boolean bNewBlockReady = false;
    private boolean bNewConnectionReady = false;
    private double startX = 0.0;
    private double startY = 0.0;
    private Scene scene;
    private long currentTime;
    private long lastTime;
    private Group grpLayout = new Group();
    private boolean bDoubleClick = false;
    private StackPane stpLayout = new StackPane();
    private ScrollPane spItems = new ScrollPane();
    private ScrollPane spLayout = new ScrollPane();

    @Override
    public void start (Stage primaryStage) {

        HBox root = new HBox();
        VBox vBox = new VBox();
//        stpLayout.setHeight(1000);
//        stpLayout.setWidth(1000);
        spLayout.setMaxSize(1000, 1000);
        spLayout.setMinSize(1000, 1000);
        spItems.setMaxHeight(800);
        spItems.setPrefWidth(100);
        spItems.setBackground(Background.EMPTY);
        spItems.setBorder(Border.EMPTY);

        vBox.setSpacing(25);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().add(new HBox());
        for (LogicBlock lb : SC_PGI.alBlocks) {
            lb.deactivateEvents(true);
            lb.getBlock().setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                        scene.setCursor(new ImageCursor(new Image("BuildingBlocksMaster/util/icons8-Hinzufügen-64.png")));
                        spLayout.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle (MouseEvent t) {
                                if (bNewBlockReady) {
                                    addNewBlock(lb.getName(), t.getX(), t.getY());
                                    bNewBlockReady = false;
                                    scene.setCursor(Cursor.DEFAULT);
                                }
                            }
                        });

                    }
                }
            });
            vBox.getChildren().add(lb.getBlock());
        }
        vBox.getChildren().add(SC_PGI.var.getBlock());

        spItems.setContent(vBox);
        spLayout.setContent(stpLayout);
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

        primaryStage.show();
    }

    private void readyNewBlock (String sName) {
        scene.setCursor(new ImageCursor(new Image("BuildingBlocksMaster/util/icons8-Hinzufügen-64.png")));
        stpLayout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent t) {
                if (bNewBlockReady) {
                    addNewBlock(sName, t.getSceneX(), t.getSceneY());
                }
            }
        });
        bNewBlockReady = false;
        scene.setCursor(Cursor.DEFAULT);
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
            default:
                System.out.println("No case for " + sName);
        }
    }

    private void newNOTBlock (double x, double y) {
        NOT notTemp = new NOT();
        notTemp.setLayoutXY(x, y);
        for (Output out : notTemp.getGUIOutputs()) {
            out.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked output");
                    bNewConnectionReady = true;
                    startX = t.getX();
                    startY = t.getY();
                }
            });
        }
        for (Input in : notTemp.getGUIInputs()) {
            in.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked input");
                    if (bNewConnectionReady) {
                        Line lineTemp = new Line();
                        lineTemp.setStartX(startX);
                        lineTemp.setStartY(startY);
                        lineTemp.setEndX(t.getX());
                        lineTemp.setEndY(t.getY());
                        stpLayout.getChildren().add(lineTemp);
                        startX = 0.0;
                        startY = 0.0;
                        bNewConnectionReady = false;
                    }
                }
            });
        }
        stpLayout.getChildren().add(notTemp.getBlock());
    }

    private void newANDBlock (double x, double y) {
        AND notTemp = new AND(CreationUtil.createStandardInputList());
        notTemp.setLayoutXY(x, y);
        for (Output out : notTemp.getGUIOutputs()) {
            out.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked output");
                    bNewConnectionReady = true;
                    startX = t.getX();
                    startY = t.getY();
                }
            });
        }
        for (Input in : notTemp.getGUIInputs()) {
            in.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked input");
                    if (bNewConnectionReady) {
                        Line lineTemp = new Line();
                        lineTemp.setStartX(startX);
                        lineTemp.setStartY(startY);
                        lineTemp.setEndX(t.getX());
                        lineTemp.setEndY(t.getY());
                        stpLayout.getChildren().add(lineTemp);
                        startX = 0.0;
                        startY = 0.0;
                        bNewConnectionReady = false;
                    }
                }
            });
        }
        stpLayout.getChildren().add(notTemp.getBlock());
    }

    private void newORBlock (double x, double y) {
        OR notTemp = new OR(CreationUtil.createStandardInputList());
        notTemp.setLayoutXY(x, y);
        for (Output out : notTemp.getGUIOutputs()) {
            out.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked output");
                    bNewConnectionReady = true;
                    startX = t.getX();
                    startY = t.getY();
                }
            });
        }
        for (Input in : notTemp.getGUIInputs()) {
            in.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked input");
                    if (bNewConnectionReady) {
                        Line lineTemp = new Line();
                        lineTemp.setStartX(startX);
                        lineTemp.setStartY(startY);
                        lineTemp.setEndX(t.getX());
                        lineTemp.setEndY(t.getY());
                        stpLayout.getChildren().add(lineTemp);
                        startX = 0.0;
                        startY = 0.0;
                        bNewConnectionReady = false;
                    }
                }
            });
        }
        stpLayout.getChildren().add(notTemp.getBlock());
    }

    private void newXORBlock (double x, double y) {
        XOR notTemp = new XOR(CreationUtil.createStandardInputList());
        notTemp.setLayoutXY(x, y);
        for (Output out : notTemp.getGUIOutputs()) {
            out.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked output");
                    bNewConnectionReady = true;
                    startX = t.getX();
                    startY = t.getY();
                }
            });
        }
        for (Input in : notTemp.getGUIInputs()) {
            in.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked input");
                    if (bNewConnectionReady) {
                        Line lineTemp = new Line();
                        lineTemp.setStartX(startX);
                        lineTemp.setStartY(startY);
                        lineTemp.setEndX(t.getX());
                        lineTemp.setEndY(t.getY());
                        stpLayout.getChildren().add(lineTemp);
                        startX = 0.0;
                        startY = 0.0;
                        bNewConnectionReady = false;
                    }
                }
            });
        }
        stpLayout.getChildren().add(notTemp.getBlock());
    }

    private void newOnDelayBlock (double x, double y) {
        OnDelay notTemp = new OnDelay();
        notTemp.setLayoutXY(x, y);
        for (Output out : notTemp.getGUIOutputs()) {
            out.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked output");
                    bNewConnectionReady = true;
                    startX = t.getX();
                    startY = t.getY();
                }
            });
        }
        for (Input in : notTemp.getGUIInputs()) {
            in.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked input");
                    if (bNewConnectionReady) {
                        Line lineTemp = new Line();
                        lineTemp.setStartX(startX);
                        lineTemp.setStartY(startY);
                        lineTemp.setEndX(t.getX());
                        lineTemp.setEndY(t.getY());
                        stpLayout.getChildren().add(lineTemp);
                        startX = 0.0;
                        startY = 0.0;
                        bNewConnectionReady = false;
                    }
                }
            });
        }
        stpLayout.getChildren().add(notTemp.getBlock());
    }

    private void newOffDelayBlock (double x, double y) {
        OffDelay notTemp = new OffDelay();
        notTemp.setLayoutXY(x, y);
        for (Output out : notTemp.getGUIOutputs()) {
            out.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked output");
                    bNewConnectionReady = true;
                    startX = t.getX();
                    startY = t.getY();
                }
            });
        }
        for (Input in : notTemp.getGUIInputs()) {
            in.getEllipse().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent t) {
                    System.out.println("clicked input");
                    if (bNewConnectionReady) {
                        Line lineTemp = new Line();
                        lineTemp.setStartX(startX);
                        lineTemp.setStartY(startY);
                        lineTemp.setEndX(t.getX());
                        lineTemp.setEndY(t.getY());
                        stpLayout.getChildren().add(lineTemp);
                        startX = 0.0;
                        startY = 0.0;
                        bNewConnectionReady = false;
                    }
                }
            });
        }
        stpLayout.getChildren().add(notTemp.getBlock());
    }
}
