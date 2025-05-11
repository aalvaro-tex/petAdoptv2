/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.external;

import com.alvaro.pse.petadopt.json.AdoptanteReader;
import com.alvaro.pse.petadopt.solicitudes.AdoptanteDTO;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author alvar
 */
@Named
@RequestScoped
public class ListaNegraAPI {

    Client client = ClientBuilder.newClient();
    WebTarget target;

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public boolean consultaListaNegra(String email) {
        boolean puedeAdoptar = false;
        target = client.target("http://serpis.infor.uva.es/darklist/api/validar_adoptante/" + email);
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            AdoptanteDTO adto = response.readEntity(AdoptanteDTO.class);
            if (adto.getListaNegra().equalsIgnoreCase("si")) {
                // no puede adoptar
                puedeAdoptar = false;
            } else if (adto.getListaNegra().equalsIgnoreCase("no")) {
                // puede adoptar
                puedeAdoptar = true;
            }
        } else if (response.getStatus() == 404) {
            // no aparece en la lista, puede adoptar
            puedeAdoptar = true;
        }
        response.close();

        return puedeAdoptar;
    }

}
