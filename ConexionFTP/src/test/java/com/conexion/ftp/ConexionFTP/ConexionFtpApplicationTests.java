package com.conexion.ftp.ConexionFTP;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConexionFtpApplicationTests {
    
    @Autowired
    ConexionFTPServicio conexionFTPServicio;

    @Test
    void contextLoads() {

        try {
            conexionFTPServicio.conectaFTP("172.30.27.45", "fren.mendez", "Enared32");
            conexionFTPServicio.descargarArchivo("201505.zip", "C:\\Users\\iOrlando\\Downloads\\");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
