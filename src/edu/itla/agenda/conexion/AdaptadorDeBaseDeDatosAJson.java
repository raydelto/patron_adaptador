package edu.itla.agenda.conexion;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;

import edu.itla.agenda.entidades.Persona;

public class AdaptadorDeBaseDeDatosAJson implements Conexion {
	private Scanner lectorArchivo;

	public AdaptadorDeBaseDeDatosAJson() {
		try {
			lectorArchivo = new Scanner(new FileReader("personas.json"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void conectar(String usuario, String clave, String ip, String baseDeDatos) {

	}

	@Override
	public void guardar(Persona persona) {
		Persona[] personas = obtener();
		Persona[] personasActualizadas = new Persona[personas.length + 1];
		for (int i = 0; i < personas.length; i++) {
			personasActualizadas[i] = personas[i];
		}
		personasActualizadas[personas.length] = persona;
		String json = JsonStream.serialize(personasActualizadas);
		try {
			PrintWriter escritorArchivo = new PrintWriter(new FileWriter("personas.json"),true);
			escritorArchivo.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public Persona[] obtener() {
		Persona[] resultado = null;
		String json = lectorArchivo.nextLine();
		resultado = JsonIterator.deserialize(json, Persona[].class);
		return resultado;
	}

}
