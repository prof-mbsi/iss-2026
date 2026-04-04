package infra;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import service.ProdutoHandler;

public class Servidor {

	public static void main(String[] args) throws IOException {

		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
				server.createContext("/produtos", new ProdutoHandler());
				System.out.println("Microsserviço de Produtos rodando em http://localhost:8080/produtos");
				server.start();
	}

}
