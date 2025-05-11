/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.registro;

import com.alvaro.pse.petadopt.login.*;
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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getCIF() {
        return cif;
    }

    public void setCIF(String CIF) {
        this.cif = CIF;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    

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


    
}
