/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.administrador;

import com.alvaro.pse.petadopt.entities.Especie;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
import com.alvaro.pse.petadopt.entities.UsuarioGrupo;
import com.alvaro.pse.petadopt.json.ClienteWriter;
import com.alvaro.pse.petadopt.json.EspeciesReader;
import com.alvaro.pse.petadopt.json.EspeciesWriter;
import com.alvaro.pse.petadopt.json.RefugioReader;
import com.alvaro.pse.petadopt.json.RefugioWriter;
import com.alvaro.pse.petadopt.json.UsuarioGrupoReader;
import com.alvaro.pse.petadopt.json.UsuarioWriter;
import java.util.ArrayList;
import java.util.List;
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
public class AdministradorClientBean {

    private Client client;
    private WebTarget target;

    @Inject
    private AdministradorBackingBean bean;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        // target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Usuario getUsuarioById(Long id) {
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

    private Refugio getRefugioById(Long id) {
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

    public void aprobarRefugio(Long idRefugio) {
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

    public void rechazarRefugio(Long idRefugio) {
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
            if (response.getStatus() == 204) {
                System.out.println("Se ha rechazado la solicitud");
            }
        }
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> all = new ArrayList<>();
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuario");
        Response response = target.register(UsuarioWriter.class)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            all = response.readEntity(List.class);
        }
        return all;
    }

    // indica si el usuario con el id asociado es administrador o no
    public boolean isAdmin(Long id) {
        boolean isAdmin = false;
        // necesitamos el email del usuario
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuario");
        Response response = target.path("{idUsuario}")
                .resolveTemplate("idUsuario", id)
                .request()
                .get();
        System.out.println(response);
        if (response.getStatus() == 200) {
            Usuario u = response.readEntity(Usuario.class);
            // buscamos si en la tabla de roles aparece el par (admin, email)
            target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuariogrupo");
            response = target.register(UsuarioGrupoReader.class)
                    .path("is-user-admin/{email}")
                    .resolveTemplate("email", u.getEmail())
                    .request()
                    .get();
            
            if(response.getStatus() == 200){
                isAdmin = response.readEntity(Boolean.class);
            }
        }
        return isAdmin;
    }

    public List<Especie> getAllEspecies() {
        List<Especie> especies = null;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.especie");
        Response response = target.register(EspeciesWriter.class)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            especies = response.readEntity(List.class);
        }
        return especies;
    }

    public void addEspecie() {
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.especie");
        Especie e = new Especie();
        e.setNombre(bean.getNuevaEspecie());
        Response response = target.register(EspeciesReader.class)
                .request()
                .post(Entity.entity(e, MediaType.APPLICATION_JSON));
    }

    public void deleteEspecie() {
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.especie");
        Especie e = new Especie();
        e.setNombre(bean.getNuevaEspecie());
        Response response = target.register(EspeciesReader.class)
                .path("{id}")
                .resolveTemplate("id", bean.getIdEspecieSelected())
                .request()
                .delete();
    }

    public void deleteUsuario() {
        // eliminar un usuario consiste en desactivar su cuenta
        // necesitamos el email del usuario en cuestión
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuario");
        Response response = target.path("{idUsuario}")
                .resolveTemplate("idUsuario", bean.getIdUsuarioSelected())
                .request()
                .get();
        System.out.println(response);
        if (response.getStatus() == 200) {
            Usuario u = response.readEntity(Usuario.class);
            // ahora desactivamos la cuenta
            response = target.path("{idUsuario}")
                    .resolveTemplate("idUsuario", bean.getIdUsuarioSelected())
                    .request()
                    .put(Entity.entity(u, MediaType.APPLICATION_JSON));
            if (response.getStatus() == 204) {
                System.out.println("Cuenta dedscativada");
            }
        }

    }
}
