package es.osalguero.tiendaelect.constants;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="seccionTienda")
@XmlEnum
public enum SeccionTienda {

	@XmlEnumValue("GAMABLANCA")
	GAMA_BLANCA, 
	@XmlEnumValue("IMAGEN")
	IMAGEN, 
	@XmlEnumValue("SONIDO")
	SONIDO, 
	@XmlEnumValue("FOTO")
	FOTO,
	@XmlEnumValue("VIDEO")
	VIDEO,
	@XmlEnumValue("INFORMATICA")
	INFORMATICA,
	@XmlEnumValue("PAE")
	PAE,
	@XmlEnumValue("ACCESORIOS")
	ACCESORIOS;
	
}
