/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.administrador;

import com.alvaro.pse.petadopt.entities.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.SortMeta;

/**
 *
 * @author alvar
 */
@Named
@SessionScoped
public class AdministradorBackingBean implements Serializable {

    private String verClientesFilter = "todos";
    private Long idUsuarioSelected;
    private Long idEspecieSelected;
    
    private String nuevaEspecie;

    public String getNuevaEspecie() {
        return nuevaEspecie;
    }

    public void setNuevaEspecie(String nuevaEspecie) {
        this.nuevaEspecie = nuevaEspecie;
    }

    public Long getIdEspecieSelected() {
        return idEspecieSelected;
    }

    public void setIdEspecieSelected(Long idEspecieSelected) {
        this.idEspecieSelected = idEspecieSelected;
    }

    public Long getIdUsuarioSelected() {
        return idUsuarioSelected;
    }

    public void setIdUsuarioSelected(Long idUsuarioSelected) {
        this.idUsuarioSelected = idUsuarioSelected;
    }

    public String getVerClientesFilter() {
        return verClientesFilter;
    }

    public void setVerClientesFilter(String verClientesFilter) {
        this.verClientesFilter = verClientesFilter;
    }

    public int sortByEmail(Usuario u1, Usuario u2, SortMeta sortMeta) {
        int sort = 0;
        if (u1.equals(u2)) {
            sort = 0;
        } else if (u1.getEmail().charAt(0) > u2.getEmail().charAt(0)) {
            sort = 1;
        } else {
            sort = -1;
        }
        return sort;
    }

    public String getFilterDisplayName() {
        String displayName = "";
        switch (this.verClientesFilter) {
            case "todos":
                displayName = "Todos los usuarios";
                break;
            case "cliente":
                displayName = "Clientes";
                break;
            case "refugio":
                displayName = "Refugios";
                break;
            default:
                break;
        }
        return displayName;
    }

}
