/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.solicitudes;

import com.alvaro.pse.petadopt.utils.FileDownloadView;
import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.HistoricoSolicitudes;
import com.alvaro.pse.petadopt.entities.Mascota;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.json.ClienteReader;
import com.alvaro.pse.petadopt.json.HistoricoSolicitudesReader;
import com.alvaro.pse.petadopt.json.HistoricoSolicitudesWriter;
import com.alvaro.pse.petadopt.json.MascotaReader;
import com.alvaro.pse.petadopt.json.MascotaWriter;
import com.alvaro.pse.petadopt.login.LoginPageBackingBean;
import com.alvaro.pse.petadopt.utils.PDFUtils;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

/**
 *
 * @author alvar
 */
@Named
@RequestScoped
public class SolicitudesClientBean {
    
    @Inject
    LoginPageBackingBean loginBean;
    @Inject
    SolicitudesBackingBean bean;
    @Inject
    PDFUtils pdfUtils;
    @Inject
    FileDownloadView fileDownload;
    
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
    
    private List<Mascota> findByRefugioSolicitudes() {
        List<Mascota> all = new ArrayList();
        //System.out.println(loginBean.getUsuarioLogeado().getId());
        Response response = target.register(MascotaWriter.class)
                .path("find-by-refugio-solicitudes/{idRefugio}")
                .resolveTemplate("idRefugio", loginBean.getUsuarioLogeado().getId())
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            all = response.readEntity(List.class);
        }
        return all;
    }
    
    public List<Mascota> findByRefugioAndEstado() {
        System.out.println(bean.getVerMascotasFilter());
        List<Mascota> all = new ArrayList<>();
        if (bean.getVerMascotasFilter().equalsIgnoreCase("pendiente")) {
            target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
            Response response = target.register(MascotaWriter.class)
                    .path("find-by-refugio-estado/{idRefugio}/{estado}")
                    .resolveTemplate("idRefugio", loginBean.getUsuarioLogeado().getId())
                    .resolveTemplate("estado", bean.getVerMascotasFilter())
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == 200) {
                all = response.readEntity(List.class);
            }
        } else {
            // si no son pendientes, son aceptadas o rechazadas
            // las busco en el histórico
            target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.historicosolicitudes");
            HistoricoSolicitudes[] response = target.register(HistoricoSolicitudesWriter.class)
                    .path("find-by-refugio-estado/{idRefugio}/{estado}")
                    .resolveTemplate("idRefugio", loginBean.getUsuarioLogeado().getId())
                    .resolveTemplate("estado", bean.getVerMascotasFilter())
                    .request()
                    .get(HistoricoSolicitudes[].class);
            
            if (response.length > 0) {
                for (HistoricoSolicitudes h : response) {
                    Mascota m = new Mascota();
                    m.setId(h.getIdMascota());
                    m.setIdCliente(h.getIdCliente());
                    m.setEstado(h.getEstado());
                    all.add(m);
                }
                
            }
            
        }
        return all;
    }
    
    public List<Mascota> findByClienteAndEstado() {
        System.out.println("Find by cliente and estado");
        List<Mascota> all = new ArrayList<>();
        if (bean.getVerMascotasFilter().equalsIgnoreCase("pendiente")) {
            target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
            Response response = target.register(MascotaWriter.class)
                    .path("find-by-cliente-estado/{idCliente}/{estado}")
                    .resolveTemplate("idCliente", loginBean.getUsuarioLogeado().getId())
                    .resolveTemplate("estado", bean.getVerMascotasFilter())
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (response.getStatus() == 200) {
                all = response.readEntity(List.class);
            }
        } else {
            // si no son pendientes, son aceptadas o rechazadas
            // las busco en el histórico
            target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.historicosolicitudes");
            HistoricoSolicitudes[] response = target.register(HistoricoSolicitudesWriter.class)
                    .path("find-by-cliente-estado/{idCliente}/{estado}")
                    .resolveTemplate("idCliente", loginBean.getUsuarioLogeado().getId())
                    .resolveTemplate("estado", bean.getVerMascotasFilter())
                    .request()
                    .get(HistoricoSolicitudes[].class);
            
            if (response.length > 0) {
                for (HistoricoSolicitudes h : response) {
                    Mascota m = new Mascota();
                    m.setId(h.getIdMascota());
                    m.setIdCliente(h.getIdCliente());
                    m.setEstado(h.getEstado());
                    all.add(m);
                }
                
            }
            
        }
        return all;
    }
    
    public Cliente findClienteById(Long id) {
        Cliente c = null;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.cliente");
        Response response = target.register(ClienteReader.class)
                .path("{clienteId}")
                .resolveTemplate("clienteId", id)
                .request()
                .get();
        if (response.getStatus() == 200) {
            c = response.readEntity(Cliente.class);
        }
        return c;
    }
    
    public Mascota findMascotaById(Long id) {
        System.out.println("Id mascota:" + id);
        Mascota m = null;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
        Response response = target
                .path("{mascotaId}")
                .resolveTemplate("mascotaId", id)
                .request()
                .get();
        if (response.getStatus() == 200) {
            m = response.readEntity(Mascota.class);
        }
        return m;
    }
    
    public Refugio findRefugioById(Long id) {
        Refugio r = null;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.refugio");
        Response response = target
                .path("{refugioId}")
                .resolveTemplate("refugioId", id)
                .request()
                .get();
        if (response.getStatus() == 200) {
            r = response.readEntity(Refugio.class);
        }
        return r;
    }
    
    public void aceptarSolicitud(Long idSolicitud) {
        // Para aceptar una solicitud basta poner 'adoptada' en el campo 
        // de estado
        //System.out.println(idSolicitud);
        // recuperamos la mascota con su id
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
        Response response = target
                .path("{mascotaId}")
                .resolveTemplate("mascotaId", idSolicitud)
                .request()
                .get();
        
        if (response.getStatus() == 200) {
            // TODO: AQUI HAY QUE COMPROBAR LA LISTA NEGRA
            bean.setMascotaSelected(response.readEntity(Mascota.class));
            Mascota m = new Mascota();
            m.setId(idSolicitud);
            m.setNombre(bean.getMascotaSelected().getNombre());
            m.setEspecie(bean.getMascotaSelected().getEspecie());
            m.setRaza(bean.getMascotaSelected().getRaza());
            m.setEdad(bean.getMascotaSelected().getEdad());
            m.setEstadoSalud(bean.getMascotaSelected().getEstadoSalud());
            m.setCosteAdopcion(bean.getMascotaSelected().getCosteAdopcion());
            m.setFoto(bean.getMascotaSelected().getFoto());
            m.setIdRefugio(bean.getMascotaSelected().getIdRefugio());
            m.setFechaPublicacion(bean.getMascotaSelected().getFechaPublicacion());
            m.setFechaSolicitud(bean.getMascotaSelected().getFechaSolicitud());
            m.setFirma(bean.getMascotaSelected().getFirma());

            // la fecha de adopcion es la actual
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fecha = hoy.format(formatter);
            m.setFechaAdopcion(fecha);

            // el id del cliente es el id del usuario registrado
            m.setIdCliente(bean.getMascotaSelected().getIdCliente());
            // el estado de la solicitud pasa a ser 'adoptada'
            m.setEstado("adoptada");
            //System.out.println(m);
            response = target.path("{mascotaId}")
                    .resolveTemplate("mascotaId", idSolicitud)
                    .request()
                    .put(Entity.entity(m, MediaType.APPLICATION_JSON));
            System.out.println(response);
            if (response.getStatus() == 204) {
                // ahora añadimos la solicitud aceptada al histórico de solicitudes
                target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.historicosolicitudes");
                HistoricoSolicitudes h = new HistoricoSolicitudes();
                h.setIdCliente(m.getIdCliente());
                h.setIdRefugio(m.getIdRefugio());
                h.setFechaModificacion(fecha);
                h.setIdMascota(m.getId());
                h.setEstado("adoptada");
                response = target.register(HistoricoSolicitudesWriter.class)
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(h, MediaType.APPLICATION_JSON));
                
                System.out.println(response);
                if (response.getStatus() == 200) {
                    System.out.println("Mascota aceptada correctamente");
                }
            }
            
        }
    }
    
    public String getAllSoliciutdesCount() {
        String count = "-1";
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.historicosolicitudes");
        Response response = target.path("count")
                .request()
                .get();
        if (response.getStatus() == 200) {
            count = response.readEntity(String.class);
        }
        return count;
    }
    
    public void rechazarSolicitud(long idSolicitud) {
        // Para aceptar una solicitud basta poner 'rechazada' en el campo 
        // de estado

        // recuperamos la mascota con su id
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
        Response response = target
                .path("{mascotaId}")
                .resolveTemplate("mascotaId", idSolicitud)
                .request()
                .get();
        
        if (response.getStatus() == 200) {
            
            bean.setMascotaSelected(response.readEntity(Mascota.class));
            Mascota m = new Mascota();
            m.setId(idSolicitud);
            m.setNombre(bean.getMascotaSelected().getNombre());
            m.setEspecie(bean.getMascotaSelected().getEspecie());
            m.setRaza(bean.getMascotaSelected().getRaza());
            m.setEdad(bean.getMascotaSelected().getEdad());
            m.setEstadoSalud(bean.getMascotaSelected().getEstadoSalud());
            m.setCosteAdopcion(bean.getMascotaSelected().getCosteAdopcion());
            m.setFoto(bean.getMascotaSelected().getFoto());
            m.setIdRefugio(bean.getMascotaSelected().getIdRefugio());
            m.setFechaPublicacion(bean.getMascotaSelected().getFechaPublicacion());
            
            m.setIdCliente(bean.getMascotaSelected().getIdCliente());
            // la mascota vuelve a estar disponible para la adopcion
            m.setEstado("sin_solicitud");
            m.setFechaSolicitud("");
            m.setFechaAdopcion("");
            //System.out.println(m);

            target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.historicosolicitudes");
            HistoricoSolicitudes h = new HistoricoSolicitudes();
            h.setIdCliente(m.getIdCliente());
            h.setIdRefugio(m.getIdRefugio());
            h.setIdMascota(m.getId());
            h.setEstado("rechazada");
            // la fecha de modificacion es la actual
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fecha = hoy.format(formatter);
            h.setFechaModificacion(fecha);

            // actualizo la tabla del histórico
            target.register(HistoricoSolicitudesWriter.class)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(h, MediaType.APPLICATION_JSON));

            // actualizo la mascota 
            m.setIdCliente(-1L);
            
            target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
            response = target.path("{mascotaId}")
                    .resolveTemplate("mascotaId", idSolicitud)
                    .request()
                    .put(Entity.entity(m, MediaType.APPLICATION_JSON));
            if (response.getStatus() == 204) {
                System.out.println("Rechazada correctamente");
            }
            
        }
    }
    
    public void descargarCertificado(Long id) {
        Mascota m = this.findMascotaById(id);
        Refugio r = this.findRefugioById(m.getIdRefugio());
        System.out.println(m);
        String nombreCliente = this.findClienteById(m.getIdCliente()).getNombre();
        String nombreRefugio = this.findRefugioById(m.getIdRefugio()).getNombre();
        String nombreMascota = m.getNombre();
        String fechaAdopcion = m.getFechaAdopcion();
        String coste = String.valueOf(m.getCosteAdopcion());
        String idOperacion = "ADP" + String.valueOf(m.getId()) + String.valueOf(r.getId()) + String.valueOf(this.findClienteById(m.getIdCliente()).getId());
        String direccionRefugio = r.getDomicilioSocial();
        String telefonoRefugio = r.getTelefono();
        String base64firma = m.getFirma();
        System.out.println(base64firma);
        try {
            String ruta = this.pdfUtils.generateCertificadoAdopcionPDF(nombreCliente, nombreMascota, nombreRefugio, coste, idOperacion, fechaAdopcion, direccionRefugio, telefonoRefugio, base64firma);
            this.fileDownload.download(ruta);
            bean.setRutaCertificado("C:/Users/alvar/OneDrive/Documentos/NetBeansProjects/petAdoptv2/src/main/java/com/alvaro/pse/petadopt/utils/certificado_" + idOperacion+ ".pdf");
        } catch (IOException ex) {
            Logger.getLogger(SolicitudesClientBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
