/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.administrador;

import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
import com.alvaro.pse.petadopt.json.RefugioReader;
import com.alvaro.pse.petadopt.json.RefugioWriter;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
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
public class AdministradorClientBean {

    private Client client;
    private WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        // target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }
    public Usuario getUsuarioById(Long id){
        Usuario found = null;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuario");
        Response response = target.register(RefugioWriter.class)
                .path("{idUsuario}")
                .resolveTemplate("idUsuario", id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            found = response.readEntity(Usuario.class);
        }
        return found;
    }
    
    private Refugio getRefugioById(Long id){
        Refugio found = null;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.refugio");
        Response response = target.register(RefugioWriter.class)
                .path("{idRefugio}")
                .resolveTemplate("idRefugio", id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            found = response.readEntity(Refugio.class);
        }
        return found;
    }

    public List<Refugio> findRefugiosPendientesAceptacion() {
        List<Refugio> found = null;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.refugio");
        Response response = target.register(RefugioWriter.class)
                .path("find-by-estado/{estado}")
                .resolveTemplate("estado", false)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            found = response.readEntity(List.class);
        }
        return found;
    }
    
    public void aprobarRefugio(Long idRefugio){
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.refugio");
        Refugio r = this.getRefugioById(idRefugio);
        r.setVerificado(true);
        Response response = target.path("{refugioId}")
                .resolveTemplate("refugioId", idRefugio)
                .request()
                .put(Entity.entity(r, MediaType.APPLICATION_JSON));
        //System.out.println("respuesta: "+response);
        if (response.getStatus() == 204) {
            System.out.println("Se ha aprobado la solicitud");
        }
    }
    
    public void rechazarRefugio(Long idRefugio){
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.refugio");
        Refugio r = this.getRefugioById(idRefugio);
        // rechazar un refugio implica eliminarlo de la bbdd
        Response response = target.path("{refugioId}")
                .resolveTemplate("refugioId", idRefugio)
                .request()
                .delete();
        //System.out.println("respuesta: "+response);
         if (response.getStatus() == 204) {
            System.out.println("Se ha aprobado la solicitud");
            target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuario");
            response = target.path("{usuarioId}")
                .resolveTemplate("usuarioId", idRefugio)
                .request()
                .delete();
            if(response.getStatus() ==204){
                System.out.println("Se ha rechazado la solicitud");
            }
        }
    }
}
