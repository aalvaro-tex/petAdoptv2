/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.adopcion;

import com.alvaro.pse.petadopt.entities.Mascota;
import com.alvaro.pse.petadopt.entities.Refugio;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author alvar
 */
@Named
@SessionScoped
public class AdopcionBackingBean implements Serializable {

    private String mascotaFilter = "todas";
    private Long idMascotaSelected;
    private Mascota mascotaSelected;
    
    private Refugio refugio;

    public String getMascotaFilter() {
        return mascotaFilter;
    }

    public void setMascotaFilter(String mascotaFilter) {
        this.mascotaFilter = mascotaFilter;
    }

    public Long getIdMascotaSelected() {
        return idMascotaSelected;
    }

    public void setIdMascotaSelected(Long idMascotaSelected) {
        this.idMascotaSelected = idMascotaSelected;
    }

    public Mascota getMascotaSelected() {
        return mascotaSelected;
    }

    public void setMascotaSelected(Mascota mascotaSelected) {
        this.mascotaSelected = mascotaSelected;
    }

    public Refugio getRefugio() {
        return refugio;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }
    
    
    
    public String getFilterDisplayName() {
        String text = "";
        switch (this.mascotaFilter) {
            case "todas":
                text = "Todas las mascotas disponibles";
                break;
            case "gato":
                text = "Gatos";
                break;
            case "perro":
                text = "Perros";
                break;
            case "pajaro":
                text = "Pájaros";
                break;
            case "roedor":
                text = "Roedores";
                break;
            default:
                break;
        }
        return text;
    }

}
