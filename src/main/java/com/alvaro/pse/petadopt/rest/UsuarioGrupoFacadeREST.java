/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.rest;

import com.alvaro.pse.petadopt.entities.UsuarioGrupo;
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
@Path("com.alvaro.pse.petadoptv2.entities.usuariogrupo")
public class UsuarioGrupoFacadeREST extends AbstractFacade<UsuarioGrupo> {

    @PersistenceContext(unitName = "com.alvaro.pse_petAdoptv2_war_1.0PU")
    private EntityManager em;

    public UsuarioGrupoFacadeREST() {
        super(UsuarioGrupo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(UsuarioGrupo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, UsuarioGrupo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UsuarioGrupo find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UsuarioGrupo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UsuarioGrupo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("is-user-admin/{email}")
    public boolean isUserAdmin(@PathParam("email") String email) {
        boolean isAdmin = false;
        List<UsuarioGrupo> all = this.findAll();
        for (UsuarioGrupo ug : all) {
            if (ug.getEmail().equalsIgnoreCase(email) && ug.getNombreRol().equalsIgnoreCase("admin")) {
                isAdmin = true;
            }
        }
        return isAdmin;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
