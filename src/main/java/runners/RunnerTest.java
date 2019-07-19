package runners;

import org.junit.runner.RunWith;

import core.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/features/", glue = { "steps", "core" }, tags = { "@teste" }, plugin = {
		"pretty" })
public class RunnerTest extends BaseTest {

	// Necessário colocar o core no Glue para identificar o @After do cucumber


}