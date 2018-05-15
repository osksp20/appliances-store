package es.osalguero.tiendaelect.gui.view.edicion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import es.osalguero.tiendaelect.constants.SeccionTienda;
import es.osalguero.tiendaelect.constants.TipoAlimentacion;
import es.osalguero.tiendaelect.constants.TipoAlmacenamiento;
import es.osalguero.tiendaelect.constants.TipoPantalla;
import es.osalguero.tiendaelect.gui.controller.edicion.VistaEdicionProductoGUIController;
import es.osalguero.tiendaelect.gui.exception.FilterFormatException;
import es.osalguero.tiendaelect.gui.util.GUIUtils;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.producto.Producto;
import es.osalguero.tiendaelect.modelo.producto.acc.Accesorio;
import es.osalguero.tiendaelect.modelo.producto.acc.AccesorioFotografia;
import es.osalguero.tiendaelect.modelo.producto.acc.AccesorioGamaBlanca;
import es.osalguero.tiendaelect.modelo.producto.acc.AccesorioImagen;
import es.osalguero.tiendaelect.modelo.producto.acc.AccesorioInformatica;
import es.osalguero.tiendaelect.modelo.producto.acc.AccesorioPAE;
import es.osalguero.tiendaelect.modelo.producto.acc.AccesorioSonido;
import es.osalguero.tiendaelect.modelo.producto.acc.AccesorioTelefonia;
import es.osalguero.tiendaelect.modelo.producto.acc.AccesorioVideo;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectFotografia;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectFotografiaDigital;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectGamaBlanca;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectGamaMarron;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectImagen;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectInformatica;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectPAE;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectSonido;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectTelefonia;
import es.osalguero.tiendaelect.modelo.producto.elect.ElectVideo;

public class VistaEdicionProductoGUI extends VistaAbstractaEdicionGUI<VistaEdicionProductoGUIController, Producto> implements ItemListener
{	
    private JLabel datosProductoLbl;
    private JLabel idProductoLbl;
    private JLabel seccionTiendaProductoLbl;
    private JLabel tipoProductoLbl;
    private JLabel marcaProductoLbl;
    private JLabel modeloProductoLbl;
    private JLabel stockProductoLbl;
    private JLabel precioProductoLbl;
    private JLabel caracteristicasProductoLbl;
    private JLabel anchoElectLbl;
    private JLabel altoElectLbl;
    private JLabel profundidadElectLbl;
    private JLabel colorElectLbl;
    private JLabel almacenamientoLbl;
    private JLabel pantallaLbl;
    private JLabel tipoPantallaLbl;
    private JLabel tipoAlimentacionLbl;
    private JLabel seccionAccesorioLbl;
    
    private JPanel panelGamaBlanca;
    private JPanel panelGamaMarron;
    
    private JComboBox<SeccionTienda> seccionTiendaProducto;
    private JComboBox<TipoAlmacenamiento> tipoAlmacenamiento;
    private JComboBox<TipoPantalla> tipoPantalla;
    private JComboBox<TipoAlimentacion> tipoAlimentacion;
    private JComboBox<SeccionTienda> seccionAccesorio;
    private JTextField id;
    private JTextField tipo;
    private JTextField marca;
    private JTextField modelo;
    private JTextField stock;
    private JTextField precio;
    private JTextArea caracteristicas;
    private JTextField anchoElect;
    private JTextField altoElect;
    private JTextField profundidadElect;
    private JTextField colorElect;
    private JTextField almacenamientoCapacidad;
    private JTextField pantallaTamanyo;
    private JCheckBox fotoDigital;
    
    public VistaEdicionProductoGUI(final VistaEdicionProductoGUIController controller, final Producto producto, final TipoEdicionEnum tipoEdicion) {
        super(controller, producto, tipoEdicion, null);
    }
    
    @Override
    protected String getViewTitle() {
        if (TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            return "Nuevo Producto";
        }
        String title = "Edición Producto";
        if (this.elementoEdicion != null && ((Producto)this.elementoEdicion).getMarca() != null) {
            title = title.concat(" ").concat(((Producto)this.elementoEdicion).getMarca());
        }
        if(this.elementoEdicion != null && ((Producto)this.elementoEdicion).getModelo() != null) {
        	title = title.concat(" ").concat(((Producto)this.elementoEdicion).getModelo());
        }
        return title;
    }
    
    @Override
    protected void fillEditPanel(final JPanel editFieldsPanel) {
        (this.datosProductoLbl = new JLabel("Datos del producto")).setBackground(Color.BLUE);
        (this.idProductoLbl = new JLabel("ID:")).setPreferredSize(new Dimension(100,20));
        (this.seccionTiendaProductoLbl = new JLabel("Seccion:")).setPreferredSize(new Dimension(100,20));
        (this.tipoProductoLbl = new JLabel("Tipo:")).setPreferredSize(new Dimension(100, 20));
        (this.marcaProductoLbl = new JLabel("Marca:")).setPreferredSize(new Dimension(100,20));
        (this.modeloProductoLbl = new JLabel("Modelo:")).setPreferredSize(new Dimension(100,20));
        (this.stockProductoLbl = new JLabel("Stock:")).setPreferredSize(new Dimension(100,20));
        (this.precioProductoLbl = new JLabel("Precio:")).setPreferredSize(new Dimension(100,20));
        (this.caracteristicasProductoLbl = new JLabel("Características:")).setPreferredSize(new Dimension(100,20));
        (this.anchoElectLbl = new JLabel("Ancho:")).setPreferredSize(new Dimension(100,20));
        (this.altoElectLbl = new JLabel("Alto:")).setPreferredSize(new Dimension(100,20));
        (this.profundidadElectLbl = new JLabel("Profundidad:")).setPreferredSize(new Dimension(100,20));
        (this.colorElectLbl = new JLabel("Color:")).setPreferredSize(new Dimension(100,20));
        (this.almacenamientoLbl = new JLabel("Almacenamiento:")).setPreferredSize(new Dimension(100,20));
        (this.pantallaLbl = new JLabel("Tamaño pantalla (pulgadas):")).setPreferredSize(new Dimension(100,20));
        (this.tipoPantallaLbl = new JLabel("Tipo pantalla:")).setPreferredSize(new Dimension(100,20));
        (this.tipoAlimentacionLbl = new JLabel("Tipo alimentacion:")).setPreferredSize(new Dimension(100,20));
        (this.seccionAccesorioLbl = new JLabel("Sección accesorio:")).setPreferredSize(new Dimension(100,20));
        
        (this.id = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        (this.tipo = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        (this.marca = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        (this.modelo = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        (this.stock = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        (this.precio = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        (this.caracteristicas = new JTextArea(10, 100)).setDisabledTextColor(Color.BLACK);
        (this.anchoElect = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        (this.altoElect = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        (this.profundidadElect = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        (this.colorElect = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        (this.almacenamientoCapacidad = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        (this.pantallaTamanyo = new JTextField(15)).setDisabledTextColor(Color.BLACK);
        this.seccionTiendaProducto = GUIUtils.getSeccionTiendaComboBox(false);
        this.tipoAlmacenamiento = GUIUtils.getTipoAlmacenamientoComboBox(true);
        this.tipoPantalla = GUIUtils.getTipoPantallaComboBox(true);
        this.tipoAlimentacion = GUIUtils.getTipoAlimentacionComboBox(true);
        this.fotoDigital = new JCheckBox();
        this.seccionAccesorio = GUIUtils.getSeccionAccesorioComboBox(false);
        
        editFieldsPanel.add(this.datosProductoLbl);
        editFieldsPanel.add(this.getLineSeparator(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue() - 6));
        editFieldsPanel.setLayout(new FlowLayout(0, 2, 6));
        editFieldsPanel.add(this.idProductoLbl);
        editFieldsPanel.add(this.id);
        editFieldsPanel.add(this.createSeparator(50));
        editFieldsPanel.add(this.seccionTiendaProductoLbl);
        editFieldsPanel.add(this.seccionTiendaProducto);
        editFieldsPanel.add(this.createSeparator(50));
        editFieldsPanel.add(this.seccionAccesorioLbl);
        editFieldsPanel.add(this.seccionAccesorio);
        editFieldsPanel.add(this.createSeparator(50));
        editFieldsPanel.add(this.tipoProductoLbl);
        editFieldsPanel.add(this.tipo);
        editFieldsPanel.add(this.createSeparator(30));
        editFieldsPanel.add(this.marcaProductoLbl);
        editFieldsPanel.add(this.marca);
        editFieldsPanel.add(this.createSeparator(50));
        editFieldsPanel.add(this.modeloProductoLbl);
        editFieldsPanel.add(this.modelo);
        editFieldsPanel.add(this.createSeparator(20));
        editFieldsPanel.add(this.stockProductoLbl);
        editFieldsPanel.add(this.stock);
        editFieldsPanel.add(this.createSeparator(50));
        editFieldsPanel.add(this.precioProductoLbl);
        editFieldsPanel.add(this.precio);
        editFieldsPanel.add(this.createSeparator(50));
        (this.panelGamaBlanca = new JPanel(new FlowLayout(0, 0, 0))).setPreferredSize(new Dimension(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue(), 20));
        panelGamaBlanca.add(this.anchoElectLbl);
        panelGamaBlanca.add(this.anchoElect);
        panelGamaBlanca.add(this.createSeparator(40));
        panelGamaBlanca.add(this.altoElectLbl);
        panelGamaBlanca.add(this.altoElect);
        panelGamaBlanca.add(this.createSeparator(50));
        panelGamaBlanca.add(this.profundidadElectLbl);
        panelGamaBlanca.add(this.profundidadElect);
        panelGamaBlanca.add(this.createSeparator(50));
        panelGamaBlanca.add(this.colorElectLbl);
        panelGamaBlanca.add(this.colorElect);
        editFieldsPanel.add(panelGamaBlanca);
        (this.panelGamaMarron = new JPanel(new FlowLayout(0, 0, 0))).setPreferredSize(new Dimension(new Double(editFieldsPanel.getPreferredSize().getWidth()).intValue(), 20));
        panelGamaMarron.add(this.almacenamientoLbl);
        panelGamaMarron.add(this.almacenamientoCapacidad);
        panelGamaMarron.add(this.tipoAlmacenamiento);
        panelGamaMarron.add(this.createSeparator(40));
        panelGamaMarron.add(this.pantallaLbl);
        panelGamaMarron.add(this.pantallaTamanyo);
        panelGamaMarron.add(this.createSeparator(50));
        panelGamaMarron.add(this.tipoPantallaLbl);
        panelGamaMarron.add(this.tipoPantalla);
        panelGamaMarron.add(this.createSeparator(50));
        panelGamaMarron.add(this.tipoAlimentacionLbl);
        panelGamaMarron.add(this.tipoAlimentacion);
        editFieldsPanel.add(panelGamaMarron);
        editFieldsPanel.add(this.caracteristicasProductoLbl);
        editFieldsPanel.add(this.caracteristicas);
        editFieldsPanel.add(this.createSeparator(30));
        this.manageTipoProducto();
        
        this.seccionTiendaProducto.addItemListener(this);
    }
    
    @Override
    protected void validate() throws FilterFormatException, Exception {
    	if(this.seccionTiendaProducto.getSelectedItem() == null) {
        	throw new FilterFormatException("La sección a la que pertenece el producto es obligatoria");
        }
    	this.validateMandatoryString("Marca", this.marca.getText());
        this.validateMandatoryString("Modelo", this.modelo.getText());
        this.validateMandatoryString("Tipo", this.tipo.getText());
        this.validateMandatoryString("Stock", this.stock.getText());
        try {
        	Integer.parseInt(this.stock.getText());
        } catch(Exception e) {
        	throw new FilterFormatException("El valor del campo 'Stock' debe ser numérico");
        }
        this.validateMandatoryString("Precio", this.precio.getText());
        try {
        	Float.parseFloat(this.precio.getText().replaceAll(",", "\\."));
        } catch(Exception e) {
        	throw new FilterFormatException("El valor del campo 'Precio' debe ser numérico");
        }
        if(SeccionTienda.GAMA_BLANCA.equals(this.seccionTiendaProducto.getSelectedItem())) {
        	this.validateMandatoryString("Alto", this.altoElect.getText());
        	try {
        		Integer.parseInt(this.altoElect.getText());
        	} catch(Exception e) {
        		throw new FilterFormatException("El formato del campo 'Alto' debe ser numérico entero");
        	}
        	this.validateMandatoryString("Ancho", this.anchoElect.getText());
    		try {
    			Integer.parseInt(this.anchoElect.getText());
    		} catch(Exception e) {
    			throw new FilterFormatException("El formato del campo 'Ancho' debe ser numérico entero");
    		}
    		this.validateMandatoryString("Profundidad", this.profundidadElect.getText());
    		try {
    			Integer.parseInt(this.profundidadElect.getText());
    		} catch(Exception e) {
    			throw new FilterFormatException("El formato del campo 'Profundidad' debe ser numérico entero");
    		}
        } else if(this.isSeccionGamaMarron((SeccionTienda)this.seccionTiendaProducto.getSelectedItem())) {
        	if(almacenamientoCapacidad.getText() != null) {
        		try {
        			Integer.parseInt(this.almacenamientoCapacidad.getText());
        		} catch(Exception e) {
        			throw new FilterFormatException("El formato del campo 'Almacenamiento capacidad' debe ser numérico entero");
        		}
        		if(this.tipoAlmacenamiento.getSelectedItem() == null) {
        			throw new FilterFormatException("Debe seleccionar la medida de almacenamiento");
        		}
        	}
        	if(this.pantallaTamanyo.getText() != null) {
        		try {
        			Integer.parseInt(this.pantallaTamanyo.getText());
        		} catch(Exception e) {
        			throw new FilterFormatException("El formato del campo 'Tamaño pantalla' debe ser numérico entero");
        		}
        		if(this.tipoPantalla.getSelectedItem() == null) {
        			throw new FilterFormatException("Debe seleccionar el tipo de pantalla");
        		}
        	}
        }
    }
    
    @Override
    protected void modificarElemento() throws Exception {
    	((Producto)this.elementoEdicion).setTipo(this.tipo.getText());
        ((Producto)this.elementoEdicion).setMarca(this.marca.getText());
        ((Producto)this.elementoEdicion).setModelo(this.modelo.getText());
        ((Producto)this.elementoEdicion).setCantidad(Integer.parseInt(this.stock.getText()));
        ((Producto)this.elementoEdicion).setPrecio(Float.parseFloat(this.precio.getText().replaceAll(",", "\\.")));
        ((Producto)this.elementoEdicion).setCaracteristicas(this.caracteristicas.getText());
        if(SeccionTienda.GAMA_BLANCA.equals(this.seccionTiendaProducto.getSelectedItem())) {
        	((ElectGamaBlanca)this.elementoEdicion).setAlto(Integer.parseInt(this.altoElect.getText()));
        	((ElectGamaBlanca)this.elementoEdicion).setAncho(Integer.parseInt(this.anchoElect.getText()));
        	((ElectGamaBlanca)this.elementoEdicion).setProfundidad(Integer.parseInt(this.profundidadElect.getText()));
        	((ElectGamaBlanca)this.elementoEdicion).setColor(this.colorElect.getText());
        } else if(this.isSeccionGamaMarron((SeccionTienda)this.seccionTiendaProducto.getSelectedItem())) {
        	if(this.almacenamientoCapacidad.getText() != null) {
	        	((ElectGamaMarron)this.elementoEdicion).setTipoAlmacenamiento((TipoAlmacenamiento)this.tipoAlmacenamiento.getSelectedItem());
	        	((ElectGamaMarron)this.elementoEdicion).setCapacidad(Integer.parseInt(this.almacenamientoCapacidad.getText()));
        	}
        	if(this.pantallaTamanyo.getText() != null) {
        		((ElectGamaMarron)this.elementoEdicion).setTamanyoPantalla(Integer.parseInt(this.pantallaTamanyo.getText()));
        		((ElectGamaMarron)this.elementoEdicion).setTipoPantalla((TipoPantalla)this.tipoPantalla.getSelectedItem());
        	}
        	((ElectGamaMarron)this.elementoEdicion).setTipoAlimentacion((TipoAlimentacion)this.tipoAlimentacion.getSelectedItem());
        }
        ((VistaEdicionProductoGUIController)this.getController()).modificarProducto((Producto)this.elementoEdicion);
    }
    
    @Override
    protected void manageTipoEdicion() {
    	this.id.setEnabled(false);
    	if (TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            this.seccionTiendaProducto.setEnabled(true);
            this.seccionAccesorio.setEnabled(true);
        }
        else {
            this.seccionTiendaProducto.setEnabled(false);
            this.seccionAccesorio.setEnabled(false);
        }
        final boolean enabled = !TipoEdicionEnum.DETALLE.equals(this.tipoEdicion);
        this.marca.setEnabled(enabled);
        this.modelo.setEnabled(enabled);
        this.stock.setEnabled(enabled);
        this.precio.setEnabled(enabled);
        this.caracteristicas.setEnabled(enabled);
        this.anchoElect.setEnabled(enabled);
        this.altoElect.setEnabled(enabled);
        this.profundidadElect.setEnabled(enabled);
        this.colorElect.setEnabled(enabled);
        this.almacenamientoCapacidad.setEnabled(enabled);
        this.pantallaTamanyo.setEnabled(enabled);
        this.fotoDigital.setEnabled(enabled);
        this.tipoAlimentacion.setEnabled(enabled);
        this.tipoAlmacenamiento.setEnabled(enabled);
        this.tipoPantalla.setEnabled(enabled);
        this.manageViewData();
    }
    
    @Override
    protected Producto crearNuevoElemento() throws Exception {
        Producto producto = null;
        switch((SeccionTienda)this.seccionTiendaProducto.getSelectedItem()) {
        case ACCESORIOS: {
        	switch((SeccionTienda)this.seccionAccesorio.getSelectedItem()) {
        	case FOTO:
        		producto = new AccesorioFotografia();
        		break;
        	case GAMA_BLANCA:
        		producto = new AccesorioGamaBlanca();
        		break;
        	case IMAGEN:
        		producto = new AccesorioImagen();
        		break;
        	case INFORMATICA:
        		producto = new AccesorioInformatica();
        		break;
        	case PAE:
        		producto = new AccesorioPAE();
        		break;
        	case SONIDO:
        		producto = new AccesorioSonido();
        		break;
        	case TELEFONIA:
        		producto = new AccesorioTelefonia();
        		break;
        	case VIDEO:
        		producto = new AccesorioVideo();
        		break;
			case ACCESORIOS:
				break;
			default:
				break;
        	}
        	break;
        }
        case FOTO: {
        	if(this.fotoDigital.isSelected()) {
        		producto = new ElectFotografiaDigital();
        	} else {
        		producto = new ElectFotografia();
        	}
        	break;
        }
        case GAMA_BLANCA:
        	producto = new ElectGamaBlanca();
        	((ElectGamaBlanca)producto).setAlto(Integer.parseInt(this.altoElect.getText()));
        	((ElectGamaBlanca)producto).setAncho(Integer.parseInt(this.anchoElect.getText()));
        	((ElectGamaBlanca)producto).setProfundidad(Integer.parseInt(this.profundidadElect.getText()));
        	((ElectGamaBlanca)producto).setColor(this.colorElect.getText());
        	break;
        case IMAGEN:
        	producto = new ElectImagen();
        	break;
        case INFORMATICA:
        	producto = new ElectInformatica();
        	break;
        case PAE:
        	producto = new ElectPAE();
        	break;
        case SONIDO:
        	producto = new ElectSonido();
        	break;
        case TELEFONIA:
        	producto = new ElectTelefonia();
        	break;
        case VIDEO:
        	producto = new ElectVideo();
        	break;
        default:
        	break;
        }
    	producto.setMarca(this.marca.getText());
    	producto.setModelo(this.modelo.getText());
    	producto.setTipo(this.tipo.getText());
    	producto.setCantidad(Integer.parseInt(this.stock.getText()));
    	producto.setPrecio(Float.parseFloat(this.precio.getText()));
    	producto.setTipo(this.tipo.getText());
    	producto.setCaracteristicas(this.caracteristicas.getText());
    	if(isSeccionGamaMarron((SeccionTienda)this.seccionTiendaProducto.getSelectedItem())) {
    		((ElectGamaMarron)producto).setTipoAlimentacion((TipoAlimentacion)this.tipoAlimentacion.getSelectedItem());
    		if(this.pantallaTamanyo.getText() != null) {
    			((ElectGamaMarron)producto).setTamanyoPantalla(Integer.parseInt(this.pantallaTamanyo.getText()));
    			((ElectGamaMarron)producto).setTipoPantalla((TipoPantalla)this.tipoPantalla.getSelectedItem());
    		}
    		if(this.almacenamientoCapacidad.getText() != null) {
    			((ElectGamaMarron)producto).setCapacidad(Integer.parseInt(this.almacenamientoCapacidad.getText()));
    			((ElectGamaMarron)producto).setTipoAlmacenamiento((TipoAlmacenamiento)this.tipoAlmacenamiento.getSelectedItem());
    		}
    	}
        return producto;
    }
    
    private JSeparator getLineSeparator(final int width) {
        final JSeparator lineSeparator = new JSeparator();
        lineSeparator.setPreferredSize(new Dimension(width, 1));
        lineSeparator.setForeground(Color.BLACK);
        return lineSeparator;
    }
    
    @Override
    public void itemStateChanged(final ItemEvent e) {
        if (e.getSource().equals(this.seccionTiendaProducto) && e.getStateChange() == 1) {
            this.manageTipoProducto();
            this.getContainer().revalidate();
            this.getContainer().repaint();
        }
    }
    
    private void manageTipoProducto() {
        this.panelGamaBlanca.setVisible(SeccionTienda.GAMA_BLANCA.equals(this.seccionTiendaProducto.getSelectedItem()));
        this.panelGamaMarron.setVisible(isSeccionGamaMarron((SeccionTienda)this.seccionTiendaProducto.getSelectedItem()));
        this.seccionAccesorio.setVisible(SeccionTienda.ACCESORIOS.equals(this.seccionTiendaProducto.getSelectedItem()));
        this.seccionAccesorioLbl.setVisible(SeccionTienda.ACCESORIOS.equals(this.seccionTiendaProducto.getSelectedItem()));
    }
    
    private void validateMandatoryString(final String fieldName, final String value) throws FilterFormatException {
        if (value == null || value.trim().isEmpty()) {
            throw new FilterFormatException("El campo '".concat(fieldName).concat("' es obligatorio."));
        }
    }
    
    @Override
    protected VistasEnum getVistaAnterior() {
        return VistasEnum.VISTA_LISTADO_PRODUCTOS;
    }
    
    @Override
    protected ElementoTiendaGenerico getElementoAnterior() {
        return null;
    }
    
    private void manageViewData() {
        this.labelVistaEdicion.setText(this.getViewTitle());
        if (!TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            this.panelLabelEdicion.setPreferredSize(new Dimension(new Double(this.getContainer().getPreferredSize().getWidth()).intValue() - 12, 35));
        }
        if (this.elementoEdicion != null && !TipoEdicionEnum.NUEVO.equals(this.tipoEdicion)) {
            this.id.setText(String.valueOf(((Producto)this.elementoEdicion).getId()));
            this.seccionTiendaProducto.setSelectedItem(((Producto)this.elementoEdicion).getSeccionTienda());
            if(SeccionTienda.ACCESORIOS.equals(((Producto)this.elementoEdicion).getSeccionTienda())) {
            	this.seccionAccesorio.setSelectedItem(((Accesorio)this.elementoEdicion).getSeccionAccesorio());
            }
            this.marca.setText(((Producto)this.elementoEdicion).getMarca());
            this.modelo.setText(((Producto)this.elementoEdicion).getModelo());
            this.stock.setText(String.valueOf(((Producto)this.elementoEdicion).getCantidad()));
            this.precio.setText(String.valueOf(((Producto)this.elementoEdicion).getPrecio()));
            this.tipo.setText(((Producto)this.elementoEdicion).getTipo());
            this.caracteristicas.setText(((Producto)this.elementoEdicion).getCaracteristicas());
            if(SeccionTienda.GAMA_BLANCA.equals(((Producto)this.elementoEdicion).getSeccionTienda())) {
            	this.altoElect.setText(String.valueOf(((ElectGamaBlanca)this.elementoEdicion).getAlto()));
            	this.anchoElect.setText(String.valueOf(((ElectGamaBlanca)this.elementoEdicion).getAncho()));
            	this.profundidadElect.setText(String.valueOf(((ElectGamaBlanca)this.elementoEdicion).getProfundidad()));
            	this.colorElect.setText(String.valueOf(((ElectGamaBlanca)this.elementoEdicion).getColor()));
            } else if(isSeccionGamaMarron(((Producto)this.elementoEdicion).getSeccionTienda())) {
            	this.almacenamientoCapacidad.setText(String.valueOf(((ElectGamaMarron)this.elementoEdicion).getCapacidad()));
            	this.pantallaTamanyo.setText(String.valueOf(((ElectGamaMarron)this.elementoEdicion).getTamanyoPantalla()));
            	this.tipoAlmacenamiento.setSelectedItem(((ElectGamaMarron)this.elementoEdicion).getTipoAlmacenamiento());
            	this.tipoAlimentacion.setSelectedItem(((ElectGamaMarron)this.elementoEdicion).getTipoAlimentacion());
            	this.tipoPantalla.setSelectedItem(((ElectGamaMarron)this.elementoEdicion).getTipoPantalla());
            }
        }
    }
    
    private boolean isSeccionGamaMarron(SeccionTienda seccion) {
    	return seccion != null && !SeccionTienda.GAMA_BLANCA.equals(seccion) && !SeccionTienda.ACCESORIOS.equals(seccion) &&
    			(!SeccionTienda.FOTO.equals(seccion) || fotoDigital.isSelected());
    }
}
