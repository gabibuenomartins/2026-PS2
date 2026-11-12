public class TesteBuscarLivro {

    public static void main(String[] args) {

        LivroDAO dao = new LivroDAO();

        int id = 1;

        Livro livro = dao.buscarLivroPorId(id);

        if (livro == null) {

            System.out.println("Livro não encontrado.");

        } else {

            System.out.println("=== Livro encontrado ===");
            System.out.println("ID: " + livro.getId());
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano: " + livro.getAno());
        }
    }
}
