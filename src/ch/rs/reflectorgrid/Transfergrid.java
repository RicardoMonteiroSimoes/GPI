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
 * This annotation is supposed to be used in conjunction with ReflectorGrid.
 * It has different variables which are used to set different options
 * during the creation of the grid. 
 * 
 * the enum <b>Fieldtype</b> is used to define if the Field is constructed
 * using a <b>TextField</b> or <b>TextArea</b>.
 * 
 * <b>editable</b> is used to define if a variable is read only or can 
 * also be written to.
 * 
 * <b>options</b> is used, once filled, to create a comboBox for the variable.
 * This is, in terms of hierarchy, above the <b>fieldtype</b> enum. As soon as
 * you fill something into options, the field for the corresponding variable
 * turns into a ComboBox.
 * 
 * @author Ricardo S., RS
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Transfergrid {

    
    /**
     * This definest the possible options for the
     * TextInputField.
     */
    public enum Fieldtype{
        TEXTFIELD, TEXTAREA
    }

    /**
     * This defines if a variable is write and read or read only.
     * default is set to true.
     * true == write/read
     * false == read only
     * @return 
     */
    public boolean editable () default true;

    /**
     * This defines if the variable is supposed to be set using a ComboBox.
     * This is above <b>fieldtype</b>. If you give options any input, it will
     * turn into a ComboBox, no matter the setting in <b>fieldtype</b>
     * 
     * To set this variable, please make use of a normal String array.
     * <b>Example:</b>
     * @Transfergrid(options = {"full", "half", "none"}); 
     * 
     * @return Stringarray of all options.
     */
    public String[] options () default {};
    
    
    /**
     * This defines what kind of TextInputField you want.
     * Default value is set to <b>TEXTFIELD</b>.
     * You can manually change it to <b>TEXTAREA</b> for something like
     * "notes" in your object.
     * 
     * <b>WARNING</b> If options() has been filled, whatever you set here
     * gets ignored.
     * 
     * @return 
     */
    public Fieldtype fieldtype () default Fieldtype.TEXTFIELD;

}
