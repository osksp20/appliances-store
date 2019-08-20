package es.osalguero.tiendaelect.modelo;

public enum Propiedades {

	FICHERO_TIENDA("ficheroTienda"),
	FICHERO_PRODUCTOS("ficheroProductos"),
	FICHERO_EMPLEADOS("ficheroEmpleados"),
	FICHERO_CLIENTES("ficheroClientes"),
	FICHERO_VENTAS("ficheroVentas"),
	FICHERO_DEVOLUCIONES("ficheroDevoluciones"),
	FICHERO_REPARACIONES("ficheroReparaciones"),
	FICHERO_FINANCIACIONES("ficheroFinanciaciones");
	
	private final String nombrePropiedad;
    
    private Propiedades(final String nombrePropiedad) {
        this.nombrePropiedad = nombrePropiedad;
    }
    
    public String getNombrePropiedad() {
        return this.nombrePropiedad;
    }
	
	public static Propiedades getByNombrePropiedad(final String nombrePropiedad) {
        for (Propiedades propiedad : Propiedades.values()) {
            if (propiedad.getNombrePropiedad().equals(nombrePropiedad)) {
                return propiedad;
            }
        }
        return null;
    }
}
