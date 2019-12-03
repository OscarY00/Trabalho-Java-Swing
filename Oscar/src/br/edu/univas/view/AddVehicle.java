package br.edu.univas.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.univas.dao.VehicleDAO;
import br.edu.univas.vo.Vehicle;
import controller.VeiculoController;

public class AddVehicle extends JPanel {

	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtCor;
	private JTextField txtPlaca;
	private VehicleDAO vehicleDAO;

	public AddVehicle() throws SQLException {
		vehicleDAO = new VehicleDAO();
		initialize();
	}

	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel marcaLabel = new JLabel();
		marcaLabel.setText("Marca:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(15, 15, 15, 15);
		this.add(marcaLabel, gbc);

		txtMarca = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(txtMarca, gbc);

		JLabel modeloLabel = new JLabel();
		modeloLabel.setText("Modelo:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(modeloLabel, gbc);

		txtModelo = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(txtModelo, gbc);

		JLabel corLabel = new JLabel();
		corLabel.setText("Cor:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(corLabel, gbc);

		txtCor = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(txtCor, gbc);

		JLabel placaLabel = new JLabel();
		placaLabel.setText("Placa:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(placaLabel, gbc);

		try {
			txtPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));

		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(txtPlaca, gbc);

		JButton saveButton = new JButton();
		saveButton.setText("Salvar");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					saveNewVehicle();
					txtPlaca.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(saveButton, gbc);
		
		
		
	}

	private void saveNewVehicle() throws SQLException {
		if (validateFields()) {

			Vehicle car = new Vehicle();
			car.setMarca(txtMarca.getText());
			car.setModelo(txtModelo.getText());
			car.setCor(txtCor.getText());
			car.setPlaca(txtPlaca.getText());
			VeiculoController veiculoController = new VeiculoController();
			veiculoController.addNewVehicle(car);

			AddVehicle addV = new AddVehicle();
			addV.setVisible(false);
			clearFields();
			JOptionPane.showMessageDialog(this, "Veiculo cadastrado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private boolean validateFields() {
		if (txtMarca.getText() == null || txtMarca.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, digite a Marca", "Campo vazio",
					JOptionPane.WARNING_MESSAGE);
			txtMarca.requestFocus();
			return false;
		}
		if (txtModelo.getText() == null || txtModelo.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, digite o Modelo", "Campo vazio",
					JOptionPane.WARNING_MESSAGE);
			txtModelo.requestFocus();
			return false;
		}
		if (txtCor.getText() == null || txtCor.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, digite a Cor", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			txtCor.requestFocus();
			return false;
		}

		if (txtPlaca.getText() == null || txtPlaca.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, digite a Placa", "Campo vazio",
					JOptionPane.WARNING_MESSAGE);
			txtPlaca.requestFocus();
			return false;
		}
		return true;
	}

	private void clearFields() {
		txtMarca.requestFocus();
		txtMarca.setText(null);
		txtModelo.setText(null);
		txtCor.setText(null);
		txtPlaca.setText(null);
	}

}
