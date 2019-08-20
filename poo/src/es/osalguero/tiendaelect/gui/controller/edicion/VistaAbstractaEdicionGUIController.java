package es.osalguero.tiendaelect.gui.controller.edicion;

import es.osalguero.tiendaelect.gui.controller.AbstractGUIController;
import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.edicion.VistaAbstractaEdicionGUI;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;

public abstract class VistaAbstractaEdicionGUIController<T extends VistaAbstractaEdicionGUI<?, O>, O extends ElementoTiendaGenerico> extends AbstractGUIController<VistaAbstractaEdicionGUI<?, O>>
{

    public VistaAbstractaEdicionGUIController(final O elementoEdicion, final ElementoTiendaGenerico elementoPadre, TipoEdicionEnum tipoEdicion) {
        if (elementoEdicion == null) {
            tipoEdicion = TipoEdicionEnum.NUEVO;
        }
        try {
            this.setVista(elementoEdicion, elementoPadre, tipoEdicion);
        }
        catch (Exception e) {
           	TiendaElectrodomesticosGUIController.getInstance().gestionarErrorAplicacion(e);
        }
    }
    
    @Override
    protected void setVista() throws Exception {
    }
    
    protected abstract T initVista(final O p0, final ElementoTiendaGenerico p1, final TipoEdicionEnum p2);
    
    protected void setVista(final O elementoEdicion, final ElementoTiendaGenerico elementoPadre, final TipoEdicionEnum tipoEdicion) throws Exception {
        this.vista = this.initVista(elementoEdicion, elementoPadre, tipoEdicion);
    }
    
    public O nuevoElemento(final O elementoNuevo) throws Exception {
        O elementoCreado = this.addNewElement(elementoNuevo);
        TiendaElectrodomesticosGUIController.getInstance().setElementoActual(elementoCreado);
        return elementoCreado;
    }
    
    public abstract O addNewElement(final O p0) throws Exception;
}
