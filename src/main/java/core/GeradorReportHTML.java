package core;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.Scenario;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class GeradorReportHTML extends GeradorReportPDF {

	static ExtentReports extensao = null;
	static ExtentTest logger = null;
	static ExtentHtmlReporter relatorio = null;
	
	private static String PATH_REPORT = "target/ReportHTML/";
	private static String PATH_IMAGENS = "Screenshot/";

	public static void inicializarReportHTML() throws IOException {

		if (relatorio == null && extensao == null) {
			new File(PATH_REPORT).mkdir();
			new File(PATH_REPORT + PATH_IMAGENS).mkdir();

			relatorio = new ExtentHtmlReporter(PATH_REPORT + "RelatorioTestes.html");
			extensao = new ExtentReports();
			extensao.attachReporter(relatorio);
		}
	}

	public static void addCenarioReportHTML(Scenario cenario) throws IOException {
		
		if (relatorio != null && extensao != null) {
			
			logger = extensao.createTest(cenario.getName());
			logger.assignAuthor("Tailon Saraiva");
			addCategoriaReport(cenario.getName());
	
			atualizaReportHTML();
	
			criaNovoReportPDF(cenario.getName());
		}
	}

	public static void atualizaReportHTML() {
		extensao.flush();
	}

	public static void logPass(String log) {
		logger.pass(log);
		atualizaReportHTML();

		addParagrafoReportPDF("PASSOU: " + log);
	}

	public static void logPrintPass(String log) {
		
		try {
			String temp = getScreenshot();
			logger.pass(log, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			atualizaReportHTML();

			addParagrafoReportPDF("PASSOU: " + log);
			addImagemReportPDF(PATH_REPORT + temp);

		} catch (IOException e) {
			logFail("Capture Failed " + e.getMessage());
		}
	}

	public static void logFail(String log) {
		logger.fail(log);
		atualizaReportHTML();

		addParagrafoReportPDF("FALHOU: " + log);
	}

	public static void logPrintFail(String log) {
		try {
			String temp = getScreenshot();
			logger.fail(log, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			atualizaReportHTML();

			addParagrafoReportPDF("FALHOU: " + log);
			addImagemReportPDF(PATH_REPORT + temp);

		} catch (IOException e) {
			logFail("Capture Failed " + e.getMessage());
		}
	}

	public static void logInfo(String log) {
		logger.info(log);
		atualizaReportHTML();

		addParagrafoReportPDF(log);
	}

	public static void logAviso(String log) {
		logger.warning(log);
		atualizaReportHTML();

		addParagrafoReportPDF("AVISO:" + log);
	}

	public static void logErro(String log) {
		logger.error(log);
		atualizaReportHTML();

		addParagrafoReportPDF("ERRO: " + log);
	}
	
	public static void logPrintPaginaInteira(String log) {
		try {
			String temp = getScreenshotAllPage();
			logger.pass(log, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			atualizaReportHTML();

			addParagrafoReportPDF(log);
			addImagemPaginaInteiraReportPDF(PATH_REPORT + temp);

		} catch (IOException e) {
			logFail("Capture Failed " + e.getMessage());
		}
	}

	public static String getScreenshot() {
		File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

		String PATH_TEMPORARIO = PATH_IMAGENS + System.currentTimeMillis() + ".png";
		File destination = new File(PATH_REPORT + PATH_TEMPORARIO);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return PATH_TEMPORARIO;
	}
	

	public static String getScreenshotAllPage() throws IOException {
		String PATH_TEMPORARIO = PATH_IMAGENS + System.currentTimeMillis() + ".png";

		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(DriverFactory.getDriver());

		ImageIO.write(screenshot.getImage(), "PNG", new File(PATH_REPORT + PATH_TEMPORARIO));

		return PATH_TEMPORARIO;
	}

	public static void addCategoriaReport(String cenario) {
		
		if (cenario.toUpperCase().contains("API") || cenario.toUpperCase().contains("SERVICO") || cenario.toUpperCase().contains("REST"))
			logger.assignCategory("API");

		else
			logger.assignCategory("TELA");

		if (cenario.toUpperCase().contains("SMOKE"))
			logger.assignCategory("SMOKE");

		else
			logger.assignCategory("FUNCIONAL");

	}
}