package core;

import static utils.DataUtils.converterData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

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

	static ExtentReports extent;
	static ExtentTest logger;
	static ExtentHtmlReporter reporter;

	private static LocalDateTime dataLocal = LocalDateTime.now();
	private static String PATH = "target/Reports/";

	public static void inicializarReportHTML() throws IOException {
		// TODO: como fiz no projeto do caixa?

		new File(PATH).mkdir();
		new File("target/Screenshot").mkdir();

		reporter = new ExtentHtmlReporter(PATH + "RelatorioTestes.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	public static void addCenarioReportHTML(Scenario cenario) throws IOException {
		logger = extent.createTest(cenario.getName());
		logger.assignAuthor("Tailon Saraiva");
		addCategoriaReport(cenario.getName());

		atualizaReportHTML();

		inicializarReportPDF(cenario.getName());
		addParagrafoReportPDF("Cenario: " + cenario.getName());
		addParagrafoReportPDF("Executado em: " + converterData(dataLocal));
		addParagrafoReportPDF("_____________________________________________________________");
		addParagrafoReportPDF("\n");
	}

	public static void atualizaReportHTML() {
		extent.flush();
	}

	public static void logPass(String log) {
		logger.pass(log);
		atualizaReportHTML();

		addParagrafoReportPDF(log);
	}

	public static void logPrintPass(String log) {
		try {
			String temp = getScreenshot();
			logger.pass(log, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			atualizaReportHTML();

			addParagrafoReportPDF(log);
			addImagemReportPDF(temp);

		} catch (IOException e) {
			logFail("Capture Failed " + e.getMessage());
		}
	}

	public static void logFail(String log) {
		logger.fail(log);
		atualizaReportHTML();

		addParagrafoReportPDF(log);
	}

	public static void logPrintFail(String log) {
		try {
			String temp = getScreenshot();
			logger.fail(log, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			atualizaReportHTML();

			addParagrafoReportPDF(log);
			addImagemReportPDF(temp);

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

		addParagrafoReportPDF(log);
	}

	public static void logErro(String log) {
		logger.error(log);
		atualizaReportHTML();

		addParagrafoReportPDF(log);
	}
	
	public static void logPrintPaginaInteira(String log) {
		try {
			String temp = getScreenshotAllPage();
			logger.pass(log, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			atualizaReportHTML();

			addParagrafoReportPDF(log);
			addImagemReportPDF(temp);

		} catch (IOException e) {
			logFail("Capture Failed " + e.getMessage());
		}
	}

	public static String getScreenshot() {
		File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/target/Screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}
	

	public static String getScreenshotAllPage() throws IOException {
		String path = System.getProperty("user.dir") + "/target/Screenshot/" + System.currentTimeMillis() + ".png";

		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(DriverFactory.getDriver());

		ImageIO.write(screenshot.getImage(), "PNG", new File(path));

		return path;
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