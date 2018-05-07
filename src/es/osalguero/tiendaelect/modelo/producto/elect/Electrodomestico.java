package es.osalguero.tiendaelect.modelo.producto.elect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.producto.Producto;

@XmlSeeAlso({ElectGamaMarron.class, ElectGamaBlanca.class, ElectPAE.class, ElectFotografia.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Electrodomestico extends Producto {

	@XmlAttribute
	private String color;

	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
}
