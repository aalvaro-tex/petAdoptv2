/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.rest;

import com.alvaro.pse.petadopt.entities.Chat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author alvar
 */
@Stateless
@Path("com.alvaro.pse.petadoptv2.entities.chat")
public class ChatFacadeREST extends AbstractFacade<Chat> {

    @PersistenceContext(unitName = "com.alvaro.pse_petAdoptv2_war_1.0PU")
    private EntityManager em;

    public ChatFacadeREST() {
        super(Chat.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Chat entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Chat entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Chat find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Chat> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Chat> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    // Busca los 4 últimos mensajes enviados en una conversación concreta
    @GET
    @Path("last-message/{idConversacion}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Chat> getLastMessageByChatAndUser(@PathParam("idConversacion") String idConversacion) {
        List<Chat> chats = null;
        try {
            chats = em.createNamedQuery("Chat.findLatestByChat", Chat.class)
                    .setParameter("idConversacion", idConversacion)
                    .setMaxResults(4)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return chats;
    }

    // Busca todos los chats en los que participa un usuario
    @GET
    @Path("my-chats/{idUsuario}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Chat> getChatsByUser(@PathParam("idUsuario") Long idUsuario) {
        List<Chat> chats = null;
        List<Chat> uniqueChats = new ArrayList<>();
        try {
            chats = em.createNamedQuery("Chat.findChatsByUser", Chat.class)
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
            if (chats.size() > 0) {
                // necesito eliminar repetidos en base al idConversacion
                uniqueChats.add(chats.get(0));
                for (Chat c : chats) {
                    for (Chat uc : uniqueChats) {
                        if (!c.getIdConversacion().equalsIgnoreCase(uc.getIdConversacion())) {
                            //System.out.println(c.getIdConversacion());
                            uniqueChats.add(c);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return uniqueChats;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
