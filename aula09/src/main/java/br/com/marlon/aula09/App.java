package br.com.marlon.aula09;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import br.com.marlon.aula09.handler.CepHandler;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
    	HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/cep", new CepHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Servidor rodando em http://localhost:8000");
    }
}
