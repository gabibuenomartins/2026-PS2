
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.*;

public class TelaBiblioteca extends JFrame {

    private Biblioteca biblioteca;
    private JTextField campoTitulo;
    private JTextField campoMatricula;
    private JTextArea areaAcervo;

    private final Color roxo = new Color(126, 87, 194);
    private final Color lilas = new Color(139, 92, 246);
    private final Color fundo = new Color(247, 243, 255);
    private final Color texto = new Color(55, 40, 75);

    public TelaBiblioteca(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;

        setTitle("BiblioTech | Biblioteca Digital");
        setSize(850, 550);
        setMinimumSize(new Dimension(700, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));

        getContentPane().setBackground(fundo);

        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBackground(roxo);
        cabecalho.setBorder(
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        JLabel titulo = new JLabel("BiblioTech | Biblioteca Digital");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 23));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel(
            "Gerenciamento de empréstimos e devoluções"
        );
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitulo.setForeground(new Color(240, 230, 255));

        cabecalho.add(titulo, BorderLayout.NORTH);
        cabecalho.add(subtitulo, BorderLayout.SOUTH);

        add(cabecalho, BorderLayout.NORTH);

        JPanel principal = new JPanel(new BorderLayout(10, 15));
        principal.setBackground(fundo);
        principal.setBorder(
            BorderFactory.createEmptyBorder(20, 25, 15, 25)
        );

        JPanel campos = new JPanel(new GridLayout(2, 2, 12, 12));
        campos.setBackground(Color.WHITE);
        campos.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(225, 210, 245)
                ),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
            )
        );

        JLabel labelTitulo = new JLabel("Título do livro:");
        JLabel labelMatricula = new JLabel("Matrícula do leitor:");

        labelTitulo.setForeground(texto);
        labelMatricula.setForeground(texto);

        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));
        labelMatricula.setFont(new Font("SansSerif", Font.BOLD, 13));

        campoTitulo = new JTextField();
        campoMatricula = new JTextField();

        estilizarCampo(campoTitulo);
        estilizarCampo(campoMatricula);

        campos.add(labelTitulo);
        campos.add(campoTitulo);
        campos.add(labelMatricula);
        campos.add(campoMatricula);

        principal.add(campos, BorderLayout.NORTH);

        JPanel painelAcervo = new JPanel(new BorderLayout(5, 10));
        painelAcervo.setBackground(Color.WHITE);
        painelAcervo.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(225, 210, 245)
                ),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            )
        );

        JLabel tituloAcervo = new JLabel("Acervo da biblioteca");
        tituloAcervo.setFont(
            new Font("SansSerif", Font.BOLD, 17)
        );
        tituloAcervo.setForeground(roxo);

        areaAcervo = new JTextArea();
        areaAcervo.setEditable(false);
        areaAcervo.setLineWrap(true);
        areaAcervo.setWrapStyleWord(true);
        areaAcervo.setFont(
            new Font("SansSerif", Font.PLAIN, 14)
        );
        areaAcervo.setForeground(texto);
        areaAcervo.setBackground(new Color(250, 248, 255));
        areaAcervo.setBorder(
            BorderFactory.createEmptyBorder(12, 12, 12, 12)
        );

        JScrollPane rolagem = new JScrollPane(areaAcervo);
        rolagem.setBorder(
            BorderFactory.createLineBorder(
                new Color(237, 229, 248)
            )
        );

        painelAcervo.add(tituloAcervo, BorderLayout.NORTH);
        painelAcervo.add(rolagem, BorderLayout.CENTER);

        principal.add(painelAcervo, BorderLayout.CENTER);

        add(principal, BorderLayout.CENTER);

        JPanel botoes = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 15, 15)
        );
        botoes.setBackground(fundo);

        JButton botaoEmprestar = new JButton("Emprestar");
        JButton botaoDevolver = new JButton("Devolver");
        JButton botaoAtualizar = new JButton("Atualizar acervo");

        estilizarBotao(botaoEmprestar);
        estilizarBotao(botaoDevolver);
        estilizarBotao(botaoAtualizar);

        botoes.add(botaoEmprestar);
        botoes.add(botaoDevolver);
        botoes.add(botaoAtualizar);

        add(botoes, BorderLayout.SOUTH);

        botaoEmprestar.addActionListener(
            e -> emprestar()
        );

        botaoDevolver.addActionListener(
            e -> devolver()
        );

        botaoAtualizar.addActionListener(
            e -> atualizarAcervo()
        );

        atualizarAcervo();
    }

    private void estilizarBotao(JButton botao) {

        botao.setBackground(lilas);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("SansSerif", Font.BOLD, 13));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setOpaque(true);

        botao.setBorder(
            BorderFactory.createEmptyBorder(12, 22, 12, 22)
        );

        botao.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );
    }

    private void estilizarCampo(JTextField campo) {

        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setForeground(texto);
        campo.setBackground(new Color(250, 248, 255));

        campo.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(203, 182, 235)
                ),
                BorderFactory.createEmptyBorder(9, 10, 9, 10)
            )
        );
    }

    private void atualizarAcervo() {

        areaAcervo.setText(
            biblioteca.obterAcervoComoTexto()
        );

        areaAcervo.setCaretPosition(0);
    }

    private void emprestar() {

        String titulo = campoTitulo.getText().trim();
        String matricula = campoMatricula.getText().trim();

        if (titulo.isEmpty() || matricula.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Preencha o título e a matrícula."
            );

            return;
        }

        boolean resultado =
            biblioteca.emprestar(titulo, matricula);

        atualizarAcervo();

        if (resultado) {

            JOptionPane.showMessageDialog(
                this,
                "Empréstimo realizado com sucesso!"
            );

            campoTitulo.setText("");
            campoMatricula.setText("");

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Empréstimo recusado. Confira o título, "
                + "a matrícula, a disponibilidade e "
                + "o limite do leitor."
            );
        }
    }

    private void devolver() {

        String titulo = campoTitulo.getText().trim();

        if (titulo.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Preencha o título do livro."
            );

            return;
        }

        boolean resultado =
            biblioteca.devolver(titulo);

        atualizarAcervo();

        if (resultado) {

            JOptionPane.showMessageDialog(
                this,
                "Devolução registrada com sucesso!"
            );

            campoTitulo.setText("");
            campoMatricula.setText("");

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Nenhum empréstimo ativo encontrado "
                + "para esse título."
            );
        }
    }

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        biblioteca.cadastrarLivro(
            new Livro(
                "Dom Casmurro",
                "Machado de Assis",
                1899
            )
        );

        biblioteca.cadastrarLivro(
            new Livro(
                "Capitaes da Areia",
                "Jorge Amado",
                1937
            )
        );

        biblioteca.cadastrarLeitor(
            new Leitor(
                "Pedro Alves",
                "2026010",
                1
            )
        );

        biblioteca.cadastrarLeitor(
            new Leitor(
                "Ana Lima",
                "2026011",
                2
            )
        );

        SwingUtilities.invokeLater(() -> {

            TelaBiblioteca tela =
                new TelaBiblioteca(biblioteca);

            tela.setVisible(true);
        });
    }
}

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.*;

public class TelaBiblioteca extends JFrame {

    private Biblioteca biblioteca;
    private JTextField campoTitulo;
    private JTextField campoMatricula;
    private JTextArea areaAcervo;

    private final Color roxo = new Color(126, 87, 194);
    private final Color lilas = new Color(139, 92, 246);
    private final Color fundo = new Color(247, 243, 255);
    private final Color texto = new Color(55, 40, 75);

    public TelaBiblioteca(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;

        setTitle("BiblioTech | Biblioteca Digital");
        setSize(850, 550);
        setMinimumSize(new Dimension(700, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));

        getContentPane().setBackground(fundo);

        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBackground(roxo);
        cabecalho.setBorder(
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        JLabel titulo = new JLabel("BiblioTech | Biblioteca Digital");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 23));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel(
            "Gerenciamento de empréstimos e devoluções"
        );
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitulo.setForeground(new Color(240, 230, 255));

        cabecalho.add(titulo, BorderLayout.NORTH);
        cabecalho.add(subtitulo, BorderLayout.SOUTH);

        add(cabecalho, BorderLayout.NORTH);

        JPanel principal = new JPanel(new BorderLayout(10, 15));
        principal.setBackground(fundo);
        principal.setBorder(
            BorderFactory.createEmptyBorder(20, 25, 15, 25)
        );

        JPanel campos = new JPanel(new GridLayout(2, 2, 12, 12));
        campos.setBackground(Color.WHITE);
        campos.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(225, 210, 245)
                ),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
            )
        );

        JLabel labelTitulo = new JLabel("Título do livro:");
        JLabel labelMatricula = new JLabel("Matrícula do leitor:");

        labelTitulo.setForeground(texto);
        labelMatricula.setForeground(texto);

        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));
        labelMatricula.setFont(new Font("SansSerif", Font.BOLD, 13));

        campoTitulo = new JTextField();
        campoMatricula = new JTextField();

        estilizarCampo(campoTitulo);
        estilizarCampo(campoMatricula);

        campos.add(labelTitulo);
        campos.add(campoTitulo);
        campos.add(labelMatricula);
        campos.add(campoMatricula);

        principal.add(campos, BorderLayout.NORTH);

        JPanel painelAcervo = new JPanel(new BorderLayout(5, 10));
        painelAcervo.setBackground(Color.WHITE);
        painelAcervo.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(225, 210, 245)
                ),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            )
        );

        JLabel tituloAcervo = new JLabel("Acervo da biblioteca");
        tituloAcervo.setFont(
            new Font("SansSerif", Font.BOLD, 17)
        );
        tituloAcervo.setForeground(roxo);

        areaAcervo = new JTextArea();
        areaAcervo.setEditable(false);
        areaAcervo.setLineWrap(true);
        areaAcervo.setWrapStyleWord(true);
        areaAcervo.setFont(
            new Font("SansSerif", Font.PLAIN, 14)
        );
        areaAcervo.setForeground(texto);
        areaAcervo.setBackground(new Color(250, 248, 255));
        areaAcervo.setBorder(
            BorderFactory.createEmptyBorder(12, 12, 12, 12)
        );

        JScrollPane rolagem = new JScrollPane(areaAcervo);
        rolagem.setBorder(
            BorderFactory.createLineBorder(
                new Color(237, 229, 248)
            )
        );

        painelAcervo.add(tituloAcervo, BorderLayout.NORTH);
        painelAcervo.add(rolagem, BorderLayout.CENTER);

        principal.add(painelAcervo, BorderLayout.CENTER);

        add(principal, BorderLayout.CENTER);

        JPanel botoes = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 15, 15)
        );
        botoes.setBackground(fundo);

        JButton botaoEmprestar = new JButton("Emprestar");
        JButton botaoDevolver = new JButton("Devolver");
        JButton botaoAtualizar = new JButton("Atualizar acervo");

        estilizarBotao(botaoEmprestar);
        estilizarBotao(botaoDevolver);
        estilizarBotao(botaoAtualizar);

        botoes.add(botaoEmprestar);
        botoes.add(botaoDevolver);
        botoes.add(botaoAtualizar);

        add(botoes, BorderLayout.SOUTH);

        botaoEmprestar.addActionListener(
            e -> emprestar()
        );

        botaoDevolver.addActionListener(
            e -> devolver()
        );

        botaoAtualizar.addActionListener(
            e -> atualizarAcervo()
        );

        atualizarAcervo();
    }

    private void estilizarBotao(JButton botao) {

        botao.setBackground(lilas);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("SansSerif", Font.BOLD, 13));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setOpaque(true);

        botao.setBorder(
            BorderFactory.createEmptyBorder(12, 22, 12, 22)
        );

        botao.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );
    }

    private void estilizarCampo(JTextField campo) {

        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setForeground(texto);
        campo.setBackground(new Color(250, 248, 255));

        campo.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(203, 182, 235)
                ),
                BorderFactory.createEmptyBorder(9, 10, 9, 10)
            )
        );
    }

    private void atualizarAcervo() {

        areaAcervo.setText(
            biblioteca.obterAcervoComoTexto()
        );

        areaAcervo.setCaretPosition(0);
    }

    private void emprestar() {

        String titulo = campoTitulo.getText().trim();
        String matricula = campoMatricula.getText().trim();

        if (titulo.isEmpty() || matricula.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Preencha o título e a matrícula."
            );

            return;
        }

        boolean resultado =
            biblioteca.emprestar(titulo, matricula);

        atualizarAcervo();

        if (resultado) {

            JOptionPane.showMessageDialog(
                this,
                "Empréstimo realizado com sucesso!"
            );

            campoTitulo.setText("");
            campoMatricula.setText("");

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Empréstimo recusado. Confira o título, "
                + "a matrícula, a disponibilidade e "
                + "o limite do leitor."
            );
        }
    }

    private void devolver() {

        String titulo = campoTitulo.getText().trim();

        if (titulo.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Preencha o título do livro."
            );

            return;
        }

        boolean resultado =
            biblioteca.devolver(titulo);

        atualizarAcervo();

        if (resultado) {

            JOptionPane.showMessageDialog(
                this,
                "Devolução registrada com sucesso!"
            );

            campoTitulo.setText("");
            campoMatricula.setText("");

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Nenhum empréstimo ativo encontrado "
                + "para esse título."
            );
        }
    }

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        biblioteca.cadastrarLivro(
            new Livro(
                "Dom Casmurro",
                "Machado de Assis",
                1899
            )
        );

        biblioteca.cadastrarLivro(
            new Livro(
                "Capitaes da Areia",
                "Jorge Amado",
                1937
            )
        );

        biblioteca.cadastrarLeitor(
            new Leitor(
                "Pedro Alves",
                "2026010",
                1
            )
        );

        biblioteca.cadastrarLeitor(
            new Leitor(
                "Ana Lima",
                "2026011",
                2
            )
        );

        SwingUtilities.invokeLater(() -> {

            TelaBiblioteca tela =
                new TelaBiblioteca(biblioteca);

            tela.setVisible(true);
        });
    }
}
