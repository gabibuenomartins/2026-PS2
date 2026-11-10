import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaLivros extends JFrame {

    private LivroDAO dao = new LivroDAO();
    private DefaultTableModel modelo;
    private JTable tabela;

    private JTextField campoId = new JTextField(6);
    private JTextField campoTitulo = new JTextField(15);
    private JLabel status = new JLabel("Pronto.");

    public TelaLivros() {

        setTitle("BiblioTech - Consulta de Livros");
        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        modelo = new DefaultTableModel(
            new Object[]{"ID", "Título", "Autor", "Ano"}, 0
        ) {
            public boolean isCellEditable(int l, int c) {
                return false;
            }
        };

        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel controles = new JPanel(new FlowLayout());

        JButton buscarId = new JButton("Buscar por ID");
        JButton buscarTitulo = new JButton("Buscar por título");
        JButton atualizar = new JButton("Atualizar");
        JButton todos = new JButton("Mostrar todos");

        controles.add(new JLabel("ID:"));
        controles.add(campoId);
        controles.add(buscarId);

        controles.add(new JLabel("Título:"));
        controles.add(campoTitulo);
        controles.add(buscarTitulo);

        controles.add(atualizar);
        controles.add(todos);

        add(controles, BorderLayout.NORTH);
        add(status, BorderLayout.SOUTH);

        buscarId.addActionListener(e -> buscarLivro());
        buscarTitulo.addActionListener(e -> buscarPorTitulo());
        atualizar.addActionListener(e -> carregarLivros());
        todos.addActionListener(e -> carregarLivros());

        campoId.addActionListener(e -> buscarLivro());
        campoTitulo.addActionListener(e -> buscarPorTitulo());

        carregarLivros();
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
                JOptionPane.showMessageDialog(this, "Livro não encontrado.");
            } else {
                adicionarLinha(livro);
                status.setText("Livro encontrado: " + livro.getTitulo());
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um ID válido.");
        }
    }

    private void buscarPorTitulo() {

        String titulo = campoTitulo.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o título.");
            return;
        }

        List<Livro> livros = dao.buscarLivrosPorTitulo(titulo);

        modelo.setRowCount(0);

        for (Livro livro : livros) {
            adicionarLinha(livro);
        }

        status.setText("Livros encontrados: " + livros.size());

        if (livros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum livro encontrado.");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TelaLivros().setVisible(true);
        });
    }
}
