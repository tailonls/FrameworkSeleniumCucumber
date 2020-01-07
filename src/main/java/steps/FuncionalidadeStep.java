package steps;

import org.junit.Assert;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.FuncionalidadePage;

public class FuncionalidadeStep {

	FuncionalidadePage page = new FuncionalidadePage();

	@Dado("que acesso o site {string}")
	public void queAcessoSite(String site) {
		page.acessarPaginaInicial(site);
	}

	@Entao("a pagina inicial deve carregar")
	public void paginaInicialDeveCarregar() {
		Assert.assertTrue("Pagina inicial nao carregou!", page.deveCarregarPaginaInicial());
	}

	@Quando("pesquiso pelo termo {string}")
	public void pesquisoPeloTermo(String termo) {
		page.pesquisarTermo(termo);
	}

	@Entao("deve carregar a pagina com resultados da pesquisa")
	public void deveCarregarPaginaComResultados() {
		Assert.assertTrue("Nao carregou pagina com resultados!", page.deveCarregarPaginaComResultados());
	}

	@Dado("url {string}")
	public void url(String url) {
		page.setarURL(url);
	}

	@Dado("endpont {string}")
	public void endpont(String endpont) {
		page.setarEndpoint(endpont);
	}

	@Entao("o status do retorno deve ser {int}")
	public void statusRetornoDeveSer(int statusEsperado) {
		Assert.assertTrue("Retono diferente do esperado!", page.validaStatusRetorno(statusEsperado));
	}
}