package es.osalguero.tiendaelect.gui.controller.edicion;

import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.edicion.VistaEdicionEmpleadoGUI;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.persona.Empleado;

public class VistaEdicionEmpleadoGUIController extends VistaAbstractaEdicionGUIController<VistaEdicionEmpleadoGUI, Empleado>
{
    public VistaEdicionEmpleadoGUIController(final Empleado elementoEdicion, final TipoEdicionEnum tipoEdicion) {
        super(elementoEdicion, null, tipoEdicion);
    }
    
    @Override
    protected VistaEdicionEmpleadoGUI initVista(final Empleado elementoEdicion, final ElementoTiendaGenerico elementoPadre, final TipoEdicionEnum tipoEdicion) {
        return new VistaEdicionEmpleadoGUI(this, elementoEdicion, tipoEdicion);
    }
    
    @Override
    public void addNewElement(final Empleado elementoNuevo) throws Exception {
        TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().addEmpleado(elementoNuevo);
    }
    
    public void modificarEmpleado(final Empleado empleado) throws Exception {
        TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().modificarEmpleado(empleado);
    }
}
