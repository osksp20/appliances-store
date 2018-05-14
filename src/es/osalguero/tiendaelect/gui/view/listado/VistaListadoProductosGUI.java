package es.osalguero.tiendaelect.gui.view.listado;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import es.osalguero.tiendaelect.constants.SeccionTienda;
import es.osalguero.tiendaelect.gui.busqueda.BusquedaProducto;
import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.controller.listado.VistaListadoProductosGUIController;
import es.osalguero.tiendaelect.gui.exception.FilterFormatException;
import es.osalguero.tiendaelect.gui.util.GUITableUtils;
import es.osalguero.tiendaelect.gui.util.GUIUtils;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.producto.Producto;

public class VistaListadoProductosGUI extends VistaAbstractaListadoGUI<VistaListadoProductosGUIController, Producto, BusquedaProducto> implements ItemListener
{
	
	private JLabel busquedaMarcaProductoLbl;
    private JLabel busquedaModeloProductoLbl;
    private JLabel busquedaTipoProductoLbl;
    private JLabel busquedaPrecioProductoDesdeLbl;
    private JLabel busquedaPrecioProductoHastaLbl;
    private JLabel busquedaSeccionProductoLbl;
    private JLabel busquedaAltoElectrodomesticoDesdeLbl;
    private JLabel busquedaAltoElectrodomesticoHastaLbl;
    private JLabel busquedaAnchoElectrodomesticoDesdeLbl;
    private JLabel busquedaAnchoElectrodomesticoHastaLbl;
    private JLabel busquedaProfundidadElectrodomesticoDesdeLbl;
    private JLabel busquedaProfundidadElectrodomesticoHastaLbl;
    private JLabel busquedaColorElectrodomesticoLbl;
    private JTextField busquedaMarcaProducto;
    private JTextField busquedaModeloProducto;
    private JComboBox<String> busquedaTipoProducto;
    private JComboBox<String> busquedaColorElectrodomestico;
    private JTextField busquedaPrecioProductoDesde;
    private JTextField busquedaPrecioProductoHasta;
    private JComboBox<SeccionTienda> busquedaSeccionProducto;
    private JTextField busquedaAltoElectrodomesticoDesde;
    private JTextField busquedaAltoElectrodomesticoHasta;
    private JTextField busquedaAnchoElectrodomesticoDesde;
    private JTextField busquedaAnchoElectrodomesticoHasta;
    private JTextField busquedaProfundidadElectrodomesticoDesde;
    private JTextField busquedaProfundidadElectrodomesticoHasta;
    private JSeparator gamaBlancaSeparator1, gamaBlancaSeparator2, gamaBlancaSeparator3, gamaBlancaSeparator4, gamaBlancaSeparator5,
    		gamaBlancaSeparator6;

    public VistaListadoProductosGUI(final VistaListadoProductosGUIController controller, final ElementoTiendaGenerico elementoPadre) {
        super(controller, elementoPadre);
    }
    
    @Override
    protected BusquedaProducto createElementoBusqueda() throws FilterFormatException, Exception {
        final BusquedaProducto busqueda = new BusquedaProducto();
        busqueda.setMarca(this.busquedaMarcaProducto.getText());
        busqueda.setModelo(this.busquedaModeloProducto.getText());
        busqueda.setTipo((String)this.busquedaTipoProducto.getSelectedItem());
        if(this.busquedaPrecioProductoDesde.getText() != null && !this.busquedaPrecioProductoDesde.getText().isEmpty()) {
	        try {
	        	busqueda.setPrecioDesde(Float.parseFloat(this.busquedaPrecioProductoDesde.getText().replaceAll(",", "\\.")));
	        } catch(Exception e) {
	        	throw new FilterFormatException("El formato del campo 'Precio desde' debe ser numérico.");
	        }
        }
        if(this.busquedaPrecioProductoHasta.getText() != null && !this.busquedaPrecioProductoHasta.getText().isEmpty()) {
	        try {
	        	busqueda.setPrecioHasta(Float.parseFloat(this.busquedaPrecioProductoHasta.getText().replaceAll(",", "\\.")));
	        } catch(Exception e) {
	        	throw new FilterFormatException("El formato del campo 'Precio hasta' debe ser numérico.");
	        }
        }
        busqueda.setSeccion((SeccionTienda)this.busquedaSeccionProducto.getSelectedItem());
        if(SeccionTienda.GAMA_BLANCA.equals(this.busquedaSeccionProducto.getSelectedItem())) {
        	busqueda.setColor((String)this.busquedaColorElectrodomestico.getSelectedItem());
        	if(this.busquedaAltoElectrodomesticoDesde.getText() != null && !this.busquedaAltoElectrodomesticoDesde.getText().isEmpty()) {
        		try {
        			busqueda.setAltoDesde(Integer.parseInt(this.busquedaAltoElectrodomesticoDesde.getText()));
        		} catch(Exception e) {
        			throw new FilterFormatException("El formato del campo 'Alto desde' debe ser numérico.");
        		}
        	}
        	if(this.busquedaAltoElectrodomesticoHasta.getText() != null && !this.busquedaAltoElectrodomesticoHasta.getText().isEmpty()) {
        		try {
        			busqueda.setAltoHasta(Integer.parseInt(this.busquedaAltoElectrodomesticoHasta.getText()));
        		} catch(Exception e) {
        			throw new FilterFormatException("El formato del campo 'Alto hasta' debe ser numérico.");
        		}
        	}
        	if(this.busquedaAnchoElectrodomesticoDesde.getText() != null && !this.busquedaAnchoElectrodomesticoDesde.getText().isEmpty()) {
        		try {
        			busqueda.setAnchoDesde(Integer.parseInt(this.busquedaAnchoElectrodomesticoDesde.getText()));
        		} catch(Exception e) {
        			throw new FilterFormatException("El formato del campo 'Ancho desde' debe ser numérico.");
        		}
        	}
        	if(this.busquedaAnchoElectrodomesticoHasta.getText() != null && !this.busquedaAnchoElectrodomesticoHasta.getText().isEmpty()) {
        		try {
        			busqueda.setAnchoHasta(Integer.parseInt(this.busquedaAnchoElectrodomesticoHasta.getText()));
        		} catch(Exception e) {
        			throw new FilterFormatException("El formato del campo 'Ancho hasta' debe ser numérico.");
        		}
        	}
        	if(this.busquedaProfundidadElectrodomesticoDesde.getText() != null && !this.busquedaProfundidadElectrodomesticoDesde.getText().isEmpty()) {
        		try {
        			busqueda.setProfundidadDesde(Integer.parseInt(this.busquedaProfundidadElectrodomesticoDesde.getText()));
        		} catch(Exception e) {
        			throw new FilterFormatException("El formato del campo 'Profundidad desde' debe ser numérico.");
        		}
        	}
        	if(this.busquedaProfundidadElectrodomesticoHasta.getText() != null && !this.busquedaProfundidadElectrodomesticoHasta.getText().isEmpty()) {
        		try {
        			busqueda.setProfundidadHasta(Integer.parseInt(this.busquedaProfundidadElectrodomesticoHasta.getText()));
        		} catch(Exception e) {
        			throw new FilterFormatException("El formato del campo 'Profundidad hasta' debe ser numérico.");
        		}
        	}
        }
        return busqueda;
    }
    
    @Override
    protected void emptySearchFilters() {
        this.busquedaMarcaProducto.setText(null);
        this.busquedaModeloProducto.setText(null);
        this.busquedaTipoProducto.setSelectedIndex(-1);
        this.busquedaColorElectrodomestico.setSelectedIndex(-1);
        this.busquedaPrecioProductoDesde.setText(null);
        this.busquedaPrecioProductoHasta.setText(null);
        this.busquedaSeccionProducto.setSelectedIndex(-1);
        this.busquedaAltoElectrodomesticoHasta.setText(null);
        this.busquedaAltoElectrodomesticoDesde.setText(null);
        this.busquedaAnchoElectrodomesticoDesde.setText(null);
        this.busquedaAnchoElectrodomesticoHasta.setText(null);
        this.busquedaProfundidadElectrodomesticoDesde.setText(null);
        this.busquedaProfundidadElectrodomesticoHasta.setText(null);
        checkGamaBlancaItemsVisibility();
    }
    
    @Override
    protected void fillSearchElementsPanel(final JPanel searchElementsPanel) {
        this.busquedaSeccionProducto = GUIUtils.getSeccionTiendaComboBox(true);
        this.busquedaSeccionProducto.addItemListener(this);
        this.busquedaSeccionProductoLbl = new JLabel("Sección:");
        this.busquedaSeccionProductoLbl.setPreferredSize(new Dimension(70, 20));
        this.busquedaColorElectrodomestico = GUIUtils.getStringComboBox(null, true);
        this.busquedaColorElectrodomestico.addItemListener(this);
        this.busquedaColorElectrodomestico.setPreferredSize(new Dimension(100, 20));
        this.busquedaColorElectrodomesticoLbl = new JLabel("Color:");
        this.busquedaColorElectrodomesticoLbl.setPreferredSize(new Dimension(60, 20));
        this.busquedaTipoProducto = GUIUtils.getStringComboBox(null, true);
        this.busquedaTipoProducto.addItemListener(this);
        this.busquedaTipoProducto.setPreferredSize(new Dimension(180, 20));
        this.busquedaTipoProductoLbl = new JLabel("Tipo:");
        this.busquedaTipoProductoLbl.setPreferredSize(new Dimension(60, 20));
        this.busquedaMarcaProducto = new JTextField(14);
        this.busquedaMarcaProductoLbl = new JLabel("Marca:");
        this.busquedaMarcaProductoLbl.setPreferredSize(new Dimension(62, 20));
        this.busquedaModeloProducto = new JTextField(14);
        this.busquedaModeloProductoLbl = new JLabel("Modelo:");
        this.busquedaModeloProductoLbl.setPreferredSize(new Dimension(72, 20));
        this.busquedaPrecioProductoHasta = new JTextField(6);
        this.busquedaPrecioProductoHastaLbl = new JLabel("Precio hasta:");
        this.busquedaPrecioProductoHastaLbl.setPreferredSize(new Dimension(100, 20));
        this.busquedaPrecioProductoDesde = new JTextField(6);
        this.busquedaPrecioProductoDesdeLbl = new JLabel("Precio desde:");
        this.busquedaPrecioProductoDesdeLbl.setPreferredSize(new Dimension(104, 20));
        this.busquedaAltoElectrodomesticoDesde = new JTextField(5);
        this.busquedaAltoElectrodomesticoDesdeLbl = new JLabel("Alto desde (cm):");
        this.busquedaAltoElectrodomesticoDesdeLbl.setPreferredSize(new Dimension(100, 20));
        this.busquedaAltoElectrodomesticoHasta = new JTextField(5);
        this.busquedaAltoElectrodomesticoHastaLbl = new JLabel("Alto hasta (cm):");
        this.busquedaAltoElectrodomesticoHastaLbl.setPreferredSize(new Dimension(100, 20));
        this.busquedaAnchoElectrodomesticoDesde = new JTextField(4);
        this.busquedaAnchoElectrodomesticoDesdeLbl = new JLabel("Ancho desde (cm):");
        this.busquedaAnchoElectrodomesticoDesdeLbl.setPreferredSize(new Dimension(124, 20));
        this.busquedaAnchoElectrodomesticoHasta = new JTextField(4);
        this.busquedaAnchoElectrodomesticoHastaLbl = new JLabel("Ancho hasta (cm):");
        this.busquedaAnchoElectrodomesticoHastaLbl.setPreferredSize(new Dimension(124, 20));
        this.busquedaProfundidadElectrodomesticoDesde = new JTextField(4);
        this.busquedaProfundidadElectrodomesticoDesdeLbl = new JLabel("Profundidad desde (cm):");
        this.busquedaProfundidadElectrodomesticoDesdeLbl.setPreferredSize(new Dimension(160, 20));
        this.busquedaProfundidadElectrodomesticoHasta = new JTextField(4);
        this.busquedaProfundidadElectrodomesticoHastaLbl = new JLabel("Profundidad hasta (cm):");
        this.busquedaProfundidadElectrodomesticoHastaLbl.setPreferredSize(new Dimension(160, 20));
        this.gamaBlancaSeparator1 = this.createSeparator(26);
        this.gamaBlancaSeparator2 = this.createSeparator(26);
        this.gamaBlancaSeparator3 = this.createSeparator(26);
        this.gamaBlancaSeparator4 = this.createSeparator(26);
        this.gamaBlancaSeparator5 = this.createSeparator(26);
        this.gamaBlancaSeparator6 = this.createSeparator(26);
        
        searchElementsPanel.add(this.busquedaSeccionProductoLbl);
        searchElementsPanel.add(this.busquedaSeccionProducto);
        searchElementsPanel.add(this.createSeparator(26));
        searchElementsPanel.add(this.busquedaTipoProductoLbl);
        searchElementsPanel.add(this.busquedaTipoProducto);
        searchElementsPanel.add(this.createSeparator(26));
        searchElementsPanel.add(this.busquedaMarcaProductoLbl);
        searchElementsPanel.add(this.busquedaMarcaProducto);
        searchElementsPanel.add(this.createSeparator(26));
        searchElementsPanel.add(this.busquedaModeloProductoLbl);
        searchElementsPanel.add(this.busquedaModeloProducto);

        searchElementsPanel.add(this.busquedaPrecioProductoDesdeLbl);
        searchElementsPanel.add(this.busquedaPrecioProductoDesde);
        searchElementsPanel.add(this.createSeparator(26));
        searchElementsPanel.add(this.busquedaPrecioProductoHastaLbl);
        searchElementsPanel.add(this.busquedaPrecioProductoHasta);
        searchElementsPanel.add(this.gamaBlancaSeparator1);
        searchElementsPanel.add(this.busquedaColorElectrodomesticoLbl);
        searchElementsPanel.add(this.busquedaColorElectrodomestico);
        searchElementsPanel.add(this.gamaBlancaSeparator2);
        searchElementsPanel.add(this.busquedaAltoElectrodomesticoDesdeLbl);
        searchElementsPanel.add(this.busquedaAltoElectrodomesticoDesde);
        searchElementsPanel.add(this.gamaBlancaSeparator3);
        searchElementsPanel.add(this.busquedaAltoElectrodomesticoHastaLbl);
        searchElementsPanel.add(this.busquedaAltoElectrodomesticoHasta);

        searchElementsPanel.add(this.busquedaAnchoElectrodomesticoDesdeLbl);
        searchElementsPanel.add(this.busquedaAnchoElectrodomesticoDesde);
        searchElementsPanel.add(this.gamaBlancaSeparator4);
        searchElementsPanel.add(this.busquedaAnchoElectrodomesticoHastaLbl);
        searchElementsPanel.add(this.busquedaAnchoElectrodomesticoHasta);
        searchElementsPanel.add(this.gamaBlancaSeparator5);
        searchElementsPanel.add(this.busquedaProfundidadElectrodomesticoDesdeLbl);
        searchElementsPanel.add(this.busquedaProfundidadElectrodomesticoDesde);
        searchElementsPanel.add(this.gamaBlancaSeparator6);
        searchElementsPanel.add(this.busquedaProfundidadElectrodomesticoHastaLbl);
        searchElementsPanel.add(this.busquedaProfundidadElectrodomesticoHasta);
        
        checkGamaBlancaItemsVisibility();
    }
    
    @Override
    protected JTable createElementsTable(final List<Producto> productos) {
        return GUITableUtils.getProductosTable(productos);
    }
    
    @Override
    protected String getListadoTitle() {
        return "Productos";
    }
    
    @Override
    protected int getSearchPanelHeight() {
        return 80;
    }
    
    @Override
    protected String getDeleteWarningMessage() {
        return "¿Está seguro de eliminar el producto seleccionado?.\nNo podrá recuperarse y además se elimanarán las reparaciones finalizadas o no iniciadas asignadas al producto.";
    }
    
    @Override
    protected VistasEnum getVistaAnterior() {
        return VistasEnum.VISTA_PRINCIPAL;
    }
    
    @Override
    protected ElementoTiendaGenerico getElementoAnterior() {
        return null;
    }
    
    @Override
    public void itemStateChanged(final ItemEvent e) {
    	checkGamaBlancaItemsVisibility();
        if(e.getSource().equals(this.busquedaSeccionProducto)) {
        	if(this.busquedaSeccionProducto.getSelectedItem() == null) {
        		this.busquedaTipoProducto.removeAllItems();
        		this.busquedaColorElectrodomestico.removeAllItems();
        	} else {
        		if(SeccionTienda.GAMA_BLANCA.equals(this.busquedaSeccionProducto.getSelectedItem())) {
        			this.busquedaColorElectrodomestico.removeAllItems();
        			for(String color : TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getColoresElectrodomestico()) {
        				this.busquedaColorElectrodomestico.addItem(color);
        			}
        			this.busquedaColorElectrodomestico.insertItemAt(null, 0);
        			this.busquedaColorElectrodomestico.setSelectedItem(null);
        		}
        		this.busquedaTipoProducto.removeAllItems();
        		for(String tipo : TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getTiposProducto(
        				(SeccionTienda)this.busquedaSeccionProducto.getSelectedItem())) {
        			this.busquedaTipoProducto.addItem(tipo);
        		}
        		this.busquedaTipoProducto.insertItemAt(null, 0);
        		this.busquedaTipoProducto.setSelectedItem(null);
        	}
        	this.getContainer().revalidate();
        	this.getContainer().repaint();
        }
    }
    
    private void checkGamaBlancaItemsVisibility() {
    	boolean visibility = 
    			SeccionTienda.GAMA_BLANCA.equals(this.busquedaSeccionProducto.getSelectedItem());
		this.gamaBlancaSeparator1.setVisible(visibility);
		this.gamaBlancaSeparator2.setVisible(visibility);
		this.gamaBlancaSeparator3.setVisible(visibility);
		this.gamaBlancaSeparator4.setVisible(visibility);
		this.gamaBlancaSeparator5.setVisible(visibility);
		this.gamaBlancaSeparator6.setVisible(visibility);
		this.busquedaColorElectrodomestico.setVisible(visibility);
		this.busquedaColorElectrodomesticoLbl.setVisible(visibility);
		this.busquedaAltoElectrodomesticoDesde.setVisible(visibility);
		this.busquedaAltoElectrodomesticoDesdeLbl.setVisible(visibility);
		this.busquedaAltoElectrodomesticoHasta.setVisible(visibility);
		this.busquedaAltoElectrodomesticoHastaLbl.setVisible(visibility);
		this.busquedaAnchoElectrodomesticoDesde.setVisible(visibility);
		this.busquedaAnchoElectrodomesticoDesdeLbl.setVisible(visibility);
		this.busquedaAnchoElectrodomesticoHasta.setVisible(visibility);
		this.busquedaAnchoElectrodomesticoHastaLbl.setVisible(visibility);
		this.busquedaProfundidadElectrodomesticoDesde.setVisible(visibility);
		this.busquedaProfundidadElectrodomesticoDesdeLbl.setVisible(visibility);
		this.busquedaProfundidadElectrodomesticoHasta.setVisible(visibility);
		this.busquedaProfundidadElectrodomesticoHastaLbl.setVisible(visibility);
    }
}
