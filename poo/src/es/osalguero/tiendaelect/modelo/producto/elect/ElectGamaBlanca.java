package es.osalguero.tiendaelect.modelo.producto.elect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import es.osalguero.tiendaelect.constants.SeccionTienda;

/**
 * Modela electrodomesticos de tipo Gama Blanca:
 * Frigorificos, Lavadoras, Secadoras, Lavavajillas...
 * 
 * @author o.salguero.palacios
 *
 */
@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos", name="electGamaBlanca")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElectGamaBlanca extends Electrodomestico {

	private int alto;
	private int ancho;
	private int profundidad;
	
	public int getAlto() {
		return alto;
	}
	
	public void setAlto(int alto) {
		this.alto = alto;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	
	public int getProfundidad() {
		return profundidad;
	}
	
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	@Override
	public SeccionTienda getSeccionTienda() {
		return SeccionTienda.GAMA_BLANCA;
	}
	
}
