package es.osalguero.tiendaelect.modelo.decorator;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.producto.Producto;

@XmlRootElement(name="productos", namespace="http://es.osalguero.elect/TiendaElectrodomesticos")
@XmlSeeAlso(Producto.class)
public class ProductosTiendaDecorator extends GenericListDecorator<Producto> {

	public ProductosTiendaDecorator() {
		
	}
	
	public ProductosTiendaDecorator(List<Producto> productos) {
		this.elementos = productos;
	}

}
