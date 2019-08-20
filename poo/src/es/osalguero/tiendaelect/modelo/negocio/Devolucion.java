package es.osalguero.tiendaelect.modelo.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;

@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos", name="devolucion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Devolucion extends ElementoTiendaGenerico {

	private Long id;
	private Date fecha;
	@XmlElementWrapper(name="articulos")
	@XmlElementRef
	private List<ArticuloVenta> articulosDevolucion;
	@XmlIDREF
	private Venta venta;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public List<ArticuloVenta> getArticulosDevolucion() {
		return articulosDevolucion;
	}
	
	public void setArticulosDevolucion(List<ArticuloVenta> articulosDevolucion) {
		this.articulosDevolucion = articulosDevolucion;
	}
	
	public Venta getVenta() {
		return venta;
	}
	
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	@Override
	public boolean equals(Object o) {
		return o != null && o instanceof Devolucion && ((Devolucion)o).getId() != null &&
				this.id != null && this.id.equals(((Devolucion)o).getId());
	}
	
	@Override
	public Devolucion clone() throws CloneNotSupportedException {
		Devolucion devolucion = (Devolucion)super.clone();
		if(articulosDevolucion != null) {
			List<ArticuloVenta> articulos = new ArrayList<ArticuloVenta>();
			for(ArticuloVenta articuloDevolucion : articulosDevolucion) {
				articulos.add((ArticuloVenta)articuloDevolucion.clone());
			}
			devolucion.setArticulosDevolucion(articulos);
		}
		if(venta != null) {
			devolucion.setVenta((Venta)venta.clone());
		}
		return devolucion;
	}
}
