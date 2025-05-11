/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.micuenta;

import com.alvaro.pse.petadopt.entities.Usuario;
import com.alvaro.pse.petadopt.login.LoginPageBackingBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author alvar
 */
@Named
@RequestScoped
public class MiCuentaPageClientBean {

    Client client;
    WebTarget target;
    
    @Inject
    LoginPageBackingBean loginBean;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();

    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public String deactivateAccount(){
        String success = "failure";
        // si un usuario quiere borrar su cuenta
        // la desactivamos
        Usuario u = loginBean.getUsuarioLogeado();
        u.setActivo(false);
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuario");
        Response response = target.path("{idUsuario}")
               .resolveTemplate("idUsuario", u.getId())
                .request()
                .put(Entity.entity(u, MediaType.APPLICATION_JSON));
        System.out.println(response);
        if(response.getStatus() == 204){
            success = "success";
        }
        System.out.println(success);
        return success;
    }
}
