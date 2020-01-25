package br.com.unicred.caixa.test;

import br.com.unicred.caixa.core.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags = "@teste", glue = {"br.com.unicred.caixa.steps", "br.com.unicred.caixa.core"},
        snippets = SnippetType.CAMELCASE, plugin = {"pretty", "rerun:target/rerun.txt"})
public class LocalTest extends BaseTest {

    // Classe para testes locais durante a implementacao de novos cenarios
}