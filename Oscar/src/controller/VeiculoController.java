package controller;

import java.sql.SQLException;

import br.edu.univas.dao.VehicleDAO;
import br.edu.univas.vo.Vehicle;

public class VeiculoController {
	public void addNewVehicle(Vehicle carro) throws SQLException {
		VehicleDAO vDAO = new VehicleDAO();
		vDAO.newVehicle(carro);
	}
}