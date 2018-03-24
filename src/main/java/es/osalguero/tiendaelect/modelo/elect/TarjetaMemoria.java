package es.osalguero.tiendaelect.modelo.elect;

import es.osalguero.tiendaelect.constants.MedidaAlmacenamiento;
import es.osalguero.tiendaelect.constants.TipoAlmacenamiento;

public class TarjetaMemoria extends ElectAccesorioGamaMarron {

	private TipoAlmacenamiento almacenamiento;
	private float capacidad;
	private MedidaAlmacenamiento medidaCapacidad;
	
	public TipoAlmacenamiento getAlmacenamiento() {
		return almacenamiento;
	}
	
	public void setAlmacenamiento(TipoAlmacenamiento almacenamiento) {
		this.almacenamiento = almacenamiento;
	}
	
	public float getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(float capacidad) {
		this.capacidad = capacidad;
	}
	
	public MedidaAlmacenamiento getMedidaCapacidad() {
		return medidaCapacidad;
	}
	
	public void setMedidadCapacidad(MedidaAlmacenamiento medidaCapacidad) {
		this.medidaCapacidad = medidaCapacidad;
	}
}
