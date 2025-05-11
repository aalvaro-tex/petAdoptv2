/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.login;

import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.Especie;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
import com.alvaro.pse.petadopt.jaas.UsuarioEJB;
import com.alvaro.pse.petadopt.json.ClienteWriter;
import com.alvaro.pse.petadopt.json.EspeciesWriter;
import com.alvaro.pse.petadopt.json.MascotaWriter;
import com.alvaro.pse.petadopt.json.RefugioWriter;
import com.alvaro.pse.petadopt.json.UsuarioReader;
import com.alvaro.pse.petadopt.json.UsuarioWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.primefaces.component.keyboard.KeyboardBase.PropertyKeys.password;

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
    @Inject
    UsuarioEJB usuarioEJB;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        bean.setCliente(null);
        bean.setRefugio(null);
        bean.setUsuarioLogeado(null);

    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    /*
    public String findUsuario() {
        this.getEspecies();
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
                // antes de buscar en la tabla de clientes, debemos comprobar
                // si es administrador o no

                target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.administrador");
                response = target.register(ClienteWriter.class)
                        .path("{id}")
                        .resolveTemplate("id", found.getId())
                        .request(MediaType.APPLICATION_JSON)
                        .get();
                if (response.getStatus() == 200) {
                    // es administrador 
                    // creamos un nuevo cliente pero asignamos el rol de la 
                    // aplicación pasa a ser 'Administrador'

                    Cliente c = response.readEntity(Cliente.class);
                    bean.setCliente(c);
                    bean.setRefugio(null);
                    bean.setRol("Administrador");
                    success = "success";
                } else {

                    // no es administrador luego debe ser cliente 'normal'
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
                    bean.setTieneNotificaciones(this.hasNotifications());
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
        bean.clearValues();
        return success;

    }
     */
    public String login() {
        String success = "failure";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            System.out.println(bean.getEmail());
            request.login(bean.getEmail(), bean.getPassword());
            this.getEspecies();
        } catch (ServletException e) {
            System.out.println("Login incorrecto");
        }
        try {
            Usuario found = usuarioEJB.findByEmail(bean.getEmail());
            if (found.getActivo() == true) {
                bean.setUsuarioLogeado(found);

                System.out.println(request.isUserInRole("usuario"));
                System.out.println("Id usuario: " + found.getId());
                if (request.isUserInRole("usuario")) {

                    // es un ususario "normal"
                    // necesitamos comprobar que está iniciando sesión desde su página
                    if (bean.getRol().equalsIgnoreCase("refugio")) {
                        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.refugio");
                        Response response = target.register(RefugioWriter.class)
                                .path("{id}")
                                .resolveTemplate("id", found.getId())
                                .request(MediaType.APPLICATION_JSON)
                                .get();
                        if (response.getStatus() == 200) {
                            // es refugio
                            System.out.println("Es refugio");
                            Refugio r = response.readEntity(Refugio.class);
                            bean.setRefugio(r);
                            bean.setCliente(null);
                            bean.setTieneNotificaciones(this.hasNotifications());
                            // hemos encontrado el id del usuario que ha intentado 
                            // inicar sesión, luego sí está accediendo correctamente
                            success = "success";
                        } else {
                            bean.showError();
                        }
                    } else if (bean.getRol().equalsIgnoreCase("cliente")) {
                        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.cliente");
                        Response response = target.register(ClienteWriter.class)
                                .path("{id}")
                                .resolveTemplate("id", found.getId())
                                .request(MediaType.APPLICATION_JSON)
                                .get();
                        if (response.getStatus() == 200) {
                            // es cliente
                            System.out.println("Es cliente");
                            Cliente c = response.readEntity(Cliente.class);
                            bean.setCliente(c);
                            bean.setRefugio(null);
                            // hemos encontrado el id del usuario que ha intentado 
                            // inicar sesión, luego sí está accediendo correctamente
                            success = "success";
                        } else {
                            bean.showError();
                        }
                    }

                } else if (request.isUserInRole("admin")) {
                    // es administrador
                    // basta crear un cliente vacio, asignarle el id del usuario
                    // y poner el rol de la aplicación en modo 'Administrador'
                    success = "success";
                    Cliente c = new Cliente();
                    c.setId(usuarioEJB.findByEmail(bean.getEmail()).getId());
                    bean.setCliente(c);
                    bean.setRol("Administrador");

                } else {
                    bean.showError();
                }
            } else {
                bean.showError();
            }
        } catch (Exception e) {
            bean.showError();
        }

        System.out.println(success);
        return success;
    }

    public String logout() {
        String logoutok = "";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            bean.setUsuarioLogeado(null);
            bean.setCliente(null);
            bean.setRefugio(null);
            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
            System.out.println("Cierre de sesión");
            logoutok = "logoutok";
        } catch (ServletException e) {
            System.out.println(e);
            System.out.println("Fallo durante el proceso de logout!");
        }
        return logoutok;
    }

    // Indica si el usuario logueado tiene alguna notificación pendiente
    // Esto es, solicitud pendiente
    private boolean hasNotifications() {
        boolean tieneNotificaciones = false;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
        Response response = target.register(MascotaWriter.class)
                .path("find-by-refugio-estado/{idRefugio}/{estado}")
                .resolveTemplate("idRefugio", bean.getUsuarioLogeado().getId())
                .resolveTemplate("estado", "pendiente")
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            tieneNotificaciones = !response.readEntity(List.class).isEmpty();
        }
        return tieneNotificaciones;
    }

    private void getEspecies() {
        HashMap<String, String> especies = new HashMap<>();
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.especie");
        Especie[] response = target.register(EspeciesWriter.class)
                .request(MediaType.APPLICATION_JSON)
                .get(Especie[].class);
        if (response.length > 0) {
            for (Especie e : response) {
                especies.put(e.getNombre(), e.getNombre().toLowerCase());
            }
        }
        bean.setEspecies(especies);
    }
}
