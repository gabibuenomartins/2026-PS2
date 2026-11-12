java

```java

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TelaLivros extends JFrame {

    private LivroDAO dao = new LivroDAO();
    private DefaultTableModel modelo;
    private JTable tabela;

    private JTextField campoId = new JTextField(6);
    private JTextField campoTitulo = new JTextField(15);
    private JLabel status = new JLabel("Pronto.");

    private final Color roxo = new Color(126, 87, 194);
    private final Color lilas = new Color(139, 92, 246);
    private final Color fundo = new Color(247, 243, 255);
    private final Color texto = new Color(55, 40, 75);

    public TelaLivros() {

        setTitle("BiblioTech - Gerenciamento de Livros");
        setSize(1000, 600);
        setMinimumSize(new Dimension(850, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));

        getContentPane().setBackground(fundo);

        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBackground(roxo);
        cabecalho.setBorder(
            BorderFactory.createEmptyBorder(18, 25, 18, 25)
        );

        JLabel titulo = new JLabel("BiblioTech | Gerenciamento de Livros");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Organize seu acervo literário");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitulo.setForeground(new Color(240, 230, 255));

        cabecalho.add(titulo, BorderLayout.NORTH);
        cabecalho.add(subtitulo, BorderLayout.SOUTH);

        add(cabecalho, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
            new Object[]{"ID", "Capa", "Título", "Autor", "Ano"}, 0
        ) {
            @Override
            public boolean isCellEditable(int l, int c) {
                return false;
            }
        };

        tabela = new JTable(modelo);

        tabela.setRowHeight(90);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabela.setBackground(Color.WHITE);
        tabela.setForeground(texto);
        tabela.setSelectionBackground(new Color(232, 218, 255));
        tabela.setSelectionForeground(new Color(76, 29, 149));
        tabela.setGridColor(new Color(237, 229, 248));
        tabela.setShowVerticalLines(false);
        tabela.setShowHorizontalLines(true);
        tabela.setFillsViewportHeight(true);
        tabela.setIntercellSpacing(new Dimension(0, 1));

        tabela.getTableHeader().setFont(
            new Font("SansSerif", Font.BOLD, 14)
        );

        tabela.getTableHeader().setBackground(roxo);
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.getTableHeader().setPreferredSize(
            new Dimension(100, 42)
        );
        tabela.getTableHeader().setReorderingAllowed(false);

        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(300);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(250);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(80);

        tabela.getColumnModel().getColumn(1).setCellRenderer(
            new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                    JTable tabela, Object valor,
                    boolean selecionada, boolean foco,
                    int linha, int coluna) {

                    JLabel label = new JLabel();
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                    label.setOpaque(true);

                    label.setBackground(
                        selecionada
                        ? tabela.getSelectionBackground()
                        : Color.WHITE
                    );

                    if (valor instanceof ImageIcon) {
                        label.setIcon((ImageIcon) valor);
                        label.setText("");
                    } else {
                        label.setIcon(null);
                        label.setText("Sem capa");
                        label.setForeground(roxo);
                    }

                    return label;
                }
            }
        );

        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBorder(
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );
        rolagem.getViewport().setBackground(Color.WHITE);

        add(rolagem, BorderLayout.CENTER);

        JPanel controles = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 10, 15)
        );
        controles.setBackground(fundo);

        JButton buscarId = new JButton("Buscar por ID");
        JButton buscarTitulo = new JButton("Buscar por título");
        JButton atualizar = new JButton("Atualizar");
        JButton todos = new JButton("Mostrar todos");
        JButton botaoCapa = new JButton("Selecionar capa");

        estilizarBotao(buscarId);
        estilizarBotao(buscarTitulo);
        estilizarBotao(atualizar);
        estilizarBotao(todos);
        estilizarBotao(botaoCapa);

        estilizarCampo(campoId);
        estilizarCampo(campoTitulo);

        JLabel labelId = new JLabel("ID:");
        JLabel labelTitulo = new JLabel("Título:");

        labelId.setForeground(texto);
        labelTitulo.setForeground(texto);

        controles.add(labelId);
        controles.add(campoId);
        controles.add(buscarId);
        controles.add(labelTitulo);
        controles.add(campoTitulo);
        controles.add(buscarTitulo);
        controles.add(atualizar);
        controles.add(todos);
        controles.add(botaoCapa);

        JPanel painelStatus = new JPanel(new BorderLayout());
        painelStatus.setBackground(new Color(237, 229, 250));
        painelStatus.setBorder(
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        );

        status.setForeground(roxo);
        status.setFont(new Font("SansSerif", Font.BOLD, 12));

        painelStatus.add(status, BorderLayout.WEST);

        JPanel inferior = new JPanel(new BorderLayout());
        inferior.add(controles, BorderLayout.CENTER);
        inferior.add(painelStatus, BorderLayout.SOUTH);

        add(inferior, BorderLayout.SOUTH);

        buscarId.addActionListener(e -> buscarLivro());
        buscarTitulo.addActionListener(e -> buscarPorTitulo());
        atualizar.addActionListener(e -> carregarLivros());

        todos.addActionListener(e -> {
            campoId.setText("");
            campoTitulo.setText("");
            carregarLivros();
        });

        botaoCapa.addActionListener(e -> selecionarCapa());

        campoId.addActionListener(e -> buscarLivro());
        campoTitulo.addActionListener(e -> buscarPorTitulo());

        carregarLivros();
    }

    private void estilizarBotao(JButton botao) {

        botao.setBackground(lilas);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("SansSerif", Font.BOLD, 12));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setOpaque(true);

        botao.setBorder(
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        );

        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void estilizarCampo(JTextField campo) {

        campo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        campo.setForeground(texto);
        campo.setBackground(Color.WHITE);

        campo.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(203, 182, 235)
                ),
                BorderFactory.createEmptyBorder(7, 8, 7, 8)
            )
        );
    }

    private ImageIcon carregarMiniatura(String caminho) {

        if (caminho == null || caminho.isBlank()) {
            return null;
        }

        File arquivo = new File(caminho);

        if (!arquivo.isFile()) {
            return null;
        }

        try {

            BufferedImage imagem = ImageIO.read(arquivo);

            if (imagem == null) {
                return null;
            }

            int larguraMaxima = 55;
            int alturaMaxima = 75;

            double escala = Math.min(
                (double) larguraMaxima / imagem.getWidth(),
                (double) alturaMaxima / imagem.getHeight()
            );

            int largura = Math.max(
                1, (int) (imagem.getWidth() * escala)
            );

            int altura = Math.max(
                1, (int) (imagem.getHeight() * escala)
            );

            Image reduzida = imagem.getScaledInstance(
                largura, altura, Image.SCALE_SMOOTH
            );

            return new ImageIcon(reduzida);

        } catch (IOException e) {
            return null;
        }
    }

    private void carregarLivros() {

        modelo.setRowCount(0);

        List<Livro> livros = dao.listarLivros();

        if (livros == null) {
            status.setText("Erro ao consultar os livros.");
            return;
        }

        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        status.setText("Livros apresentados: " + livros.size());
    }

    private void adicionarLinha(Livro livro) {

        modelo.addRow(new Object[]{
            livro.getId(),
            carregarMiniatura(livro.getCapa()),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getAno()
        });
    }

    private void buscarLivro() {

        try {

            int id = Integer.parseInt(campoId.getText().trim());

            if (id <= 0) {
                throw new NumberFormatException();
            }

            Livro livro = dao.buscarLivroPorId(id);
            modelo.setRowCount(0);

            if (livro == null) {
                status.setText("Livro não encontrado.");
                JOptionPane.showMessageDialog(
                    this, "Livro não encontrado."
                );
            } else {
                adicionarLinha(livro);
                status.setText(
                    "Livro encontrado: " + livro.getTitulo()
                );
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this, "Informe um ID válido."
            );
        }
    }

    private void buscarPorTitulo() {

        String titulo = campoTitulo.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Informe o título."
            );
            return;
        }

        List<Livro> livros = dao.buscarLivrosPorTitulo(titulo);

        modelo.setRowCount(0);

        if (livros == null) {
            status.setText("Erro ao consultar os livros.");
            return;
        }

        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        status.setText("Livros encontrados: " + livros.size());

        if (livros.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Nenhum livro encontrado."
            );
        }
    }

    private void selecionarCapa() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(
                this, "Selecione um livro na tabela."
            );
            return;
        }

        int linhaModelo = tabela.convertRowIndexToModel(linha);

        int id = (int) modelo.getValueAt(linhaModelo, 0);

        JFileChooser seletor = new JFileChooser();

        seletor.setDialogTitle("Selecione a capa do livro");

        seletor.setFileFilter(
            new FileNameExtensionFilter(
                "Imagens JPG e PNG", "jpg", "jpeg", "png"
            )
        );

        if (seletor.showOpenDialog(this)
                != JFileChooser.APPROVE_OPTION) {
            return;
        }

        Path destino = null;

        try {

            String caminho = ImagemLivro.salvar(
                seletor.getSelectedFile().toPath(), id
            );

            destino = Path.of(caminho);

            boolean atualizado = dao.atualizarCapa(
                id, caminho.replace('\\', '/')
            );

            if (!atualizado) {
                Files.deleteIfExists(destino);

                JOptionPane.showMessageDialog(
                    this, "Não foi possível atualizar o livro."
                );
                return;
            }

            carregarLivros();

            JOptionPane.showMessageDialog(
                this,
                "Capa salva com sucesso!",
                "BiblioTech",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {

            if (destino != null) {
                try {
                    Files.deleteIfExists(destino);
                } catch (IOException ignorado) {
                }
            }

            JOptionPane.showMessageDialog(
                this, "Erro ao salvar capa: " + ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TelaLivros().setVisible(true);
        });
    }
}
