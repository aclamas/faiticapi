package com.faiticapi.jarvest;

import java.io.InputStreamReader;
import java.io.Reader;

import es.uvigo.ei.sing.jarvest.dsl.Jarvest;

/**
 * Ejecuta el robot que busca la descripcion de un ejercicio
 * @author Abel Castro Lamas
 *
 */
public class InfoExerciseRobot extends JarvestRobot {

	private String id;
	private String idExercise;
	private String login;
	private String pass;
	
	public InfoExerciseRobot( String id, String idExercise, String login, String pass ) {
		this.id = id;
		this.idExercise = idExercise;
		this.login = login;
		this.pass = pass;
	}
	
	public String[] _run () {
		try {
			Jarvest jarvest = new Jarvest();
			Reader reader = new InputStreamReader(getClass().getResourceAsStream( "/robots/exerciseInfoRobot.rb"));
			String[] results = jarvest.exec( reader, id, idExercise, login, pass );
					
			return results;
		} catch ( Exception e ) { return null; }
	}	
}