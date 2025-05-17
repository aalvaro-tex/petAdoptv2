/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.login;

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
public class LoginPageValidator {
    
    @Email
    private String email;
    
    @Size(min = 8)
    private String password;

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
