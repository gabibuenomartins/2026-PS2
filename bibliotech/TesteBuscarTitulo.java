import java.util.List;

public class TesteBuscarTitulo {

    public static void main(String[] args) {

        LivroDAO dao = new LivroDAO();

        List<Livro> livros =
            dao.buscarLivrosPorTitulo("Dom");

        if (livros.isEmpty()) {

            System.out.println("Nenhum livro encontrado.");

        } else {

            System.out.println("=== Livros encontrados ===");

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
