package br.com.unicred.caixa.core;

import br.com.unicred.caixa.servicos.DTO.LoginDTO;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
public class GrafanaUtil extends BasePage {

    @Autowired
    LoginDTO loginDTO;

    private static final String HOST_ELASTICSEARCH = "elk-tst01.e-unicred.com.br";
    private static final int PORTA_ELASTICSEARCH = 9200;
    private static final String ID_SUITE = "id_" + new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());

    public void enviarDadosElasticSearch(String nomeTeste, int resultadoTeste) {
        Date dataLocal = new Date();

        String cooperativa = String.valueOf(loginDTO.getCooperativa());
        String login = loginDTO.getUsuario();
        String agora = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(dataLocal);
        String docID = new SimpleDateFormat("yyyyMMddHHmmSS").format(dataLocal);
        String docIndex = new SimpleDateFormat("yyyyMMdd").format(dataLocal);
        String ambiente = properties.getProperty("teste.ambiente");

        String url = "/automacao-caixa-" + docIndex + "/automacao-caixa/" + docID;
        Request request = new Request("PUT", url);

        String dados = "{\n" +
                            "\"Cooperativa\": \"" + cooperativa + "\",\n" +
                            "\"CasoDeTeste\": \"" + nomeTeste + "\",\n" +
                            "\"Ambiente\": \"" + ambiente + "\",\n" +
                            "\"Usuario\": \"" + login + "\",\n" +
                            "\"Resultado\": \"" + resultadoTeste + "\",\n" +
                            "\"Suite\": \"" + ID_SUITE + "\",\n" +
                            "\"@timestamp\": \"" + agora + "\"\n" +
                        "}";

        request.setJsonEntity(dados);
        RestClient restClient = RestClient.builder(new HttpHost(HOST_ELASTICSEARCH, PORTA_ELASTICSEARCH, "http")).build();

        try {
            Response response = restClient.performRequest(request);
            System.out.println("Dados enviados ao ELK com sucesso: " + Arrays.toString(response.getHeaders()));

        } catch (Exception e) {
            System.out.println("\n\n[ERRO] Envio de dados para o Elasticsearch falhou: " + e.getMessage() + "\n\n");
        }
    }
}