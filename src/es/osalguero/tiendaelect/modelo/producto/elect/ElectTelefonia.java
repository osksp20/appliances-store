package es.osalguero.tiendaelect.modelo.producto.elect;

import es.osalguero.tiendaelect.constants.SeccionTienda;

public class ElectTelefonia extends Electrodomestico {

	@Override
	public SeccionTienda getSeccionTienda() {
		return SeccionTienda.TELEFONIA;
	}

}
