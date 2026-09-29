/*
 * Disciplina: 2026-PS
 * Projeto   : bibliotech
 * Arquivo   : Bibliotecario.java
 * Autor     : Gabriele Bueno Martins
 * Descricao : representa uma bibliotecaria do BiblioTech.
 */
public class Bibliotecario extends Usuario {

    private String matriculaFuncional;

    public Bibliotecario(
        String nome,
        String matricula,
        String matriculaFuncional
    ) {
        super(nome, matricula);
        this.matriculaFuncional = matriculaFuncional;
    }

    public String getMatriculaFuncional() {
        return matriculaFuncional;
    }

    public boolean consultarAcervo() {
        return true;
    }

    @Override
    public String toString() {
        return "Bibliotecario(a) " + super.toString()
             + " - funcional " + matriculaFuncional;
    }
}
