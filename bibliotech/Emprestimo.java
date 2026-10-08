import java.time.LocalDate;

public class Emprestimo {

    private Livro livro;
    private Leitor leitor;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private LocalDate dataPrevistaDevolucao;
    private boolean ativo;

    public Emprestimo(Livro livro, Leitor leitor) {
        if (livro == null || leitor == null) {
            throw new IllegalArgumentException(
                "Livro e leitor sao obrigatorios."
            );
        }

        this.livro = livro;
        this.leitor = leitor;
        this.ativo = false;
    }

    public boolean realizarEmprestimo() {
        if (ativo || !livro.estaDisponivel()
                  || !leitor.podePegarEmprestado()) {
            return false;
        }

        livro.emprestar();
        leitor.pegouLivro();

        dataRetirada = LocalDate.now();
        dataPrevistaDevolucao = dataRetirada.plusDays(7);
        dataDevolucao = null;
        ativo = true;

        return true;
    }

    public boolean registrarDevolucao() {
        if (!ativo) {
            return false;
        }

        livro.devolver();
        leitor.devolveuLivro();
        dataDevolucao = LocalDate.now();
        ativo = false;

        return true;
    }

    public Livro getLivro() {
        return livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public boolean estaAtivo() {
        return ativo;
    }

    public boolean estaAtrasado(LocalDate data) {
        if (!ativo || data == null || dataPrevistaDevolucao == null) {
            return false;
        }

        return data.isAfter(dataPrevistaDevolucao);
    }

    @Override
    public String toString() {
        if (dataRetirada == null) {
            return "Emprestimo ainda nao realizado: "
                 + livro.getTitulo();
        }

        String situacao = ativo
            ? "ativo"
            : "devolvido em " + dataDevolucao;

        return livro.getTitulo() + " para "
             + leitor.getNome()
             + " | retirada: " + dataRetirada
             + " | prevista: " + dataPrevistaDevolucao
             + " | " + situacao;
    }
}