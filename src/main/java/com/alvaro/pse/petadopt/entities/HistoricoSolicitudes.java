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

    public HistoricoSolicitudes() {
    }

    public HistoricoSolicitudes(Long id) {
        this.id = id;
    }

    public HistoricoSolicitudes(Long id, long idRefugio, long idMascota, long idCliente, String estado, String fechaModificacion) {
        this.id = id;
        this.idRefugio = idRefugio;
        this.idMascota = idMascota;
        this.idCliente = idCliente;
        this.estado = estado;
        this.fechaModificacion = fechaModificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdRefugio() {
        return idRefugio;
    }

    public void setIdRefugio(long idRefugio) {
        this.idRefugio = idRefugio;
    }

    public long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(long idMascota) {
        this.idMascota = idMascota;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

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
    
}
