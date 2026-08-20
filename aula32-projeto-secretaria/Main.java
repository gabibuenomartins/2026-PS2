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
            System.out.println("[0] Sair");

            System.out.print("Sua escolha: ");
            String opcao = teclado.nextLine();

            if (opcao.equals("1")) {
                cadastrar();
            } else if (opcao.equals("2")) {
                listar();
            } else if (opcao.equals("0")) {
                System.out.println("Secretaria fechada. Ate a proxima!");
                break;
            } else {
                System.out.println("Opcao invalida! Vale 0, 1 ou 2.");
            }
        }
    }

    static void cadastrar() {

        System.out.print("Nome: ");
        String nome = teclado.nextLine();

        System.out.print("Matricula: ");
        String matricula = teclado.nextLine();

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
            System.out.println(
                aluno.getMatricula() + " | "
                + aluno.getNome() + " | "
                + aluno.getCurso()
            );
        }
    }
}