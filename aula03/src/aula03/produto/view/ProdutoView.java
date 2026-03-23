package aula03.produto.view;

import java.util.List;

import aula03.produto.model.Produto;

public class ProdutoView {
	
	public void exibirProduto(Produto p) {
		System.out.println("\n=== Detalhes do Produto ===");
		System.out.println("ID: " + p.getId());
		System.out.println("Nome: " + p.getNome());
		System.out.println("Preço: R$ " + p.getPreco());
		
	}
	
	public void mostrarMensagem(String msg) {
		System.out.println(msg);
	}

	public void listarTodos(List<Produto> produtos) {
		for(Produto p : produtos) {
			exibirProduto(p);
		}
	}

}
