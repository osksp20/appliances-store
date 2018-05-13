package es.osalguero.tiendaelect.gui.view.busqueda;

public class BusquedaPersona implements ModeloBusqueda
{
    private static final long serialVersionUID = 2853776371256180501L;
    private String nombre;
    private String apellido1;
    private String apellido2;
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(final String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        }
    }
    
    public String getApellido1() {
        return this.apellido1;
    }
    
    public void setApellido1(final String apellido1) {
        if (apellido1 != null && !apellido1.isEmpty()) {
            this.apellido1 = apellido1;
        }
    }
    
    public String getApellido2() {
        return this.apellido2;
    }
    
    public void setApellido2(final String apellido2) {
        if (apellido2 != null && !apellido2.isEmpty()) {
            this.apellido2 = apellido2;
        }
    }
}
