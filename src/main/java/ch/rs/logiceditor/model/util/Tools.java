/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Ricardo
 */
public class Tools {

    public static String getCurrentTime () {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMMM.yyyy");
        return sdf.format(cal.getTime());
    }
}


