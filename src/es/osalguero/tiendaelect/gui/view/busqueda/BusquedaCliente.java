package es.osalguero.tiendaelect.gui.view.busqueda;

import java.util.Date;

public class BusquedaCliente extends BusquedaPersona
{
    private static final long serialVersionUID = 6806915687295024567L;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;
    private Date fechaNacDesde;
    private Date fechaNacHasta;
    
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(final String dni) {
        if (dni != null && !dni.isEmpty()) {
            this.dni = dni;
        }
    }
    
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(final String telefono) {
        if (telefono != null && !telefono.isEmpty()) {
            this.telefono = telefono;
        }
    }
    
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(final String direccion) {
        if (direccion != null && !direccion.isEmpty()) {
            this.direccion = direccion;
        }
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
    }
    
    public Date getFechaNacDesde() {
        return this.fechaNacDesde;
    }
    
    public void setFechaNacDesde(final Date fechaNacDesde) {
        this.fechaNacDesde = fechaNacDesde;
    }
    
    public Date getFechaNacHasta() {
        return this.fechaNacHasta;
    }
    
    public void setFechaNacHasta(final Date fechaNacHasta) {
        this.fechaNacHasta = fechaNacHasta;
    }
}
