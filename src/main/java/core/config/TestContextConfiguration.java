package br.com.unicred.caixa.servicos.restAPI.configuration;

import java.util.Objects;

import javax.sql.DataSource;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static br.com.unicred.caixa.core.GeradorReportHTMLAutomacao.*;

@SpringBootApplication
@Configuration
@ComponentScan("br.com.unicred")
@PropertySource("classpath:application.properties")
@EnableFeignClients("br.com.unicred")
public class TestContextConfiguration {

    @Autowired
    private Environment environment;

    private final String COOPERATIVA = "teste.cooperativa";

    private final String DRIVER = "spring.datasource.driver";
    private final String URL = "spring.datasource.url";
    private final String USER = "spring.datasource.user";
    private final String PASS = "spring.datasource.pass";

    private final String URL_ARQ = "spring.datasource.arq.url";
    private final String URL_DEPOSITO = "spring.datasource.deposito.url";
    private final String USER_DEPOSITO = "spring.datasource.deposito.user";
    private final String PASS_DEPOSITO = "spring.datasource.deposito.pass";

    private final String URL_COBRANCA = "spring.datasource.COB.url";
    private final String USER_COBRANCA = "spring.datasource.COB.user";
    private final String PASS_COBRANCA = "spring.datasource.COB.pass";

    @Bean("jdbcTemplate")
    JdbcTemplate jdbcTemplate() {
        String baseName = getBaseName(Objects.requireNonNull(environment.getProperty(COOPERATIVA)));
        return new JdbcTemplate(dataSource(environment.getProperty(URL) + baseName));
    }

    @Bean("jdbcTemplateArq")
    JdbcTemplate jdbcTemplateArq() {
        return new JdbcTemplate(dataSource(environment.getProperty(URL_ARQ)));
    }

    @Bean("jdbcTemplateCobranca")
    JdbcTemplate jdbcTemplateCobranca() {
        return new JdbcTemplate(dataSourceCobranca());
    }

    @Bean("jdbcTemplateDeposito")
    JdbcTemplate jdbcTemplateDeposito() {
        return new JdbcTemplate(dataSourceDeposito());
    }

    DataSource dataSource(final String url) {
        return getDataSource(url, USER, PASS);
    }

    DataSource dataSourceDeposito() {
        return getDataSource(environment.getProperty(URL_DEPOSITO), USER_DEPOSITO, PASS_DEPOSITO);
    }

    DataSource dataSourceCobranca() {
        return getDataSource(environment.getProperty(URL_COBRANCA), USER_COBRANCA, PASS_COBRANCA);
    }

    DataSource getDataSource(String property, String user, String pass) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty(DRIVER)));
        driverManagerDataSource.setUrl(property);
        driverManagerDataSource.setUsername(environment.getProperty(user));
        driverManagerDataSource.setPassword(environment.getProperty(pass));

        return driverManagerDataSource;
    }

    private String getBaseName(String cooperativa) {

        switch (cooperativa) {
            case "19":
                return "DBCW01";
            case "544":
                return "DBCW02";
            case "59184":
                return "DBCW04";
            case "4022":
                return "DBCW03";
            case "4014":
                return "DBCW06";
            case "1538":
                return "DBCW05";
            case "4049":
                return "DBCW07";
            case "515":
                return "DBCW08";
            case "590":
                return "DBCW09";

            default:
                logFail("Nenhuma base encontrada para cooperativa: " + cooperativa);
                Assert.fail("Nenhuma base encontrada para cooperativa: " + cooperativa);
                return null;
        }
    }
}