package es.osalguero.tiendaelect.modelo.elect;

/**
 * Modela electrodomesticos de tipo Gama Blanca:
 * Frigorificos, Lavadoras, Secadoras, Lavavajillas...
 * 
 * @author o.salguero.palacios
 *
 */
public class ElectGamaBlanca extends Electrodomestico {

	private int alto;
	private int ancho;
	private int profundidad;
	
	public int getAlto() {
		return alto;
	}
	
	public void setAlto(int alto) {
		this.alto = alto;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	
	public int getProfundidad() {
		return profundidad;
	}
	
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
	
}
