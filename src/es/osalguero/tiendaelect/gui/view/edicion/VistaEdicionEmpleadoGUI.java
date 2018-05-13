package es.osalguero.tiendaelect.gui.view.edicion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;

import es.osalguero.tiendaelect.constants.CategoriaEmpleado;
import es.osalguero.tiendaelect.gui.controller.edicion.VistaEdicionEmpleadoGUIController;
import es.osalguero.tiendaelect.gui.exception.FilterFormatException;
import es.osalguero.tiendaelect.gui.util.GUIUtils;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.persona.Empleado;

public class VistaEdicionEmpleadoGUI extends VistaAbstractaEdicionGUI<VistaEdicionEmpleadoGUIController, Empleado>
{
    private JLabel loginLbl;
    private JLabel nombreLbl;
    private JLabel apellido1Lbl;
    private JLabel apellido2Lbl;
    private JLabel tipoLbl;
    private JLabel datosEmpleadoLbl;
    private JTextField login;
    private JTextField nombre;
    private JTextField apellido1;
    private JTextField apellido2;
    private JComboBox<CategoriaEmpleado> tipo;
    private JCheckBox administrador;
    
    public VistaEdicionEmpleadoGUI(final VistaEdicionEmpleadoGUIController controller, final Empleado elementoEdicion, final TipoEdicionEnum tipoEdicion) {
        super(controller, elementoEdicion, tipoEdicion, null);
    }
    
    @Override
    protected String getViewTitle() {
        if (TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            return "Nuevo Empleado";
        }
        return "Edición Empleado";
    }
    
    @Override
    protected void fillEditPanel(final JPanel editFieldsPanel) {
        UIManager.put("JCheckBox.disabledBackground", Color.BLACK);
        UIManager.put("JCheckBox.disabledForeground", Color.BLACK);
        (this.datosEmpleadoLbl = new JLabel("Datos del empleado")).setBackground(Color.BLUE);
        (this.loginLbl = new JLabel("Login:")).setPreferredSize(new Dimension(90, 20));
        (this.nombreLbl = new JLabel("Nombre:")).setPreferredSize(new Dimension(90, 20));
        (this.apellido1Lbl = new JLabel("Apellido1:")).setPreferredSize(new Dimension(90, 20));
        (this.apellido2Lbl = new JLabel("Apellido2:")).setPreferredSize(new Dimension(90, 20));
        (this.tipoLbl = new JLabel("Tipo:")).setPreferredSize(new Dimension(90, 20));
        (this.login = new JTextField(10)).setDisabledTextColor(Color.BLACK);
        (this.nombre = new JTextField(18)).setDisabledTextColor(Color.BLACK);
        (this.apellido1 = new JTextField(18)).setDisabledTextColor(Color.BLACK);
        (this.apellido2 = new JTextField(18)).setDisabledTextColor(Color.BLACK);
        (this.tipo = GUIUtils.getCategoriaEmpleadoComboBox(false)).setSelectedItem(CategoriaEmpleado.COMERCIAL);
        this.tipo.setPreferredSize(new Dimension(135, 20));
        this.tipo.addActionListener(this);	
        this.administrador = new JCheckBox("Administrador");
        editFieldsPanel.setLayout(new FlowLayout(0, 2, 6));
        editFieldsPanel.add(this.datosEmpleadoLbl);
        editFieldsPanel.add(this.getLineSeparator(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 6));
        editFieldsPanel.add(this.loginLbl);
        editFieldsPanel.add(this.login);
        editFieldsPanel.add(this.createSeparator(118));
        editFieldsPanel.add(this.tipoLbl);
        editFieldsPanel.add(this.tipo);
        editFieldsPanel.add(this.createSeparator(96));
        editFieldsPanel.add(this.administrador);
        editFieldsPanel.add(this.createSeparator(206));
        editFieldsPanel.add(this.nombreLbl);
        editFieldsPanel.add(this.nombre);
        editFieldsPanel.add(this.createSeparator(30));
        editFieldsPanel.add(this.apellido1Lbl);
        editFieldsPanel.add(this.apellido1);
        editFieldsPanel.add(this.createSeparator(34));
        editFieldsPanel.add(this.apellido2Lbl);
        editFieldsPanel.add(this.apellido2);
        if (this.elementoEdicion != null) {
            this.login.setText(((Empleado)this.elementoEdicion).getLogin());
            this.nombre.setText(((Empleado)this.elementoEdicion).getNombre());
            this.apellido1.setText(((Empleado)this.elementoEdicion).getApellido1());
            this.apellido2.setText(((Empleado)this.elementoEdicion).getApellido2());
            this.administrador.setSelected(((Empleado)this.elementoEdicion).esAdministrador());
            this.tipo.setSelectedItem(this.elementoEdicion.getCategoria());
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
    	super.actionPerformed(e);
    }
    
    @Override
    protected void validate() throws FilterFormatException, Exception {
        if (this.login.getText() == null || this.login.getText().trim().isEmpty()) {
            throw new FilterFormatException("El campo 'Login' es obligatorio.");
        }
        if (this.nombre.getText() == null || this.nombre.getText().trim().isEmpty()) {
            throw new FilterFormatException("El campo 'Nombre' es obligatorio.");
        }
        if (this.apellido1.getText() == null || this.apellido1.getText().trim().isEmpty()) {
            throw new FilterFormatException("El campo 'Apellido1' es obligatorio.");
        }
        if (this.apellido2.getText() == null || this.apellido2.getText().trim().isEmpty()) {
            throw new FilterFormatException("El campo 'Apellido2' es obligatorio.");
        }
        if (this.tipo.getSelectedItem() == null) {
            throw new FilterFormatException("Debe seleccionar un tipo para el empleado.");
        }
    }
    
    @Override
    protected void modificarElemento() throws Exception {
        ((Empleado)this.elementoEdicion).setNombre(this.nombre.getText().trim());
        ((Empleado)this.elementoEdicion).setApellido1(this.apellido1.getText().trim());
        ((Empleado)this.elementoEdicion).setApellido2(this.apellido2.getText().trim());
        ((Empleado)this.elementoEdicion).setEsAdministrador(this.administrador.isSelected());
        ((VistaEdicionEmpleadoGUIController)this.getController()).modificarEmpleado((Empleado)this.elementoEdicion);
    }
    
    @Override
    protected void manageTipoEdicion() {
        final boolean enabled = !TipoEdicionEnum.DETALLE.equals(this.tipoEdicion);
        this.login.setEnabled(TipoEdicionEnum.NUEVO.equals(this.tipoEdicion));
        this.nombre.setEnabled(enabled);
        this.apellido1.setEnabled(enabled);
        this.apellido2.setEnabled(enabled);
        this.tipo.setEnabled(TipoEdicionEnum.NUEVO.equals(this.tipoEdicion));
        this.administrador.setEnabled(enabled);
        if (this.elementoEdicion != null) {
            this.nombre.setText(((Empleado)this.elementoEdicion).getNombre());
            this.apellido1.setText(((Empleado)this.elementoEdicion).getApellido1());
            this.apellido2.setText(((Empleado)this.elementoEdicion).getApellido2());
            this.administrador.setSelected(((Empleado)this.elementoEdicion).esAdministrador());
        }
    }
    
    @Override
    protected Empleado crearNuevoElemento() throws Exception {
        Empleado empleado = new Empleado();
        empleado.setCategoria((CategoriaEmpleado)this.tipo.getSelectedItem());
        empleado.setNombre(this.nombre.getText().trim());
        empleado.setApellido1(this.apellido1.getText().trim());
        empleado.setApellido2(this.apellido2.getText().trim());
        empleado.setEsAdministrador(this.administrador.isSelected());
        empleado.setLogin(this.login.getText().trim());
        return empleado;
    }
    
    private JSeparator getLineSeparator(final int width) {
        final JSeparator lineSeparator = new JSeparator();
        lineSeparator.setPreferredSize(new Dimension(width, 1));
        lineSeparator.setForeground(Color.BLACK);
        return lineSeparator;
    }
    
    @Override
    protected VistasEnum getVistaAnterior() {
        return VistasEnum.VISTA_LISTADO_EMPLEADOS;
    }
    
    @Override
    protected ElementoTiendaGenerico getElementoAnterior() {
        return null;
    }
}
