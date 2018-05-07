package es.osalguero.tiendaelect.modelo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.producto.Producto;

@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos")
@XmlSeeAlso(Producto.class)
public class Tienda {

	private String nombre;

	@XmlAttribute
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
