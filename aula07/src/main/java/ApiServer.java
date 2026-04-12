import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import handler.ProdutoHandler;

public class ApiServer {

	public static void main(String[] args) throws IOException {
		
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		
		server.createContext("/produtos", new ProdutoHandler());
		
		server.setExecutor(null);
        server.start();

        System.out.println("Servidor rodando na porta 8000...");

	}

}
