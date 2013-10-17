package com.faiticapi.daos;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

import com.faiticapi.api.SubjectService;
import com.faiticapi.jarvest.AnouncementRobot;
import com.faiticapi.jarvest.DescriptionRobot;
import com.faiticapi.jarvest.DocumentRobot;
import com.faiticapi.jarvest.ExerciseRobot;
import com.faiticapi.jarvest.FileRobot;
import com.faiticapi.jarvest.GroupRobot;
import com.faiticapi.jarvest.InfoExerciseRobot;
import com.faiticapi.jarvest.JarvestRobot;
import com.faiticapi.jarvest.LoginRobot;
import com.faiticapi.jarvest.MarkRobot;
import com.faiticapi.jarvest.NotificationRobot;
import com.faiticapi.jarvest.SubDocumentsRobot;
import com.faiticapi.jarvest.SubjectRobot;
import com.faiticapi.model.Description;
import com.faiticapi.model.Document;
import com.faiticapi.model.Exercise;
import com.faiticapi.model.Mark;
import com.faiticapi.model.Section;
import com.faiticapi.model.Subject;

/**
 * Adaptador entre SubjectService y faitic. Aisla el funcionamiento de extraccion de la informacion de faitic de 
 * la manera que se reciben las peticiones
 * Crea la interfaz JarvestRobot y las clases de modelos las listas que se codificaran en json
 * @author Abel Castro Lamas
 * @see SubjectService
 * @see JarvestRobot
 */
public class SubjectDAO {
	private JarvestRobot jRobot;
	
	private List<Subject> listSubjects;
	private List<Document> listDocuments;
	private List<Exercise> listExercises;
	private List<Section> listSection;
	private List<Mark> listMarks;
	private List<Description> listDescription;

	private String[] results;

	public SubjectDAO() { }
	
	public List<Subject> getAllSubjects(String user, String pass) {
		jRobot = new LoginRobot(user, pass);
		results = jRobot.run();	
		listSubjects = new ArrayList<Subject>();
		if(results.length == 0)
			return null;
		for( int i = 0; i < results.length - 1; i++) {	
			String utf8;
				utf8 = new String( StringEscapeUtils.unescapeHtml4( results[i] ).getBytes(), Charset.forName("ISO-8859-1"));
				listSubjects.add( new Subject( utf8 , results[ ++i ] ) );					
		}	
		return listSubjects;
	}
	
	public List<Section> getSubject( String id, String user, String pass ) {	
		jRobot = new SubjectRobot( id, user, pass );
		results = jRobot.run();
		listSection = new ArrayList<Section>();
		
		for( int i = 0; i < results.length; i++) {
			try {
				String utf8 = new String(results[i].substring(6).getBytes("ISO-8859-1"), Charset.forName("ISO-8859-1"));
				listSection.add( new Section( utf8 ));
			} catch (UnsupportedEncodingException e) { }	
		}
		return listSection;
	}
	
	public List<Description> getDescription( String id, String user, String pass ) {
		jRobot = new DescriptionRobot( id, user, pass );
		results = jRobot.run();
		listDescription = new ArrayList<Description>();
		
		for( int i = 0; i < results.length; i++ ) {
			try {
				listDescription.add( new Description( new String( StringEscapeUtils.unescapeHtml4( results[i] ).getBytes("ISO-8859-1") , Charset.forName("ISO-8859-1") ) ) );
			} catch (UnsupportedEncodingException e) { }
		}
		return listDescription ;
	}
	
	public List<Document> getDocumentsSubject( String id, String user, String pass ) {
		jRobot = new DocumentRobot( id, user, pass );
		results = jRobot.run();
		listDocuments = new ArrayList<Document>();
		
		for( int i = 0; i < results.length; i++ ) {
			try {
				String utf8 = new String(results[i].getBytes("ISO-8859-1"), Charset.forName("ISO-8859-1"));
				String url = results[ ++i ];
				listDocuments.add( new Document( utf8, url ) );
			} catch (UnsupportedEncodingException e) { }
		}
		return listDocuments;
	}
	
	public List<Document> getSubDocumentSubject( String id, String folder, String user, String pass ) {
		jRobot = new SubDocumentsRobot( id, folder, user, pass );
		results = jRobot.run();
		listDocuments = new ArrayList<Document>();
		
		for( int i = 0; i < results.length; i++ ) {
			listDocuments.add( new Document( results[ i ], results[ ++i ] ) );
		}
		return listDocuments;
	}
	
	public List<Exercise> getAllExercises( String id, String user, String pass ) {
		jRobot = new ExerciseRobot( id, user, pass );
		results = jRobot.run();
		listExercises = new ArrayList<Exercise>();
		
		for( int i = 0; i < results.length; i++ ) {
			listExercises.add( new Exercise( results[ i++ ], results[ i++ ], results[ i++ ], id, results[ i ].charAt(results[i].length() - 1) ) );
		}
		return listExercises;
	}
	
	public List<Description> getInfoExercise( String id, String idExercise, String user, String pass ) {
		jRobot = new InfoExerciseRobot( id, idExercise, user, pass );
		results = jRobot.run();
		listDescription = new ArrayList<Description>();
		
		for( int i = 0; i < results.length; i++ ) {		
			try {
				listDescription.add( new Description ( new String( StringEscapeUtils.unescapeHtml4(results[i]).getBytes("ISO-8859-1"), Charset.forName("ISO-8859-1") ) ) );
			} catch (Exception e) { }
		}
		return listDescription;
	}
	
	public List<Mark> getAllMarks( String id, String user, String pass ) {
		jRobot = new MarkRobot( id, user, pass );
		results = jRobot.run();
		listMarks = new ArrayList<Mark>();
		
		for( int i = 0; i < results.length; i++ ) {
			listMarks.add( new Mark( results[ i++ ], results[ i++ ], results[ i ] ) );
		}
		return listMarks;
	}
	
	public List<Description> getAnouncements(String id, String user, String pass) {
		jRobot = new AnouncementRobot(id, user, pass);
		results = jRobot.run();
		listDescription = new ArrayList<Description>();
		
		for( int i = 0; i < results.length; i++ ) {
			try {
				listDescription.add( new Description( new String( StringEscapeUtils.unescapeHtml4( "<b>"+results[i++]+"</b>" ).getBytes("ISO-8859-1") , Charset.forName("ISO-8859-1") ) ) );
				listDescription.add( new Description( new String( StringEscapeUtils.unescapeHtml4( results[i] ).getBytes("ISO-8859-1") , Charset.forName("ISO-8859-1") ) ) );
			} catch (UnsupportedEncodingException e) { }
		}
		return listDescription;
	}
	
	public List<Description> getGroup(String id, String user, String pass) {
		jRobot = new GroupRobot(id, user, pass);
		results = jRobot.run();
		listDescription = new ArrayList<Description>();
		
		for( int i = 0; i < results.length; i++ ) {
			try {
				listDescription.add( new Description( new String( StringEscapeUtils.unescapeHtml4( results[i] ).getBytes("ISO-8859-1") , Charset.forName("ISO-8859-1") ) ) );
			} catch (UnsupportedEncodingException e) { }
		}
		return listDescription;
	}
	
	public String[] downloadDocument(String id, String url, String user, String pass) {
		jRobot = new FileRobot(id, url, user, pass);
		results = jRobot.run();
		
		return results;
	}
	
	public List<Section> getNotification( String id, String user, String pass ) {
		jRobot = new NotificationRobot(id, user, pass);
		results = jRobot.run();
		listSection = new ArrayList<Section>();
			
		for( int i = 0; i < results.length; i++) {
			try {
				String utf8 = new String(results[i].substring(6).getBytes("ISO-8859-1"), Charset.forName("ISO-8859-1"));
				listSection.add( new Section( utf8 ) );
			} catch (UnsupportedEncodingException e) { }			
		}
		return listSection;
		
	}
	public static void main(String[]args){
		System.out.println(Charset.defaultCharset());
	}
}
