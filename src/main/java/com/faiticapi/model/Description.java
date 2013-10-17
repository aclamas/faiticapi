package com.faiticapi.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo de descripcion
 * @author Abel Castro Lamas
 *
 */
@XmlRootElement
public class Description extends Archive {
	private static final long serialVersionUID = 1L;
	private String description;
	
	public Description() { };
	
	public Description( String description ) {
		super("description");
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}
	
}
