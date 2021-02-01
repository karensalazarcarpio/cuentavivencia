/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.cuentavivencia.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Casa
 */
public class FechasUtil {

    public static Date getFechaFormato(String fecha, String formato, String descripcionFecha) {
        DateFormat dateFormat = new SimpleDateFormat(formato);
        Date fechaRespuesta = null;
        try {
            fechaRespuesta = dateFormat.parse(fecha);
        } catch (ParseException e) {
            if (descripcionFecha == null) {
                descripcionFecha = " ";
            } else {
                descripcionFecha = " " + descripcionFecha.trim() + " ";
            }
            MostrarMensajeError.enviarError("El dato de la fecha" + descripcionFecha + "tiene errores",
                    "El dato de la fecha es nulo o tiene un formato inválido. "
                    + "El formato requerido es: " + formato,
                    "Verifique la fecha ingresada");
        }
        return fechaRespuesta;
    }
}
