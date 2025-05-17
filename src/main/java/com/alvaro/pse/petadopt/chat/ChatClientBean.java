/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.chat;

import com.alvaro.pse.petadopt.entities.Chat;
import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
import com.alvaro.pse.petadopt.json.ChatWriter;
import com.alvaro.pse.petadopt.json.ClienteReader;
import com.alvaro.pse.petadopt.json.UsuarioReader;
import com.alvaro.pse.petadopt.login.LoginPageBackingBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author alvar
 */
@Named
@RequestScoped
public class ChatClientBean implements Serializable {

    @Inject
    LoginPageBackingBean loginBean;
    
    @Inject
    ChatBackingBean bean;

    private Client client;
    private WebTarget target;

    /**
     *
     */
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();

    }

    /**
     *
     */
    @PreDestroy
    public void destroy() {
        client.close();
    }

    /**
     *
     * @param idConversacion
     * @return
     */
    public Usuario getUsuarioById(String idConversacion) {
        Long id = -1L;
        if (loginBean.getRol().equalsIgnoreCase("cliente")) {
            id = Long.parseLong(idConversacion.split(":")[0]);
        } else {
            id = Long.parseLong(idConversacion.split(":")[1]);
        }
        Usuario found = null;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuario");
        Response response = target.register(UsuarioReader.class)
                .path("{id}")
                .resolveTemplate("id", id)
                .request()
                .get();

        if (response.getStatus() == 200) {
            found = response.readEntity(Usuario.class);
        }
        return found;

    }

    /**
     *
     * @param idConversacion
     * @return
     */
    public Cliente getClienteById(String idConversacion) {
        Long id = -1L;
        if (loginBean.getRol().equalsIgnoreCase("cliente")) {
            id = Long.parseLong(idConversacion.split(":")[0]);
        } else {
            id = Long.parseLong(idConversacion.split(":")[1]);
        }
        Cliente found = null;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.cliente");
        Response response = target.register(ClienteReader.class)
                .path("{id}")
                .resolveTemplate("id", id)
                .request()
                .get();

        if (response.getStatus() == 200) {
            found = response.readEntity(Cliente.class);
        }
        return found;
    }

    /**
     *
     * @param idConversacion
     * @return
     */
    public Refugio getRefugioById(String idConversacion) {
        Long id = -1L;
        if (loginBean.getRol().equalsIgnoreCase("refugio")) {
            id = Long.parseLong(idConversacion.split(":")[1]);
        } else {
            id = Long.parseLong(idConversacion.split(":")[0]);
        }
        //System.out.println(id);
        Refugio found = null;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.refugio");
        Response response = target
                .path("{id}")
                .resolveTemplate("id", id)
                .request()
                .get();

        if (response.getStatus() == 200) {
            found = response.readEntity(Refugio.class);
            //System.out.println(found);
        }
        return found;
    }

    /**
     *
     * @param idConversacion
     * @return
     */
    public List<Chat> getLatestMessageByUser(String idConversacion) {
        List<Chat> chats = null;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.chat");
        Response response = target.register(ChatWriter.class)
                .path("last-message/{idConversacion}")
                .resolveTemplate("idConversacion", idConversacion)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            chats = response.readEntity(List.class);
            Collections.reverse(chats);
        }
        return chats;
    }

    /**
     *
     * @param idConversacion
     * @return
     */
    public List<Chat> getChatsByUser(String idConversacion) {
        List<Chat> chats = new ArrayList<>();
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.chat");
        Response response = target.register(ChatWriter.class)
                .path("my-chats/{idUsuario}")
                .resolveTemplate("idUsuario", loginBean.getUsuarioLogeado().getId())
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            chats = response.readEntity(List.class);
            
        }
        return chats;
        
    }
    
}
