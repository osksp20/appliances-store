package es.osalguero.tiendaelect.service;

import java.util.Calendar;

import es.osalguero.tiendaelect.modelo.decorator.FinanciacionesTiendaDecorator;
import es.osalguero.tiendaelect.modelo.decorator.GenericListDecorator;
import es.osalguero.tiendaelect.modelo.negocio.Financiacion;
import es.osalguero.tiendaelect.modelo.negocio.Venta;

public class FinanciacionesService extends GenericService<Financiacion> {

	private VentasService ventasService;
	
	protected FinanciacionesService() {
	}
	
	protected FinanciacionesService(String ficheroElementos) {
	}
	
	public FinanciacionesService(String ficheroElementos, VentasService ventasService) {
		super(ficheroElementos);
		this.ventasService = ventasService;
		initVentasFinanciaciones();
	}
	
	public FinanciacionesService(VentasService ventasService) {
		super();
		this.ventasService = ventasService;
		initVentasFinanciaciones();
	}
	
	@Override
	protected Class<? extends GenericListDecorator<Financiacion>> getElementsDecoratorClass() {
		return FinanciacionesTiendaDecorator.class;
	}

	@Override
	protected void validaNuevoElemento(Financiacion financiacion) throws Exception {
		if(financiacion.getId() != null) {
			throw new Exception("El ID de la financiacion no puede estar informado");
		}
	}

	@Override
	protected void inicializarNuevoElemento(Financiacion financiacion) {
		financiacion.setId(Calendar.getInstance().getTimeInMillis());
		try {
			Thread.sleep(1);
		} catch(Exception e) {
			//Nothing to do
		}
	}

	@Override
	protected void validaElementoABorrar(Financiacion financiacion) throws Exception {
		if(financiacion.getId() == null) {
			throw new Exception("No se ha informado el ID de la financiación a borrar");
		}
	}

	@Override
	protected void validaElementoAModificar(Financiacion financiacion) throws Exception {
		if(financiacion.getId() == null) {
			throw new Exception("No se ha informado el ID de la financiacion a eliminar");
		}
		if(financiacion.getVenta() == null || financiacion.getVenta().getId() == null) {
			throw new Exception("No se ha informado la venta sobre la que se realiza la financiación");
		}
		if(financiacion.getPlazos() == null) {
			throw new Exception("No se han indicado los plazos de la financiación");
		}
		if(financiacion.getPlazos() > 60) {
			throw new Exception("El plazo máximo de financiación es de 60 meses");
		}
		Venta venta = ventasService.getVentaById(financiacion.getVenta().getId());
		if(venta == null) {
			throw new Exception("La venta indicada para la financiacion no existe");
		}
		for(Financiacion financiacionExistente : this.listado) {
			if(!financiacionExistente.equals(financiacion) && financiacionExistente.getVenta().equals(financiacion.getVenta())) {
				throw new Exception("Ya existe una financiación para la venta indicada");
			}
		}
		//Comprobacion de negocio
		if(venta.getCliente().getUltimaNomina() == null) {
			throw new Exception("El cliente no dispone de nómina para comprobar la viabilidad de la financiación");
		}
		if(!listado.contains(financiacion)) {
			//La estamos añadiendo
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			Calendar nominaCal = Calendar.getInstance();
			nominaCal.setTime(venta.getCliente().getUltimaNomina().getFecha());
			if(cal.compareTo(nominaCal) > 0) {
				throw new Exception("La fecha de la última nómina del cliente no puede ser anterior a un mes de la fecha actual");
			}
		}
		Double importeVenta = ventasService.getImporteVenta(venta.getId());
		Double cuota = importeVenta / financiacion.getPlazos();
		Double cuotaMax = new Double(venta.getCliente().getUltimaNomina().getCantidad());
		cuotaMax = cuotaMax * 0.15;
		if(cuota > cuotaMax) {
			throw new Exception("La cuota de la financiacion excede del máximo permitido para este cliente");
		}
	}

	@Override
	protected void preparaElementoAModificar(Financiacion financiacion) {
		financiacion.setVenta(ventasService.getVentaById(financiacion.getVenta().getId()));
	}

	@Override
	protected void actualizaEstadoTienda(Financiacion elemento) throws Exception {
		//Nothing to do
		
	}
	
	private void initVentasFinanciaciones() {
		for(Financiacion financiacion : listado) {
			financiacion.setVenta(ventasService.getVentaById(financiacion.getVenta().getId()));
		}
	}
	
	/************************************
	 * Métodos propios de este servicio *
	 ************************************/
	public Financiacion getFinanciacionByVenta(Venta venta) {
		for(Financiacion financiacion : this.listado) {
			if(financiacion.getVenta().equals(venta)) {
				try {
					return (Financiacion)financiacion.clone();
				} catch(Exception e) {
					return null;
				}
			}
		}
		return null;
	}
}
