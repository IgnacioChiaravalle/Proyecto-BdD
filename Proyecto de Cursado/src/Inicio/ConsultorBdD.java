package Inicio;

import java.sql.SQLException;

public class ConsultorBdD {
	private String baseDatos = "vuelos", url;
	
	public ConsultorBdD(String servidor) {
		
		url = "jdbc:mysql://" + servidor + "/" + baseDatos + "?serverTimezone=America/Argentina/Buenos_Aires";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			java.sql.DriverManager.getConnection(url);
		} catch (SQLException ex) {
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQL State: " + ex.getSQLState());
			System.out.println("SQL Error Code: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean chequearAdministrador (String password) {
		try {
			return java.sql.DriverManager.getConnection(url + "admin" + password) != null;
		} catch (SQLException ex) {
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQL State: " + ex.getSQLState());
			System.out.println("SQL Error Code: " + ex.getErrorCode());
		}
		return false;
	}
	public boolean chequearEmpleado(String legajo, String password) {
		try {
			return java.sql.DriverManager.getConnection(url + legajo + password) != null;
		} catch (SQLException ex) {
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQL State: " + ex.getSQLState());
			System.out.println("SQL Error Code: " + ex.getErrorCode());
		}
		return false;
	}
}
