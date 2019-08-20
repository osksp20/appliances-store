package es.osalguero.tiendaelect.gui.controller.gestionApp;

import es.osalguero.tiendaelect.gui.controller.AbstractGUIController;
import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.gestionApp.VistaExceptionGUI;

public class VistaExceptionGUIController extends AbstractGUIController<VistaExceptionGUI>
{
    @Override
    protected void setVista() {
        this.vista = new VistaExceptionGUI(this);
    }
    
    @Override
    public void goBack() {
        TiendaElectrodomesticosGUIController.getInstance().goBack();
    }
    
    public boolean mostrarVistaAtras() {
        return TiendaElectrodomesticosGUIController.getInstance().getVistaActual() != null;
    }
}
