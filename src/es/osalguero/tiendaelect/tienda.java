package es.osalguero.tiendaelect;

import es.osalguero.tiendaelect.app.TiendaElectrodomesticosApp;

public class tienda {

	/**
	 * Método de acceso a la aplicación.
	 * Puede ejecutarse con el parámetro 'ficheroPropiedades=XXX' lo que permite que la 
	 * aplicación se configure según los valores contenidos en el fichero de propiedades
	 * indicado
	 * 
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		String ficheroPropiedades = null;
		if(args != null && args.length > 0) {
			for(int i=0;i<args.length;i++) {
				String appArgs[] = args[i].split("=");
				if(appArgs.length == 2 && appArgs[0].equals("ficheroPropiedades")) {
					ficheroPropiedades = appArgs[1];
				}
			}
		}
		TiendaElectrodomesticosApp app = new TiendaElectrodomesticosApp(ficheroPropiedades);
	}
	
}
