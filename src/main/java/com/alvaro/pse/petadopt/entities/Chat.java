/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.entities;

/**
 *
 * @author alvar
 */
public class Chat {
    
    private Long idChat;
    private Long idCliente;
    private Long idRefugio;

    public Chat() {
    }
    

    public Chat(Long idChat, Long idCliente, Long idRefugio) {
        this.idChat = idChat;
        this.idCliente = idCliente;
        this.idRefugio = idRefugio;
    }
    
    

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdRefugio() {
        return idRefugio;
    }

    public void setIdRefugio(Long idRefugio) {
        this.idRefugio = idRefugio;
    }
    
    
}
