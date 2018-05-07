package es.osalguero.tiendaelect.modelo;

import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.persona.Persona;
import es.osalguero.tiendaelect.modelo.producto.Producto;

@XmlSeeAlso({Producto.class, Persona.class})
public abstract class ElementoTiendaGenerico implements Cloneable {

	@Override
	public ElementoTiendaGenerico clone() throws CloneNotSupportedException {
		return (ElementoTiendaGenerico)super.clone();
	}
	
}
