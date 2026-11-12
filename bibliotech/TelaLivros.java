
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
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

    public TelaLivros() {

        setTitle("BiblioTech - Gerenciamento de Livros");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        modelo = new DefaultTableModel(
            new Object[]{"ID", "Capa", "Título", "Autor", "Ano"}, 0
        ) {
            public boolean isCellEditable(int l, int c) {
                return false;
            }
        };

        tabela = new JTable(modelo);

        tabela.setRowHeight(85);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabela.setBackground(new Color(248, 250, 252));
        tabela.setForeground(new Color(30, 41, 59));
        tabela.setSelectionBackground(new Color(191, 219, 254));

        tabela.getTableHeader().setFont(
            new Font("SansSerif", Font.BOLD, 14)
        );

        tabela.getTableHeader().setBackground(
            new Color(30, 64, 175)
        );

        tabela.getTableHeader().setForeground(Color.WHITE);

        tabela.getColumnModel().getColumn(1).setPreferredWidth(90);

        tabela.getColumnModel().getColumn(1).setCellRenderer(
            new DefaultTableCellRenderer() {

                @Override
                public Component getTableCellRendererComponent(
                    JTable tabela, Object valor,
                    boolean selecionada, boolean foco,
                    int linha, int coluna) {

                    JLabel label = new JLabel();
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setOpaque(true);

                    label.setBackground(
                        selecionada
                        ? tabela.getSelectionBackground()
                        : tabela.getBackground()
                    );

                    if (valor instanceof ImageIcon) {
                        label.setIcon((ImageIcon) valor);
                    } else {
                        label.setText("Sem capa");
                    }

                    return label;
                }
            }
        );

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel controles = new JPanel(new FlowLayout());
        controles.setBackground(new Color(226, 232, 240));

        JButton buscarId = new JButton("Buscar por ID");
        JButton buscarTitulo = new JButton("Buscar por título");
        JButton atualizar = new JButton("Atualizar");
        JButton todos = new JButton("Mostrar todos");
        JButton botaoCapa = new JButton("Selecionar capa");

        controles.add(new JLabel("ID:"));
        controles.add(campoId);
        controles.add(buscarId);

        controles.add(new JLabel("Título:"));
        controles.add(campoTitulo);
        controles.add(buscarTitulo);

        controles.add(atualizar);
        controles.add(todos);
        controles.add(botaoCapa);

        add(controles, BorderLayout.NORTH);

        status.setBorder(
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );

        add(status, BorderLayout.SOUTH);

        buscarId.addActionListener(e -> buscarLivro());
        buscarTitulo.addActionListener(e -> buscarPorTitulo());
        atualizar.addActionListener(e -> carregarLivros());
        todos.addActionListener(e -> carregarLivros());
        botaoCapa.addActionListener(e -> selecionarCapa());

        campoId.addActionListener(e -> buscarLivro());
        campoTitulo.addActionListener(e -> buscarPorTitulo());

        carregarLivros();
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

            Image reduzida = imagem.getScaledInstance(
                55, 75, Image.SCALE_SMOOTH
            );

            return new ImageIcon(reduzida);

        } catch (Exception e) {
            return null;
        }
    }

    private void carregarLivros() {

        modelo.setRowCount(0);

        List<Livro> livros = dao.listarLivros();

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
                JOptionPane.showMessageDialog(this,
                    "Livro não encontrado.");
            } else {
                adicionarLinha(livro);
                status.setText("Livro encontrado: " + livro.getTitulo());
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Informe um ID válido.");
        }
    }

    private void buscarPorTitulo() {

        String titulo = campoTitulo.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Informe o título.");
            return;
        }

        List<Livro> livros = dao.buscarLivrosPorTitulo(titulo);

        modelo.setRowCount(0);

        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        status.setText("Livros encontrados: " + livros.size());

        if (livros.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nenhum livro encontrado.");
        }
    }

    private void selecionarCapa() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this,
                "Selecione um livro na tabela.");
            return;
        }

        int linhaModelo = tabela.convertRowIndexToModel(linha);

        int id = (int) modelo.getValueAt(linhaModelo, 0);

        JFileChooser seletor = new JFileChooser();

        seletor.setDialogTitle("Selecione a capa do livro");

        seletor.setFileFilter(
            new javax.swing.filechooser.FileNameExtensionFilter(
                "Imagens JPG e PNG", "jpg", "jpeg", "png"
            )
        );

        if (seletor.showOpenDialog(this)
                != JFileChooser.APPROVE_OPTION) {
            return;
        }

        Path destino = null;

        try {

            destino = Path.of(
                ImagemLivro.salvar(
                    seletor.getSelectedFile().toPath(),
                    id
                )
            );

            boolean atualizado = dao.atualizarCapa(
                id, destino.toString().replace('\\', '/')
            );

            if (!atualizado) {

                Files.deleteIfExists(destino);

                JOptionPane.showMessageDialog(this,
                    "Não foi possível atualizar o livro.");

                return;
            }

            carregarLivros();

            JOptionPane.showMessageDialog(this,
                "Capa salva com sucesso!");

        } catch (Exception ex) {

            if (destino != null) {
                try {
                    Files.deleteIfExists(destino);
                } catch (Exception ignorado) {
                }
            }

            JOptionPane.showMessageDialog(this,
                "Erro ao salvar capa: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TelaLivros().setVisible(true);
        });
    }
}

