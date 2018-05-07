package es.osalguero.tiendaelect.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.decorator.GenericListDecorator;

public abstract class GenericService<T extends ElementoTiendaGenerico> {

	protected List<T> listado;
	private String ficheroElementos;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public GenericService() {
		listado = inicializarListado();
		if(listado == null) {
			listado = new ArrayList<T>();
		}
	}
	
	public GenericService(String ficheroElementos) {
		this.ficheroElementos = ficheroElementos;
		listado = inicializarListado();
		if(listado == null) {
			listado = new ArrayList<T>();
		}
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> inicializarListado() {
		List<T> elementos = new ArrayList<T>();
		try {
			JAXBContext jbContext = JAXBContext.newInstance(getElementsDecoratorClass());
			Unmarshaller unmarshaller = jbContext.createUnmarshaller();
			GenericListDecorator<T> decorator =
					(GenericListDecorator<T>)unmarshaller.unmarshal(
							new File(this.getFicheroElementos()));
			this.listado = decorator.getElementos();
		} catch(Exception e) {
			logger.warning("No se ha podido inicializar la lista de elementos");
		}
		return elementos;
	}
	
	protected String getFicheroElementos() {
		return ficheroElementos;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listado() {
		List<T> elementos = new ArrayList<T>();
		for(T elemento : this.listado) {
			try {
				elementos.add((T)elemento.clone());
			} catch(Exception e) {
				logger.log(Level.WARNING, "", e);
			}
		}
		return elementos;
	}
	
	@SuppressWarnings("unchecked")
	public T crear(T elemento) throws Exception {
		if(elemento == null) {
			throw new Exception("El elemento a añadir es nulo");
		}
		validaNuevoElemento(elemento);
		//Primero hago clone para que el elemento y los asociados guardados en el servicio
		//se desacoplen de los que vienen desde la llamada
		T nuevoElemento = (T)elemento.clone();
		//Valido y preparo el elemento que voy a guardar
		inicializarNuevoElemento(nuevoElemento);
		validaElementoAModificar(nuevoElemento);
		preparaElementoAModificar(nuevoElemento);
		try {
			actualizaEstadoTienda(nuevoElemento);
		} catch(Exception e) {
			listado.remove(nuevoElemento);
			throw e;
		}
		//Al preparar se tienen que haber insertado elementos desacoplados de las listas de los servicios
		//así cuando se modifique el elemento que se devuelve, no se modificará el almacenado en el servicio
		listado.add(nuevoElemento);
		//Hago clone en el return para que el objeto que vuelve tenga todos los datos recuperados
		//pero desacoplados de los que existen en el servicio
		//Esto es lo que se consigue con una capa Modelo - DAO o cargando el método de creación, pero asi ahorro tiempo
		return (T)nuevoElemento.clone();
	}
	
	public void borrar(T elemento) throws Exception {
		if(elemento == null) {
			throw new Exception("El elemento a eliminar es nulo");
		}
		if(!listado.contains(elemento)) {
			throw new Exception("El elemento indicado no existe");
		}
		T elementoABorrar = this.listado.get(this.listado.indexOf(elemento));
		validaElementoABorrar(elementoABorrar);
		listado.remove(elementoABorrar);
	}
	
	@SuppressWarnings("unchecked")
	public void modificar(T elemento) throws Exception {
		if(elemento == null) {
			throw new Exception("El elemento indicado para modificar es nulo");
		}
		if(!this.listado.contains(elemento)) {
			throw new Exception("El elemento indicado no existe");
		}
		validaElementoAModificar(elemento);
		preparaElementoAModificar(elemento);
		try {
			actualizaEstadoTienda(elemento);
		} catch(Exception e) {
			listado.remove(elemento);
			throw e;
		}
		this.listado.remove(this.listado.indexOf(elemento));
		T nuevoElemento = (T)elemento.clone();
		this.listado.add(nuevoElemento);
	}

	public void guardar() throws Exception {
		try {
			File ficheroElementos = new File(this.ficheroElementos);
			File ficheroElementosParent = ficheroElementos.getParentFile();
			ficheroElementosParent.mkdirs();
			ficheroElementos.createNewFile();
			JAXBContext jbContext = JAXBContext.newInstance(getElementsDecoratorClass());
			GenericListDecorator<T> decorator = getElementsDecoratorClass().newInstance();
			decorator.setElementos(this.listado);
			Marshaller em = jbContext.createMarshaller();
			em.marshal(decorator, new File(this.getFicheroElementos()));
		} catch(Exception e) {
			logger.log(Level.SEVERE, "Se ha producido un error intentando guardar los elementos", e);
		}
	}
	
	protected abstract Class<? extends GenericListDecorator<T>> getElementsDecoratorClass();
	
	/**
	 * Valida los valores con los que se quiere crear el nuevo elemento.
	 * 
	 * @param elemento
	 * @throws Exception
	 */
	protected abstract void validaNuevoElemento(T elemento) throws Exception;
	
	/**
	 * Establece los valores necesarios para un nuevo elemento (p.e. el ID o la fecha
	 * de creación)
	 * 
	 * @param elemento
	 */
	protected abstract void inicializarNuevoElemento(T elemento);
	
	/**
	 * Comprobaciones adicionales, de negocio, a realizar antes de borrar el elemento
	 * 
	 * @param elemento
	 * @throws Exception
	 */
	protected abstract void validaElementoABorrar(T elemento) throws Exception;
	
	/**
	 * Realiza las validaciones necesarias para modificar el elemento
	 * 
	 * @param elemento
	 * @throws Exception
	 */
	protected abstract void validaElementoAModificar(T elemento) throws Exception;
	
	/**
	 * Realiza operaciones necesarias para establecer correctamente el elemento
	 * 
	 * @param elemento
	 */
	protected abstract void preparaElementoAModificar(T elemento);
	
	protected abstract void actualizaEstadoTienda(T elemento) throws Exception;
}
