package es.osalguero.tiendaelect.gui.view.listado;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import es.osalguero.tiendaelect.gui.controller.listado.VistaListadoClientesGUIController;
import es.osalguero.tiendaelect.gui.exception.FilterFormatException;
import es.osalguero.tiendaelect.gui.util.GUITableUtils;
import es.osalguero.tiendaelect.gui.view.busqueda.BusquedaCliente;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.persona.Cliente;

public class VistaListadoClientesGUI extends VistaAbstractaListadoGUI<VistaListadoClientesGUIController, Cliente, BusquedaCliente>
{
    private JLabel busquedaDniClienteLbl;
    private JLabel busquedaNombreClienteLbl;
    private JLabel busquedaApellido1ClienteLbl;
    private JLabel busquedaApellido2ClienteLbl;
    private JLabel busquedaDireccionClienteLbl;
    private JLabel busquedaTelefonoClienteLbl;
    private JLabel busquedaEmailClienteLbl;
    private JLabel busquedaFechaNacDesdeClienteLbl;
    private JLabel busquedaFechaNacHastaClienteLbl;
    private JTextField busquedaDniCliente;
    private JTextField busquedaNombreCliente;
    private JTextField busquedaApellido1Cliente;
    private JTextField busquedaApellido2Cliente;
    private JTextField busquedaDireccionCliente;
    private JTextField busquedaTelefonoCliente;
    private JTextField busquedaEmailCliente;
    private JTextField busquedaFechaNacDesdeCliente;
    private JTextField busquedaFechaNacHastaCliente;

    public VistaListadoClientesGUI(final VistaListadoClientesGUIController controller, final ElementoTiendaGenerico elementoPadre) {
        super(controller, elementoPadre);
    }
    
    @Override
    protected JTable createElementsTable(final List<Cliente> clientes) {
        return GUITableUtils.getClientesTable(clientes);
    }
    
    @Override
    protected void fillSearchElementsPanel(final JPanel searchElementsPanel) {
    	//Primera línea
        this.busquedaDniCliente = new JTextField(12);
        (this.busquedaDniClienteLbl = new JLabel("DNI:")).setPreferredSize(new Dimension(80, 20));
        searchElementsPanel.add(this.busquedaDniClienteLbl);
        searchElementsPanel.add(this.busquedaDniCliente);
        searchElementsPanel.add(this.createSeparator(35));
        this.busquedaTelefonoCliente = new JTextField(12);
        (this.busquedaTelefonoClienteLbl = new JLabel("Teléfono:")).setPreferredSize(new Dimension(100, 20));
        searchElementsPanel.add(this.busquedaTelefonoClienteLbl);
        searchElementsPanel.add(this.busquedaTelefonoCliente);
        searchElementsPanel.add(this.createSeparator(35));
        this.busquedaEmailCliente = new JTextField(12);
        (this.busquedaEmailClienteLbl = new JLabel("Email:")).setPreferredSize(new Dimension(100, 20));
        searchElementsPanel.add(this.busquedaEmailClienteLbl);
        searchElementsPanel.add(this.busquedaEmailCliente);
        searchElementsPanel.add(this.createSeparator(180));
        
        //Segunda línea
        this.busquedaNombreCliente = new JTextField(12);
        (this.busquedaNombreClienteLbl = new JLabel("Nombre:")).setPreferredSize(new Dimension(80, 20));
        searchElementsPanel.add(this.busquedaNombreClienteLbl);
        searchElementsPanel.add(this.busquedaNombreCliente);
        searchElementsPanel.add(this.createSeparator(35));
        this.busquedaApellido1Cliente = new JTextField(12);
        (this.busquedaApellido1ClienteLbl = new JLabel("1er Apellido:")).setPreferredSize(new Dimension(100, 20));
        searchElementsPanel.add(this.busquedaApellido1ClienteLbl);
        searchElementsPanel.add(this.busquedaApellido1Cliente);
        searchElementsPanel.add(this.createSeparator(35));
        this.busquedaApellido2Cliente = new JTextField(12);
        (this.busquedaApellido2ClienteLbl = new JLabel("2o Apellido:")).setPreferredSize(new Dimension(100, 20));
        searchElementsPanel.add(this.busquedaApellido2ClienteLbl);
        searchElementsPanel.add(this.busquedaApellido2Cliente);
        searchElementsPanel.add(this.createSeparator(150));
        
        //Tercera línea
        this.busquedaDireccionCliente = new JTextField(22);
        (this.busquedaDireccionClienteLbl = new JLabel("Dirección:")).setPreferredSize(new Dimension(80, 20));
        searchElementsPanel.add(this.busquedaDireccionClienteLbl);
        searchElementsPanel.add(this.busquedaDireccionCliente);
        searchElementsPanel.add(this.createSeparator(30));
        (this.busquedaFechaNacDesdeCliente = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"))).setPreferredSize(
        		new Dimension(128, 20));
        this.busquedaFechaNacDesdeClienteLbl = new JLabel("Fecha nac. desde (dd/mm/yyyy):");
        this.busquedaFechaNacDesdeClienteLbl.setPreferredSize(new Dimension(200, 20));
        (this.busquedaFechaNacHastaCliente = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"))).setPreferredSize(
        		new Dimension(128, 20));
        this.busquedaFechaNacHastaClienteLbl = new JLabel("Hasta (dd/mm/yyyy):");
        this.busquedaFechaNacHastaClienteLbl.setPreferredSize(new Dimension(130, 20));
        searchElementsPanel.add(this.busquedaFechaNacDesdeClienteLbl);
        searchElementsPanel.add(this.busquedaFechaNacDesdeCliente);
        searchElementsPanel.add(this.createSeparator(16));
        searchElementsPanel.add(this.busquedaFechaNacHastaClienteLbl);
        searchElementsPanel.add(this.busquedaFechaNacHastaCliente);
    }
    
    @Override
    protected BusquedaCliente createElementoBusqueda() throws FilterFormatException, Exception {
        final BusquedaCliente busqueda = new BusquedaCliente();
        busqueda.setDni(this.busquedaDniCliente.getText());
        busqueda.setNombre(this.busquedaNombreCliente.getText());
        busqueda.setApellido1(this.busquedaApellido1Cliente.getText());
        busqueda.setApellido2(this.busquedaApellido2Cliente.getText());
        if (this.busquedaTelefonoCliente.getText() != null && !this.busquedaTelefonoCliente.getText().trim().isEmpty()) {
            try {
                final long telefono = Long.valueOf(this.busquedaTelefonoCliente.getText());
                if (telefono < 0L) {
                    throw new Exception();
                }
            }
            catch (Exception ex) {
                throw new FilterFormatException("El formato del campo 'Teléfono' debe ser numérico y mayor o igual que 0.");
            }
        }
        busqueda.setTelefono(this.busquedaTelefonoCliente.getText());
        busqueda.setDireccion(this.busquedaDireccionCliente.getText());
        if (this.busquedaFechaNacDesdeCliente.getText() != null && !this.busquedaFechaNacDesdeCliente.getText().trim().isEmpty()) {
            try {
                busqueda.setFechaNacDesde(new SimpleDateFormat("dd/MM/yyyy").parse(this.busquedaFechaNacDesdeCliente.getText()));
            }
            catch (Exception ex) {
                throw new FilterFormatException("El formato del campo 'Fecha Nacimiento Desde' no es correcto, debe ser dd/mm/yyyy");
            }
        }
        if (this.busquedaFechaNacHastaCliente.getText() != null && !this.busquedaFechaNacHastaCliente.getText().trim().isEmpty()) {
            try {
                busqueda.setFechaNacHasta(new SimpleDateFormat("dd/MM/yyyy").parse(this.busquedaFechaNacHastaCliente.getText()));
            }
            catch (Exception ex) {
                throw new FilterFormatException("El formato del campo 'Fecha Nacimiento Hasta' no es correcto, debe ser dd/mm/yyyy");
            }
        }
        if (busqueda.getFechaNacDesde() != null && busqueda.getFechaNacDesde().compareTo(Calendar.getInstance().getTime()) > 0) {
            throw new FilterFormatException("El valor del campo 'Fecha Nacimiento Desde' no puede ser mayor a hoy.");
        }
        if (busqueda.getFechaNacHasta() != null && busqueda.getFechaNacHasta().compareTo(Calendar.getInstance().getTime()) > 0) {
            throw new FilterFormatException("El valor del campo 'Fecha Nacimiento Hasta' no puede ser mayor a hoy.");
        }
        if (busqueda.getFechaNacDesde() != null && busqueda.getFechaNacHasta() != null && busqueda.getFechaNacDesde().compareTo(busqueda.getFechaNacHasta()) > 0) {
            throw new FilterFormatException("El valor del campo 'Fecha Nacimiento Desde' no puede ser posterior al valor del campo 'Fecha Nacimiento Hasta'.");
        }
        busqueda.setEmail(this.busquedaEmailCliente.getText());
        return busqueda;
    }
    
    @Override
    protected void emptySearchFilters() {
        this.busquedaDniCliente.setText(null);
        this.busquedaNombreCliente.setText(null);
        this.busquedaApellido1Cliente.setText(null);
        this.busquedaApellido2Cliente.setText(null);
        this.busquedaTelefonoCliente.setText(null);
        this.busquedaEmailCliente.setText(null);
        this.busquedaDireccionCliente.setText(null);
        this.busquedaFechaNacDesdeCliente.setText(null);
        this.busquedaFechaNacHastaCliente.setText(null);
    }
    
    @Override
    protected String getListadoTitle() {
        return "Clientes";
    }
    
    @Override
    protected int getSearchPanelHeight() {
        return 78;
    }
    
    @Override
    protected String getDeleteWarningMessage() {
        return "¿Está seguro de eliminar el cliente seleccionado?.\nNo podrá recuperarse y además se elimanarán las reparaciones finalizadas o no iniciadas asignadas al cliente.";
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
