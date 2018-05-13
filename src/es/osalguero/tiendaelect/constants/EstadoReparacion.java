package es.osalguero.tiendaelect.constants;

public enum EstadoReparacion {

	PENDIENTE("Pendiente"),
	EN_PROCESO("En proceso"),
	PARADO_PIEZAS("Esperando piezas"),
	PTE_CONFIRMAR_CLIENTE("Pte. confirmar cliente"),
	PRUEBA("Validando"),
	TERMINADO("Terminado");
	
	private String nombre;
    
    private EstadoReparacion(final String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return this.nombre;
    }
	
}
