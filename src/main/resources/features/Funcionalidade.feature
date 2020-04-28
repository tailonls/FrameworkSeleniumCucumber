#language: pt
Funcionalidade: Pesquisa no Github
  
  # Como um usuario da internet
  # Gostaria de acessar o Github
  # Para realizar pesquisas

  @teste
  Cenario: Realizar uma pesquisa no Github
    Dado que acesso o site do github
    Entao a pagina inicial deve carregar
    Quando pesquiso pelo termo "teste"
    Entao deve carregar a pagina com resultados da pesquisa

  @api @teste
  Cenario: Testar requisicao API rest
    Dado url "https://jsonplaceholder.typicode.com"
    Dado endpont "/todos/"
    Entao o status do retorno deve ser 200
