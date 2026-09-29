/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : Usuario.java
 * Autor     : Gabriele Bueno Martins
 * Descricao : classe geral dos usuarios.
 */
public class Usuario {

    private String nome;
    private String matricula;

    public Usuario(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public boolean entrar() {
        return !matricula.isEmpty();
    }

    @Override
    public String toString() {
        return nome + " (" + matricula + ")";
    }
}
