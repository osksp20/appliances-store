package es.osalguero.tiendaelect.gui.view.edicion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.controller.edicion.VistaEdicionClienteGUIController;
import es.osalguero.tiendaelect.gui.exception.FilterFormatException;
import es.osalguero.tiendaelect.gui.util.GUITableUtils;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.negocio.Nomina;
import es.osalguero.tiendaelect.modelo.negocio.Reparacion;
import es.osalguero.tiendaelect.modelo.negocio.Venta;
import es.osalguero.tiendaelect.modelo.persona.Cliente;

public class VistaEdicionClienteGUI extends VistaAbstractaEdicionGUI<VistaEdicionClienteGUIController, Cliente>
{
    private JLabel datosClienteLbl;
    private JLabel ventasClienteLbl;
    private JLabel reparacionesClienteLbl;
    private JLabel dniLbl;
    private JLabel nombreLbl;
    private JLabel apellido1Lbl;
    private JLabel apellido2Lbl;
    private JLabel direccionLbl;
    private JLabel tlfLbl;
    private JLabel emailLbl;
    private JLabel fechaNacLbl;
    private JLabel sinVentasLbl;
    private JLabel sinReparacionesLbl;
    private JLabel ultimaNominaLbl;
    private JLabel ultimaNominaFechaLbl;
    private JLabel ultimaNominaImporteLbl;
    private JScrollPane ventasPane;
    private JScrollPane reparacionesPane;
    private JTextField dni;
    private JTextField nombre;
    private JTextField apellido1;
    private JTextField apellido2;
    private JTextField direccion;
    private JTextField tlf;
    private JTextField email;
    private JTextField fechaNac;
    private JTextField ultimaNominaFecha;
    private JTextField ultimaNominaImporte;
    
    public VistaEdicionClienteGUI(final VistaEdicionClienteGUIController controller, final Cliente cliente, final TipoEdicionEnum tipoEdicion) {
        super(controller, cliente, tipoEdicion, null);
    }
    
    @Override
    protected String getViewTitle() {
        if (TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            return "Nuevo Cliente";
        }
        String title = "Edicion Cliente";
        if (this.elementoEdicion != null && ((Cliente)this.elementoEdicion).getDni() != null) {
            title = title.concat(" DNI ").concat(((Cliente)this.elementoEdicion).getDni());
        }
        return title;
    }
    
    @Override
    protected void fillEditPanel(final JPanel editFieldsPanel) {
        (this.datosClienteLbl = new JLabel("Datos del cliente")).setBackground(Color.BLUE);
        (this.dniLbl = new JLabel("DNI:")).setPreferredSize(new Dimension(80, 20));
        (this.nombreLbl = new JLabel("Nombre:")).setPreferredSize(new Dimension(80, 20));
        (this.apellido1Lbl = new JLabel("1er apellido:")).setPreferredSize(new Dimension(98, 20));
        (this.apellido2Lbl = new JLabel("2º apellido:")).setPreferredSize(new Dimension(80, 20));
        (this.direccionLbl = new JLabel("Dirección:")).setPreferredSize(new Dimension(80, 20));
        (this.tlfLbl = new JLabel("Teléfono:")).setPreferredSize(new Dimension(80, 20));
        (this.emailLbl = new JLabel("Email:")).setPreferredSize(new Dimension(80, 20));
        (this.fechaNacLbl = new JLabel("Fecha nacimiento (dd/mm/yyyy):")).setPreferredSize(new Dimension(200, 20));
        (this.dni = new JTextField(12)).setDisabledTextColor(Color.BLACK);
        (this.nombre = new JTextField(12)).setDisabledTextColor(Color.BLACK);
        (this.apellido1 = new JTextField(20)).setDisabledTextColor(Color.BLACK);
        (this.apellido2 = new JTextField(20)).setDisabledTextColor(Color.BLACK);
        (this.direccion = new JTextField(45)).setDisabledTextColor(Color.BLACK);
        (this.tlf = new JTextField(12)).setDisabledTextColor(Color.BLACK);
        (this.email = new JTextField(20)).setDisabledTextColor(Color.BLACK);
        (this.fechaNac = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"))).setDisabledTextColor(Color.BLACK);
        this.fechaNac.setPreferredSize(new Dimension(122, 20));
        this.ultimaNominaLbl = new JLabel("Última nómina");
        this.ultimaNominaFechaLbl = new JLabel("Fecha (dd/mm/yyyy):");
        this.ultimaNominaFechaLbl.setPreferredSize(new Dimension(150, 20));
        this.ultimaNominaImporteLbl = new JLabel("Importe:");
        this.ultimaNominaImporteLbl.setPreferredSize(new Dimension(80, 20));
        (this.ultimaNominaFecha = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"))).setDisabledTextColor(Color.BLACK);
        this.ultimaNominaFecha.setPreferredSize(new Dimension(122, 20));
        (this.ultimaNominaImporte = new JTextField(12)).setDisabledTextColor(Color.BLACK);
        this.sinVentasLbl = new JLabel("El cliente no ha realizado ninguna compra");
        this.sinReparacionesLbl = new JLabel("El cliente no tiene reparaciones");
        (this.ventasPane = new JScrollPane()).setBorder(null);
        (this.reparacionesPane = new JScrollPane()).setBorder(null);
        this.ventasClienteLbl = new JLabel("Compras del cliente");
        this.reparacionesClienteLbl = new JLabel("Reparaciones del cliente");
        this.sinVentasLbl.setPreferredSize(new Dimension(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 12, 86));
        this.sinReparacionesLbl.setPreferredSize(new Dimension(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 12, 86));
        this.ventasPane.setPreferredSize(new Dimension(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 6, 86));
        this.reparacionesPane.setPreferredSize(new Dimension(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 6, 86));
        editFieldsPanel.add(this.datosClienteLbl);
        editFieldsPanel.add(this.getLineSeparator(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 6));
        editFieldsPanel.setLayout(new FlowLayout(0, 2, 6));
        
        //Primera línea
        editFieldsPanel.add(this.dniLbl);
        editFieldsPanel.add(this.dni);
        editFieldsPanel.add(this.createSeparator(35));
        editFieldsPanel.add(this.fechaNacLbl);
        editFieldsPanel.add(this.fechaNac);
        editFieldsPanel.add(this.createSeparator(35));
        editFieldsPanel.add(this.tlfLbl);
        editFieldsPanel.add(this.tlf);
        editFieldsPanel.add(this.createSeparator(160));

        //Segunda línea
        editFieldsPanel.add(this.nombreLbl);
        editFieldsPanel.add(this.nombre);
        editFieldsPanel.add(this.createSeparator(35));
        editFieldsPanel.add(this.apellido1Lbl);
        editFieldsPanel.add(this.apellido1);
        editFieldsPanel.add(this.createSeparator(35));
        editFieldsPanel.add(this.apellido2Lbl);
        editFieldsPanel.add(this.apellido2);
        editFieldsPanel.add(this.createSeparator(40));
        
        //Tercera línea
        editFieldsPanel.add(this.direccionLbl);
        editFieldsPanel.add(this.direccion);
        editFieldsPanel.add(this.createSeparator(35));
        editFieldsPanel.add(this.emailLbl);
        editFieldsPanel.add(this.email);
        editFieldsPanel.add(this.createSeparator(20));
        
        //Nómina
        this.ultimaNominaLbl.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));
        editFieldsPanel.add(this.ultimaNominaLbl);
        editFieldsPanel.add(this.getLineSeparator(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 6));
        editFieldsPanel.add(ultimaNominaFechaLbl);
        editFieldsPanel.add(ultimaNominaFecha);
        editFieldsPanel.add(this.createSeparator(30));
        editFieldsPanel.add(ultimaNominaImporteLbl);
        editFieldsPanel.add(ultimaNominaImporte);
        editFieldsPanel.add(this.createSeparator(400));
        
        //Datos complementarios
        this.ventasClienteLbl.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));
        editFieldsPanel.add(this.ventasClienteLbl);
        editFieldsPanel.add(this.getLineSeparator(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 6));
        List<Venta> ventasCliente = null;
        if(this.elementoEdicion != null) {
        	ventasCliente = TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getVentasByCliente(((Cliente)this.elementoEdicion).getDni());
        }
        if (ventasCliente == null || ventasCliente.isEmpty()) {
            editFieldsPanel.add(this.sinVentasLbl);
        }
        else {
            final JTable ventasTable = GUITableUtils.getVentasTable(ventasCliente);
            this.ventasPane = new JScrollPane(ventasTable);
            ventasPane.setPreferredSize(new Dimension(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 6, 120));
            this.ventasPane.getViewport().setBackground(Color.WHITE);
            editFieldsPanel.add(this.ventasPane);
        }
        this.reparacionesClienteLbl.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));
        editFieldsPanel.add(this.reparacionesClienteLbl);
        editFieldsPanel.add(this.getLineSeparator(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 6));
        List<Reparacion> reparacionesCliente = null;
        if(this.elementoEdicion != null) {
        	reparacionesCliente = TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getReparacionesByCliente(((Cliente)this.elementoEdicion));
        }
        if(reparacionesCliente == null || reparacionesCliente.isEmpty()) {
        	editFieldsPanel.add(this.sinReparacionesLbl);
        } else {
        	final JTable reparacionesTable = GUITableUtils.getReparacionesTable(reparacionesCliente);
        	this.reparacionesPane = new JScrollPane(reparacionesTable);
        	reparacionesPane.setPreferredSize(new Dimension(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 6, 120));
        	this.reparacionesPane.getViewport().setBackground(Color.WHITE);
        	editFieldsPanel.add(this.reparacionesPane);
        }
        
    }
    
    @Override
    protected void validate() throws FilterFormatException, Exception {
        if (TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            this.validateMandatoryString("DNI", this.dni.getText());
        }
        this.validateMandatoryString("Nombre", this.nombre.getText());
        this.validateMandatoryString("1er apellido", this.apellido1.getText());
        this.validateMandatoryString("2º", this.apellido2.getText());
        this.validateMandatoryString("Fecha de nacimiento", this.fechaNac.getText());
        this.validateMandatoryString("Direccion", this.direccion.getText());
        this.validateMandatoryString("Teléfono", this.tlf.getText());
        Date fechaNacDate = null;
        try {
            fechaNacDate = new SimpleDateFormat("dd/MM/yyyy").parse(this.fechaNac.getText());
        }
        catch (Exception e2) {
            throw new FilterFormatException("El valor del campo 'Fecha de nacimiento' debe mantener el formato 'dd/mm/yyyy'.");
        }
        if (fechaNacDate.compareTo(Calendar.getInstance().getTime()) > 0) {
            throw new FilterFormatException("El valor del campo 'Fecha de nacimiento' no puede ser posterior a hoy.");
        }
        try {
            String[] emailValues = this.email.getText().split("@");
            if (emailValues.length != 2) {
                throw new Exception();
            }
            emailValues = emailValues[1].split("\\.");
            if (emailValues.length != 2) {
                throw new Exception();
            }
        }
        catch (Exception e2) {
            throw new FilterFormatException("El formato del campo 'Email' debe ser una dirección de email válida.");
        }
        if((ultimaNominaFecha.getText().isEmpty() && !ultimaNominaImporte.getText().trim().isEmpty()) || 
        		(ultimaNominaImporte.getText().isEmpty() && !ultimaNominaFecha.getText().isEmpty())) {
        			throw new FilterFormatException("Deben indicarse tanto la fecha como el importe de la última nómina.");
        } else if(!ultimaNominaFecha.getText().isEmpty() && !ultimaNominaImporte.getText().isEmpty()) {
        	Date ultimaNominaDate = null;
        	try {
        		ultimaNominaDate = new SimpleDateFormat("dd/MM/yyyy").parse(this.ultimaNominaFecha.getText());
        	} catch(Exception e) {
        		throw new FilterFormatException("EL valor del campo 'Última nomina - Fecha' debe mantener el formato 'dd/mm/yyyy'.");
        	}
        	if(ultimaNominaDate.compareTo(Calendar.getInstance().getTime()) > 0) {
        		throw new FilterFormatException("El valor del campo 'Última nómina - Fecha' no puede ser posterior a hoy.");
        	}
        	try {
        		Double.parseDouble(this.ultimaNominaImporte.getText().replaceAll(",", "\\."));
        	} catch(Exception e) {
        		throw new FilterFormatException("El formato del campo 'Última nómina - Importe' debe ser numérico");
        	}
        }
    }
    
    @Override
    protected void modificarElemento() throws Exception {
        if (TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            ((Cliente)this.elementoEdicion).setDni(this.dni.getText());
        }
        ((Cliente)this.elementoEdicion).setNombre(this.nombre.getText());
        ((Cliente)this.elementoEdicion).setApellido1(this.apellido1.getText());
        ((Cliente)this.elementoEdicion).setApellido2(this.apellido2.getText());
        ((Cliente)this.elementoEdicion).setDireccion(this.direccion.getText());
        ((Cliente)this.elementoEdicion).setTelefono(this.tlf.getText());
        ((Cliente)this.elementoEdicion).setEmail(this.email.getText());
        ((Cliente)this.elementoEdicion).setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(this.fechaNac.getText()));
        if(this.ultimaNominaFecha.getText().isEmpty() && this.ultimaNominaImporte.getText().isEmpty()) {
        	((Cliente)this.elementoEdicion).setUltimaNomina(null);
        } else {
        	if(((Cliente)this.elementoEdicion).getUltimaNomina() == null) {
        		((Cliente)this.elementoEdicion).setUltimaNomina(new Nomina());
        	}
        	((Cliente)this.elementoEdicion).getUltimaNomina().setCantidad(Double.parseDouble(this.ultimaNominaImporte.getText().replaceAll(",","\\.")));
        	((Cliente)this.elementoEdicion).getUltimaNomina().setFecha(new SimpleDateFormat("dd/MM/yyyy").parse(this.ultimaNominaFecha.getText()));
        }
        ((VistaEdicionClienteGUIController)this.getController()).modificarCliente((Cliente)this.elementoEdicion);
    }
    
    @Override
    protected void manageTipoEdicion() {
        final boolean enabled = !TipoEdicionEnum.DETALLE.equals(this.tipoEdicion);
        if (TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            this.dni.setEnabled(true);
        }
        else {
            this.dni.setEnabled(false);
        }
        this.nombre.setEnabled(enabled);
        this.apellido1.setEnabled(enabled);
        this.apellido2.setEnabled(enabled);
        this.direccion.setEnabled(enabled);
        this.tlf.setEnabled(enabled);
        this.email.setEnabled(enabled);
        this.fechaNac.setEnabled(enabled);
        this.ultimaNominaFecha.setEnabled(enabled);
        this.ultimaNominaImporte.setEnabled(enabled);
        this.manageViewData();
    }
    
    private JSeparator getLineSeparator(final int width) {
        final JSeparator lineSeparator = new JSeparator();
        lineSeparator.setPreferredSize(new Dimension(width, 1));
        lineSeparator.setForeground(Color.BLACK);
        return lineSeparator;
    }
    
    private void validateMandatoryString(final String fieldName, final String value) throws FilterFormatException {
        if (value == null || value.trim().isEmpty()) {
            throw new FilterFormatException("El campo '".concat(fieldName).concat("' es obligatorio."));
        }
    }
    
    @Override
    protected Cliente crearNuevoElemento() throws Exception {
        final Cliente cliente = new Cliente();
        cliente.setDni(this.dni.getText());
        cliente.setNombre(this.nombre.getText());
        cliente.setApellido1(this.apellido1.getText());
        cliente.setApellido2(this.apellido2.getText());
        cliente.setDireccion(this.direccion.getText());
        cliente.setTelefono(this.tlf.getText());
        cliente.setEmail(this.email.getText());
        cliente.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(this.fechaNac.getText()));
        return cliente;
    }
    
    @Override
    protected VistasEnum getVistaAnterior() {
        return VistasEnum.VISTA_LISTADO_CLIENTES;
    }
    
    private void manageViewData() {
        this.labelVistaEdicion.setText(this.getViewTitle());
        if (!TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            this.panelLabelEdicion.setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 12, 35));
        }
        if (this.elementoEdicion != null && !this.tipoEdicion.equals(TipoEdicionEnum.NUEVO)) {
            this.dni.setText(((Cliente)this.elementoEdicion).getDni());
            this.nombre.setText(((Cliente)this.elementoEdicion).getNombre());
            this.apellido1.setText(((Cliente)this.elementoEdicion).getApellido1());
            this.apellido2.setText(((Cliente)this.elementoEdicion).getApellido2());
            this.direccion.setText(((Cliente)this.elementoEdicion).getDireccion());
            this.tlf.setText(((Cliente)this.elementoEdicion).getTelefono());
            this.email.setText(((Cliente)this.elementoEdicion).getEmail());
            if(((Cliente)this.elementoEdicion).getFechaNacimiento() != null) {
                this.fechaNac.setText(new SimpleDateFormat("dd/MM/yyyy").format(((Cliente)this.elementoEdicion).getFechaNacimiento()));
            }
            if(((Cliente)this.elementoEdicion).getUltimaNomina() != null) {
            	if(((Cliente)this.elementoEdicion).getUltimaNomina().getFecha() != null) {
            		this.ultimaNominaFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(((Cliente)this.elementoEdicion).getUltimaNomina().getFecha()));
            	}
            	if(((Cliente)this.elementoEdicion).getUltimaNomina().getCantidad() != null) {
            		this.ultimaNominaImporte.setText(String.valueOf(new BigDecimal((((Cliente)this.elementoEdicion).getUltimaNomina().getCantidad())).setScale(2, RoundingMode.HALF_UP).doubleValue()));
            	}
            }
        }
    }

	@Override
	protected ElementoTiendaGenerico getElementoAnterior() {
		return null;
	}
}
