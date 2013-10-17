package com.faiticapi.jarvest;

import java.io.InputStreamReader;
import java.io.Reader;

import es.uvigo.ei.sing.jarvest.dsl.Jarvest;

/**
 * Ejecuta el robot que hace el login y busca las asignaturas
 * @author Abel Castro Lamas
 *
 */
public class LoginRobot extends JarvestRobot {

	private String login;
	private String password;

	public LoginRobot(String login, String password) { 
		this.login = login;
		this.password = password;
	}
	
	public String[] _run ()	{
		try {
			Jarvest jarvest = new Jarvest();
			Reader reader = new InputStreamReader( getClass().getResourceAsStream( "/robots/loginRobot.rb") );
			String[] results = jarvest.exec( reader, login, password );
			return results;	
		} catch ( Exception e ) { return null; }
	}
}
