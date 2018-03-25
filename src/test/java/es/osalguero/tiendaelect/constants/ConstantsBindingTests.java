package es.osalguero.tiendaelect.constants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.istack.internal.logging.Logger;

import es.osalguero.tiendaelect.modelo.Tienda;
import es.osalguero.tiendaelect.modelo.producto.Producto;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectFotografiaDigital;

public class ConstantsBindingTests {

	Logger log = Logger.getLogger(ConstantsBindingTests.class);
	
	public static void main(String args[]) {
		ConstantsBindingTests cbt = new ConstantsBindingTests();
		cbt.test1();
	}

	public void test1() {
		Tienda tienda = new Tienda();
		tienda.setNombre("Tienda de prueba");
		ElectFotografiaDigital camara = new ElectFotografiaDigital();
		camara.setColor("negro");
		//camara.setMarca("canon");
		camara.setModelo("ixus-700");
		camara.setPrecio(199.95f);
		camara.setTipo("camara digital");
		Map<TipoConexion, Integer> entradas = new HashMap<TipoConexion, Integer>();
		entradas.put(TipoConexion.USB, 1);
		Map<TipoConexion, Integer> salidas = new HashMap<TipoConexion, Integer>();
		salidas.put(TipoConexion.DVI, 1);
		camara.setEntradas(entradas);
		camara.setSalidas(salidas);
		List<Producto> productos = new ArrayList<Producto>();
		productos.add(camara);
		tienda.setProductos(productos);
		try {
			File f = new File("tienda.xml");
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			JAXBContext jaxbContext = JAXBContext.newInstance(Tienda.class);
			Marshaller m = jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(tienda, System.out);
			m.marshal(tienda, bw);
			bw.close();
			BufferedReader br = new BufferedReader(new FileReader(f));
			Unmarshaller u = jaxbContext.createUnmarshaller();
			Tienda newTienda = (Tienda)u.unmarshal(br);
			System.out.println(newTienda.getNombre());
			System.out.println(newTienda.getProductos().size());
			System.out.println(newTienda.getProductos().get(0).getClass());
			System.out.println(newTienda.getProductos().get(0).getMarca());
			br.close();
		} catch(Exception e) {
			log.logException(e, Level.SEVERE);
		}
	}
}
