package es.osalguero.tiendaelect.gui.view.edicion;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import es.osalguero.tiendaelect.constants.SeccionTienda;
import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.controller.edicion.VistaEdicionVentaGUIController;
import es.osalguero.tiendaelect.gui.exception.FilterFormatException;
import es.osalguero.tiendaelect.gui.util.GUITableUtils;
import es.osalguero.tiendaelect.gui.util.GUIUtils;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.negocio.ArticuloVenta;
import es.osalguero.tiendaelect.modelo.negocio.Venta;
import es.osalguero.tiendaelect.modelo.persona.Cliente;
import es.osalguero.tiendaelect.modelo.producto.Producto;

public class VistaEdicionVentaGUI extends VistaAbstractaEdicionGUI<VistaEdicionVentaGUIController, Venta> implements ListSelectionListener, ItemListener
{
	protected JLabel datosClienteLbl;
    protected JLabel dniClienteLbl;
    protected JTextField dniCliente;
    protected JScrollPane clientePane;
    protected JTable clienteTable;
    protected JButton searchClienteBtn;
   
    protected JPanel datosVentaPanel;
    protected JLabel datosVentaLbl;
    protected JLabel ventaIdLbl;
    protected JLabel descVentaIdLbl;
    protected JLabel fechaVentaLbl;
    protected JLabel descFechaVentaLbl;
    protected JLabel listadoArticulosLbl;
    protected JLabel importeVentaLbl;
    protected JLabel descImporteVentaLbl;
    protected JScrollPane articulosPane;
    protected JTable articulosTable;
    protected JButton addArticuloBtn;
    protected JButton removeArticuloBtn;
    
    protected JDialog addArticuloDialog;
    protected JPanel addArticuloPanel;
    protected JLabel addArticuloLbl;
    protected JLabel cantidadLbl;
    protected JTextField cantidad;
    protected JScrollPane productosPane;
    protected JTable productosTable;
    protected JButton okAddArticulo;
    protected JButton cancelAddArticulo;
    protected JComboBox<SeccionTienda> seccionProducto;
    protected JComboBox<String> marcaProducto;
    protected JComboBox<String> modeloProducto;
    protected JLabel seccionProductoLbl;
    protected JLabel marcaProductoLbl;
    protected JLabel modeloProductoLbl;
    protected JPanel productoButtonsPane;
    
    protected JButton imprimirTicketBtn;
    
    protected Cliente cliente;
    protected List<ArticuloVenta> articulosVenta;
    protected Date fechaVenta;
    protected Producto producto;
    
    
    public VistaEdicionVentaGUI(final VistaEdicionVentaGUIController controller, final Venta elementoEdicion, final TipoEdicionEnum tipoEdicion) {
        super(controller, elementoEdicion, tipoEdicion, null);
    }
    
    @Override
    protected String getViewTitle() {
        if (TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            return "Nueva Venta";
        }
        String title = "Edición Venta";
        if (this.elementoEdicion != null && ((Venta)this.elementoEdicion).getId() != null) {
            title = title.concat(" ID ").concat(String.valueOf(((Venta)this.elementoEdicion).getId()));
        }
        return title;
    }
    
    @Override
    protected void fillEditPanel(final JPanel editFieldsPanel) {
    	this.articulosVenta = new ArrayList<ArticuloVenta>();
    	String importe = "";
        if (this.elementoEdicion != null) {
            this.cliente = ((Venta)this.elementoEdicion).getCliente();
            this.articulosVenta = ((Venta)this.elementoEdicion).getArticulosVenta();
            this.fechaVenta = ((Venta)this.elementoEdicion).getFecha();
            try {
            	importe = String.valueOf(TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getImporteVenta(((Venta)this.elementoEdicion)));
            } catch(Exception e) {
            	this.getController().showError("Los datos de la venta no son consistentes, por favor revise el log de la aplicación.");
            }
            
        } else {
        	this.fechaVenta = Calendar.getInstance().getTime();
        	importe = "0";
        }
        editFieldsPanel.setLayout(new FlowLayout(0, 6, 6));
        this.datosClienteLbl = new JLabel("Datos del cliente");
        this.dniClienteLbl = new JLabel("DNI:");
        this.datosVentaLbl = new JLabel("Datos de la venta:");
        this.fechaVentaLbl = new JLabel("Fecha:");
        this.importeVentaLbl = new JLabel("Importe:");
        this.ventaIdLbl = new JLabel("Id:");
        this.descVentaIdLbl = new JLabel(this.elementoEdicion != null ? String.valueOf(((Venta)this.elementoEdicion).getId()) : "");
        this.listadoArticulosLbl = new JLabel("Artículos:");
        this.addArticuloLbl = new JLabel("Producto");
        this.cantidadLbl = new JLabel("Cantidad:");
        this.seccionProductoLbl = new JLabel("Sección:");
        this.marcaProductoLbl = new JLabel("Marca:");
        this.modeloProductoLbl = new JLabel("Modelo:");
        this.descFechaVentaLbl = new JLabel(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(this.fechaVenta));
        this.descImporteVentaLbl = new JLabel(importe);
        this.descImporteVentaLbl.setPreferredSize(new Dimension(60, 20));
        this.descImporteVentaLbl.setHorizontalAlignment(JLabel.RIGHT);
        this.datosClienteLbl.setPreferredSize(new Dimension(160, 20));
        this.dniClienteLbl.setPreferredSize(new Dimension(80, 20));
        this.datosVentaLbl.setPreferredSize(new Dimension(160, 20));
        this.fechaVentaLbl.setPreferredSize(new Dimension(60, 20));
        this.ventaIdLbl.setPreferredSize(new Dimension(30, 20));
        this.importeVentaLbl.setPreferredSize(new Dimension(60, 20));
        this.descVentaIdLbl.setPreferredSize(new Dimension(120, 20));
        this.descFechaVentaLbl.setPreferredSize(new Dimension(120, 20));
        this.listadoArticulosLbl.setPreferredSize(new Dimension(80, 20));
        this.addArticuloLbl.setPreferredSize(new Dimension(400, 20));
        this.cantidadLbl.setPreferredSize(new Dimension(80, 20));
        this.seccionProductoLbl.setPreferredSize(new Dimension(100, 20));
        this.marcaProductoLbl.setPreferredSize(new Dimension(100, 20));
        this.modeloProductoLbl.setPreferredSize(new Dimension(100, 20));
        (this.dniCliente = new JTextField(8)).setDisabledTextColor(Color.BLACK);
        (this.cantidad = new JTextField(8)).setDisabledTextColor(Color.BLACK);
        this.searchClienteBtn = GUIUtils.createSearchIconButton();
        this.searchClienteBtn.addActionListener(this);
        this.addArticuloBtn = GUIUtils.createIconButton("Añadir artículo", "add.png");
        this.addArticuloBtn.addActionListener(this);
        this.removeArticuloBtn = GUIUtils.createIconButton("Eliminar artículo", "delete.png");
        this.removeArticuloBtn.addActionListener(this);
        this.imprimirTicketBtn = GUIUtils.createButton("Imprimir factura", "print.png");
        this.imprimirTicketBtn.addActionListener(this);
        this.seccionProducto = GUIUtils.getSeccionTiendaComboBox(true);
        this.marcaProducto = GUIUtils.getStringComboBox(this.getMarcasProductos((SeccionTienda)seccionProducto.getSelectedItem()), true);
        this.modeloProducto = GUIUtils.getStringComboBox(this.getModelosProductos((SeccionTienda)seccionProducto.getSelectedItem(), (String)marcaProducto.getSelectedItem()), true);
        this.seccionProducto.addItemListener(this);
        this.marcaProducto.addItemListener(this);
        this.modeloProducto.addItemListener(this);
        this.cancelAddArticulo = GUIUtils.createButton("Cancelar", "delete.png");
        this.okAddArticulo = GUIUtils.createApplyButton();
        
        editFieldsPanel.add(this.datosClienteLbl);
        editFieldsPanel.add(this.getLineSeparator(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 12));
        editFieldsPanel.add(this.dniClienteLbl);
        editFieldsPanel.add(this.dniCliente);
        editFieldsPanel.add(this.searchClienteBtn);
        editFieldsPanel.add(this.createSeparator(600));
        this.clienteTable = this.getClienteTable();
        (this.clientePane = new JScrollPane(this.clienteTable)).setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 20, 41));
        this.clientePane.getViewport().setBackground(Color.WHITE);
        editFieldsPanel.add(this.clientePane);
        this.datosVentaLbl.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));
        editFieldsPanel.add(this.datosVentaLbl);
        editFieldsPanel.add(this.createSeparator(10));
        editFieldsPanel.add(this.getLineSeparator(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 12));
        (this.datosVentaPanel = new JPanel(new FlowLayout(0, 0, 0))).setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 12, 320));
        this.datosVentaPanel.add(this.ventaIdLbl);
        this.datosVentaPanel.add(this.descVentaIdLbl);
        this.datosVentaPanel.add(this.createSeparator(18));
        this.datosVentaPanel.add(this.fechaVentaLbl);
        this.datosVentaPanel.add(this.descFechaVentaLbl);
        this.datosVentaPanel.add(this.createSeparator(18));
        this.datosVentaPanel.add(this.importeVentaLbl);
        this.datosVentaPanel.add(this.descImporteVentaLbl);
        this.datosVentaPanel.add(this.createSeparator(460));
        this.datosVentaPanel.add(listadoArticulosLbl);
        this.articulosTable = this.getArticulosVentaTable();
        this.articulosTable.getSelectionModel().addListSelectionListener(this);
        (this.articulosPane = new JScrollPane(this.articulosTable)).setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 20, 280));
        this.articulosPane.getViewport().setBackground(Color.WHITE);
        this.datosVentaPanel.add(this.articulosPane);
        editFieldsPanel.add(this.datosVentaPanel);
        editFieldsPanel.add(this.addArticuloBtn);
        editFieldsPanel.add(this.removeArticuloBtn);
        this.bottomPane.add(this.imprimirTicketBtn);
        ((SpringLayout)this.bottomPane.getLayout()).putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imprimirTicketBtn, 0, SpringLayout.HORIZONTAL_CENTER, this.bottomPane);
        this.addArticuloPanel = new JPanel();
        this.addArticuloPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 6));
        this.addArticuloPanel.setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 12, 500));
        this.addArticuloPanel.setOpaque(true);
        this.addArticuloPanel.add(this.addArticuloLbl);
        JSeparator addArticuloSeparator = this.getLineSeparator(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 12);
        this.addArticuloPanel.add(addArticuloSeparator);
        this.addArticuloPanel.add(this.seccionProductoLbl);
        this.addArticuloPanel.add(this.seccionProducto);
        this.addArticuloPanel.add(this.createSeparator(30));
        this.addArticuloPanel.add(this.marcaProductoLbl);
        this.addArticuloPanel.add(this.marcaProducto);
        this.addArticuloPanel.add(this.createSeparator(30));
        this.addArticuloPanel.add(this.modeloProductoLbl);
        this.addArticuloPanel.add(this.modeloProducto);
        this.productosTable = this.getProductoTable(new ArrayList<Producto>());
        (this.productosPane = new JScrollPane(this.productosTable)).setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 20, 360));
        this.productosPane.getViewport().setBackground(Color.WHITE);
        this.addArticuloPanel.add(productosPane);
        this.addArticuloPanel.add(cantidadLbl);
        this.addArticuloPanel.add(cantidad);
        (this.productoButtonsPane = new JPanel()).setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 20, 40));
        this.productoButtonsPane.add(okAddArticulo);
        this.productoButtonsPane.add(cancelAddArticulo);
        this.productoButtonsPane.setOpaque(false);
        final SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.EAST, this.okAddArticulo, 0, SpringLayout.EAST, this.productoButtonsPane);
        layout.putConstraint(SpringLayout.WEST, this.cancelAddArticulo, 0, SpringLayout.WEST, this.productoButtonsPane);
        this.productoButtonsPane.setLayout(layout);
        this.addArticuloPanel.add(productoButtonsPane);
        (this.addArticuloDialog = new JDialog(SwingUtilities.getWindowAncestor(this.getContainer()), "Añadir artículo", Dialog.ModalityType.APPLICATION_MODAL)).setResizable(false);
        this.addArticuloDialog.setAlwaysOnTop(true);
        this.okAddArticulo.addActionListener(this);
        this.cancelAddArticulo.addActionListener(this);
        this.addArticuloDialog.add(this.addArticuloPanel);
        this.addArticuloDialog.pack();
    }
    
    @Override
    protected void validate() throws FilterFormatException, Exception {
        if (this.cliente == null) {
            throw new FilterFormatException("Debe seleccionarse un cliente.");
        }
        if (this.articulosVenta == null || this.articulosVenta.isEmpty()) {
            throw new FilterFormatException("La venta debe contener al menos un artículo.");
        }
    }
    
    @Override
    protected void modificarElemento() throws Exception {
    	((Venta)this.elementoEdicion).setArticulosVenta(this.articulosVenta);
    	((VistaEdicionVentaGUIController)this.getController()).modificarVenta((Venta)this.elementoEdicion);
    }
    
    @Override
    protected void manageTipoEdicion() {
    	final boolean nuevo = TipoEdicionEnum.NUEVO.equals(this.tipoEdicion);
    	final boolean modificar = TipoEdicionEnum.EDITAR.equals(this.tipoEdicion);
    	final boolean detalle = TipoEdicionEnum.DETALLE.equals(this.tipoEdicion);
        this.dniCliente.setEnabled(nuevo);
        this.searchClienteBtn.setEnabled(nuevo);
        this.searchClienteBtn.setVisible(nuevo);
        this.addArticuloBtn.setVisible(nuevo || modificar);
        this.removeArticuloBtn.setVisible(nuevo || modificar);
        this.imprimirTicketBtn.setVisible(detalle);
        this.addArticuloBtn.setEnabled(true);
        this.removeArticuloBtn.setEnabled((nuevo || modificar) && this.articulosTable.getSelectedRow() > -1);
        this.manageViewData();
    }
    
    @Override
    protected Venta crearNuevoElemento() throws Exception {
        final Venta venta = new Venta();
        venta.setCliente(this.cliente);
        venta.setArticulosVenta(this.articulosVenta);
        venta.setFecha(this.fechaVenta);
        return venta;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource().equals(this.searchClienteBtn)) {
            if (this.dniCliente.getText() == null || this.dniCliente.getText().trim().isEmpty()) {
                ((VistaEdicionVentaGUIController)this.getController()).showError("Debe informarse el DNI del Cliente.");
            }
            else {
                try {
                    final Cliente cliente = ((VistaEdicionVentaGUIController)this.getController()).getCliente(this.dniCliente.getText().trim());
                    if (cliente == null) {
                    	((VistaEdicionVentaGUIController)this.getController()).showNotification("No se ha encontrado ningún cliente con el DNI: ".concat(this.dniCliente.getText().trim()));
                    }
                    else {
                        this.cliente = cliente;
                        ((DefaultTableModel)this.clienteTable.getModel()).setRowCount(0);
                        final Object[] elementData = GUITableUtils.getDataFromElement(cliente);
                        ((DefaultTableModel)this.clienteTable.getModel()).addRow(elementData);
                    }
                    this.manageViewData();
                    this.getContainer().revalidate();
                    this.getContainer().repaint();
                }
                catch (Exception ex) {
                	((VistaEdicionVentaGUIController)this.getController()).showError(ex.getMessage());
                }
            }
        }
        else if (e.getSource().equals(this.imprimirTicketBtn)) {
        	((VistaEdicionVentaGUIController)this.getController()).imprimirFactura((Venta)this.elementoEdicion);
            this.manageTipoEdicion();
            this.getContainer().revalidate();
            this.getContainer().repaint();
        }
        else if (e.getSource().equals(this.addArticuloBtn)) {
            this.cantidad.setText(null);
            this.producto = null;
            this.emptyProductoSearchFields();
            this.addArticuloDialog.setLocation(SwingUtilities.getWindowAncestor(this.getContainer()).getX(), SwingUtilities.getWindowAncestor(this.getContainer()).getY());
            this.addArticuloDialog.setVisible(true);
        }
        else if(e.getSource().equals(this.removeArticuloBtn)) {
        	if(this.articulosTable.getSelectedRow() > -1) {
                try {
                    this.articulosVenta.remove(this.articulosVenta.get(this.articulosTable.convertRowIndexToModel(this.articulosTable.getSelectedRow())));
                    ((DefaultTableModel)this.articulosTable.getModel()).removeRow(this.articulosTable.getSelectedRow());
                }
                catch (Exception ex) {
                	logger.log(Level.SEVERE, "Error recuperando elemento del listado para borrar", ex);
                    this.getController().showError("Se ha producido un error intentando recuperar los datos del elemento a eliminar.\nPor favor, revise los logs de la aplicación.");
                }
                this.manageTipoEdicion();
                this.getContainer().revalidate();
                this.getContainer().repaint();
        	}
        }
        else if (e.getSource().equals(this.okAddArticulo)) {
            if(this.productosTable.getSelectedRow() < 0) {
            	this.showDialogMessage("Debe seleccionar un producto de la lista");
            	return;
            }
            if(this.cantidad.getText().trim().isEmpty()) {
            	this.showDialogMessage("Debe indicar la cantidad de productos a añadir");
            	return;
            }
            int cantidad = -1;
            try {
            	cantidad = Integer.parseInt(this.cantidad.getText().trim());
            } catch(Exception ex) {
            	this.showDialogMessage("El formato del campo 'Cantidad' debe ser numérico");
            	return;
            }
            if(cantidad <= 0) {
            	this.showDialogMessage("Debe indicar una cantidad mayor que 0");
            	return;
            }
            else {
            	Long idProducto = null;
            	try {
            		idProducto = (Long)((DefaultTableModel)this.productosTable.getModel()).getValueAt(this.productosTable.convertRowIndexToModel(this.productosTable.getSelectedRow()), 0);
            	} catch(Exception ex) {
            		this.showDialogMessage("No se han podido recuperar los datos del producto seleccionado");
            		return;
            	}
            	Producto producto = null;
            	for(Producto productoTienda : TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getProductosTienda()) {
            		if(productoTienda.getId().equals(idProducto)) {
            			producto = productoTienda;
            		}
            	}
            	if(producto == null) {
            		this.showDialogMessage("No se han podido recuperar los datos del producto seleccionado");
            		return;
            	}
            	boolean articuloExiste = false;
        		for(ArticuloVenta articuloVenta : this.articulosVenta) {
            		if(articuloVenta.getProducto().equals(producto)) {
            			if(producto.getCantidad() < (articuloVenta.getCantidad() + cantidad)) {
            				this.showDialogMessage("No existe stock suficiente del producto indicado");
            				return;
            			}
            			articuloVenta.setCantidad(articuloVenta.getCantidad() + cantidad);
            			articuloExiste = true;
            			break;
            		}
            	}
            	
            	if(!articuloExiste) {
            		if(producto.getCantidad() < cantidad) {
            			this.showDialogMessage("No existe stock suficiente del producto indicado");
            			return;
            		}
            		ArticuloVenta articuloVenta = new ArticuloVenta();
            		articuloVenta.setCantidad(cantidad);
            		articuloVenta.setProducto(producto);
            		articuloVenta.setPrecio(producto.getPrecio());
            		this.articulosVenta.add(articuloVenta);
            	}
            	BigDecimal importe = new BigDecimal(0);
            	((DefaultTableModel)this.articulosTable.getModel()).setRowCount(0);
            	for(ArticuloVenta articuloVenta : this.articulosVenta) {
            		((DefaultTableModel)this.articulosTable.getModel()).addRow(GUITableUtils.getDataFromElement(articuloVenta));
            		importe = importe.add(new BigDecimal(articuloVenta.getPrecio()).multiply(new BigDecimal(articuloVenta.getCantidad())));
            	}
            	this.descImporteVentaLbl.setText(String.valueOf(importe.setScale(2, RoundingMode.HALF_UP).doubleValue()));
            	this.showDialogSuccesMessage("El artículo se ha añadido correctamente");
            	this.addArticuloDialog.setVisible(false);
            	this.manageTipoEdicion();
            	this.getContainer().revalidate();
            	this.getContainer().repaint();
            }
        }
        else if(e.getSource().equals(cancelAddArticulo)) {
        	this.addArticuloDialog.setVisible(false);
        	this.manageTipoEdicion();
        	this.getContainer().revalidate();
        	this.getContainer().repaint();
        }
        else {
            super.actionPerformed(e);
        }
    }
    
    private JTable getClienteTable() {
        final JTable clienteTable = GUITableUtils.getClientesTable(new ArrayList<Cliente>(Arrays.asList(this.cliente)));
        ((DefaultTableCellRenderer)clienteTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(2);
        clienteTable.getTableHeader().setFont(new Font(clienteTable.getTableHeader().getFont().getName(), 1, 12));
        clienteTable.setIntercellSpacing(new Dimension(6, 1));
        clienteTable.setRowHeight(18);
        clienteTable.setAutoResizeMode(0);
        clienteTable.setAutoCreateRowSorter(true);
        clienteTable.setRowSelectionAllowed(true);
        clienteTable.setSelectionMode(0);
        clienteTable.setShowGrid(true);
        clienteTable.setAutoResizeMode(0);
        return clienteTable;
    }
    
    private JTable getArticulosVentaTable() {
        final JTable articulosVentaTable = GUITableUtils.getArticulosVentaTable(this.articulosVenta);
        ((DefaultTableCellRenderer)articulosVentaTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(2);
        articulosVentaTable.getTableHeader().setFont(new Font(articulosVentaTable.getTableHeader().getFont().getName(), 1, 12));
        articulosVentaTable.setIntercellSpacing(new Dimension(6, 1));
        articulosVentaTable.setRowHeight(18);
        articulosVentaTable.setAutoResizeMode(0);
        articulosVentaTable.setAutoCreateRowSorter(true);
        articulosVentaTable.setRowSelectionAllowed(true);
        articulosVentaTable.setSelectionMode(0);
        articulosVentaTable.setShowGrid(true);
        articulosVentaTable.setAutoResizeMode(0);
        return articulosVentaTable;
    }
    
    private JTable getProductoTable(List<Producto> productos) {
    	final JTable productoTable = GUITableUtils.getProductosTable(productos);
    	((DefaultTableCellRenderer)productoTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(2);
    	productoTable.getTableHeader().setFont(new Font(productoTable.getTableHeader().getFont().getName(), 1, 12));
    	productoTable.setIntercellSpacing(new Dimension(6, 1));
    	productoTable.setRowHeight(18);
    	productoTable.setAutoResizeMode(0);
    	productoTable.setAutoCreateRowSorter(true);
    	productoTable.setRowSelectionAllowed(true);
    	productoTable.setSelectionMode(0);
    	productoTable.setShowGrid(true);
    	productoTable.setAutoResizeMode(0);
    	return productoTable;
    }
    
    private JSeparator getLineSeparator(final int width) {
        final JSeparator lineSeparator = new JSeparator();
        lineSeparator.setPreferredSize(new Dimension(width, 1));
        lineSeparator.setForeground(Color.BLACK);
        return lineSeparator;
    }
    
    private void manageViewData() {
        if (!TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            this.panelLabelEdicion.setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 12, 35));
        }
        this.dniCliente.setVisible(this.tipoEdicion.equals(TipoEdicionEnum.NUEVO));
        this.dniClienteLbl.setVisible(this.tipoEdicion.equals(TipoEdicionEnum.NUEVO));
        this.clientePane.setVisible(this.clienteTable.getModel().getRowCount() > 0);
        this.articulosPane.setVisible(true);
        if (this.elementoEdicion != null) {
            if (this.cliente != null) {
                this.dniCliente.setText(this.cliente.getDni());
            }
            else if (((Venta)this.elementoEdicion).getCliente() != null) {
                this.dniCliente.setText(((Venta)this.elementoEdicion).getCliente().getDni());
            }
        }
    }
    
    @Override
    protected VistasEnum getVistaAnterior() {
        return VistasEnum.VISTA_LISTADO_VENTAS;
    }
    
    @Override
    protected ElementoTiendaGenerico getElementoAnterior() {
        return this.elementoPadre;
    }

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource().equals(this.articulosTable.getSelectionModel())) {
			this.manageTipoEdicion();
			this.getContainer().revalidate();
			this.getContainer().repaint();
		}
	}
	
	public List<String> getMarcasProductos(SeccionTienda seccionTienda) {
		List<String> marcasProductos = new ArrayList<String>();
		List<Producto> productos = TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getProductosTienda();
		for(Producto producto : productos) {
			if(seccionTienda != null && !seccionTienda.equals(producto.getSeccionTienda())) {
				break;
			} else if(!marcasProductos.contains(producto.getMarca())){
				marcasProductos.add(producto.getMarca());
			}
		}
		return marcasProductos;
	}
	
	public List<String> getModelosProductos(SeccionTienda seccionTienda, String marcaProducto) {
		List<String> modelosProductos = new ArrayList<String>();
		List<Producto> productos = TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getProductosTienda();
		for(Producto producto : productos) {
			if((seccionTienda != null && !seccionTienda.equals(producto.getSeccionTienda()) || (marcaProducto != null && !marcaProducto.equals(producto.getMarca())))) {
				break;
			} else if(!modelosProductos.contains(producto.getModelo())){
				modelosProductos.add(producto.getModelo());
			}
		}
		return modelosProductos;
	}
    
	private void emptyProductoSearchFields() {
		this.seccionProducto.setSelectedItem(null);
		this.marcaProducto.setSelectedItem(null);
		this.modeloProducto.setSelectedItem(null);
		((DefaultTableModel)this.productosTable.getModel()).setRowCount(0);
		for(Producto producto : TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getProductosTienda()) {
			((DefaultTableModel)this.productosTable.getModel()).addRow(GUITableUtils.getDataFromElement(producto));
		}
	}
	
	private void showDialogMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this.addArticuloDialog, errorMessage, "Error", 0);
	}
	
	private void showDialogSuccesMessage(String message) {
		JOptionPane.showMessageDialog(this.addArticuloDialog, message, "Artículo añadido", -1);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		List<Producto> productosBusqueda = new ArrayList<Producto>();
		List<Producto> productosTienda = TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getProductosTienda();
		for(Producto producto : productosTienda) {
			if((seccionProducto.getSelectedItem() == null || seccionProducto.getSelectedItem().equals(producto.getSeccionTienda())) &&
					(marcaProducto.getSelectedItem() == null || marcaProducto.getSelectedItem().equals(producto.getMarca())) &&
					(modeloProducto.getSelectedItem() == null || modeloProducto.getSelectedItem().equals(producto.getModelo()))) {
				productosBusqueda.add(producto);
			}
		}
		((DefaultTableModel)this.productosTable.getModel()).setRowCount(0);
		for(Producto producto : productosBusqueda) {
			((DefaultTableModel)this.productosTable.getModel()).addRow(GUITableUtils.getDataFromElement(producto));
		}
	}
}
