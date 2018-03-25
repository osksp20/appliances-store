package es.osalguero.tiendaelect.modelo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.producto.Producto;

@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(Producto.class)
public class Tienda {

	@XmlAttribute
	private String nombre;
	@XmlElementWrapper(name="productos")
	@XmlElementRef
	private List<Producto> productos;

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return productos;
	}
	
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
}
