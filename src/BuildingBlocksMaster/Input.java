/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocksMaster;

/**
 *
 * @author Ricardo
 */
public class Input {
    
    private String sName;
    private boolean bStatus = false;
    private boolean bIsBoolean = true;
    private String sInput;
    
    public Input(String sName){
        this.sName = sName;
    }
    
    public Input(){
        this.sName = "Input";
    }
    
    public Input(Input in){
        this.sName = new String(in.getName());
    }
    
    public void setStatus(boolean bStatus){
        this.bStatus = bStatus;
    }
    
    public void setInput(String sVariable){
        this.sInput = sVariable;
    }
    
    public boolean getStatus(){
        return this.bStatus;
    }
    
    public String getInput(){
        return this.sInput;
    }
    
    public String getName(){
        return this.sName;
    }
}
