package es.osalguero.tiendaelect.modelo.decorator;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.negocio.Venta;

@XmlRootElement(name="ventas", namespace="http://es.osalguero.elect/TiendaElectrodomesticos")
@XmlSeeAlso(Venta.class)
public class VentasTiendaDecorator extends GenericListDecorator<Venta> {

	public VentasTiendaDecorator() {
		
	}
	
	public VentasTiendaDecorator(List<Venta> ventas) {
		this.elementos = ventas;
	}

}
