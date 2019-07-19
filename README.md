# FrameworkSeleniumCucumber

Framework Selenium para automação de testes (Maven + Java + JUnit + Cucumber)

Para executar os testes basta rodar as classes dentro do diretório \src\test\java\runners, essa classe irá executar os testes escritos em BDD nos arquivos de Features contidos no diretório src\test\resources\features.

Ao final do teste será gerado um relatório contendo os prints da imagem de cada passo executado do teste, abra ele utilizando o browser.

Obs.: Para retirar a dependencia do BaseTest do FuncionalidadePage é necessário extender o BaseTest no PageElementPage, mas para isso será preciso retirar os Hooks do BaseTest (@Before e @ After do cucumber.api.java).


