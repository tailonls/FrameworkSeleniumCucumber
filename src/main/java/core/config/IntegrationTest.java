package br.com.unicred.caixa.test;

import br.com.unicred.caixa.servicos.restAPI.configuration.TestContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContextConfiguration.class)
public abstract class IntegrationTest {

    // Classe com configuracoes Spring que deve extendida pela classe de login no inicio de cada teste
}
