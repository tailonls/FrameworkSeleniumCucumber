#language: pt
Funcionalidade: Pesquisa no Github
  
  Como um usuario da internet
  Gostaria de acessar o Github
  Para realizar pesquisas

  @teste
  Cenario: Realizar uma pesquisa no Github
    Dado que acesso o site "https://github.com/"
    Entao a pagina inicial deve carregar
    Quando pesquiso pelo termo "teste"
    Entao deve carregar a pagina com resultados da pesquisa 
