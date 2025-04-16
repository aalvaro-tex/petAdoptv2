/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "mascota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mascota.findAll", query = "SELECT m FROM Mascota m"),
    @NamedQuery(name = "Mascota.findById", query = "SELECT m FROM Mascota m WHERE m.id = :id"),
    @NamedQuery(name = "Mascota.findByNombre", query = "SELECT m FROM Mascota m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Mascota.findByEspecie", query = "SELECT m FROM Mascota m WHERE m.especie = :especie"),
    @NamedQuery(name = "Mascota.findByRaza", query = "SELECT m FROM Mascota m WHERE m.raza = :raza"),
    @NamedQuery(name = "Mascota.findByEdad", query = "SELECT m FROM Mascota m WHERE m.edad = :edad"),
    @NamedQuery(name = "Mascota.findByEstadoSalud", query = "SELECT m FROM Mascota m WHERE m.estadoSalud = :estadoSalud"),
    @NamedQuery(name = "Mascota.findByCosteAdopcion", query = "SELECT m FROM Mascota m WHERE m.costeAdopcion = :costeAdopcion"),
    @NamedQuery(name = "Mascota.findByFoto", query = "SELECT m FROM Mascota m WHERE m.foto = :foto"),
    @NamedQuery(name = "Mascota.findByIdRefugio", query = "SELECT m FROM Mascota m WHERE m.idRefugio = :idRefugio"),
    @NamedQuery(name = "Mascota.findByFechaPublicacion", query = "SELECT m FROM Mascota m WHERE m.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "Mascota.findByIdCliente", query = "SELECT m FROM Mascota m WHERE m.idCliente = :idCliente"),
    @NamedQuery(name = "Mascota.findByEstado", query = "SELECT m FROM Mascota m WHERE m.estado = :estado"),
    @NamedQuery(name = "Mascota.findByFechaAdopcion", query = "SELECT m FROM Mascota m WHERE m.fechaAdopcion = :fechaAdopcion"),
    @NamedQuery(name = "Mascota.findByFechaSolicitud", query = "SELECT m FROM Mascota m WHERE m.fechaSolicitud = :fechaSolicitud")})
public class Mascota implements Serializable {

    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "id_refugio")
    private Long idRefugio;
    @Size(max = 2147483647)
    @Column(name = "firma")
    private String firma;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "especie")
    private String especie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "raza")
    private String raza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edad")
    private long edad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estado_salud")
    private String estadoSalud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coste_adopcion")
    private double costeAdopcion;
    @Column(name = "foto")
    private String foto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "fecha_publicacion")
    private String fechaPublicacion;
    @Size(max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @Size(max = 2147483647)
    @Column(name = "fecha_adopcion")
    private String fechaAdopcion;
    @Size(max = 2147483647)
    @Column(name = "fecha_solicitud")
    private String fechaSolicitud;

    public Mascota() {
    }

    public Mascota(Long id) {
        this.id = id;
    }

    public Mascota(Long id, String nombre, String especie, String raza, Long edad, String estadoSalud, double costeAdopcion, Long idRefugio, String fechaPublicacion, Long idCliente) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.estadoSalud = estadoSalud;
        this.costeAdopcion = costeAdopcion;
        this.idRefugio = idRefugio;
        this.fechaPublicacion = fechaPublicacion;
        this.idCliente = idCliente;
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public long getEdad() {
        return edad;
    }

    public void setEdad(long edad) {
        this.edad = edad;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public double getCosteAdopcion() {
        return costeAdopcion;
    }

    public void setCosteAdopcion(double costeAdopcion) {
        this.costeAdopcion = costeAdopcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public long getIdRefugio() {
        return idRefugio;
    }

    public void setIdRefugio(long idRefugio) {
        this.idRefugio = idRefugio;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
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

    public String getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(String fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
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
        if (!(object instanceof Mascota)) {
            return false;
        }
        Mascota other = (Mascota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alvaro.pse.petadoptv2.entities.Mascota[ id=" + id + " ]";
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

}
