package br.com.marlon.aula10.service;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.marlon.aula10.model.Localizacao;
import br.com.marlon.aula10.model.PositionResponse;

public class PositionService {

    private static final String ACCESS_KEY = "SUA_CHAVE";

    private HttpClient client = HttpClient.newHttpClient();

    private ObjectMapper mapper = new ObjectMapper();

    public Localizacao buscarCidade(String cidade) throws Exception {

        String url =
                "http://api.positionstack.com/v1/forward"
                + "?access_key=" + ACCESS_KEY
                + "&query=" + cidade;

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        // DEBUG (opcional)
        System.out.println(response.body());

        PositionResponse pr =
                mapper.readValue(
                        response.body(),
                        PositionResponse.class
                );

        // Verifica se encontrou resultado
        if (pr.getData() == null || pr.getData().isEmpty()) {
            throw new Exception("Cidade não encontrada");
        }

        return pr.getData().get(0);
    }
}