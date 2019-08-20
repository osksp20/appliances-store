package es.osalguero.tiendaelect.gui.busqueda;

import es.osalguero.tiendaelect.constants.CategoriaEmpleado;

public class BusquedaEmpleado extends BusquedaPersona
{
    private static final long serialVersionUID = -4390934957380154106L;
    private String login;
    private CategoriaEmpleado tipo;
    private Boolean administrador;
    
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(final String login) {
        this.login = login;
    }
    
    public CategoriaEmpleado getTipo() {
        return this.tipo;
    }
    
    public void setTipo(final CategoriaEmpleado tipo) {
        this.tipo = tipo;
    }
    
    public Boolean getAdministrador() {
        return this.administrador;
    }
    
    public void setAdministrador(final Boolean administrador) {
        this.administrador = administrador;
    }
}
