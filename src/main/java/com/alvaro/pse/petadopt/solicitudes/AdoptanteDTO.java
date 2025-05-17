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

    /**
     *
     * @param email
     * @param id
     * @param listaNegra
     */
    public AdoptanteDTO(String email, Long id, String listaNegra) {
        this.email = email;
        this.id = id;
        this.listaNegra = listaNegra;
    }

    /**
     *
     */
    public AdoptanteDTO() {
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
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getListaNegra() {
        return listaNegra;
    }

    /**
     *
     * @param listaNegra
     */
    public void setListaNegra(String listaNegra) {
        this.listaNegra = listaNegra;
    }
    
    
    
}
