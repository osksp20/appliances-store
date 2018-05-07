package es.osalguero.tiendaelect.modelo.producto.acc;

import es.osalguero.tiendaelect.constants.SeccionTienda;

public class AccesorioTelefonia extends Accesorio {

	@Override
	public SeccionTienda getSeccionAccesorio() {
		return SeccionTienda.TELEFONIA;
	}

}
