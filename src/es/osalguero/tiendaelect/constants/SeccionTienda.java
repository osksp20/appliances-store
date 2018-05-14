package es.osalguero.tiendaelect.constants;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="seccionTienda")
@XmlEnum
public enum SeccionTienda {

	@XmlEnumValue("GAMABLANCA")
	GAMA_BLANCA("Gama Blanca"), 
	@XmlEnumValue("IMAGEN")
	IMAGEN("Imagen"), 
	@XmlEnumValue("SONIDO")
	SONIDO("Sonido"), 
	@XmlEnumValue("FOTO")
	FOTO("Fotografía"),
	@XmlEnumValue("VIDEO")
	VIDEO("Vídeo"),
	@XmlEnumValue("INFORMATICA")
	INFORMATICA("Informática"),
	@XmlEnumValue("PAE")
	PAE("PAE"),
	@XmlEnumValue("ACCESORIOS")
	ACCESORIOS("Accesorios"),
	@XmlEnumValue("TELEFONIA")
	TELEFONIA("Telefonía");
	
	private String nombre;
	
    private SeccionTienda(final String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return this.nombre;
    }
	
	public static SeccionTienda getByNombre(final String nombre) {
        for (SeccionTienda seccion : SeccionTienda.values()) {
            if (seccion.getNombre().equals(nombre)) {
                return seccion;
            }
        }
        return null;
    }
}
