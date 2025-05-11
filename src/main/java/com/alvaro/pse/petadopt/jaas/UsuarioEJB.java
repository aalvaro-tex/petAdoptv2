/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.jaas;

import com.alvaro.pse.petadopt.entities.Chat;
import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
import com.alvaro.pse.petadopt.entities.UsuarioGrupo;
import com.alvaro.pse.petadopt.utils.AutenticacionUtils;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author alvar
 */
@Stateless
public class UsuarioEJB {

    @PersistenceContext
    private EntityManager em;

    public Long createUser(Usuario user) throws Exception  {
        Long idUsuarioCreado = -1L;
        try {
            user.setPassword(AutenticacionUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        UsuarioGrupo group = new UsuarioGrupo();
        group.setEmail(user.getEmail());
        group.setNombreRol("usuario");
        try {
            if(em.createNamedQuery("Usuario.findByEmail", Usuario.class)
                    .setParameter("email", user.getEmail())
                    .getResultList().size() < 1)
            {
            em.persist(user);
            System.out.println(user.getEmail());
            // necesitamos el id del usuario creado para añadirlo al cliente/refugio
            try{
            Usuario u = em.createNamedQuery("Usuario.findByEmail", Usuario.class)
                    .setParameter("email", user.getEmail())
                    .getSingleResult();
            idUsuarioCreado = u.getId();
            } catch(Exception e){
                // el email ya está registrado
                throw new Exception();
            }
            }
            else{
                throw new Exception();
            }
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(err -> System.out.println(err.toString()));
        }
        try {
            em.persist(group);
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(err -> System.out.println(err.toString()));
        }
        return idUsuarioCreado;
    }

    public String createRefugio(Refugio refugio) {
        String success = "failure";
        try {
            em.persist(refugio);
            success = "success";
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(err -> System.out.println(err.toString()));
        }
        System.out.println("Creamos un refugio");
        return success;
    }

    public String createCliente(Cliente cliente) {
        String success = "failure";
        try {
            em.persist(cliente);
            success = "success";
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(err -> System.out.println(err.toString()));
        }
        System.out.println("Creamos un cliente");
        return success;
    }

    public Usuario findByEmail(String email) throws Exception {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByEmail",
                Usuario.class);
        query.setParameter("email", email);
        Usuario user = null;
        try{
        user = query.getSingleResult();
        } catch(Exception e){
            throw new Exception();
        }
        return user;
    }

}
