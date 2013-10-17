package com.faiticapi.jarvest;

import java.io.InputStreamReader;
import java.io.Reader;

import es.uvigo.ei.sing.jarvest.dsl.Jarvest;

/**
 * Ejecuta el robot que busca las calificaciones
 * @author Abel Castro Lamas
 *
 */
public class MarkRobot extends JarvestRobot {
	
	private String id;
	private String login;
	private String password;
	
	public MarkRobot( String id, String login, String password ) {
		this.id = id;
		this.login = login;
		this.password = password;
	}
	
	public String[] _run () {
		try {
			Jarvest jarvest = new Jarvest();
			Reader reader = new InputStreamReader(getClass().getResourceAsStream( "/robots/markRobot.rb"));
			String[] results = jarvest.exec( reader, id, login, password );
				
			return results;
		} catch ( Exception e ) { return null; }
		
	}
			
}
