
import java.util.List;

public class TesteListarLivros {

    public static void main(String[] args) {
        LivroDAO dao = new LivroDAO();
        List<Livro> livros = dao.listarLivros();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            System.out.println("=== Livros cadastrados ===");

            for (Livro livro : livros) {
                System.out.println(
                    "ID: " + livro.getId()
                    + " | Título: " + livro.getTitulo()
                    + " | Autor: " + livro.getAutor()
                    + " | Ano: " + livro.getAno()
                );
            }
        }
    }
}
