package es.osalguero.tiendaelect.modelo.producto.elect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import es.osalguero.tiendaelect.constants.SeccionTienda;

@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos", name="electVideo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElectVideo extends ElectGamaMarron {

	public SeccionTienda getSeccionTienda() {
		return SeccionTienda.VIDEO;
	}
	
}
