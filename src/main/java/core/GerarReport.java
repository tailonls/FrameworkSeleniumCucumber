package core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GerarReport {

	public static void main(String[] args) throws IOException {

		FileWriter arq = new FileWriter("index.html");
		PrintWriter gravarArq = new PrintWriter(arq);

		// TODO: implementar...
		gravarArq.printf("<!DOCTYPE html>");
		
		arq.close();
	}
}