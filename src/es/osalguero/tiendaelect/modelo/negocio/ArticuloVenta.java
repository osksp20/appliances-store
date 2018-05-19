package es.osalguero.tiendaelect.modelo.negocio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import es.osalguero.tiendaelect.modelo.producto.Producto;

@XmlRootElement(name="articulo", namespace="http://es.osalguero.elect/TiendaElectrodomesticos")
public class ArticuloVenta implements Cloneable {

	
	private Producto producto;
	private Integer cantidad;
	private Float precio;
	
	@XmlID
	public String getXmlId() {
		return String.valueOf(producto.getId());
	}
	
	@XmlElement
	public Producto getProducto() {
		return this.producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Integer getCantidad() {
		return this.cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Float getPrecio() {
		return precio;
	}
	
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
	@Override
	public ArticuloVenta clone() throws CloneNotSupportedException {
		ArticuloVenta articuloVenta = (ArticuloVenta)super.clone();
		if(producto != null) {
			articuloVenta.setProducto((Producto)this.producto.clone());
		}
		return articuloVenta;
	}
	
}
