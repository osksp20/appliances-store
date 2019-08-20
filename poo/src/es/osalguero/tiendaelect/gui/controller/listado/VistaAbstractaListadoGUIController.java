package es.osalguero.tiendaelect.gui.controller.listado;

import java.util.ArrayList;
import java.util.List;

import es.osalguero.tiendaelect.gui.busqueda.ModeloBusqueda;
import es.osalguero.tiendaelect.gui.controller.AbstractGUIController;
import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.gui.view.listado.VistaAbstractaListadoGUI;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;

public abstract class VistaAbstractaListadoGUIController<T extends VistaAbstractaListadoGUI<?, S, V>, 
	S extends ElementoTiendaGenerico, V extends ModeloBusqueda> extends AbstractGUIController<VistaAbstractaListadoGUI<?, S, V>>
{
    private V modeloBusqueda;
    
    @SuppressWarnings("unused")
	private VistaAbstractaListadoGUIController() {
    }
    
    public VistaAbstractaListadoGUIController(final ElementoTiendaGenerico elementoPadre) {
        try {
            this.setVista(elementoPadre);
        }
        catch (Exception e) {
            TiendaElectrodomesticosGUIController.getInstance().gestionarErrorAplicacion(e);
        }
    }
    
    @Override
    protected void setVista() throws Exception {
    }
    
    protected void setVista(final ElementoTiendaGenerico elementoPadre) throws Exception {
        this.vista = this.initVista(elementoPadre);
        final List<S> elementos = this.getElementosListado(elementoPadre);
        if (elementos == null) {
            this.vista.setTotalElementos(0);
        }
        else {
           	this.vista.setElementos(elementos);
            this.vista.setTotalElementos(elementos.size());
        }
        this.setVista();
    }
    
    protected abstract T initVista(final ElementoTiendaGenerico p0) throws Exception;
    
    protected abstract List<S> getElementosListado(final ElementoTiendaGenerico p0);
    
    public void delete(final S element, final ElementoTiendaGenerico elementoPadre) throws Exception {
        this.deleteElement(element);
        this.busqueda(this.modeloBusqueda, elementoPadre);
    }
    
    protected abstract boolean matchesFilter(final S p0, final V p1);
    
    public void busqueda(final V modeloBusqueda, final ElementoTiendaGenerico elementoPadre) throws Exception {
        this.modeloBusqueda = modeloBusqueda;
        final List<S> elementos = this.getElementosListado(elementoPadre);
        this.vista.setTotalElementos(elementos.size());
        if (modeloBusqueda != null) {
            final List<S> elementosFiltrados = new ArrayList<S>();
            for (final S element : elementos) {
                if (this.matchesFilter(element, modeloBusqueda)) {
                    elementosFiltrados.add(element);
                }
            }
            this.vista.setElementos(elementosFiltrados);
        }
        else {
            this.vista.setElementos(elementos);
        }
        this.vista.generaVista();
    }
    
    protected abstract void deleteElement(final S p0) throws Exception;
    
    public void goToNewElement() {
        TiendaElectrodomesticosGUIController.getInstance().gestionarCambioVista(this.getVistaEdicionElemento(), null, TipoEdicionEnum.NUEVO);
    }
    
    public void editarElemento(final S elemento) {
        TiendaElectrodomesticosGUIController.getInstance().gestionarCambioVista(this.getVistaEdicionElemento(), elemento, TipoEdicionEnum.DETALLE);
    }
    
    protected abstract VistasEnum getVistaEdicionElemento();
}
