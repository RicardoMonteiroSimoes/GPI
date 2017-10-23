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
    public String jsonString;
    
    public Class classType;

    public ClassData (String jsonString, Class classType) {
        this.jsonString = jsonString;
        this.classType = classType;
    }
    
    public Class getClassType(){
        return classType;
    }
}


