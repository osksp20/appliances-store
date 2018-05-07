package es.osalguero.tiendaelect.modelo.decorator;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.negocio.Financiacion;

@XmlRootElement(name="financiaciones", namespace="http://es.osalguero.elect/TiendaElectrodomesticos")
@XmlSeeAlso(Financiacion.class)
public class FinanciacionesTiendaDecorator extends GenericListDecorator<Financiacion> {

	public FinanciacionesTiendaDecorator() {
		
	}
	
	public FinanciacionesTiendaDecorator(List<Financiacion> financiaciones) {
		this.setElementos(financiaciones);
	}
	
}
