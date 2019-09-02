#language: pt
Funcionalidade: Pesquisa no google
  
  Como um usuario da internet
  Gostaria de acessar o Google
  Para realizar pesquisas

  @teste
  Cenario: Realizar uma pesquisa no Google
    Dado que acesso o site do google
    E informo o termo "teste" no campo de pesquisa
    E clico no botao Pesquisa Google
    Entao deve carregar a pagina de pesquisa por "Todas"

  @teste
  Cenario: Realizar uma pesquisa no Google imagens
    Dado que acesso o site do google
    E informo o termo "teste" no campo de pesquisa
    E clico no botao Pesquisa Google
    Entao deve carregar a pagina de pesquisa por "Todas"
    Quando clico na aba "Imagens"
    Entao deve carregar a pagina de pesquisa por "Imagens"

  @teste
  Cenario: Realizar login no gmail
    Dado que acesso o site do google
    E cliquei no link do gmail
    Entao deve aparecer um label com a mensagem "Faça mais com o Gmail"
    Quando clico em Iniciar Sessao
    E preencho o email
    E clico em "Proximo"
    E informo a senha
    E clico em "Proximo"
		Entao o login deve ser realizado com sucesso
		Quando clico no icone do usuario logado
		Entao deve aparecer um card com o email do usuario logado
		

