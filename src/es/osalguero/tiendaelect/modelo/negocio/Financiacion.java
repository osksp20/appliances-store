package es.osalguero.tiendaelect.modelo.negocio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;

@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos", name="financiacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Financiacion extends ElementoTiendaGenerico {

	private Long id;
	@XmlIDREF
	private Venta venta;
	private Integer plazos;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Venta getVenta() {
		return venta;
	}
	
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	public Integer getPlazos() {
		return plazos;
	}
	
	public void setPlazos(Integer plazos) {
		this.plazos = plazos;
	}
	
	@Override
	public boolean equals(Object o) {
		return o != null && o instanceof Financiacion && ((Financiacion)o).getId() != null &&
				this.id != null && this.id.equals(((Financiacion)o).getId());
	}
	
	@Override
	public Financiacion clone() throws CloneNotSupportedException {
		Financiacion financiacion = (Financiacion)super.clone();
		if(venta != null) {
			financiacion.setVenta((Venta)venta.clone());
		}
		return financiacion;
	}
}
