package es.osalguero.tiendaelect.gui.view.listado;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import es.osalguero.tiendaelect.gui.busqueda.BusquedaVenta;
import es.osalguero.tiendaelect.gui.controller.listado.VistaListadoVentasGUIController;
import es.osalguero.tiendaelect.gui.exception.FilterFormatException;
import es.osalguero.tiendaelect.gui.util.GUITableUtils;
import es.osalguero.tiendaelect.gui.util.GUIUtils;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.negocio.Venta;

public class VistaListadoVentasGUI extends VistaAbstractaListadoGUI<VistaListadoVentasGUIController, Venta, BusquedaVenta>
{	
	private JLabel busquedaIdVentaLbl;
    private JLabel busquedaDNIClienteLbl;
    private JLabel busquedaFechaDesdeLbl;
    private JLabel busquedaFechaHastaLbl;
    private JLabel busquedaPrecioDesdeLbl;
    private JLabel busquedaPrecioHastaLbl;
    private JLabel busquedaVentaFinanciadaLbl;
    private JLabel busquedaVentaFinanciacionEnProcesoLbl;

    protected JTextField busquedaIdVenta;
    protected JTextField busquedaDNICliente;
    protected JTextField busquedaFechaDesde;
    protected JTextField busquedaFechaHasta;
    protected JTextField busquedaPrecioDesde;
    protected JTextField busquedaPrecioHasta;
    protected JComboBox<Boolean> ventaFinanciadaComboBox;
    protected JComboBox<Boolean> ventaFinanciacionTerminadaComboBox;
    
    public VistaListadoVentasGUI(final VistaListadoVentasGUIController controller, final ElementoTiendaGenerico elementoPadre) {
        super(controller, elementoPadre);
    }
    
    @Override
    protected void fillSearchElementsPanel(final JPanel searchElementsPanel) {
        this.busquedaIdVenta = new JTextField(10);
        (this.busquedaIdVentaLbl = new JLabel("ID Venta:")).setPreferredSize(new Dimension(80, 20));
        searchElementsPanel.add(this.busquedaIdVentaLbl);
        searchElementsPanel.add(this.busquedaIdVenta);
        searchElementsPanel.add(this.createSeparator(40));
        this.busquedaDNICliente = new JTextField(10);
        (this.busquedaDNIClienteLbl = new JLabel("DNI Cliente:")).setPreferredSize(new Dimension(90, 20));
        searchElementsPanel.add(this.busquedaDNIClienteLbl);
        searchElementsPanel.add(this.busquedaDNICliente);
        searchElementsPanel.add(this.createSeparator(40));
        (this.ventaFinanciadaComboBox = GUIUtils.getBooleanComboBox(true)).setPreferredSize(new Dimension(new Double(this.ventaFinanciadaComboBox.getPreferredSize().getWidth()).intValue(), 20));
        (this.busquedaVentaFinanciadaLbl = new JLabel("Financiado:")).setPreferredSize(new Dimension(82, 20));
        searchElementsPanel.add(this.busquedaVentaFinanciadaLbl);
        searchElementsPanel.add(this.ventaFinanciadaComboBox);
        searchElementsPanel.add(this.createSeparator(40));
        (this.ventaFinanciacionTerminadaComboBox = GUIUtils.getBooleanComboBox(true)).setPreferredSize(new Dimension(new Double(this.ventaFinanciacionTerminadaComboBox.getPreferredSize().getWidth()).intValue(), 20));
        (this.busquedaVentaFinanciacionEnProcesoLbl = new JLabel("Financiación terminada:")).setPreferredSize(new Dimension(161, 20));
        searchElementsPanel.add(this.busquedaVentaFinanciacionEnProcesoLbl);
        searchElementsPanel.add(this.ventaFinanciacionTerminadaComboBox);
        searchElementsPanel.add(this.createSeparator(40));
        this.busquedaFechaDesde = new JTextField(10);
        (this.busquedaFechaDesdeLbl = new JLabel("Fecha desde (dd/mm/yyyy):")).setPreferredSize(new Dimension(160, 20));
        searchElementsPanel.add(this.busquedaFechaDesdeLbl);
        searchElementsPanel.add(this.busquedaFechaDesde);
        searchElementsPanel.add(this.createSeparator(6));
        this.busquedaFechaHasta = new JTextField(10);
        (this.busquedaFechaHastaLbl = new JLabel("hasta (dd/mm/yyyy):")).setPreferredSize(new Dimension(130, 20));
        searchElementsPanel.add(this.busquedaFechaHastaLbl);
        searchElementsPanel.add(this.busquedaFechaHasta);
        searchElementsPanel.add(this.createSeparator(40));
        this.busquedaPrecioDesde =  new JTextField(10);
        (this.busquedaPrecioDesdeLbl = new JLabel("Precio desde:")).setPreferredSize(new Dimension(80, 20));
        this.busquedaPrecioHasta = new JTextField(10);
        (this.busquedaPrecioHastaLbl = new JLabel("hasta:")).setPreferredSize(new Dimension(40, 20));
        searchElementsPanel.add(this.busquedaPrecioDesdeLbl);
        searchElementsPanel.add(this.busquedaPrecioDesde);
        searchElementsPanel.add(this.createSeparator(6));
        searchElementsPanel.add(this.busquedaPrecioHastaLbl);
        searchElementsPanel.add(this.busquedaPrecioHasta);        
    }
    
    @Override
    protected JTable createElementsTable(final List<Venta> ventas) {
        return GUITableUtils.getVentasTable(ventas);
    }
    
    @Override
    protected void emptySearchFilters() {
        this.busquedaIdVenta.setText(null);
        this.busquedaDNICliente.setText(null);
        this.busquedaPrecioDesde.setText(null);
        this.busquedaPrecioHasta.setText(null);
        this.busquedaFechaDesde.setText(null);
        this.busquedaFechaHasta.setText(null);
        this.ventaFinanciadaComboBox.setSelectedIndex(-1);
        this.ventaFinanciacionTerminadaComboBox.setSelectedIndex(-1);
    }
    
    @Override
    protected BusquedaVenta createElementoBusqueda() throws FilterFormatException, Exception {
        final BusquedaVenta busqueda = new BusquedaVenta();
        if(this.busquedaIdVenta.getText() != null && !this.busquedaIdVenta.getText().isEmpty()) {
        	try {
        		busqueda.setIdVenta(Long.parseLong(busquedaIdVenta.getText()));
        	} catch(Exception e) {
        		throw new FilterFormatException("El formato del campo 'ID Venta' debe ser numérico entero");
        	}
        }
        busqueda.setDNICliente(busquedaDNICliente.getText());
        if (this.busquedaFechaDesde.getText() != null && !this.busquedaFechaDesde.getText().isEmpty()) {
            try {
                busqueda.setFechaDesde(new SimpleDateFormat("dd/MM/yyyy").parse(this.busquedaFechaDesde.getText()));
            }
            catch (Exception ex) {
                throw new FilterFormatException("El formato del campo 'Fecha Desde' no es correcto, debe ser dd/mm/yyyy");
            }
        }
        if (this.busquedaFechaHasta.getText() != null && !this.busquedaFechaHasta.getText().isEmpty()) {
            try {
                busqueda.setFechaHasta(new SimpleDateFormat("dd/MM/yyyy").parse(this.busquedaFechaHasta.getText()));
            }
            catch (Exception ex) {
                throw new FilterFormatException("El formato del campo 'Fecha Hasta' no es correcto, debe ser dd/mm/yyyy");
            }
        }
        if (busqueda.getFechaDesde() != null && busqueda.getFechaDesde().compareTo(Calendar.getInstance().getTime()) > 0) {
            throw new FilterFormatException("El valor del campo 'Fecha Desde' no puede ser mayor a hoy.");
        }
        if (busqueda.getFechaHasta() != null && busqueda.getFechaHasta().compareTo(Calendar.getInstance().getTime()) > 0) {
            throw new FilterFormatException("El valor del campo 'Fecha Hasta' no puede ser mayor a hoy.");
        }
        if (busqueda.getFechaDesde() != null && busqueda.getFechaHasta() != null && busqueda.getFechaDesde().compareTo(busqueda.getFechaHasta()) > 0) {
            throw new FilterFormatException("El valor del campo 'Fecha Desde' no puede ser posterior al valor del campo 'Fecha Hasta'.");
        }
        if (this.busquedaPrecioDesde.getText() != null && !this.busquedaPrecioDesde.getText().isEmpty()) {
            try {
                busqueda.setPrecioDesde(Double.parseDouble(this.busquedaPrecioDesde.getText()));
            }
            catch (Exception ex) {
                throw new FilterFormatException("El formato del campo 'Precio desde' debe ser numérico");
            }
        }
        if (this.busquedaPrecioHasta.getText() != null && !this.busquedaPrecioHasta.getText().isEmpty()) {
            try {
                busqueda.setPrecioHasta(Double.parseDouble(this.busquedaPrecioHasta.getText()));
            }
            catch (Exception ex) {
                throw new FilterFormatException("El formato del campo 'Precio Hasta' debe ser numérico");
            }
        }
        if (busqueda.getPrecioDesde() != null && busqueda.getPrecioHasta() != null && busqueda.getPrecioDesde().compareTo(busqueda.getPrecioHasta()) > 0) {
            throw new FilterFormatException("El valor del campo 'Precio Desde' no puede ser posterior al valor del campo 'Precio Hasta'.");
        }
        busqueda.setFinanciado((Boolean)this.ventaFinanciadaComboBox.getSelectedItem());
        if(Boolean.FALSE.equals((Boolean)this.ventaFinanciadaComboBox.getSelectedItem())) {
        	busqueda.setFinanciacionTerminada((Boolean)this.ventaFinanciacionTerminadaComboBox.getSelectedItem());
        }
        return busqueda;
    }
    
    @Override
    protected String getListadoTitle() {
        return "Ventas";
    }
    
    @Override
    protected int getSearchPanelHeight() {
        return 54;
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
