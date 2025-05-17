/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.rest;

import com.alvaro.pse.petadopt.entities.Mascota;
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
@Path("com.alvaro.pse.petadoptv2.entities.mascota")
public class MascotaFacadeREST extends AbstractFacade<Mascota> {

    @PersistenceContext(unitName = "com.alvaro.pse_petAdoptv2_war_1.0PU")
    private EntityManager em;

    /**
     *
     */
    public MascotaFacadeREST() {
        super(Mascota.class);
    }

    /**
     *
     * @param entity
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Mascota entity) {
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
    public void edit(@PathParam("id") Long id, Mascota entity) {
        //System.out.println("Id: " + entity.getId());
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
    public Mascota find(@PathParam("id") Long id) {
        return super.find(id);
    }

    /**
     *
     * @return
     */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mascota> findAll() {
        return super.findAll();
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
    public List<Mascota> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    /* Obtiene las mascotas de un determinado refugio */

    /**
     *
     * @param idRefugio
     * @return
     */

    @GET
    @Path("find-by-refugio/{idRefugio}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mascota> findByRefugio(@PathParam("idRefugio") Long idRefugio) {
        List<Mascota> all = this.findAll();
        List<Mascota> found = new ArrayList<>();
        for (Mascota m : all) {
            if (m.getIdRefugio() == idRefugio) {
                found.add(m);
            }
        }
        return found;
    }
    
    /* Obtiene las mascotas de un determinado refugio */

    /**
     *
     * @param idCliente
     * @return
     */

    @GET
    @Path("find-by-cliente/{idRefugio}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mascota> findByCliente(@PathParam("idCliente") Long idCliente) {
        List<Mascota> all = this.findAll();
        List<Mascota> found = new ArrayList<>();
        for (Mascota m : all) {
            if (m.getIdCliente() == idCliente) {
                found.add(m);
            }
        }
        return found;
    }

    /* Obtiene las mascotas de un determinado refugio */

    /**
     *
     * @param estado
     * @return
     */

    @GET
    @Path("find-by-estado/{estado}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mascota> findByEstado(@PathParam("estado") String estado) {
        List<Mascota> all = this.findAll();
        List<Mascota> found = new ArrayList<>();
        for (Mascota m : all) {
            if (m.getEstado().equalsIgnoreCase(estado)) {
                found.add(m);
            }
        }
        return found;
    }

    /* Obtiene las mascotas de un determinado refugio y un determinado estado */

    /**
     *
     * @param idRefugio
     * @param estado
     * @return
     */

    @GET
    @Path("find-by-refugio-estado/{idRefugio}/{estado}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mascota> findByRefugioAndEstado(@PathParam("idRefugio") Long idRefugio, @PathParam("estado") String estado) {
        List<Mascota> all = this.findByRefugio(idRefugio);
        List<Mascota> found = new ArrayList<>();
        for (Mascota m : all) {
            if (m.getEstado().equalsIgnoreCase(estado)) {
                found.add(m);
            }
        }
        return found;
    }
    
    /* Obtiene las mascotas de un determinado cliente y un determinado estado */

    /**
     *
     * @param idCliente
     * @param estado
     * @return
     */

    @GET
    @Path("find-by-cliente-estado/{idCliente}/{estado}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mascota> findByClienteAndEstado(@PathParam("idCliente") Long idCliente, @PathParam("estado") String estado) {
        List<Mascota> all = this.findByCliente(idCliente);
        List<Mascota> found = new ArrayList<>();
        for (Mascota m : all) {
            if (m.getEstado().equalsIgnoreCase(estado)) {
                found.add(m);
            }
        }
        return found;
    }

    /* Obtiene todas las solicitudes de un refugio */
    // Evita obtener las mascotas que no tienen solicitud 

    /**
     *
     * @param idRefugio
     * @return
     */
    @GET
    @Path("find-by-refugio-solicitudes/{idRefugio}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mascota> findByRefugioSolicitudes(@PathParam("idRefugio") Long idRefugio) {
        List<Mascota> all = this.findByRefugio(idRefugio);
        List<Mascota> found = new ArrayList<>();
        for (Mascota m : all) {
            if (!m.getEstado().equalsIgnoreCase("sin_solicitud")) {
                found.add(m);
            }
        }
        return found;
    }

    /* Obtiene las mascotas de un determinado refugio y un determinado estado */

    /**
     *
     * @param especie
     * @return
     */

    @GET
    @Path("find-by-especie-and-disponible/{especie}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mascota> findByEspecie(@PathParam("especie") String especie) {
        List<Mascota> all = this.findAll();
        List<Mascota> found = new ArrayList<>();
        for (Mascota m : all) {
            if (m.getEspecie().equalsIgnoreCase(especie) && m.getEstado().equalsIgnoreCase("sin_solicitud")) {
                found.add(m);
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
