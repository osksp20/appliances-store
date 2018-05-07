package es.osalguero.tiendaelect.modelo.negocio;

import java.util.List;

import es.osalguero.tiendaelect.modelo.persona.Cliente;

public class FichaCliente {

	private Cliente cliente;
	private List<FichaVenta> ventas;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<FichaVenta> getVentas() {
		return ventas;
	}
	
	public void setVentas(List<FichaVenta> ventas) {
		this.ventas = ventas;
	}
}
