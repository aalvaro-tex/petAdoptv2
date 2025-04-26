/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.jaas;

import com.alvaro.pse.petadopt.entities.Usuario;
import com.alvaro.pse.petadopt.entities.UsuarioRol;
import com.alvaro.pse.petadopt.utils.AutenticacionUtils;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author alvar
 */
@Stateless
public class UsuarioEJB {

    @PersistenceContext
    private EntityManager em;

    public Usuario createUser(Usuario user) {
        try {
            user.setPassword(AutenticacionUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        UsuarioRol group = new UsuarioRol();
        group.setIdUsuario(user.getId());
        group.setNombreRol("usuario");
        em.persist(user);
        em.persist(group);
        return user;
    }

    public Usuario findByEmail(String email) {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByEmail",
                Usuario.class);
        query.setParameter("email", email);
        Usuario user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
        }
        return user;
    }

}
