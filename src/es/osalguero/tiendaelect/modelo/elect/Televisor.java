package es.osalguero.tiendaelect.modelo.elect;

import es.osalguero.tiendaelect.constants.TipoPantalla;

public class Televisor extends ElectImagen {

	private int pulgadas;
	private TipoPantalla tipoPantalla;
	
	public int getPulgadas() {
		return pulgadas;
	}
	
	public void setPulgadas(int pulgadas) {
		this.pulgadas = pulgadas;
	}
	
	public TipoPantalla getTipoPantalla() {
		return tipoPantalla;
	}
	
	public void setTipoPantalla(TipoPantalla tipoPantalla) {
		this.tipoPantalla = tipoPantalla;
	}
	
}
