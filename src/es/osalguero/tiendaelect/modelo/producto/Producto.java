package es.osalguero.tiendaelect.modelo.producto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.producto.acc.Accesorio;
import es.osalguero.tiendaelect.modelo.producto.elect.Electrodomestico;

@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos", name="producto")
@XmlSeeAlso({Accesorio.class, Electrodomestico.class})
@XmlAccessorType(XmlAccessType.PROPERTY)
public abstract class Producto extends ElementoTiendaGenerico implements ProductoInterface {

	private Long id;
	private String marca;
	private String modelo;
	private String tipo;
	private Float precio;
	private String caracteristicas;
	private int cantidad;
	
	@XmlID
	public String getXmlId() {
		return String.valueOf(this.getId());
	}
	
	public void setXmlId(String id) {
		try {
			this.id = Long.parseLong(id);
		} catch(Exception e) {
			//Nothing to do
		}
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Float getPrecio() {
		return precio;
	}
	
	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}
	
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public boolean equals(Object o) {
		return o != null && o instanceof Producto &&
				((Producto)o).getId() != null && this.getId() != null &&
				((Producto)o).getId().equals(this.id);
	}
	
	@Override
	public String toString() {
		return this.getMarca().concat(" ").concat(this.getModelo()).concat("(").concat(this.getTipo()).concat(")");
	}
}
