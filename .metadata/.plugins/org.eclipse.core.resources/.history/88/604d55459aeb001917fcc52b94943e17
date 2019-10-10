package Inicio;

import java.sql.Date;
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
			tabla = Conexion.ejecutarSentenciaSQL(sentencia);
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
	
	@SuppressWarnings("static-access")
	public Date[] obtenerFechas(String partida, String destino) {
		Date[] arreglo = {};
		try {
			LinkedList<Date> listaFechas = conexion.getFechas(partida, destino);
			arreglo = setearArregloFechas(listaFechas);
		} catch (SQLException e) {
			imprimirError(e);
		}
		return arreglo;
	}
	
	@SuppressWarnings("static-access")
	public DBTable obtenerVuelos(String cSalida, String cLlegada, Date fechaVuelo) {
		DBTable tabla = null;
		try {
			tabla = conexion.getVuelos(cSalida, cLlegada, fechaVuelo);
		} catch (SQLException e) {
			imprimirError(e);
		}
		return tabla;
	}
	
	@SuppressWarnings("static-access")
	public String[] obtenerClasesVuelo(String nroVuelo) {
		String[] arreglo = {};
		try {
			LinkedList<String> listaClases = conexion.getClasesVuelo(nroVuelo);
			arreglo = setearArreglo(listaClases);
		} catch (SQLException e) {
			imprimirError(e);
		}
		return arreglo;
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
	private Date[] setearArregloFechas(LinkedList<Date> lista) {
		Date[] arreglo = new Date[lista.size()];
		int i = 0;
		for (Date s : lista) {
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
