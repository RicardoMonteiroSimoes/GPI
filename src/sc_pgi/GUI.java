/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc_pgi;

import BuildingBlocks.Blocks.*;
import BuildingBlocksMaster.util.*;
import BuildingBlocksMaster.Dialogs;
import BuildingBlocksMaster.LogicBlock;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
 
public class GUI extends Application {
    
    private long currentTime;
    private long lastTime;
    private boolean bDoubleClick = false;
    private ScrollPane spItems = new ScrollPane();
    private ScrollPane spLayout = new ScrollPane();
    private Group grpLayout = new Group();
    
    @Override
    public void start(Stage primaryStage) {
        
        HBox root = new HBox();
        VBox vBox = new VBox();
        spItems.setMaxHeight(800);
        spItems.setPrefWidth(100);
        spLayout.setPrefWidth(1500);
        spLayout.setPrefHeight(800);
        spItems.setBackground(Background.EMPTY);
        spItems.setBorder(Border.EMPTY);
        
        vBox.setSpacing(25);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().add(new HBox());
        for(LogicBlock lb: SC_PGI.alBlocks){
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
                    addNewBlock(lb.getName());
                }
            }
        });
        vBox.getChildren().add(lb.getBlock());
        }
        vBox.getChildren().add(SC_PGI.var.getBlock());
        
        
        
        
        spItems.setContent(vBox);
        spLayout.setContent(grpLayout);
        root.getChildren().add(spItems);
        root.getChildren().add(spLayout);

        Scene scene = new Scene(root, 300, 250);
         
        primaryStage.setScene(scene);
        
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        System.exit(0);
                    }
                });
            }
        });
        
        primaryStage.show();
    }
    
    private void addNewBlock (String sName) {
                switch(sName){
                    case "NOT":
                        newNOTBlock();
                        break;
                    case "OR":
                        newORBlock();
                        break;
                    case "XOR":
                        newXORBlock();
                        break;
                    case "AND":
                        newANDBlock();
                        break;
                    case "OnDelay":
                        newOnDelayBlock();
                        break;
                    case "OffDelay":
                        newOffDelayBlock();
                        break;
                    default:
                        System.out.println("No case for " + sName);
                }
            }
    
    private void newNOTBlock(){
        grpLayout.getChildren().add(new NOT().getBlock());
    }
    
    private void newANDBlock(){
        grpLayout.getChildren().add(new AND(CreationUtil.createStandardInputList()).getBlock());
    }
    
    private void newORBlock(){
        grpLayout.getChildren().add(new OR(CreationUtil.createStandardInputList()).getBlock());
    }
    
    private void newXORBlock(){
        grpLayout.getChildren().add(new XOR(CreationUtil.createStandardInputList()).getBlock());
    }

    private void newOnDelayBlock () {
        grpLayout.getChildren().add(new OnDelay().getBlock());
    }

    private void newOffDelayBlock () {
        grpLayout.getChildren().add(new OffDelay().getBlock());
    }
}