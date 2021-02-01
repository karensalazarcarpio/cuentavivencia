package bo.gob.senasir.cuentavivencia.services.impl;

import bo.gob.senasir.cuentavivencia.constantes.CuentaVivenciaConstantes;
import bo.gob.senasir.cuentavivencia.constantes.EstadosConstantes;
import bo.gob.senasir.cuentavivencia.model.CuentaVivencia;
import bo.gob.senasir.cuentavivencia.model.CuentaVivenciaDetalle;
import bo.gob.senasir.cuentavivencia.repositories.CuentaVivenciaDetalleRepository;
import bo.gob.senasir.cuentavivencia.vo.CuentaVivenciaVo;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.senasir.cuentavivencia.services.CuentaVivenciaDetalleService;
import bo.gob.senasir.cuentavivencia.services.CuentaVivenciaService;
import bo.gob.senasir.seguridad.model.Archivosadjuntos;
import bo.gob.senasir.seguridad.services.ArchivosadjuntosService;
import bo.gob.senasir.seguridad.vo.SeguridadVo;

/**
 *
 * @author KAREN
 */
@Service("cuentaVivenciaDetalleService")
@Transactional(readOnly = true)
public class CuentaVivenciaDetalleServiceImpl implements CuentaVivenciaDetalleService {

    @Autowired
    private CuentaVivenciaDetalleRepository cuentaVivenciaDetalleRepository;
    @Autowired
    private ArchivosadjuntosService archivosadjuntosService;
    @Autowired
    private CuentaVivenciaService cuentaVivenciaService;

    @Transactional
    private CuentaVivenciaDetalle crearRegistro(CuentaVivenciaVo cuentaVivenciaVo) {
        CuentaVivenciaDetalle cuentaVivenciaDetalle = new CuentaVivenciaDetalle();
        //cuentaVivenciaDetalle.setFechaInicio(fechaInicio);
        //cuentaVivenciaDetalle.setFechaFin(fechaFin);
        cuentaVivenciaDetalle.setFechaRegistro(new Date());
        cuentaVivenciaDetalle.setIdArchivoSelphi(cuentaVivenciaVo.getIdArchivoSelphi());
        cuentaVivenciaDetalle.setIdArchivoVideo(cuentaVivenciaVo.getIdArchivoVideo());
        cuentaVivenciaDetalle.setIdCuentaVivencia(new CuentaVivencia(cuentaVivenciaVo.getIdCuentaVivencia()));
        cuentaVivenciaDetalle.setIdEstado(EstadosConstantes.ID_ESTADO_INICIAL);
        cuentaVivenciaDetalle.setIdUsuario(cuentaVivenciaVo.getIdUsuario());
        cuentaVivenciaDetalle.setMargenInferior(cuentaVivenciaVo.getMargenInferior());
        cuentaVivenciaDetalle.setMargenSuperior(cuentaVivenciaVo.getMargenSuperior());
        cuentaVivenciaDetalle.setOrigen(cuentaVivenciaVo.getOrigen());
        cuentaVivenciaDetalle.setToken(cuentaVivenciaVo.getToken());
        return this.cuentaVivenciaDetalleRepository.save(cuentaVivenciaDetalle);
    }

    @Transactional
    public void guardarCuentaVivenciaDetalles(CuentaVivenciaVo cuentaVivenciaVo, FormDataBodyPart files) {
        List<BodyPart> archivos = files.getParent().getBodyParts();
        archivos.remove(0);

        InputStream fileInputStream = archivos.get(0).getEntityAs(InputStream.class);
        ContentDisposition fileMetaData = archivos.get(0).getContentDisposition();

        SeguridadVo seguridadVo = new SeguridadVo();
        seguridadVo.setUploadPathModulo(CuentaVivenciaConstantes.UPLOAD_PATH);
        seguridadVo.setDescripcion("Selphi");
        seguridadVo.setIdtipoadjunto(1L);//TODO
        Archivosadjuntos archivoSelphi = this.archivosadjuntosService.adjuntarArchivo(seguridadVo, fileInputStream, fileMetaData);

        fileInputStream = archivos.get(1).getEntityAs(InputStream.class);
        fileMetaData = archivos.get(1).getContentDisposition();
        seguridadVo.setDescripcion("Video");
        seguridadVo.setIdtipoadjunto(1L);//TODO
        Archivosadjuntos archivoVideo = this.archivosadjuntosService.adjuntarArchivo(seguridadVo, fileInputStream, fileMetaData);

        cuentaVivenciaVo.setIdArchivoSelphi(archivoSelphi.getIdarchivoadjunto());
        cuentaVivenciaVo.setIdArchivoVideo(archivoVideo.getIdarchivoadjunto());
        CuentaVivenciaDetalle cuentaVivenciaDetalle = this.crearRegistro(cuentaVivenciaVo);

        this.cuentaVivenciaService.actualizarCuentaVigenciaDetInicial(cuentaVivenciaVo.getIdCuentaVivencia(),
                cuentaVivenciaDetalle.getIdCuentaVivenciaDet());
    }
}
