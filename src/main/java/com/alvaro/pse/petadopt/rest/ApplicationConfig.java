/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author alvar
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.alvaro.pse.petadopt.json.ChatWriter.class);
        resources.add(com.alvaro.pse.petadopt.json.ClienteWriter.class);
        resources.add(com.alvaro.pse.petadopt.json.ComunidadReader.class);
        resources.add(com.alvaro.pse.petadopt.json.EspeciesWriter.class);
        resources.add(com.alvaro.pse.petadopt.json.HistoricoSolicitudesWriter.class);
        resources.add(com.alvaro.pse.petadopt.json.MascotaWriter.class);
        resources.add(com.alvaro.pse.petadopt.json.RefugioReader.class);
        resources.add(com.alvaro.pse.petadopt.json.RefugioWriter.class);
        resources.add(com.alvaro.pse.petadopt.json.UsuarioReader.class);
        resources.add(com.alvaro.pse.petadopt.json.UsuarioWriter.class);
        resources.add(com.alvaro.pse.petadopt.rest.AdministradorFacadeREST.class);
        resources.add(com.alvaro.pse.petadopt.rest.ChatFacadeREST.class);
        resources.add(com.alvaro.pse.petadopt.rest.ClienteFacadeREST.class);
        resources.add(com.alvaro.pse.petadopt.rest.EspecieFacadeREST.class);
        resources.add(com.alvaro.pse.petadopt.rest.HistoricoSolicitudesFacadeREST.class);
        resources.add(com.alvaro.pse.petadopt.rest.MascotaFacadeREST.class);
        resources.add(com.alvaro.pse.petadopt.rest.RefugioFacadeREST.class);
        resources.add(com.alvaro.pse.petadopt.rest.UsuarioFacadeREST.class);
    }
    
}
