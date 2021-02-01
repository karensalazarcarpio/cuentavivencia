/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.cuentavivencia.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pedrito
 */
@Entity
@Table(name = "CuentaVivenciaDetalle", catalog = "Plataforma", schema = "Servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaVivenciaDetalle.findAll", query = "SELECT c FROM CuentaVivenciaDetalle c")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByIdCuentaVivenciaDet", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.idCuentaVivenciaDet = :idCuentaVivenciaDet")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByFechaRegistro", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByFechaInicio", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByFechaFin", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.fechaFin = :fechaFin")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByIdArchivoVideo", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.idArchivoVideo = :idArchivoVideo")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByIdArchivoSelphi", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.idArchivoSelphi = :idArchivoSelphi")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByMargenSuperior", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.margenSuperior = :margenSuperior")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByMargenInferior", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.margenInferior = :margenInferior")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByIdEstado", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.idEstado = :idEstado")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByToken", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.token = :token")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByOrigen", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.origen = :origen")
    , @NamedQuery(name = "CuentaVivenciaDetalle.findByIdUsuario", query = "SELECT c FROM CuentaVivenciaDetalle c WHERE c.idUsuario = :idUsuario")})
public class CuentaVivenciaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCuentaVivenciaDet", nullable = false)
    private Long idCuentaVivenciaDet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRegistro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaInicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaFin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idArchivoVideo", nullable = false)
    private long idArchivoVideo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idArchivoSelphi", nullable = false)
    private long idArchivoSelphi;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "margenSuperior", precision = 17, scale = 17)
    private Double margenSuperior;
    @Column(name = "margenInferior", precision = 17, scale = 17)
    private Double margenInferior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEstado", nullable = false)
    private long idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "token", nullable = false, length = 255)
    private String token;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "origen", nullable = false, length = 20)
    private String origen;
    @Column(name = "idUsuario")
    private BigInteger idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaVivenciaDetInicial", fetch = FetchType.LAZY)
    private List<CuentaVivencia> cuentaVivenciaList;
    @JoinColumn(name = "idCuentaVivencia", referencedColumnName = "idCuentaVivencia", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CuentaVivencia idCuentaVivencia;

    public CuentaVivenciaDetalle() {
    }

    public CuentaVivenciaDetalle(Long idCuentaVivenciaDet) {
        this.idCuentaVivenciaDet = idCuentaVivenciaDet;
    }

    public CuentaVivenciaDetalle(Long idCuentaVivenciaDet, Date fechaRegistro, Date fechaInicio, Date fechaFin, long idArchivoVideo, long idArchivoSelphi, long idEstado, String token, String origen) {
        this.idCuentaVivenciaDet = idCuentaVivenciaDet;
        this.fechaRegistro = fechaRegistro;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idArchivoVideo = idArchivoVideo;
        this.idArchivoSelphi = idArchivoSelphi;
        this.idEstado = idEstado;
        this.token = token;
        this.origen = origen;
    }

    public Long getIdCuentaVivenciaDet() {
        return idCuentaVivenciaDet;
    }

    public void setIdCuentaVivenciaDet(Long idCuentaVivenciaDet) {
        this.idCuentaVivenciaDet = idCuentaVivenciaDet;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public long getIdArchivoVideo() {
        return idArchivoVideo;
    }

    public void setIdArchivoVideo(long idArchivoVideo) {
        this.idArchivoVideo = idArchivoVideo;
    }

    public long getIdArchivoSelphi() {
        return idArchivoSelphi;
    }

    public void setIdArchivoSelphi(long idArchivoSelphi) {
        this.idArchivoSelphi = idArchivoSelphi;
    }

    public Double getMargenSuperior() {
        return margenSuperior;
    }

    public void setMargenSuperior(Double margenSuperior) {
        this.margenSuperior = margenSuperior;
    }

    public Double getMargenInferior() {
        return margenInferior;
    }

    public void setMargenInferior(Double margenInferior) {
        this.margenInferior = margenInferior;
    }

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public BigInteger getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigInteger idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<CuentaVivencia> getCuentaVivenciaList() {
        return cuentaVivenciaList;
    }

    public void setCuentaVivenciaList(List<CuentaVivencia> cuentaVivenciaList) {
        this.cuentaVivenciaList = cuentaVivenciaList;
    }

    public CuentaVivencia getIdCuentaVivencia() {
        return idCuentaVivencia;
    }

    public void setIdCuentaVivencia(CuentaVivencia idCuentaVivencia) {
        this.idCuentaVivencia = idCuentaVivencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuentaVivenciaDet != null ? idCuentaVivenciaDet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaVivenciaDetalle)) {
            return false;
        }
        CuentaVivenciaDetalle other = (CuentaVivenciaDetalle) object;
        if ((this.idCuentaVivenciaDet == null && other.idCuentaVivenciaDet != null) || (this.idCuentaVivenciaDet != null && !this.idCuentaVivenciaDet.equals(other.idCuentaVivenciaDet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.gob.senasir.cuentavivencia.model.CuentaVivenciaDetalle[ idCuentaVivenciaDet=" + idCuentaVivenciaDet + " ]";
    }
    
}
