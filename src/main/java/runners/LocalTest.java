package runners;

import core.BaseTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags = "@teste", glue = {"steps", "core"},
        snippets = CucumberOptions.SnippetType.CAMELCASE, plugin = {"pretty", "rerun:target/rerun.txt"})
public class LocalTest extends BaseTest {

    // Classe para testes locais durante a implementacao de novos cenarios
}