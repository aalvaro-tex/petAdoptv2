/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.mascotas;

import com.alvaro.pse.petadopt.entities.Mascota;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author alvar
 */
@Named
@SessionScoped
public class MascotasPageBackingBean implements Serializable {

    private String verMascotasFilter = "todas";
    private Long idMascotaSelected;

  
    private String nombre;
    private String especie;
    private String raza;

    private int edad;
    private String estadoSalud;
    private double costeAdopcion;
    private String foto;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public double getCosteAdopcion() {
        return costeAdopcion;
    }

    public void setCosteAdopcion(double costeAdopcion) {
        this.costeAdopcion = costeAdopcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getVerMascotasFilter() {
        return verMascotasFilter;
    }

    public void setVerMascotasFilter(String verMascotasFilter) {
        this.verMascotasFilter = verMascotasFilter;

    }

    public Long getIdMascotaSelected() {
        return idMascotaSelected;
    }

    public void setIdMascotaSelected(Long idMascotaSelected) {
        this.idMascotaSelected = idMascotaSelected;
    }


    public String getFilterDisplayName() {
        String text = "";
        switch (this.verMascotasFilter) {
            case "todas":
                text = "Todas las mascotas";
                break;
            case "sin_solicitud":
                text = "Mascotas sin adoptar";
                break;
            case "adoptada":
                text = "Mascotas adoptadas";
                break;
            case "pendiente":
                text = "Mascotas pendientes de revisar";
                break;
            default:
                break;
        }
        return text;
    }

    public String getEstadoDisplayName(String estado) {
        String text = "";
        switch (estado) {
            case "sin_solicitud":
                text = "Sin solicitud";
                break;
            case "adoptada":
                text = "Adoptada";
                break;
            case "pendiente":
                text = "Pendiente de aprobar";
                break;
            default:
                break;
        }
        return text;
    }
}
