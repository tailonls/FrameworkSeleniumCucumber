package steps;

import org.junit.Assert;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.FuncionalidadePage;

public class FuncionalidadeStep {

	FuncionalidadePage page = new FuncionalidadePage();

	@Dado("^que acesso o site \"([^\"]*)\"$")
	public void queAcessoSite(String site) {
		page.acessarPaginaInicial(site);
	}

	@Entao("^a pagina inicial deve carregar$")
	public void paginaInicialDeveCarregar() {
		Assert.assertTrue("Pagina inicial nao carregou!", page.deveCarregarPaginaInicial());
	}

	@Quando("^pesquiso pelo termo \"([^\"]*)\"$")
	public void pesquisoPeloTermo(String termo) {
		page.pesquisarTermo(termo);
	}

	@Entao("^deve carregar a pagina com resultados da pesquisa$")
	public void deveCarregarPaginaComResultados() {
		Assert.assertTrue("Nao carregou pagina com resultados!", page.deveCarregarPaginaComResultados());
	}
}