package br.com.unicred.caixa.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class UploadFTP {

    // TODO: implementation group: 'commons-net', name: 'commons-net', version: '3.6'

    private static final String SERVER = "www.myserver.com";
    private static final int PORT = 21;
    private static final String USER = "user";
    private static final String PASS = "pass";

    public static void enviarArquivosServidor(String nomeArquivo, String pathCompletoEvidencia) {

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(SERVER, PORT);
            ftpClient.login(USER, PASS);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream

            //File pathCompletoEvidencia = new File("D:/Test/Projects.zip");
            //String nomeArquivo = "Projects.zip";

            InputStream inputStream = new FileInputStream(pathCompletoEvidencia);

            System.out.println("Iniciando envio do primeiro arquivo...");
            boolean done = ftpClient.storeFile(nomeArquivo, inputStream);
            inputStream.close();

            if (done) {
                System.out.println("O primeiro arquivo foi enviado com sucesso!");
            }

            // ------------------ APPROACH #2: uploads second file using an OutputStream ------------------

/*
            File secondLocalFile = new File("E:/Test/Report.doc");
            String secondRemoteFile = "test/Report.doc";
            inputStream = new FileInputStream(secondLocalFile);

            System.out.println("Start uploading second file");
            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
            byte[] bytesIn = new byte[4096];
            int read = 0;

            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }

            inputStream.close();
            outputStream.close();

            boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                System.out.println("The second file is uploaded successfully.");
            }
*/

        } catch (IOException ex) {
            System.out.println("\n[Erro] " + ex.getMessage() + "\n");

        } finally {

            try {

                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }

            } catch (IOException ex) {
                System.out.println("\nErro ao desconectar ftpClient: " + ex.getMessage() + "\n");
            }
        }
    }
}