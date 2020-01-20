package core;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import core.Propriedades.TipoExecucao;

public class DriverFactoryThread {
	
	// USAR ESSA FACTORY QUANDO RODAR TESTES EM PARALELO NO SELENIUM GRID

	private static final String ENDERECO_HUB = "http://196.168.0.184:/wd/hub";

	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};

	private DriverFactoryThread() {
	}

	public static WebDriver getDriver() {
		return threadDriver.get();
	}

	public static WebDriver initDriver() {
		WebDriver driver = null;

		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.LOCAL) {
			switch (Propriedades.BROWSER) {

				case FIREFOX:
					driver = new FirefoxDriver();
					break;
				case CHROME:
					driver = new ChromeDriver();
					break;
				default:
					Assert.fail("Brownser nao encontrado!");
					break;
			}
		}

		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.GRID) {

			DesiredCapabilities cap = null;
			switch (Propriedades.BROWSER) {
				case FIREFOX:
					cap = DesiredCapabilities.firefox();
					break;
				case CHROME:
					cap = DesiredCapabilities.chrome();
					break;
				default:
					Assert.fail("Brownser nao encontrado!");
					break;
			}

			try {
				driver = new RemoteWebDriver(new URL(ENDERECO_HUB), cap);

			} catch (MalformedURLException e) {
				System.err.println("Falha na conexão com o GRID");
				e.printStackTrace();
			}
		}
		driver.manage().window().setSize(new Dimension(1200, 765));
		return driver;
	}

	public static void killDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
		}
		if (threadDriver != null) {
			threadDriver.remove();
		}
	}
}