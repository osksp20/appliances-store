package es.osalguero.tiendaelect.gui.controller.gestionApp;

import es.osalguero.tiendaelect.gui.controller.AbstractGUIController;
import es.osalguero.tiendaelect.gui.view.gestionApp.VistaCargaDatosGUI;

public class VistaCargaDatosGUIController extends AbstractGUIController<VistaCargaDatosGUI>
{
    @Override
    protected void setVista() {
        this.vista = new VistaCargaDatosGUI(this);
    }
}
