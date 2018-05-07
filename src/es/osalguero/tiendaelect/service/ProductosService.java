package es.osalguero.tiendaelect.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.osalguero.tiendaelect.constants.SeccionTienda;
import es.osalguero.tiendaelect.modelo.decorator.GenericListDecorator;
import es.osalguero.tiendaelect.modelo.decorator.ProductosTiendaDecorator;
import es.osalguero.tiendaelect.modelo.producto.Producto;

public class ProductosService extends GenericService<Producto> {
	
	public ProductosService() {
		super();
	}
	
	public ProductosService(String ficheroProductos) {
		super(ficheroProductos);
	}

	@Override
	protected void validaNuevoElemento(Producto producto) throws Exception {
		if(producto.getId() != null) {
			throw new Exception("El ID del producto a añadir debe ser nulo");
		}
	}

	@Override
	protected void inicializarNuevoElemento(Producto producto) {
		producto.setId(Calendar.getInstance().getTimeInMillis());
		try {
			Thread.sleep(1);
		} catch(Exception e) {
			//Nothing to do
		}
	}

	@Override
	protected void validaElementoABorrar(Producto producto) throws Exception {
		//Nothing special to do
	}

	@Override
	protected void validaElementoAModificar(Producto producto) throws Exception {
		if(producto.getId() == null) {
			throw new Exception("El ID del producto indicado no puede ser nulo");
		}
		if(producto.getMarca() == null) {
			throw new Exception("La marca del producto no puede ser nula");
		}
		if(producto.getModelo() == null) {
			throw new Exception("El modelo del producto no puede ser nulo");
		}
		if(producto.getPrecio() == null) {
			throw new Exception("El precio del producto no puede ser nulo");
		}
		if(producto.getTipo() == null) {
			throw new Exception("El tipo del producto no puede ser nulo");
		}
	}

	@Override
	protected Class<? extends GenericListDecorator<Producto>> getElementsDecoratorClass() {
		return ProductosTiendaDecorator.class;
	}

	@Override
	protected void preparaElementoAModificar(Producto elemento) {
		//Nothing special to do
	}
	
	@Override
	protected void actualizaEstadoTienda(Producto elemento) throws Exception {
		//Nothing to do
		
	}
	
	/************************************
	 * Métodos propios de este servicio *
	 ************************************/
	public Producto getProductoById(Long id) {
		for(Producto producto : listado) {
			if(producto.getId().equals(id)) {
				try { 
					return (Producto)producto.clone();
				} catch(Exception e) {
					return null;
				}
			}
		}
		return null;
	}
	
	public List<Producto> getProductosBySeccion(SeccionTienda seccion) {
		List<Producto> productos = new ArrayList<Producto>();
		if(seccion == null) {
			return productos;
		}
		for(Producto producto :listado) {
			if(producto.getSeccionTienda().equals(seccion)) {
				try {
					productos.add((Producto)producto.clone());
				} catch(Exception e) {
					//Nothing to do
				}
			}
		}
		return productos;
	}
	
	public Map<SeccionTienda, Integer> getInventarioBySeccion() {
		Map<SeccionTienda, Integer> inventario = new HashMap<>();
		for(Producto producto : listado) {
			if(inventario.get(producto.getSeccionTienda()) == null) {
				inventario.put(producto.getSeccionTienda(), 0);
			}
			inventario.put(producto.getSeccionTienda(), inventario.get(producto.getSeccionTienda())+1);
		}
		return inventario;
	}
}
