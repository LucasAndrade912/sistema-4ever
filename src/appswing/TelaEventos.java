package appswing;

import modelosDeNegocio.Evento;
import modelosDeNegocio.Ingresso;
import regrasDeNegocio.Fachada;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class TelaEventos {
    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton button_1;
    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JLabel label_5;

    private JButton button;
    private JButton button_2;
    private JButton button_4;

    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    /**
     * Create the application.
     */
    public TelaEventos() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                listagem();
            }
        });
        frame.setTitle("Eventos");
        frame.setBounds(100, 100, 575, 420);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 11, 315, 172);
        frame.getContentPane().add(scrollPane);

        table = new JTable() {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() >= 0) {
                    Evento evento = null;
                    for (Evento ev : Fachada.listarEventos()) {
                        table.getValueAt(table.getSelectedRow(), 0);
                        if (ev.getId() == (int) table.getValueAt(table.getSelectedRow(), 0)) evento = ev;
                    }

                    assert evento != null;
                    StringBuilder ingressos = new StringBuilder();
                    for (Ingresso ingresso : evento.getIngressos()) {
                        ingressos.append(ingresso.getCodigo() + " " + ingresso.getTelefone() + ", ");
                    }
                    label.setText("selecionado=" + ingressos);
                }
            }
        });
        table.setGridColor(Color.BLACK);
        table.setRequestFocusEnabled(false);
        table.setFocusable(false);
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        scrollPane.setViewportView(table);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        button_1 = new JButton("Apagar Selecionado");
        button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (table.getSelectedRow() >= 0) {
                        int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                        //confirmação
                        Object[] options = {"Confirmar", "Cancelar"};
                        int escolha = JOptionPane.showOptionDialog(null, "Confirma exclusão " + id, "Alerta",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                        if (escolha == 0) {
                            Fachada.apagarEvento(id);
                            label.setText("exclusão realizada");
                            listagem();
                        }
                    } else
                        label.setText("selecione uma linha");
                } catch (Exception erro) {
                    label.setText(erro.getMessage());
                }
            }
        });
        button_1.setBounds(380, 49, 160, 23);
        frame.getContentPane().add(button_1);

        label = new JLabel("");
        label.setForeground(Color.RED);
        label.setBounds(26, 340, 514, 14);
        frame.getContentPane().add(label);

        label_1 = new JLabel("data:");
        label_1.setHorizontalAlignment(SwingConstants.LEFT);
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_1.setBounds(26, 220, 71, 14);
        frame.getContentPane().add(label_1);

        label_2 = new JLabel("descricao:");
        label_2.setHorizontalAlignment(SwingConstants.LEFT);
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_2.setBounds(26, 245, 71, 14);
        frame.getContentPane().add(label_2);

        label_3 = new JLabel("capacidade:");
        label_3.setHorizontalAlignment(SwingConstants.LEFT);
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_3.setBounds(26, 270, 71, 14);
        frame.getContentPane().add(label_3);

        label_5 = new JLabel("preco:");
        label_5.setHorizontalAlignment(SwingConstants.LEFT);
        label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_5.setBounds(26, 295, 71, 14);
        frame.getContentPane().add(label_5);

        label_4 = new JLabel("selecione uma linha");
        label_4.setBounds(26, 181, 315, 14);
        frame.getContentPane().add(label_4);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(110, 218, 105, 20);
        frame.getContentPane().add(textField);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(110, 243, 105, 20);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(110, 268, 105, 20);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(110, 293, 105, 20);
        frame.getContentPane().add(textField_3);

        button = new JButton("Criar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textField.getText().isEmpty() || textField_1.getText().isEmpty()) {
                        label.setText("campo vazio");
                        return;
                    }
                    String data = textField.getText();
                    String descricao = textField_1.getText();
                    String capacidade = textField_2.getText();
                    String preco = textField_3.getText();
                    Fachada.criarEvento(data, descricao, Integer.parseInt(capacidade), Double.parseDouble(preco));
                    label.setText("evento criado");
                    listagem();
                } catch (Exception ex) {
                    label.setText(ex.getMessage());
                }
            }
        });
        button.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button.setBounds(220, 216, 95, 23);
        frame.getContentPane().add(button);

        button_4 = new JButton("Listar");
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listagem();
            }
        });
        button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button_4.setBounds(380, 14, 160, 23);
        frame.getContentPane().add(button_4);
    }

    public void listagem() {
        try {
            List<Evento> lista = Fachada.listarEventos();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Data");
            model.addColumn("Capacidade");
            model.addColumn("Preco");
            model.addColumn("Qtd. Ingressos");
            model.addColumn("Total Arrecadado");
            model.addColumn("Lotado");
            for (Evento e : lista)
                model.addRow(new Object[]{e.getId(), e.getData(), e.getCapacidade(), e.getPreco(), e.quantidadeIngressos(), e.totalArrecadado(), e.lotado()});

            table.setModel(model);
            label_4.setText("resultados: " + lista.size() + " linhas  - selecione uma linha");

            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        //desabilita
            table.getColumnModel().getColumn(1).setMaxWidth(100);
            table.getColumnModel().getColumn(2).setMaxWidth(100);
            table.getColumnModel().getColumn(3).setMaxWidth(100);
            table.getColumnModel().getColumn(4).setMaxWidth(100);
            table.getColumnModel().getColumn(5).setMaxWidth(100);
            table.getColumnModel().getColumn(6).setMaxWidth(100);
            table.getColumnModel().getColumn(7).setMaxWidth(100);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
        } catch (Exception erro) {
            label.setText(erro.getMessage());
        }
    }
}
