/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.registro;

import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
import com.alvaro.pse.petadopt.jaas.UsuarioEJB;
import com.alvaro.pse.petadopt.json.ClienteWriter;
import com.alvaro.pse.petadopt.json.UsuarioWriter;
import com.alvaro.pse.petadopt.json.RefugioWriter;
import com.alvaro.pse.petadopt.login.LoginPageBackingBean;
import com.alvaro.pse.petadopt.utils.ImageUtils;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    @Inject
    ImageUtils imageUtils;
    @Inject
    private UsuarioEJB usuarioEJB;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/petAdoptv2/webresources/com.alvaro.pse.petadoptv2.entities.usuario");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    /*
    public String addUsuario() {
        // comprobamos que las dos contraseñas sean iguales
        Long id = -1L;
        String success = "failure";
        String p1 = bean.getPassword1();
        String p2 = bean.getPassword2();
        if (!p1.equalsIgnoreCase(p2)) {
            bean.showError("Las contraseñas no coinciden");
        } else {

            Usuario u = new Usuario();
            u.setEmail(bean.getEmail());
            u.setPassword(bean.getPassword1()); // da igual, las dos coinciden
            u.setFoto(this.imageUtils.upload(bean.getFotoPerfil()));
            // hay que comprobar si el correo ya está registrado
            Response response = target
                    .path("find-by-email/{email}")
                    .resolveTemplate("email", u.getEmail())
                    .request()
                    .get();
            if (response.getStatus() == 200) {
                // el usuario ya está registrado
                bean.showError("El correo ya está registrado");
            } else {
                System.out.println(u.toString());
                target.register(UsuarioWriter.class)
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(u, MediaType.APPLICATION_JSON));
                // recuperamos el id del usuario que acabamos de crear
                // para asignarselo al id del refugio/cliente que corresponda
                response = target
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
                    // llegamos aquí si no hemos elegido una foto de perfil
                    bean.showError("Debes seleccionar una imagen de perfil");
                }
            }
        }
        bean.clearValues();
        return success;
    }
     */
    public String register() {
        String success = "failure";
        System.out.println(bean.getFechaNacimiento());
        if(esMayorDeEdad(bean.getFechaNacimiento())){
        Usuario user = new Usuario(bean.getEmail(), bean.getPassword1(), this.imageUtils.upload(bean.getFotoPerfil()));
        user.setActivo(true);
        try {
            Long idUsuario = usuarioEJB.createUser(user);
            // tenemos el id del usuario para poder crear el refugio/cliente
            if (loginBean.getRol().equalsIgnoreCase("refugio")) {
                Refugio r = new Refugio();
                r.setId(idUsuario);
                r.setNombre(bean.getNombreRefugio());
                r.setTelefono(bean.getTelefono());
                r.setCif(bean.getCif());
                r.setVerificado(false);
                r.setDomicilioSocial(bean.getDomicilioSocial());

                usuarioEJB.createRefugio(r);
                success = "success";
                loginBean.setCliente(null);
                loginBean.setRefugio(r);
                loginBean.setUsuarioLogeado(user);
            } else if (loginBean.getRol().equalsIgnoreCase("cliente")) {
                Cliente c = new Cliente();
                c.setId(idUsuario);
                c.setNif(bean.getNif());
                c.setNombre(bean.getNombreCliente());
                c.setApellidos(bean.getApellidos());
                c.setDomicilio(bean.getDomicilio());
                c.setTelefono(bean.getTelefono());
                // hay que validar la mayoría de edad
                
                c.setFechaNacimiento(bean.getFechaNacimiento());
                System.out.println(c.getId());
                System.out.println("Añadimos un cliente");

                usuarioEJB.createCliente(c);

                success = "success";
                loginBean.setCliente(c);
                loginBean.setRefugio(null);
                loginBean.setUsuarioLogeado(user);
                
            }
        } catch (Exception e) {
            bean.showError("El email ya está en uso");
        }
        } else{
            bean.showError("Debes ser mayor de edad para registrarte");
        }
        
        return success;
    }

    public boolean esMayorDeEdad(String fechaNacimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate nacimiento = LocalDate.parse(fechaNacimiento, formatter);
            LocalDate hoy = LocalDate.now(); // Se calcula la diferencia en años 
            int edad = Period.between(nacimiento, hoy).getYears();
            return edad >= 18;
        } catch (DateTimeParseException e) {
            System.err.println("Formato de fecha incorrecto. Debe ser dd/MM/yyyy");
        }
        return false;
    
}

}
