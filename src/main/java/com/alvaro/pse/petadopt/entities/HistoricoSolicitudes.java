/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alvar
 */
@Entity
@Table(name = "historico_solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoSolicitudes.findAll", query = "SELECT h FROM HistoricoSolicitudes h"),
    @NamedQuery(name = "HistoricoSolicitudes.findById", query = "SELECT h FROM HistoricoSolicitudes h WHERE h.id = :id"),
    @NamedQuery(name = "HistoricoSolicitudes.findByIdRefugio", query = "SELECT h FROM HistoricoSolicitudes h WHERE h.idRefugio = :idRefugio"),
    @NamedQuery(name = "HistoricoSolicitudes.findByIdMascota", query = "SELECT h FROM HistoricoSolicitudes h WHERE h.idMascota = :idMascota"),
    @NamedQuery(name = "HistoricoSolicitudes.findByIdCliente", query = "SELECT h FROM HistoricoSolicitudes h WHERE h.idCliente = :idCliente"),
    @NamedQuery(name = "HistoricoSolicitudes.findByEstado", query = "SELECT h FROM HistoricoSolicitudes h WHERE h.estado = :estado"),
    @NamedQuery(name = "HistoricoSolicitudes.findByFechaModificacion", query = "SELECT h FROM HistoricoSolicitudes h WHERE h.fechaModificacion = :fechaModificacion")})
public class HistoricoSolicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_refugio")
    private long idRefugio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_mascota")
    private long idMascota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private long idCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "fecha_modificacion")
    private String fechaModificacion;

    /**
     *
     */
    public HistoricoSolicitudes() {
    }

    /**
     *
     * @param id
     */
    public HistoricoSolicitudes(Long id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param idRefugio
     * @param idMascota
     * @param idCliente
     * @param estado
     * @param fechaModificacion
     */
    public HistoricoSolicitudes(Long id, long idRefugio, long idMascota, long idCliente, String estado, String fechaModificacion) {
        this.id = id;
        this.idRefugio = idRefugio;
        this.idMascota = idMascota;
        this.idCliente = idCliente;
        this.estado = estado;
        this.fechaModificacion = fechaModificacion;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public long getIdRefugio() {
        return idRefugio;
    }

    /**
     *
     * @param idRefugio
     */
    public void setIdRefugio(long idRefugio) {
        this.idRefugio = idRefugio;
    }

    /**
     *
     * @return
     */
    public long getIdMascota() {
        return idMascota;
    }

    /**
     *
     * @param idMascota
     */
    public void setIdMascota(long idMascota) {
        this.idMascota = idMascota;
    }

    /**
     *
     * @return
     */
    public long getIdCliente() {
        return idCliente;
    }

    /**
     *
     * @param idCliente
     */
    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     *
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     *
     * @return
     */
    public String getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     *
     * @param fechaModificacion
     */
    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoSolicitudes)) {
            return false;
        }
        HistoricoSolicitudes other = (HistoricoSolicitudes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alvaro.pse.petadopt.entities.HistoricoSolicitudes[ id=" + id + " ]";
    }

    /**
     *
     * @param index
     * @return
     */
    public String getAttrAtIdx(int index) {
        switch (index) {
            case 0:
                return this.getFechaModificacion();
            case 1:
                return String.valueOf(this.getIdCliente());
            case 2:
                return String.valueOf(this.getIdMascota());
            case 3:
                return this.getEstado();
            default:
                return "";
        }
    }

}
