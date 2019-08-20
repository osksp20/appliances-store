package es.osalguero.tiendaelect.gui.controller.listado;

import java.util.List;

import es.osalguero.tiendaelect.constants.SeccionTienda;
import es.osalguero.tiendaelect.gui.busqueda.BusquedaProducto;
import es.osalguero.tiendaelect.gui.busqueda.util.BusquedaUtils;
import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.gui.view.listado.VistaListadoProductosGUI;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.producto.Producto;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectGamaBlanca;

public class VistaListadoProductosGUIController extends VistaAbstractaListadoGUIController<VistaListadoProductosGUI, Producto, BusquedaProducto>
{
    public VistaListadoProductosGUIController(final ElementoTiendaGenerico elementoPadre) {
        super(elementoPadre);
    }
    
    @Override
    protected VistaListadoProductosGUI initVista(final ElementoTiendaGenerico elementoPadre) {
        return new VistaListadoProductosGUI(this, elementoPadre);
    }
    
    @Override
    protected List<Producto> getElementosListado(final ElementoTiendaGenerico elementoPadre) {
        return TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getProductosTienda();
    }
    
    @Override
    protected boolean matchesFilter(final Producto producto, final BusquedaProducto modeloBusqueda) {
        if (modeloBusqueda == null) {
            return true;
        }
        boolean matches = modeloBusqueda.getSeccion() == null || modeloBusqueda.getSeccion().equals(producto.getSeccionTienda());
        if (matches) {
            matches = BusquedaUtils.compareStrings(modeloBusqueda.getTipo(), producto.getTipo());
        }
        if (matches) {
            matches = BusquedaUtils.compareStrings(modeloBusqueda.getMarca(), producto.getMarca());
        }
        if(matches) {
        	matches = BusquedaUtils.compareStrings(modeloBusqueda.getModelo(), producto.getModelo());
        }
        if(matches) {
        	matches = modeloBusqueda.getPrecioDesde() == null || modeloBusqueda.getPrecioDesde() <= producto.getPrecio();
        }
        if(matches) {
        	matches = modeloBusqueda.getPrecioHasta() == null || modeloBusqueda.getPrecioHasta() >= producto.getPrecio();
        }
        if(matches && modeloBusqueda.getSeccion() != null && SeccionTienda.GAMA_BLANCA.equals(modeloBusqueda.getSeccion())) {
        	matches = modeloBusqueda.getColor() == null || modeloBusqueda.getColor().equals(((ElectGamaBlanca)producto).getColor());
        	if(matches) {
        		matches = modeloBusqueda.getAltoDesde() == null || modeloBusqueda.getAltoDesde() <= ((ElectGamaBlanca)producto).getAlto();
        	}
        	if(matches) {
        		matches = modeloBusqueda.getAltoHasta() == null || modeloBusqueda.getAltoHasta() >= ((ElectGamaBlanca)producto).getAlto();
        	}
        	if(matches) {
        		matches = modeloBusqueda.getAnchoDesde() == null || modeloBusqueda.getAnchoDesde() <= ((ElectGamaBlanca)producto).getAncho();
        	}
        	if(matches) {
        		matches = modeloBusqueda.getAnchoHasta() == null || modeloBusqueda.getAnchoHasta() >= ((ElectGamaBlanca)producto).getAncho();
        	}
        	if(matches) {
        		matches = modeloBusqueda.getProfundidadDesde() == null || modeloBusqueda.getProfundidadDesde() <= ((ElectGamaBlanca)producto).getProfundidad();
        	}
        	if(matches) {
        		matches = modeloBusqueda.getProfundidadHasta() == null || modeloBusqueda.getProfundidadHasta() >= ((ElectGamaBlanca)producto).getProfundidad();
        	}
        }
        return matches;
    }
    
    @Override
    protected void deleteElement(final Producto producto) throws Exception {
        TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().deleteProducto(producto);
    }
    
    @Override
    protected VistasEnum getVistaEdicionElemento() {
        return VistasEnum.VISTA_EDICION_PRODUCTO;
    }
}
