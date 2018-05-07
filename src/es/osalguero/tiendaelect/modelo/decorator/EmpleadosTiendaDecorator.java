package es.osalguero.tiendaelect.modelo.decorator;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.persona.Empleado;

@XmlRootElement(name="empleados", namespace="http://es.osalguero.elect/TiendaElectrodomesticos")
@XmlSeeAlso(Empleado.class)
public class EmpleadosTiendaDecorator extends GenericListDecorator<Empleado> {

	public EmpleadosTiendaDecorator() {
		
	}
	
	public EmpleadosTiendaDecorator(List<Empleado> empleados) {
		this.setElementos(empleados);
	}
	
}
