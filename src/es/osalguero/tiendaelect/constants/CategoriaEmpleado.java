package es.osalguero.tiendaelect.constants;

public enum CategoriaEmpleado {

	CAJERO("Cajero/a"),
	FINANCIACION("Finaciación"),
	REPARACION("Reparación"),
	POSTVENTA("Postventa"),
	COMERCIAL("Comercial");
	
	private String nombre;
    
    private CategoriaEmpleado(final String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return this.nombre;
    }
}
