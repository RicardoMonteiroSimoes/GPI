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
public class Output {
    
    private String sName;
    private boolean bValue = false;
    private boolean bIsBoolean = true;
    private String sOutput;
    
    public Output(String sName){
        this.sName = sName;
    }
    
    public Output(){
        this.sName = "Output";
    }
    
    public Output(Output out){
        this.sName = new String(out.getName());
    }
    
    public boolean getBOutput(){
        return this.bValue;
    }
    
    public String getSOutput(){
        return this.sOutput;
    }
    
    public boolean isBOutput(){
        return bIsBoolean;
    }
    
    public void setOutput(String sVariable){
        this.sOutput = sVariable;
        this.bIsBoolean = false;
    }
    
    public void setOutput(boolean bValue){
        this.sOutput = null;
        this.bIsBoolean = true;
        this.bValue = bValue;
    }
    
    public String getName(){
        return this.sName;
    }
}
