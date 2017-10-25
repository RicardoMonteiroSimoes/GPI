/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.view.master;

import ch.rs.logiceditor.model.master.LogicBlock;
import java.lang.reflect.Field;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ricardo
 */
public class blockVariablesAdapter {
    
    public static GridPane getVariablesGrid(LogicBlock block, GridPane grid){
        Field[] fields = block.getClass().getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getName());
        }               
        return grid;
    }
    
}
