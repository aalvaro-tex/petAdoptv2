/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.login;

import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.utils.StringUtils;
import com.alvaro.pse.petadopt.entities.Usuario;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;

/**
 *
 * @author alvar
 */
@Named
@SessionScoped
public class LoginPageBackingBean implements Serializable {

    private String rol;

    private StringUtils stringUtils;

    private String email;
    private String password;

    private boolean usuarioFound;
    private boolean tieneNotificaciones;

    private Cliente cliente;
    private Refugio refugio;
    
    private Map<String,String> especies;

    /**
     *
     */
    public LoginPageBackingBean() {
        this.stringUtils = new StringUtils();
    }

    /**
     *
     * @return
     */
    public boolean isUsuarioFound() {
        return usuarioFound;
    }

    /**
     *
     * @param usuarioFound
     */
    public void setUsuarioFound(boolean usuarioFound) {
        this.usuarioFound = usuarioFound;
    }

    private Usuario usuarioLogeado;

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
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    private UploadedFile file;
    private UploadedFiles files;

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
        this.rol = this.stringUtils.capitalizeAllFirstLetters(rol);
    }

    /**
     *
     * @return
     */
    public StringUtils getStringUtils() {
        return stringUtils;
    }

    /**
     *
     * @param stringUtils
     */
    public void setStringUtils(StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    /**
     *
     * @return
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     *
     * @return
     */
    public UploadedFiles getFiles() {
        return files;
    }

    /**
     *
     * @param files
     */
    public void setFiles(UploadedFiles files) {
        this.files = files;
    }

    /**
     *
     */
    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     *
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *
     * @return
     */
    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    /**
     *
     * @param usuarioLogeado
     */
    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    /**
     *
     * @return
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     *
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
    public boolean isTieneNotificaciones() {
        return tieneNotificaciones;
    }

    /**
     *
     * @param tieneNotificaciones
     */
    public void setTieneNotificaciones(boolean tieneNotificaciones) {
        this.tieneNotificaciones = tieneNotificaciones;
    }

    /**
     *
     */
    public void showError() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los datos introducidos no se corresponden con ningún usuario");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *
     * @return
     */
    public Map<String,String> getEspecies() {
        return especies;
    }

    /**
     *
     * @param especies
     */
    public void setEspecies(Map<String,String> especies) {
        this.especies = especies;
    }
    
    /**
     *
     */
    public void clearValues() {
        setEmail(null);
        setPassword(null);
    }
}
