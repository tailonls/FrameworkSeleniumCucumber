package utils;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.xfer.FileSystemFile;
import utils.config.NullHostKeyVerifier;

import java.io.File;
import java.io.IOException;

public class FTPUtil {

    // Substituir por servidor real
    private static final String SERVIDOR = "10.64.110.159";
    private static final String PORTA = "22";
    private static final String USER = "usuario";
    private static final String PASS = "password";

    private static final String PATH_PRINCIPAL_REMOTO = "/var/www/html/reportes_html";
    private static final String PATH_SCREENSHOT = "screenshots/";
    private static final String PATH_ARQUIVO_LOCAL = "target/reporte_html/";

    public static void enviarEvidenciaParaServidor(String nomeArquivo) {
        SSHClient ssh = new SSHClient();
        ssh.addHostKeyVerifier(new NullHostKeyVerifier());

        try {
            ssh.connect(SERVIDOR);
            ssh.authPassword(USER, PASS);

            File arquivoLocal = new File(PATH_ARQUIVO_LOCAL + PATH_SCREENSHOT + nomeArquivo + ".png");

            ssh.newSCPFileTransfer().upload(new FileSystemFile(arquivoLocal), PATH_PRINCIPAL_REMOTO + "/" + PATH_SCREENSHOT);
            ssh.disconnect();

        } catch (IOException ex) {
            System.out.println("[ERRO] - " + ex.getMessage());
        }
    }

}