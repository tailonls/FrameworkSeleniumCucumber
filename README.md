# FrameworkSeleniumCucumber

Framework Selenium para automação de testes (Maven + Java + JUnit + Cucumber)

Para executar os testes basta rodar as classes dentro do diretório \src\test\java\runners, essa classe irá executar os testes escritos em BDD nos arquivos de Features contidos no diretório src\test\resources\features.

Ao final do teste será gerado um relatório contendo os prints da imagem de cada passo executado do teste, abra ele utilizando o browser.

**Obs.:** Para retirar a dependencia do BaseTest do FuncionalidadePage é necessário extender o BaseTest no PageElementPage, mas para isso será preciso retirar os Hooks do BaseTest (@Before e @ After do cucumber.api.java).

Para criar um novo teste (Necessário ter o mínimo de conhecimento em Selenium, Cucumber, Junit e Java):

- Crie um novo arquivo .feature com o nome da funcionalidade que deseja testar:
	Ex: EditarCadastro.feature

- Escreva os steps do teste no padrão BDD utilizando a linguagem Gherkin:

	Ex: 
		Cenario: Testar pesqusia no google
			Dado que acesso o site google
			E pesquiso pela termo "Assistir filme online"
			E clico no botao "Pesquisar"
			Entao deve carregar a pagina com os resultados da pesqusia
		
- Execute esse arquivo clicando no botão direito -> run as... -> arquivo feature 
- Ao executar deve aparecer na tela do console as implementações desses steps

	Ex:  
	@Dado("^que acesso o site google$")
	public void que_acesso_o_site_google() throws Throwable {
    	// Write code here that turns the phrase above into concrete actions
    	throw new PendingException();
	}

- Crie uma classe de Step na pasta Step do projeto com o nome da sua funcionalidade, nessa classe serão colocados os métodos que implementam os stesp escritos no arquivo .feature:
	Ex: PesquisaGoogleStep.java
	
- Cole as implementações dos steps que apareceram no console
- Apague as linhas  que começam com "//Write code here..." e "throw new..." elas não serão necessarias
- Apague tambem os "throws Throwable" que aparecem ao lado dos métodos, tambem não serão necessarias

- Crie uma classe de Page na pasta Page do projeto com o nome da sua funcionalidade, nessa classe serão colocados os métodos que são usados somente na funcionalidade  ser testada:
	Ex: PesquisaGooglePage.java
	
- Crie uma classe de ElementMap na pasta Page do projeto com o nome da sua funcionalidade, nessa classe serão mapeados os elementos presentes na tela que deseja testar:
	Ex: PesquisaGooglePageElementMap.java
	
- Extenda a classe BasePage à essa classe
- Extenda a classe ElementMap à classe Page
- Estancie a classe Page na classe Step

- Agora basta implementar cada método que fara o teste na classe page utilizando os elementos mapeados na classe ElementMap e utilizar esses métodos na classe Step.
