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
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.util.EscapeUtils;

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
    
    private Cliente cliente;
    private Refugio refugio;
    
    public LoginPageBackingBean(){
         this.stringUtils = new StringUtils();
    }

    public boolean isUsuarioFound() {
        return usuarioFound;
    }

    public void setUsuarioFound(boolean usuarioFound) {
        this.usuarioFound = usuarioFound;
    }

    private Usuario usuarioLogeado;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private UploadedFile file;
    private UploadedFiles files;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = this.stringUtils.capitalizeAllFirstLetters(rol);
    }

    public StringUtils getStringUtils() {
        return stringUtils;
    }

    public void setStringUtils(StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFiles getFiles() {
        return files;
    }

    public void setFiles(UploadedFiles files) {
        this.files = files;
    }

    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Refugio getRefugio() {
        return refugio;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }
    
    

    public void showError() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Los datos introducidos no se corresponden con ningún usuario");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
