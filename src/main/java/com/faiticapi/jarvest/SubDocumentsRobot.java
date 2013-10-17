package com.faiticapi.jarvest;

import java.io.InputStreamReader;
import java.io.Reader;

import es.uvigo.ei.sing.jarvest.dsl.Jarvest;

public class SubDocumentsRobot extends JarvestRobot {

	private String id;
	private String folder;
	private String login;
	private String password;
	
	public SubDocumentsRobot() {}
	
	public SubDocumentsRobot( String id, String folder, String login, String password ) {
		this.id = id;
		this.folder = folder;
		this.login = login;
		this.password = password;
	}
	
	public String[] _run() {	
			try {
				
				Jarvest jarvest = new Jarvest();
			
				Reader reader = new InputStreamReader( getClass().getResourceAsStream( "/robots/subDocumentsSubject.rb" ) );
			
				String[] results = jarvest.exec( reader, id, folder, login, password );
			
			return results;
		
		} catch( Exception e ) { e.printStackTrace(); return null; }
	}

}
