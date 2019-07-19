package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.BaseTest;
import core.DriverFactory;

public class FuncionalidadePage extends FuncionalidadePageElementMap {

	WebDriver driver = DriverFactory.getDriver();
	BaseTest base = new BaseTest();

	public FuncionalidadePage() {
		PageFactory.initElements(driver, this);
	}

	public void acessarPaginaInicial() {
		DriverFactory.getDriver().get("https://www.google.com.br/");
		aguardarSegundos(3);
		base.logPass("Acessou página https://www.google.com.br/");
	}

	public void informarTermoPesquisa(String termoPesquisa) {
		aguardaElemento(campoPesquisarGoogle, 3);
		campoPesquisarGoogle.sendKeys(termoPesquisa);
		campoPesquisarGoogle.sendKeys(Keys.TAB);
		base.logPass("Informou o termo de pesquisa [" + termoPesquisa + "]");
	}

	public void clicarBotaoPesquisa(String botao) {
		botaoPesquisarGoogle.click();
		base.logPass("Clicou no botão de pesquisar");
	}

	public boolean validaCarregametoPaginaPesquisa(String tipoPagina) {
		aguardarSegundos(3);
		String elementoPesquisa = "";

		switch (tipoPagina) {
		case "Todas":
			elementoPesquisa = "//div[@class='g']";
			break;

		case "Imagens":
			elementoPesquisa = "//div[@class='rg_bx rg_di rg_el ivg-i']";
			break;

		case "Maps":
			elementoPesquisa = "//div[@class='section-result']";
			break;

		case "Vídeos":
			elementoPesquisa = "//div[@class='rc']";
			break;

		case "Notícias":
			elementoPesquisa = "//div[@class='g']";
			break;

		case "Mais":
			elementoPesquisa = "";
			// Chamar metodo para selecionar opção dentro de select
			break;
		}

		if (driver.findElement(By.xpath(elementoPesquisa)) != null) {
			base.logPass("Página de pesquisa retornou resultados!");
			return true;
		}
		return false;
	}

	public void clicarAbaPesquisa(String aba) {
		String xpath = "//a[text()='" + aba + "']";

		aguardaElemento(By.xpath(xpath), 3);
		driver.findElement(By.xpath(xpath)).click();

		base.logPass("Cliou na aba [" + aba + "]");
	}
}