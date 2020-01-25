package core.relarorios;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import core.BasePage;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;

import static utils.DataUtils.converterData;

public class GeradorReportPDF extends BasePage {

	private static Document documento = null;
	private static String PATH = "target/Evidencias";
	private static LocalDate dataLocal = LocalDate.now();

	private static String responsavelTestes = properties.getProperty("teste.nome");

	public static void criaNovoReportPDF(String nomeCenario) {
		new File(PATH).mkdir();
		documento = new Document();

		try {
			PdfWriter.getInstance(documento, new FileOutputStream(PATH + "/" + nomeCenario + ".pdf"));
			documento.open();

			documento.addAuthor(responsavelTestes);
			documento.addCreationDate();
			documento.addCreator("Automação testes");
			documento.addTitle("Cenario: " + nomeCenario);
			documento.addSubject("Arquivo PDF criado por um teste automatizado!");

			addParagrafoReportPDF("Cenario: " + nomeCenario);
			addParagrafoReportPDF("Responsavel pelos testes: " + responsavelTestes);
			addParagrafoReportPDF("Executado em: " + converterData(dataLocal));
			addParagrafoReportPDF("______________________________________________________");
			addParagrafoReportPDF("\n");

		} catch (Exception e) {
			System.out.println("Erro ao inicializar report PDF! " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void addParagrafoReportPDF(String paragrafo) {

		if (documento.isOpen()) {
			try {
				documento.add(new Paragraph("\n" + paragrafo));

			} catch (Exception e) {
				System.out.println("Erro ao adicionar paragrafo no PDF!");
				e.printStackTrace();
			}
		}
	}

	public static void addImagemReportPDF(String path_imagem) {

		if (documento.isOpen()) {
			try {
				Image imagem = Image.getInstance(path_imagem);
				imagem.scaleAbsolute(500, 250);
				documento.add(imagem);

			} catch (Exception e) {
				System.out.println("Erro ao adicionar imagem no PDF! " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public static void addImagemPaginaInteiraReportPDF(String path_imagem) {

		if (documento.isOpen()) {
			try {
				Image imagem = Image.getInstance(path_imagem);
				imagem.scaleAbsolute(350, 550);
				documento.add(imagem);

			} catch (Exception e) {
				System.out.println("Erro ao adicionar imagem no PDF! " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public static void encerraReportPDF() {
		documento.close();
	}
}