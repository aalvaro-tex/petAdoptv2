/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.utils;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 *
 * @author alvar
 */
@Named
@ManagedBean
@SessionScoped
public class StringUtils  implements Serializable {

    /**
     *
     * @param name
     * @return
     */
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
    
    /**
     *
     * @param date
     * @return
     */
    public String dateToString(Date date){
        return DateFormatUtils.format(date, "dd/MM/yyyy");
    }
    
    /**
     *
     * @param input
     * @return
     */
    public int stringToInt(String input){
        return Integer.parseInt(input);
    }
    
    /**
     *
     * @param input
     * @return
     */
    public double stringToDouble(String input){
        return Double.parseDouble(input);
    }

}
