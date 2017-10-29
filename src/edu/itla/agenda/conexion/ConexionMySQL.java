package edu.itla.agenda.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.itla.agenda.entidades.Persona;

public class ConexionMySQL implements Conexion {
	private Connection conexion;
	private Statement enunciado;

	@Override
	public void conectar(String usuario, String clave, String ip, String baseDeDatos) {
		// Cargar en memoria el driver de MySQL
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://" + ip +"/" + baseDeDatos+ "?user=" + usuario + "&password=" + clave);
			enunciado = conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void guardar(Persona persona) {
		StringBuilder consulta = new StringBuilder();
		consulta.append("INSERT INTO personas(nombre,apellido,telefono) values('")
		.append(persona.getNombre()).append("'")
		.append(", '").append(persona.getApellido()).append("'")
		.append(", '").append(persona.getTelefono()).append("')");
		try {
			System.out.println(consulta.toString());
			enunciado.execute(consulta.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Persona[] obtener() {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		try {
			ResultSet resultado = enunciado.executeQuery("SELECT nombre, apellido, telefono from personas");
			while(resultado.next()) {
				personas.add(new Persona(resultado.getString("nombre"),resultado.getString("apellido"),resultado.getString("telefono")));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Persona[] arregloPersonas = new Persona[personas.size()];
		for(int i = 0 ; i < personas.size() ; i++) {
			arregloPersonas[i] = personas.get(i);
		}
		return arregloPersonas;
	}

}
