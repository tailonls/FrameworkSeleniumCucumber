package core;

import static core.DriverFactory.killDriver;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class BaseTest extends GeradorReportHTML {

	@BeforeClass
	public static void iniciarTestes() throws IOException {
		inicializarReportHTML();
		
		// TODO: crair report PDF
	}

	@Before
	public static void iniciarCenario(Scenario cenario) throws IOException {
		addCenarioReportHTML(cenario);
	}

	@After
	public void finalizarCenario(Scenario cenario) {
		if (cenario.isFailed()) {
			logFail("O cenario falhou!");
		}

		if (Propriedades.FECHAR_BROWSER) {
			DriverFactory.killDriver();
		}
	}

	@AfterClass
	public static void finalizarTestes() throws IOException {
		atualizaReportHTML();
		killDriver();
	}
}