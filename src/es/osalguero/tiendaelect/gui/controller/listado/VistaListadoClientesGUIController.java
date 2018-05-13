package es.osalguero.tiendaelect.gui.controller.listado;

import java.util.Calendar;
import java.util.List;

import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.busqueda.BusquedaCliente;
import es.osalguero.tiendaelect.gui.view.busqueda.util.BusquedaUtils;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.gui.view.listado.VistaListadoClientesGUI;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.persona.Cliente;

public class VistaListadoClientesGUIController extends VistaAbstractaListadoGUIController<VistaListadoClientesGUI, Cliente, BusquedaCliente>
{
    public VistaListadoClientesGUIController(final ElementoTiendaGenerico elementoPadre) {
        super(elementoPadre);
    }
    
    @Override
    protected VistaListadoClientesGUI initVista(final ElementoTiendaGenerico elementoPadre) {
        return new VistaListadoClientesGUI(this, elementoPadre);
    }
    
    @Override
    protected List<Cliente> getElementosListado(final ElementoTiendaGenerico elementoPadre) {
        return TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getClientesTienda();
    }
    
    @Override
    protected boolean matchesFilter(final Cliente cliente, final BusquedaCliente modeloBusqueda) {
        if (modeloBusqueda == null) {
            return true;
        }
        boolean matches = BusquedaUtils.compareStrings(modeloBusqueda.getDni(), cliente.getDni());
        if (matches) {
            matches = BusquedaUtils.compareStrings(modeloBusqueda.getNombre(), cliente.getNombre());
        }
        if (matches) {
            matches = BusquedaUtils.compareStrings(modeloBusqueda.getApellido1(), cliente.getApellido1());
        }
        if (matches) {
            matches = BusquedaUtils.compareStrings(modeloBusqueda.getApellido2(), cliente.getApellido2());
        }
        if (matches) {
            matches = BusquedaUtils.compareStrings(modeloBusqueda.getDireccion(), cliente.getDireccion());
        }
        if (matches) {
            matches = BusquedaUtils.compareStrings(modeloBusqueda.getEmail(), cliente.getEmail());
        }
        if (matches) {
            matches = (modeloBusqueda.getFechaNacDesde() == null || modeloBusqueda.getFechaNacDesde().compareTo(cliente.getFechaNacimiento()) <= 0);
        }
        if (matches && modeloBusqueda.getFechaNacHasta() != null) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(modeloBusqueda.getFechaNacHasta());
            cal.add(5, 1);
            matches = (cal.getTime().compareTo(cliente.getFechaNacimiento()) >= 0);
        }
        if (matches) {
            matches = BusquedaUtils.compareStrings(modeloBusqueda.getTelefono(), cliente.getTelefono());
        }
        return matches;
    }
    
    @Override
    protected void deleteElement(final Cliente cliente) throws Exception {
        TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().deleteCliente(cliente);
    }
    
    @Override
    protected VistasEnum getVistaEdicionElemento() {
        return VistasEnum.VISTA_EDICION_CLIENTE;
    }
}
