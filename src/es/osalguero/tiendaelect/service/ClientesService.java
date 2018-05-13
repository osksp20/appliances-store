package es.osalguero.tiendaelect.service;

import java.util.Calendar;

import es.osalguero.tiendaelect.modelo.decorator.ClientesTiendaDecorator;
import es.osalguero.tiendaelect.modelo.decorator.GenericListDecorator;
import es.osalguero.tiendaelect.modelo.persona.Cliente;

public class ClientesService extends GenericService<Cliente> {

	public ClientesService() {
		super();
	}

	public ClientesService(String ficheroClientes) {
		super(ficheroClientes);
	}

	@Override
	protected void validaNuevoElemento(Cliente cliente) throws Exception {
		if (cliente.getDni() == null) {
			throw new Exception("El DNI del cliente a añadir no puede ser nulo");
		}
		for (Cliente clienteListado : this.listado) {
			if (clienteListado.getDni().equals(cliente.getDni())) {
				throw new Exception("El DNI del cliente a añadir ya existe");
			}
		}
	}

	@Override
	protected void inicializarNuevoElemento(Cliente cliente) {
		// Nothing special to do
	}

	@Override
	protected void validaElementoABorrar(Cliente cliente) throws Exception {
		// Nothing special to do
	}

	@Override
	protected void validaElementoAModificar(Cliente cliente) throws Exception {
		if (cliente.getDni() == null) {
			throw new Exception("El DNI del cliente a modificar no puede ser nulo");
		}
		if (cliente.getNombre() == null) {
			throw new Exception("El nombre del cliente a modificar no puede ser nulo");
		}
		if (cliente.getApellido1() == null) {
			throw new Exception("El apellido1 del cliente a modificar no puede ser nulo");
		}
		if (cliente.getApellido2() == null) {
			throw new Exception("El apellido2 del cliente a modificar no puede ser nulo");
		}
		if (cliente.getDireccion() == null) {
			throw new Exception("La direcci�n del cliente a modificar no puede ser nula");
		}
		if (cliente.getTelefono() == null) {
			throw new Exception("El tel�fono del cliente a modificar no puede ser nulo");
		}
		if (cliente.getUltimaNomina() != null) {
			if (cliente.getUltimaNomina().getFecha() == null) {
				throw new Exception("La fecha de la última nómina no puede ser nula");
			}
			if (cliente.getUltimaNomina().getCantidad() == null) {
				throw new Exception("No se ha indicado la cantidad de la última nómina");
			}
			Calendar cal = Calendar.getInstance();
			Calendar nominaCal = Calendar.getInstance();
			nominaCal.setTime(cliente.getUltimaNomina().getFecha());
			if (cal.compareTo(nominaCal) < 0) {
				throw new Exception("La fecha de la última nómina no puede ser posterior a la fecha actual");
			}
			boolean comprobarAnterior = true;
			if (this.listado.contains(cliente)) {
				Cliente clienteExistente = this.listado.get(this.listado.indexOf(cliente));
				if (clienteExistente.getUltimaNomina() != null) {
					comprobarAnterior = false;
				}
			}
			if (comprobarAnterior) {
				cal.add(Calendar.MONTH, -1);
				if (cal.compareTo(nominaCal) > 0) {
					throw new Exception(
							"La fecha de la última nómina no puede ser anterior a un mes de la fecha actual");
				}
			}
		}
	}

	@Override
	protected Class<? extends GenericListDecorator<Cliente>> getElementsDecoratorClass() {
		return ClientesTiendaDecorator.class;
	}

	@Override
	protected void preparaElementoAModificar(Cliente elemento) {
		// Nothing special to do
	}

	@Override
	protected void actualizaEstadoTienda(Cliente cliente) {
		// Do nothing
	}

	/************************************
	 * Métodos propios de este servicio *
	 ************************************/
	public Cliente getClienteByDni(String dni) {
		Cliente cliente = new Cliente();
		cliente.setDni(dni);
		if (this.listado.contains(cliente)) {
			try {
				return (Cliente) listado.get(listado.indexOf(cliente)).clone();
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
}
