/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.reflectorgrid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Ricardo
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Transfergrid {

    public enum Fieldtype{
        TEXTFIELD, TEXTAREA
    }

    public boolean editable () default true;

    public String[] options () default {};
    
    public Fieldtype fieldtype () default Fieldtype.TEXTFIELD;

}
