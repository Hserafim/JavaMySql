package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Clientes;

public class Cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setBounds(10, 25, 86, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Cpf");
		lblNewLabel_1.setBounds(116, 11, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtCpf = new JTextField();
		txtCpf.setBounds(116, 25, 86, 20);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Cidade");
		lblNewLabel_2.setBounds(224, 11, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtCidade = new JTextField();
		txtCidade.setBounds(222, 25, 86, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Estado");
		lblNewLabel_3.setBounds(326, 11, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtEstado = new JTextField();
		txtEstado.setBounds(325, 25, 86, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Botão que salva
				salvar();

			}
		});
		btnSalvar.setBounds(322, 227, 89, 23);
		contentPane.add(btnSalvar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 414, 140);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(
				new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Cpf", "Cidade", "Estado"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		scrollPane.setViewportView(table);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Botão que deleta
				deletarCliente();
			}
		});
		btnDeletar.setBounds(163, 227, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Botão que atualiza
				atualizarCliente();
			}
		});
		btnAtualizar.setBounds(20, 227, 89, 23);
		contentPane.add(btnAtualizar);
		refreshTB();
	}

	// Métodos
	
	public void atualizarCliente() {

		boolean status;
		Clientes cliente = new Clientes();
		Controller banco = new Controller();

		cliente.setId((int) table.getValueAt(table.getSelectedRow(), 0));

		cliente.setNome(txtNome.getText());
		cliente.setCpf(txtCpf.getText());
		cliente.setCidade(txtCidade.getText());
		cliente.setEstado(txtEstado.getText());
		
		

		status = banco.updateCadastro(cliente);

		if (status) {

			cleanFields();
			refreshTB();
			JOptionPane.showMessageDialog(null, "Cliente " + cliente.getNome() + "  foi atulizado", "Cadastro",
					JOptionPane.INFORMATION_MESSAGE);

		} else {

			JOptionPane.showMessageDialog(null, "ERRO ao tentar atualizar o cliente " + cliente.getNome(), "ERRO",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}
	
	public void deletarCliente() {

		int confirm = JOptionPane.showConfirmDialog(null,
				"Deseja apagar o cliente " + table.getValueAt(table.getSelectedRow(), 1), "Aviso",
				JOptionPane.YES_NO_OPTION);

		if (confirm == 0) { // The ISSUE is here

			Clientes cliente = new Clientes();
			Controller banco = new Controller();

			cliente.setId((int) table.getValueAt(table.getSelectedRow(), 0));

			banco.deletarCadastro(cliente);

			cleanFields();
			refreshTB();

		}

	}
	
	public void salvar() {

		Clientes cliente = new Clientes();
		Controller banco = new Controller();
		Boolean status;

		cliente.setNome(txtNome.getText());
		cliente.setCpf(txtCpf.getText());
		cliente.setCidade(txtCidade.getText());
		cliente.setEstado(txtEstado.getText());

		status = banco.addCastro(cliente);

		 refreshTB();

		if (status) {

			cleanFields();

			JOptionPane.showMessageDialog(null, "Cliente " + cliente.getNome() + " salvo no banco de dados", "Cadastro",
					JOptionPane.INFORMATION_MESSAGE);

		} else {

			JOptionPane.showMessageDialog(null,
					"ERRO ao tentar salvar o cliente " + cliente.getNome() + " no banco de dados", "Cadastro",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}
	
	public void refreshTB() {
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		 
        modelo.setNumRows(0);
 
        Controller banco = new Controller();
 
        for (Clientes c : banco.buscarClientes("")) {
 
            modelo.addRow(new Object[] { c.getId(), c.getNome(), c.getCpf(), c.getCidade(), c.getEstado()});
 
        }
	}

	public void cleanFields() {

		txtNome.setText("");
		txtCpf.setText("");
		txtCidade.setText("");
		txtEstado.setText("");
	}
}
