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

	@Quando("^clico no botao Pesquisa Google$")
	public void clicoNoBotao() {
		funcionalidade.clicarBotaoPesquisa();
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

	@Dado("^cliquei no link do gmail$")
	public void cliqueiNoLinkGmail() {

	}

	@Quando("^preencho o email$")
	public void preenchoEmail() {

	}

	@Quando("^clico em \"([^\"]*)\"$")
	public void clicoEm(String opacao) {

	}

	@Quando("^informo a senha$")
	public void informoSenha() {

	}

	@Entao("^o login deve ser realizado com sucesso$")
	public void loginDeveSerRealizadoComSucesso() {

	}

	@Quando("^clico no icone do usuario logado$")
	public void quandoClicoNoIconeDoUsuarioLogado() {

	}

	@Entao("^deve aparecer um card com o email do usuario logado$")
	public void deveAparecerUmCardComEmailDoUsuarioLogado() {

	}
}