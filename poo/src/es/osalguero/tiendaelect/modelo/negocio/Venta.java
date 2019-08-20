package es.osalguero.tiendaelect.modelo.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.persona.Cliente;

@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos", name="venta")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Venta extends ElementoTiendaGenerico {

	private Long id;
	private Cliente cliente;
	private List<ArticuloVenta> articulosVenta;
	private Date fecha;
	
	@XmlID
	public String getXmlId() {
		return String.valueOf(this.id);
	}
	
	public void setXmlId(String id) {
		try {
			this.id = Long.valueOf(id);
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
	
	@XmlElement
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@XmlElementWrapper(name="articulos")
	@XmlElementRef
	public List<ArticuloVenta> getArticulosVenta() {
		return articulosVenta;
	}
	
	public void setArticulosVenta(List<ArticuloVenta> articulosVenta) {
		this.articulosVenta = articulosVenta;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public boolean equals(Object o) {
		return o != null && o instanceof Venta && ((Venta)o).getId() != null &&
				this.id != null && this.id.equals(((Venta)o).getId());
	}
	
	@Override
	public Venta clone() throws CloneNotSupportedException {
		Venta venta = (Venta)super.clone();
		if(articulosVenta != null) {
			List<ArticuloVenta> articulos = new ArrayList<ArticuloVenta>();
			for(ArticuloVenta articuloVenta : articulosVenta) {
				articulos.add((ArticuloVenta)articuloVenta.clone());
			}
			venta.setArticulosVenta(articulos);
		}
		if(cliente != null) {
			venta.setCliente((Cliente)cliente.clone());
		}
		return venta;
	}
}
