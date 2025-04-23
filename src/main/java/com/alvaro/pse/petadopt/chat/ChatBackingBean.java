/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.chat;

import com.alvaro.pse.petadopt.entities.Chat;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    private List<Chat> chats = new ArrayList<>();
    
    public ChatBackingBean(){
        
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }
    
    
}
