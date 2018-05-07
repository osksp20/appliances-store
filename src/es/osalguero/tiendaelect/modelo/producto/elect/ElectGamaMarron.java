package es.osalguero.tiendaelect.modelo.producto.elect;

import java.util.Map;

import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.constants.TipoConexion;

/**
 * Modela los electrodomesticos de consumo, identificados generalmente
 * por tener entradas y salidas, por ejemplo TVs, camaras digitales,
 * videocamaras, equipos de sonido, informatica, etc...
 * 
 * @author o.salguero.palacios
 *
 */
@XmlSeeAlso({ElectFotografiaDigital.class, ElectFotografia.class, ElectImagen.class,
	ElectSonido.class, ElectInformatica.class, ElectSonido.class, ElectVideo.class})
public abstract class ElectGamaMarron extends Electrodomestico {

	private Map<TipoConexion, Integer> entradas;
	private Map<TipoConexion, Integer> salidas;

	public Map<TipoConexion, Integer> getEntradas() {
		return entradas;
	}
	
	public void setEntradas(Map<TipoConexion, Integer> entradas) {
		this.entradas = entradas;
	}

	public Map<TipoConexion, Integer> getSalidas() {
		return salidas;
	}
	
	public void setSalidas(Map<TipoConexion, Integer> salidas) {
		this.salidas = salidas;
	}
}
