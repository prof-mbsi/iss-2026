package br.com.marlon.aula09.handler;

import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import br.com.marlon.aula09.model.Endereco;
import br.com.marlon.aula09.service.CepService;

public class CepHandler implements HttpHandler {

    private CepService service = new CepService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (exchange.getRequestMethod().equals("GET")) {
                buscarCep(exchange);
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        } catch (Exception e) {

            String erro = "{\"erro\":\"" + e.getMessage() + "\"}";

            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(500, erro.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(erro.getBytes());
            os.close();

            e.printStackTrace();
        }
    }

    private void buscarCep(HttpExchange exchange) throws Exception {

        String path = exchange.getRequestURI().getPath();
        String[] partes = path.split("/");

        if (partes.length < 3) {
            exchange.sendResponseHeaders(400, -1);
            return;
        }

        String cep = partes[2];

        Endereco endereco = service.buscarEndereco(cep);

        String json = mapper.writeValueAsString(endereco);

        enviar(exchange, json);
    }

    private void enviar(HttpExchange exchange, String resposta) throws IOException {

        exchange.getResponseHeaders().add("Content-Type", "application/json");

        exchange.sendResponseHeaders(200, resposta.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(resposta.getBytes());
        os.close();
    }
}