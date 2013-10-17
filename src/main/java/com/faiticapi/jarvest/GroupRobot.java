package com.faiticapi.jarvest;

import java.io.InputStreamReader;
import java.io.Reader;

import es.uvigo.ei.sing.jarvest.dsl.Jarvest;

/**
 * Ejecuta el robot que busca los grupos
 * @author Abel Castro Lamas
 *
 */
public class GroupRobot extends JarvestRobot {
	
	private String id; 
	private String login;
	private String pass;
	
	public  GroupRobot( String id, String login, String pass ) {
		this.id = id;
		this.login = login;
		this.pass = pass;
	}

	public String[] _run() {
		try {
			Jarvest jarvest = new Jarvest();	
			Reader reader = new InputStreamReader( getClass().getResourceAsStream( "/robots/groupRobot.rb" ) );
			String[] results = jarvest.exec( reader, id, login, pass );
	
			return results;	
		} catch( Exception e ) { return null; }
	}
}
