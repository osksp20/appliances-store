package es.osalguero.tiendaelect.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import es.osalguero.tiendaelect.constants.EstadoReparacion;
import es.osalguero.tiendaelect.constants.SeccionTienda;
import es.osalguero.tiendaelect.modelo.Propiedades;
import es.osalguero.tiendaelect.modelo.negocio.ArticuloVenta;
import es.osalguero.tiendaelect.modelo.negocio.FichaCliente;
import es.osalguero.tiendaelect.modelo.negocio.FichaVenta;
import es.osalguero.tiendaelect.modelo.negocio.Reparacion;
import es.osalguero.tiendaelect.modelo.negocio.Venta;
import es.osalguero.tiendaelect.modelo.persona.Cliente;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectFotografiaDigital;

public class TiendaService {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	private PropiedadesService propiedadesService;
	private ProductosService productosService;
	private ClientesService clientesService;
	private EmpleadosService empleadosService;
	private VentasService ventasService;
	private DevolucionesService devolucionesService;
	private ReparacionesService reparacionesService;
	private FinanciacionesService financiacionesService;
	
	public TiendaService(String ficheroConfiguracion) throws Exception {
		this.propiedadesService = new PropiedadesService(ficheroConfiguracion);
		this.productosService = new ProductosService(
				this.propiedadesService.getValor(Propiedades.FICHERO_PRODUCTOS));
		this.clientesService = new ClientesService(
				this.propiedadesService.getValor(Propiedades.FICHERO_CLIENTES));
		this.empleadosService = new EmpleadosService(
				this.propiedadesService.getValor(Propiedades.FICHERO_EMPLEADOS));
		this.ventasService = new VentasService(
				this.propiedadesService.getValor(Propiedades.FICHERO_VENTAS),
				this.productosService, this.clientesService);
		this.devolucionesService = new DevolucionesService(
				this.propiedadesService.getValor(Propiedades.FICHERO_DEVOLUCIONES),
				ventasService, productosService);
		this.financiacionesService = new FinanciacionesService(
				this.propiedadesService.getValor(Propiedades.FICHERO_FINANCIACIONES),
				ventasService);
		this.reparacionesService = new ReparacionesService(
				this.propiedadesService.getValor(Propiedades.FICHERO_REPARACIONES),
				ventasService, productosService, empleadosService);
		
		if(clientesService.listado().isEmpty()) {
			Cliente cliente = new Cliente();
			cliente.setNombre("Prueba");
			cliente.setDni("11111111A");
			cliente.setApellido1("Prueba1");
			cliente.setApellido2("Prueba2");
			cliente.setDireccion("Direccion prueba");
			cliente.setTelefono("666666666");
			clientesService.crear(cliente);
		}
		if(productosService.listado().isEmpty()) {
			ElectFotografiaDigital producto = new ElectFotografiaDigital();
			producto.setMarca("canon");
			producto.setModelo("ixus-700");
			producto.setPrecio(199.99f);
			producto.setTipo("camara digital");
			producto.setColor("negro");
			producto.setCantidad(2);
			productosService.crear(producto);
		}
		if(ventasService.listado().isEmpty()) {
			Venta venta = new Venta();
			venta.setCliente(clientesService.listado().get(0));
			List<ArticuloVenta> articulos = new ArrayList<ArticuloVenta>();
			ArticuloVenta articulo = new ArticuloVenta();
			articulo.setProducto(productosService.listado().get(0));
			articulo.setCantidad(2);
			articulo.setPrecio(articulo.getProducto().getPrecio() * articulo.getCantidad());
			articulos.add(articulo);
			venta.setArticulosVenta(articulos);
			ventasService.crear(venta);
		}

		logger.info("Se han cargado "+this.productosService.listado().size()+" productos");
		logger.info("Se han cargado "+this.clientesService.listado().size()+" clientes");
		logger.info("Se han cargado "+this.empleadosService.listado().size()+" empleados");
		logger.info("Se han cargado "+this.ventasService.listado().size()+" ventas");
		logger.info("Se han cargado "+this.devolucionesService.listado().size()+" devoluciones");
		logger.info("Se han cargado "+this.reparacionesService.listado().size()+" reparaciones");
		logger.info("Se han cargado "+this.financiacionesService.listado().size()+" financiaciones");
	}
	
	public void guardar() throws Exception {
		this.productosService.guardar();
		this.clientesService.guardar();
		this.empleadosService.guardar();
		this.ventasService.guardar();
		this.devolucionesService.guardar();
		this.financiacionesService.guardar();
		this.reparacionesService.guardar();
	}
	
	public FichaCliente getFichaCliente(String clienteDni) {
		Cliente cliente = clientesService.getClienteByDni(clienteDni);
		if(cliente == null) {
			return null;
		}
		FichaCliente fichaCliente = new FichaCliente();
		fichaCliente.setCliente(cliente);
		List<Venta> ventasCliente = ventasService.getVentasByCliente(cliente);
		if(!ventasCliente.isEmpty()) {
			fichaCliente.setVentas(new ArrayList<FichaVenta>());
		}
		for(Venta venta : ventasCliente) {
			FichaVenta fichaVenta = new FichaVenta();
			fichaVenta.setVenta(venta);
			fichaVenta.setDevoluciones(devolucionesService.getDevolucionesByVenta(venta));
			fichaVenta.setReparaciones(reparacionesService.getReparacionesByVenta(venta));
			fichaVenta.setFinanciacion(financiacionesService.getFinanciacionByVenta(venta));
			fichaCliente.getVentas().add(fichaVenta);
		}
		return fichaCliente;	
	}
	
	public Map<SeccionTienda, Integer> getInventarioTienda() {
		return productosService.getInventarioBySeccion();
	}
	
	public List<String> getPiezasReparaciones() {
		List<String> piezas = new ArrayList<String>();
		for(Reparacion reparacion : reparacionesService.getReparacionesByEstado(EstadoReparacion.PARADO_PIEZAS)) {
			piezas.add(reparacion.getPiezas());
		}
		return piezas;
	}
	
	public Map<Cliente, List<Reparacion>> getReparacionesPendientesConfirmar() {
		Map<Cliente, List<Reparacion>> reparacionesCliente = new HashMap<Cliente, List<Reparacion>>();
		for(Reparacion reparacion : reparacionesService.getReparacionesByEstado(EstadoReparacion.PTE_CONFIRMAR_CLIENTE)) {
			if(!reparacionesCliente.containsKey(reparacion.getVenta().getCliente())) {
				reparacionesCliente.put(clientesService.getClienteByDni(reparacion.getVenta().getCliente().getDni()), 
						new ArrayList<Reparacion>());
			}
			reparacionesCliente.get(reparacion.getVenta().getCliente()).add(reparacion);
		}
		return reparacionesCliente;
	}
}
