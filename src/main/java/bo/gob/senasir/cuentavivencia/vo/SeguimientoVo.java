/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.cuentavivencia.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author KSalazar
 */
@Entity
public class SeguimientoVo {
    @Id
    @Column(name = "idseguimiento")
    private Long idseguimiento;
    @Column(name = "area")
    private String area;
    @Column(name = "fechaingreso")
    private String fechaingreso;

   
    @Column(name = "mensaje")
    private String mensaje;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "descripcionhojaruta")
    private String descripcionhojaruta;
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcionhojaruta() {
        return descripcionhojaruta;
    }

    public void setDescripcionhojaruta(String descripcionhojaruta) {
        this.descripcionhojaruta = descripcionhojaruta;
    }
    
    public Long getIdseguimiento() {
        return idseguimiento;
    }

    public void setIdseguimiento(Long idseguimiento) {
        this.idseguimiento = idseguimiento;
    }

       
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }
  
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    
}
