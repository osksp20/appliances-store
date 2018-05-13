package es.osalguero.tiendaelect.gui.view.gestionApp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import es.osalguero.tiendaelect.gui.controller.gestionApp.VistaConfiguracionPropiedadesGUIController;
import es.osalguero.tiendaelect.gui.util.GUITableUtils;
import es.osalguero.tiendaelect.gui.util.GUIUtils;
import es.osalguero.tiendaelect.gui.view.VistaAbstractaGUI;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.Propiedades;

public class VistaConfiguracionPropiedadesGUI extends VistaAbstractaGUI<VistaConfiguracionPropiedadesGUIController> implements ActionListener
{
    private JTable tablePropiedades;
    private JScrollPane propiedadesScrollPane;
    private JLabel configurarPropiedadesLbl;
    private JPanel configurarPropiedadesLblPanel;
    private JPanel panelElementos;
    private JPanel panelBotones;
    private JButton backButton;
    private JButton guardarButton;
    
    public VistaConfiguracionPropiedadesGUI(final VistaConfiguracionPropiedadesGUIController controller) {
        super(controller);
    }
    
    @Override
    public void generaVista() throws Exception {
        this.getContainer().removeAll();
        this.getContainer().setLayout(new FlowLayout(0));
        this.configurarPropiedadesLbl = GUIUtils.createOptionsPanelLabel("Configuración");
        (this.configurarPropiedadesLblPanel = GUIUtils.createOptionsLabelPanel()).add(this.configurarPropiedadesLbl);
        this.tablePropiedades = GUITableUtils.getConfiguracionPropiedadesTable();
        (this.propiedadesScrollPane = new JScrollPane(this.tablePropiedades)).setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 10, 380));
        this.panelElementos = new JPanel(new FlowLayout(0, 2, 6));
        (this.panelBotones = new JPanel(new SpringLayout())).setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 10, 40));
        this.panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        this.panelBotones.setOpaque(false);
        this.panelElementos.setBorder(BorderFactory.createEmptyBorder());
        this.panelElementos.setOpaque(true);
        this.panelElementos.add(this.propiedadesScrollPane);
        (this.backButton = GUIUtils.createBackButton()).addActionListener(this);
        (this.guardarButton = GUIUtils.createButton("Guardar", "apply.png")).addActionListener(this);
        this.getContainer().add(this.configurarPropiedadesLblPanel);
        this.getContainer().add(this.panelElementos);
        this.panelElementos.setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 10, 390));
        this.panelBotones.add(this.backButton);
        this.panelBotones.add(this.guardarButton);
        final SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.WEST, this.backButton, 0, SpringLayout.WEST, this.panelBotones);
        layout.putConstraint(SpringLayout.EAST, this.guardarButton, 0, SpringLayout.EAST, this.panelBotones);
        this.panelBotones.setLayout(layout);
        this.getContainer().add(this.panelBotones);
        this.getContainer().revalidate();
        this.getContainer().repaint();
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource().equals(this.backButton)) {
            this.getController().goToVista(VistasEnum.VISTA_PRINCIPAL, null);
        }
        else if (e.getSource().equals(this.guardarButton)) {
            this.tablePropiedades.getCellEditor().stopCellEditing();
            final Map<Propiedades, String> propiedades = new HashMap<Propiedades, String>();
            for (int i = 0; i < this.tablePropiedades.getRowCount(); ++i) {
                try {
                    propiedades.put(Propiedades.getByNombrePropiedad((String)this.tablePropiedades.getValueAt(i, 0)), (String)this.tablePropiedades.getValueAt(i, 1));
                }
                catch (Exception ex) {}
            }
            this.getController().guardarConfiguracion(propiedades);
        }
    }
}
