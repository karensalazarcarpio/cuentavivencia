/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.seguridad.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pedrito
 */
@Entity
@Table(name = "archivosadjuntos", catalog = "Plataforma", schema = "Seguridad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Archivosadjuntos.findAll", query = "SELECT a FROM Archivosadjuntos a")
    , @NamedQuery(name = "Archivosadjuntos.findByIdarchivoadjunto", query = "SELECT a FROM Archivosadjuntos a WHERE a.idarchivoadjunto = :idarchivoadjunto")
    , @NamedQuery(name = "Archivosadjuntos.findByIdtipoadjunto", query = "SELECT a FROM Archivosadjuntos a WHERE a.idtipoadjunto = :idtipoadjunto")
    , @NamedQuery(name = "Archivosadjuntos.findByDescripcion", query = "SELECT a FROM Archivosadjuntos a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Archivosadjuntos.findByRuta", query = "SELECT a FROM Archivosadjuntos a WHERE a.ruta = :ruta")
    , @NamedQuery(name = "Archivosadjuntos.findByExtension", query = "SELECT a FROM Archivosadjuntos a WHERE a.extension = :extension")
    , @NamedQuery(name = "Archivosadjuntos.findByFirmadigital", query = "SELECT a FROM Archivosadjuntos a WHERE a.firmadigital = :firmadigital")
    , @NamedQuery(name = "Archivosadjuntos.findByCrc", query = "SELECT a FROM Archivosadjuntos a WHERE a.crc = :crc")
    , @NamedQuery(name = "Archivosadjuntos.findByNombrearchivo", query = "SELECT a FROM Archivosadjuntos a WHERE a.nombrearchivo = :nombrearchivo")})
public class Archivosadjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarchivoadjunto", nullable = false)
    private Long idarchivoadjunto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipoadjunto", nullable = false)
    private long idtipoadjunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "ruta", nullable = false, length = 500)
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "extension", nullable = false, length = 50)
    private String extension;
    @Basic(optional = false)
    @NotNull
    @Column(name = "firmadigital", nullable = false)
    private boolean firmadigital;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "crc", nullable = false, length = 50)
    private String crc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombrearchivo", nullable = false, length = 100)
    private String nombrearchivo;
    @NotNull
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;

    public Archivosadjuntos() {
    }

    public Archivosadjuntos(Long idarchivoadjunto) {
        this.idarchivoadjunto = idarchivoadjunto;
    }

    public Archivosadjuntos(Long idarchivoadjunto, long idtipoadjunto, String descripcion, String ruta, String extension, boolean firmadigital, String crc, String nombrearchivo) {
        this.idarchivoadjunto = idarchivoadjunto;
        this.idtipoadjunto = idtipoadjunto;
        this.descripcion = descripcion;
        this.ruta = ruta;
        this.extension = extension;
        this.firmadigital = firmadigital;
        this.crc = crc;
        this.nombrearchivo = nombrearchivo;
    }

    public Long getIdarchivoadjunto() {
        return idarchivoadjunto;
    }

    public void setIdarchivoadjunto(Long idarchivoadjunto) {
        this.idarchivoadjunto = idarchivoadjunto;
    }

    public long getIdtipoadjunto() {
        return idtipoadjunto;
    }

    public void setIdtipoadjunto(long idtipoadjunto) {
        this.idtipoadjunto = idtipoadjunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean getFirmadigital() {
        return firmadigital;
    }

    public void setFirmadigital(boolean firmadigital) {
        this.firmadigital = firmadigital;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getNombrearchivo() {
        return nombrearchivo;
    }

    public void setNombrearchivo(String nombrearchivo) {
        this.nombrearchivo = nombrearchivo;
    }
    
    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarchivoadjunto != null ? idarchivoadjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archivosadjuntos)) {
            return false;
        }
        Archivosadjuntos other = (Archivosadjuntos) object;
        if ((this.idarchivoadjunto == null && other.idarchivoadjunto != null) || (this.idarchivoadjunto != null && !this.idarchivoadjunto.equals(other.idarchivoadjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.gob.senasir.seguridad.model.Archivosadjuntos[ idarchivoadjunto=" + idarchivoadjunto + " ]";
    }
    
}
