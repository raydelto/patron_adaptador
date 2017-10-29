package edu.itla.agenda;

import edu.itla.agenda.conexion.AdaptadorDeBaseDeDatosAJson;
import edu.itla.agenda.conexion.Conexion;
import edu.itla.agenda.conexion.ConexionMySQL;
import edu.itla.agenda.entidades.Persona;

public class Sistema {
	public static void main(String[] args) {
		Conexion conexion = new AdaptadorDeBaseDeDatosAJson();
		conexion.conectar("ddscards_agenda", "programacion2", "raydelto.org", "ddscards_agenda");
//		conexion.guardar(new Persona("Raydelto","Hernandez","8097387832"));
		Persona[] personas = conexion.obtener();
		for(Persona persona : personas) {
			System.out.println(persona);
		}
	}
}
