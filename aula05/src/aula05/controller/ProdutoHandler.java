package aula05.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import aula05.model.Produto;
import aula05.repository.BancoFake; 

public class ProdutoHandler implements HttpHandler { 

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

    private void listarProdutos(HttpExchange exchange) throws IOException { 

        String json = "["; 

        for (int i = 0; i < BancoFake.produtos.size(); i++) { 
            Produto p = BancoFake.produtos.get(i); 

            json += String.format( 
                "{\"id\":\"%s\",\"nome\":\"%s\",\"preco\":%f}", 
                p.getId(), p.getNome(), p.getPreco() 
            );

            if (i < BancoFake.produtos.size() - 1) { 
                json += ","; 
            } 
        } 

        json += "]"; 

        enviarResposta(exchange, json); 
    }

    private void criarProduto(HttpExchange exchange) throws IOException { 

        InputStream is = exchange.getRequestBody(); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(is)); 

        String body = reader.readLine(); 

        // Exemplo simples (sem parsing JSON real) 
        Produto p = new Produto(UUID.randomUUID().toString(), body, 0); 

        BancoFake.produtos.add(p); 

        enviarResposta(exchange, "{\"mensagem\":\"Produto criado\"}"); 
    }

    private void enviarResposta(HttpExchange exchange, String resposta) throws IOException { 

        exchange.getResponseHeaders().add("Content-Type", "application/json"); 
        exchange.sendResponseHeaders(200, resposta.getBytes().length); 

        OutputStream os = exchange.getResponseBody(); 
        os.write(resposta.getBytes()); 
        os.close(); 
    } 
} 
