package es.osalguero.tiendaelect.modelo.producto.elect;

public class ElectrodomesticosFactory {

	public ElectFotografiaDigital nuevoElectFotografiaDigital(String marca, String modelo,
			String color, String tipo, float precio, String caracteristicas) {
		ElectFotografiaDigital efd = new ElectFotografiaDigital();
		efd.setCaracteristicas(caracteristicas);
		efd.setColor(color);
		efd.setMarca(marca);
		efd.setModelo(modelo);
		efd.setPrecio(precio);
		efd.setTipo(tipo);
		return efd;
	}
	
}
