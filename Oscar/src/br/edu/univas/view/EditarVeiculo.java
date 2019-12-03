package br.edu.univas.view;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.edu.univas.dao.VehicleDAO;
import br.edu.univas.vo.Vehicle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class EditarVeiculo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtMarca2;
	private JTextField txtModelo2;
	private JTextField txtCor2;
	private JTextField txtPlaca2;
	private VehicleDAO vehicleDAO;

	public EditarVeiculo() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 749, 248);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) {

					txtMarca2.setText((String) (table.getValueAt(table.getSelectedRow(), 1)));
					txtModelo2.setText((String) (table.getValueAt(table.getSelectedRow(), 2)));
					txtCor2.setText((String) (table.getValueAt(table.getSelectedRow(), 3)));
					txtPlaca2.setText((String) (table.getValueAt(table.getSelectedRow(), 4)));

				}
			}
		});

		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Marca", "Modelo", "Cor", "Placa" }) {
					boolean[] columnEditables = new boolean[] { false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		updateTable();
		scrollPane.setViewportView(table);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(10, 296, 46, 14);
		contentPane.add(lblMarca);

		txtMarca2 = new JTextField();
		txtMarca2.setBounds(66, 293, 320, 20);
		contentPane.add(txtMarca2);
		txtMarca2.setColumns(10);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(10, 331, 46, 14);
		contentPane.add(lblModelo);

		txtModelo2 = new JTextField();
		txtModelo2.setBounds(66, 328, 320, 20);
		contentPane.add(txtModelo2);
		txtModelo2.setColumns(10);

		JLabel lblCor = new JLabel("Cor");
		lblCor.setBounds(10, 370, 46, 14);
		contentPane.add(lblCor);

		txtCor2 = new JTextField();
		txtCor2.setBounds(66, 367, 320, 20);
		contentPane.add(txtCor2);
		txtCor2.setColumns(10);

		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(10, 403, 46, 14);
		contentPane.add(lblPlaca);
		
		
		try {
			txtPlaca2 = new JFormattedTextField(new MaskFormatter("UUU-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtPlaca2 = new JTextField();
		txtPlaca2.setBounds(66, 400, 320, 20);
		contentPane.add(txtPlaca2);
		txtPlaca2.setColumns(10);

		JButton btnSalvar = new JButton("");
		btnSalvar.setIcon(new ImageIcon(EditarVeiculo.class.getResource("/Images/disquete (1).png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRow() != -1) {

					Vehicle v = new Vehicle();
					try {
						VehicleDAO dao = new VehicleDAO();
						v.setMarca(txtMarca2.getText());
						v.setModelo(txtModelo2.getText());
						v.setCor(txtCor2.getText());
						v.setPlaca(txtPlaca2.getText());
						v.setId((int) table.getValueAt(table.getSelectedRow(), 0));
						dao.altera(v);

						txtMarca2.setText(null);
						txtModelo2.setText(null);
						txtCor2.setText(null);
						txtPlaca2.setText(null);

						updateTable();
						JOptionPane.showMessageDialog(null, "Dados Alterado Com Sucesso!");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
		btnSalvar.setBounds(10, 500, 90, 90);

		contentPane.add(btnSalvar);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon(EditarVeiculo.class.getResource("/Images/casa.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(684, 500, 90, 90);
		contentPane.add(btnVoltar);

		JButton btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					delete();
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.removeRow(table.getSelectedRow());
					txtMarca2.setText(null);
					txtModelo2.setText(null);
					txtCor2.setText(null);
					txtPlaca2.setText(null);
					JOptionPane.showMessageDialog(null, "Veículo Excluido com Sucesso!");
				}
			}
		});
		btnExcluir.setIcon(new ImageIcon(EditarVeiculo.class.getResource("/Images/excluir.png")));
		btnExcluir.setBounds(110, 500, 90, 90);
		contentPane.add(btnExcluir);
	}

	public void updateTable() throws SQLException {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		VehicleDAO vdao = new VehicleDAO();

		for (Vehicle vehicle : vdao.getAll()) {
			Object[] data = { vehicle.getId(), vehicle.getMarca(), vehicle.getModelo(), vehicle.getCor(),
					vehicle.getPlaca() };

			tableModel.addRow(data);
		}
	}

	public void delete() {
		if (table.getSelectedRow() != -1) {
			try {
				Vehicle v = new Vehicle();
				VehicleDAO dao = new VehicleDAO();
				v.setMarca((String) table.getValueAt(table.getSelectedRow(), 1));
				dao.del(v);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
