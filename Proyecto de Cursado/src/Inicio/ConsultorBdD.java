package Inicio;

import java.sql.SQLException;

public class ConsultorBdD {
	private String baseDatos = "vuelos", url;

	
	public ConsultorBdD(String servidor) {

		/*
		 * url = "jdbc:mysql://" + servidor + "/" + baseDatos +
		 * "?serverTimezone=America/Argentina/Buenos_Aires"; try {
		 * Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 * java.sql.DriverManager.getConnection(url); } catch (SQLException ex) {
		 * System.out.println("SQL Exception: " + ex.getMessage());
		 * System.out.println("SQL State: " + ex.getSQLState());
		 * System.out.println("SQL Error Code: " + ex.getErrorCode()); } catch
		 * (ClassNotFoundException e) { e.printStackTrace(); } }
		 */
	}

	public boolean chequearAdministrador(String password) {
		java.sql.Connection cnx=null;
		String servidor = "localhost:3306";
		try {
			cnx = java.sql.DriverManager.getConnection(
					"jdbc:mysql://"+servidor+"/vuelos?serverTimezone=America/Argentina/Buenos_Aires", "admin", password);
			System.out.println("Conectado correctamente a la Base de Datos");
	
        } catch (SQLException e) {
            System.out.println("Error de conexion: "+e);
        } catch (Exception e) {
            System.out.println("Error desconocido: "+e);
        }
		return cnx != null;
	}
	/*public boolean login(String usuario, String contrasena) {
    boolean resultado = false;

    sSQL = "SELECT u.nombre FROM usuario U WHERE u.usuario='"
            + usuario + "' AND u.contrasena='" + contrasena + "'";

    // Java 7 try-with-resources
    try (Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sSQL)) {

        resultado = rs.next();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "SQLException:\n" + e, "Error: Logica_usuario.tableRegistros(String buscar)", JOptionPane.ERROR_MESSAGE);
        }

    return resultado;
}*/
	public boolean chequearEmpleado(String legajo, String password) {
		try {
			return java.sql.DriverManager.getConnection(url,"empleando", password) != null;
		} catch (SQLException ex) {
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQL State: " + ex.getSQLState());
			System.out.println("SQL Error Code: " + ex.getErrorCode());
		}
		return false;
	}
}
