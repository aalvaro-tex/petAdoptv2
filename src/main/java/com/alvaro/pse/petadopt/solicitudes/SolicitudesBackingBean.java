/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.solicitudes;

import com.alvaro.pse.petadopt.entities.Mascota;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author alvar
 */
@Named
@SessionScoped
public class SolicitudesBackingBean implements Serializable {

    private String verMascotasFilter = "pendiente";
    
    private String rutaCertificado;

    private StreamedContent file;

    private Long idMascotaSelected;
    private Mascota mascotaSelected;

    public Mascota getMascotaSelected() {
        return mascotaSelected;
    }

    public void setMascotaSelected(Mascota mascotaSelected) {
        this.mascotaSelected = mascotaSelected;
    }

    public String getVerMascotasFilter() {
        return verMascotasFilter;
    }

    public Long getIdMascotaSelected() {
        return idMascotaSelected;
    }

    public void setIdMascotaSelected(Long idMascotaSelected) {
        this.idMascotaSelected = idMascotaSelected;
    }

    public void setVerMascotasFilter(String verMascotasFilter) {
        this.verMascotasFilter = verMascotasFilter;
    }

    public String getRutaCertificado() {
        return rutaCertificado;
    }

    public void setRutaCertificado(String rutaCertificado) {
        this.rutaCertificado = rutaCertificado;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
    

    public String getFilterDisplayName() {
        String text = "";
        switch (this.verMascotasFilter) {
            case "todas":
                text = "Todas las solicitudes";
                break;
            case "adoptada":
                text = "Solicitudes aceptadas";
                break;
            case "pendiente":
                text = "Solicitudes pendientes de revisar";
                break;
            case "rechazada":
                text = "Solicitudes rechazadas";
                break;
            default:
                break;
        }
        return text;
    }
    
    public String getEstadoDisplayName(String estado) {
        String text = "";
        switch (estado) {
            case "sin_solicitud":
                text = "Sin solicitud";
                break;
            case "adoptada":
                text = "Aceptada";
                break;
            case "pendiente":
                text = "Pendiente de aprobar";
                break;
                case "rechazada":
                text = "Rechazada";
                break;
            default:
                break;
        }
        return text;
    }

}
