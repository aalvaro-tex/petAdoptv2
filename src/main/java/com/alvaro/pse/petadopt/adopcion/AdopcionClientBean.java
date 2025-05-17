/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.adopcion;

import com.alvaro.pse.petadopt.entities.Mascota;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.json.MascotaWriter;
import com.alvaro.pse.petadopt.json.RefugioReader;
import com.alvaro.pse.petadopt.login.LoginPageBackingBean;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
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
public class AdopcionClientBean{

    @Inject
    Adopcion bean;
    @Inject
    LoginPageBackingBean loginBean;

    private Client client;
    private WebTarget target;

    /**
     *
     */
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
    }

    /**
     *
     */
    @PreDestroy
    public void destroy() {
        client.close();
    }

    private List<Mascota> findAllDisponibles() {
        List<Mascota> all = null;
        Response response = target.register(MascotaWriter.class)
                .path("find-by-estado/{estado}")
                .resolveTemplate("estado", "sin_solicitud")
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            all = response.readEntity(List.class);
        }
        return all;
    }

    /**
     *
     * @return
     */
    public List<Mascota> findByEspecie() {
        List<Mascota> all = null;
        if (bean.getMascotaFilter().equalsIgnoreCase("todas")) {
            all = this.findAllDisponibles();
        } else {
            Response response = target.register(MascotaWriter.class)
                    .path("find-by-especie-and-disponible/{especie}")
                    .resolveTemplate("especie", bean.getMascotaFilter())
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == 200) {
                all = response.readEntity(List.class);
            }
        }
        return all;
    }

    /**
     *
     * @return
     */
    public String getMascotaById() {
        String success = "failure";
        Mascota found;
        Response response = target.path("{mascotaId}")
                .resolveTemplate("mascotaId", bean.getIdMascotaSelected())
                .request()
                .get();
        if (response.getStatus() == 200) {
            found = response.readEntity(Mascota.class);
            bean.setMascotaSelected(found);
            success = "success";
        }

        return success;
    }

    /**
     *
     * @return
     */
    public String confirmarAdopcion() {
        String success = "failure";
        Mascota m = new Mascota();
        m.setId(bean.getIdMascotaSelected());
        m.setNombre(bean.getMascotaSelected().getNombre());
        m.setEspecie(bean.getMascotaSelected().getEspecie());
        m.setRaza(bean.getMascotaSelected().getRaza());
        m.setEdad(bean.getMascotaSelected().getEdad());
        m.setEstadoSalud(bean.getMascotaSelected().getEstadoSalud());
        m.setCosteAdopcion(bean.getMascotaSelected().getCosteAdopcion());
        m.setFoto(bean.getMascotaSelected().getFoto());
        m.setIdRefugio(bean.getMascotaSelected().getIdRefugio());
        m.setFechaPublicacion(bean.getMascotaSelected().getFechaPublicacion());
        m.setFechaAdopcion(bean.getMascotaSelected().getFechaAdopcion());
        m.setFirma(bean.getBase64firma().substring(22));

        // la fecha de solicitud es la actual
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fecha = hoy.format(formatter);
        m.setFechaSolicitud(fecha);

        // el id del cliente es el id del usuario registrado
        m.setIdCliente(loginBean.getUsuarioLogeado().getId());
        // el estado de la solicitud pasa a ser 'pendiente'
        m.setEstado("pendiente");
        //System.out.println("Mascota: " +m.getId());
        Response response = target.path("{mascotaId}")
                .resolveTemplate("mascotaId", bean.getIdMascotaSelected())
                .request()
                .put(Entity.entity(m, MediaType.APPLICATION_JSON));
        //System.out.println("respuesta: "+response);
        if (response.getStatus() == 204) {
            success = "success";
        }
        this.getRefugioById();
        bean.setBase64firma("");
        bean.setValueFirma("");
        return success;
    }
    
     private void getRefugioById(){
        String success = "failure";
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.refugio");
        Response response = target.register(RefugioReader.class)
                .path("{refugioId}")
                .resolveTemplate("refugioId", bean.getMascotaSelected().getIdRefugio())
                .request()
                .get();
        //System.out.println("Refugio: "+response);
        if(response.getStatus() == 200){
            Refugio r = response.readEntity(Refugio.class);
            bean.setRefugio(r);
            success = "success";
        }
        //return success;
    }

}
