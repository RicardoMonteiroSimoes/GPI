/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author Ricardo
 */
public class ClassDataSerializer implements JsonSerializer<ClassData> {

    @Override
    public JsonElement serialize (ClassData src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.jsonString);
    }
}
