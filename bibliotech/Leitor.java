/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : Leitor.java
 * Autor     : Gabriele Bueno Martins
 * Descricao : representa um leitor do BiblioTech.
 */
public class Leitor extends Usuario {

    private int limiteEmprestimos;
    private int livrosEmMaos;

    public Leitor(String nome, String matricula, int limiteEmprestimos) {
        super(nome, matricula);
        this.limiteEmprestimos = limiteEmprestimos;
        this.livrosEmMaos = 0;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public int getLivrosEmMaos() {
        return livrosEmMaos;
    }

    public boolean podePegarEmprestado() {
        return livrosEmMaos < limiteEmprestimos;
    }

    public void pegouLivro() {
        livrosEmMaos++;
    }

    public void devolveuLivro() {
        if (livrosEmMaos > 0) {
            livrosEmMaos--;
        }
    }

    @Override
    public String toString() {
        return "Leitor " + super.toString()
             + " - " + livrosEmMaos
             + " de " + limiteEmprestimos + " livros";
    }
}
