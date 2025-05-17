/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.chat;

import com.alvaro.pse.petadopt.entities.Chat;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author alvar
 */
@Named
@SessionScoped
public class ChatBackingBean implements Serializable{
    
   private String idConversacion;
   private List<Chat> chats;

    /**
     *
     * @return
     */
    public String getIdConversacion() {
        return idConversacion;
    }

    /**
     *
     * @param idConversacion
     */
    public void setIdConversacion(String idConversacion) {
        this.idConversacion = idConversacion;
    }

    /**
     *
     * @return
     */
    public List<Chat> getChats() {
        return chats;
    }

    /**
     *
     * @param chats
     */
    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }
    
    
     
}
