package com.faiticapi.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;



import com.faiticapi.daos.SubjectDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.uvigo.ei.sing.jarvest.core.Base64Coder;

/**
 * Esta clase es el servlet que recibe las llamadas HTTP 
 * Segun la ruta que lleve la llamada, se ejecuta el metodo correspondiente
 * Crea la clase SubjectDAO
 * Formatea la respuesta de SubjectDAO para devolver el json
 * @author Abel Castro Lamas
 * @see SubjectDAO
 */
@Path("subjects")
public class SubjectService {
	
	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();


	/**
	 * Hace login en faitic y muestra las asignaturas del alumno
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @return String - Cadena en json que codifica la lista de objetos Subject del alumno que hizo login
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public String getAllSubjects( @HeaderParam("authorization") String authorization ) {
		SubjectDAO subjectDAO = new SubjectDAO();	
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
		
		return gson.toJson( subjectDAO.getAllSubjects(user, pass) );
	}
	
	/**
	 * Escanea una asignatura y muestra las secciones por las que esta compuesta
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @param id - Id de la asignatura que se escanea
	 * @return String - Cadena en json que codifica la lista de objetos Section de la asignatura que se escanea
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path("{id}")
	public String getSubject( @HeaderParam("authorization") String authorization, @PathParam("id") String id  ) {	
		SubjectDAO subjectDAO = new SubjectDAO();
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
		
		return gson.toJson( subjectDAO.getSubject( id, user, pass ) );
	}
	
	/**
	 * Muestra la descripcion de la asignatura consultada
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @param id - Id de la asignatura que se escanea
	 * @return String - Cadena en json que codifica la lista de objetos Description, con la descripcion de la asignatura
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path("{id}/description")
	public String getDescription( @HeaderParam("authorization") String authorization, @PathParam("id") String id ) {
		SubjectDAO subjectDAO = new SubjectDAO();
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
		
		return gson.toJson( subjectDAO.getDescription( id, user, pass) );
	}
	
	/**
	 * Muestra la raiz de la carpeta documentos y enlaces de la asignatura consultada
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @param id - Id de la asignatura que se escanea
	 * @return String - Cadena en json que codifica la lista de objetos Document de la carpeta raiz
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path("{id}/documents")
	public String getDocumentsSubject( @HeaderParam("authorization") String authorization, @PathParam("id") String id ) {
		SubjectDAO subjectDAO = new SubjectDAO();
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
		
		return gson.toJson( subjectDAO.getDocumentsSubject( id, user, pass ) );
	}
	
	/**
	 * Muestra la carpeta a la que se ha hecho click, contenida en la raiz de documentos y enlaces
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @param id - Id de la asignatura que se escanea
	 * @param folder - Carpeta o ruta de carpetas a la que se accede
	 * @return String - Cadena en json que codifica la lista de objetos Document de la carpeta accedida
	 * @throws UnsupportedEncodingException 
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path("{id}/documents/{folder: [?/0-9.a-zA-Z_%=&]+}")
	public String getSubDocument( @HeaderParam("authorization") String authorization,
								  @PathParam("id") String id, @PathParam("folder") String folder ) 
									throws UnsupportedEncodingException {		
		SubjectDAO subjectDAO = new SubjectDAO();
		folder = URLEncoder.encode("/"+folder, "ISO-8859-1"); //las url de los archivos pueden tener tildes
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
		
		return gson.toJson( subjectDAO.getSubDocumentSubject( id, folder, user, pass ) );
	}	
	
	/**
	 * Muestra los ejercicios de la asignatura accedida
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @param id - Id de la asignatura que se escanea
	 * @return String - Cadena en json que codifica la lista de objetos Exercise de la seccion ejercicios
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path("{id}/exercises")
	public String getAllExercises( @HeaderParam("authorization") String authorization, @PathParam("id") String id ) {
		SubjectDAO subjectDAO = new SubjectDAO();
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
		
		return gson.toJson( subjectDAO.getAllExercises( id, user, pass ) );
	}
	
	/**
	 * Muestra la informacion sobre un ejercicio concreto de una asignatura
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @param id - Id de la asignatura que se escanea
	 * @param idExercise - Id del ejercicio que se consulta
	 * @return String - Cadena en json que codifica la lista de objectos Description, que contienen la descripcion del ejercicio que se consulta
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path("{id}/exercises/{idExercise}")
	public String getInfoExercise( @HeaderParam("authorization") String authorization,
			@PathParam("id") String id, @PathParam("idExercise") String idExercise ) {
		SubjectDAO subjectDAO = new SubjectDAO();
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
		
		return gson.toJson( subjectDAO.getInfoExercise( id, idExercise, user, pass ) );
	}
	
	/**
	 * Muestra las notas de la asignatura accedida
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @param id - Id de la asignatura que se escanea
	 * @return String - Cadena en json que codifica la lista de objectos Mark de la seccion calificaciones
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path("{id}/marks")
	public String getAllMarks( @HeaderParam("authorization") String authorization, @PathParam("id") String id ) {
		SubjectDAO subjectDAO = new SubjectDAO();
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
		
		return gson.toJson( subjectDAO.getAllMarks( id, user, pass ) );
	}
	
	/**
	 * Muestra los anuncios de la asignatura accedida
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @param id - Id de la asignatura que se escanea
	 * @return String - Cadena en json que codifica la lista de objectos Announcement de la seccion anuncios
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path("{id}/announcements")
	public String getAnouncements( @HeaderParam("authorization") String authorization,
								   @PathParam("id") String id ) {
		SubjectDAO subjectDAO = new SubjectDAO();
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
		
		return gson.toJson( subjectDAO.getAnouncements( id, user, pass ) );
	}
	
	/**
	 * Muestra los grupos a los que se pertenece en la asignatura accedida
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @param id - Id de la asignatura que se escanea
	 * @return String - Cadena en json que codifica la lista de objectos Description, que tienen la informacion de la seccion grupos
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path("{id}/groups")
	public String getGroup( @HeaderParam("authorization") String authorization,
			@PathParam("id") String id ) {
		SubjectDAO subjectDAO = new SubjectDAO();
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
		
		return gson.toJson( subjectDAO.getGroup( id, user, pass ) );
	}
	
	/**
	 * Descarga el documento con la direccion que se envia de la asignatura accedida
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @param id - Id de la asignatura que se escanea
	 * @param url - Direccion donde esta el documento 
	 * @param cidReq - parte de la direccion url
	 * @return String - Cadena en json que codifica el archivo en Base64
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path("download/{id}/{url: [-:/0-9.a-zA-Z_%]+}")
	public String donwloadDocument( @HeaderParam("authorization") String authorization, @PathParam("id") String id, @PathParam("url") String url, @QueryParam("cidReq") String cidReq) {
		SubjectDAO subjectDAO = new SubjectDAO();
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
		
		return gson.toJson( subjectDAO.downloadDocument( id, url, user, pass ) );	
	}
	
	/**
	 * Muestra las notificaciones de la asignatura accedida
	 * @param authorization - Cadena encripta en Base64 que contiene el login y el password
	 * @param id - Id de la asignatura que se escanea
	 * @return String - Cadena en json que codifica la lista de objetos Section, las secciones donde hay novedades
	 */
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	@Path("notification/{id}")
	public String getNotification( @HeaderParam("authorization") String authorization,
			   @PathParam("id") String id ) {
		SubjectDAO subjectDAO = new SubjectDAO();
		String userPass = Base64Coder.decodeString(authorization.split(" ")[1].toString());
		String user = userPass.split(":")[0];
		String pass = userPass.split(":")[1];
	
		return gson.toJson( subjectDAO.getNotification( id, user, pass ) );
	}
}
