package br.com.marlon.aula10.handler;

import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import br.com.marlon.aula10.model.Localizacao;
import br.com.marlon.aula10.service.PositionService;

public class PositionHandler implements HttpHandler {

    private PositionService service = new PositionService();

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {

            if (exchange.getRequestMethod().equals("GET")) {

                buscarCidade(exchange);

            } else {

                exchange.sendResponseHeaders(405, -1);

            }

        } catch (Exception e) {

            String erro =
                    "{\"erro\":\"" + e.getMessage() + "\"}";

            exchange.getResponseHeaders()
                    .add("Content-Type", "application/json");

            exchange.sendResponseHeaders(
                    500,
                    erro.getBytes().length
            );

            OutputStream os =
                    exchange.getResponseBody();

            os.write(erro.getBytes());

            os.close();

            e.printStackTrace();
        }
    }

    private void buscarCidade(HttpExchange exchange)
            throws Exception {

        String path =
                exchange.getRequestURI().getPath();

        String[] partes = path.split("/");

        if (partes.length < 3) {

            exchange.sendResponseHeaders(400, -1);

            return;
        }

        String cidade = partes[2];

        Localizacao localizacao =
                service.buscarCidade(cidade);

        String json =
                mapper.writeValueAsString(localizacao);

        enviar(exchange, json);
    }

    private void enviar(
            HttpExchange exchange,
            String resposta
    ) throws IOException {

        exchange.getResponseHeaders()
                .add("Content-Type", "application/json");

        exchange.sendResponseHeaders(
                200,
                resposta.getBytes().length
        );

        OutputStream os =
                exchange.getResponseBody();

        os.write(resposta.getBytes());

        os.close();
    }
}