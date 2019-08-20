package es.osalguero.tiendaelect.modelo.persona;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import es.osalguero.tiendaelect.constants.CategoriaEmpleado;

/**
 * Clase que representa a un trabajador de la tienda
 * El tipo concreto heredará de esta clase
 * 
 * @author o.salguero.palacios
 */
@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos", name="empleado")
@XmlAccessorType(XmlAccessType.FIELD)
public class Empleado extends Persona {

	//El login funcionara como identificador, tendra que ser unico
	private String login;
	private CategoriaEmpleado categoria;
	private boolean administrador = false;

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
	
	public boolean esAdministrador() {
		return administrador;
	}
	
	public void setEsAdministrador(boolean esAdministrador) {
		this.administrador = esAdministrador;
	}
	
	@Override
	public boolean equals(Object o) {
		return o != null && o instanceof Empleado && ((Empleado)o).getLogin() != null &&
				this.login != null && this.login.equals(((Empleado)o).getLogin());
	}
}
