package es.osalguero.tiendaelect.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.osalguero.tiendaelect.modelo.Propiedades;

public class PropiedadesService {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	private final String FICHERO_PROPIEDADES_DEFAULT="cfg/tiendaElectrodomesticos.properties";
	private final String ficheroPropiedades;
	private Map<Propiedades, String> propiedades;

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
		String linea = null;
		try {
			File f = new File(ficheroPropiedades);
			br = new BufferedReader(new FileReader(f));
			propiedades = new HashMap<Propiedades, String>();
			linea = br.readLine();
			while(linea != null) {
				String valoresPropiedad[] = linea.split("=");
				if(valoresPropiedad.length == 2) {
					Propiedades propiedad = Propiedades.getByNombrePropiedad(valoresPropiedad[0]);
					if(propiedad != null) {
						propiedades.put(propiedad, valoresPropiedad[1]);
					}
				}
				linea = br.readLine();
			}
		} catch(Exception e) {
			if(linea != null) {
				logger.log(Level.WARNING,"Error procesando la siguiente línea del fichero de propiedades '".concat(ficheroPropiedades).concat("'\n").concat(linea));
			}
			logger.log(Level.WARNING, "No se han podido recuperar las propiedades del fichero de propiedades, se aplicarán las propiedades por defecto.");
			propiedades = new HashMap<Propiedades, String>();
			for(Propiedades propiedad : Propiedades.values()) {
				if(propiedad.name().startsWith("FICHERO")) {
					propiedades.put(propiedad, "data/".concat(propiedad.getNombrePropiedad()).concat(".xml"));
				} else if(propiedad.getNombrePropiedad() != null){
					propiedades.put(propiedad, propiedad.getNombrePropiedad());
				} else {
					propiedades.put(propiedad, propiedad.name().toLowerCase());
				}
			}
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
	
	public void guardar() throws Exception {
		BufferedWriter bw = null;
		try {
			File ficheroElementos = new File(this.ficheroPropiedades);
			File ficheroElementosParent = ficheroElementos.getParentFile();
			ficheroElementosParent.mkdirs();
			ficheroElementos.createNewFile();
			bw = new BufferedWriter(new FileWriter(ficheroElementos));
			for(Entry<Propiedades,String> propiedad : this.propiedades.entrySet()) {
				bw.write(propiedad.getKey().getNombrePropiedad().concat("=").concat(propiedad.getValue()).concat("\n"));
			}
		} catch(Exception e) {
			throw e;
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch(Exception e) {
					//Nothing to do
				}
			}
		}
	}
	
	public void modificarPropiedad(Propiedades propiedad, String valor) throws Exception {
		if(propiedad == null) {
			throw new Exception("Debe indicarse una propiedad válida");
		}
		if(valor == null) {
			throw new Exception("Debe indicarse un valor de propiedad válido");
		}
		propiedades.put(propiedad, valor);
	}

}
