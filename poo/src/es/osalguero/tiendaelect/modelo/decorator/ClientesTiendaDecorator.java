package es.osalguero.tiendaelect.modelo.decorator;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.persona.Cliente;

@XmlRootElement(name="clientes", namespace="http://es.osalguero.elect/TiendaElectrodomesticos")
@XmlSeeAlso(Cliente.class)
public class ClientesTiendaDecorator extends GenericListDecorator<Cliente> {
	
	public ClientesTiendaDecorator() {
		
	}
	
	public ClientesTiendaDecorator(List<Cliente> clientes) {
		this.setElementos(clientes);
	}

}
