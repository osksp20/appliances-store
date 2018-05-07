package es.osalguero.tiendaelect.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import es.osalguero.tiendaelect.modelo.decorator.DevolucionesTiendaDecorator;
import es.osalguero.tiendaelect.modelo.decorator.GenericListDecorator;
import es.osalguero.tiendaelect.modelo.negocio.ArticuloVenta;
import es.osalguero.tiendaelect.modelo.negocio.Devolucion;
import es.osalguero.tiendaelect.modelo.negocio.Venta;
import es.osalguero.tiendaelect.modelo.producto.Producto;

public class DevolucionesService extends GenericService<Devolucion> {

	private VentasService ventasService;
	private ProductosService productosService;
	
	protected DevolucionesService() {
	}
	
	protected DevolucionesService(String ficheroElementos) {
	}
	
	public DevolucionesService(String ficheroElementos, VentasService ventasService, ProductosService productosService) {
		super(ficheroElementos);
		this.ventasService = ventasService;
		this.productosService = productosService;
		initVentasDevoluciones();
	}
	
	public DevolucionesService(VentasService ventasService, ProductosService productosService) {
		super();
		this.ventasService = ventasService;
		this.productosService = productosService;
		initVentasDevoluciones();
	}
	
	@Override
	protected Class<? extends GenericListDecorator<Devolucion>> getElementsDecoratorClass() {
		return DevolucionesTiendaDecorator.class;
	}

	@Override
	protected void validaNuevoElemento(Devolucion devolucion) throws Exception {
		if(devolucion.getId() != null) {
			throw new Exception("El ID de la devolución no puede estar informado");
		}
		if(devolucion.getFecha() != null) {
			throw new Exception("La fecha de la devolucion no puede estar informada");
		}
	}

	@Override
	protected void inicializarNuevoElemento(Devolucion devolucion) {
		Calendar cal = Calendar.getInstance();
		try {
			Thread.sleep(1);
		} catch(Exception e) {
			//Nothing to do
		}
		devolucion.setId(cal.getTimeInMillis());
		devolucion.setFecha(cal.getTime());
	}

	@Override
	protected void validaElementoABorrar(Devolucion devolucion) throws Exception {
		if(devolucion.getId() == null) {
			throw new Exception("Debe indicarse el ID de la devolución a borrar");
		}
	}

	@Override
	protected void validaElementoAModificar(Devolucion devolucion) throws Exception {
		if(devolucion.getId() == null) {
			throw new Exception("El ID de la devolución debe estar informado");
		}
		if(devolucion.getFecha() == null) {
			throw new Exception("La fecha de la devolución debe estar informada");
		}
		if(devolucion.getVenta() == null || devolucion.getVenta().getId() == null) {
			throw new Exception("No se ha informado la venta sobre la que se realiza la devolución");
		}
		if(devolucion.getArticulosDevolucion() == null || devolucion.getArticulosDevolucion().isEmpty()) {
			throw new Exception("No se han informado los productos que se devuelven");
		}
		Venta venta = ventasService.getVentaById(devolucion.getVenta().getId());
		if(venta == null) {
			throw new Exception("La venta indicada para la devolución no existe");
		}
		List<Long> articulosDevueltos = new ArrayList<Long>();
		for(ArticuloVenta articuloDevolucion : devolucion.getArticulosDevolucion()) {
			if(articuloDevolucion.getProducto() == null || articuloDevolucion.getProducto().getId() == null) {
				throw new Exception("No se ha indicado el ID del producto de la devolución");
			}
			if(articulosDevueltos.contains(articuloDevolucion.getProducto().getId())) {
				throw new Exception("Se ha indicado mas de una vez el mismo producto en la devolución");
			}
			if(articuloDevolucion.getCantidad() == null) {
				throw new Exception("No se ha indicado la cantidad de productos que se devuelven");
			}
			articulosDevueltos.add(articuloDevolucion.getProducto().getId());
			boolean ventaTieneProducto = false;
			Calendar fechaDevolucion = Calendar.getInstance();
			fechaDevolucion.setTime(devolucion.getFecha());
			fechaDevolucion.add(Calendar.MONTH, -3);
			Calendar fechaVenta = Calendar.getInstance();
			fechaVenta.setTime(devolucion.getFecha());
			if(fechaDevolucion.compareTo(fechaVenta) > 0) {
				throw new Exception("La fecha de la venta no puede ser mayor de tres meses anterior a la devolución");
			}
			for(ArticuloVenta articuloVenta : venta.getArticulosVenta()) {
				if(articuloVenta.getProducto().getId().equals(articuloDevolucion.getProducto().getId())) {
					ventaTieneProducto = true;
					int cantidad = articuloVenta.getCantidad() - articuloDevolucion.getCantidad();
					//Compruebo todas las devoluciones sobre esta venta y los productos
					for(Devolucion devolucionComprobar : this.listado) {
						if(!devolucionComprobar.getId().equals(devolucion.getId()) && devolucionComprobar.getVenta().equals(devolucion.getVenta())) {
							for(ArticuloVenta articuloDevolucionComprobar : devolucionComprobar.getArticulosDevolucion()) {
								if(articuloDevolucionComprobar.getProducto().getId().equals(articuloDevolucion.getProducto().getId())) {
									cantidad = cantidad - articuloDevolucionComprobar.getCantidad();
									break;
								}
							}
						}
					}
					if(cantidad < 0) {
						throw new Exception("La cantidad de productos devueltos es superior al número de los existentes en la venta");
					}
					break;
				}
			}
			if(ventaTieneProducto == false) {
				throw new Exception("El producto indicado en la devolución no pertenece a la venta asociada");
			}
		}
	}

	@Override
	protected void preparaElementoAModificar(Devolucion devolucion) {
		devolucion.setVenta(ventasService.getVentaById(devolucion.getVenta().getId()));
		for(ArticuloVenta articuloDevolucion : devolucion.getArticulosDevolucion()) {
			for(ArticuloVenta articuloVenta : devolucion.getVenta().getArticulosVenta()) {
				if(articuloVenta.getProducto().getId().equals(articuloDevolucion.getProducto().getId())) {
					articuloDevolucion.setProducto(productosService.getProductoById(
							articuloDevolucion.getProducto().getId()));
					break;
				}
			}
		}
	}
	
	private void initVentasDevoluciones() {
		for(Devolucion devolucion : listado) {
			devolucion.setVenta(ventasService.getVentaById(devolucion.getVenta().getId()));
			for(ArticuloVenta articuloDevolucion : devolucion.getArticulosDevolucion()) {
				articuloDevolucion.setProducto(productosService.getProductoById(
						articuloDevolucion.getProducto().getId()));
			}
		}
	}
	
	@Override
	protected void actualizaEstadoTienda(Devolucion devolucion) throws Exception {
		Devolucion devolucionExistente = this.getDevolucionById(devolucion.getId());
		Map<Producto, Integer> cantidadesProductos = new HashMap<Producto, Integer>();
		for(ArticuloVenta articuloDevolucion : devolucion.getArticulosDevolucion()) {
			int cantidad = articuloDevolucion.getCantidad();
			if(devolucionExistente != null) {
				for(ArticuloVenta articuloDevolucionExistente : devolucionExistente.getArticulosDevolucion()) {
					if(articuloDevolucionExistente.equals(articuloDevolucion)) {
						cantidad = cantidad - articuloDevolucionExistente.getCantidad();
						break;
					}
				}
			}
			if(cantidadesProductos.get(articuloDevolucion.getProducto()) == null) {
				cantidadesProductos.put(articuloDevolucion.getProducto(), 0);
			}
			cantidadesProductos.put(articuloDevolucion.getProducto(),
					cantidadesProductos.get(articuloDevolucion.getProducto())+cantidad);
		}
		for(Entry<Producto, Integer> entryProducto : cantidadesProductos.entrySet()) {
			Producto producto = productosService.getProductoById(entryProducto.getKey().getId());
			producto.setCantidad(producto.getCantidad()+entryProducto.getValue());
			productosService.modificar(producto);
		}
	}
	
	/************************************
	 * Métodos propios de este servicio *
	 ************************************/
	public List<Devolucion> getDevolucionesByVenta(Venta venta) {
		List<Devolucion> devoluciones = new ArrayList<Devolucion>();
		for(Devolucion devolucion : this.listado) {
			if(devolucion.getVenta().equals(venta)) {
				try {
					devoluciones.add((Devolucion)devolucion.clone());
				} catch(Exception e) {
					//Nothing to do
				}
			}
		}
		return devoluciones;
	}
	
	public Devolucion getDevolucionById(Long devolucionId) {
		for(Devolucion devolucion : listado) {
			if(devolucion.getId().equals(devolucionId)) {
				try {
					return (Devolucion)devolucion.clone();
				} catch(Exception e) {
					return null;
				}
			}
		}
		return null;
	}
}
