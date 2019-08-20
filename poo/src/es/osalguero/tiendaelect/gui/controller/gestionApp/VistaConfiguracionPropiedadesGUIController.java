package es.osalguero.tiendaelect.gui.controller.gestionApp;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import es.osalguero.tiendaelect.gui.controller.AbstractGUIController;
import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.gestionApp.VistaConfiguracionPropiedadesGUI;
import es.osalguero.tiendaelect.modelo.Propiedades;

public class VistaConfiguracionPropiedadesGUIController extends AbstractGUIController<VistaConfiguracionPropiedadesGUI>
{
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
    @Override
    protected void setVista() throws Exception {
        this.vista = new VistaConfiguracionPropiedadesGUI(this);
    }
    
    public void guardarConfiguracion(final Map<Propiedades, String> propiedades) {
        try {
            for (final Map.Entry<Propiedades, String> propiedadEntry : propiedades.entrySet()) {
                TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().modificarPropiedad(propiedadEntry.getKey(), propiedadEntry.getValue());
            }
            JOptionPane.showMessageDialog(this.getTallerFrameContainer(), "Los cambios se han guardado correctamente.\nPor favor, recuerde guardar los cambios a través del menú 'Archivo' -> 'Guardar cambios'\npara que estos sean permanentes.", "Operación realizada", -1);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Error intentando modificar propieades de configuración de la aplicación", e);
            this.showError("Se ha producido un error intentando guardar la configuración.\nPor favor revise los logs de la aplicación.");
        }
    }
}
