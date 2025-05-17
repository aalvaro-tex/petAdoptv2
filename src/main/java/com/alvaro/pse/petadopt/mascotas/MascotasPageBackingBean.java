/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.mascotas;

import com.alvaro.pse.petadopt.entities.Mascota;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author alvar
 */
@Named
@SessionScoped
public class MascotasPageBackingBean implements Serializable {

    private String verMascotasFilter = "todas";
    private Long idMascotaSelected;
    private Mascota mascotaSelected;
    private UploadedFile mascotaImg;
    private UploadedFile mascotaUpdateImg;

    private String nombreMascotaSeleccionada = "";
    private String especieMascotaSeleccionada = "";
    private String razaMascotaSeleccionada = "";
    private String edadMascotaSeleccionada = "";
    private String estadoSaludMascotaSeleccionada = "";
    private String costeAdopcionMascotaSeleccionada = "";
    private String fotoMascotaSeleccionada = "";

    private String nombre;
    private String especie;
    private String raza;

    private int edad;
    private String estadoSalud;
    private double costeAdopcion;
    private String foto;

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getRaza() {
        return raza;
    }

    /**
     *
     * @param raza
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     *
     * @return
     */
    public int getEdad() {
        return edad;
    }

    /**
     *
     * @param edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     *
     * @return
     */
    public String getEspecie() {
        return especie;
    }

    /**
     *
     * @param especie
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     *
     * @return
     */
    public String getEstadoSalud() {
        return estadoSalud;
    }

    /**
     *
     * @param estadoSalud
     */
    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    /**
     *
     * @return
     */
    public double getCosteAdopcion() {
        return costeAdopcion;
    }

    /**
     *
     * @param costeAdopcion
     */
    public void setCosteAdopcion(double costeAdopcion) {
        this.costeAdopcion = costeAdopcion;
    }

    /**
     *
     * @return
     */
    public String getFoto() {
        return foto;
    }

    /**
     *
     * @param foto
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     *
     * @return
     */
    public String getVerMascotasFilter() {
        return verMascotasFilter;
    }

    /**
     *
     * @param verMascotasFilter
     */
    public void setVerMascotasFilter(String verMascotasFilter) {
        this.verMascotasFilter = verMascotasFilter;

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
    public UploadedFile getMascotaImg() {
        return mascotaImg;
    }

    /**
     *
     * @param mascotaImg
     */
    public void setMascotaImg(UploadedFile mascotaImg) {
        this.mascotaImg = mascotaImg;
    }

    /**
     *
     * @return
     */
    public String getNombreMascotaSeleccionada() {
        return nombreMascotaSeleccionada;
    }

    /**
     *
     * @param nombreMascotaSeleccionada
     */
    public void setNombreMascotaSeleccionada(String nombreMascotaSeleccionada) {
        this.nombreMascotaSeleccionada = nombreMascotaSeleccionada;
    }

    /**
     *
     * @return
     */
    public String getEspecieMascotaSeleccionada() {
        return especieMascotaSeleccionada;
    }

    /**
     *
     * @param especieMascotaSeleccionada
     */
    public void setEspecieMascotaSeleccionada(String especieMascotaSeleccionada) {
        this.especieMascotaSeleccionada = especieMascotaSeleccionada;
    }

    /**
     *
     * @return
     */
    public String getRazaMascotaSeleccionada() {
        return razaMascotaSeleccionada;
    }

    /**
     *
     * @param razaMascotaSeleccionada
     */
    public void setRazaMascotaSeleccionada(String razaMascotaSeleccionada) {
        this.razaMascotaSeleccionada = razaMascotaSeleccionada;
    }

    /**
     *
     * @return
     */
    public String getEdadMascotaSeleccionada() {
        return edadMascotaSeleccionada;
    }

    /**
     *
     * @param edadMascotaSeleccionada
     */
    public void setEdadMascotaSeleccionada(String edadMascotaSeleccionada) {
        this.edadMascotaSeleccionada = edadMascotaSeleccionada;
    }

    /**
     *
     * @return
     */
    public String getEstadoSaludMascotaSeleccionada() {
        return estadoSaludMascotaSeleccionada;
    }

    /**
     *
     * @param estadoSaludMascotaSeleccionada
     */
    public void setEstadoSaludMascotaSeleccionada(String estadoSaludMascotaSeleccionada) {
        this.estadoSaludMascotaSeleccionada = estadoSaludMascotaSeleccionada;
    }

    /**
     *
     * @return
     */
    public String getCosteAdopcionMascotaSeleccionada() {
        return costeAdopcionMascotaSeleccionada;
    }

    /**
     *
     * @param costeAdopcionMascotaSeleccionada
     */
    public void setCosteAdopcionMascotaSeleccionada(String costeAdopcionMascotaSeleccionada) {
        this.costeAdopcionMascotaSeleccionada = costeAdopcionMascotaSeleccionada;
    }

    /**
     *
     * @return
     */
    public String getFotoMascotaSeleccionada() {
        return fotoMascotaSeleccionada;
    }

    /**
     *
     * @param fotoMascotaSeleccionada
     */
    public void setFotoMascotaSeleccionada(String fotoMascotaSeleccionada) {
        this.fotoMascotaSeleccionada = fotoMascotaSeleccionada;
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
    public UploadedFile getMascotaUpdateImg() {
        return mascotaUpdateImg;
    }

    /**
     *
     * @param mascotaUpdateImg
     */
    public void setMascotaUpdateImg(UploadedFile mascotaUpdateImg) {
        this.mascotaUpdateImg = mascotaUpdateImg;
    }

    /**
     *
     */
    public void cleanInputs() {
        this.setCosteAdopcion(0);
        this.setEdad(0);
        this.setEspecie("");
        this.setEstadoSalud("");
        this.setFoto("");
        this.setNombre("");
        this.setRaza("");
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @param estado
     * @return
     */
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
