package com.faiticapi.jarvest;

import es.uvigo.ei.sing.jarvest.core.HTTPUtils;

/**
 * Interfaz de los Robots
 * @author Abel Castro Lamas
 *
 */
public abstract class JarvestRobot {
	/**
	 * Templated method. Limpia las cookies de otras sesiones
	 * y ejecuta el run 
	 * @return String[] - Array de cadenas con la informacion 
	 * @exception FileNotFound - No encuentra el archivo con las reglas del robot
	 */
	public String[] run(){
		HTTPUtils.clearCookies();
		return this._run();
	}
	
	/**
	 * Ejecuta su metodo de llamada al jarvest
	 * @return String[] - Array de cadenas con la informacion 
	 * @exception FileNotFound - No encuentra el archivo con las reglas del robot
	 */
	protected abstract String[] _run();
}
