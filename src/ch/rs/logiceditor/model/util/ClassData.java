/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.util;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Ricardo
 */
public class ClassData {

    @Expose
    public String classType;

    public ClassData (Class classType) {
        this.classType = classType.getName();
    }
    
    public ClassData(){
        
    }
    
    
    public String getClassType(){
        return classType;
    }
    
    public String getClassString(){
        return classType;
    }
    
}


