package es.osalguero.tiendaelect.modelo.decorator;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.negocio.Devolucion;

@XmlRootElement(name="devoluciones", namespace="http://es.osalguero.elect/TiendaElectrodomesticos")
@XmlSeeAlso(Devolucion.class)
public class DevolucionesTiendaDecorator extends GenericListDecorator<Devolucion> {

	public DevolucionesTiendaDecorator() {
		
	}
	
	public DevolucionesTiendaDecorator(List<Devolucion> devoluciones) {
		this.setElementos(devoluciones);
	}
	
}
