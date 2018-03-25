package es.osalguero.tiendaelect.modelo.producto;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;

import es.osalguero.tiendaelect.modelo.producto.acc.Accesorio;
import es.osalguero.tiendaelect.modelo.producto.elect.Electrodomestico;

@XmlSeeAlso({Accesorio.class, Electrodomestico.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Producto implements ProductoInterface {

	@XmlAttribute
	private String marca;
	@XmlAttribute
	private String modelo;
	@XmlAttribute
	private String tipo;
	@XmlAttribute
	private float precio;
	private Map<String, String> caracteristicas;
	
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

	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Map<String, String> getCaracteristicas() {
		return caracteristicas;
	}
	
	public void setCaracteristicas(Map<String, String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
}
