package com.conexion.ftp.ConexionFTP;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author iOrlando
 */
public class ConexionFTPServicio {

    private static final Logger LOG = Logger.getLogger(ConexionFTPServicio.class.getName());

    private FTPClient clienteFtp;
    private BufferedOutputStream buffOut;

    public Boolean conectaFTP(String servidor, String usuario, String llave) throws IOException {
        LOG.info("---------->> Estableciendo conexion a servidor FTP ... ");
        clienteFtp = new FTPClient();
        clienteFtp.connect(servidor);
        Integer respuesta = clienteFtp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(respuesta)) {
            LOG.log(Level.WARNING, "---------->> Error: {0}", respuesta);
        }
        if (clienteFtp.login(usuario, llave)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean descargarArchivo(String nombre, String ruta) throws FileNotFoundException, IOException {
        LOG.info("---------->> Descargando documentos ... ");
        buffOut = new BufferedOutputStream(new FileOutputStream(ruta.concat(nombre)));
        if (clienteFtp.retrieveFile(nombre, buffOut)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void desconectarFTP() throws IOException {
        LOG.info("---------->> Saliendo de servidor FTP ... ");
        this.buffOut.close();
    }

}
