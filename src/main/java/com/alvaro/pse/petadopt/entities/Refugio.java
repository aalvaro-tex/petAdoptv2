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
@Table(name = "refugio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Refugio.findAll", query = "SELECT r FROM Refugio r"),
    @NamedQuery(name = "Refugio.findById", query = "SELECT r FROM Refugio r WHERE r.id = :id"),
    @NamedQuery(name = "Refugio.findByNombre", query = "SELECT r FROM Refugio r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Refugio.findByCif", query = "SELECT r FROM Refugio r WHERE r.cif = :cif"),
    @NamedQuery(name = "Refugio.findByTelefono", query = "SELECT r FROM Refugio r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "Refugio.findByDomicilioSocial", query = "SELECT r FROM Refugio r WHERE r.domicilioSocial = :domicilioSocial"),
    @NamedQuery(name = "Refugio.findByVerificado", query = "SELECT r FROM Refugio r WHERE r.verificado = :verificado")})
public class Refugio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @NotNull
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "cif")
    private String cif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "domicilio_social")
    private String domicilioSocial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "verificado")
    private boolean verificado;


    public Refugio() {
    }

    public Refugio(Long id) {
        this.id = id;
    }

    public Refugio(Long id, String nombre, String cif, String telefono, String domicilioSocial, boolean verificado) {
        this.id = id;
        this.nombre = nombre;
        this.cif = cif;
        this.telefono = telefono;
        this.domicilioSocial = domicilioSocial;
        this.verificado = verificado;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilioSocial() {
        return domicilioSocial;
    }

    public void setDomicilioSocial(String domicilioSocial) {
        this.domicilioSocial = domicilioSocial;
    }

    public boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
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
        if (!(object instanceof Refugio)) {
            return false;
        }
        Refugio other = (Refugio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alvaro.pse.petadoptv2.entities.Refugio[ id=" + id + " ]";
    }
    
}
