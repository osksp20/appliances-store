package es.osalguero.tiendaelect.gui.view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import es.osalguero.tiendaelect.gui.controller.AbstractGUIController;
import es.osalguero.tiendaelect.gui.util.GUIUtils;

public abstract class VistaAbstractaGUI<T extends AbstractGUIController<?>>
{
    private T controller;
    
    @SuppressWarnings("unused")
	private VistaAbstractaGUI() {
    }
    
    public VistaAbstractaGUI(final T controller) {
        this.controller = controller;
    }
    
    public abstract void generaVista() throws Exception;
    
    public void setDefaultFocus() {
    }
    
    protected T getController() {
        return this.controller;
    }
    
    protected JPanel getContainer() {
        return this.controller.getTallerFrameContainer();
    }
    
    protected JPanel createOptionsPanel() {
        return GUIUtils.createOptionsPanel(new Double(this.getContainer().getSize().getWidth()).intValue(), 200);
    }
    
    protected JSeparator createSeparator(final int width) {
        final JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(width, -1));
        separator.setOpaque(false);
        return separator;
    }
}
