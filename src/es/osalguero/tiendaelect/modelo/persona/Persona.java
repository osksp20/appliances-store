package es.osalguero.tiendaelect.modelo.persona;

/**
 * Clase modelo para los usuarios de la tienda
 * 
 * @author o.salguero.palacios
 * */
public abstract class Persona {

	private String name;
	private String apellido1;
	private String apellido2;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
