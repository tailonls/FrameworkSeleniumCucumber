package steps;

import org.junit.Assert;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.FuncionalidadePage;

public class FuncionalidadeStep {

	private static FuncionalidadePage funcionalidade = new FuncionalidadePage();

	@Dado("^que acesso o site do google$")
	public void queAcessoSiteDoGoogle() {
		funcionalidade.acessarPaginaInicial();
	}

	@E("^informo o termo \"([^\"]*)\" no campo de pesquisa$")
	public void informoTermoNoCampoDePesquisa(String termoPesquisa) {
		funcionalidade.informarTermoPesquisa(termoPesquisa);
	}

	@Quando("^clico no botao \"([^\"]*)\"$")
	public void clicoNoBotao(String botao) {
		funcionalidade.clicarBotaoPesquisa(botao);
	}

	@Quando("^clico na aba \"([^\"]*)\"$")
	public void clicoNaAba(String aba) {
		funcionalidade.clicarAbaPesquisa(aba);
	}

	@Entao("^deve carregar a pagina de pesquisa por \"([^\"]*)\"$")
	public void deveCarregarPaginaPesquisaImagens(String tipoPagina) {
		Assert.assertTrue("Nao carregou pagina de pesquisa por [" + tipoPagina + "]",
				funcionalidade.validaCarregametoPaginaPesquisa(tipoPagina));
	}
}