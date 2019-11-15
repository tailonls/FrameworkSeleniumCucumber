package core;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorReportPDF {

	private static Document document = null;
	private static PdfWriter writer = null;
	private static String PATH = "target/Evidencias/";

	public static void inicializarReportPDF(String nomeCenario) {
		try {
			new File(PATH).mkdir();

			document = new Document();
			writer = PdfWriter.getInstance(document, new FileOutputStream(PATH + nomeCenario + ".pdf"));
			document.open();

			document.addAuthor("Tailon Saraiva");
			document.addCreationDate();
			document.addCreator("Automação testes");
			document.addTitle("Cenario: " + nomeCenario);
			document.addSubject("Arquivo PDF criado por um teste automatizado!");

		} catch (Exception e) {
			System.out.println("Erro ao inicializar report PDF! " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void addParagrafoReportPDF(String paragrafo) {

		if (document != null && writer != null) {
			try {
				document.add(new Paragraph(paragrafo));

			} catch (Exception e) {
				System.out.println("Erro ao adicionar paragrafo no PDF!");
				e.printStackTrace();
			}
		}
	}

	public static void addImagemReportPDF(String path) {

		if (document != null && writer != null) {
			try {
				Image imagem = Image.getInstance(path);
				imagem.scaleToFit(500, 350);
				document.add(imagem);

			} catch (Exception e) {
				System.out.println("Erro ao adicionar imagem no PDF! " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public static void encerraReportPDF() {
		if (document != null && writer != null) {
			document.close();
			writer.close();
		}
	}
}