package es.osalguero.tiendaelect.modelo.negocio;

import java.util.Date;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import es.osalguero.tiendaelect.constants.EstadoReparacion;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.persona.Empleado;
import es.osalguero.tiendaelect.modelo.producto.Producto;

@XmlRootElement(namespace="http://es.osalguero.elect/TiendaElectrodomesticos", name="reparacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reparacion extends ElementoTiendaGenerico {

	private Long id;
	@XmlIDREF
	private Venta venta;
	@XmlIDREF
	private Producto producto;
	private Date fechaReparacion;
	private Date fechaEntrega;
	private String descripcion;
	private Double importe;
	private Empleado empleadoReparacion;
	private EstadoReparacion estado;
	private Map<Date, String> comentarios;
	private String piezas;
	
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
	
	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Date getFechaReparacion() {
		return fechaReparacion;
	}
	
	public void setFechaReparacion(Date fechaReparacion) {
		this.fechaReparacion = fechaReparacion;
	}
	
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Double getImporte() {
		return importe;
	}
	
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	public Empleado getEmpleadoReparacion() {
		return empleadoReparacion;
	}
	
	public void setEmpleado(Empleado empleadoReparacion) {
		this.empleadoReparacion = empleadoReparacion;
	}
	
	public EstadoReparacion getEstado() {
		return this.estado;
	}
	
	public void setEstado(EstadoReparacion estado) {
		this.estado = estado;
	}
	
	public Map<Date, String> getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(Map<Date, String> comentarios) {
		this.comentarios = comentarios;
	}
	
	public String getPiezas() {
		return piezas;
	}
	
	public void setPiezas(String piezas) {
		this.piezas = piezas;
	}
	
	@Override
	public boolean equals(Object o) {
		return o != null && o instanceof Reparacion && ((Reparacion)o).getId() != null &&
				this.id != null && this.id.equals(((Reparacion)o).getId());
	}
	
	@Override
	public Reparacion clone() throws CloneNotSupportedException {
		Reparacion reparacion = (Reparacion)super.clone();
		if(venta != null) {
			reparacion.setVenta((Venta)venta.clone());
		}
		if(producto != null) {
			reparacion.setProducto((Producto)producto.clone());
		}
		if(empleadoReparacion != null) {
			reparacion.setEmpleado((Empleado)empleadoReparacion.clone());
		}
		return reparacion;
	}
}
