package pages;

import static core.GeradorReportHTML.logFail;
import static core.GeradorReportHTML.logPass;
import static core.GeradorReportHTML.logPrintFail;
import static core.GeradorReportHTML.logPrintPaginaInteira;
import static core.GeradorReportHTML.logPrintPass;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import core.DriverFactory;

public class FuncionalidadePage extends FuncionalidadePageElementMap {

	WebDriver driver = DriverFactory.getDriver();

	public FuncionalidadePage() {
		// Inicializar elementos da classe ElementMap
		PageFactory.initElements(driver, this);
	}

	public void acessarPaginaInicial(String pagina) {
		DriverFactory.getDriver().get(pagina);
		aguardarSegundos(3);
		logPrintPass("Acessou página " + pagina);
	}

	public boolean deveCarregarPaginaInicial() {
		aguardarSegundos(1);
		if (aguardaElemento(By.xpath(DIV_PAGINA_INICIAL), 2) != null) {
			logPrintPass("Pagina inicial carregou com sucesso!");
			return true;
		}

		logPrintFail("Pagina inicial NAO apareceu!");
		return false;
	}

	public void pesquisarTermo(String termo) {
		aguardarSegundos(1);

		WebElement campoPesquisa = aguardaElemento(By.xpath(CAMPO_PESQUISA), 3);

		if (campoPesquisa != null) {
			escrever(termo, campoPesquisa);
			logPrintPass("Informou o termo [" + termo + "] no campo de pesquisa!");
			escrever(Keys.ENTER, campoPesquisa);

		} else {
			logPrintFail("NAO encontrou o campo de pesquisa!");
			Assert.fail();
		}
	}

	public boolean deveCarregarPaginaComResultados() {
		aguardarSegundos(1);
		List<WebElement> resultados = driver.findElements(By.xpath(RESULTADOS_PESQUISA));

		if (resultados != null) {
			logPrintPaginaInteira("Pagina carregou com sucesso mostrando " + resultados.size() + " resultados!");
			return true;
		}

		logPrintFail("Pagina com resultados NAO apareceu!");
		return false;
	}

	// PARA USO FUTURO
	public boolean validandoVariosElementos() {
		AtomicBoolean validar = new AtomicBoolean();

		validar.set(
				comparaValores("casa", "casa") && 
				comparaValores("casa", "CASA") && 
				comparaValores("casa", "caza"));

		if (validar.get())
			logPass("Valores estavam de acordo com o esperado!");

		return validar.get();
	}

	public boolean comparaValores(String valorEsperado, String valorObtido) {
		boolean valida = valorEsperado.equalsIgnoreCase(valorObtido);
		if (!valida)
			logFail("Erro: valor esperado: [" + valorEsperado + "] valor recebido: [" + valorObtido + "]!");

		return valida;
	}
}