package es.osalguero.tiendaelect.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.osalguero.tiendaelect.constants.CategoriaEmpleado;
import es.osalguero.tiendaelect.constants.EstadoReparacion;
import es.osalguero.tiendaelect.modelo.decorator.GenericListDecorator;
import es.osalguero.tiendaelect.modelo.decorator.ReparacionesTiendaDecorator;
import es.osalguero.tiendaelect.modelo.negocio.ArticuloVenta;
import es.osalguero.tiendaelect.modelo.negocio.Reparacion;
import es.osalguero.tiendaelect.modelo.negocio.Venta;
import es.osalguero.tiendaelect.modelo.persona.Empleado;

public class ReparacionesService extends GenericService<Reparacion> {

	private VentasService ventasService;
	private ProductosService productosService;
	private EmpleadosService empleadosService;
	
	protected ReparacionesService() {
	}
	
	protected ReparacionesService(String ficheroElementos) {
	}
	
	public ReparacionesService(String ficheroElementos, VentasService ventasService, ProductosService productosService,
			EmpleadosService empleadosService) {
		super(ficheroElementos);
		this.ventasService = ventasService;
		this.productosService = productosService;
		this.empleadosService = empleadosService;
		initVentasReparaciones();
	}
	
	public ReparacionesService(VentasService ventasService, ProductosService productosService, EmpleadosService empleadosService) {
		super();
		this.ventasService = ventasService;
		this.productosService = productosService;
		this.empleadosService = empleadosService;
		initVentasReparaciones();
	}
	
	@Override
	protected Class<? extends GenericListDecorator<Reparacion>> getElementsDecoratorClass() {
		return ReparacionesTiendaDecorator.class;
	}

	@Override
	protected void validaNuevoElemento(Reparacion reparacion) throws Exception {
		if(reparacion.getId() != null) {
			throw new Exception("El ID de la reparación no puede estar informado");
		}
		if(reparacion.getFechaReparacion() != null) {
			throw new Exception("La fecha de la reparación no puede estar informada");
		}
		if(reparacion.getFechaEntrega() != null) {
			throw new Exception("La fecha de entrega no puede estar informada");
		}
		if(reparacion.getImporte() != null) {
			throw new Exception("El importe de la reparación no puede informarse hasta que el producto sea devuelto");
		}
		if(reparacion.getEstado() != null) {
			throw new Exception("El estado de la reparación no puede estar informada");
		}
	}

	@Override
	protected void inicializarNuevoElemento(Reparacion reparacion) {
		Calendar cal = Calendar.getInstance();
		try {
			Thread.sleep(1);
		} catch(Exception e) {
			//Nothing to do
		}
		reparacion.setId(cal.getTimeInMillis());
		reparacion.setFechaReparacion(cal.getTime());
		reparacion.setEstado(EstadoReparacion.PENDIENTE);
	}

	@Override
	protected void validaElementoABorrar(Reparacion reparacion) throws Exception {
		if(reparacion.getId() == null) {
			throw new Exception("Debe indicarse el ID de la reparación a borrar");
		}
	}

	@Override
	protected void validaElementoAModificar(Reparacion reparacion) throws Exception {
		if(reparacion.getId() == null) {
			throw new Exception("El ID de la reparación debe estar informado");
		}
		if(reparacion.getFechaReparacion() == null) {
			throw new Exception("La fecha de la reparación debe estar informada");
		}
		if(reparacion.getVenta() == null || reparacion.getVenta().getId() == null) {
			throw new Exception("No se ha informado la venta sobre la que se realiza la reparación");
		}
		if(reparacion.getProducto() == null || reparacion.getProducto().getId() == null) {
			throw new Exception("No se ha informado el producto que se repara");
		}
		if(reparacion.getImporte() != null && reparacion.getFechaEntrega() == null) {
			throw new Exception("No puede indicarse un importe antes de la entrega");
		}
		Venta venta = ventasService.getVentaById(reparacion.getVenta().getId());
		if(venta == null) {
			throw new Exception("La venta indicada para la reparación no existe");
		}
		boolean ventaContainsProducto = false;
		for(ArticuloVenta articulo : venta.getArticulosVenta()) {
			if(articulo.getProducto().equals(reparacion.getProducto())) {
				ventaContainsProducto = true;
				break;
			}
		}
		if(!ventaContainsProducto) {
			throw new Exception("El producto indicado no pertenece a la venta indicada");
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(venta.getFecha());
		Calendar reparacionCal = Calendar.getInstance();
		reparacionCal.setTime(reparacion.getFechaReparacion());
		cal.add(Calendar.MONTH, 6);
		boolean warranty = cal.compareTo(reparacionCal) > 0;
		if(reparacion.getImporte() == null && !warranty) {
			throw new Exception("La fecha de venta es anterior a 6 meses, por lo que debe indicarse un importe de reparación");
		} else if(reparacion.getImporte() != null && warranty) {
			reparacion.setImporte(new Double(0));
		}
		if(reparacion.getFechaEntrega() != null) {
			cal = Calendar.getInstance();
			Calendar entregaCal = Calendar.getInstance();
			if(cal.compareTo(entregaCal) > 0) {
				throw new Exception("La fecha de entrega de la reparación no puede ser anterior a la fecha de la reparación");
			}
		}
		if(reparacion.getDescripcion() == null || reparacion.getDescripcion().trim().isEmpty()) {
			throw new Exception("No se ha indicado el problema a reparar");
		}
		if(reparacion.getEmpleadoReparacion() == null || reparacion.getEmpleadoReparacion().getLogin() == null) {
			throw new Exception("Debe indicarse un empleado asignado a la reparación");
		}
		Empleado empleadoReparacion = empleadosService.getEmpleadoByLogin(reparacion.getEmpleadoReparacion().getLogin());
		if(empleadoReparacion == null) {
			throw new Exception("El empleado asignado a la reparación no existe");
		}
		if(!CategoriaEmpleado.REPARACION.equals(reparacion.getEmpleadoReparacion().getCategoria())) {
			throw new Exception("El empleado asignado a la reparación no es de la categoría adecuada");
		}
		if(reparacion.getEstado() == null) {
			throw new Exception("Debe indicarse un estado para la reparación");
		}
		if(EstadoReparacion.PARADO_PIEZAS.equals(reparacion.getEstado()) && reparacion.getPiezas() == null) {
			throw new Exception("Deben indicarse las piezas necesarias para la reparación");
		}
	}

	@Override
	protected void preparaElementoAModificar(Reparacion reparacion) {
		reparacion.setVenta(ventasService.getVentaById(reparacion.getVenta().getId()));
		for(ArticuloVenta articuloVenta : reparacion.getVenta().getArticulosVenta()) {
			if(articuloVenta.getProducto().getId().equals(reparacion.getProducto().getId())) {
				try{
					reparacion.setProducto(productosService.getProductoById(reparacion.getProducto().getId()));
				} catch(Exception e) {
					//TODO
				}
				break;
			}
		}
		reparacion.setEmpleado(empleadosService.getEmpleadoByLogin(reparacion.getEmpleadoReparacion().getLogin()));
	}
	
	@Override
	protected void actualizaEstadoTienda(Reparacion elemento) throws Exception {
		//Nothing to do
	}
	
	private void initVentasReparaciones() {
		for(Reparacion reparacion : listado) {
			reparacion.setVenta(ventasService.getVentaById(reparacion.getVenta().getId()));
			for(ArticuloVenta articuloVenta : reparacion.getVenta().getArticulosVenta()) {
				if(articuloVenta.getProducto().equals(reparacion.getProducto())) {
					reparacion.setProducto(productosService.getProductoById(reparacion.getProducto().getId()));
					break;
				}
			}
		}
	}

	/************************************
	 * Métodos propios de este servicio *
	 ************************************/
	public List<Reparacion> getReparacionesByVenta(Venta venta) {
		List<Reparacion> reparaciones = new ArrayList<Reparacion>();
		for(Reparacion reparacion : this.listado) {
			if(reparacion.getVenta().equals(venta)) {
				try {
					reparaciones.add((Reparacion)reparacion.clone());
				} catch(Exception e) {
					//Nothing to do
				}
			}
		}
		return reparaciones;
	}
	
	public List<Reparacion> getReparacionesByEmpleado(String login) {
		Empleado empleado = empleadosService.getEmpleadoByLogin(login);
		if(empleado == null) {
			return null;
		}
		List<Reparacion> reparaciones = new ArrayList<Reparacion>();
		for(Reparacion reparacion : listado) {
			if(reparacion.getEmpleadoReparacion().equals(empleado)) {
				try {
					reparaciones.add((Reparacion)reparacion.clone());
				} catch(Exception e) {
					//Nothing to do
				}
			}
		}
		return reparaciones;
	}
	
	public List<Reparacion> getReparacionesByEstado(EstadoReparacion estado) {
		List<Reparacion> reparaciones = new ArrayList<Reparacion>();
		for(Reparacion reparacion : listado) {
			if(estado.equals(reparacion.getEstado())) {
				try {
					reparaciones.add((Reparacion)reparacion.clone());
				} catch(Exception e) {
					//No lo añado
				}
			}
		}
		return reparaciones;
	}
}
