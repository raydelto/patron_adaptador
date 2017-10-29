package edu.itla.agenda.conexion;

import edu.itla.agenda.entidades.Persona;

public interface Conexion {
	public void conectar(String usuario, String clave, String ip, String baseDeDatos);
	public void guardar(Persona persona);
	public Persona[] obtener();
}
