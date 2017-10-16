/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import BuildingBlocks.Master.ContactPoint.Datatype;
import BuildingBlocks.Master.util.CreationUtil;
import BuildingBlocks.Master.util.Dialogs;
import java.util.ArrayList;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;

/**
 *
 * @author Ricardo
 */
public abstract class BlockGraphic implements Observer{

    private final double DISTANCE_BETWEEN_POINTS = 20.0;
    private final double OPACITY_VALUE = 0.5;
    private final Color STROKE_COLOR = Color.BLACK;
    private final double CONNECTION_POINT_RADIUS = 4.0;
    private double DISTANCE_PER_LETTER = 4;
    private String blockName;
    private String blockSubName;
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
    private Observer observer;
    private Group grp = new Group();
    private boolean hasAdditionalDialogFunction = false;
    private ContextMenu contextMenu = new ContextMenu();
    private double orgSceneX;
    private double orgSceneY;

    private Text notes = new Text();

    private long currentTime;
    private long lastTime;

    private boolean bDoubleClick = false;

    public void setSubName(String text) {
        blockSubName = text;
        addTooltipTo(labelBlockGroup, blockSubName);
    }

    public boolean hasAdditionalDialogFunction() {
        return hasAdditionalDialogFunction;
    }

    public enum Type {
        VARIABLE, TIMER, LOGIC, FILTER, NETWORK
    }

    public BlockGraphic(String blockName, String blockSubName, Input in, boolean canChangeInputs, Output out, Type type) {
        this.blockName = blockName;
        this.blockSubName = blockSubName;
        this.type = type;
        this.canChangeInputs = canChangeInputs;
        inputs.add(in);
        outputs.add(out);
        constructBlock();
    }

    public BlockGraphic(String blockName, String blockSubName, Input in, boolean canChangeInputs, Type type) {
        this.blockName = blockName;
        this.blockSubName = blockSubName;
        this.type = type;
        this.canChangeInputs = canChangeInputs;
        inputs.add(in);
        constructBlock();
    }

    public BlockGraphic(String blockName, String blockSubName, Output out, Type type) {
        this.blockName = blockName;
        this.blockSubName = blockSubName;
        this.type = type;
        outputs.add(out);
        constructBlock();
    }

    public BlockGraphic(String blockName, String blockSubName, ArrayList<Input> inputs, boolean canChangeInputs, Output out, Type type) {
        this.blockName = blockName;
        this.blockSubName = blockSubName;
        this.type = type;
        this.canChangeInputs = canChangeInputs;
        this.inputs = inputs;
        outputs.add(out);
        constructBlock();
    }
    
    public BlockGraphic(String blockName, String blockSubName, Input in, Type type) {
        this.blockName = blockName;
        this.blockSubName = blockSubName;
        this.type = type;
        this.canChangeInputs = false;
        this.inputs.add(in);
        constructBlock();
    }

    /**
     *
     *
     * @param blockName
     * @param out
     * @param blockType
     * @throws IllegalArgumentException
     */
    public BlockGraphic(String blockName, Output out, Type blockType) throws IllegalArgumentException {
        if ((blockType != Type.VARIABLE && blockType != Type.NETWORK)) {
            throw new IllegalArgumentException("Cannot initialize Variable with the type " + blockType.toString());
        }
        this.blockName = blockName;
        this.outputs.add(out);
        this.type = blockType;
        constructBlock();
    }

    private Color getFillColor() {
        switch (type) {
            case VARIABLE:
                return Color.TURQUOISE;
            case TIMER:
                return Color.GREEN;
            case LOGIC:
                return Color.BURLYWOOD;
            case FILTER:
                return Color.YELLOW;
            case NETWORK:
                return Color.KHAKI;
            default:
                return Color.PURPLE;
        }
    }

    private void reconstructBlock() {
        labelBlockGroup.getChildren().clear();
        wholeBlockGroup.getChildren().clear();
        ellipses.clear();
        constructBlock();
        addConnectionWatcher(this.observer);
    }

    private void removeObserversFromInput(Input in) {
        in.removeObservers();
    }

    private void constructBlock() {
        try {
            if (type == Type.VARIABLE || type == Type.NETWORK) {
                constructCorneredBlock();
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
    private void constructCorneredBlock() {
        //Make the corners corners
        setBlockArcs(0);
        //set block fill etc
        createBlockGraphic();
    }

    private void constructGeneralBlock() {
        //Make the corners round
        setBlockArcs(10);
        //set block fill etc
        createBlockGraphic();
    }

    private void createBlockGraphic() {
        setDPISettings();
        setBlockNameLabel();
        setBlockHeight();
        setBlockWidth();
        setBlockColors();
        setLowerBlockGroup();
        createInputPoints();
        createOutputPoints();
        setUpperBlockGroup();
        setBlockFunctions();
        setupDrag();
    }
    
    private void setDPISettings(){
        double screenfactor = Screen.getPrimary().getDpi()/100;
        DISTANCE_PER_LETTER *= screenfactor;
    }

    private void setLowerBlockGroup() {
        labelBlockGroup.getChildren().add(rectBlock);
        labelBlockGroup.getChildren().add(blockNameLabel);
        labelBlockGroup.toBack();
        addTooltipTo(labelBlockGroup, blockSubName);
    }

    private void setUpperBlockGroup() {
        wholeBlockGroup.getChildren().addAll(labelBlockGroup);
        wholeBlockGroup.getChildren().addAll(ellipses);
    }

    private void addTooltipTo(Node n, String tooltip) {
        Tooltip t = new Tooltip(tooltip);
        Tooltip.install(n, t);
    }

    private void createInputPoints() {
        int count = 1;
        for (Input in : inputs) {
            in.setPointXY(0, count * DISTANCE_BETWEEN_POINTS);
            in.addObserver(this);
            ellipses.add(in.getCircle());
            count++;
        }
    }

    private void createOutputPoints() {
        int count = 1;
        for (Output out : outputs) {
            out.setPointXY(rectBlock.getWidth(), count * DISTANCE_BETWEEN_POINTS);
            ellipses.add(out.getCircle());
            count++;
        }
    }

    private void redoInputPoints() {
        ellipses.clear();
        createOutputPoints();
        createInputPoints();
        addConnectionWatcher(this.observer);
    }

    private void setBlockArcs(double radius) {
        rectBlock.setArcHeight(radius);
        rectBlock.setArcWidth(radius);
    }

    private void setBlockHeight() {
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

    private void setBlockWidth() {
        rectBlock.setWidth(blockNameLabel.getText().length() * DISTANCE_PER_LETTER + 2 * DISTANCE_BETWEEN_POINTS);
    }

    private void setBlockColors() {
        rectBlock.setStroke(STROKE_COLOR);
        rectBlock.setFill(getFillColor());
        rectBlock.setOpacity(OPACITY_VALUE);
    }

    private void setBlockNameLabel() {
        blockNameLabel.setWrapText(false);
        blockNameLabel.setText(blockName);
        blockNameLabel.setMinWidth(blockName.length() * DISTANCE_PER_LETTER + 2 * DISTANCE_BETWEEN_POINTS);
        blockNameLabel.setAlignment(Pos.CENTER);
    }

    private void setBlockFunctions() {
        
 
        MenuItem item1 = new MenuItem("Einstellungen");
        item1.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Dialogs.informationWindow(getBlockObject());
            }
        });
        
        contextMenu.getItems().add(item1);
        
        labelBlockGroup.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
 
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(labelBlockGroup, event.getScreenX(), event.getScreenY());
            }
        });

        labelBlockGroup.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!bDeactivateEvents) {
                    setOnMousePressedEvent(event);
                }
            }

        });

        labelBlockGroup.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!bDeactivateEvents) {
                    setOnMouseReleasedEvent(event);
                }
            }

        });
        
    }
    
    private void setupDrag () {
        wholeBlockGroup.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

        });
        wholeBlockGroup.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            double xCoord = wholeBlockGroup.getLayoutX() + offsetX;
            double yCoord = wholeBlockGroup.getLayoutY() + offsetY;
            
            if(xCoord > (rectBlock.getWidth()/2) && yCoord > (DISTANCE_BETWEEN_POINTS/2) && xCoord < (4000 - rectBlock.getWidth()*1.5) && yCoord <(4000 - rectBlock.getHeight()*1.5)){
                wholeBlockGroup.setLayoutX(wholeBlockGroup.getLayoutX() + offsetX);
                wholeBlockGroup.setLayoutY(wholeBlockGroup.getLayoutY() + offsetY);
            updateConnections();
            }
            

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });
    }
    
    private void updateConnections(){
        for(Input in : inputs){
            in.updateInput();
        }
        for(Output out : outputs){
            out.updateOutput();
        }
    }

    public void setLayoutXY(double x, double y) {
        wholeBlockGroup.setLayoutX(x);
        wholeBlockGroup.setLayoutY(y);
    }

    public ArrayList<Output> getOutputs() {
        return this.outputs;
    }

    public ArrayList<Input> getInputs() {
        return this.inputs;
    }

    public int getAmountOfInputs() {
        return inputs.size();
    }

    public int getAmountOfOutputs() {
        return outputs.size();
    }

    public Group getBlockGraphic() {
        return this.wholeBlockGroup;
    }

    public BlockGraphic getBlockObject() {
        return this;
    }

    public String getName() {
        return this.blockName;
    }

    public void setName(String sName) {
        this.blockName = sName;
    }

    public void deactivateEvents(boolean bDeactivateEvents) {
        this.bDeactivateEvents = bDeactivateEvents;
    }

    public void setNote(Text text) {
        this.notes = text;
    }

    public Text getNotes() {
        return notes;
    }

    public Type getType() {
        return type;
    }

    public boolean canChangeInputs() {
        return canChangeInputs;
    }

    protected void setOnMousePressedEvent(MouseEvent event) {

    }

    protected void setOnMouseReleasedEvent(MouseEvent event) {

    }

    public void setAmountOfInputs(int amountInputs) throws IllegalAccessError {
        if (!canChangeInputs()) {
            throw new IllegalAccessError("Cannot change amount of Inputs as it is Locked!");
        }
        if (inputs.size() > amountInputs) {
            for (int takeAway = 1; takeAway <= inputs.size() - amountInputs; takeAway++) {
                removeObserversFromInput(inputs.get(inputs.size() - takeAway));
                inputs.remove(inputs.size() - takeAway);
                reconstructBlock();
            }
        } else if (inputs.size() < amountInputs) {
            for (int addToInputs = 1; addToInputs <= amountInputs - inputs.size(); addToInputs++) {
                inputs.add(CreationUtil.createInput(Datatype.BOOLEAN));
                reconstructBlock();
            }
        }
    }

    public void addConnectionWatcher(Observer observer) {
        this.observer = observer;
        for (Output out : outputs) {
            out.addObserver(observer);
        }
        for (Input in : inputs) {
            in.addObserver(observer);
        }
    }

    public String getSubName() {
        return blockSubName;
    }

    protected void addDialogFunction(Group grp) {
        this.grp = grp;
        hasAdditionalDialogFunction = true;

    }

    protected void addToContextMenu(Menu menu){
        contextMenu.getItems().add(0, menu);
    }

    public Group getAdditionalDialogFunction() {
        return grp;
    }
    
    public Group getLabelGroup(){
        return labelBlockGroup;
    }
    
    public void select(){
        rectBlock.setStroke(Color.RED);
    }
    
    public void unselect(){
        rectBlock.setStroke(Color.BLACK);
    }


}
