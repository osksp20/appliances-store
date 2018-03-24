package es.osalguero.tiendaelect.modelo.negocio;

import java.util.List;

import es.osalguero.tiendaelect.modelo.elect.Electrodomestico;
import es.osalguero.tiendaelect.modelo.persona.Cliente;

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
