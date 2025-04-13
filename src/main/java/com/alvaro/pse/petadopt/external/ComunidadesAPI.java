/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.external;

import com.alvaro.pse.petadopt.shared.Constantes;
import com.alvaro.pse.petadopt.json.ComunidadReader;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;



/**
 *
 * @author alvar
 */
@Named
@RequestScoped
public class ComunidadesAPI implements Serializable{
    
    Client client;
    WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://apiv1.geoapi.es/comunidades?type=JSON&version=2024.07&key="+Constantes.COMUNIDADES_API_KEY);
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public void getComunidades(){
        // Make a GET request
        Response response = target.request().get();
        String json = response.readEntity(String.class);
        
    }
}
