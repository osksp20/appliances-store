package es.osalguero.tiendaelect.modelo.elect;

import es.osalguero.tiendaelect.constants.TipoAlimentacion;
import es.osalguero.tiendaelect.constants.TipoAlmacenamiento;

public abstract class CamaraDigital extends ElectFotografiaDigital {

	private int resolucion;
	private TipoAlimentacion alimentacion;
	private TipoAlmacenamiento almacenamiento;
	
	public int getResolucion() {
		return resolucion;
	}
	
	public void setResolucion(int resolucion) {
		this.resolucion = resolucion;
	}
	
	public TipoAlimentacion getAlimentacion() {
		return alimentacion;
	}
	
	public void setAlimentacion(TipoAlimentacion alimentacion) {
		this.alimentacion = alimentacion;
	}
	
	public TipoAlmacenamiento getAlmacenamiento() {
		return almacenamiento;
	}
	
	public void setAlmacenamiento(TipoAlmacenamiento almacenamiento) {
		this.almacenamiento = almacenamiento;
	}
}
