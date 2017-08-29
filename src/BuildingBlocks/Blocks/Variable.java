/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;


import BuildingBlocks.Master.BlockGraphic;
import BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;
import com.sun.corba.se.spi.logging.CORBALogDomains;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polyline;

/**
 *
 * @author Ricardo
 */
public class Variable extends BlockGraphic{

    private String sName = "Variable";
    private String sTooltip = "Not set";
    ArrayList<Output> alOutputs = new ArrayList();
    private final double OPACITY_VALUE = 0.6;
    private final double ARROW_HEIGHT = 20.0;
    private final double ARROW_WIDTH = 50.0;
    private final double ARROW_POINT_WIDTH = 5.0;
    private final double CONNECTION_POINT_RADIUS = 4.0;
    private boolean bValue;
    private Group grpBlock = new Group();

    public Variable (String blockName, boolean bValue) {
        super(blockName, CreationUtil.getOutput() , Type.VARIABLE);
        this.bValue = bValue;
    }
    
    public void setTooltip(String sTooltip){
        this.sTooltip = sTooltip;
    }
    
    public String getTooltip(){
        return this.sTooltip;
    }

    public String getName () {
        return this.sName;
    }

    private void setOutput () {
        alOutputs.get(0).setStatus(this.bValue);
    }

}
