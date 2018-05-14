package es.osalguero.tiendaelect.gui.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import es.osalguero.tiendaelect.constants.CategoriaEmpleado;
import es.osalguero.tiendaelect.gui.component.ElementoTiendaGenericoTable;
import es.osalguero.tiendaelect.gui.controller.TiendaElectrodomesticosGUIController;
import es.osalguero.tiendaelect.modelo.Propiedades;
import es.osalguero.tiendaelect.modelo.negocio.ArticuloVenta;
import es.osalguero.tiendaelect.modelo.negocio.Reparacion;
import es.osalguero.tiendaelect.modelo.negocio.Venta;
import es.osalguero.tiendaelect.modelo.persona.Cliente;
import es.osalguero.tiendaelect.modelo.persona.Empleado;
import es.osalguero.tiendaelect.modelo.producto.Producto;

public class GUITableUtils
{
    private static final String[] productosHeader;
    private static final String[] clientesHeader;
    private static final String[] empleadosHeader;
    private static final String[] configuracionPropiedadesHeader;
    private static final String[] ventasHeader;
    private static final String[] reparacionesHeader;
    
    static {
    	productosHeader = new String[] {"ID", "Sección", "Tipo", "Marca", "Modelo", "Precio", "Stock"};
        clientesHeader = new String[] { "DNI", "Nombre", "1er apellido", "2º apellido", "Fec. nac.", "Dirección", "Teléfono", "Email", "Nº compras", "Última nómina" };
        empleadosHeader = new String[] { "Login", "Tipo", "Administrador", "Nombre", "Apellido1", "Apellido2" };
        configuracionPropiedadesHeader = new String[] { "Nombre variable configuración", "Valor variable" };
        ventasHeader = new String[] {"ID", "Fecha", "Importe", "Nº artículos", "Financiado", "Financiación terminada"};
        reparacionesHeader = new String[] {"ID", "Venta", "Producto", "Estado", "Empleado", "Fecha reparación", "Fecha entrega", "Importe"};
    }
    
    public static JTable getConfiguracionPropiedadesTable() {
        final DefaultTableModel model = new DefaultTableModel(GUITableUtils.configuracionPropiedadesHeader, GUITableUtils.configuracionPropiedadesHeader.length) {
            private static final long serialVersionUID = 1L;
            
            @Override
            public Class<?> getColumnClass(final int column) {
                return String.class;
            }
            
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return column == 1;
            }
        };
        final JTable configuracionPropiedadesTable = new ElementoTiendaGenericoTable(model) {
            private static final long serialVersionUID = 1L;
            
            @Override
            public TableCellRenderer getCellRenderer(final int row, final int column) {
                return this.leftRenderer;
            }
        };
        ((DefaultTableModel)configuracionPropiedadesTable.getModel()).setRowCount(0);
        for(Propiedades propiedad : Propiedades.values()) {
        	final Object[] elementData = getDataFromElement(propiedad);
        	((DefaultTableModel)configuracionPropiedadesTable.getModel()).addRow(elementData);
        }
        decorateJTable(configuracionPropiedadesTable);
        return configuracionPropiedadesTable;
    }
    
    public static JTable getEmpleadosTable(final List<Empleado> empleados) {
        final DefaultTableModel model = new DefaultTableModel(GUITableUtils.empleadosHeader, GUITableUtils.empleadosHeader.length) {
            private static final long serialVersionUID = -6357227387970649202L;
            
            @Override
            public Class<?> getColumnClass(final int column) {
                switch (column) {
                    case 2: {
                        return Boolean.class;
                    }
                    default: {
                        return String.class;
                    }
                }
            }
            
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return column == 0;
            }
        };
        final JTable empleadosTable = new ElementoTiendaGenericoTable(model) {
            private static final long serialVersionUID = 8825658335277297474L;
            
            @Override
            public TableCellRenderer getCellRenderer(final int row, final int column) {
                switch (column) {
                    case 2: {
                        return super.getCellRenderer(row, column);
                    }
                    default: {
                        return this.leftRenderer;
                    }
                }
            }
        };
        ((DefaultTableModel)empleadosTable.getModel()).setRowCount(0);
        if (empleados != null) {
            for (final Empleado empleado : empleados) {
                if (empleado != null) {
                    final Object[] elementData = getDataFromElement(empleado);
                    ((DefaultTableModel)empleadosTable.getModel()).addRow(elementData);
                }
            }
        }
        decorateJTable(empleadosTable);
        return empleadosTable;
    }

    public static JTable getVentasTable(final List<Venta> ventas) {
        final DefaultTableModel tableModel = new DefaultTableModel(GUITableUtils.ventasHeader, GUITableUtils.ventasHeader.length) {
            private static final long serialVersionUID = 7570741393834743697L;
            
            @Override
            public Class<?> getColumnClass(final int column) {
                switch (column) {
                    case 0: 
                    case 3: {
                        return Integer.class;
                    }
                    case 1: {
                        return Date.class;
                    }
                    case 2: {
                    	return Double.class;
                    }
                    case 4: 
                    case 5: {
                    	return Boolean.class;
                    }
                    default: {
                        return String.class;
                    }
                }
            }
            
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return column == 0;
            }
        };
        final JTable ventasTable = new ElementoTiendaGenericoTable(tableModel) {
            private static final long serialVersionUID = -35223324469207970L;
            
            @Override
            public TableCellRenderer getCellRenderer(final int row, final int column) {
                switch (column) {
                	case 1: {
                        return this.dateCellRenderer;
                    }
                	case 2:
                	case 3: {
                        return this.rightRenderer;
                    }
                	case 4: 
                	case 5: {
                		return super.getCellRenderer(row, column);
                	}
                    default: {
                        return this.leftRenderer;
                    }
                }
            }
        };
        ((DefaultTableModel)ventasTable.getModel()).setRowCount(0);
        if (ventas != null) {
            for (final Venta venta : ventas) {
                if (ventas != null) {
                    final Object[] elementData = getDataFromElement(venta);
                    ((DefaultTableModel)ventasTable.getModel()).addRow(elementData);
                }
            }
        }
        decorateJTable(ventasTable);
        return ventasTable;
    }
    
    public static JTable getReparacionesTable(final List<Reparacion> reparaciones) {
    	final DefaultTableModel tableModel = new DefaultTableModel(GUITableUtils.reparacionesHeader, GUITableUtils.reparacionesHeader.length) {
    		
    		/**
			 * 
			 */
			private static final long serialVersionUID = 676223689124100105L;

			@Override
    		public Class<?> getColumnClass(final int column) {
    			switch (column) {
	    			case 0: 
	    			case 1: {
	    				return Integer.class;
	    			}
	    			case 5:
	    			case 6: {
	    				return Date.class;
	    			}
	    			case 7: {
	    				return Double.class;
	    			}
	    			default: {
	    				return String.class;
	    			}
    			}
    		}
    		
    		@Override
    		public boolean isCellEditable(final int row, int column) {
    			return column == 0;
    		}
    	};
    	final JTable reparacionesTable = new ElementoTiendaGenericoTable(tableModel) {
    		/**
			 * 
			 */
			private static final long serialVersionUID = 5407845985707157146L;

			@Override
    		public TableCellRenderer getCellRenderer(final int row, final int column) {
    			switch(column) {
	    			case 5:
	    			case 6: {
	    				return this.dateCellRenderer;
	    			}
	    			case 7: {
	    				return this.rightRenderer;
	    			}
    				default: {
    					return this.leftRenderer;
    				}
    			}
    		}
    	};
    	((DefaultTableModel)reparacionesTable.getModel()).setRowCount(0);
        if (reparaciones != null) {
            for (final Reparacion reparacion : reparaciones) {
                if (reparacion != null) {
                    final Object[] elementData = getDataFromElement(reparacion);
                    ((DefaultTableModel)reparacionesTable.getModel()).addRow(elementData);
                }
            }
        }
        decorateJTable(reparacionesTable);
        return reparacionesTable;
    	    	
    }
    
    public static JTable getProductosTable(final List<Producto> productos) {
        final DefaultTableModel tableModel = new DefaultTableModel(GUITableUtils.productosHeader, GUITableUtils.productosHeader.length) {
            private static final long serialVersionUID = 7570741393834743697L;
            
            @Override
            public Class<?> getColumnClass(final int column) {
                switch (column) {
                	case 0:
                	case 6: {
                        return Integer.class;
                    }
                	case 5:
                		return Double.class;
                    default: {
                        return String.class;
                    }
                }
            }
            
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return column == 0;
            }
        };
        final JTable productosTable = new ElementoTiendaGenericoTable(tableModel) {
            private static final long serialVersionUID = -35223324469207970L;
            
            @Override
            public TableCellRenderer getCellRenderer(final int row, final int column) {
                switch (column) {
                	case 0:
                	case 5:
                	case 6: {
                        return this.rightRenderer;
                    }
                    default: {
                        return this.leftRenderer;
                    }
                }
            }
        };
        ((DefaultTableModel)productosTable.getModel()).setRowCount(0);
        if (productos != null) {
            for (final Producto producto : productos) {
                if (producto != null) {
                    final Object[] elementData = getDataFromElement(producto);
                    ((DefaultTableModel)productosTable.getModel()).addRow(elementData);
                }
            }
        }
        decorateJTable(productosTable);
        return productosTable;
    }
    
    public static JTable getClientesTable(final List<Cliente> clientes) {
        final DefaultTableModel tableModel = new DefaultTableModel(GUITableUtils.clientesHeader, GUITableUtils.clientesHeader.length) {
            private static final long serialVersionUID = 7570741393834743697L;
            
            @Override
            public Class<?> getColumnClass(final int column) {
                switch (column) {
                    case 4:
                    case 9: {
                        return Date.class;
                    }
                    default: {
                        return String.class;
                    }
                }
            }
            
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return column == 0;
            }
        };
        final JTable clientesTable = new ElementoTiendaGenericoTable(tableModel) {
            private static final long serialVersionUID = 8993791049360227884L;
            
            @Override
            public TableCellRenderer getCellRenderer(final int row, final int column) {
                switch (column) {
                    case 4: 
                    case 9: {
                        return this.dateCellRenderer;
                    }
                    case 6:
                    case 8: {
                        return this.rightRenderer;
                    }
                    default: {
                        return this.leftRenderer;
                    }
                }
            }
        };
        ((DefaultTableModel)clientesTable.getModel()).setRowCount(0);
        if (clientes != null) {
            for (final Cliente cliente : clientes) {
                if (cliente != null) {
                    final Object[] elementData = getDataFromElement(cliente);
                    ((DefaultTableModel)clientesTable.getModel()).addRow(elementData);
                }
            }
        }
        decorateJTable(clientesTable);
        return clientesTable;
    }
    
    public static Object[] getDataFromElement(final Producto producto) {
        final Object[] productoData = {
        		producto.getId(), producto.getSeccionTienda().getNombre(), producto.getTipo(), producto.getMarca(), producto.getModelo(), 
        		producto.getPrecio(), producto.getCantidad()};
        return productoData;
    }
    
    public static Object[] getDataFromElement(final Cliente cliente) {
        final Object[] clienteData = {
        		cliente.getDni(), cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(),
        		cliente.getFechaNacimiento(), cliente.getDireccion(), cliente.getTelefono(), cliente.getEmail(),
        		TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getVentasByCliente(cliente.getDni()).size(),
        		cliente.getUltimaNomina() != null ? cliente.getUltimaNomina().getFecha() : null
        };
        return clienteData;
    }
    
    public static Object[] getDataFromElement(final Empleado empleado) {
        CategoriaEmpleado tipoEmpleado = empleado.getCategoria();
        final Object[] empleadoData = {
        		empleado.getLogin(), tipoEmpleado.getNombre(), empleado.esAdministrador(), 
        		empleado.getNombre(), empleado.getApellido1(), empleado.getApellido2() };
        return empleadoData;
    }
    
    public static Object[] getDataFromElement(final Venta venta) {
        Double importeVenta = null;
        try {
        	importeVenta = TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().getImporteVenta(venta);
        } catch(Exception e) {
        	//Nothing to do
        }
        boolean ventaFinanciada = TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().isVentaFinanciada(venta);
    	int articulos = 0;
    	for(ArticuloVenta articuloVenta : venta.getArticulosVenta()) {
    		articulos = articulos + articuloVenta.getCantidad();
    	}
        final Object[] ventaData = {
        	venta.getId(), venta.getFecha(), importeVenta, articulos,
        	ventaFinanciada,
        	ventaFinanciada ? TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().isVentaFinanciacionTerminada(venta) : false
        };
        return ventaData;
    }

    public static Object[] getDataFromElement(Propiedades propiedad) {
        final Object[] configuracionPropiedadesData = {
        		propiedad.getNombrePropiedad(), TiendaElectrodomesticosGUIController.getInstance().getTiendaAppService().
        		getPropiedadConfiguracion(propiedad)
        };
        return configuracionPropiedadesData;
    }
    
    public static Object[] getDataFromElement(Reparacion reparacion) {
    	final Object[] reparacionData = {
    			reparacion.getId(), reparacion.getVenta().getId(), reparacion.getProducto().toString(), reparacion.getEstado(), reparacion.getEmpleadoReparacion(),
    			reparacion.getFechaReparacion(), reparacion.getFechaEntrega(), reparacion.getImporte()
    	};
    	return reparacionData;
    }
    
    private static void decorateJTable(final JTable jtable) {
        jtable.setIntercellSpacing(new Dimension(6, 1));
        jtable.setRowHeight(18);
        jtable.setAutoResizeMode(0);
        jtable.setAutoCreateRowSorter(true);
        jtable.setRowSelectionAllowed(true);
        jtable.setSelectionMode(0);
        jtable.setShowGrid(true);
        jtable.setAutoResizeMode(0);
        final Font font = new Font(jtable.getTableHeader().getFont().getName(), 1, 14);
        final Enumeration<TableColumn> tableColumns = jtable.getColumnModel().getColumns();
        while (tableColumns.hasMoreElements()) {
            final TableColumn tableColumn = tableColumns.nextElement();
            final int columnMinWidth = new BigDecimal(font.getStringBounds((String)tableColumn.getHeaderValue(), new FontRenderContext(font.getTransform(), false, false)).getBounds().getWidth()).setScale(0, 2).intValue() + 10;
            tableColumn.setMinWidth(columnMinWidth);
        }
    }
}
