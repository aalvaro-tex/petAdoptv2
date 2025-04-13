/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.utils;

import java.util.Date;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 *
 * @author alvar
 */
public class StringUtils {

    public String capitalizeAllFirstLetters(String name) {
        char[] array = name.toLowerCase().toCharArray();
        array[0] = Character.toUpperCase(array[0]);

        for (int i = 1; i < array.length; i++) {
            if (Character.isWhitespace(array[i - 1])) {
                array[i] = Character.toUpperCase(array[i]);
            }
        }

        return new String(array);
    }
    
    public String dateToString(Date date){
        return DateFormatUtils.format(date, "dd/MM/yyyy");
    }

}
