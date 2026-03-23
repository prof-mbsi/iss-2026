package aula03.produto.main;
import aula03.produto.controller.ProdutoController;
import aula03.produto.view.ProdutoView;

public class App {

	public static void main(String[] args) {
		ProdutoView view = new ProdutoView();
		ProdutoController controller = new ProdutoController(view);
		
		controller.adicionarProduto(1, "Teclado", 38.0);
		controller.adicionarProduto(2, "Mouse", 25.5);
		controller.adicionarProduto(3, "Monitor", 350.0);
		controller.adicionarProduto(4, "Garrafa Térmica", 62.5);
		
		controller.listarTodos();
		
		controller.atualizarProduto(2, "Mouse gamer", 55.5);
		
		controller.exibirProduto(2);
	}

}
