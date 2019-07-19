package core;

import static core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class BaseTest {

	static ExtentReports extent;
	static ExtentTest logger;
	static ExtentHtmlReporter reporter;

	@BeforeClass
	public static void iniciarTestes() throws IOException {
		new File("target/Reports").mkdir();
		new File("target/Screenshot").mkdir();

		reporter = new ExtentHtmlReporter("target/Reports/Relatorios_testes.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@Before
	public static void iniciarCenario(Scenario cenario) throws IOException {
		logger = extent.createTest(cenario.getName());
		extent.flush();
	}

	@After
	public void finalizarCenario(Scenario cenario) {
		if (cenario.isFailed()) {
			logFail("O cenário falhou!");
		}

		if (Propriedades.FECHAR_BROWSER) {
			DriverFactory.killDriver();
		}
	}

	@AfterClass
	public static void finalizarTestes() throws IOException {
		extent.flush();
		killDriver();
	}

	public void logPass(String log) {
		try {
			String temp = getScreenshot();
			logger.pass(log, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			extent.flush();

		} catch (IOException e) {
			logFail("Capture Failed " + e.getMessage());
		}
	}

	public static void logFail(String log) {
		try {
			String temp = getScreenshot();
			logger.fail(log, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			extent.flush();

		} catch (IOException e) {
			logFail("Capture Failed " + e.getMessage());
		}
	}

	public static void logInfo(String log) {
		logger.info(log);
		extent.flush();
	}

	public static String getScreenshot() {
		File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/target/Screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			logFail("Capture Failed " + e.getMessage());
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
}