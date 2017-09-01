/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import BuildingBlocks.Master.util.Dialogs;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observer;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sun.applet.Main;

/**
 *
 * @author Ricardo
 */
public abstract class BlockGraphic {

    private final double DISTANCE_BETWEEN_POINTS = 20.0;
    private final double OPACITY_VALUE = 0.5;
    private final Color STROKE_COLOR = Color.BLACK;
    private final double CONNECTION_POINT_RADIUS = 4.0;
    private String blockName;
    private Rectangle rectBlock = new Rectangle();
    private Label blockNameLabel = new Label();
    private Group wholeBlockGroup = new Group();
    private Group labelBlockGroup = new Group();
    private ArrayList<Input> inputs = new ArrayList();
    private ArrayList<Output> outputs = new ArrayList();
    private ArrayList<Circle> ellipses = new ArrayList();
    private boolean bDeactivateEvents = false;
    private boolean canChangeInputs = false;
    private Type type;

    private Text notes = new Text();

    private long currentTime;
    private long lastTime;

    private boolean bDoubleClick = false;

    public enum Type {
        VARIABLE, TIMER, LOGIC, FILTER, NETWORK
    }

    public BlockGraphic (String blockName, Input in, boolean canChangeInputs, Output out, Type type) {
        this.blockName = blockName;
        this.type = type;
        this.canChangeInputs = canChangeInputs;
        inputs.add(new Input(in));
        outputs.add(new Output(out));
        constructBlock();
    }

    public BlockGraphic (String blockName, ArrayList<Input> alInputs, boolean canChangeInputs, Output out, Type type) {
        this.blockName = blockName;
        this.type = type;
        this.canChangeInputs = canChangeInputs;
        this.inputs = new ArrayList(alInputs);
        outputs.add(new Output(out));
        constructBlock();
    }

    public BlockGraphic (String blockName, ArrayList<Input> alInputs, boolean canChangeInputs, ArrayList<Output> alOutputs, Type type) {
        this.blockName = blockName;
        this.type = type;
        this.canChangeInputs = canChangeInputs;
        this.inputs = new ArrayList(alInputs);
        this.outputs = new ArrayList(alOutputs);
        constructBlock();
    }

    /**
     * This Constructer is used ONLY for the Creation of a Variable.
     *
     * @param blockName
     * @param out
     * @param blockType
     * @throws IllegalArgumentException
     */
    public BlockGraphic (String blockName, Output out, Type blockType) throws IllegalArgumentException {
        if (!(blockType == Type.VARIABLE)) {
            throw new IllegalArgumentException("Cannot initialize Variable with the type " + blockType.toString());
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

    private void reconstructBlock () {
        labelBlockGroup.getChildren().clear();
        wholeBlockGroup.getChildren().clear();
        ellipses.clear();
        constructBlock();
        
    }

    private void constructBlock () {
        try {
            if (type == Type.VARIABLE) {
                constructVariableBlock();
            } else {
                constructGeneralBlock();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Variable blocks have an Arc of 0 instead of 10 like normal blocks.
     *
     */
    private void constructVariableBlock () {
        //Make the corners corners
        setBlockArcs(0);
        //set block fill etc
        createBlockGraphic();
    }

    private void constructGeneralBlock () {
        //Make the corners round
        setBlockArcs(10);
        //set block fill etc
        createBlockGraphic();
    }

    private void createBlockGraphic () {
        setBlockNameLabel();
        setBlockHeight();
        setBlockWidth();
        setBlockColors();
        labelBlockGroup.getChildren().add(rectBlock);
        labelBlockGroup.getChildren().add(blockNameLabel);
        labelBlockGroup.toBack();
        createInputPoints();
        createOutputPoints();
        wholeBlockGroup.getChildren().addAll(labelBlockGroup);
        wholeBlockGroup.getChildren().addAll(ellipses);
        setBlockFunctions();
        System.out.println("finished block! " + getName());
    }

    private void createInputPoints () {
        int count = 1;
        for (Input in : inputs) {
            in.setPointXY(0, count * DISTANCE_BETWEEN_POINTS);
            ellipses.add(in.getCircle());
            count++;
        }
    }

    private void createOutputPoints () {
        int count = 1;
        for (Output out : outputs) {
            out.setPointXY(rectBlock.getWidth(), count * DISTANCE_BETWEEN_POINTS);
            ellipses.add(out.getCircle());
            count++;
        }
    }


    private void setBlockArcs (double radius) {
        rectBlock.setArcHeight(radius);
        rectBlock.setArcWidth(radius);
    }

    private void setBlockHeight () {
        //Sets the height according to the amount of in/outputs. 
        if (!inputs.isEmpty() && inputs.size() > outputs.size()) {
            rectBlock.setHeight(inputs.size() * DISTANCE_BETWEEN_POINTS + DISTANCE_BETWEEN_POINTS);
        } else if (!outputs.isEmpty()) {
            rectBlock.setHeight(outputs.size() * DISTANCE_BETWEEN_POINTS + DISTANCE_BETWEEN_POINTS);
        } else {
            rectBlock.setHeight(2 * DISTANCE_BETWEEN_POINTS);
        }
        blockNameLabel.setLayoutY(rectBlock.getHeight() / 2 - 10);
    }

    private void setBlockWidth () {
        rectBlock.setWidth(blockNameLabel.getText().length() * 3.5 + 2 * DISTANCE_BETWEEN_POINTS);
    }

    private void setBlockColors () {
        rectBlock.setStroke(STROKE_COLOR);
        rectBlock.setFill(getFillColor());
        rectBlock.setOpacity(OPACITY_VALUE);
    }

    private void setBlockNameLabel () {
        blockNameLabel.setWrapText(false);
        blockNameLabel.setText(blockName);
        blockNameLabel.setMinWidth(blockName.length() * 3.5 + 2 * DISTANCE_BETWEEN_POINTS);
        blockNameLabel.setAlignment(Pos.CENTER);
    }

    private void setBlockFunctions () {
        //Function to open up overview Window, fires when doubleclick happens
        labelBlockGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                        Dialogs.informationWindow(getBlockObject());
                    }
                }
            }
        });
    }

    public void setLayoutXY (double x, double y) {
        wholeBlockGroup.setLayoutX(x);
        wholeBlockGroup.setLayoutY(y);
    }

    public ArrayList<Output> getGUIOutputs () {
        return this.outputs;
    }

    public ArrayList<Input> getGUIInputs () {
        return this.inputs;
    }

    public int getAmountOfInputs () {
        return inputs.size();
    }

    public int getAmountOfOutputs () {
        return outputs.size();
    }

    public Group getBlockGraphic () {
        return this.wholeBlockGroup;
    }

    public BlockGraphic getBlockObject () {
        return this;
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

    public void setNote (Text text) {
        this.notes = text;
    }

    public Text getNotes () {
        return notes;
    }

    public Type getType () {
        return type;
    }

    public boolean canChangeInputs () {
        return canChangeInputs;
    }

    public void setAmountOfInputs (int amountInputs) throws IllegalAccessError {
        if (!canChangeInputs()) {
            throw new IllegalAccessError("Cannot change amount of Inputs as it is Locked!");
        }
        if (inputs.size() > amountInputs) {
            for (int takeAway = 1; takeAway <= inputs.size() - amountInputs; takeAway++) {
                inputs.remove(inputs.size() - takeAway);
                reconstructBlock();
            }
        } else if (inputs.size() < amountInputs) {
            for (int addToInputs = 1; addToInputs <= amountInputs - inputs.size(); addToInputs++) {
                inputs.add(new Input("Input " + inputs.size()+1));
                reconstructBlock();
            }
        }
    }
    
    public void addConnectionWatcher(Observer observer){
        for(Output out : outputs){
            out.addObserver(observer);
        }
    }

}
