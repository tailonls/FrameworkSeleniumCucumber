package core;

public class Propriedades {
	public static boolean FECHAR_BROWSER = true;

	public static Browsers browser = Browsers.CHROME;

	public static boolean CHROME_HEADLESS = false;
	
	public enum Browsers {
		CHROME, FIREFOX, IEXPLORER
	}
}
