package Inicio;

import java.sql.SQLException;
import java.util.LinkedList;

import quick.dbtable.DBTable;

public class ConsultorBdD {
	Conexion conexion;
	
	public ConsultorBdD() { }

	@SuppressWarnings("static-access")
	public boolean chequearAdministrador(String password) {
		java.sql.Connection cnx = null;
		
		try {
			cnx = conexion.iniciarConnection("admin", password);
		}
		catch (SQLException e) {
			imprimirError(e);
		}
		catch (Exception e) {
            System.out.println("Error Desconocido:");
            e.printStackTrace();
        }
		
		return cnx != null;
	}
	
	@SuppressWarnings("static-access")
	public boolean chequearEmpleado(String legajo, String password) {
		boolean exito = false;
		try {
			exito = conexion.conctarEmpleado(legajo, password);
		}
		catch (SQLException e) {
			imprimirError(e);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}
	
	public DBTable comprobarSentencia(String sentencia) throws SQLException {
		DBTable tabla = null;
		try {
			tabla = Conexion.ejecutarSELECT(sentencia);
		} catch (ClassNotFoundException e) {
			System.out.println("Error Desconocido:");
            e.printStackTrace();
		}
		return tabla;
	}
	
	@SuppressWarnings("static-access")
	public String[] obtenerTablas() {
		String[] arreglo = {};
		try {
			LinkedList<String> listaTablas = conexion.getTablas();
			arreglo = setearArreglo(listaTablas);
		} catch (SQLException e) {
			imprimirError(e);
		}
		return arreglo;
	}
	
	@SuppressWarnings("static-access")
	public String[] obtenerAtributos(String nombreTabla) {
		String[] arreglo = {};
		try {
			LinkedList<String> listaAtributos = conexion.getAtributos(nombreTabla);
			arreglo = setearArreglo(listaAtributos);
		} catch (SQLException e) {
			imprimirError(e);
		}
		return arreglo;
	}
	
	@SuppressWarnings("static-access")
	public String[] obtenerCiudades() {
		String[] arreglo = {};
		try {
			LinkedList<String> listaCiudades = conexion.getCiudades();
			arreglo = setearArreglo(listaCiudades);
		} catch (SQLException e) {
			imprimirError(e);
		}
		return arreglo;
	}
	
	public String[] obtenerFechas(String partida, String destino) {
		String[] arreglo = {"1/1/1", "32/13/-1", "36/85/4655"};
		return arreglo;
	}
	
	public DBTable obtenerVuelos(String cSalida, String cLlegada) {
		DBTable tabla = null;
		try {
			tabla = Conexion.getVuelos(cSalida, cLlegada);
		} catch (SQLException e) {
			imprimirError(e);
		}
		return tabla;
	}
	
	
	private String[] setearArreglo(LinkedList<String> lista) {
		String[] arreglo = new String[lista.size()];
		int i = 0;
		for (String s : lista) {
			arreglo[i] = s;
			i++;
		}
		return arreglo;
	}
	
	private void imprimirError(SQLException e) {
		System.out.println("Error de Conexión:");
		System.out.println("SQL Exception: " + e.getMessage());
		System.out.println("SQL State: " + e.getSQLState());
		System.out.println("SQL Error Code: " + e.getErrorCode());
	}
}
