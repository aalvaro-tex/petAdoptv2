/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.external;

/**
 *
 * @author alvar
 */
public class Comunidad {
    
    private String CCOM;
    private String COM;

    public Comunidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCCOM() {
        return CCOM;
    }

    public void setCCOM(String CCOM) {
        this.CCOM = CCOM;
    }

    public String getCOM() {
        return COM;
    }

    public void setCOM(String COM) {
        this.COM = COM;
    }

    public Comunidad(String CCOM, String COM) {
        this.CCOM = CCOM;
        this.COM = COM;
    }
    
    
}
