package es.osalguero.tiendaelect.gui.controller.principal;

import es.osalguero.tiendaelect.gui.controller.AbstractGUIController;
import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.gui.view.principal.VistaPrincipalGUI;

public abstract class VistaPrincipalGUIController extends AbstractGUIController<VistaPrincipalGUI>{

	public void goToListadoClientes() {
		//Cargo la lista de clientes del comercial y muestro su vista
		TiendaElectrodomesticosGUIController.getInstance().gestionarCambioVista(VistasEnum.VISTA_LISTADO_CLIENTES, null);
	}
	
	public void goToListadoProductos() {
		TiendaElectrodomesticosGUIController.getInstance().gestionarCambioVista(VistasEnum.VISTA_LISTADO_PRODUCTOS, null);
	}
	
	public void goToListadoVentas() {
		TiendaElectrodomesticosGUIController.getInstance().gestionarCambioVista(VistasEnum.VISTA_LISTADO_VENTAS, null);
	}
}
