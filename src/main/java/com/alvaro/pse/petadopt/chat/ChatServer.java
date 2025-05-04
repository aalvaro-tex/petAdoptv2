/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.chat;

import com.alvaro.pse.petadopt.entities.Chat;
import com.alvaro.pse.petadopt.json.ChatWriter;
import com.alvaro.pse.petadopt.login.LoginPageBackingBean;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
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
@ServerEndpoint("/websocket/{room}")
public class ChatServer {

    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @Inject
    LoginPageBackingBean loginBean;

    private Client client;
    private WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();

    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    @OnOpen
    public void onOpen(Session peer, @PathParam("room") String room) {
        System.out.println(room);
        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }

    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException {
        for (Session peer : peers) {
            peer.getBasicRemote().sendText(message);
            String datos = message.split("-")[0];
            String idConversacion = datos.split("@")[1];
            Long idEmisor = Long.parseLong(datos.split("@")[0]);
            Long idReceptor = -1L;
            // el receptor lo obtenemos como la parte del id de la conversacion que no es el id del emisor
            if(idEmisor == Long.parseLong(idConversacion.split(":")[0])){
                idReceptor  = Long.parseLong(idConversacion.split(":")[1]);
            } else {
                idReceptor  = Long.parseLong(idConversacion.split(":")[0]);
            }
            String mensaje = message.split("-")[1];
            this.saveMessage(idEmisor, idReceptor, idConversacion, mensaje);
        }
    }

    private void saveMessage(Long idEmisor, Long idReceptor, String idConversacion, String message) {
        // primero determinamos quién es el emisor y quién el receptor
        // si el usuario logueado es un refugio, su id es el número después de los dos puntos
        // fecha y hora actuales
        LocalDateTime hoy = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String timestamp = hoy.format(formatter);

        // construimos el objeto chat para guardarlo en la BD
        Chat c = new Chat();
        c.setIdConversacion(idConversacion);
        c.setIdEmisor(idEmisor);
        c.setIdReceptor(idReceptor);
        c.setMensaje(message);
        c.setTimestamp(timestamp);

        // lo guardamos en la BD
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.chat");
        Response response = target.register(ChatWriter.class)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(c, MediaType.APPLICATION_JSON));
        System.out.println("Añadir mensaje: "+response);

    }

}
