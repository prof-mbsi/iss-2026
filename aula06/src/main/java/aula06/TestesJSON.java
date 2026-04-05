package aula06;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aula06.model.Produto;

public class TestesJSON {

	public static void main(String[] args) throws JsonProcessingException {

		//Exemplo Saída:
		Produto p1 = new Produto("1", "Notebook", 3500);
		ObjectMapper mapper = new ObjectMapper();
		String jsonSaida = mapper.writeValueAsString(p1);
		System.out.println(jsonSaida);
		
		//Exemplo Entrada:
		String jsonEntrada = """
	        { 
	          "id": "2", 
	          "nome": "Mouse", 
	          "preco": 80 
	        } 
	        """;
		Produto p2 = mapper.readValue(jsonEntrada, Produto.class);
		System.out.println("Produto: " + p2.getNome() + ", preço: " + p2.getPreco());
	}

}
