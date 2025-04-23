/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.registro;

import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
import com.alvaro.pse.petadopt.json.ClienteWriter;
import com.alvaro.pse.petadopt.json.UsuarioWriter;
import com.alvaro.pse.petadopt.json.RefugioWriter;
import com.alvaro.pse.petadopt.login.LoginPageBackingBean;
import com.alvaro.pse.petadopt.utils.ImageUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class SignUpPageClientBean {

    Client client;
    WebTarget target;

    @Inject
    SignUpPageBackingBean bean;
    @Inject
    LoginPageBackingBean loginBean;
    @PersistenceContext(unitName = "com.alvaro.pse_petAdoptv2_war_1.0PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    @Inject
    ImageUtils imageUtils;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuario");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public String addUsuario() {
        // comprobamos que las dos contraseñas sean iguales
        Long id = -1L;
        String success = "failure";
        String p1 = bean.getPassword1();
        String p2 = bean.getPassword2();
        if (!p1.equalsIgnoreCase(p2)) {
            bean.showError();
        } else {
            Usuario u = new Usuario();
            u.setEmail(bean.getEmail());
            u.setPassword(bean.getPassword1()); // da igual, las dos coinciden
            u.setFoto(this.imageUtils.upload(bean.getFotoPerfil()));
            target.register(UsuarioWriter.class)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(u, MediaType.APPLICATION_JSON));
            // recuperamos el id del usuario que acabamos de crear
            // para asignarselo al id del refugio/cliente que corresponda
            Response response = target
                    .path("find-by-login")
                    .request()
                    .post(Entity.entity(u, MediaType.APPLICATION_JSON));
            if (response.hasEntity()) {
                Usuario found = response.readEntity(Usuario.class);
                id = found.getId();
                loginBean.setUsuarioLogeado(found);
                // aqui podemos guardar en la bbdd al refugio/cliente en su tabla correspondiente
                if (loginBean.getRol().equalsIgnoreCase("Refugio")) {
                    target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.refugio");
                    Refugio r = new Refugio();
                    r.setId(id);
                    r.setNombre(bean.getNombreRefugio());
                    r.setTelefono(bean.getTelefono());
                    r.setCif(bean.getCif());
                    r.setVerificado(false);
                    r.setDomicilioSocial(bean.getDomicilioSocial());
                    target.register(RefugioWriter.class).request()
                            .post(Entity.entity(r, MediaType.APPLICATION_JSON));
                    success = "success";
                    loginBean.setRefugio(r);
                } else if (loginBean.getRol().equalsIgnoreCase("Cliente")) {
                    target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.cliente");
                    Cliente c = new Cliente();
                    c.setId(id);
                    c.setNif(bean.getNif());
                    c.setNombre(bean.getNombreCliente());
                    //c.setApellidos(bean.getApellidos());
                    c.setApellidos(bean.getApellidos());
                    c.setDomicilio(bean.getDomicilio());
                    c.setTelefono(bean.getTelefono());
                    c.setFechaNacimiento(bean.getFechaNacimiento());
                    target.register(ClienteWriter.class).request()
                            .post(Entity.entity(c, MediaType.APPLICATION_JSON));
                    success = "success";
                    loginBean.setCliente(c);

                }
            } else {
                bean.showError();
            }
        }
        bean.clearValues();
        return success;
    }

}
