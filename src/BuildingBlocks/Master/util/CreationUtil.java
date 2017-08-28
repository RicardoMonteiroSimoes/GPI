/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master.util;

import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.VarInput;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class CreationUtil {
    
    
    public static ArrayList<Input> createInputList(String[] saInputs){
        ArrayList<Input> alInputs = new ArrayList();
        for(String s : saInputs){
            alInputs.add(new Input(s));
        }
        return alInputs;
    }
    
    public static ArrayList<Input> createStandardInputList(){
        ArrayList<Input> alInputs = new ArrayList();
        alInputs.add(new Input("Input 1"));
        alInputs.add(new Input("Input 2"));
        return alInputs;
    }
    
    public static ArrayList<Input> getTimerInputs(){
        ArrayList<Input> alTemp = new ArrayList();
        alTemp.add(new Input("Input"));
        alTemp.add(new VarInput("Timevalue"));
        return alTemp;
    }
}
