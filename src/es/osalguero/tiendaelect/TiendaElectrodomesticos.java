package es.osalguero.tiendaelect;

import es.osalguero.tiendaelect.app.TiendaElectrodomesticosApp;

public class TiendaElectrodomesticos {

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
		new TiendaElectrodomesticosApp(ficheroPropiedades);
	}
	
}
