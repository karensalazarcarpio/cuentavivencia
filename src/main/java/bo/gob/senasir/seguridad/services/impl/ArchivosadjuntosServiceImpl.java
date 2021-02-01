/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.seguridad.services.impl;

import bo.gob.senasir.cuentavivencia.util.MostrarMensajeError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.senasir.seguridad.model.Archivosadjuntos;
import bo.gob.senasir.seguridad.repositories.ArchivosadjuntosRepository;
import bo.gob.senasir.seguridad.services.ArchivosadjuntosService;
import bo.gob.senasir.seguridad.vo.SeguridadVo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;
import org.glassfish.jersey.media.multipart.ContentDisposition;

/**
 *
 * @author KSalazar
 */
@Service("archivosadjuntosService")
@Transactional(readOnly = true)
public class ArchivosadjuntosServiceImpl implements ArchivosadjuntosService {

    @Autowired
    private ArchivosadjuntosRepository archivosadjuntosRepository;

    @Transactional
    private Archivosadjuntos crearRegistro(SeguridadVo seguridadVo) {
        Archivosadjuntos archivosadjuntos = new Archivosadjuntos();

        archivosadjuntos.setDescripcion(seguridadVo.getDescripcion());
        archivosadjuntos.setNombrearchivo(seguridadVo.getNombreArchivo());
        archivosadjuntos.setFirmadigital("S".equals(seguridadVo.getFirmadigital()));
        archivosadjuntos.setIdtipoadjunto(seguridadVo.getIdtipoadjunto());
        archivosadjuntos.setExtension(seguridadVo.getExtension());
        archivosadjuntos.setRuta(seguridadVo.getRuta());
        archivosadjuntos.setCrc(seguridadVo.getCrc());
        archivosadjuntos.setFecharegistro(new Date());
        return this.archivosadjuntosRepository.save(archivosadjuntos);
    }

    private void actualizarRutaYCrc(Archivosadjuntos archivosadjuntos, String ruta, String crc) {
        archivosadjuntos.setCrc(crc);
        archivosadjuntos.setRuta(ruta);
        this.archivosadjuntosRepository.save(archivosadjuntos);
    }

    public Archivosadjuntos adjuntarArchivo(SeguridadVo seguridadVo,
            InputStream fileInputStream,
            ContentDisposition fileMetaData) {
        seguridadVo.setExtension(getExtension(fileMetaData.getFileName()));

        Archivosadjuntos archivosadjuntos = this.crearRegistro(seguridadVo);

        seguridadVo.setRuta(this.getRuta(
                seguridadVo.getUploadPathModulo(),
                archivosadjuntos.getIdarchivoadjunto(), seguridadVo.getExtension())
        );
        this.adjuntar(fileInputStream, seguridadVo.getRuta());
        seguridadVo.setCrc(generarCRC(seguridadVo.getRuta()));
        this.actualizarRutaYCrc(archivosadjuntos, seguridadVo.getRuta(), seguridadVo.getCrc());

        return archivosadjuntos;
    }

    private String getRuta(String uploadPathModulo,Long idArchivoAdjunto, String extensionArchivo) {
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        String ruta = uploadPathModulo
                + calendar.get(Calendar.YEAR)
                + "/" + (calendar.get(Calendar.MONTH) + 1) + "/";
        /*+ "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/";*/

        File uploadPath = Paths.get(ruta).toFile();
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        ruta = ruta + idArchivoAdjunto + extensionArchivo;
        return ruta;
    }

    private String adjuntar(InputStream fileInputStream, String ruta) {
        try {
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream out = new FileOutputStream(new File(ruta));
            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            MostrarMensajeError.enviarError(
                    "Error al subir archivo al servidor",
                    "No se pudo subir el documento adjunto",
                    "Favor intente nuevamente, de persistirse el error contáctese con el administrador del sistema");
            //e.printStackTrace();
        }
        return ruta;
    }

    private static String getExtension(String nombreArchivo) {
        // Obtenemos la extension del archivo
        String ext = "";
        int pos = nombreArchivo.lastIndexOf('.');
        try {

            if (pos == -1) {
                MostrarMensajeError.enviarError("El nombre de archivo ["
                        + nombreArchivo
                        + "] no tiene extension.", "El archivo enviado no es permitido", "Revise el archivo enviado");
            }
            ext = nombreArchivo.substring(pos);
            if (ext.length() == 0) {
                MostrarMensajeError.enviarError("El nombre de archivo ["
                        + nombreArchivo
                        + "] no tiene extension.", "El archivo enviado no es permitido", "Revise el archivo enviado");
            }
            ext = ext.toLowerCase();
        } catch (Exception e) {
            MostrarMensajeError.enviarError("Error al revisar el nombre de archivo [" + nombreArchivo
                    + "] no tiene extension.", "El archivo enviado no es permitido", "Revise el archivo enviado");
        }

        // Verificando la extension del archivo
        if (!ext.equals(".pdf")) {
            MostrarMensajeError.enviarError(
                    "El formato de archivo [" + ext + "] no es admitido",
                    "El archivo enviado no es permitido",
                    "Revise el archivo enviado");
        }
        // Verificamos el nombre del archivo
        if (pos > 0) {
            String nameFile = nombreArchivo.substring(0, pos);
            if (!isValidName(nameFile)) {
                MostrarMensajeError.enviarError(
                        "El nombre de archivo [" + nombreArchivo + "] no es valido",
                        "El archivo enviado no es permitido",
                        "Verifique que no tenga caracteres invalidos en el nombre del archivo");
            }
        }
        return ext;
    }

    public static boolean isValidName(String text) {
        Pattern pattern = Pattern.compile(
                "# Match a valid Windows filename (unspecified file system).          \n"
                + "^                                # Anchor to start of string.        \n"
                + "(?!                              # Assert filename is not: CON, PRN, \n"
                + "  (?:                            # AUX, NUL, COM1, COM2, COM3, COM4, \n"
                + "    CON|PRN|AUX|NUL|             # COM5, COM6, COM7, COM8, COM9,     \n"
                + "    COM[1-9]|LPT[1-9]            # LPT1, LPT2, LPT3, LPT4, LPT5,     \n"
                + "  )                              # LPT6, LPT7, LPT8, and LPT9...     \n"
                + "  (?:\\.[^.]*)?                  # followed by optional extension    \n"
                + "  $                              # and end of string                 \n"
                + ")                                # End negative lookahead assertion. \n"
                + "[^<>:\"/\\\\|?*\\x00-\\x1F]*     # Zero or more valid filename chars.\n"
                + "[^<>:\"/\\\\|?*\\x00-\\x1F\\. ]  # Last char is not a space or dot.  \n"
                + "$                                # Anchor to end of string.            ",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(text);
        boolean isMatch = matcher.matches();
        return isMatch;
    }

    // CRC = GenerarCRC(img);// el parametro img es la ruta del archivo del cual se desea sacar el crc
    protected static String generarCRC(String img) {
        String vHash = null;
        try {
            java.nio.file.Path path = Paths.get(img);
            byte data[] = Files.readAllBytes(path);
            vHash = algoritmoHash(data, "MD5");

        } catch (Exception e) {
            MostrarMensajeError.enviarError(
                    "Error al generar CRC",
                    "No se pudo realizar la generación del CRC",
                    "Favor intente nuevamente, de persistirse el error contáctese con el administrador del sistema");
        }
        return vHash;
    }

    public static String algoritmoHash(byte[] pArchivo, String algorithm) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(pArchivo);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        } catch (Exception e) {
            MostrarMensajeError.enviarError(
                    "Error al generar el algortimo del Hash",
                    "No se pudo utilizar el método del algoritmo del Hash",
                    "Favor intente nuevamente, de persistirse el error contáctese con el administrador del sistema");

        }
        return hashValue;
    }

}
