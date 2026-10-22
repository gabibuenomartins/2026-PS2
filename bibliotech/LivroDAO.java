
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LivroDAO {

    public boolean adicionarLivro(String titulo, String autor, int ano) {
        String sql = "INSERT INTO livros (titulo, autor, ano) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.setInt(3, ano);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarLivro(int id, String titulo, String autor, int ano) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, ano = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.setInt(3, ano);
            stmt.setInt(4, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirLivro(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir livro: " + e.getMessage());
            return false;
        }
    }
}
