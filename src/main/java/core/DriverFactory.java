package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static WebDriver driver;

	private DriverFactory() {
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			switch (Propriedades.browser) {
			case FIREFOX:
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				break;

			case CHROME:
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");

				driver = new ChromeDriver();
				break;

			case CHROME_HEADLESS: // Funciona a partir da versão 60 do chrome
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
				driver = new ChromeDriver(options);
				break;

			default:
				System.out.println("Driver nao enontrado!");
				break;
			}
		}
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
		return driver;
	}
}