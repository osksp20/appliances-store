package es.osalguero.tiendaelect.gui.view.edicion;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import es.osalguero.tiendaelect.gui.controller.edicion.VistaAbstractaEdicionGUIController;
import es.osalguero.tiendaelect.gui.exception.FilterFormatException;
import es.osalguero.tiendaelect.gui.util.GUIUtils;
import es.osalguero.tiendaelect.gui.view.VistaAbstractaGUI;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;

public abstract class VistaAbstractaEdicionGUI<T extends VistaAbstractaEdicionGUIController<?, O>, O extends ElementoTiendaGenerico> extends VistaAbstractaGUI<VistaAbstractaEdicionGUIController<?, O>> implements ActionListener
{
	Logger logger = Logger.getLogger(this.getClass().getName());
	
    protected JPanel panelLabelEdicion;
    protected JPanel editFieldsPanel;
    protected JPanel bottomPane;
    protected JLabel labelVistaEdicion;
    protected final String title;
    protected JButton backButton;
    protected JButton applyButton;
    protected JButton modifyButton;
    protected ElementoTiendaGenerico elementoPadre;
    protected O elementoEdicion;
    protected TipoEdicionEnum tipoEdicion;
    
    private VistaAbstractaEdicionGUI(final T controller) {
        super(controller);
        this.title = this.getViewTitle();
    }
    
    public VistaAbstractaEdicionGUI(final T controller, final O elementoEdicion, final TipoEdicionEnum tipoEdicion, final ElementoTiendaGenerico elementoPadre) {
        super(controller);
        this.elementoPadre = elementoPadre;
        this.elementoEdicion = elementoEdicion;
        this.tipoEdicion = tipoEdicion;
        if (this.tipoEdicion == null) {
            this.tipoEdicion = TipoEdicionEnum.DETALLE;
        }
        this.title = this.getViewTitle();
        this.panelLabelEdicion = GUIUtils.createOptionsLabelPanel();
        this.labelVistaEdicion = GUIUtils.createOptionsPanelLabel(this.title);
        this.panelLabelEdicion.add(this.labelVistaEdicion);
        (this.editFieldsPanel = new JPanel()).setPreferredSize(new Dimension(this.getContainer().getWidth() - 10, 521));
        this.editFieldsPanel.setOpaque(true);
        (this.bottomPane = new JPanel()).setPreferredSize(new Dimension(this.getContainer().getWidth(), 40));
        this.bottomPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        this.bottomPane.setOpaque(false);
        this.bottomPane.setLayout(new GridLayout(1, 3));
        (this.backButton = GUIUtils.createBackButton()).addActionListener(this);
        (this.applyButton = GUIUtils.createApplyButton()).addActionListener(this);
        (this.modifyButton = GUIUtils.createModifyButton()).addActionListener(this);
        final SpringLayout layout = new SpringLayout();
        this.bottomPane.add(this.backButton);
        this.bottomPane.add(this.applyButton);
        this.bottomPane.add(this.modifyButton);
        layout.putConstraint("West", this.backButton, 0, "West", this.bottomPane);
        layout.putConstraint("East", this.applyButton, 0, "East", this.bottomPane);
        layout.putConstraint("East", this.modifyButton, 0, "East", this.bottomPane);
        this.bottomPane.setLayout(layout);
        this.backButton.setEnabled(true);
        this.applyButton.setEnabled(false);
        this.fillEditPanel(this.editFieldsPanel);
    }
    
    @Override
    public void generaVista() throws Exception {
        this.getContainer().removeAll();
        switch (this.tipoEdicion) {
            case DETALLE: {
                this.modifyButton.setEnabled(true);
                this.modifyButton.setVisible(true);
                this.applyButton.setVisible(false);
                break;
            }
            case EDITAR: {
                this.modifyButton.setVisible(false);
                this.applyButton.setVisible(true);
                this.applyButton.setEnabled(true);
                break;
            }
            case NUEVO: {
                this.modifyButton.setVisible(false);
                this.applyButton.setEnabled(true);
                break;
            }
        }
        this.getContainer().setLayout(new FlowLayout(0));
        this.getContainer().add(this.panelLabelEdicion);
        this.getContainer().add(this.editFieldsPanel);
        this.getContainer().add(this.bottomPane);
        this.manageTipoEdicion();
        this.getContainer().revalidate();
        this.getContainer().repaint();
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource().equals(this.modifyButton)) {
            this.tipoEdicion = TipoEdicionEnum.EDITAR;
            try {
                this.generaVista();
            }
            catch (Exception ex) {
            	logger.log(Level.SEVERE, "Error intentando modificar el tipo de edición de la pantalla", ex);
                this.getController().showError("Se ha producido un error inesperado, por favor consulte los logs de la aplicación.");
            }
        }
        else if (e.getSource().equals(this.backButton)) {
            if (TipoEdicionEnum.DETALLE.equals(this.tipoEdicion) || JOptionPane.showConfirmDialog(this.getContainer(), "¿Está seguro de cancelar la edición?. No se guardarán los cambios.", "Confirmar acción.", 2) == 0) {
                this.getController().goToVista(this.getVistaAnterior(), this.getElementoAnterior());
            }
        }
        else if (e.getSource().equals(this.applyButton)) {
            try {
                this.validate();
                boolean okProceso = false;
                if (TipoEdicionEnum.EDITAR.equals(this.tipoEdicion)) {
                    try {
                        this.modificarElemento();
                        okProceso = true;
                    }
//                    catch (AppException appEx) {
//                        this.getController().showError(appEx.getMessage());
//                    }
                    catch (Exception ex) {
                    	logger.log(Level.SEVERE, "Error guardando los cambios en el elemento modificado.", ex);
                        this.getController().showError("Se ha producido un error realizando la modificaci\u00f3n.\nPor favor revise los logs de la aplicaci\u00f3n.");
                    }
                }
                else if (TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
                    try {
                        final O elemento = this.crearNuevoElemento();
                        this.getController().nuevoElemento(elemento);
                        this.elementoEdicion = elemento;
                        okProceso = true;
                    }
//                    catch (AppException appEx) {
//                        this.getController().showError(appEx.getMessage());
//                    }
                    catch (Exception ex) {
                    	logger.log(Level.SEVERE, "Error guardando el nuevo elemento.", ex);
                        this.getController().showError("Se ha producido un error guardando el nuevo elemento.\nPor favor revise los logs de la aplicaci\u00f3n.");
                    }
                }
                if (okProceso) {
                    JOptionPane.showMessageDialog(this.getContainer(), "Los cambios se han aplicado correctamente.\nPor favor, recuerde guardar los cambios a trav\u00e9s del men\u00fa 'Archivo' -> 'Guardar cambios'\npara que estos sean permanentes.", "Operaci\u00f3n realizada", -1);
                    this.tipoEdicion = TipoEdicionEnum.DETALLE;
                }
                try {
                    this.generaVista();
                }
                catch (Exception ex) {
                	logger.log(Level.SEVERE, "Error intentando modificar el tipo de ediciónn de la pantalla", ex);
                    this.getController().showError("Se ha producido un error inesperado, por favor consulte los logs de la aplicaci\u00f3n.");
                }
            }
            catch (FilterFormatException ffex) {
                this.getController().showError(ffex.getMessage());
            }
            catch (Exception ex) {
            	logger.log(Level.SEVERE, "Error intentando modificar el elemento", ex);
                this.getController().showError("Se ha producido un error modificando el elemento que se estaba editando.\nPor favor, revise el log de la aplicaci\u00f3n.");
            }
        }
    }
    
    protected abstract String getViewTitle();
    
    protected abstract void fillEditPanel(final JPanel p0);
    
    protected abstract void validate() throws FilterFormatException, Exception;
    
    protected abstract void modificarElemento() throws Exception;
    
    protected abstract void manageTipoEdicion();
    
    protected abstract O crearNuevoElemento() throws Exception;
    
    protected abstract VistasEnum getVistaAnterior();
    
    protected abstract ElementoTiendaGenerico getElementoAnterior();
}
