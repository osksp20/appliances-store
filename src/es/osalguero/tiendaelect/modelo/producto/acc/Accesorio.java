package es.osalguero.tiendaelect.modelo.producto.acc;

import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.constants.SeccionTienda;
import es.osalguero.tiendaelect.modelo.producto.Producto;

@XmlSeeAlso({AccesorioFotografia.class, AccesorioGamaBlanca.class, AccesorioImagen.class,
	AccesorioInformatica.class, AccesorioPAE.class, AccesorioSonido.class, AccesorioVideo.class})
public abstract class Accesorio extends Producto implements AccesorioInterface {

	public SeccionTienda getSeccionTienda() {
		return SeccionTienda.ACCESORIOS;
	}
	
}
