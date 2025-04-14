/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.login;

import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
import com.alvaro.pse.petadopt.json.ClienteWriter;
import com.alvaro.pse.petadopt.json.RefugioWriter;
import com.alvaro.pse.petadopt.json.UsuarioReader;
import com.alvaro.pse.petadopt.json.UsuarioWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
public class LoginPageClientBean implements Serializable {

    Client client;
    WebTarget target;

    @Inject
    LoginPageBackingBean bean;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public String findUsuario() {
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuario");
        String success = "failure";
        Usuario u = new Usuario();
        u.setEmail(bean.getEmail());
        u.setPassword(bean.getPassword());
        Response response = target
                .path("find-by-login")
                .request()
                .post(Entity.entity(u, MediaType.APPLICATION_JSON));
        if (response.getStatus() == 200) {
            Usuario found = response.readEntity(Usuario.class);
            bean.setUsuarioLogeado(found);
            bean.setUsuarioFound(true);

            // aqui sabemos que el usuario está registrado, 
            // pero hay que comprobar si está iniciando sesión desde
            // la página correcta (refugio o cliente)
            // como sabemos desde qué página está accediendo, basta llamar
            // a getById de la entidad correspondiente
            if (bean.getRol().equalsIgnoreCase("Cliente")) {
                target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.cliente");
                response = target.register(ClienteWriter.class)
                        .path("{id}")
                        .resolveTemplate("id", found.getId())
                        .request(MediaType.APPLICATION_JSON)
                        .get();
                if (response.getStatus() == 200) {
                    Cliente c = response.readEntity(Cliente.class);
                    bean.setCliente(c);
                    bean.setRefugio(null);
                    // hemos encontrado el id del usuario que ha intentado 
                    // inicar sesión, luego sí está accediendo correctamente
                    success = "success";
                } else {
                    bean.showError();
                }

            } else if (bean.getRol().equalsIgnoreCase("Refugio")) {
                target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.refugio");
                response = target.register(RefugioWriter.class)
                        .path("{id}")
                        .resolveTemplate("id", found.getId())
                        .request(MediaType.APPLICATION_JSON)
                        .get();
                if (response.getStatus() == 200) {
                    // hemos encontrado el id del usuario que ha intentado 
                    // inicar sesión, luego sí está accediendo correctamente
                    Refugio r = response.readEntity(Refugio.class);
                    bean.setRefugio(r);
                    bean.setCliente(null);
                    // hemos encontrado el id del usuario que ha intentado 
                    // inicar sesión, luego sí está accediendo correctamente
                    success = "success";
                } else {
                    bean.showError();
                }

            }

        } else {
            bean.setUsuarioFound(false);
            bean.showError();
        }
        //System.out.println(success);
        return success;

    }
}
