package es.osalguero.tiendaelect.gui.view.principal;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import es.osalguero.tiendaelect.gui.controller.principal.VistaPrincipalComercialGUIController;
import es.osalguero.tiendaelect.gui.util.GUIUtils;

public class VistaPrincipalComercialGUI extends VistaPrincipalGUI
{
    JButton ofertasBtn;
    JButton misReparacionesBtn;
    JButton misTrabajosBtn;
    JButton avisosBtn;
    
    public VistaPrincipalComercialGUI(final VistaPrincipalComercialGUIController controller) {
        super(controller);
    }
    
    @Override
    protected JPanel createPanelAreaPrivada() {
        final JPanel panelPrivado = this.createOptionsPanel();
        panelPrivado.setLayout(new GridLayout(1, 4, 20, 300));
        this.ofertasBtn = GUIUtils.createOptionButton("Gestionar ofertas", "listadoOfertas.png");
        this.misReparacionesBtn = GUIUtils.createOptionButton("Mis reparaciones", "listadoReparaciones.png");
        this.avisosBtn = GUIUtils.createOptionButton("Mis tareas", "tareas.png");
        this.misTrabajosBtn = GUIUtils.createOptionButton("Gesti\u00f3n trabajos", "trabajos.png");
        panelPrivado.add(this.ofertasBtn);
        panelPrivado.add(this.misReparacionesBtn);
        panelPrivado.add(this.misTrabajosBtn);
        panelPrivado.add(this.avisosBtn);
        this.ofertasBtn.addActionListener(this);
        this.misReparacionesBtn.addActionListener(this);
        this.avisosBtn.addActionListener(this);
        this.misTrabajosBtn.addActionListener(this);
        return panelPrivado;
    }
    
    @Override
    protected void gestionaEvento(final ActionEvent e) {
//        if (e.getSource().equals(this.avisosBtn)) {
//            this.getController().goToVista(VistasEnum.VISTA_TAREAS, null);
//        }
//        else if (e.getSource().equals(this.misReparacionesBtn)) {
//            this.getController().goToMisReparaciones();
//        }
//        else if (e.getSource().equals(this.ofertasBtn)) {
//            this.getController().goToListadoOfertas();
//        }
//        else if (e.getSource().equals(this.misTrabajosBtn)) {
//            this.getController().goToVista(VistasEnum.VISTA_LISTADO_TRABAJOS_EMPLEADO_COMERCIAL, null);
//        }
//        else {
//            super.actionPerformed(e);
//        }
    }
}
