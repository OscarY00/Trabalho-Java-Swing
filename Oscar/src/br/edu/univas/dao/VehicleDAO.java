package br.edu.univas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyleContext.SmallAttributeSet;

import br.edu.univas.vo.Vehicle;

public class VehicleDAO {

	private Connection connection;

	public VehicleDAO() throws SQLException {
		connection = ConnectionUtil.getConnection();
	}

	public void newVehicle(Vehicle carro) {
		String sql = "insert into carros (id, marca, modelo, cor, placa) " + "values (?, ?, ?, ?, ?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			int index = 1;
			statement.setInt(index++, getLestId());
			statement.setString(index++, carro.getMarca());
			statement.setString(index++, carro.getModelo());
			statement.setString(index++, carro.getCor());
			statement.setString(index++, carro.getPlaca());
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Vehicle> getAll() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();

		String sql = "select * from carros";

		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Vehicle v = new Vehicle();
				v.setId(result.getInt("id"));
				v.setMarca(result.getString("marca"));
				v.setModelo(result.getString("modelo"));
				v.setCor(result.getString("cor"));
				v.setPlaca(result.getString("placa"));
				vehicles.add(v);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vehicles;
	}
	
	
	
	public void altera(Vehicle reg) throws SQLException {
		String sql = "UPDATE carros SET  marca = ?, modelo = ?, cor = ?, placa = ? WHERE id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, reg.getMarca());
		stmt.setString(2, reg.getModelo());
		stmt.setString(3, reg.getCor());
		stmt.setString(4, reg.getPlaca());
		stmt.setInt(5, reg.getId());
		stmt.execute();

	}
	
	
	public void del(Vehicle v) {
		String sql = "DELETE FROM carros WHERE marca = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, v.getMarca());
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private int getLestId() {
		String sql = "select id from carros where id = (select max(id) from carros)";

		int id = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				id = Integer.parseInt(result.getString("id"));
			}

			return id + 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return id;
		}

	}

	
}

