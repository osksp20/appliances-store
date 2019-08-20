package es.osalguero.tiendaelect.gui.controller.principal;

import es.osalguero.tiendaelect.gui.view.principal.VistaPrincipalComercialGUI;

public class VistaPrincipalComercialGUIController extends VistaPrincipalGUIController
{
    @Override
    protected void setVista() {
        this.vista = new VistaPrincipalComercialGUI(this);
    }
    
    public void goToMisReparaciones() {
       	//TiendaElectrodomesticosGUIController.getInstance().gestionarCambioVista(VistasEnum.VISTA_LISTADO_REPARACIONES_EMPLEADO, null);
    }
    
    public void goToListadoOfertas() {
        //TiendaElectrodomesticosGUIController.getInstance().gestionarCambioVista(VistasEnum.VISTA_LISTADO_OFERTAS, null);
    }
}
