package service;

import model.Produto;
import repository.ProdutoRepository;

import java.util.*;

public class ProdutoService {

    private ProdutoRepository repo = new ProdutoRepository();

    public Produto criarProduto(Produto p) throws Exception {

        if (p.getNome() == null || p.getNome().isEmpty()) {
            throw new Exception("Nome é obrigatório");
        }

        p.setId(UUID.randomUUID().toString());

        repo.salvar(p);

        return p;
    }

    public List<Produto> listarProdutos() throws Exception {
        return repo.listar();
    }

    public Produto buscarPorId(String id) throws Exception {
        return repo.buscarPorId(id);
    }

    public void deletar(String id) throws Exception {
        repo.deletar(id);
    }

    public Produto atualizar(Produto p) throws Exception {

        if (p.getId() == null) {
            throw new Exception("ID obrigatório");
        }

        repo.atualizar(p);

        return p;
    }
}