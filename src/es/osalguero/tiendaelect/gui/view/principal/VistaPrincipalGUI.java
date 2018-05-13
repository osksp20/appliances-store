package es.osalguero.tiendaelect.gui.view.principal;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.osalguero.tiendaelect.gui.controller.principal.VistaPrincipalGUIController;
import es.osalguero.tiendaelect.gui.util.GUIUtils;
import es.osalguero.tiendaelect.gui.view.VistaAbstractaGUI;

public abstract class VistaPrincipalGUI extends VistaAbstractaGUI<VistaPrincipalGUIController> implements ActionListener
{
    JButton buttonListadoClientes;
    JButton buttonListadoProductos;
    JButton buttonListadoVentas;
    
    public VistaPrincipalGUI(final VistaPrincipalGUIController controller) {
        super(controller);
    }
    
    @Override
    public void generaVista() throws Exception {
        this.getContainer().setLayout(new FlowLayout(0));
        final JPanel panelLabelAreaTienda = GUIUtils.createOptionsLabelPanel();
        final JLabel labelAreaTienda = GUIUtils.createOptionsPanelLabel("Gestión de la tienda");
        panelLabelAreaTienda.add(labelAreaTienda);
        final JPanel panelAreaTienda = this.createOptionsPanel();
        this.buttonListadoClientes = GUIUtils.createOptionButton("Clientes", "listadoClientes.png");
        this.buttonListadoProductos = GUIUtils.createOptionButton("Productos", "listadoProductos.png");
        this.buttonListadoVentas = GUIUtils.createOptionButton("Ventas", "listadoVentas.png");
        this.buttonListadoClientes.addActionListener(this);
        this.buttonListadoProductos.addActionListener(this);
        this.buttonListadoVentas.addActionListener(this);
        panelAreaTienda.add(this.buttonListadoClientes);
        panelAreaTienda.add(this.buttonListadoProductos);
        panelAreaTienda.add(this.buttonListadoVentas);
        this.getContainer().add(panelLabelAreaTienda);
        this.getContainer().add(panelAreaTienda);
        final JPanel panelAreaPrivada = this.createPanelAreaPrivada();
        if (panelAreaPrivada != null) {
            final JPanel panelLabelAreaPrivada = GUIUtils.createOptionsLabelPanel();
            final JLabel labelAreaPrivada = GUIUtils.createOptionsPanelLabel("Área personal");
            panelLabelAreaPrivada.add(labelAreaPrivada);
            this.getContainer().add(panelLabelAreaPrivada);
            this.getContainer().add(panelAreaPrivada);
        }
    }
    
    protected abstract JPanel createPanelAreaPrivada();
    
    protected abstract void gestionaEvento(final ActionEvent p0);
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource().equals(this.buttonListadoClientes)) {
            this.getController().goToListadoClientes();
        }
        else if (e.getSource().equals(this.buttonListadoProductos)) {
            this.getController().goToListadoProductos();
        }
        else if(e.getSource().equals(this.buttonListadoVentas)) {
        	this.getController().goToListadoVentas();
        }
        else {
            this.gestionaEvento(e);
        }
    }
}
