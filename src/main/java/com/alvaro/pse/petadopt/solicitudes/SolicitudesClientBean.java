/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.solicitudes;

import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.HistoricoSolicitudes;
import com.alvaro.pse.petadopt.entities.Mascota;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
import com.alvaro.pse.petadopt.external.ListaNegraAPI;
import com.alvaro.pse.petadopt.json.ClienteReader;
import com.alvaro.pse.petadopt.json.HistoricoSolicitudesReader;
import com.alvaro.pse.petadopt.json.HistoricoSolicitudesWriter;
import com.alvaro.pse.petadopt.json.MascotaReader;
import com.alvaro.pse.petadopt.json.MascotaWriter;
import com.alvaro.pse.petadopt.login.LoginPageBackingBean;
import com.alvaro.pse.petadopt.utils.ExcelUtils;
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
import org.primefaces.model.StreamedContent;

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
    ExcelUtils excelUtils;
    @Inject
    ListaNegraAPI listaNegraAPI;

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
        //System.out.println(bean.getVerMascotasFilter());
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
                    m.setFechaAdopcion(h.getFechaModificacion());
                    all.add(m);
                }

            }

        }
        return all;
    }

    public List<Mascota> findByClienteAndEstado() {
        //System.out.println("Find by cliente and estado");
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
                    m.setFechaAdopcion(h.getFechaModificacion());
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
        //System.out.println("Id mascota:" + id);
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
    
    public boolean puedeAdoptar(Long idCliente){
        boolean puedeAdoptar = false;
        // necesito recuperar el email del usuario que ha solicitado la adopción
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuario");
        Response response = target.path("{usuarioId}")
                .resolveTemplate("usuarioId", idCliente)
                .request()
                .get();
        if(response.getStatus() == 200){
            Usuario u = response.readEntity(Usuario.class);
            puedeAdoptar = listaNegraAPI.consultaListaNegra(u.getEmail());
        }
        return puedeAdoptar;
    }

    public boolean aceptarSolicitud(Long idSolicitud) {
        // Para aceptar una solicitud basta poner 'adoptada' en el campo 
        // de estado
        //System.out.println(idSolicitud);
        // recuperamos la mascota con su id
        boolean aceptada = false;
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
        Response response = target
                .path("{mascotaId}")
                .resolveTemplate("mascotaId", idSolicitud)
                .request()
                .get();

        if (response.getStatus() == 200) {
            // TODO: AQUI HAY QUE COMPROBAR LA LISTA NEGRA
            boolean puedeAdoptar = listaNegraAPI.consultaListaNegra("darthvader@uva.es");
            System.out.println(puedeAdoptar);
            if (puedeAdoptar == false) {
                aceptada = false;
            } else {
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
                //System.out.println(response);
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

                    //System.out.println(response);
                    if (response.getStatus() == 200) {
                        System.out.println("Mascota aceptada correctamente");
                    }
                }
            }
        }
        return aceptada;
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

    public String cancelarSolicitud() {
        String success = "failure";
        // cancelar una solicitud es poner en el estado de la mascota 'sin_solicitud'
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.mascota");
        // recupero la mascota seleccionada
        Response response = target
                .path("{mascotaId}")
                .resolveTemplate("mascotaId", bean.getIdMascotaSelected())
                .request()
                .get();

        if (response.getStatus() == 200) {

            Mascota m = response.readEntity(Mascota.class);
            m.setEstado("sin_solicitud");

            response = target.path("{mascotaId}")
                    .resolveTemplate("mascotaId", m.getId())
                    .request()
                    .put(Entity.entity(m, MediaType.APPLICATION_JSON));
            if (response.getStatus() == 204) {
                System.out.println("Solicitud cancelada correctamente");
                success = "success";
            }
        }
        return success;
    }

    public StreamedContent descargarCertificado(Long id) {
        StreamedContent pdf = null;
        Mascota m = this.findMascotaById(bean.getIdMascotaSelected());
        Refugio r = this.findRefugioById(m.getIdRefugio());
        Cliente c = this.findClienteById(m.getIdCliente());
        System.out.println(m);
        String nombreCliente = c.getNombre();
        String nombreRefugio = r.getNombre();
        String nombreMascota = m.getNombre();
        String fechaAdopcion = m.getFechaAdopcion();
        String coste = String.valueOf(m.getCosteAdopcion());
        String idOperacion = "ADP" + String.valueOf(m.getId()) + String.valueOf(r.getId()) + String.valueOf(c.getId());
        String direccionRefugio = r.getDomicilioSocial();
        String telefonoRefugio = r.getTelefono();
        String base64firma = m.getFirma();
        try {
            pdf = this.pdfUtils.generateCertificadoAdopcionPDF(nombreCliente, nombreMascota, nombreRefugio, coste, idOperacion, fechaAdopcion, direccionRefugio, telefonoRefugio, base64firma);
        } catch (IOException ex) {
            Logger.getLogger(SolicitudesClientBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pdf;
    }

    public StreamedContent descargarSolicitudes() {
        StreamedContent excel = null;
        // necesito obtener el histórico del refugio logeado
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.historicosolicitudes");
        HistoricoSolicitudes[] response = target.register(HistoricoSolicitudesWriter.class)
                .path("find-by-refugio/{idRefugio}")
                .resolveTemplate("idRefugio", loginBean.getUsuarioLogeado().getId())
                .request()
                .get(HistoricoSolicitudes[].class);

        if (response.length > 0) {
            try {
                // aqui llamamos al método que nos genera el excel
                excel = excelUtils.generateHistorialExcel(response);
            } catch (IOException ex) {
                Logger.getLogger(SolicitudesClientBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return excel;
    }

}
