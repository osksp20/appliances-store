package es.osalguero.tiendaelect.modelo.negocio;

import java.util.Date;

import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;

public class Nomina extends ElementoTiendaGenerico{

	private Double cantidad;
	private Date fecha;
	
	public Double getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
