package bo.gob.senasir.cuentavivencia.controllers;

import bo.gob.senasir.cuentavivencia.services.CuentaVivenciaDetalleService;
import bo.gob.senasir.cuentavivencia.vo.CuentaVivenciaVo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Pedrito
 */
@Path("v1/cuentavivenciadetalle")
public class CuentaVivenciaDetalleController {

    @Autowired
    private CuentaVivenciaDetalleService cuentaVivenciaDetalleService;

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
        cuentaVivenciaDetalleService.guardarCuentaVivenciaDetalles(cuentaVivenciaVo, files);
        return "OK";
    }
}
