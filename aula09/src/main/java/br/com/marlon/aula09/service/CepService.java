package br.com.marlon.aula09.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.marlon.aula09.model.Endereco;

public class CepService {

    private HttpClient client = HttpClient.newHttpClient();
    private ObjectMapper mapper = new ObjectMapper();

    public Endereco buscarEndereco(String cep) throws Exception {

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verifica erro HTTP
        if (response.statusCode() != 200) {
            throw new Exception("Erro ao chamar API externa");
        }

        // Verifica erro do ViaCEP
        if (response.body().contains("\"erro\"")) {
            throw new Exception("CEP inválido");
        }

        return mapper.readValue(response.body(), Endereco.class);
    }
}