package es.osalguero.tiendaelect.modelo.decorator;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;

import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;

public abstract class GenericListDecorator<T extends ElementoTiendaGenerico> {

	protected List<T> elementos;
	
	public GenericListDecorator() {
		this.elementos = new ArrayList<T>();
	}

	public GenericListDecorator(List<T> elementos) {
		this.elementos = elementos;
	}
	
	@XmlElementRef
	public List<T> getElementos() {
		return this.elementos;
	}

	public void setElementos(List<T> elementos) {
		this.elementos = elementos;
	}
}
