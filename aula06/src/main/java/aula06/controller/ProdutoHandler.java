package aula06.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import aula06.model.Produto;
import aula06.repository.BancoFake;

public class ProdutoHandler implements HttpHandler {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String metodo = exchange.getRequestMethod();

        if (metodo.equals("GET")) {
            listarProdutos(exchange);
        } else if (metodo.equals("POST")) {
            criarProduto(exchange);
        } else {
            exchange.sendResponseHeaders(405, -1);
        }
    }

    // 🔹 GET → retorna lista em JSON
    private void listarProdutos(HttpExchange exchange) throws IOException {

        String json = mapper.writeValueAsString(BancoFake.produtos);

        enviarResposta(exchange, json);
    }

    // 🔹 POST → recebe JSON e converte para objeto
    private void criarProduto(HttpExchange exchange) throws IOException {

        try {
            InputStream is = exchange.getRequestBody();
            Produto p = mapper.readValue(is, Produto.class);
            p.setId(UUID.randomUUID().toString());
            BancoFake.produtos.add(p);
            String resposta = mapper.writeValueAsString(p);
            enviarResposta(exchange, resposta);

        } catch (Exception e) {

            String erro = "{\"erro\":\"JSON inválido\"}";
            exchange.sendResponseHeaders(400, erro.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(erro.getBytes());
            os.close();
        }
    }

    private void enviarResposta(HttpExchange exchange, String resposta) throws IOException {

        exchange.getResponseHeaders().add("Content-Type", "application/json");

        exchange.sendResponseHeaders(200, resposta.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(resposta.getBytes());
        os.close();
    }
}