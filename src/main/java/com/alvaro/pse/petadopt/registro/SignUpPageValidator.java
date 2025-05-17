/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.registro;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 *
 * @author alvar
 */
@Named
@RequestScoped
public class SignUpPageValidator {
    
    @Email
    private String email;
    
    @Size(min = 8)
    private String password;
    
    @Size(min =10, max = 10)
    private String cif;
    
    @Size(max = 9)
    private String telefono;
    
    @Size(min =10, max = 10)
    private String nif;

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
    public String getCIF() {
        return cif;
    }

    /**
     *
     * @param CIF
     */
    public void setCIF(String CIF) {
        this.cif = CIF;
    }

    /**
     *
     * @return
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
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


    
}
