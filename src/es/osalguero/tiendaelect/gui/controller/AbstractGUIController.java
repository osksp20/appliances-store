package es.osalguero.tiendaelect.gui.controller;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.osalguero.tiendaelect.gui.view.VistaAbstractaGUI;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;

public abstract class AbstractGUIController<T extends VistaAbstractaGUI<?>>
{
    protected T vista;
    
    protected AbstractGUIController() {
        try {
            this.setVista();
        }
        catch (Exception e) {
           TiendaElectrodomesticosGUIController.getInstance().gestionarErrorAplicacion(e);
        }
    }
    
    protected abstract void setVista() throws Exception;
    
    public JPanel getTallerFrameContainer() {
        return TiendaElectrodomesticosGUIController.getInstance().getTiendaFrameContainer();
    }
    
    public void generarVista() throws Exception {
        this.vista.generaVista();
        this.vista.setDefaultFocus();
    }
    
    public void goToVistaPrincipal() {
        TiendaElectrodomesticosGUIController.getInstance().gestionarCambioVista(VistasEnum.VISTA_PRINCIPAL, null);
    }
    
    public void goBack() {
        this.goToVistaPrincipal();
    }
    
    public void goToVista(final VistasEnum vista, final ElementoTiendaGenerico elemento) {
        TiendaElectrodomesticosGUIController.getInstance().gestionarCambioVista(vista, elemento);
    }
    
    public void showError(final String errorMessage) {
        JOptionPane.showMessageDialog(this.getTallerFrameContainer(), errorMessage, "Error", 0);
    }
    
    public void showWarning(final String warningMessage) {
        JOptionPane.showMessageDialog(this.getTallerFrameContainer(), warningMessage, "Advertencia", 2);
    }
    
    public void showNotification(final String notificationMessage) {
        JOptionPane.showMessageDialog(this.getTallerFrameContainer(), notificationMessage, "Mensaje", -1);
    }
    
    public void showSucces(final String successMessage) {
        JOptionPane.showMessageDialog(this.getTallerFrameContainer(), successMessage, "Operación realizada", -1);
    }
    
    public int showConfirm(final String confirmMessage) {
        return JOptionPane.showConfirmDialog(this.getTallerFrameContainer(), confirmMessage, "Confirmar operación", 0);
    }
}
