package es.osalguero.tiendaelect.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

import es.osalguero.tiendaelect.modelo.decorator.GenericListDecorator;
import es.osalguero.tiendaelect.modelo.decorator.VentasTiendaDecorator;
import es.osalguero.tiendaelect.modelo.negocio.ArticuloVenta;
import es.osalguero.tiendaelect.modelo.negocio.Venta;
import es.osalguero.tiendaelect.modelo.persona.Cliente;
import es.osalguero.tiendaelect.modelo.producto.Producto;

public class VentasService extends GenericService<Venta> {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private ProductosService productosService;
	private ClientesService clientesService;
	
	protected VentasService() {
	}
	
	protected VentasService(String ficheroElementos) {
	}

	public VentasService(String ficheroElementos, ProductosService productosService, 
			ClientesService clientesService) {
		super(ficheroElementos);
		this.productosService = productosService;
		this.clientesService = clientesService;
		for(Venta venta : this.listado()) {
			try {
				for(ArticuloVenta articulo : venta.getArticulosVenta()) {
					articulo.setProducto(productosService.getProductoById(articulo.getProducto().getId()));
				}
				venta.setCliente(clientesService.getClienteByDni(venta.getCliente().getDni()));
			} catch(Exception e) {
				this.listado.remove(venta);
				logger.log(Level.WARNING, "No ha podido reconstruirse la venta: ".concat(String.valueOf(venta.getId())));
			}
		}
	}

	public VentasService(ProductosService productosService, ClientesService clientesService) {
		super(null);
		this.productosService = productosService;
		this.clientesService = clientesService;
		for(Venta venta : this.listado()) {
			for(ArticuloVenta articulo : venta.getArticulosVenta()) {
				articulo.setProducto(productosService.getProductoById(articulo.getProducto().getId()));
			}
			venta.setCliente(clientesService.getClienteByDni(venta.getCliente().getDni()));
		}
	}
	
	@Override
	protected void validaNuevoElemento(Venta venta) throws Exception {
		if(venta.getId() != null) {
			throw new Exception("El ID de la venta a añadir debe ser nulo");
		}
	}

	@Override
	protected void inicializarNuevoElemento(Venta venta) {
		Calendar cal = Calendar.getInstance();
		try {
			//CUIDADO Esto es para que no se solapen los IDs generados
			Thread.sleep(1);
		} catch(Exception e) {
			//Nothing to do
		}
		venta.setId(cal.getTimeInMillis());
		venta.setFecha(cal.getTime());
	}

	@Override
	protected void validaElementoABorrar(Venta venta) throws Exception {
		//Nothing special to do
	}

	@Override
	protected void validaElementoAModificar(Venta venta) throws Exception {
		if(venta.getId() == null) {
			throw new Exception("No se ha indicado un ID de venta válido");
		}
		if(venta.getCliente() == null || venta.getCliente().getDni() == null) {
			throw new Exception("No se ha indicado un DNI de cliente válido");
		}
		if(clientesService.getClienteByDni(venta.getCliente().getDni()) == null) {
			throw new Exception("El cliente indicado no existe");
		}
		if(venta.getArticulosVenta() == null || venta.getArticulosVenta().isEmpty()) {
			throw new Exception("No se ha indicado una lista de productos válida");
		}
		Venta ventaExistente = this.getVentaById(venta.getId());
		for(ArticuloVenta articuloVenta : venta.getArticulosVenta()) {
			if(articuloVenta.getProducto() == null || articuloVenta.getProducto().getId() == null) {
				throw new Exception("No se ha indicado un ID de producto válido para la venta");
			}
			Producto producto = productosService.getProductoById(articuloVenta.getProducto().getId());
			if(producto == null) {
				throw new Exception("El producto indicado no existe");
			}
			if(articuloVenta.getCantidad() == null) {
				throw new Exception("No se ha indicado el número de productos comprados");
			}
			int cantidad = articuloVenta.getCantidad();
			if(ventaExistente != null) {
				for(ArticuloVenta articuloVentaExistente : ventaExistente.getArticulosVenta()) {
					if(articuloVentaExistente.getProducto().equals(articuloVenta.getProducto())) {
						cantidad = cantidad - articuloVentaExistente.getCantidad();
						break;
					}
				}
			}
			if(producto.getCantidad() < cantidad) {
				throw new Exception("No existen artículos disponibles");
			}
			if(articuloVenta.getPrecio() == null) {
				throw new Exception("No se ha indicado el precio del producto comprado");
			}
		}
		if(venta.getFecha() == null) {
			throw new Exception("No se ha indicado una fecha para la venta");
		}
		Calendar cal = Calendar.getInstance();
		Calendar ventaCal = Calendar.getInstance();
		ventaCal.setTime(venta.getFecha());
		if(cal.compareTo(ventaCal) < 0) {
			throw new Exception("La fecha de la venta no puede ser posterior a la fecha actual");
		}
	}

	@Override
	protected Class<? extends GenericListDecorator<Venta>> getElementsDecoratorClass() {
		return VentasTiendaDecorator.class;
	}

	@Override
	protected void preparaElementoAModificar(Venta venta) {
		venta.setCliente(clientesService.getClienteByDni(venta.getCliente().getDni()));
		for(ArticuloVenta articuloVenta : venta.getArticulosVenta()) {
			articuloVenta.setProducto(productosService.getProductoById(
					articuloVenta.getProducto().getId()));
		}
	}
	
	@Override
	protected void actualizaEstadoTienda(Venta venta) throws Exception {
		Venta ventaExistente = this.getVentaById(venta.getId());
		Map<Producto, Integer> cantidadesProductos = new HashMap<Producto, Integer>();
		for(ArticuloVenta articuloVenta : venta.getArticulosVenta()) {
			int cantidad = articuloVenta.getCantidad();
			if(ventaExistente != null) {
				for(ArticuloVenta articuloVentaExistente : ventaExistente.getArticulosVenta()) {
					if(articuloVentaExistente.getProducto().equals(articuloVenta.getProducto())) {
						cantidad = cantidad - articuloVentaExistente.getCantidad();
						break;
					}
				}
			}
			if(cantidadesProductos.get(articuloVenta.getProducto()) == null) {
				cantidadesProductos.put(articuloVenta.getProducto(), 0);
			}
			cantidadesProductos.put(articuloVenta.getProducto(),
					cantidadesProductos.get(articuloVenta.getProducto())+cantidad);
		}
		for(Entry<Producto, Integer> entryProducto : cantidadesProductos.entrySet()) {
			Producto producto = productosService.getProductoById(entryProducto.getKey().getId());
			producto.setCantidad(producto.getCantidad()-entryProducto.getValue());
			productosService.modificar(producto);
		}
	}
	
	/************************************
	 * Métodos propios de este servicio *
	 ************************************/	
	public Venta getVentaById(Long id) {
		for(Venta venta : listado) {
			if(venta.getId().equals(id)) {
				try {
					Venta ventaReturn = (Venta)venta.clone();
					List<ArticuloVenta> articulos = new ArrayList<ArticuloVenta>();
					for(ArticuloVenta articulo : venta.getArticulosVenta()) {
						articulos.add((ArticuloVenta)articulo.clone());
					}
					ventaReturn.setArticulosVenta(articulos);
					ventaReturn.setCliente(clientesService.getClienteByDni(venta.getCliente().getDni()));
					return ventaReturn;
				} catch(Exception e) {
					return null;
				}
			}
		}
		return null;
	}
	
	public double getImporteVenta(Long idVenta) throws Exception {
		Venta venta = this.getVentaById(idVenta);
		if(venta == null) {
			throw new Exception("No existe la venta indicada");
		}
		BigDecimal importe = new BigDecimal(0);
		for(ArticuloVenta articulo : venta.getArticulosVenta()) {
			importe = importe.add(new BigDecimal(articulo.getPrecio()));
		}
		return importe.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	public List<Venta> getVentasByCliente(Cliente cliente) {
		List<Venta> ventas = new ArrayList<Venta>();
		for(Venta venta : this.listado) {
			if(venta.getCliente().equals(cliente)) {
				try {
					ventas.add((Venta)venta.clone());
				} catch(Exception e) {
					//Nothing to do
				}
			}
		}
		return ventas;
	}
}
