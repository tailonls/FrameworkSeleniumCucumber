package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

public class FuncionalidadePageElementMap extends BasePage {

	@FindBy(xpath = "//*[@id='searchboxinput']")
	WebElement campoPesquisarMaps;

	@FindBy(xpath = "//*[@class='vdLsw gsfi']/following-sibling::input")
	WebElement campoPesquisarGoogle;

	@FindBy(xpath = "//*[@id='tsf']//div[3]//input[1]")
	WebElement botaoPesquisarGoogle;
}
