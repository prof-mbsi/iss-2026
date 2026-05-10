package br.com.marlon.aula10;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import br.com.marlon.aula10.handler.PositionHandler;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
    	// Cria servidor HTTP na porta 8000
        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(8000),
                        0
                );

        // Endpoint Localização
        server.createContext(
                "/localizacao",
                new PositionHandler()
        );

        // Executor padrão
        server.setExecutor(null);

        // Inicia servidor
        server.start();

        System.out.println(
                "Servidor rodando em http://localhost:8000"
        );
    }
}
