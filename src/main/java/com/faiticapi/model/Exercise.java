package com.faiticapi.model;

import javax.xml.bind.annotation.XmlRootElement;




/**
 * Modelo de ejercicio
 * @author Abel Castro Lamas
 *
 */
@XmlRootElement
public class Exercise extends Archive {
	private static final long serialVersionUID = 1L;
	private String title;
	private String initialDate;
	private String endDate;
	private String idSubject;
	private Character id;
	private enum MESESGAL { xaneiro, febreiro, maio, xuño, xullo, setembro, outubro, novembro, decembro, dicembre };
	private enum DIASGAL { Luns, Mércores, Xoves, Venres };
	
	public Exercise() {}
	
	public Exercise( String title, String initialDate, String endDate, String idSubject, Character id ) {
		super("exercise");
		this.title = title.replaceAll("\n", "");
		this.initialDate = format(initialDate);
		this.endDate = format(endDate);
		this.idSubject = idSubject;
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(String idSubject) {
		this.idSubject = idSubject;
	}
	
	public Character getId() {
		return id;
	}

	public void setId(Character id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return title+"\n"+initialDate+"\n"+endDate;
	}
	
	public String format( String date ) {
		String dia = date.split(",")[0];
		
		try {
			DIASGAL diasGal = DIASGAL.valueOf(dia);
			switch (diasGal) {
				case Luns:
					date = date.replace("Luns", "Lunes");
				break;
				case Mércores:
					date = date.replace("Mércores", "Miércoles");
				break;
				case Xoves:
					date = date.replace("Xoves", "Jueves");
				break;
				case Venres:
					date = date.replace("Venres", "Viernes");
				break;
			}
		} catch(Exception e) {}
		
		String mes = date.split(" ")[3];		
		try {	
			MESESGAL mesesGal = MESESGAL.valueOf(mes);		
			switch(mesesGal) {
				case xaneiro:
					date = date.replace("xaneiro", "enero");
				break;
				case febreiro:
					date = date.replace("febreiro", "febrero");
				break;
				case maio:
					date = date.replace("maio", "mayo");
				break;
				case xuño:
					date = date.replace("xuño", "junio");
				break;
				case xullo:
					date = date.replace("xullo", "julio");
				break;
				case setembro:
					date = date.replace("setembro", "septiembre");
				break;
				case outubro:
					date = date.replace("outubro", "octubre");
				break;
				case novembro:
					date = date.replace("novembro", "noviembre");
				break;
				case decembro:
					date = date.replace("decembro", "diciembre");
				break;
				case dicembre:
					date = date.replace("dicembre", "diciembre");
				break;
			}
		} catch(Exception e) {}
		date = date.replace("ás", "a las");
		return date;
	}
}
