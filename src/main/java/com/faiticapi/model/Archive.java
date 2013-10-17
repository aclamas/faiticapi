package com.faiticapi.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase Base de todos los modelos. 
 * Sirve para hacer la ligadura dinámica.
 * @author Abel Castro Lamas
 *
 */
@XmlRootElement
public class Archive implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;

	public Archive() { }
	
	public Archive(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
