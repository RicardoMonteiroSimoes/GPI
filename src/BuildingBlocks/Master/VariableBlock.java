/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public abstract class VariableBlock extends BlockGraphic {

    private Output output;
    private Datatype datatype;

    public enum Datatype {
        STRING, BOOLEAN, FLOAT, DOUBLE, INTEGER
    }

    public VariableBlock (String blockName, Output out, boolean value) {
        super(blockName, out, Type.VARIABLE);
        output = out;
        this.datatype = Datatype.BOOLEAN;
    }

    public VariableBlock (String blockName, Output out, String value) {
        super(blockName, out, Type.VARIABLE);
        output = out;
        this.datatype = Datatype.STRING;
    }

    public VariableBlock (String blockName, Output out, Float value) {
        super(blockName, out, Type.VARIABLE);
        output = out;
        this.datatype = Datatype.FLOAT;
    }

    public VariableBlock (String blockName, Output out, double value) {
        super(blockName, out, Type.VARIABLE);
        output = out;
        this.datatype = Datatype.DOUBLE;
    }

    public VariableBlock (String blockName, Output out, int value) {
        super(blockName, out, Type.VARIABLE);
        output = out;
        this.datatype = Datatype.INTEGER;
    }

    protected Output getOutput () {
        return this.output;
    }

}
