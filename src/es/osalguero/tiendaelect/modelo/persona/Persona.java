package es.osalguero.tiendaelect.modelo.persona;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;

/**
 * Clase modelo para los usuarios de la tienda
 * 
 * @author o.salguero.palacios
 * */
@XmlSeeAlso({Cliente.class, Empleado.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Persona extends ElementoTiendaGenerico {

	private String nombre;
	private String apellido1;
	private String apellido2;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}	
}
