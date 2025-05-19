/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.registro;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author alvar
 */
@Named
@SessionScoped
public class SignUpPageBackingBean implements Serializable {


    private List<String> comunidades;
    private String rol;

    private boolean terminosAceptados;

    /* Atributos de usuario, comunes a refugio y cliente */
    private String email;
    private String password1;
    private String password2;
    private UploadedFile fotoPerfil;

    /* Atributos del registro de refugio */
    private String nombreRefugio;
    private String cif;
    private String domicilioSocial;
    private String telefonoRefugio;
    private String comunidadRefugio;


    /* Atributos del registro de cliente */
    private String nombreCliente;
    private String apellidos;
    private String domicilio;
    private String nif;
    private String fechaNacimiento;

    /**
     *
     * @return
     */
    public UploadedFile getFotoPerfil() {
        return fotoPerfil;
    }

    /**
     *
     * @param fotoPerfil
     */
    public void setFotoPerfil(UploadedFile fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    /**
     *
     */
    public SignUpPageBackingBean() {

    }

    /**
     *
     * @return
     */
    public String getTelefonoRefugio() {
        return telefonoRefugio;
    }

    /**
     *
     * @param telefonoRefugio
     */
    public void setTelefonoRefugio(String telefonoRefugio) {
        this.telefonoRefugio = telefonoRefugio;
    }

    /**
     *
     * @return
     */
    public String getComunidadRefugio() {
        return comunidadRefugio;
    }

    /**
     *
     * @param comunidadRefugio
     */
    public void setComunidadRefugio(String comunidadRefugio) {
        this.comunidadRefugio = comunidadRefugio;
    }

    /**
     *
     * @return
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     *
     * @param nombreCliente
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     *
     * @return
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     *
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     *
     * @return
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     *
     * @param domicilio
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     *
     * @return
     */
    public String getNif() {
        return nif;
    }

    /**
     *
     * @param nif
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     *
     * @return
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     *
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     *
     * @return
     */
    public String getNombreRefugio() {
        return nombreRefugio;
    }

    /**
     *
     * @param nombreRefugio
     */
    public void setNombreRefugio(String nombreRefugio) {
        this.nombreRefugio = nombreRefugio;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getCif() {
        return cif;
    }

    /**
     *
     * @param cif
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     *
     * @return
     */
    public String getDomicilioSocial() {
        return domicilioSocial;
    }

    /**
     *
     * @param domicilioSocial
     */
    public void setDomicilioSocial(String domicilioSocial) {
        this.domicilioSocial = domicilioSocial;
    }

    /**
     *
     * @return
     */
    public String getTelefono() {
        return telefonoRefugio;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefonoRefugio = telefono;
    }

    /**
     *
     * @return
     */
    public String getPassword1() {
        return password1;
    }

    /**
     *
     * @param password1
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    /**
     *
     * @return
     */
    public String getPassword2() {
        return password2;
    }

    /**
     *
     * @param password2
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     *
     * @return
     */
    public List<String> getComunidades() {
        return comunidades;
    }

    /**
     *
     */
    public void setComunidades() {
    }

    /**
     *
     * @return
     */
    public String getRol() {
        return rol;
    }

    /**
     *
     * @param rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     *
     * @return
     */
    public String getComunidad() {
        return comunidadRefugio;
    }

    /**
     *
     * @param comunidad
     */
    public void setComunidad(String comunidad) {
        this.comunidadRefugio = comunidad;
    }

    /**
     *
     * @return
     */
    public boolean isTerminosAceptados() {
        return terminosAceptados;
    }

    /**
     *
     * @param terminosAceptados
     */
    public void setTerminosAceptados(boolean terminosAceptados) {
        this.terminosAceptados = terminosAceptados;
    }

    /**
     *
     * @param mensaje
     */
    public void showError(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *
     */
    public void clearValues() {
        setCif(null);
        setEmail(null);
        setPassword1(null);
        setPassword2(null);
        setFotoPerfil(null);
        setNombreRefugio(null);
        setDomicilioSocial(null);
        setTelefonoRefugio(null);
        setComunidadRefugio(null);
        setNombreCliente(null);
        setApellidos(null);
        setDomicilio(null);
        setNif(null);
        setFechaNacimiento(null);
        setTerminosAceptados(false);
    }

}
