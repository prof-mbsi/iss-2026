package repository;

import model.Produto;
import config.ConnectionFactory;

import java.sql.*;
import java.util.*;

public class ProdutoRepository {

    public void salvar(Produto p) throws SQLException {

        String sql = "INSERT INTO produto (id, nome, preco) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getId());
            stmt.setString(2, p.getNome());
            stmt.setDouble(3, p.getPreco());

            stmt.executeUpdate();
        }
    }

    public List<Produto> listar() throws SQLException {

        List<Produto> lista = new ArrayList<>();

        String sql = "SELECT * FROM produto";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Produto(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco")
                ));
            }
        }

        return lista;
    }

    // 🔹 EXERCÍCIO RESOLVIDO: Buscar por ID
    public Produto buscarPorId(String id) throws SQLException {

        String sql = "SELECT * FROM produto WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Produto(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco")
                );
            }
        }

        return null;
    }

    // 🔹 EXERCÍCIO RESOLVIDO: DELETE
    public void deletar(String id) throws SQLException {

        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    // 🔹 EXERCÍCIO RESOLVIDO: UPDATE
    public void atualizar(Produto p) throws SQLException {

        String sql = "UPDATE produto SET nome = ?, preco = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setString(3, p.getId());

            stmt.executeUpdate();
        }
    }
}