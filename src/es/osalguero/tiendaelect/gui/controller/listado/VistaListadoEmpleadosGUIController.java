package es.osalguero.tiendaelect.gui.controller.listado;

import java.util.ArrayList;
import java.util.List;

import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.busqueda.BusquedaEmpleado;
import es.osalguero.tiendaelect.gui.view.busqueda.util.BusquedaUtils;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.gui.view.listado.VistaListadoEmpleadosGUI;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.persona.Empleado;

public class VistaListadoEmpleadosGUIController extends VistaAbstractaListadoGUIController<VistaListadoEmpleadosGUI, Empleado, BusquedaEmpleado>
{
    public VistaListadoEmpleadosGUIController(final ElementoTiendaGenerico elementoPadre) {
        super(elementoPadre);
    }
    
    @Override
    protected VistaListadoEmpleadosGUI initVista(final ElementoTiendaGenerico elementoPadre) {
        return new VistaListadoEmpleadosGUI(this, elementoPadre);
    }
    
    @Override
    protected List<Empleado> getElementosListado(final ElementoTiendaGenerico elementoPadre) {
        return TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getEmpleadosTienda();
    }
    
    @Override
    protected boolean matchesFilter(final Empleado empleado, final BusquedaEmpleado modeloBusqueda) {
        if (modeloBusqueda == null) {
            return true;
        }
        boolean matches = BusquedaUtils.compareStrings(modeloBusqueda.getLogin(), empleado.getLogin());
        if (matches) {
            matches = BusquedaUtils.compareStrings(modeloBusqueda.getNombre(), empleado.getNombre());
        }
        if (matches) {
            matches = BusquedaUtils.compareStrings(modeloBusqueda.getApellido1(), empleado.getApellido1());
        }
        if (matches) {
            matches = BusquedaUtils.compareStrings(modeloBusqueda.getApellido2(), empleado.getApellido2());
        }
        if (matches) {
            matches = (modeloBusqueda.getAdministrador() == null || modeloBusqueda.getAdministrador().equals(empleado.esAdministrador()));
        }
        if (matches && modeloBusqueda.getTipo() != null) {
            matches = ((modeloBusqueda.getTipo().equals(empleado.getCategoria())));
        }
        return matches;
    }
    
    @Override
    protected void deleteElement(final Empleado empleado) throws Exception {
        final Empleado empleadoReemplazo = ((VistaListadoEmpleadosGUI)this.vista).showEmployeeReplacement(this.getEmployeeReplacementOptions(empleado));
        if (empleadoReemplazo != null) {
            TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().borrarEmpleado(empleado, empleadoReemplazo);
            return;
        }
        throw new Exception("Se ha cancelado la eliminación del empleado, al no haberse seleccionado un empleado válido para sustituirlo.");
    }
    
    @Override
    protected VistasEnum getVistaEdicionElemento() {
        return VistasEnum.VISTA_EDICION_EMPLEADO;
    }
    
    private List<Empleado> getEmployeeReplacementOptions(final Empleado empleado) {
        final List<Empleado> empleadosApp = this.getElementosListado(null);
        final List<Empleado> empleadosReemplazo = new ArrayList<Empleado>();
        for (final Empleado empleadoApp : empleadosApp) {
            if (empleadoApp.getClass().equals(empleado.getClass()) && !empleadoApp.equals(empleado)) {
                empleadosReemplazo.add(empleadoApp);
            }
        }
        return empleadosReemplazo;
    }
}
