package es.osalguero.tiendaelect.modelo.negocio;

import java.util.List;

public class FichaVenta {
	
	private Venta venta;
	private List<Reparacion> reparaciones;
	private List<Devolucion> devoluciones;
	private Financiacion financiacion;

	public Venta getVenta() {
		return venta;
	}
	
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	public Financiacion getFinanciacion() {
		return financiacion;
	}

	public void setFinanciacion(Financiacion financiacion) {
		this.financiacion = financiacion;
	}
	
	public List<Reparacion> getReparaciones() {
		return reparaciones;
	}
	
	public void setReparaciones(List<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}
	
	public List<Devolucion> getDevoluciones() {
		return devoluciones;
	}
	
	public void setDevoluciones(List<Devolucion> devoluciones) {
		this.devoluciones = devoluciones;
	}
}
