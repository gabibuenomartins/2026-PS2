import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Aluno> lista = new ArrayList<>();
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("==========================================");
            System.out.println("   SECRETARIA DA GABRIELE");
            System.out.println("==========================================");
            System.out.println("[1] Cadastrar aluno");
            System.out.println("[2] Listar alunos");
            System.out.println("[3] Buscar por matricula");
            System.out.println("[4] Atualizar curso");
            System.out.println("[5] Remover aluno");
            System.out.println("[6] Relatorio");
            System.out.println("[0] Sair");

            System.out.print("Sua escolha: ");
            String opcao = teclado.nextLine();

            if (opcao.equals("1")) {
                cadastrar();
            } else if (opcao.equals("2")) {
                listar();
            } else if (opcao.equals("3")) {
                buscar();
            } else if (opcao.equals("4")) {
                atualizar();
            } else if (opcao.equals("5")) {
                remover();
            } else if (opcao.equals("6")) {
                relatorio();
            } else if (opcao.equals("0")) {
                System.out.println("Secretaria fechada. Ate a proxima!");
                break;
            } else {
                System.out.println("Opcao invalida! Vale 0, 1, 2, 3, 4, 5 ou 6.");
            }
        }
    }

    static void cadastrar() {

    System.out.print("Nome: ");
    String nome = teclado.nextLine();

    System.out.print("Matricula: ");
    String matricula = teclado.nextLine();

    Aluno existente = buscarPorMatricula(matricula);

    if (existente != null) {
        System.out.println("Ja existe ficha com a matricula " + matricula + "!");
        return;
    }

    System.out.print("Curso: ");
    String curso = teclado.nextLine();

    Aluno novo = new Aluno(nome, matricula, curso);

    lista.add(novo);

    System.out.println("Ficha de " + nome + " arquivada!");
}


    static void listar() {

        if (lista.isEmpty()) {
            System.out.println("Nenhuma ficha cadastrada.");
            return;
        }

        System.out.println("--- FICHAS NO GAVETEIRO: " + lista.size() + " ---");

        for (Aluno aluno : lista) {
            System.out.println(aluno);
        }
    }

    static Aluno buscarPorMatricula(String matricula) {

        for (Aluno aluno : lista) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }

        return null;
    }

    static void buscar() {

        System.out.print("Matricula procurada: ");
        String matricula = teclado.nextLine();

        Aluno aluno = buscarPorMatricula(matricula);

        if (aluno == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
        } else {
            System.out.println("Achei: " + aluno);
        }
    }

    static void atualizar() {

        System.out.print("Matricula da ficha a atualizar: ");
        String matricula = teclado.nextLine();

        Aluno aluno = buscarPorMatricula(matricula);

        if (aluno == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
            return;
        }

        System.out.print("Novo curso de " + aluno.getNome() + ": ");
        String novoCurso = teclado.nextLine();

        aluno.setCurso(novoCurso);

        System.out.println("Ficha atualizada: " + aluno);
    }

    static void remover() {

        System.out.print("Matricula da ficha a remover: ");
        String matricula = teclado.nextLine();

        Aluno aluno = buscarPorMatricula(matricula);

        if (aluno == null) {
            System.out.println("Nenhuma ficha com a matricula " + matricula + ".");
            return;
        }

        System.out.print("Tem certeza que remove " + aluno.getNome() + "? (s/n): ");
        String resposta = teclado.nextLine();

        if (resposta.equals("s")) {
            lista.remove(aluno);
            System.out.println("Ficha removida.");
        } else {
            System.out.println("Remocao cancelada.");
        }
    }

    static void relatorio() {

        System.out.println("--- RELATORIO DA SECRETARIA ---");
        System.out.println("Total de fichas: " + lista.size());

        System.out.print("Contar alunos de qual curso? ");
        String curso = teclado.nextLine();

        int contador = 0;

        for (Aluno aluno : lista) {
            if (aluno.getCurso().equals(curso)) {
                contador++;
            }
        }

        System.out.println("Alunos de " + curso + ": " + contador);
    }
}
