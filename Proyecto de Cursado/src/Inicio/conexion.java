package Inicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import quick.dbtable.DBTable;

public class Conexion {
	static Connection co;
	static Statement statement;
	
	//Establece una conexi�n con la base de datos Vuelos para un dado par usuario-password.
	public static Connection iniciarConnection(String usuario, String password) throws SQLException, Exception {
		Class.forName("com.mysql.jdbc.Driver");
		co = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/vuelos?serverTimezone=America/Argentina/Buenos_Aires", usuario,
				password);
		statement = co.createStatement();
		System.out.println("Conectado correctamente a la Base de Datos");
		return co;
	}
	
	//Conecta a un empleado con legajo y password dados a la base de datos Vuelos.
	public static boolean conctarEmpleado(String legajo, String password) throws SQLException, Exception {
		iniciarConnection("empleado", "empleado");
		ResultSet r = statement.executeQuery("SELECT\r\n e.legajo, e.password\r\n" +
											 "FROM (empleados as e)\r\n" +
											 "WHERE (e.legajo =" + legajo + ");");
		if (!r.next() || !r.getString(2).equals(password)) {
			r.close();
			statement.close();
			co.close();
			co = null;
			statement = null;
			return false;
		}
		return true;
	}
	
	//Retorna una lista de los nombres de las tablas de la base de datos.
	public static LinkedList<String> getTablas() throws SQLException {
		LinkedList<String> tablas = new LinkedList<String>();
		ResultSet r = statement.executeQuery("SHOW TABLES;");
		int i = 0;
		while (r.next())
			tablas.add(i++, r.getString(1));
		r.close();
		return tablas;
	}

	//Recibe el nombre de una tabla y retorna todos los artibutos de la misma.
	public static LinkedList<String> getAtributos(String tabla) throws SQLException {
		LinkedList<String> atributos = new LinkedList<String>();
		ResultSet r = statement.executeQuery("DESCRIBE " + tabla + ";");
		int i = 0;
		while (r.next())
			atributos.add(i++, r.getString(1));
		r.close();
		return atributos;
	}

	//Retorna todas las ciudades de la base de datos.
	public static LinkedList<String> getCiudades() throws SQLException {
		LinkedList<String> ciudades = new LinkedList<String>();
		ResultSet r = statement.executeQuery("SELECT\r\n u.ciudad, u.estado, u.pais\r\n FROM (ubicaciones as u);");
		int i = 0;
		while (r.next())
			ciudades.add(i++, r.getString(1) + ", " + r.getString(2) + ", " + r.getString(3));
		r.close();
		return ciudades;
	}
	
	//Ejecuta una consulta (una sentencia SELECT) en SQL sobre la base de datos Vuelos.
	public static DBTable ejecutarSELECT(String sql) throws SQLException, ClassNotFoundException {
		DBTable tabla = new DBTable();
		tabla.setSelectSql(sql);
		tabla.connectDatabase("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/vuelos?serverTimezone=America/Argentina/Buenos_Aires", "admin", "admin");
		tabla.createColumnModelFromQuery();
		tabla.refresh();
		return tabla;
	}

	//retorna una tabla con los vuelos de ida almacenados en la base de datos.
	public static DBTable getVuelos(String cSalida, String cLlegada) throws SQLException { //TODOS LOS TIEMPOS SON FECHAS!
		ResultSet r = statement.executeQuery("SELECT DISTINCT\r\n vd.Nro_Vuelo, vd.Aeropuerto_Salida, vd.Hora_Salida," +
																  "vd.Aeropuerto_Llegada, vd.Hora_Llegada, vd.Modelo_Avion," +
																  "vd.Tiempo_Estimado" +
																  "\r\nFROM (vuelos_disponibles as vd)" +
																  "\r\nWHERE (vd.Ciudad_Salida = \"" + cSalida + "\" AND vd.Ciudad_Llegada = \"" + cLlegada + "\");");
		
		DBTable tabla = new DBTable();
		tabla.refresh(r);
		r.close();
		return tabla;
	}	
}