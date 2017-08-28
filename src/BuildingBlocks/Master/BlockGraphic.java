/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.ArrayList;
import java.util.Objects;
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
public abstract class BlockGraphic {

    private final double DISTANCE_BETWEEN_POINTS = 20.0;
    private final double BLOCK_WIDTH = 65.0;
    private final double OPACITY_VALUE = 0.6;
    private final Color STROKE_COLOR = Color.BLACK;
    private final double CONNECTION_POINT_RADIUS = 4.0;
    private String blockName;
    private Rectangle rectBlock = new Rectangle();
    private Label lblName = new Label();
    private Group grpBlock = new Group();
    private ArrayList<Input> inputs = new ArrayList();
    private ArrayList<Output> outputs = new ArrayList();
    private ArrayList<Ellipse> ellipses = new ArrayList();
    private boolean bDeactivateEvents = false;
    private Type type;

    private long currentTime;
    private long lastTime;
    
    
    private boolean bDoubleClick = false;

    public enum Type {
        VARIABLE, TIMER, LOGIC, FILTER
    }

    public BlockGraphic (String blockName, Input in, Output out, Type type) {
        this.blockName = blockName;
        this.type = type;
        inputs.add(new Input(in));
        outputs.add(new Output(out));
        constructBlock();
    }

    public BlockGraphic (String blockName, ArrayList<Input> alInputs, Output out, Type type) {
        this.blockName = blockName;
        this.type = type;
        this.inputs = new ArrayList(alInputs);
        outputs.add(new Output(out));
        constructBlock();
    }

    public BlockGraphic (String blockName, ArrayList<Input> alInputs, ArrayList<Output> alOutputs, Type type) {
        this.blockName = blockName;
        this.type = type;
        this.inputs = new ArrayList(alInputs);
        this.outputs = new ArrayList(alOutputs);
        constructBlock();
    }
    
    /**
     * This Constructer is used for the Creation of a Variable.
     * @param blockName
     * @param out
     * @param blockType
     * @throws IllegalArgumentException 
     */
    public BlockGraphic (String blockName, Output out, Type blockType) throws IllegalArgumentException {
        if(!(blockType == Type.VARIABLE)){
           throw new IllegalArgumentException ("Cannot initialize Variable with the type " + blockType.toString());
        }
        this.blockName = blockName;
        this.outputs.add(out);
        this.type = blockType;
        constructBlock();
    }

    private Color getFillColor () {
        switch (type) {
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
        try {
            switch (type) {
                case LOGIC:
                    
                    break;

                case FILTER:

                    break;

                case VARIABLE:

                    break;

                case TIMER:

                    break;

                default:
                    throw new Exception("Block Type " + type.toString() + " not found!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Construct Block
        rectBlock.setHeight(inputs.size() * DISTANCE_BETWEEN_POINTS + DISTANCE_BETWEEN_POINTS);
        rectBlock.setWidth(BLOCK_WIDTH);
        rectBlock.setStroke(STROKE_COLOR);
        rectBlock.setFill(getFillColor());
        rectBlock.setOpacity(OPACITY_VALUE);
        rectBlock.setArcHeight(15);
        rectBlock.setArcWidth(15);
        grpBlock.getChildren().add(rectBlock);

        //Set Name
        lblName.setMaxWidth(BLOCK_WIDTH - 10);
        lblName.setWrapText(true);
        lblName.setText(blockName);
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
                        blockName = sTemp;
                    }
                }
            }
        });
        grpBlock.getChildren().add(lblName);

        //Construct Input Points
        int count = 1;
        for (Input in : inputs) {
            in.setPointXY(0, count * DISTANCE_BETWEEN_POINTS);
            ellipses.add(in.getEllipse());
            count++;
        }

        count = 1;
        for (Output out : outputs) {
            out.setPointXY(BLOCK_WIDTH, count * DISTANCE_BETWEEN_POINTS);
            ellipses.add(out.getEllipse());
            count++;
        }

        grpBlock.getChildren().addAll(ellipses);
    }

    public void setLayoutXY (double x, double y) {
        grpBlock.setLayoutX(x);
        grpBlock.setLayoutY(y);
    }

    public ArrayList<Output> getGUIOutputs () {
        return this.outputs;
    }

    public ArrayList<Input> getGUIInputs () {
        return this.inputs;
    }

    public Group getBlock () {
        return this.grpBlock;
    }

    public String getName () {
        return this.blockName;
    }

    public void setName (String sName) {
        this.blockName = sName;
    }

    public void deactivateEvents (boolean bDeactivateEvents) {
        this.bDeactivateEvents = bDeactivateEvents;
    }

}
