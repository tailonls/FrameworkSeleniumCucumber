#language: pt
Funcionalidade: Pesquisa no google
  
  Como um usuario da internet
  Gostaria de acessar o Google
  Para realizar pesquisas

  @teste
  Cenario: Realizar uma pesquisa no Google
    Dado que acesso o site "https://github.com/"
    Entao a pagina inicial deve carregar
    Quando pesquiso pelo termo "teste"
    Entao deve carregar a pagina com resultados da pesquisa 
