package es.osalguero.tiendaelect.modelo.persona;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import es.osalguero.tiendaelect.modelo.negocio.Nomina;

/**
 * Esta clase representa a un cliente de la tienda 
 * 
 * @author o.salguero.palacios
 */
@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos", name="cliente")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cliente extends Persona {

	private String telefono;
	private String direccion;
	@XmlID
	private String dni;
	private Nomina ultimaNomina;
	
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
	public Nomina getUltimaNomina() {
		return ultimaNomina;
	}
	public void setUltimaNomina(Nomina ultimaNomina) {
		this.ultimaNomina = ultimaNomina;
	}
	@Override
	public boolean equals(Object object) {
		return object != null && object instanceof Cliente &&
				((Cliente)object).getDni() != null && this.getDni() != null &&
				this.getDni().equals(((Cliente)object).getDni());
	}
	
	@Override
	public Cliente clone() throws CloneNotSupportedException {
		Cliente cliente = (Cliente)super.clone();
		if(ultimaNomina != null) {
			cliente.setUltimaNomina((Nomina)ultimaNomina.clone());
		}
		return cliente;
	}
}
