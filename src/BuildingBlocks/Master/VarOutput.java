/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import BuildingBlocks.Master.Output;

/**
 *
 * @author Ricardo
 */
public class VarOutput extends Output {

    private String sValue;

    public VarOutput (String sName) {
        super(sName);
    }

    public void setInput (String sValue) {
        this.sValue = sValue;
    }

    public String getInput () {
        return this.sValue;
    }

    @Override
    public void setStatus (boolean bStatus) {
        throw new AbstractMethodError("This Variable can only handle Strings");
    }

    @Override
    public boolean getStatus () {
        throw new AbstractMethodError("This Variable can only return Strings");
    }

}
