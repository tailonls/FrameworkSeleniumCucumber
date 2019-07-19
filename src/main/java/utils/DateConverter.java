package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cucumber.api.Transformer;

public class DateConverter extends Transformer<Date> {

	@Override
	public Date transform(String data) {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date retorno = format.parse(data);
			return retorno;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}