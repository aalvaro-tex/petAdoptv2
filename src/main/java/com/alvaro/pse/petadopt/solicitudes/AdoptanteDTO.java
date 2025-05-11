/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.solicitudes;

/**
 *
 * @author alvar
 */
public class AdoptanteDTO {
    
    private String email;
    private Long id;
    private String listaNegra;

    public AdoptanteDTO(String email, Long id, String listaNegra) {
        this.email = email;
        this.id = id;
        this.listaNegra = listaNegra;
    }

    public AdoptanteDTO() {
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListaNegra() {
        return listaNegra;
    }

    public void setListaNegra(String listaNegra) {
        this.listaNegra = listaNegra;
    }
    
    
    
}
