package core;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static core.relarorios.GeradorReportHTML.logPrintFail;

public class BasePage {

	public static Properties properties = new Properties();

	WebDriver driver = DriverFactory.getDriver();

	public BasePage() {
		try {
			properties.load(BaseTest.class.getClassLoader().getResourceAsStream("config.properties"));

		} catch (IOException e) {
			System.out.println("Erro ao carregar arquivo de properties: " + e.getMessage());
		}
	}

	public void clicaBotao(String id) {
		clicaBotao(By.xpath("//*[@id='" + id + "']"));
	}

	public void clicaBotao(By by) {
		driver.findElement(by).click();
	}

	public String obtemValorTexto(String id) {
		return obtemValorTexto(By.xpath("//*[@id='" + id + "']"));
	}

	public String obtemValorTexto(By by) {
		return driver.findElement(by).getText();
	}

	public void informaTexto(By by, String texto) {
		driver.findElement(by).sendKeys(texto);
	}

	public void informaTexto(String id, String texto) {
		informaTexto(By.xpath("//*[@id='" + id + "']"), texto);
	}

	public void informaTexto(WebElement elemento, String texto) {
		elemento.sendKeys(texto);
	}

	public void informaTexto(String id, Keys key) {
		driver.findElement(By.xpath("//*[@id='" + id + "']")).sendKeys(key);
	}

	public void clicaElemento(By by) {
		driver.findElement(by).click();
	}

	public void clicaElemento(String id) {
		clicaElemento(By.xpath("//*[@id='" + id + "']"));
	}

	public void limpaElemento(By by) {
		driver.findElement(by).clear();
	}

	public void limpaElemento(String id) {
		limpaElemento(By.xpath("//*[@id='" + id + "']"));
	}

	// RADIOS
	public void clicarRadio(By by) {
		driver.findElement(by).click();
	}

	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}

	public boolean verificaRadioClicado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	// Selecionar combos
	public void selecionaCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}

	// ALERTS
	public String obtemTextoAlert() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public String obtemTextoEAceitaAlert() {
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		alert.accept();
		return texto;
	}

	public void escreveAlertPrompt(String valor) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}

	// FRAMES
	public void entrarFrame(String id) {
		driver.switchTo().frame(id);
	}

	public void sairFrame() {
		driver.switchTo().defaultContent();
	}

	public void trocaJanela(String id) {
		driver.switchTo().window(id);
	}

	// Java Script
	public Object executaJavaScript(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
	}

	// TABELAS

	public WebElement obterCelula(String colunaBusca, String valor, String colunaBotao, String idTabela) {

		// Procurar coluna do registro
		WebElement tabela = driver.findElement(By.xpath("//*[@id='" + idTabela + "']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);

		// Encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);

		// Procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

		// Clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr[" + idLinha + "]/td[" + idColunaBotao + "]"));
		return celula;
	}

	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		WebElement celula = obterCelula(colunaBusca, valor, colunaBotao, idTabela);
		celula.findElement(By.xpath(".//input")).click();
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + idColuna + "]"));
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equals(valor)) {
				idLinha = i + 1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equals(coluna)) {
				idColuna = i + 1;
				break;
			}
		}
		return idColuna;
	}

	// FUNCOES DE AGUARDAR
	public WebElement aguardaElemento(By by, int tempoEmSegundos) {
		WebElement webElement = null;
		WebElement webElements = driver.findElement(by);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(tempoEmSegundos, TimeUnit.SECONDS)
				.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class).ignoring(StaleElementReferenceException.class);

		try {
			webElement = wait.until(ExpectedConditions.visibilityOf(webElements));

		} catch (TimeoutException e) {
			return null;
		}
		return webElement;
	}

	protected WebElement aguardaElemento(WebElement elemento, int tempoEmSegundos) {
		WebElement webElement = null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(tempoEmSegundos, TimeUnit.SECONDS)
				.pollingEvery(200, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class).ignoring(StaleElementReferenceException.class);

		try {
			webElement = wait.until(ExpectedConditions.visibilityOf(elemento));

		} catch (TimeoutException e) {
			return null;
		}
		return webElement;
	}

	public void aguardarSegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);

		} catch (InterruptedException e) {
			System.out.println("Erro ao aguardar segundo: " + e.getMessage());
		}
	}
	
	public void escrever(String texto, String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).sendKeys(texto);

		} catch (Exception e) {
			logPrintFail("Erro ao encontrar elemento na tela! " + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	public boolean elementoPresente(String elemento) {
		aguardarSegundos(2);
		if (!driver.findElements(By.xpath(elemento)).isEmpty()) {
			return true;			
		}
		return false;
	}

	public void escrever(String texto, WebElement elementoTela) {
		try {
			elementoTela.sendKeys(texto);

		} catch (Exception e) {
			logPrintFail("Erro ao encontrar elemento na tela! " + e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	public void escrever(Keys key, WebElement elementoTela) {
		try {
			elementoTela.sendKeys(key);

		} catch (Exception e) {
			logPrintFail("Erro ao encontrar elemento na tela! " + e.getMessage());
			Assert.assertTrue(false);
		}
	}
}