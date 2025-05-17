/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.rest;

import com.alvaro.pse.petadopt.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
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
@Named
@Stateless
@Path("com.alvaro.pse.petadoptv2.entities.usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.alvaro.pse_petAdoptv2_war_1.0PU")
    private EntityManager em;

    /**
     *
     */
    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    /**
     *
     * @param entity
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public void create(Usuario entity) {
        super.create(entity);
    }

    /**
     *
     * @param id
     * @param entity
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Usuario entity) {
        super.edit(entity);
    }

    /**
     *
     * @param id
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario find(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    /**
     *
     * @param email
     * @return
     */
    @GET
    @Path("find-by-email/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario findByEmail(@PathParam("email") String email){
        Usuario found = null;
        List<Usuario> all = this.findAll();
        for(Usuario u : all){
            if(u.getEmail().equalsIgnoreCase(email)){
                found = u;
            }
        }
        return found;
    }

    /**
     *
     * @return
     */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        List<Usuario> found = new ArrayList<>();
        // hay que eliminar los usuarios desactivados
        List<Usuario> all = super.findAll();
        for(Usuario u : all){
            if(u.getActivo() == true){
                found.add(u);
            }
        }
        return found;
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     *
     * @return
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    /**
     *
     * @param entity
     * @return
     */
    @POST
    @Path("find-by-login")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario findByLogin(Usuario entity){
        List<Usuario> all = this.findAll();
        Usuario found = null;
        for(Usuario u : all){
            if(u.getEmail().equalsIgnoreCase(entity.getEmail()) && u.getPassword().equalsIgnoreCase(entity.getPassword())){
                found = u;
            }
        }
        return found;
    }
    
    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
