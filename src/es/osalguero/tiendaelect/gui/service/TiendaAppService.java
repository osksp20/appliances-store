package es.osalguero.tiendaelect.gui.service;

import java.util.Calendar;
import java.util.List;

import es.osalguero.tiendaelect.modelo.Propiedades;
import es.osalguero.tiendaelect.modelo.negocio.Financiacion;
import es.osalguero.tiendaelect.modelo.negocio.Reparacion;
import es.osalguero.tiendaelect.modelo.negocio.Venta;
import es.osalguero.tiendaelect.modelo.persona.Cliente;
import es.osalguero.tiendaelect.modelo.persona.Empleado;
import es.osalguero.tiendaelect.service.TiendaService;

public class TiendaAppService {

	private TiendaService tiendaService;
    private List<Empleado> empleados;
	private Empleado empleadoLogin;
	private boolean cambiosSinGuardar = false;
	private boolean tiendaInicializada = false;

	protected TiendaAppService() {
	}

	public TiendaAppService(TiendaService tiendaService) throws Exception {
		if(tiendaService == null) {
			throw new Exception("Debe proporcionarse un servicio de tienda para poder operar");
		}
		this.tiendaService = tiendaService;
	}

	public void inicializarTienda() throws Exception {
		tiendaService.cargarEmpleados();
		this.empleados = tiendaService.getEmpleadosService().listado();
        this.tiendaInicializada = true;
    }
	
	public void cargarDatosTienda() throws Exception {
		this.tiendaService.cargarDatos();
		this.tiendaInicializada = true;
	}

	public Empleado getEmpleadoLogin() {
		return this.empleadoLogin;
	}

	public boolean getCambiosSinGuardar() {
		return cambiosSinGuardar;
	}

	public boolean isTiendaInicializada() {
		return this.tiendaInicializada;
	}

	public boolean login(String login) {
		Empleado empleadoSearch = new Empleado();
		empleadoSearch.setLogin(login);
		return this.empleados.contains(empleadoSearch);
	}

	public void logout() {
		this.empleadoLogin = null;
		this.cambiosSinGuardar = false;
	}
	
	public void guardarDatosTienda() throws Exception {
		this.tiendaService.guardar();
		this.cambiosSinGuardar = false;
	}
	
	public void cargarDatosUsuario(String usuario) throws Exception {
		//Primero establezco los datos del usuario que se loga
		empleadoLogin = tiendaService.getEmpleadosService().getEmpleadoByLogin(usuario);
	}
	
	public void modificarPropiedad(Propiedades propiedad, String valor) throws Exception {
		this.tiendaService.getPropieadesService().modificarPropiedad(propiedad, valor);
		this.cambiosSinGuardar = true;
	}
	
	public String getPropiedadConfiguracion(Propiedades propiedad) {
		return this.tiendaService.getPropieadesService().getValor(propiedad);
	}
	
	public List<Empleado> getEmpleadosTienda() {
		return this.tiendaService.getEmpleadosService().listado();
	}
	
	 public void borrarEmpleado(final Empleado empleado, final Empleado empleadoReemplazo) throws Exception {
		 //TODO - ¿Reemplazar empleado?
		 this.tiendaService.getEmpleadosService().borrar(empleado);
		 this.cambiosSinGuardar = true;
	 }
	 
	 public void addCliente(Cliente cliente) throws Exception {
		 this.tiendaService.getClientesService().crear(cliente);
		 this.cambiosSinGuardar = true;
	 }
	 
	 public void modificarCliente(Cliente cliente) throws Exception {
		 this.tiendaService.getClientesService().modificar(cliente);
		 this.cambiosSinGuardar = true;
	 }
	 
	 public void addEmpleado(Empleado empleado) throws Exception {
		 this.tiendaService.getEmpleadosService().crear(empleado);
		 this.cambiosSinGuardar = true;
	 }
	 
	 public void modificarEmpleado(Empleado empleado) throws Exception {
		 this.tiendaService.getEmpleadosService().modificar(empleado);
		 this.cambiosSinGuardar = true;
	 }
	 
	 public List<Cliente> getClientesTienda() {
		 return this.tiendaService.getClientesService().listado();
	 }
	 
	 public void deleteCliente(Cliente cliente) throws Exception {
		 this.tiendaService.getClientesService().borrar(cliente);
		 this.cambiosSinGuardar = true;
	 }
	 
	 public List<Venta> getVentasByCliente(String clienteDNI) {
		 Cliente cliente = new Cliente();
		 cliente.setDni(clienteDNI);
		 return this.tiendaService.getVentasService().getVentasByCliente(cliente);
	 }
	 
	 public Double getImporteVenta(Venta venta) throws Exception {
		 return this.tiendaService.getVentasService().getImporteVenta(venta.getId());
	 }
	 
	 public boolean isVentaFinanciada(Venta venta) {
		 return this.tiendaService.getFinanciacionesService().getFinanciacionByVenta(venta) != null;
	 }
	 
	 public boolean isVentaFinanciacionTerminada(Venta venta) {
		 Financiacion financiacion = this.tiendaService.getFinanciacionesService().getFinanciacionByVenta(venta);
		 if(financiacion != null) {
			 Calendar ventaCal = Calendar.getInstance();
			 ventaCal.setTime(venta.getFecha());
			 ventaCal.add(Calendar.MONTH, financiacion.getPlazos());
			 return ventaCal.getTime().compareTo(Calendar.getInstance().getTime()) < 0;
		 }
		 return true;
	 }
	 
	 public List<Reparacion> getReparacionesByCliente(Cliente cliente) {
		 return this.tiendaService.getReparacionesService().getReparacionesByCliente(cliente);
	 }
}
