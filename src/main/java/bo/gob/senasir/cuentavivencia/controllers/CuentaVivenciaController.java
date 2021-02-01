/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.cuentavivencia.controllers;

import bo.gob.senasir.cuentavivencia.vo.CuentaVivenciaVo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import bo.gob.senasir.cuentavivencia.services.CuentaVivenciaService;

/**
 *
 * @author KSalazar
 */
@Path("v1/cuentavivencia")
public class CuentaVivenciaController {

    @Autowired
    private CuentaVivenciaService cuentaVivenciaService;

    private void validarVivencia(CuentaVivenciaVo cuentaVivenciaVo) {
        //TODO
    }

    @Path("crea")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(
            @FormDataParam("file") FormDataBodyPart files,
            @FormDataParam("data") FormDataBodyPart data
    ) {
        data.setMediaType(MediaType.APPLICATION_JSON_TYPE);
        CuentaVivenciaVo cuentaVivenciaVo = data.getValueAs(CuentaVivenciaVo.class);

        validarVivencia(cuentaVivenciaVo);
        cuentaVivenciaService.guardarCuentaVivencia(cuentaVivenciaVo, files);
        return "OK";
    }
}
