package com.faiticapi.jarvest;

import java.io.InputStreamReader;
import java.io.Reader;

import es.uvigo.ei.sing.jarvest.dsl.Jarvest;

/**
 * Ejecuta el robot que descarga los archivos
 * @author Abel Castro Lamas
 *
 */
public class FileRobot extends JarvestRobot {

	private String id;
	private String url;
	private String login; 
	private String pass;
	
	public FileRobot( String id, String url, String login, String pass ) {
		this.id = id;
		this.url = url;
		this.login = login;
		this.pass = pass;
	}
	
	public String[] _run () {
		try {
			Jarvest jarvest = new Jarvest();
			Reader reader = new InputStreamReader( getClass().getResourceAsStream( "/robots/fileRobot.rb" ) );

			String[] results = jarvest.exec( reader, id, login, pass, url );

			return results;
		} catch ( Exception e ) { return null; }
	}
}
