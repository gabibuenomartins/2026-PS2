import java.time.LocalDate;

public class TesteRequisitos {

    static int passaram = 0;
    static int falharam = 0;

    static void verificar(String nome, boolean condicao) {
        if (condicao) {
            System.out.println("OK      " + nome);
            passaram++;
        } else {
            System.out.println("FALHOU  " + nome);
            falharam++;
        }
    }

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        Livro dom = new Livro(
            "Dom Casmurro",
            "Machado de Assis",
            1899
        );

        Livro capitaes = new Livro(
            "Capitaes da Areia",
            "Jorge Amado",
            1937
        );

        Livro terceiroLivro = new Livro(
            "O Cortico",
            "Aluisio Azevedo",
            1890
        );

        Leitor ana = new Leitor(
            "Ana Lima",
            "2026011",
            2
        );

        Leitor pedro = new Leitor(
            "Pedro Alves",
            "2026012",
            2
        );

        biblioteca.cadastrarLivro(dom);
        biblioteca.cadastrarLivro(capitaes);
        biblioteca.cadastrarLivro(terceiroLivro);

        biblioteca.cadastrarLeitor(ana);
        biblioteca.cadastrarLeitor(pedro);

        Livro encontrado = biblioteca.buscarLivro("Dom Casmurro");

        verificar(
            "RF01 livro cadastrado aparece na busca",
            encontrado != null
        );

        Leitor leitorEncontrado =
            biblioteca.buscarLeitor("2026011");

        verificar(
            "RF02 leitor cadastrado aparece na busca",
            leitorEncontrado != null
        );

        verificar(
            "RF03 livro novo esta disponivel",
            dom.estaDisponivel()
        );

        verificar(
            "RF03 titulo fora do acervo nao e encontrado",
            biblioteca.buscarLivro("Livro que nao existe") == null
        );

        boolean emprestimoPedro = biblioteca.emprestar(
            "Dom Casmurro",
            "2026012"
        );

        verificar(
            "RF05 emprestimo de livro disponivel e aceito",
            emprestimoPedro
        );

        verificar(
            "RF05 livro emprestado fica indisponivel",
            !dom.estaDisponivel()
        );

        verificar(
            "RF05 leitor passa a ter 1 livro em maos",
            pedro.getLivrosEmMaos() == 1
        );

        boolean emprestimoAna = biblioteca.emprestar(
            "Dom Casmurro",
            "2026011"
        );

        verificar(
            "RF05 livro emprestado e recusado para outro leitor",
            !emprestimoAna
        );

        boolean segundoEmprestimoPedro = biblioteca.emprestar(
            "Capitaes da Areia",
            "2026012"
        );

        verificar(
            "RF05 segundo livro do leitor e aceito",
            segundoEmprestimoPedro
        );

        boolean terceiroEmprestimoPedro = biblioteca.emprestar(
            "O Cortico",
            "2026012"
        );

        verificar(
            "RF05 leitor no limite e recusado",
            !terceiroEmprestimoPedro
        );

        boolean devolucao = biblioteca.devolver(
            "Dom Casmurro"
        );

        verificar(
            "RF04 devolucao de emprestimo ativo e aceita",
            devolucao
        );

        verificar(
            "RF04 livro devolvido volta a ficar disponivel",
            dom.estaDisponivel()
        );

        boolean segundaDevolucao = biblioteca.devolver(
            "Dom Casmurro"
        );

        verificar(
            "RF04 segunda devolucao e recusada",
            !segundaDevolucao
        );

        biblioteca.devolver("Capitaes da Areia");

        LocalDate hoje = LocalDate.now();

        Livro testeAtraso = new Livro(
            "Livro Atrasado",
            "Autor Teste",
            2026
        );

        Leitor leitorAtraso = new Leitor(
            "Leitor Atraso",
            "2026999",
            2
        );

        biblioteca.cadastrarLivro(testeAtraso);
        biblioteca.cadastrarLeitor(leitorAtraso);

        boolean emprestimoAtraso = biblioteca.emprestar(
            "Livro Atrasado",
            "2026999"
        );

        verificar(
            "Prazo emprestimo criado",
            emprestimoAtraso
        );

        LocalDate prazo = hoje.plusDays(7);

        verificar(
            "HU05 hoje ninguem esta atrasado",
            biblioteca.buscarEmprestimosAtrasados(hoje).size() == 0
        );

        verificar(
            "HU05 no ultimo dia do prazo ninguem esta atrasado",
            biblioteca.buscarEmprestimosAtrasados(prazo).size() == 0
        );

        verificar(
            "HU05 um dia depois do prazo ha 1 atrasado",
            biblioteca.buscarEmprestimosAtrasados(
                prazo.plusDays(1)
            ).size() == 1
        );

        System.out.println();
        System.out.println(
            passaram + " passaram, "
            + falharam + " falharam."
        );
    }
}