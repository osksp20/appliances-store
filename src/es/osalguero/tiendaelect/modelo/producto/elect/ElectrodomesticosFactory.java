package es.osalguero.tiendaelect.modelo.producto.elect;

import java.util.Map;

import es.osalguero.tiendaelect.constants.TipoConexion;

public class ElectrodomesticosFactory {

	public ElectFotografiaDigital nuevoElectFotografiaDigital(String marca, String modelo,
			String color, String tipo, float precio, Map<String, String> caracteristicas, 
			Map<TipoConexion, Integer> entradas, Map<TipoConexion, Integer> salidas) {
		ElectFotografiaDigital efd = new ElectFotografiaDigital();
		efd.setCaracteristicas(caracteristicas);
		efd.setColor(color);
		efd.setEntradas(entradas);
		efd.setMarca(marca);
		efd.setModelo(modelo);
		efd.setPrecio(precio);
		efd.setSalidas(salidas);
		efd.setTipo(tipo);
		return efd;
	}
	
}
