package es.osalguero.tiendaelect.modelo.decorator;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.negocio.Reparacion;

@XmlRootElement(name="reparaciones", namespace="http://es.osalguero.elect/TiendaElectrodomesticos")
@XmlSeeAlso(Reparacion.class)
public class ReparacionesTiendaDecorator extends GenericListDecorator<Reparacion> {

	public ReparacionesTiendaDecorator() {
		
	}
	
	public ReparacionesTiendaDecorator(List<Reparacion> reparaciones) {
		this.setElementos(reparaciones);
	}
	
}
