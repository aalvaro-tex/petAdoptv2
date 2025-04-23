/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.chat;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author alvar
 */
@Named
@ManagedBean
@SessionScoped
public class ChatBean implements Serializable {

    private int roomId;

    public int getRoomId() {

        return roomId;

    }

    public void setRoomId(int roomId) {

        this.roomId = roomId;

    }
}
