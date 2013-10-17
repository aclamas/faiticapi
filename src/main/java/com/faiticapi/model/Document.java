package com.faiticapi.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo de Documento
 * Puede ser un directorio o un archivo
 * @author Abel Castro Lamas
 *
 */
@XmlRootElement
public class Document extends Archive {
	private static final long serialVersionUID = 1L;
	private String name;
	private String uri;
	private boolean dir = false;
	
	public Document() {}
	
	public Document( String name, String uri ) {
		super("document");
		this.name = name;	
		this.uri = uri.replace("&amp;", "&");
			
		if(uri.contains("file=%2F")) {
			if( uri.contains("jpg") || uri.contains("bmp") || uri.contains("png") || uri.contains("jpeg") ) {
				String[] namesDir = uri.split("%2F");

				this.uri = "goto/index.php";
				int i = 1;
				for(;i < namesDir.length - 1 ; i++) {
					this.uri = this.uri + "/" + namesDir[i];
				}
				this.uri = this.uri + "/" + namesDir[i].split("&amp")[0];
				
			}
			else this.dir = true;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}

	public boolean isDir() {
		return dir;
	}

	public void setDir(boolean dir) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		return name;
	}
}
