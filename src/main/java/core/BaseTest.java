package core;

import core.relarorios.GeradorReportHTML;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;

import static core.DriverFactory.killDriver;

public class BaseTest extends GeradorReportHTML {
	
	@BeforeClass
	public static void iniciarTestes() throws IOException {
		inicializarReportHTML();
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
		
		encerraReportPDF();
	}

	@AfterClass
	public static void finalizarTestes() throws IOException {
		atualizaReportHTML();
		killDriver();
	}
}