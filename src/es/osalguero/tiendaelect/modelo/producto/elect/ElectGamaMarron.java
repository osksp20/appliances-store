package es.osalguero.tiendaelect.modelo.producto.elect;

import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.constants.TipoAlimentacion;
import es.osalguero.tiendaelect.constants.TipoAlmacenamiento;
import es.osalguero.tiendaelect.constants.TipoPantalla;

/**
 * Modela los electrodomesticos de consumo, identificados generalmente
 * por tener entradas y salidas, por ejemplo TVs, camaras digitales,
 * videocamaras, equipos de sonido, informatica, etc...
 * 
 * @author o.salguero.palacios
 *
 */
@XmlSeeAlso({ElectFotografiaDigital.class, ElectImagen.class,
	ElectSonido.class, ElectInformatica.class, ElectVideo.class})
public abstract class ElectGamaMarron extends Electrodomestico {

	private Integer capacidad;
	private TipoAlmacenamiento tipoAlmacenamiento;
	private TipoPantalla tipoPantalla;
	private Integer tamanyoPantalla;
	private TipoAlimentacion tipoAlimentacion;
	
	public Integer getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	
	public TipoAlmacenamiento getTipoAlmacenamiento() {
		return this.tipoAlmacenamiento;
	}
	
	public void setTipoAlmacenamiento(TipoAlmacenamiento tipoAlmacenamiento) {
		this.tipoAlmacenamiento = tipoAlmacenamiento;
	}
	
	public TipoPantalla getTipoPantalla() {
		return this.tipoPantalla;
	}
	
	public void setTipoPantalla(TipoPantalla tipoPantalla) {
		this.tipoPantalla = tipoPantalla;
	}
	
	public TipoAlimentacion getTipoAlimentacion() {
		return this.tipoAlimentacion;
	}
	
	public void setTipoAlimentacion(TipoAlimentacion tipoAlimentacion) {
		this.tipoAlimentacion = tipoAlimentacion;
	}
	
	public Integer getTamanyoPantalla() {
		return this.tamanyoPantalla;
	}
	
	public void setTamanyoPantalla(Integer tamanyoPantalla) {
		this.tamanyoPantalla = tamanyoPantalla;
	}
}
