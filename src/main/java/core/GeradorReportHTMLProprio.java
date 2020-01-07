package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.core.api.Scenario;

import static core.DriverFactory.getDriver;

public class GeradorReportHTMLProprio {

	private static FileWriter arquivo;
	private static PrintWriter gravarArq;

	private static final String PATH_REPORT = "target/ReportHTML/";
	private static final String PATH_IMAGENS = "Screenshot/";

	public static void inicializarReportHTML(String nomeCenario) {

		try {
			arquivo = new FileWriter(PATH_REPORT + nomeCenario + ".html");
			gravarArq = new PrintWriter(arquivo);

		} catch (IOException e) {
			System.out.println("Falha ao inicializar Report! " + e.getMessage());
		}
	}

	public static void addCenarioReportHTML(Scenario cenario) {
		gravarArq.printf("");
	}

	public static void logPass(String log) {
		gravarArq.printf("");
	}

	public static void logPrintPass(String log) {
		gravarArq.printf("");
	}

	public static void logFail(String log) {
		gravarArq.printf("");
	}

	public static void logPrintFail(String log) {
		gravarArq.printf("");
	}

	public static void logInfo(String log) {
		gravarArq.printf("");
	}

	public static void logAviso(String log) {
		gravarArq.printf("");
	}

	public static void logErro(String log) {
		gravarArq.printf("");
	}

	public static String getScreenshot() {
		String PATH_TEMPORARIO = "";
		try {
			PATH_TEMPORARIO = PATH_IMAGENS + System.currentTimeMillis() + ".png";
			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			File destination = new File(PATH_REPORT + PATH_TEMPORARIO);
			FileUtils.copyFile(src, destination);

		} catch (IOException e) {
			System.out.println("Screenshot falhou! " + e.getMessage());
		}
		return PATH_TEMPORARIO;
	}

	public static void finalizarReportHTML() {
		try {
			arquivo.close();
			gravarArq.close();

		} catch (IOException e) {
			System.out.println("Falha ao finalizar Report! " + e.getMessage());
		}
	}
}