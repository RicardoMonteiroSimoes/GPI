/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.reflectorgrid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ricardo
 */
public class ReflectorGrid {

    public static void turnObjectIntoGrid (Object object) {
        ArrayList<Field> fields = new ArrayList();
        fields.addAll(Arrays.asList(object.getClass().getDeclaredFields()));
        Class objClass = object.getClass().getSuperclass();
        while (hasSuperClass(objClass)) {
            fields.addAll(Arrays.asList(objClass.getSuperclass().getDeclaredFields()));
            objClass = objClass.getSuperclass();

        }
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation anot : annotations) {
                if (anot.annotationType().getSimpleName().equals("TransferGrid")) {
                    System.out.println(field.getName());
                }
            }
        }
        System.out.println("");
        System.out.println("");
    }

    private static boolean hasSuperClass (Class object) {
        return (object.getSuperclass() != null);
    }

}
