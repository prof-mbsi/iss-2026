package aula03.produto.controller;

import java.util.ArrayList;
import java.util.List;

import aula03.produto.model.Produto;
import aula03.produto.view.ProdutoView;

public class ProdutoController {
	
	private List<Produto> produtos = new ArrayList<>();
	private ProdutoView pView;
	
	public ProdutoController(ProdutoView pView) {
		super();
		this.pView = pView;
	}
	
	public void adicionarProduto(int id, String nome, double preco) {
		Produto p = new Produto(id, nome, preco);
		produtos.add(p);
		pView.mostrarMensagem("Produto " + p.getNome() 
			+ " adicionado com sucesso!");
	}
	
	public void atualizarProduto(int novoId, String novoNome, double novoPreco) {
		for(Produto p : produtos) {
			if(p.getId() == novoId) {
				p.setId(novoId);
				p.setNome(novoNome);
				p.setPreco(novoPreco);
				pView.mostrarMensagem("Produto " + p.getId() 
					+ " atualizado com sucesso!");
			}
		}
	}
	
	public void exibirProduto(int id) {
		for(Produto p : produtos) {
			if(p.getId() == id) {
				pView.exibirProduto(p);
			}
		}
		pView.mostrarMensagem("Produto não encontrado!");
	}
	
	public void listarTodos() {
		pView.listarTodos(this.produtos);
	}

}
