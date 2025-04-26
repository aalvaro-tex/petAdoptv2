/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.registro;

import com.alvaro.pse.petadopt.external.ComunidadesAPI;
import com.alvaro.pse.petadopt.utils.StringUtils;
import java.io.Serializable;
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
    private ComunidadesAPI api;
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

    public UploadedFile getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(UploadedFile fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public SignUpPageBackingBean() {

    }

    public String getTelefonoRefugio() {
        return telefonoRefugio;
    }

    public void setTelefonoRefugio(String telefonoRefugio) {
        this.telefonoRefugio = telefonoRefugio;
    }

    public String getComunidadRefugio() {
        return comunidadRefugio;
    }

    public void setComunidadRefugio(String comunidadRefugio) {
        this.comunidadRefugio = comunidadRefugio;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public ComunidadesAPI getApi() {
        return api;
    }

    public void setApi(ComunidadesAPI api) {
        this.api = api;
    }

    public String getNombreRefugio() {
        return nombreRefugio;
    }

    public void setNombreRefugio(String nombreRefugio) {
        this.nombreRefugio = nombreRefugio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDomicilioSocial() {
        return domicilioSocial;
    }

    public void setDomicilioSocial(String domicilioSocial) {
        this.domicilioSocial = domicilioSocial;
    }

    public String getTelefono() {
        return telefonoRefugio;
    }

    public void setTelefono(String telefono) {
        this.telefonoRefugio = telefono;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public List<String> getComunidades() {
        return comunidades;
    }

    public void setComunidades() {
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getComunidad() {
        return comunidadRefugio;
    }

    public void setComunidad(String comunidad) {
        this.comunidadRefugio = comunidad;
    }

    public boolean isTerminosAceptados() {
        return terminosAceptados;
    }

    public void setTerminosAceptados(boolean terminosAceptados) {
        this.terminosAceptados = terminosAceptados;
    }

    public void showError(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

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
