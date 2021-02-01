/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.cuentavivencia.services;

import bo.gob.senasir.cuentavivencia.vo.CuentaVivenciaVo;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

/**
 *
 * @author KSalazar
 */
public interface CuentaVivenciaService {
    
    public void guardarCuentaVivencia(CuentaVivenciaVo cuentaVivenciaVo, FormDataBodyPart files);
    
    public void actualizarCuentaVigenciaDetInicial(Long idCuentaVivencia, Long idCuentaVivenciaDetInicial);
}
