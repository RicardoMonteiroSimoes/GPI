/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master.util;

import old.BuildingBlocks.Master.ContactPoint.Datatype;
import old.BuildingBlocks.Master.Input;
import ch.rs.logiceditor.model.util.ServerPacket;
import old.BuildingBlocks.Master.Output;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class CreationUtil {
    
    /**
     * This Function generates an ArrayList<Input> that contains as many inputs
     * the number you give
     * @param amountInputs amount of Inputs you want
     * @return ArrayList<Inputs> with your inputs
     */
    public static ArrayList<Input> createStandardInputList(int amountInputs, Datatype datatype){
        ArrayList<Input> alInputs = new ArrayList();
        int count = 1;
        while(count <= amountInputs){
            alInputs.add(createInput(datatype));
            count++;
        }
        return alInputs;
    }
    
    public static Output createOutput(Datatype datatype){
        Output out = new Output("Output", datatype);
        return out;
    }
    
    public static ArrayList<Input> createInputList(String[] saInputs, Datatype datatype){
        ArrayList<Input> alInputs = new ArrayList();
        for(String s : saInputs){
            alInputs.add(createInput(s, datatype));
        }
        return alInputs;
    }
    
    public static Input createInput(Datatype datatype){
        switch(datatype){
            case BOOLEAN:
                return new Input <Boolean>("Input", datatype);
            case STRING:
                return new Input <String>("Input", datatype);
            case DOUBLE:
                return new Input <Double>("Input", datatype);
            case FLOAT:
                return new Input <Float>("Input", datatype);
            case INTEGER:
                return new Input <Integer>("Input", datatype);
            case SERVERPACKET:
                return new Input <ServerPacket>("Input", datatype);
            default:
                throw new TypeNotPresentException("The Datatype " + datatype + " does not exist!",
                        new Throwable("Wrong Datatype!"));
        }
    }
    
    public static Input createInput(String s, Datatype datatype){
        switch(datatype){
            case BOOLEAN:
                return new Input <Boolean>(s, datatype);
            case STRING:
                return new Input <String>(s, datatype);
            case DOUBLE:
                return new Input <Double>(s, datatype);
            case FLOAT:
                return new Input <Float>(s, datatype);
            case INTEGER:
                return new Input <Integer>(s, datatype);
            case SERVERPACKET:
                return new Input <ServerPacket>(s, datatype);
            default:
                throw new TypeNotPresentException("The Datatype " + datatype + " does not exist!",
                        new Throwable("Wrong Datatype!"));
        }
    }
}
