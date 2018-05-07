package es.osalguero.tiendaelect.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.service.TiendaService;

public class TiendaElectrodomesticosApp {
	
	private final String ficheroConfiguracion;
	private TiendaService tiendaService;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public TiendaElectrodomesticosApp() {
		this.ficheroConfiguracion = null;
		this.initTienda();
	}
	
	public TiendaElectrodomesticosApp(String ficheroConfiguracion) {
		this.ficheroConfiguracion = ficheroConfiguracion;
		this.initTienda();
	}
	
	private void initTienda() {
		try {
			this.tiendaService = new TiendaService(ficheroConfiguracion);
			TiendaElectrodomesticosGUIController tiendaController =
					new TiendaElectrodomesticosGUIController(tiendaService);
		} catch(Exception e) {
			logger.log(Level.SEVERE, "Se ha producido un error inesperado y se ha cerrado la aplicación", e);
		}
	}
	
}
