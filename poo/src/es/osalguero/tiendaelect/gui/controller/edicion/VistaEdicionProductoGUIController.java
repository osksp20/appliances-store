package es.osalguero.tiendaelect.gui.controller.edicion;

import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.edicion.VistaEdicionProductoGUI;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.producto.Producto;

public class VistaEdicionProductoGUIController extends VistaAbstractaEdicionGUIController<VistaEdicionProductoGUI, Producto>
{
    public VistaEdicionProductoGUIController(final Producto producto, final TipoEdicionEnum tipoEdicion) {
        super(producto, null, tipoEdicion);
    }
    
    @Override
    protected VistaEdicionProductoGUI initVista(final Producto producto, final ElementoTiendaGenerico elementoPadre, final TipoEdicionEnum tipoEdicion) {
        return new VistaEdicionProductoGUI(this, producto, tipoEdicion);
    }
    
    @Override
    public Producto addNewElement(final Producto nuevoProducto) throws Exception {
        return TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().addProducto(nuevoProducto);
    }
    
    public void modificarProducto(final Producto producto) throws Exception {
        TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().modificarProducto(producto);
    }
}
