/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.cuentavivencia.util;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Pedrito
 */
public class MostrarMensajeError {

    public static void enviarError(String mensaje, String causa, String accion) {
        MensajeWebError mensajeWebError = new MensajeWebError();

        mensajeWebError.setMensaje(mensaje);
        mensajeWebError.setCausa(causa);
        mensajeWebError.setAccion(accion);

        List<MensajeWebError> errores = new ArrayList<MensajeWebError>();
        errores.add(mensajeWebError);

        enviarListaErrores(errores);
    }

    public static void enviarListaErrores(List<MensajeWebError> errores) {
        MensajeWebListaError mensajeWebListaError = new MensajeWebListaError();
        mensajeWebListaError.setErrores(errores);
        
        throw new WebApplicationException(
                Response.status(
                        Response.Status.INTERNAL_SERVER_ERROR).entity(
                                mensajeWebListaError).build());
    }
}
