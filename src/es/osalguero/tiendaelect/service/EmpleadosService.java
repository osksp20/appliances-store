package es.osalguero.tiendaelect.service;

import es.osalguero.tiendaelect.constants.CategoriaEmpleado;
import es.osalguero.tiendaelect.modelo.decorator.EmpleadosTiendaDecorator;
import es.osalguero.tiendaelect.modelo.decorator.GenericListDecorator;
import es.osalguero.tiendaelect.modelo.persona.Empleado;

public class EmpleadosService extends GenericService<Empleado> {
	
	public EmpleadosService() throws Exception {
		super();
		if(this.listado().isEmpty() || !existeAdministrador()) {
			this.crearUsuarioAdministrador();
		}
	}
	
	public EmpleadosService(String ficheroElementos) throws Exception {
		super(ficheroElementos);
		if(this.listado().isEmpty() || !existeAdministrador()) {
			this.crearUsuarioAdministrador();
		}
	}
	
	@Override
	protected void validaNuevoElemento(Empleado empleado) throws Exception {
		if(empleado.getLogin() == null) {
			throw new Exception("El Login del empleado a añadir no puede ser nulo");
		}
		for(Empleado empleadoListado : this.listado()) {
			if(empleadoListado.getLogin().equals(empleado.getLogin())) {
				throw new Exception("Ya existe un empleado con el login indicado");
			}
		}
	}

	@Override
	protected void inicializarNuevoElemento(Empleado empleado) {
		//Nothing special to do
	}

	@Override
	protected void validaElementoABorrar(Empleado empleado) throws Exception {
		if(empleado.esAdministrador() && !masAdministradores(empleado)) {
			throw new Exception("No puede eliminarse el empleado porque es el único administrador del sistema");
		}
	}

	@Override
	protected void validaElementoAModificar(Empleado empleado) throws Exception {
		if(empleado.getLogin() == null) {
			throw new Exception("Debe indicarse un ID de empleado válido");
		}
		if(!empleado.esAdministrador() && !masAdministradores(empleado)) {
			throw new Exception("El usuario es el único administrador del sistema");
		}
		if(empleado.getNombre() == null) {
			throw new Exception("El nombre del empleado no puede ser nulo");
		}
		if(empleado.getApellido1() == null) {
			throw new Exception("El apellido1 del empleado no puede ser nulo");
		}
		if(empleado.getApellido2() == null) {
			throw new Exception("El apellido2 del empleado no puede ser nulo");
		}
		if(empleado.getCategoria() == null) {
			throw new Exception("La categoria del empleado no puede ser nulo");
		}
	}

	@Override
	protected Class<? extends GenericListDecorator<Empleado>> getElementsDecoratorClass() {
		return EmpleadosTiendaDecorator.class;
	}

	@Override
	protected void preparaElementoAModificar(Empleado elemento) {
		//Nothing special to do
	}

	@Override
	protected void actualizaEstadoTienda(Empleado elemento) throws Exception {
		//Nothing to do
	}
	
	/**
	 * Comprueba que exista al menos otro empleado administrador en el sistema
	 * 
	 * @param empleado
	 * @return
	 */
	private boolean masAdministradores(Empleado empleado) {
		for(Empleado empleadoListado : this.listado()) {
			if(!empleadoListado.getLogin().equals(empleado.getLogin()) &&
					empleadoListado.esAdministrador()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean existeAdministrador() {
		for(Empleado empleado : this.listado()) {
			if(empleado.esAdministrador()) {
				return true;
			}
		}
		return false;
	}
	
	private void crearUsuarioAdministrador() throws Exception {
		Empleado empleado = new Empleado();
		empleado.setLogin("admin");
		empleado.setNombre("Administrador");
		empleado.setApellido1("Administrador");
		empleado.setApellido2("Administrador");
		empleado.setEsAdministrador(true);
		empleado.setCategoria(CategoriaEmpleado.COMERCIAL);
		try {
			this.crear(empleado);
		} catch(Exception e) {
			throw new Exception("No se ha podido inicializar correctamente la aplicación", e);
		}
	}
	
	public Empleado getEmpleadoByLogin(String login) {
		for(Empleado empleadoExistente : listado) {
			if(empleadoExistente.getLogin().equals(login)) {
				try {
					return (Empleado)empleadoExistente.clone();
				} catch(Exception e) {
					break;
				}
			}
		}
		return null;
	}
	
}
