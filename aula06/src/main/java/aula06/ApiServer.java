package aula06;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import aula06.controller.ProdutoHandler;
 

public class ApiServer { 

    public static void main(String[] args) throws IOException {
    	HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    	server.createContext("/produtos", new ProdutoHandler());
    	server.setExecutor(null);
    	server.start();
    	System.out.println("API rodando na porta 8000...");
	}
}