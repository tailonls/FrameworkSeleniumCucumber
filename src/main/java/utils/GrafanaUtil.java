package utils;

import core.BasePage;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GrafanaUtil extends BasePage {

    private static final String HOST = "http://127.0.0.1";
    private static final int PORT = 9200;

    public static void enviarDadosAoElasticsearch(String nomeTeste, boolean statusTeste) {
        Date dataLocal = new Date();

        String agora = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(dataLocal);
        String docID = new SimpleDateFormat("yyyyMMddHHmmSS").format(dataLocal);
        String docIndex = new SimpleDateFormat("yyyyMMdd").format(dataLocal);
        String usuario = properties.getProperty("teste.nome");
        String ambiente = "TST";

        String url = "/teste_automacao_" + docIndex + "/automacao/" + docID;

        String dados = "{" +
                "\"nomeTeste\" : \"" + nomeTeste + "\"," +
                "\"statusTeste\" : \"" + statusTeste + "\"," +
                "\"dataLocal\" : \"" + dataLocal + "\"," +
                "\"usuario\" : \"" + usuario + "\"," +
                "\"docID\" : \"" + docID + "\"," +
                "\"docIndex\" : \"" + docIndex + "\"," +
                "\"ambiente\" : \"" + ambiente + "\"," +
                "\"@timestamp\" : \"" + agora + "\"" +
                "}";

        Request request = new Request("PUT", url);
        request.setJsonEntity(dados);

        RestClient restClient = RestClient.builder(new HttpHost(HOST, PORT, "https")).build();

        try {
            restClient.performRequest(request);
        System.out.println("\nDados enviados com sucesso para o index: " + url);

    } catch (Exception e) {
        System.out.println("[ERRO] Ao enviar dados ao Elasticsearch: " + e.getMessage());
    }
    }
}