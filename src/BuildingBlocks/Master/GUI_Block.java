/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.ArrayList;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import sun.applet.Main;

/**
 *
 * @author Ricardo
 */
public abstract class GUI_Block {

    private final double DISTANCE_BETWEEN_POINTS = 20.0;
    private final double BLOCK_WIDTH = 65.0;
    private final double OPACITY_VALUE = 0.6;
    private final Color STROKE_COLOR = Color.BLACK;
    private final Color FILL_COLOR = Color.RED;
    private final double CONNECTION_POINT_RADIUS = 4.0;
    private String sName;
    private Rectangle rectBlock = new Rectangle();
    private Label lblName = new Label();
    private Group grpBlock = new Group();
    private ArrayList<Input> alInputs = new ArrayList();
    private ArrayList<Output> alOutputs = new ArrayList();
    private ArrayList<Ellipse> alEllipse = new ArrayList();
    private boolean bDeactivateEvents = false;
    private Type type;

    private long currentTime;
    private long lastTime;
    private boolean bDoubleClick = false;
    
    public enum Type {
        VARIABLE, TIMER, LOGIC, FILTER
    }

    public GUI_Block (String sName, Input in, Output out, Type type) {
        this.sName = sName;
        this.type = type;
        alInputs.add(new Input(in));
        alOutputs.add(new Output(out));
        constructBlock();
    }

    public GUI_Block (String sName, ArrayList<Input> alInputs, Output out, Type type) {
        this.sName = sName;
        this.type = type;
        this.alInputs = new ArrayList(alInputs);
        alOutputs.add(new Output(out));
        constructBlock();
    }

    public GUI_Block (String sName, ArrayList<Input> alInputs, ArrayList<Output> alOutputs, Type type) {
        this.sName = sName;
        this.type = type;
        this.alInputs = new ArrayList(alInputs);
        this.alOutputs = new ArrayList(alOutputs);
        constructBlock();
    }

    private Color getFillColor(){
        switch(type){
            case VARIABLE:
                return Color.TURQUOISE;
            case TIMER:
                return Color.GREEN;
            case LOGIC:
                return Color.BURLYWOOD;
            case FILTER:
                return Color.YELLOW;
            default:
                return Color.PURPLE;
        }
    }
    
    private void constructBlock () {
        //Construct Block
        rectBlock.setHeight(alInputs.size() * DISTANCE_BETWEEN_POINTS + DISTANCE_BETWEEN_POINTS);
        rectBlock.setWidth(BLOCK_WIDTH);
        rectBlock.setStroke(STROKE_COLOR);
        rectBlock.setFill(getFillColor());
        rectBlock.setOpacity(OPACITY_VALUE);
        grpBlock.getChildren().add(rectBlock);

        //Set Name
        lblName.setMaxWidth(BLOCK_WIDTH - 10);
        lblName.setWrapText(true);
        lblName.setText(sName);
        lblName.setAlignment(Pos.CENTER);

        lblName.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent t) {
                if (!bDeactivateEvents) {
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
                        String sTemp = Dialogs.nameInputDialog();
                        lblName.setText(sTemp);
                        sName = sTemp;
                    }
                }
            }
        });
        grpBlock.getChildren().add(lblName);

        //Construct Input Points
        int count = 1;
        for (Input in : alInputs) {
            in.setPointXY(0, count * DISTANCE_BETWEEN_POINTS);
            alEllipse.add(in.getEllipse());
            count++;
        }

        count = 1;
        for (Output out : alOutputs) {
            out.setPointXY(BLOCK_WIDTH, count * DISTANCE_BETWEEN_POINTS);
            alEllipse.add(out.getEllipse());
            count++;
        }

        grpBlock.getChildren().addAll(alEllipse);
    }
    
    public void setLayoutXY(double x, double y){
        grpBlock.setLayoutX(x);
        grpBlock.setLayoutY(y);
    }
    
    public ArrayList<Output> getGUIOutputs(){
        return this.alOutputs;
    }
    
    public ArrayList<Input> getGUIInputs(){
        return this.alInputs;
    }

    public Group getBlock () {
        return this.grpBlock;
    }

    public String getName () {
        return this.sName;
    }

    public void setName (String sName) {
        this.sName = sName;
    }

    public void deactivateEvents (boolean bDeactivateEvents) {
        this.bDeactivateEvents = bDeactivateEvents;
    }

}
