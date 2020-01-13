package br.com.unicred.caixa.core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import static br.com.unicred.caixa.core.GeradorReportHTMLAutomacao.*;
import static br.com.unicred.caixa.core.WiniumFactory.getWiniumDriver;

public class BasePage extends RobotServices {

    public static Properties properties = new Properties();

    public BasePage() {
        try {
            properties.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("application.properties")));

        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo .properties! " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected Boolean isElementoPresente(String idElemento, String descricao) {
       if (isElementoPresente(idElemento)) {
            logPrintPass(descricao + " apareceu!");
            return true;
        }
        logPrintFail(descricao + " NAO apareceu!");
        return false;
    }

    protected boolean isElementoPresente(String idElemento) {
        aguardaVisibilidadeElemento(idElemento, 3);
        return !getWiniumDriver().findElements(By.id(idElemento)).isEmpty();
    }

    public void acessaOperacao(String operacao) {
        try {
            sairOperacao();

            digitar(operacao);
            logPrintPass("Informou operacao [" + operacao + "]");
            digitar(KeyEvent.VK_ENTER);

        } catch (Exception e) {
            logPrintFail("Erro ao informar operacao [" + operacao + "]! " + e.getMessage());
            e.printStackTrace();
        }
    }

    // FUNCOES DE AGUARDAR
    protected void aguardarSegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);

        } catch (InterruptedException e) {
            System.out.println("Erro ao aguardar segundos! " + e.getMessage());
            Assert.fail();
        }
    }

    protected WebElement aguardaVisibilidadeElemento(String id, int tempoEmSegundos) {
        aguardarSegundos(1);
        WebElement webElement;

        Wait<WebDriver> wait = new FluentWait<WebDriver>(getWiniumDriver())
                .withTimeout(Duration.ofSeconds(tempoEmSegundos))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);

        try {
            webElement = wait.until(ExpectedConditions.visibilityOf(getWiniumDriver().findElement(By.id(id))));

        } catch (Exception e) {
            System.out.println("Aguardou " + tempoEmSegundos + " segundo(s) mas nao achou o elemento " + id);
            return null;
        }
        return webElement;
    }

    protected WebElement aguardaPresencaDoElemento(String id, int tempoEmSegundos) {
        aguardarSegundos(1);
        WebElement webElement;

        Wait<WebDriver> wait = new FluentWait<WebDriver>(getWiniumDriver())
                .withTimeout(Duration.ofSeconds(tempoEmSegundos))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);

        try {
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));

        } catch (Exception e) {
            System.out.println("Aguardou " + tempoEmSegundos + " segundo(s) mas nao achou o elemento " + id);
            return null;
        }
        return webElement;
    }

    protected Boolean aguardaMensagemAparecer(String id, String mensagem, int tempoEmSegundos) {
        Boolean apareceuMensagem = false;

        Wait<WebDriver> wait = new FluentWait<WebDriver>(getWiniumDriver())
                .withTimeout(Duration.ofSeconds(tempoEmSegundos))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);

        try {
            apareceuMensagem = wait.until(ExpectedConditions.attributeContains(getWiniumDriver().findElement(By.id(id)), "Name", mensagem));

        } catch (Exception e) {
            System.out.println("Aguardou " + tempoEmSegundos + " segundos e mas NAO encontrou a mensagem '" + mensagem + "'!");
            return null;
        }
        return apareceuMensagem;
    }

    protected void selecionaOpcaoCombo(String campo, String opcao) {
        try {
            setComboSelectedValue(campo, opcao);
            aguardarSegundos(1);

        } catch (Exception e) {
            logPrintFail("Ocorreu um erro ao selecionar opcao '" + opcao + "' no combo! " + e.getMessage());
            Assert.fail();
        }
    }

    protected void preencheCampoNumerico(String campo, String conteudo) {
        try {
            setElementValueNumbersByKey(campo, conteudo);
            aguardarSegundos(1);

        } catch (Exception e) {
            logPrintFail("Ocorreu um erro ao digitar valores numericos! " + e.getMessage());
            Assert.fail();
        }
    }

    protected String pegaTextoTela(String idElemento) {
        return getWiniumDriver().findElement(By.id(idElemento)).getText();
    }

    protected String pegaAtributoTela(String idElemento) {
        return getWiniumDriver().findElement(By.id(idElemento)).getAttribute("Name");
    }

    protected boolean saoValoresIguais(String conteudoRecebido, String conteudoEsperado) {
        if (conteudoRecebido.toLowerCase().equalsIgnoreCase(conteudoEsperado.toLowerCase())) {
            return true;
        }
        logFail("Conteudo recebido " + conteudoRecebido + " diferente do conteudo esperado " + conteudoEsperado + "!");
        return false;
    }

    protected String criaListaFormatada(String mensagem, String... parametros) {
        mensagem = "<b>" + mensagem + "</b>";

        for (String parametro : parametros) {
            if (!parametro.isEmpty())
                mensagem = mensagem + "<br /> - " + parametro;
        }

        return mensagem;
    }

    public LocalDate addDiasUteis(LocalDate dataInformada, int diasUteis) {
        if (diasUteis < 1) {
            return dataInformada;
        }

        LocalDate dataFinal = dataInformada;
        int addDias = 0;

        while (addDias < diasUteis) {
            dataFinal = dataFinal.plusDays(1);
            if (!(dataFinal.getDayOfWeek() == DayOfWeek.SATURDAY || dataFinal.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addDias;
            }
        }
        return dataFinal;
    }

    public boolean verificaValorNoComprovante(String texto, String conteudo) {
        if (texto.toLowerCase().contains(conteudo.toLowerCase())) {
            return true;
        }
        logFail("Conteudo [" + conteudo + "] NAO encontrado no comprovante!");
        return false;
    }

    public void sairOperacao() {
        for (int i = 0; i < 2; i++) {
            digitar(KeyEvent.VK_ESCAPE);
            aguardarSegundos(1);
        }
        aguardarSegundos(1);
    }
}