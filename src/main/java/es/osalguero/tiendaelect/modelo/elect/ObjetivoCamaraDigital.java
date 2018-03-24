package es.osalguero.tiendaelect.modelo.elect;

import java.util.List;

public class ObjetivoCamaraDigital extends ElectFotografiaDigital {

	private List<String> marcasCompatibles;
	private float focalMin;
	private float focalMax;
	private float aperturaMin;
	private float aperturaMax;
	private float zoom;
	private boolean tieneZoom;
	private boolean tieneEstabilizador;

	public ObjetivoCamaraDigital(List<String> marcasCompatibles, float focalMin, float focalMax,
			float aperturaMin, float aperturaMax, float zoom, boolean tieneZoom) {
		this.marcasCompatibles = marcasCompatibles;
		this.focalMin = focalMin;
		this.aperturaMin = aperturaMin;
		this.tieneZoom = tieneZoom;
		if(tieneZoom) {
			this.focalMax = focalMax;
			this.aperturaMax = aperturaMax;
			this.zoom = zoom;
		} else {
			this.focalMax = focalMin;
			this.aperturaMax = aperturaMin;
		}
	}
	
	public List<String> getMarcasCompatibles() {
		return marcasCompatibles;
	}

	public void setMarcasCompatibles(List<String> marcasCompatibles) {
		this.marcasCompatibles = marcasCompatibles;
	}

	public float getFocalMin() {
		return focalMin;
	}

	public void setFocalMin(float focalMin) {
		this.focalMin = focalMin;
	}

	public float getFocalMax() {
		return focalMax;
	}

	public void setFocalMax(float focalMax) {
		this.focalMax = focalMax;
	}

	public float getAperturaMin() {
		return aperturaMin;
	}

	public void setAperturaMin(float aperturaMin) {
		this.aperturaMin = aperturaMin;
	}

	public float getAperturaMax() {
		return aperturaMax;
	}

	public void setAperturaMax(float aperturaMax) {
		this.aperturaMax = aperturaMax;
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public boolean isTieneZoom() {
		return tieneZoom;
	}

	public void setTieneZoom(boolean tieneZoom) {
		this.tieneZoom = tieneZoom;
	}
	
	public boolean getTieneEstabilizador() {
		return tieneEstabilizador;
	}
	
	public void setTieneEstabilizador(boolean tieneEstabilizador) {
		this.tieneEstabilizador = tieneEstabilizador;
	}
}
