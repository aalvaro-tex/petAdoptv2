/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.rest;

import com.alvaro.pse.petadopt.entities.HistoricoSolicitudes;
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
@Path("com.alvaro.pse.petadoptv2.entities.historicosolicitudes")
public class HistoricoSolicitudesFacadeREST extends AbstractFacade<HistoricoSolicitudes> {

    @PersistenceContext(unitName = "com.alvaro.pse_petAdoptv2_war_1.0PU")
    private EntityManager em;

    public HistoricoSolicitudesFacadeREST() {
        super(HistoricoSolicitudes.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(HistoricoSolicitudes entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, HistoricoSolicitudes entity) {
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
    public HistoricoSolicitudes find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HistoricoSolicitudes> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HistoricoSolicitudes> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("find-by-refugio-estado/{idRefugio}/{estado}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HistoricoSolicitudes> findByRefugioAndEstado(@PathParam("idRefugio") Long idRefugio, @PathParam("estado") String estado){
        List<HistoricoSolicitudes> all = this.findAll();
        List<HistoricoSolicitudes> found = new ArrayList<>();
        for(HistoricoSolicitudes h : all){
            if(h.getIdRefugio() == idRefugio && h.getEstado().equalsIgnoreCase(estado)){
                found.add(h);
            }
        }
        //System.out.println("foud: "+found.size());
        return found;
    }
    
    @GET
    @Path("find-by-cliente-estado/{idCliente}/{estado}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HistoricoSolicitudes> findByClienteAndEstado(@PathParam("idCliente") Long idCliente, @PathParam("estado") String estado){
        List<HistoricoSolicitudes> all = this.findAll();
        List<HistoricoSolicitudes> found = new ArrayList<>();
        for(HistoricoSolicitudes h : all){
            if(h.getIdCliente() == idCliente && h.getEstado().equalsIgnoreCase(estado)){
                found.add(h);
            }
        }
        //System.out.println("foud: "+found.size());
        return found;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
