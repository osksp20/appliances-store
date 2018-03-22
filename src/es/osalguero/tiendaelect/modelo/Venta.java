package es.osalguero.tiendaelect.modelo;

import java.util.List;

public class Venta {

	private Cliente cliente;
	private List<Electrodomestico> electrodomesticos;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Electrodomestico> getElectrodomesticos() {
		return electrodomesticos;
	}
	
	public void setElectrodomesticos(List<Electrodomestico> electrodomesticos) {
		this.electrodomesticos = electrodomesticos;
	}
	
}
