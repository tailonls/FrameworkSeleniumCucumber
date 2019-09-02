package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

public class FuncionalidadePageElementMap extends BasePage {

	@FindBy(xpath = "//*[@id='searchboxinput']")
	WebElement campoPesquisarMaps;

	@FindBy(xpath = "//*[@class='vdLsw gsfi']/following-sibling::input")
	WebElement campoPesquisarGoogle;

	String elemento = "//*[@class='vdLxsw gsfi']/following-sibling::input";

	@FindBy(xpath = "//*[@id='tsf']//div[3]//input[1]")
	WebElement botaoPesquisarGoogle;

	@FindBy(xpath = "//a[text()='Gmail']")
	WebElement linkGmail;

	@FindBy(xpath = "//*[@id='identifierId']")
	WebElement lblEmailUsuario;

	@FindBy(xpath = "//a[@class='gb_z gb_Fa gb_g']")
	WebElement iconeUsuario;

	@FindBy(xpath = "")
	WebElement lblSenhaUsuario;

	@FindBy(xpath = "")
	WebElement btnProximo;

	// h1[text()='Faça mais com o Gmail']

	@FindBy(xpath = "//div[@class='gb_hb' and text()='tailonlimas@gmail.com']")
	WebElement emailIconeUsuario;

}