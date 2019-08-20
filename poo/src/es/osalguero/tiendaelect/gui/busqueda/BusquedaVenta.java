package es.osalguero.tiendaelect.gui.busqueda;

import java.util.Date;

public class BusquedaVenta implements ModeloBusqueda
{
    private static final long serialVersionUID = 1040524803435665049L;
    private Long idVenta;
    private String dniCliente;
    private Date fechaDesde;
    private Date fechaHasta;
    private Double precioDesde;
    private Double precioHasta;
    private Boolean financiado;
    private Boolean financiacionTerminada;

    public Long getIdVenta() {
    	return idVenta;
    }
    public void setIdVenta(Long idVenta) {
    	this.idVenta = idVenta;
    }
    public String getDNICliente() {
		return dniCliente;
	}
	public void setDNICliente(String dniCliente) {
		if (dniCliente != null && !dniCliente.trim().isEmpty()) {
            this.dniCliente = dniCliente;
        }
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Double getPrecioDesde() {
		return precioDesde;
	}
	public void setPrecioDesde(Double precioDesde) {
		this.precioDesde = precioDesde;
	}
	public Double getPrecioHasta() {
		return precioHasta;
	}
	public void setPrecioHasta(Double precioHasta) {
		this.precioHasta = precioHasta;
	}
	
	public Boolean isFinanciado() {
		return financiado;
	}
	
	public void setFinanciado(Boolean financiado) {
		this.financiado = financiado;
	}
	
	public Boolean isFinanciacionTerminada() {
		return financiacionTerminada;
	}
	
	public void setFinanciacionTerminada(Boolean financiacionTerminada) {
		this.financiacionTerminada = financiacionTerminada;
	}
}
