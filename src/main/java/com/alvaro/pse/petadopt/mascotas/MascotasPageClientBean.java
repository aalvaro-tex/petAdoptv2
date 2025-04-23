/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.mascotas;

import com.alvaro.pse.petadopt.entities.Mascota;
import com.alvaro.pse.petadopt.json.ClienteWriter;
import com.alvaro.pse.petadopt.json.MascotaReader;
import com.alvaro.pse.petadopt.json.MascotaWriter;
import com.alvaro.pse.petadopt.login.LoginPageBackingBean;
import com.alvaro.pse.petadopt.utils.ImageUtils;
import com.alvaro.pse.petadopt.utils.StringUtils;
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
    @Inject
            StringUtils stringUtils;

    private Client client;
    private WebTarget target;

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
        m.setRaza(bean.getRaza() != null ? bean.getRaza() : "");
        m.setNombre(bean.getNombre());
        m.setFoto(this.imageUtils.upload(bean.getMascotaImg()));
        // al crear una nueva mascota
        // el estado de la solicitud es pendiente
        m.setEstado("sin_solicitud");

        // el resto de campos los creamos como cadena vacia/-1
        m.setFechaAdopcion("");
        m.setIdCliente(-1);
        m.setFechaSolicitud("");

        // necesitamos añadir el id del rufugio que crea la mascota
        m.setIdRefugio(loginBean.getUsuarioLogeado().getId());
        // la fecha de publicación será la actual
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fecha = hoy.format(formatter);
        m.setFechaPublicacion(fecha);

        // añadimos la mascota a la bbdd
        target.register(MascotaWriter.class)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(m, MediaType.APPLICATION_JSON));

        // limpiamos los inputs del formulario
        bean.cleanInputs();
        return success;
    }

    private List<Mascota> findByRefugio() {
        List<Mascota> all = null;
        System.out.println(loginBean.getUsuarioLogeado().getId());
        Response response = target.register(MascotaWriter.class)
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
            Response response = target.register(MascotaWriter.class)
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
    
    public String getMascotaById(){
        String success = "failure";
        Mascota found;
        Response response = target.path("{mascotaId}")
                .resolveTemplate("mascotaId", bean.getIdMascotaSelected())
                .request()
                .get();
        if(response.getStatus() == 200){
            found = response.readEntity(Mascota.class);
            bean.setMascotaSelected(found);
            success = "success";
        }
        
        return success;
    }
    
    public String updateMascota(){
        String success = "failure";
        Mascota m = new Mascota();
        m.setId(bean.getIdMascotaSelected());
        m.setCosteAdopcion(bean.getCosteAdopcionMascotaSeleccionada().isEmpty() ? bean.getMascotaSelected().getCosteAdopcion() : this.stringUtils.stringToDouble(bean.getCosteAdopcionMascotaSeleccionada()));
        m.setEdad(bean.getEdadMascotaSeleccionada().isEmpty() ? bean.getMascotaSelected().getEdad() : this.stringUtils.stringToInt(bean.getEdadMascotaSeleccionada()));
        m.setEspecie(bean.getEspecieMascotaSeleccionada().isEmpty() ? bean.getMascotaSelected().getEspecie()  : bean.getEspecieMascotaSeleccionada() );
        m.setEstadoSalud(bean.getEstadoSaludMascotaSeleccionada().isEmpty() ? bean.getMascotaSelected().getEstadoSalud() : bean.getEstadoSaludMascotaSeleccionada());
        m.setRaza(bean.getRazaMascotaSeleccionada().isEmpty() ? bean.getMascotaSelected().getRaza() : bean.getRazaMascotaSeleccionada());
        m.setNombre(bean.getNombreMascotaSeleccionada().isEmpty() ? bean.getMascotaSelected().getNombre() : bean.getNombreMascotaSeleccionada());
        m.setFoto(bean.getMascotaUpdateImg() == null ? bean.getMascotaSelected().getFoto() : this.imageUtils.upload(bean.getMascotaUpdateImg()));
        m.setEstado(bean.getMascotaSelected().getEstado());
        m.setFechaAdopcion(bean.getMascotaSelected().getFechaAdopcion());
        m.setIdCliente(bean.getMascotaSelected().getIdCliente());
        m.setFechaPublicacion(bean.getMascotaSelected().getFechaPublicacion());
        m.setIdRefugio(bean.getMascotaSelected().getIdRefugio());
        m.setFechaSolicitud(bean.getMascotaSelected().getFechaSolicitud());
        Response response = target.path("{mascotaId}")
                .resolveTemplate("mascotaId", bean.getIdMascotaSelected())
                .request()
                .put(Entity.entity(m, MediaType.APPLICATION_JSON));
        System.out.println(response);
        if(response.getStatus() == 204){
            success = "success";
        }
       return success;
    }

}
