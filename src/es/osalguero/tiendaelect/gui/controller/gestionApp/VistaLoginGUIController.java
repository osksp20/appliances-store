package es.osalguero.tiendaelect.gui.controller.gestionApp;

import es.osalguero.tiendaelect.gui.controller.AbstractGUIController;
import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.service.TiendaAppService;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.gui.view.gestionApp.VistaLoginGUI;

public class VistaLoginGUIController extends AbstractGUIController<VistaLoginGUI>
{
    @Override
    protected void setVista() {
        this.vista = new VistaLoginGUI(this);
    }
    
    public void login(final String loginTxt) {
        try {
            if (loginTxt == null || loginTxt.isEmpty()) {
                ((VistaLoginGUI)this.vista).addLoginEmpty();
            }
            else if (this.getTiendaAppService().login(loginTxt)) {
                TiendaElectrodomesticosGUIController.getInstance().gestionarCambioVista(VistasEnum.VISTA_CARGA_DATOS, null);
                this.getTiendaAppService().cargarDatosTienda();
                this.getTiendaAppService().cargarDatosUsuario(loginTxt);
                TiendaElectrodomesticosGUIController.getInstance().gestionarCambioVista(VistasEnum.VISTA_PRINCIPAL, null);
            }
            else {
                ((VistaLoginGUI)this.vista).addBadLogin();
            }
        }
        catch (Exception e) {
            TiendaElectrodomesticosGUIController.getInstance().gestionarErrorAplicacion(new Exception("Error realizando el login del usuario", e));
        }
    }
    
    private TiendaAppService getTiendaAppService() {
        return TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService();
    }
}
