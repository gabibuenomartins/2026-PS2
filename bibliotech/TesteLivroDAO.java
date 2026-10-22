public class TesteLivroDAO {
    public static void main(String[] args) {
        LivroDAO dao = new LivroDAO();

        boolean atualizado = dao.atualizarLivro(
            1,
            "Dom Casmurro - Edição Revisada",
            "Machado de Assis",
            1899
        );
        System.out.println("Livro atualizado: " + atualizado);

        boolean excluido = dao.excluirLivro(2);
        System.out.println("Livro excluído: " + excluido);

        System.out.println("Atualização de ID inexistente: " +
            dao.atualizarLivro(999999, "Teste", "Autor", 2000));

        System.out.println("Exclusão de ID inexistente: " +
            dao.excluirLivro(999999));
    }
}