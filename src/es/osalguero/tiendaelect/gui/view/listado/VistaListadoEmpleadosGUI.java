package es.osalguero.tiendaelect.gui.view.listado;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import es.osalguero.tiendaelect.constants.CategoriaEmpleado;
import es.osalguero.tiendaelect.gui.controller.listado.VistaListadoEmpleadosGUIController;
import es.osalguero.tiendaelect.gui.exception.FilterFormatException;
import es.osalguero.tiendaelect.gui.util.GUITableUtils;
import es.osalguero.tiendaelect.gui.util.GUIUtils;
import es.osalguero.tiendaelect.gui.view.busqueda.BusquedaEmpleado;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.persona.Empleado;

public class VistaListadoEmpleadosGUI extends VistaAbstractaListadoGUI<VistaListadoEmpleadosGUIController, Empleado, BusquedaEmpleado>
{
    private JLabel busquedaLoginLbl;
    private JLabel busquedaNombreLbl;
    private JLabel busquedaApellido1Lbl;
    private JLabel busquedaApellido2Lbl;
    private JLabel busquedaAdministradorLbl;
    private JLabel busquedaTipoLbl;
    private JTextField busquedaLogin;
    private JTextField busquedaNombre;
    private JTextField busquedaApellido1;
    private JTextField busquedaApellido2;
    private JComboBox<Boolean> busquedaAdministrador;
    private JComboBox<CategoriaEmpleado> busquedaTipo;
    
    public VistaListadoEmpleadosGUI(final VistaListadoEmpleadosGUIController controller, final ElementoTiendaGenerico elementoPadre) {
        super(controller, elementoPadre);
    }
    
    @Override
    protected BusquedaEmpleado createElementoBusqueda() throws FilterFormatException, Exception {
        final BusquedaEmpleado busqueda = new BusquedaEmpleado();
        if (this.busquedaLogin.getText() != null && !this.busquedaLogin.getText().trim().isEmpty()) {
            busqueda.setLogin(this.busquedaLogin.getText().trim());
        }
        if (this.busquedaNombre.getText() != null && !this.busquedaNombre.getText().trim().isEmpty()) {
            busqueda.setNombre(this.busquedaNombre.getText().trim());
        }
        if (this.busquedaApellido1.getText() != null && !this.busquedaApellido1.getText().trim().isEmpty()) {
            busqueda.setApellido1(this.busquedaApellido1.getText().trim());
        }
        if (this.busquedaApellido2.getText() != null && !this.busquedaApellido2.getText().trim().isEmpty()) {
            busqueda.setApellido2(this.busquedaApellido2.getText().trim());
        }
        busqueda.setTipo((CategoriaEmpleado)this.busquedaTipo.getSelectedItem());
        busqueda.setAdministrador((Boolean)this.busquedaAdministrador.getSelectedItem());
        return busqueda;
    }
    
    @Override
    protected void emptySearchFilters() {
        this.busquedaLogin.setText(null);
        this.busquedaNombre.setText(null);
        this.busquedaApellido1.setText(null);
        this.busquedaApellido2.setText(null);
        this.busquedaAdministrador.setSelectedIndex(-1);
        this.busquedaTipo.setSelectedIndex(-1);
    }
    
    @Override
    protected void fillSearchElementsPanel(final JPanel searchElementsPanel) {
        //Primera línea
    	this.busquedaLogin = new JTextField(11);
        (this.busquedaLoginLbl = new JLabel("Login:")).setPreferredSize(new Dimension(70, 20));
        searchElementsPanel.add(this.busquedaLoginLbl);
        searchElementsPanel.add(this.busquedaLogin);
        searchElementsPanel.add(this.createSeparator(134));
        (this.busquedaTipo = GUIUtils.getCategoriaEmpleadoComboBox(true)).setPreferredSize(new Dimension(135, 20));
        (this.busquedaTipoLbl = new JLabel("Tipo:")).setPreferredSize(new Dimension(90, 20));
        searchElementsPanel.add(this.busquedaTipoLbl);
        searchElementsPanel.add(this.busquedaTipo);
        searchElementsPanel.add(this.createSeparator(124));
        (this.busquedaAdministrador = GUIUtils.getBooleanComboBox(true)).setPreferredSize(new Dimension(60, 20));
        (this.busquedaAdministradorLbl = new JLabel("Administrador:")).setPreferredSize(new Dimension(90, 20));
        searchElementsPanel.add(this.busquedaAdministradorLbl);
        searchElementsPanel.add(this.busquedaAdministrador);
        searchElementsPanel.add(this.createSeparator(71));
        
        //Segunda línea
        this.busquedaNombre = new JTextField(17);
        (this.busquedaNombreLbl = new JLabel("Nombre:")).setPreferredSize(new Dimension(70, 20));
        searchElementsPanel.add(this.busquedaNombreLbl);
        searchElementsPanel.add(this.busquedaNombre);
        searchElementsPanel.add(this.createSeparator(68));
        this.busquedaApellido1 = new JTextField(17);
        (this.busquedaApellido1Lbl = new JLabel("1er Apellido:")).setPreferredSize(new Dimension(90, 20));
        searchElementsPanel.add(this.busquedaApellido1Lbl);
        searchElementsPanel.add(this.busquedaApellido1);
        searchElementsPanel.add(this.createSeparator(68));
        this.busquedaApellido2 = new JTextField(17);
        (this.busquedaApellido2Lbl = new JLabel("2º Apellido:")).setPreferredSize(new Dimension(90, 20));
        searchElementsPanel.add(this.busquedaApellido2Lbl);
        searchElementsPanel.add(this.busquedaApellido2);
    }
    
    @Override
    protected JTable createElementsTable(final List<Empleado> empleados) {
        return GUITableUtils.getEmpleadosTable(empleados);
    }
    
    @Override
    protected String getListadoTitle() {
        return "Empleados";
    }
    
    @Override
    protected int getSearchPanelHeight() {
        return 50;
    }
    
    @Override
    protected String getDeleteWarningMessage() {
        return "¿Está seguro de eliminar el empleado seleccionado?. No podrá recuperarse.";
    }
    
    public Empleado showEmployeeReplacement(final List<Empleado> empleadosReemplazo) {
        if (empleadosReemplazo == null || empleadosReemplazo.isEmpty()) {
            this.getController().showError("No es posible eliminar el empleado seleccionado, ya que es el único existente en su categoría.");
            return null;
        }
        final Empleado empleadoSeleccionado = (Empleado)JOptionPane.showInputDialog(this.getContainer(), "Debe seleccionar un empleado con la misma categoría que el seleccionado para eliminar,\npara traspasar sus trabajos y/o reparaciones asignadas.", "Seleccionar empleado", 3, null, empleadosReemplazo.toArray(new Empleado[0]), null);
        return empleadoSeleccionado;
    }
    
    @Override
    protected VistasEnum getVistaAnterior() {
        return VistasEnum.VISTA_PRINCIPAL;
    }
    
    @Override
    protected ElementoTiendaGenerico getElementoAnterior() {
        return null;
    }
}
