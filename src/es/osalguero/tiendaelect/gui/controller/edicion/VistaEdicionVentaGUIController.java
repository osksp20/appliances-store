package es.osalguero.tiendaelect.gui.controller.edicion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.gui.view.edicion.VistaEdicionVentaGUI;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.modelo.negocio.ArticuloVenta;
import es.osalguero.tiendaelect.modelo.negocio.Venta;
import es.osalguero.tiendaelect.modelo.persona.Cliente;
import es.osalguero.tiendaelect.modelo.producto.Producto;

public class VistaEdicionVentaGUIController extends VistaAbstractaEdicionGUIController<VistaEdicionVentaGUI, Venta>
{

	private Logger logger = Logger.getLogger(this.getClass().getName());

	public VistaEdicionVentaGUIController(final Venta venta, final TipoEdicionEnum tipoEdicion) {
		super(venta, null, tipoEdicion);
	}

	@Override
	protected VistaEdicionVentaGUI initVista(final Venta venta, final ElementoTiendaGenerico elementoPadre, final TipoEdicionEnum tipoEdicion) {
		return new VistaEdicionVentaGUI(this, venta, tipoEdicion);
	}

	@Override
	public Venta addNewElement(final Venta nuevaVenta) throws Exception {
		return TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().addVenta(nuevaVenta);
	}

	public Cliente getCliente(final String dni) throws Exception {
		Cliente cliente = null;
		try {
			final List<Cliente> clientes = TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getClientesTienda();
			for (final Cliente clienteTaller : clientes) {
				if (clienteTaller.getDni().trim().toLowerCase().equals(dni.trim().toLowerCase())) {
					cliente = clienteTaller;
				}
			}
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "Error buscando cliente con DNI: ".concat(dni), e);
			throw new Exception("Se ha producido un error realizando la búsqueda del cliente.");
		}
		return cliente;
	}

	public Producto getProducto(final String idProducto) throws Exception {
		Producto producto = null;
		try {
			final List<Producto> productos = TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getProductosTienda();
			for (final Producto productoTienda : productos) {
				if (productoTienda.equals(producto)) {
					producto = productoTienda;
				}
			}
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "Error buscando producto con ID: ".concat(idProducto), e);
			throw new Exception("Se ha producido un error realizando la búsqueda del producto");
		}
		return producto;
	}

	public void modificarVenta(final Venta venta) throws Exception {
		TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().modificarVenta(venta);
	}

	public void imprimirFactura(final Venta venta) {
		if (venta == null) {
			this.showError("La venta no es válida, no puede imprimirse la factura.");
		}
		else if (venta.getCliente() == null) {
			this.showError("La venta no tiene asignada un cliente, no puede imprimirse la factura.");
		}
		else if(venta.getArticulosVenta() == null || venta.getArticulosVenta().isEmpty()) {
			this.showError("La venta no contiene articulos, no puede imprimirse la factura");
		}
		else {

			try {
				String text = "Factura nº".concat(String.valueOf(venta.getId()));
				text = text.concat("\nFecha: ").concat(new SimpleDateFormat("dd/MM/yy hh:mm").format(venta.getFecha()));
				final String nombreCliente = venta.getCliente().getNombre().concat(" ").concat(venta.getCliente().getApellido1()).concat(" ").concat(venta.getCliente().getApellido2());
				text = text.concat("\n\nCliente: D./Dña. ").concat(nombreCliente).concat(" con DNI.").concat(venta.getCliente().getDni());
				BigDecimal importeTotal = new BigDecimal(0);
				for(ArticuloVenta articuloVenta : venta.getArticulosVenta()) {
					BigDecimal importe = new BigDecimal(articuloVenta.getPrecio()).multiply(new BigDecimal(articuloVenta.getCantidad()));
					text = text.concat("\nArtículo: ").concat(articuloVenta.getProducto().getModelo()).concat(" ").concat(articuloVenta.getProducto().getMarca()).
							concat(". Precio: ").concat(String.valueOf(articuloVenta.getPrecio())).concat(". Cantidad: ").concat(String.valueOf(articuloVenta.getCantidad())).
							concat(". Total: ").concat(String.valueOf(importe.setScale(2, RoundingMode.HALF_UP).doubleValue())).
							concat(".");
					importeTotal = importeTotal.add(importe);
				}
				text = text.concat("\n\nTotal: ").concat(String.valueOf(importeTotal.setScale(2, RoundingMode.HALF_UP).doubleValue()));
				JOptionPane.showMessageDialog(this.getTallerFrameContainer(), text);
			}
			catch (Exception e) {
				logger.log(Level.SEVERE, "Error generando factura", e);
				this.showError("Se ha producido un error generando la factura.\nPor favor revise los logs de la aplicación");
			}
		}

	}
}
