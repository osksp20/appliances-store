package es.osalguero.tiendaelect.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import es.osalguero.tiendaelect.modelo.Propiedades;

public class PropiedadesService {

	private final String FICHERO_PROPIEDADES_DEFAULT="cfg/tiendaElectrodomesticos.properties";
	private final String ficheroPropiedades;
	private final Map<Propiedades, String> propiedades;

	public PropiedadesService() throws Exception {
		ficheroPropiedades = FICHERO_PROPIEDADES_DEFAULT;
		propiedades = leerPropiedades();
	}

	public PropiedadesService(String ficheroPropiedades) throws Exception {
		if(ficheroPropiedades == null) {
			this.ficheroPropiedades = FICHERO_PROPIEDADES_DEFAULT;
		} else {
			this.ficheroPropiedades = ficheroPropiedades;
		}
		propiedades = leerPropiedades();
	}

	private Map<Propiedades, String> leerPropiedades() throws Exception {
		Map<Propiedades, String> propiedades = null;
		BufferedReader br = null;
		try {
			File f = new File(ficheroPropiedades);
			br = new BufferedReader(new FileReader(f));
			propiedades = new HashMap<Propiedades, String>();
			String linea = br.readLine();
			while(linea != null) {
				String valoresPropiedad[] = linea.split("=");
				if(valoresPropiedad.length == 2) {
					Propiedades propiedad = Propiedades.valueOf(valoresPropiedad[0]);
					if(propiedad != null) {
						propiedades.put(propiedad, valoresPropiedad[1]);
					}
				}
				linea = br.readLine();
			}
		} catch(Exception e) {
			throw new Exception("No se han podido obtener los valores de las propiedades de la aplicación", e);
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch(Exception e) {
					//Do nothing
				}
			}
		}
		for(Propiedades propiedad : Propiedades.values()) {
			if(!propiedades.containsKey(propiedad)) {
				throw new Exception("No se ha podido obtener el valor de la propiedad ".concat(propiedad.toString()));
			}
		}
		return propiedades;
	}

	public String getValor(Propiedades propiedad) {
		return propiedades.get(propiedad);
	}

}
