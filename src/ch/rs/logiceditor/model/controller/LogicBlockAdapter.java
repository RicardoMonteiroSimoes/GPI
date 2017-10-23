/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.controller;

import ch.rs.logiceditor.model.master.LogicBlock;
import com.google.gson.*;
import java.lang.reflect.Type;

/**
 *
 * @author Ricardo
 */
public class LogicBlockAdapter implements JsonSerializer<LogicBlock>, JsonDeserializer<LogicBlock> {
  @Override
  public JsonElement serialize(LogicBlock src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject result = new JsonObject();
      result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
      result.add("properties", context.serialize(src, src.getClass())); 
      return result;
  }


  @Override
  public LogicBlock deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    String type = jsonObject.get("type").getAsString();
    JsonElement element = jsonObject.get("properties");

    try {            
        String thepackage = "ch.rs.logiceditor.model.blocks.";         
        return context.deserialize(element, Class.forName(thepackage + type));
    } catch (ClassNotFoundException cnfe) {
        throw new JsonParseException("Unknown element type: " + type, cnfe);
    }
  }
}