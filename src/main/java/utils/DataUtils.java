package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {

	public static Date aumentarDiasNaData(int dias) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, dias);

		return cal.getTime();
	}

	public static Date diminuirDiasNaData(int dias) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -dias);

		return cal.getTime();
	}

	public static String converterData(LocalDateTime dataLocal) {
		DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return dataLocal.format(formatadorBarra);
	}
}