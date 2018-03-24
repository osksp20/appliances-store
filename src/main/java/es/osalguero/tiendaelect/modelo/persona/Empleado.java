package es.osalguero.tiendaelect.modelo.persona;

import es.osalguero.tiendaelect.constants.CategoriaEmpleado;

/**
 * Clase que representa a un trabajador de la tienda
 * El tipo concreto heredará de esta clase
 * 
 * @author o.salguero.palacios
 */
public class Empleado extends Persona {

	//El login funcionara como identificador, tendra que ser unico
	private String login;
	private CategoriaEmpleado categoria;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public CategoriaEmpleado getCategoria() {
		return categoria;
	}
	
	public void setCategoria(CategoriaEmpleado categoria) {
		this.categoria = categoria;
	}
}
