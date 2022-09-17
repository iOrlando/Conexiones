package com.conexion.ftp.ConexionFTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Logger;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.LoggerFactory;



public class FTPServicio {
    /**
     * FTP connection handler
     */
    FTPClient ftpconnection;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(FTPServicio.class);
    /**
     * Method that implement FTP connection.
     * @param host IP of FTP server
     * @param user FTP valid user
     * @param pass FTP valid pass for user
     */
    public void connectToFTP(String host, String user, String pass) {
        ftpconnection = new FTPClient();
        ftpconnection.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        try {
            ftpconnection.connect(host);
        } catch (IOException e) {
            logger.info(e.toString());
        }
        reply = ftpconnection.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            try {
                ftpconnection.disconnect();
            } catch (IOException e) {
                logger.info(e.toString());
            }
        }
        try {
            ftpconnection.login(user, pass);
        } catch (IOException e) {
            logger.info(e.toString());
        }
        try {
            ftpconnection.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            logger.info(e.toString());
        }
        ftpconnection.enterLocalPassiveMode();
    }
    /**
     * Method that allow upload file to FTP
     * @param file File object of file to upload
     * @param ftpHostDir FTP host internal directory to save file
     * @param serverFilename Name to put the file in FTP server.
     */
    public void uploadFileToFTP(File file, String ftpHostDir , String serverFilename) {
        try {
            InputStream input = new FileInputStream(file);
            this.ftpconnection.storeFile(ftpHostDir + serverFilename, input);
        } catch (IOException e) {
            logger.info(e.toString());
        }
    }
    /**
     * Method for download files from FTP.
     * @param ftpRelativePath Relative path of file to download into FTP server.
     * @param copytoPath Path to copy the file in download process.
     */
    public void downloadFileFromFTP(String ftpRelativePath, String copytoPath) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(copytoPath);
        } catch (FileNotFoundException e) {
            logger.info(e.toString());
        }
        try {
            this.ftpconnection.retrieveFile(ftpRelativePath, fos);
        } catch (IOException e) {
            logger.info(e.toString());
        }
    }
    /**
     * Method for release the FTP connection.
     */
    public void disconnectFTP() {
        if (this.ftpconnection.isConnected()) {
            try {
                this.ftpconnection.logout();
                this.ftpconnection.disconnect();
            } catch (IOException f) {
               logger.info(f.toString());
            }
        }
    }
}
