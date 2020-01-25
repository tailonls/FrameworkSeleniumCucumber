package runners;

import core.BaseTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features/",
        glue = {"steps", "core"},
        tags = "@teste",
        plugin = {"pretty", "rerun:target/rerun.txt"},
        snippets = SnippetType.CAMELCASE
)
public class RunnerTest extends BaseTest {

    // Necessário colocar o core no Glue para identificar o @After do cucumber
}