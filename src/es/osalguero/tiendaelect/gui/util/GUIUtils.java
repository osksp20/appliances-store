package es.osalguero.tiendaelect.gui.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import es.osalguero.tiendaelect.constants.CategoriaEmpleado;
import es.osalguero.tiendaelect.constants.MedidaAlmacenamiento;
import es.osalguero.tiendaelect.constants.Provincias;
import es.osalguero.tiendaelect.constants.SeccionTienda;
import es.osalguero.tiendaelect.constants.TipoAlimentacion;
import es.osalguero.tiendaelect.constants.TipoAlmacenamiento;
import es.osalguero.tiendaelect.constants.TipoPantalla;

public class GUIUtils
{   
	@SuppressWarnings("unchecked")
	public static JComboBox<MedidaAlmacenamiento> getMedidaAlmacenamientoComboBox(final boolean firstNull) {
		final JComboBox<MedidaAlmacenamiento> medidaAlmacenamientoComboBox = new JComboBox<MedidaAlmacenamiento>(MedidaAlmacenamiento.values());
		medidaAlmacenamientoComboBox.setRenderer(new BasicComboBoxRenderer() {
			private static final long serialVersionUID = 45692578919059504L;

			@Override
			public void paint(final Graphics g) {
				this.setForeground(Color.BLACK);
				super.paint(g);
			}
		});
		if(firstNull) {
			medidaAlmacenamientoComboBox.insertItemAt(null, 0);
			medidaAlmacenamientoComboBox.setSelectedIndex(-1);
		}
		medidaAlmacenamientoComboBox.setPreferredSize(new Dimension(new Double(medidaAlmacenamientoComboBox.getPreferredSize().getWidth()).intValue(), 20));
		return medidaAlmacenamientoComboBox;
	}
	
	@SuppressWarnings("unchecked")
	public static JComboBox<TipoAlmacenamiento> getTipoAlmacenamientoComboBox(final boolean firstNull) {
		final JComboBox<TipoAlmacenamiento> tipoAlmacenamientoComboBox = new JComboBox<TipoAlmacenamiento>(TipoAlmacenamiento.values());
		tipoAlmacenamientoComboBox.setRenderer(new BasicComboBoxRenderer() {
			private static final long serialVersionUID = 45692578919059504L;

			@Override
			public void paint(final Graphics g) {
				this.setForeground(Color.BLACK);
				super.paint(g);
			}
		});
		if(firstNull) {
			tipoAlmacenamientoComboBox.insertItemAt(null, 0);
			tipoAlmacenamientoComboBox.setSelectedIndex(-1);
		}
		tipoAlmacenamientoComboBox.setPreferredSize(new Dimension(new Double(tipoAlmacenamientoComboBox.getPreferredSize().getWidth()).intValue(), 20));
		return tipoAlmacenamientoComboBox;
	}
	
	@SuppressWarnings("unchecked")
	public static JComboBox<TipoAlimentacion> getTipoAlimentacionComboBox(final boolean firstNull) {
		final JComboBox<TipoAlimentacion> tipoAlimentacionComboBox = new JComboBox<TipoAlimentacion>(TipoAlimentacion.values());
		tipoAlimentacionComboBox.setRenderer(new BasicComboBoxRenderer() {
			private static final long serialVersionUID = 45692578919059504L;

			@Override
			public void paint(final Graphics g) {
				this.setForeground(Color.BLACK);
				super.paint(g);
			}
		});
		if(firstNull) {
			tipoAlimentacionComboBox.insertItemAt(null, 0);
			tipoAlimentacionComboBox.setSelectedIndex(-1);
		}
		tipoAlimentacionComboBox.setPreferredSize(new Dimension(new Double(tipoAlimentacionComboBox.getPreferredSize().getWidth()).intValue(), 20));
		return tipoAlimentacionComboBox;
	}
	
	@SuppressWarnings("unchecked")
	public static JComboBox<TipoPantalla> getTipoPantallaComboBox(final boolean firstNull) {
		final JComboBox<TipoPantalla> tipoPantallaComboBox = new JComboBox<TipoPantalla>(TipoPantalla.values());
		tipoPantallaComboBox.setRenderer(new BasicComboBoxRenderer() {
			private static final long serialVersionUID = 45692578919059504L;

			@Override
			public void paint(final Graphics g) {
				this.setForeground(Color.BLACK);
				super.paint(g);
			}
		});
		if(firstNull) {
			tipoPantallaComboBox.insertItemAt(null, 0);
			tipoPantallaComboBox.setSelectedIndex(-1);
		}
		tipoPantallaComboBox.setPreferredSize(new Dimension(new Double(tipoPantallaComboBox.getPreferredSize().getWidth()).intValue(), 20));
		return tipoPantallaComboBox;
	}
	
	@SuppressWarnings("unchecked")
	public static JComboBox<Boolean> getBooleanComboBox(final boolean firstNull) {
        final JComboBox<Boolean> booleanComboBox = new JComboBox<Boolean>();
        booleanComboBox.setRenderer(new BasicComboBoxRenderer() {
            private static final long serialVersionUID = -995394277457807877L;
            
            @Override
            public void paint(final Graphics g) {
                this.setForeground(Color.BLACK);
                super.paint(g);
            }
            
            @SuppressWarnings("rawtypes")
			@Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                if (value != null) {
                    value = (Boolean.TRUE.equals(value) ? "Sí" : "No");
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        booleanComboBox.addItem(Boolean.TRUE);
        booleanComboBox.addItem(Boolean.FALSE);
        if (firstNull) {
            booleanComboBox.insertItemAt(null, 0);
            booleanComboBox.setSelectedIndex(-1);
        }
        booleanComboBox.setPreferredSize(new Dimension(new Double(booleanComboBox.getPreferredSize().getWidth()).intValue(), 20));
        return booleanComboBox;
    }

	@SuppressWarnings("unchecked")
	public static JComboBox<SeccionTienda> getSeccionAccesorioComboBox(final boolean firstNull) {
		List<SeccionTienda> seccionTiendaValues = new ArrayList<SeccionTienda>();
		for(SeccionTienda seccionTiendaValue : SeccionTienda.values()) {
			if(!SeccionTienda.ACCESORIOS.equals(seccionTiendaValue)) {
				seccionTiendaValues.add(seccionTiendaValue);
			}
		}
		final JComboBox<SeccionTienda> seccionAccesorioComboBox = new JComboBox<SeccionTienda>(seccionTiendaValues.toArray(new SeccionTienda[seccionTiendaValues.size()]));
		seccionAccesorioComboBox.setRenderer(new BasicComboBoxRenderer() {
			private static final long serialVersionUID = -3253318314306600291L;

			@Override
			public void paint(final Graphics g) {
				this.setForeground(Color.BLACK);
				super.paint(g);
			}
			
			@SuppressWarnings("rawtypes")
			@Override
			public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
				if(value != null) {
					value = ((SeccionTienda)value).getNombre();
				}
				return super.getListCellRendererComponent(list,  value, index, isSelected, cellHasFocus);
			}
		});
		if(firstNull) {
			seccionAccesorioComboBox.insertItemAt(null, 0);
			seccionAccesorioComboBox.setSelectedItem(-1);
		}
		seccionAccesorioComboBox.setPreferredSize(new Dimension(new Double(seccionAccesorioComboBox.getPreferredSize().getWidth()).intValue(), 20));
		return seccionAccesorioComboBox;
	}
	
	@SuppressWarnings("unchecked")
	public static JComboBox<SeccionTienda> getSeccionTiendaComboBox(final boolean firstNull) {
		final JComboBox<SeccionTienda> seccionTiendaComboBox = new JComboBox<SeccionTienda>(SeccionTienda.values());
		seccionTiendaComboBox.setRenderer(new BasicComboBoxRenderer() {
			private static final long serialVersionUID = -6099589429272293097L;

			@Override
			public void paint(final Graphics g) {
				this.setForeground(Color.BLACK);
				super.paint(g);
			}
			
			@SuppressWarnings("rawtypes")
			@Override
			public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
				if(value != null) {
					value = ((SeccionTienda)value).getNombre();
				}
				return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			}
		});
		if(firstNull) {
			seccionTiendaComboBox.insertItemAt(null, 0);
			seccionTiendaComboBox.setSelectedIndex(-1);
		}
		seccionTiendaComboBox.setPreferredSize(new Dimension(new Double(seccionTiendaComboBox.getPreferredSize().getWidth()).intValue(), 20));
		return seccionTiendaComboBox;
	}
	
    @SuppressWarnings("unchecked")
	public static JComboBox<CategoriaEmpleado> getCategoriaEmpleadoComboBox(final boolean firstNull) {
        final JComboBox<CategoriaEmpleado> categoriaEmpleadoComboBox = new JComboBox<CategoriaEmpleado>(CategoriaEmpleado.values());
        categoriaEmpleadoComboBox.setRenderer(new BasicComboBoxRenderer() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public void paint(final Graphics g) {
                this.setForeground(Color.BLACK);
                super.paint(g);
            }
            
            @SuppressWarnings("rawtypes")
			@Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                if (value != null) {
                    value = ((CategoriaEmpleado)value).getNombre();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        if (firstNull) {
            categoriaEmpleadoComboBox.insertItemAt(null, 0);
            categoriaEmpleadoComboBox.setSelectedIndex(-1);
        }
        categoriaEmpleadoComboBox.setPreferredSize(new Dimension(new Double(categoriaEmpleadoComboBox.getPreferredSize().getWidth()).intValue(), 20));
        return categoriaEmpleadoComboBox;
    }

    @SuppressWarnings("unchecked")
	public static JComboBox<Provincias> getProvinciaComboBox(final boolean firstNull) {
        final JComboBox<Provincias> provinciaComboBox = new JComboBox<Provincias>(Provincias.values());
        provinciaComboBox.setRenderer(new BasicComboBoxRenderer() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public void paint(final Graphics g) {
                this.setForeground(Color.BLACK);
                super.paint(g);
            }
            
            @SuppressWarnings("rawtypes")
			@Override
            public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                if (value != null) {
                    value = ((Provincias)value).getNombre();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        if (firstNull) {
            provinciaComboBox.insertItemAt(null, 0);
            provinciaComboBox.setSelectedIndex(-1);
        }
        provinciaComboBox.setPreferredSize(new Dimension(new Double(provinciaComboBox.getPreferredSize().getWidth()).intValue(), 20));
        return provinciaComboBox;
    }
    
    @SuppressWarnings("unchecked")
	public static JComboBox<String> getStringComboBox(final List<String> stringValues, final boolean firstNull) {
    	final JComboBox<String> stringComboBox = new JComboBox<String>(stringValues != null ? stringValues.toArray(new String[stringValues.size()]) : new String[0]);
    	stringComboBox.setRenderer(new BasicComboBoxRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
    		public void paint(final Graphics g) {
    			this.setForeground(Color.BLACK);
    			super.paint(g);
    		}
    	});
    	if(firstNull) {
    		stringComboBox.insertItemAt(null, 0);
    		stringComboBox.setSelectedIndex(-1);
    	}
    	stringComboBox.setPreferredSize(new Dimension(new Double(stringComboBox.getPreferredSize().getWidth()).intValue(), 20));
    	return stringComboBox;
    }
    
    public static JButton createOptionButton(final String buttonText, final String iconFilename) {
        final InputStream iconFile = GUIUtils.class.getClassLoader().getResourceAsStream("resources/images/".concat(iconFilename));
        final JButton button = new JButton(buttonText);
        try {
            button.setIcon(new ImageIcon(ImageIO.read(iconFile).getScaledInstance(-1, 80, 4)));
        }
        catch (Exception ex) {}
        finally {
            if(iconFile != null) {
	        	try {
	                iconFile.close();
	            }
	            catch (Exception e) {
	            	//Nothing to do}
	            }
            }
        }
        button.setHorizontalTextPosition(0);
        button.setVerticalTextPosition(3);
        button.setIconTextGap(20);
        button.setFocusPainted(false);
        button.setBackground(new Color(221, 221, 221));
        button.setFont(new Font(button.getFont().getName(), 1, 13));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return button;
    }
    
    public static JButton createIconButton(final String text, final String file) {
        final JButton button = new JButton(text);
        if (file != null) {
            InputStream iconFile = null;
            try {
                iconFile = GUIUtils.class.getClassLoader().getResourceAsStream("resources/images/".concat(file));
                button.setIcon(new ImageIcon(ImageIO.read(iconFile).getScaledInstance(-1, 14, 4)));
            }
            catch (Exception ex) {}
            finally {
                try {
                    iconFile.close();
                }
                catch (Exception ex2) {}
            }
            try {
                iconFile.close();
            }
            catch (Exception ex3) {}
        }
        button.setHorizontalTextPosition(4);
        button.setVerticalTextPosition(0);
        button.setIconTextGap(6);
        button.setFocusPainted(false);
        button.setBackground(new Color(221, 221, 221));
        button.setFont(new Font(button.getFont().getName(), 1, 12));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        return button;
    }
    
    public static JButton createRevisionInicialButton() {
        return createButton("Revisi\u00f3n inicial", "tareas.png");
    }
    
    public static JButton createCallButton() {
        return createButton("Avisar cliente", "callClient.png");
    }
    
    public static JButton createManageTrabajosReparacion() {
        return createButton("Gestionar trabajos", "trabajos.png");
    }
    
    public static JButton createSearchIconButton() {
        return createIconButton("Buscar", "detalle.png");
    }
    
    public static JButton createSaveButton() {
        return createButton("Guardar", "guardar.png");
    }
    
    public static JButton createAsignarButton() {
        return createButton("Asignar", "add.png");
    }
    
    public static JButton createBackButton() {
        return createButton("Volver", "backarrow.png");
    }
    
    public static JButton createSearchButton() {
        return createButton("Buscar", "busqueda.png");
    }
    
    public static JButton createAddButton() {
        return createButton("A\u00f1adir", "add.png");
    }
    
    public static JButton createDeleteButton() {
        return createButton("Eliminar", "delete.png");
    }
    
    public static JButton createModifyButton() {
        return createButton("Modificar", "modificar.png");
    }
    
    public static JButton createDetailBigButton() {
        return createButton("Detalle", "detalle.png");
    }
    
    public static JButton createDetailButton() {
        return createSmallButton("Detalle", "detalle.png");
    }
    
    public static JButton createFilterSearchButton() {
        return createSmallButton("Buscar", "detalle.png");
    }
    
    public static JButton createFilterRemoveButton() {
        return createSmallButton("Borrar filtros", "emptyFilter.png");
    }
    
    public static JButton createApplyButton() {
        return createButton("Aplicar", "apply.png");
    }
    
    public static JButton createSmallButton(final String text, final String file) {
        final JButton button = new JButton(text);
        if (file != null) {
            final InputStream iconFile = GUIUtils.class.getClassLoader().getResourceAsStream("resources/images/".concat(file));
            try {
                button.setIcon(new ImageIcon(ImageIO.read(iconFile).getScaledInstance(-1, 18, 4)));
            }
            catch (Exception ex) {}
            finally {
                try {
                    iconFile.close();
                }
                catch (Exception ex2) {}
            }
            try {
                iconFile.close();
            }
            catch (Exception ex3) {}
        }
        button.setHorizontalTextPosition(4);
        button.setVerticalTextPosition(0);
        button.setIconTextGap(6);
        button.setFocusPainted(false);
        button.setBackground(new Color(221, 221, 221));
        button.setFont(new Font(button.getFont().getName(), 1, 12));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        return button;
    }
    
    public static JButton createButton(final String text, final String file) {
        final InputStream iconFile = GUIUtils.class.getClassLoader().getResourceAsStream("resources/images/".concat(file));
        final JButton button = new JButton(text);
        try {
            button.setIcon(new ImageIcon(ImageIO.read(iconFile).getScaledInstance(-1, 29, 4)));
        }
        catch (Exception ex) {}
        finally {
            try {
                iconFile.close();
            }
            catch (Exception ex2) {}
        }
        try {
            iconFile.close();
        }
        catch (Exception ex3) {}
        button.setHorizontalTextPosition(4);
        button.setVerticalTextPosition(0);
        button.setIconTextGap(6);
        button.setFocusPainted(false);
        button.setBackground(new Color(221, 221, 221));
        button.setFont(new Font(button.getFont().getName(), 1, 13));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        return button;
    }
    
    public static JPanel createOptionsPanel(final int width, final int height) {
        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));
        panel.setLayout(new GridLayout(1, 3, 100, 300));
        panel.setOpaque(false);
        return panel;
    }
    
    public static JLabel createOptionsPanelLabel(final String labelText) {
        final JLabel label = new JLabel(labelText);
        label.setFont(new Font(label.getFont().getName(), 3, 16));
        label.setOpaque(false);
        label.setForeground(Color.WHITE);
        return label;
    }
    
    public static JPanel createOptionsLabelPanel() {
        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 35));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setBackground(new Color(92, 98, 181));
        panel.setOpaque(true);
        panel.setLayout(new FlowLayout(0));
        return panel;
    }
}
