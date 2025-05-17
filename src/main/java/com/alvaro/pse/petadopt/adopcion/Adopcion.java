/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.adopcion;

import com.alvaro.pse.petadopt.entities.Mascota;
import com.alvaro.pse.petadopt.entities.Refugio;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author alvar
 */
@Named
@SessionScoped
public class Adopcion implements Serializable {

    private String mascotaFilter = "todas";
    private Long idMascotaSelected;
    private Mascota mascotaSelected;
    private String base64firma = "";
    private String valueFirma = "";
    
    private Refugio refugio;

    /**
     *
     * @return
     */
    public String getMascotaFilter() {
        return mascotaFilter;
    }

    /**
     *
     * @param mascotaFilter
     */
    public void setMascotaFilter(String mascotaFilter) {
        this.mascotaFilter = mascotaFilter;
    }

    /**
     *
     * @return
     */
    public Long getIdMascotaSelected() {
        return idMascotaSelected;
    }

    /**
     *
     * @param idMascotaSelected
     */
    public void setIdMascotaSelected(Long idMascotaSelected) {
        this.idMascotaSelected = idMascotaSelected;
    }

    /**
     *
     * @return
     */
    public Mascota getMascotaSelected() {
        return mascotaSelected;
    }

    /**
     *
     * @param mascotaSelected
     */
    public void setMascotaSelected(Mascota mascotaSelected) {
        this.mascotaSelected = mascotaSelected;
    }

    /**
     *
     * @return
     */
    public Refugio getRefugio() {
        return refugio;
    }

    /**
     *
     * @param refugio
     */
    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }

    /**
     *
     * @return
     */
    public String getBase64firma() {
        return base64firma;
    }

    /**
     *
     * @param base64firma
     */
    public void setBase64firma(String base64firma) {
        this.base64firma = base64firma;
    }

    /**
     *
     * @return
     */
    public String getValueFirma() {
        return valueFirma;
    }

    /**
     *
     * @param valueFirma
     */
    public void setValueFirma(String valueFirma) {
        this.valueFirma = valueFirma;
    }
    
    /**
     *
     * @return
     */
    public String getFilterDisplayName() {
        return this.mascotaFilter.substring(0,1).toUpperCase() + this.mascotaFilter.substring(1).toLowerCase();
    }

}
