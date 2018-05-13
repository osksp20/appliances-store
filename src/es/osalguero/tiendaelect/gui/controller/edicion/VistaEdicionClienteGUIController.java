package es.osalguero.tiendaelect.gui.controller.edicion;

import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.edicion.VistaEdicionClienteGUI;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.persona.Cliente;

public class VistaEdicionClienteGUIController extends VistaAbstractaEdicionGUIController<VistaEdicionClienteGUI, Cliente>
{
    public VistaEdicionClienteGUIController(final Cliente elementoEdicion, final TipoEdicionEnum tipoEdicion) {
        super(elementoEdicion, null, tipoEdicion);
    }
    
    @Override
    protected VistaEdicionClienteGUI initVista(final Cliente elementoEdicion, final ElementoTiendaGenerico elementoPadre, final TipoEdicionEnum tipoEdicion) {
        return new VistaEdicionClienteGUI(this, elementoEdicion, tipoEdicion);
    }
    
    @Override
    public void addNewElement(final Cliente elementoNuevo) throws Exception {
        TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().addCliente(elementoNuevo);
    }
    
    public void modificarCliente(final Cliente cliente) throws Exception {
        TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().modificarCliente(cliente);
    }
}
