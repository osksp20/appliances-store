package es.osalguero.tiendaelect.modelo.producto.acc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import es.osalguero.tiendaelect.constants.SeccionTienda;

@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos", name="accGamaBlanca")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccesorioGamaBlanca extends Accesorio {

	@Override
	public SeccionTienda getSeccionAccesorio() {
		return SeccionTienda.GAMA_BLANCA;
	}

}
