package com.conexion.ftp.ConexionFTP;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConexionFtpApplication {

    private static final Logger LOG = Logger.getLogger(ConexionFtpApplication.class.getName());

    public static void main(String[] args) throws FileNotFoundException, IOException {

        final String SERVIDOR = "172.30.27.45";
        //final Integer PUERTO = 22; // Puerto FTP por default
        final String USUARIO = "efren.mendez";
        final String PASSWORD = "Enared32";
        final String HOSTFILE = "00195100.pdf";//201505.zip";
        final String localFileDownload = "C:\\Users\\iOrlando\\Downloads/" + HOSTFILE;
        File tempFile = File.createTempFile("archivoTermporal", "pdf");
        
        FTPClient clienteFtp = new FTPClient();

        try {
            clienteFtp.connect(SERVIDOR);
            Integer respuesta = clienteFtp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(respuesta)) {
                LOG.info("---------->> Error");
            }
            LOG.info("---------->> Servidor: " + SERVIDOR + " Usuario: " + USUARIO + " Contraseña: " + PASSWORD);
            LOG.info("---------->> Respuesta: " + respuesta);
            boolean loginSatisfactorio = clienteFtp.login(USUARIO, PASSWORD);

            if (loginSatisfactorio) {
                System.out.println("Se ha iniciado sesión en el servidor FTP.");
            } else {
                System.out.println("Las credenciales son inválidas.");
            }

            for (String file : clienteFtp.listNames()) {
                LOG.info("---------->> Raiz: " + file);
            }
            
            clienteFtp.changeWorkingDirectory("201505/001/00/");
            for (String file : clienteFtp.listNames()) {
                LOG.info("---------->> RUTA: " + file);
            }
            
            

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));
        out.write("Esto es un fichero temporal");
        out.close();

        BufferedOutputStream buffOut = new BufferedOutputStream(
                new FileOutputStream(localFileDownload)
        );

        if (clienteFtp.retrieveFile(HOSTFILE, buffOut)) {
            System.out.println("Descarga correcta");
        } else {
            System.out.println("Error Descarga");
        }

        buffOut.close();

        SpringApplication.run(ConexionFtpApplication.class,
                args
        );
//
    }

}
