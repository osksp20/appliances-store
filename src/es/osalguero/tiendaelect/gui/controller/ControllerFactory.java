package es.osalguero.tiendaelect.gui.controller;

import es.osalguero.tiendaelect.constants.CategoriaEmpleado;
import es.osalguero.tiendaelect.gui.controller.edicion.VistaEdicionClienteGUIController;
import es.osalguero.tiendaelect.gui.controller.edicion.VistaEdicionEmpleadoGUIController;
import es.osalguero.tiendaelect.gui.controller.gestionApp.VistaCargaDatosGUIController;
import es.osalguero.tiendaelect.gui.controller.gestionApp.VistaConfiguracionPropiedadesGUIController;
import es.osalguero.tiendaelect.gui.controller.gestionApp.VistaExceptionGUIController;
import es.osalguero.tiendaelect.gui.controller.gestionApp.VistaLoginGUIController;
import es.osalguero.tiendaelect.gui.controller.listado.VistaListadoClientesGUIController;
import es.osalguero.tiendaelect.gui.controller.listado.VistaListadoEmpleadosGUIController;
import es.osalguero.tiendaelect.gui.controller.listado.VistaListadoProductosGUIController;
import es.osalguero.tiendaelect.gui.controller.principal.VistaPrincipalComercialGUIController;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.persona.Cliente;
import es.osalguero.tiendaelect.modelo.persona.Empleado;

public class ControllerFactory {

	public static AbstractGUIController<?> createViewController(final VistasEnum tipoVista, final VistasEnum vistaAnterior, 
			final ElementoTiendaGenerico elementoAnterior, final Empleado empleado, final ElementoTiendaGenerico elementoEdicion,
			final TipoEdicionEnum tipoEdicion) throws Exception {
        AbstractGUIController<?> controller = null;
        switch (tipoVista) {
            case VISTA_LOGIN: {
                controller = new VistaLoginGUIController();
                break;
            }
            case VISTA_PRINCIPAL: {
                if (CategoriaEmpleado.COMERCIAL.equals(empleado.getCategoria())) {
                    controller = new VistaPrincipalComercialGUIController();
                    break;
                }
                
                throw new Exception("No se ha definido una vista para un empleado de tipo: ".concat(empleado.getCategoria().getNombre()));
            }
            case VISTA_EXCEPTION: {
                controller = new VistaExceptionGUIController();
                break;
            }
            case VISTA_CARGA_DATOS: {
                controller = new VistaCargaDatosGUIController();
                break;
            }
            case VISTA_CONFIGURACION: {
                controller = new VistaConfiguracionPropiedadesGUIController();
                break;
            }
            case VISTA_LISTADO_EMPLEADOS: {
            	controller = new VistaListadoEmpleadosGUIController(elementoEdicion);
            	break;
            }
            case VISTA_EDICION_EMPLEADO: {
            	controller = new VistaEdicionEmpleadoGUIController((Empleado)elementoEdicion, tipoEdicion);
            	break;
            }
            case VISTA_LISTADO_CLIENTES: {
            	controller = new VistaListadoClientesGUIController(elementoEdicion);
            	break;
            }
            case VISTA_EDICION_CLIENTE: {
            	controller = new VistaEdicionClienteGUIController((Cliente)elementoEdicion, tipoEdicion);
            	break;
            }
            case VISTA_LISTADO_PRODUCTOS: {
            	controller = new VistaListadoProductosGUIController(elementoEdicion);
            	break;
            }
            default: {
                throw new Exception("No se ha definido ninguna vista para: ".concat(tipoVista.toString()));
            }
        }
        return controller;
	}
	
}
