package es.osalguero.tiendaelect.gui.view.gestionApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.osalguero.tiendaelect.gui.controller.gestionApp.VistaExceptionGUIController;
import es.osalguero.tiendaelect.gui.util.GUIUtils;
import es.osalguero.tiendaelect.gui.view.VistaAbstractaGUI;

public class VistaExceptionGUI extends VistaAbstractaGUI<VistaExceptionGUIController> implements ActionListener
{
    private final String MENSAJE_EXCEPTION_TITLE = "<html><p align=\"center\" style=\"font-size:14px\">Se ha producido un error en la aplicaci\u00f3n</p></html>";
    private final String MENSAJE_EXCEPTION_DESCRIPTION = "<html><p align=\"justify\" style=\"font-size:11px\">Se ha producido un error no controlado en la aplicaci\u00f3n. Por favor,<br/>revise el fichero de log de la aplicaci\u00f3n. Si considera que el error<br/>puede deberse a un error de configuraci\u00f3n, revise la configuraci\u00f3n en<br/>el men\u00fa 'Editar' y recargue la aplicaci\u00f3n.</p></html>";
    private JButton backButton;
    
    public VistaExceptionGUI(final VistaExceptionGUIController controller) {
        super(controller);
    }
    
    @Override
    public void generaVista() throws Exception {
        final JLabel labelTitulo = new JLabel(MENSAJE_EXCEPTION_TITLE);
        final JLabel labelDescripcion = new JLabel(MENSAJE_EXCEPTION_DESCRIPTION);
        final Box panelDatosBox = new Box(1);
        panelDatosBox.setOpaque(true);
        panelDatosBox.setBackground(Color.white);
        panelDatosBox.setPreferredSize(new Dimension(580, 220));
        panelDatosBox.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        panel.add(labelTitulo);
        panel.add(labelDescripcion);
        panel.setOpaque(false);
        panelDatosBox.add(panel);
        final Box verticalBox = new Box(1);
        verticalBox.setAlignmentY(0.5f);
        final Component glue1 = Box.createVerticalGlue();
        glue1.setPreferredSize(new Dimension(0, 120));
        verticalBox.add(glue1);
        verticalBox.add(panelDatosBox);
        if (this.getController().mostrarVistaAtras()) {
            (this.backButton = GUIUtils.createBackButton()).addActionListener(this);
            final JPanel bottomPanel = new JPanel();
            bottomPanel.setOpaque(false);
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            bottomPanel.setLayout(new BorderLayout());
            bottomPanel.add(this.backButton, "East");
            verticalBox.add(bottomPanel);
        }
        verticalBox.add(Box.createVerticalGlue());
        this.getContainer().add(verticalBox);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource().equals(this.backButton)) {
            this.getController().goBack();
        }
    }

	@Override
	public void setDefaultFocus() {
		// TODO Auto-generated method stub
		
	}
}
