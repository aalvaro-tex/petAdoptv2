/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.mascotas;

import com.alvaro.pse.petadopt.entities.Mascota;
import com.alvaro.pse.petadopt.json.ClienteWriter;
import com.alvaro.pse.petadopt.json.MascotaWritter;
import com.alvaro.pse.petadopt.login.LoginPageBackingBean;
import com.alvaro.pse.petadopt.utils.ImageUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author alvar
 */
@Named
@RequestScoped
public class MascotasPageClientBean {

    @Inject
    MascotasPageBackingBean bean;
    @Inject
    LoginPageBackingBean loginBean;
    @Inject
    ImageUtils imageUtils;

    Client client;
    WebTarget target;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public String addMascota() {
        String success = "failure";
        Mascota m = new Mascota();
        m.setCosteAdopcion(bean.getCosteAdopcion());
        m.setEdad(bean.getEdad());
        m.setEspecie(bean.getEspecie());
        m.setEstadoSalud(bean.getEstadoSalud());
        m.setRaza(bean.getRaza());
        m.setNombre(bean.getNombre());
        m.setFoto(this.imageUtils.upload(bean.getMascotaImg()));
        // al crear una nueva mascota
        // el estado de la solicitud es pendiente
        m.setEstado("sin_solicitud");

        // el resto de campos los creamos como cadena vacia/-1
        m.setFechaAdopcion("");
        m.setIdCliente(-1);

        // necesitamos añadir el id del rufugio que crea la mascota
        m.setIdRefugio(loginBean.getUsuarioLogeado().getId());
        // la fecha de publicación será la actual
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fecha = hoy.format(formatter);
        m.setFechaPublicacion(fecha);

        // añadimos la mascota a la bbdd
        target.register(MascotaWritter.class)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(m, MediaType.APPLICATION_JSON));

        return success;
    }

    private List<Mascota> findByRefugio() {
        List<Mascota> all = null;
        Response response = target.register(ClienteWriter.class)
                .path("find-by-refugio/{idRefugio}")
                .resolveTemplate("idRefugio", loginBean.getUsuarioLogeado().getId())
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            all = response.readEntity(List.class);
        }
        return all;
    }

    public List<Mascota> findByRefugioAndEstado() {
        List<Mascota> all = null;
        if (bean.getVerMascotasFilter().equalsIgnoreCase("todas")) {
            all = this.findByRefugio();
        } else {
            Response response = target.register(ClienteWriter.class)
                    .path("find-by-refugio-estado/{idRefugio}/{estado}")
                    .resolveTemplate("idRefugio", loginBean.getUsuarioLogeado().getId())
                    .resolveTemplate("estado", bean.getVerMascotasFilter())
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == 200) {
                all = response.readEntity(List.class);
            }

        }
        return all;
    }

    public String deleteMascota() {
        target.path("{mascotaId}")
                .resolveTemplate("mascotaId", bean.getIdMascotaSelected())
                .request()
                .delete();
        return "success";
    }
    
    public String updateMascota(){
       return "success";
    }

}
