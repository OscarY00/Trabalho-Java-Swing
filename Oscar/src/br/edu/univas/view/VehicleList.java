package br.edu.univas.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import br.edu.univas.dao.VehicleDAO;
import br.edu.univas.vo.Vehicle;

public class VehicleList extends JPanel {

	private JTable carTable;
	private VehicleDAO vehicleDAO;

	public VehicleList() throws SQLException {
		vehicleDAO = new VehicleDAO();
		initialize();
	}

	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel emailLabel = new JLabel();
		emailLabel.setText("Veículos Cadastrados:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.0;
		
		gbc.fill = GridBagConstraints.NONE;
		this.add(emailLabel, gbc);

		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Id");
		columnNames.add("Marca");
		columnNames.add("Modelo");
		columnNames.add("Cor");
		columnNames.add("Placa");

		Vector<? extends Vector> vector = new Vector();
		carTable = new JTable(vector, columnNames);

		JScrollPane TableScroll = new JScrollPane(carTable);
		TableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		TableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		TableScroll.setMinimumSize(new Dimension(750, 300));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		carTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				int ind = carTable.getSelectedRow();
				// System.out.println("Linha: " + ind);

			}
		});
		this.add(TableScroll, gbc);

		JButton editButton = new JButton();
		editButton.setText("Editar");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					EditarVeiculo editar = new EditarVeiculo();
					editar.setVisible(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});

		this.add(editButton, gbc);

		
	}



	public void updateTable() {
		DefaultTableModel tableModel = (DefaultTableModel) carTable.getModel();
		tableModel.setRowCount(0);

		for (Vehicle vehicle : vehicleDAO.getAll()) {
			Object[] data = { vehicle.getId(), vehicle.getMarca(), vehicle.getModelo(), vehicle.getCor(),
					vehicle.getPlaca() };

			tableModel.addRow(data);
		}
	}

}
