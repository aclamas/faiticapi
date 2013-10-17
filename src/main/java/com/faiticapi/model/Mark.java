package com.faiticapi.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo de calificacion
 * @author Abel Castro Lamas
 *
 */
@XmlRootElement
public class Mark extends Archive {
	private static final long serialVersionUID = 1L;
	private String title;
	private String date;
	private String grade;
	
	public Mark() { }
	
	public Mark( String title, String date, String grade ) {
		super("mark");
		this.title = title;
		this.date = date;
		this.grade = grade.replace(",", "."); ///Para poder convertirlo a numero
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return title+"\n"+date+"\n"+grade;
	}
}
