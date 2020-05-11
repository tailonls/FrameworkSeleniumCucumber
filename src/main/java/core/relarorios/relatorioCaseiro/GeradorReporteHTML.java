package br.com.unicred.caixa.core.report;

import br.com.unicred.caixa.core.WiniumFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static br.com.unicred.caixa.core.Propriedades.RODANDO_SUITE_COMPLETA;
import static br.com.unicred.caixa.core.report.TagsReporteHTML.*;
import static br.com.unicred.caixa.utils.DateUtil.formatDate;

public class GeradorReporteHTML extends GeradorEvidenciaPDF {

    private static FileWriter arquivo = null;
    private static PrintWriter gravarArquivo = null;

    private static boolean cenarioPassou = true;
    private static int contadorCenarios = 0;
    private static int qntPass = 0;
    private static int qntFail = 0;
    private static String PATH = "target/report_HTML/";
    private static String PATH_IMAGENS = "Screenshot/";

    private static LocalDate dataLocal = LocalDate.now();

    public static void inicializaReportHTML(String nomeCenario) {
        if (contadorCenarios == 0)
            apagaArquivosHTMLAntigos();

        criaNovoReportHTML(nomeCenario);
        abreTAGReportHTML();
    }

    public static void finalizaReportHTML() {
        if (RODANDO_SUITE_COMPLETA) {
            addDadosTesteReportHTML();
        }
        fechaTAGReportHTML();

        try {
            if (arquivo != null)
                arquivo.close();

        } catch (Exception e) {
            logConsole("Erro ao fechar arquivo FileWriter! " + e.getMessage());
        }
        logConsole("Finalizando Report HTML!");
    }

    public static void addDadosTesteReportHTML() {
        double ctsPassados = qntPass > 0 ? (double) qntPass / contadorCenarios * 100 : 0;
        double ctsFalhados = qntFail > 0 ? (double) qntFail / contadorCenarios * 100 : 0;

        escreveNoReportHTML(
                TAG_DADOS_TESTES(
                        String.valueOf(Math.round(ctsPassados)),
                        String.valueOf(Math.round(ctsFalhados)),
                        String.valueOf(qntPass),
                        String.valueOf(qntFail),
                        String.valueOf(contadorCenarios)
                )
        );
    }

    public static void addNovoCenarioReportHTML(String nomeCenario) {
        String colapsar = RODANDO_SUITE_COMPLETA ? "class='collapse'" : "";
        contadorCenarios++;
        cenarioPassou = true;

        escreveNoReportHTML(
                TAG_ABRIR_CENARIO(nomeCenario, colapsar, getHora(), String.valueOf(contadorCenarios))
        );

        gerarDocumentoPDF(nomeCenario);
        addTextoDocumentoPDF("Cenario: " + nomeCenario);
        addTextoDocumentoPDF("Executado em: " + formatDate(dataLocal));
        addTextoDocumentoPDF("___________________________________________________\n");

        logConsole("Iniciando cenario: [" + nomeCenario + "]");
    }

    public static void fecharCenarioReportHTML() {
        String cor = cenarioPassou ? "23a342" : "da1b20";
        String mensagem = cenarioPassou ? "O TEST CASE PASSOU!" : "O TEST CASE FALHOU!";
        String classe = cenarioPassou ? "footer_passed" : "footer_failed";

        if (cenarioPassou)
            qntPass++;
        else
            qntFail++;

        escreveNoReportHTML(TAG_FECHAR_CENARIO(cor, mensagem, classe, String.valueOf(contadorCenarios)));

        encerraDocumentoPDF();
    }

    private static void abreTAGReportHTML() {
        escreveNoReportHTML(ABRE_TAG_REPORTE());
    }

    public static void addSecaoHTML(String secao) {
        escreveNoReportHTML(TAG_SECAO(secao));
        logConsole(secao);
    }

    public static void logPass(String log) {
        escreveNoReportHTML(TAG_LOG(log, getHora(), "OK"));

        addTextoDocumentoPDF(log);
        logConsole(log);
    }

    public static void logPrintPass(String log) {
        String pathImagem = getScreenshot();
        escreveNoReportHTML(TAG_LOG_IMAGEM(log, getHora(), pathImagem, "OK"));

        addTextoDocumentoPDF(log);
        addImagemDocumentoPDF(pathImagem);

        logConsole(log);
    }

    public static void logFail(String log) {
        cenarioPassou = false;
        escreveNoReportHTML(TAG_LOG(log, getHora(), "FAIL"));

        addTextoDocumentoPDF(log);
        logConsole(log);
    }

    public static void logPrintFail(String log) {
        cenarioPassou = false;

        String pathImagem = getScreenshot();
        escreveNoReportHTML(TAG_LOG_IMAGEM(log, getHora(), pathImagem, "FAIL"));

        addTextoDocumentoPDF(log, "FAIL");
        addImagemDocumentoPDF(pathImagem);
        logConsole(log);
    }

    public static void logWarning(String log, boolean passou) {
        cenarioPassou = passou;
        escreveNoReportHTML(TAG_LOG(log, getHora(), "WARNING"));

        addTextoDocumentoPDF(log);
        logConsole(log);
    }

    public static void logPrintWarning(String log, boolean passou) {
        cenarioPassou = passou;

        String pathImagem = getScreenshot();
        escreveNoReportHTML(TAG_LOG_IMAGEM(log, getHora(), pathImagem, "WARNING"));

        addTextoDocumentoPDF(log, "WARNING");
        addImagemDocumentoPDF(pathImagem);
        logConsole(log);
    }

    public static void logInfo(String log, boolean passou) {
        cenarioPassou = passou;
        escreveNoReportHTML(TAG_LOG(log, getHora(), "INFO"));

        addTextoDocumentoPDF(log);
        logConsole(log);
    }

    public static void logPrinInfo(String log, boolean passou) {
        cenarioPassou = passou;

        String pathImagem = getScreenshot();
        escreveNoReportHTML(TAG_LOG_IMAGEM(log, getHora(), pathImagem, "INFO"));

        addTextoDocumentoPDF(log, "INFO");
        addImagemDocumentoPDF(pathImagem);
        logConsole(log);
    }


    private static void fechaTAGReportHTML() {
        escreveNoReportHTML(TAG_FECHA_REPORTE());
    }

    private static String getHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date hora = new Date();
        return sdf.format(hora).replace(":", "h") + "m";
    }

    private static String getScreenshot() {
        File src = ((TakesScreenshot) WiniumFactory.getWiniumDriver()).getScreenshotAs(OutputType.FILE);
        String PATH_TEMPORARIO = PATH_IMAGENS + System.currentTimeMillis() + ".png";
        File destination = new File(PATH + PATH_TEMPORARIO);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PATH_TEMPORARIO;
    }

    private static void apagaArquivosHTMLAntigos() {
        logConsole("Limpando diretorio de evidencias...");

        File screenshots = new File(PATH + PATH_IMAGENS); // Apagando pasta com imagens de testes anteriores
        if (screenshots.exists() && screenshots.isDirectory()) {
            File[] files = screenshots.listFiles();
            for (File fileToDelete : files)
                fileToDelete.delete();
            screenshots.delete();
        }

        File reportes = new File(PATH); // Apagando pasta com html de testes anteriores
        if (reportes.exists() && reportes.isDirectory()) {
            File[] files = reportes.listFiles();
            for (File fileToDelete : files)
                fileToDelete.delete();
            reportes.delete();
        }
    }

    private static void criaNovoReportHTML(String nomeReport) {
        try {
            FileUtils.forceMkdir(new File(PATH));
            FileUtils.forceMkdir(new File(PATH + PATH_IMAGENS));

            arquivo = new FileWriter(PATH + nomeReport + ".html");
            gravarArquivo = new PrintWriter(arquivo);

        } catch (Exception e) {
            logConsole("Erro ao criar report HTML com o nome " + nomeReport + "! " + e.getMessage());
        }
    }

    private static void escreveNoReportHTML(String texto) {
        if (gravarArquivo != null)
            gravarArquivo.println(texto);
    }

    public static void logConsole(String log) {
        System.out.println("\n" + log.replace("<b>", "").replace("</b>", "").replace("<br />", "\n") + "\n");
    }
}