/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.seguridad.services;

import bo.gob.senasir.seguridad.model.Archivosadjuntos;
import bo.gob.senasir.seguridad.vo.SeguridadVo;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.ContentDisposition;

/**
 *
 * @author KSalazar
 */
public interface ArchivosadjuntosService {
        
    public Archivosadjuntos adjuntarArchivo(SeguridadVo seguridadVo,
            InputStream fileInputStream,
            ContentDisposition fileMetaData);
}
