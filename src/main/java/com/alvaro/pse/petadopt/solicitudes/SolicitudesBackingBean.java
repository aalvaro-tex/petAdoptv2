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
    
    private StreamedContent excel;

    /**
     *
     * @return
     */
    public StreamedContent getExcel() {
        return excel;
    }

    /**
     *
     * @param excel
     */
    public void setExcel(StreamedContent excel) {
        this.excel = excel;
    }

    private Long idMascotaSelected;
    private Mascota mascotaSelected;

    /**
     *
     * @return
     */
    public Mascota getMascotaSelected() {
        return mascotaSelected;
    }

    /**
     *
     * @param mascotaSelected
     */
    public void setMascotaSelected(Mascota mascotaSelected) {
        this.mascotaSelected = mascotaSelected;
    }

    /**
     *
     * @return
     */
    public String getVerMascotasFilter() {
        return verMascotasFilter;
    }

    /**
     *
     * @return
     */
    public Long getIdMascotaSelected() {
        return idMascotaSelected;
    }

    /**
     *
     * @param idMascotaSelected
     */
    public void setIdMascotaSelected(Long idMascotaSelected) {
        this.idMascotaSelected = idMascotaSelected;
    }

    /**
     *
     * @param verMascotasFilter
     */
    public void setVerMascotasFilter(String verMascotasFilter) {
        this.verMascotasFilter = verMascotasFilter;
    }

    /**
     *
     * @return
     */
    public String getRutaCertificado() {
        return rutaCertificado;
    }

    /**
     *
     * @param rutaCertificado
     */
    public void setRutaCertificado(String rutaCertificado) {
        this.rutaCertificado = rutaCertificado;
    }

    /**
     *
     * @return
     */
    public StreamedContent getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
    /**
     *
     * @return
     */
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
    
    /**
     *
     * @param estado
     * @return
     */
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
