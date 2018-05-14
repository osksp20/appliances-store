package es.osalguero.tiendaelect.gui.busqueda;

import es.osalguero.tiendaelect.constants.SeccionTienda;

public class BusquedaProducto implements ModeloBusqueda
{
    private static final long serialVersionUID = 1040524803435665049L;
    private String marca;
    private String modelo;
    private String tipo;
    private Float precioDesde;
    private Float precioHasta;
    private SeccionTienda seccion;
    private Integer altoDesde;
    private Integer altoHasta;
    private Integer anchoDesde;
    private Integer anchoHasta;
    private Integer profundidadDesde;
    private Integer profundidadHasta;
    private String color;

    public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		if (marca != null && !marca.isEmpty()) {
            this.marca = marca;
        }
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		if (modelo != null && !modelo.isEmpty()) {
            this.modelo = modelo;
        }
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		if (tipo != null && !tipo.isEmpty()) {
            this.tipo = tipo;
        }
	}
	public Float getPrecioDesde() {
		return precioDesde;
	}
	public void setPrecioDesde(Float precioDesde) {
		this.precioDesde = precioDesde;
	}
	public Float getPrecioHasta() {
		return precioHasta;
	}
	public void setPrecioHasta(Float precioHasta) {
		this.precioHasta = precioHasta;
	}
	public SeccionTienda getSeccion() {
		return seccion;
	}
	public void setSeccion(SeccionTienda seccion) {
		this.seccion = seccion;
	}
	public Integer getAltoDesde() {
		return altoDesde;
	}
	public void setAltoDesde(Integer altoDesde) {
		this.altoDesde = altoDesde;
	}
	public Integer getAltoHasta() {
		return altoHasta;
	}
	public void setAltoHasta(Integer altoHasta) {
		this.altoHasta = altoHasta;
	}
	public Integer getAnchoDesde() {
		return anchoDesde;
	}
	public void setAnchoDesde(Integer anchoDesde) {
		this.anchoDesde = anchoDesde;
	}
	public Integer getAnchoHasta() {
		return anchoHasta;
	}
	public void setAnchoHasta(Integer anchoHasta) {
		this.anchoHasta = anchoHasta;
	}
	public Integer getProfundidadDesde() {
		return profundidadDesde;
	}
	public void setProfundidadDesde(Integer profundidadDesde) {
		this.profundidadDesde = profundidadDesde;
	}
	public Integer getProfundidadHasta() {
		return profundidadHasta;
	}
	public void setProfundidadHasta(int profundidadHasta) {
		this.profundidadHasta = profundidadHasta;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		if (color != null && !color.isEmpty()) {
            this.color = color;
        }
	}
}
