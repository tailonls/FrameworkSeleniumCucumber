package utils;

import java.time.LocalDate;
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

	public static String converterData(LocalDate dataLocal) {
		DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataLocal.format(formatadorBarra);
	}
}