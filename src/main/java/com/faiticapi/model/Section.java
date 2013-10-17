package com.faiticapi.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo de seccion
 * @author Abel Castro Lamas
 *
 */
@XmlRootElement
public class Section {
	private enum SECTIONS { Descripción, Descrición, Documentos, Ejercicios, Exercicios, Grupos, Calificaciones, 
		Anuncios, Cuestionarios, Agenda, Axenda, Foros, Inscricións, Temarios, Secuencia, Debate, Wiki, Glosario,
		Conferencia, Prácticas, Enquisas, Encuestas };
	private String name;
	private String url;
	
	public Section() {}
	
	public Section( String name ) {
		this.name = name;
		SECTIONS s = SECTIONS.valueOf(name.split(" ")[0]);
				
		switch(s) {
			case Descripción:
			case Descrición:
				this.url = "description";
			break;
			case Documentos:
				this.url = "documents";
			break;
			case Ejercicios:
			case Exercicios:
				this.url = "exercises";
			break;
			case Grupos:
				this.url = "groups";
			break;
			case Calificaciones:
				this.url = "marks";
			break;
			case Anuncios:
				this.url = "announcements";
			break;
			case Cuestionarios:
				this.url = "questionnaires";
			break;
			case Agenda:
			case Axenda:
				this.url = "diary";
			break;
			case Foros:
				this.url = "forums";
			break;
			case Inscricións:
				this.url = "inscription";
			break;
			case Temarios:
				this.url = "temarios";
				break;	
			case Secuencia:
				this.url = "sequence";
				break;
			case Debate:
				this.url = "debate";
				break;	
			case Wiki:
				this.url = "wiki";
				break;	
			case Glosario:
				this.url = "glossary";
				break;	
			case Conferencia:
				this.url = "conference";
				break;	
			case Prácticas:
				this.url = "practices";
				break;	
			case Encuestas:
			case Enquisas:
				this.url = "polls";
				break;		
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return name;
	}
}
