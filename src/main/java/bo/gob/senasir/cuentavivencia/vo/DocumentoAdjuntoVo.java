/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.cuentavivencia.vo;

/**
 *
 * @author KSalazar
 */
public class DocumentoAdjuntoVo {

    private Long idDocumento;
    private Long idtipoadjunto;
    private String descripcion;
    private String nombreArchivo;
    private String firmadigital;
    private String ruta;
    private String extension;
    private String crc;

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Long getIdtipoadjunto() {
        return idtipoadjunto;
    }

    public void setIdtipoadjunto(Long idtipoadjunto) {
        this.idtipoadjunto = idtipoadjunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getFirmadigital() {
        return firmadigital;
    }

    public void setFirmadigital(String firmadigital) {
        this.firmadigital = firmadigital;
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

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

}
