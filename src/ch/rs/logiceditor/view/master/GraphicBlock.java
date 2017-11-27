/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.view.master;

import ch.rs.logiceditor.model.master.LogicBlock;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Ricardo
 */
public class GraphicBlock {

    private LogicBlock block;
    private final double ARC_RADIUS = 5.0;
    private final double DISTANCE_BETWEEN_POINTS = 20.0;
    private final double OPACITY_VALUE = 0.5;
    private final Color STROKE_COLOR = Color.BLACK;
    private final double CONNECTION_POINT_RADIUS = 4.0;
    private final double DISTANCE_PER_LETTER = 4;
    private Rectangle baseRect = new Rectangle();
    private guiVariableDeclarations controller;
    private Group blockGraphic;

    public GraphicBlock(LogicBlock block) {
        this.block = block;
        generateGraphicBlock();
    }
    
    public Group getBlockGraphic(){
        return blockGraphic;
    }

    private void generateGraphicBlock() {
        blockGraphic = new Group();
        blockGraphic.getChildren().add(createBlockBase());
    }

    private Group createConnectionPoints(Rectangle blockRec) {
        Group connectionPoints = new Group();
        for (int x = 1; x <= block.getInputs().size(); x++) {
            Ellipse point = new Ellipse();
            point.setFill(Color.BLACK);
            point.setRadiusX(CONNECTION_POINT_RADIUS);
            point.setRadiusY(CONNECTION_POINT_RADIUS);
            point.setCenterX(0);
            point.setCenterY(x * DISTANCE_BETWEEN_POINTS);
            connectionPoints.getChildren().add(point);
        }

        for (int x = 1; x <= block.getOutputs().size(); x++) {
            Ellipse point = new Ellipse();
            point.setFill(Color.BLACK);
            point.setRadiusX(CONNECTION_POINT_RADIUS);
            point.setRadiusY(CONNECTION_POINT_RADIUS);
            point.setCenterX(blockRec.getWidth());
            point.setCenterY(x * DISTANCE_BETWEEN_POINTS);
            connectionPoints.getChildren().add(point);
        }
        return connectionPoints;
    }

    private Color getBlockTypeColor() {
        if (block.getBlockType() != null) {
            switch (block.getBlockType()) {
                case FILTER:
                    return Color.GOLDENROD;
                case LOGIC:
                    return Color.CORAL;
                case NETWORK:
                    return Color.AQUAMARINE;
                case TIMER:
                    return Color.CORNFLOWERBLUE;
            }
        }
        return Color.BLACK;
    }

    private Group createBlockBase() {
        Group blockBase = new Group();
        baseRect.setFill(getBlockTypeColor());
        baseRect.setOpacity(OPACITY_VALUE);
        baseRect.setStroke(STROKE_COLOR);
        baseRect.setArcHeight(ARC_RADIUS);
        baseRect.setArcWidth(ARC_RADIUS);
        setRectangleHeight(baseRect);

        Label blockName = new Label(block.getName());
        blockName.setLayoutY(baseRect.getHeight() / 2 - 10);
        blockName.setMinWidth(blockName.getText().length() * DISTANCE_PER_LETTER + 2 * DISTANCE_BETWEEN_POINTS);
        blockName.setAlignment(Pos.CENTER);
        setRectangleWidth(baseRect, blockName);

        blockBase.getChildren().addAll(baseRect, blockName);
        blockBase.getChildren().add(createConnectionPoints(baseRect));
        return blockBase;
    }

    private void setRectangleWidth(Rectangle baseRect, Label blockName) {
        baseRect.setWidth(blockName.getText().length() * DISTANCE_PER_LETTER + 2 * DISTANCE_BETWEEN_POINTS);
    }

    private void setRectangleHeight(Rectangle baseRect) {

        if ((!block.getInputs().isEmpty()) && block.getInputs().size() > block.getOutputs().size()) {
            baseRect.setHeight(block.getInputs().size() * DISTANCE_BETWEEN_POINTS + DISTANCE_BETWEEN_POINTS);
        } else if (!block.getOutputs().isEmpty()) {
            baseRect.setHeight(block.getOutputs().size() * DISTANCE_BETWEEN_POINTS + DISTANCE_BETWEEN_POINTS);
        } else {
            baseRect.setHeight(2 * DISTANCE_BETWEEN_POINTS);
        }
    }

    public void setBlockStrokeColor(Color color) {
        baseRect.setStroke(color);
    }

}
