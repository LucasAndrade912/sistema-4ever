package appswing;

import modelosDeNegocio.Convidado;
import modelosDeNegocio.Evento;
import modelosDeNegocio.Ingresso;
import modelosDeNegocio.Participante;
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
import java.util.Objects;

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

public class TelaIngressos {
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
    public TelaIngressos() {
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
        frame.setTitle("Ingressos");
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
                    label.setText("selecionado=" + table.getValueAt(table.getSelectedRow(), 0));
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
                        String codigo = (String) table.getValueAt(table.getSelectedRow(), 0);
                        //confirmação
                        Object[] options = {"Confirmar", "Cancelar"};
                        int escolha = JOptionPane.showOptionDialog(null, "Confirma exclusão " + codigo, "Alerta",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                        if (escolha == 0) {
                            Fachada.apagarIngresso(codigo);
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

        label_1 = new JLabel("id:");
        label_1.setHorizontalAlignment(SwingConstants.LEFT);
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_1.setBounds(26, 220, 71, 14);
        frame.getContentPane().add(label_1);

        label_2 = new JLabel("cpf:");
        label_2.setHorizontalAlignment(SwingConstants.LEFT);
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_2.setBounds(26, 245, 71, 14);
        frame.getContentPane().add(label_2);

        label_3 = new JLabel("telefone:");
        label_3.setHorizontalAlignment(SwingConstants.LEFT);
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_3.setBounds(26, 270, 71, 14);
        frame.getContentPane().add(label_3);

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

        button = new JButton("Criar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textField.getText().isEmpty() || textField_1.getText().isEmpty()) {
                        label.setText("campo vazio");
                        return;
                    }
                    String id = textField.getText();
                    String cpf = textField_1.getText();
                    String telefone = textField_2.getText();

                    Fachada.criarIngresso(Integer.parseInt(id), cpf, telefone);

                    label.setText("ingresso criado");
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
            List<Ingresso> lista = Fachada.listarIngressos();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Código");
            model.addColumn("Telefone");
            model.addColumn("Preco Ingresso");
            model.addColumn("Preco Evento");
            model.addColumn("Data Evento");
            model.addColumn("Idade Participante");
            for (Ingresso i : lista)
                model.addRow(new Object[]{i.getCodigo(), i.getTelefone(), i.calcularPreco(), i.getEvento().getPreco(), i.getEvento().getData(), i.getParticipante().calcularIdade()});

            table.setModel(model);
            label_4.setText("resultados: " + lista.size() + " linhas  - selecione uma linha");

            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        //desabilita
            table.getColumnModel().getColumn(1).setMinWidth(100);
            table.getColumnModel().getColumn(2).setMinWidth(100);
            table.getColumnModel().getColumn(3).setMinWidth(100);
            table.getColumnModel().getColumn(4).setMinWidth(100);
            table.getColumnModel().getColumn(5).setMinWidth(100);
            table.getColumnModel().getColumn(6).setMinWidth(100);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
        } catch (Exception erro) {
            label.setText(erro.getMessage());
        }
    }
}
