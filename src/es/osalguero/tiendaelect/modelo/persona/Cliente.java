package es.osalguero.tiendaelect.modelo.persona;

/**
 * Esta clase representa a un cliente de la tienda 
 * 
 * @author o.salguero.palacios
 */
public class Cliente extends Persona {

	private String telefono;
	private String direccion;
	private String dni;
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
}
