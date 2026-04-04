package service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ProdutoHandler implements HttpHandler{
	
	private List<Produto> produtos = new ArrayList<>();
	
	public ProdutoHandler() {
		produtos.add(new Produto(1, "Teclado"));
		produtos.add(new Produto(2, "Mouse"));
		produtos.add(new Produto(3, "Monitor"));
	}
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		StringBuilder resposta = new StringBuilder();
		resposta.append("[\n");
		for (Produto p : produtos) {
			resposta.append(" { \"id\": ")
			.append(p.getId())
			.append(", \"nome\": \"")
			.append(p.getNome())
			.append("\" },\n");
		}
		resposta.append("]");
		exchange.getResponseHeaders().add("Content-Type",
		"application/json");
		exchange.sendResponseHeaders(200, resposta.length());
		OutputStream os = exchange.getResponseBody();
		os.write(resposta.toString().getBytes());
		os.close();
	}
}
