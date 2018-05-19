package es.osalguero.tiendaelect.gui.controller.listado;

import java.util.Calendar;
import java.util.List;

import es.osalguero.tiendaelect.gui.busqueda.BusquedaVenta;
import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.gui.view.listado.VistaListadoVentasGUI;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.negocio.Venta;
import es.osalguero.tiendaelect.modelo.persona.Cliente;

public class VistaListadoVentasGUIController extends VistaAbstractaListadoGUIController<VistaListadoVentasGUI, Venta, BusquedaVenta>
{
    public VistaListadoVentasGUIController(final Cliente elementoPadre) {
        super(elementoPadre);
    }
    
    @Override
    protected VistaListadoVentasGUI initVista(final ElementoTiendaGenerico elementoPadre) {
        return new VistaListadoVentasGUI(this, elementoPadre);
    }
    
    @Override
    protected List<Venta> getElementosListado(final ElementoTiendaGenerico elementoPadre) {
        return TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getVentasTienda();
    }
    
    public void deleteElement(final Venta element) throws Exception {
        TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().deleteVenta(element);
    }
    
    @Override
    protected boolean matchesFilter(final Venta venta, final BusquedaVenta modeloBusqueda) {
        if (modeloBusqueda == null) {
            return true;
        }
        boolean matches = modeloBusqueda.getDNICliente() == null || modeloBusqueda.getDNICliente().equals(venta.getCliente().getDni());
        if (matches) {
        	matches = modeloBusqueda.getFechaDesde() == null || modeloBusqueda.getFechaDesde().compareTo(venta.getFecha()) <= 0;
        }
        if(matches && modeloBusqueda.getFechaHasta() != null) {
        	Calendar cal = Calendar.getInstance();
        	cal.setTime(modeloBusqueda.getFechaHasta());
        	cal.add(Calendar.DATE, 1);
        	matches = (cal.getTime().compareTo(venta.getFecha()) >= 0);
        }
        Double importeVenta = null;
        if(matches && modeloBusqueda.getPrecioDesde() != null) {
        	try {
        		importeVenta = TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getImporteVenta(venta);
        		matches = modeloBusqueda.getPrecioDesde() <= importeVenta;
        	} catch(Exception e) {
        		matches = false;
        	}
        }
        if(matches && modeloBusqueda.getPrecioHasta() != null) {
        	try {
        		if(importeVenta == null) {
        			importeVenta = TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getImporteVenta(venta);
        		}
        		matches = modeloBusqueda.getPrecioHasta() >= importeVenta;
        	} catch(Exception e) {
        		matches = false;
        	}
        }
        if(matches && modeloBusqueda.isFinanciado() != null) {
        	matches = modeloBusqueda.isFinanciado().equals(
        			TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().isVentaFinanciada(venta));
        }
        if(matches && modeloBusqueda.isFinanciacionTerminada() != null) {
        	matches = !modeloBusqueda.isFinanciacionTerminada().equals(
        			TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().isVentaFinanciacionTerminada(venta));
        }
        return matches;
    }
    
    @Override
    protected VistasEnum getVistaEdicionElemento() {
        return VistasEnum.VISTA_EDICION_VENTA;
    }
}
