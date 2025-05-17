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
import com.alvaro.pse.petadopt.login.LoginPageBackingBean;
import com.alvaro.pse.petadopt.utils.ImageUtils;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alvar
 */
@Named
@RequestScoped
public class SignUpPageClientBean {

    @Inject
    SignUpPageBackingBean bean;
    @Inject
    LoginPageBackingBean loginBean;
    @Inject
    ImageUtils imageUtils;
    @Inject
    private UsuarioEJB usuarioEJB;

    /**
     *
     */
    @PreDestroy
    public void destroy() {
        bean.clearValues();
    }

    /**
     *
     * @return
     */
    public String register() {
        String success = "failure";
        if ((loginBean.getRol().equalsIgnoreCase("cliente") && esMayorDeEdad(bean.getFechaNacimiento())) || loginBean.getRol().equalsIgnoreCase("refugio")) {
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
        } else {
            bean.showError("Debes ser mayor de edad para registrarte");
        }
        bean.clearValues();
        return success;
    }

    /**
     *
     * @param fechaNacimiento
     * @return
     */
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
