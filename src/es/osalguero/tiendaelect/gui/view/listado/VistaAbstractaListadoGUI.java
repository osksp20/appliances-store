package es.osalguero.tiendaelect.gui.view.listado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import es.osalguero.tiendaelect.gui.controller.listado.VistaAbstractaListadoGUIController;
import es.osalguero.tiendaelect.gui.exception.FilterFormatException;
import es.osalguero.tiendaelect.gui.util.GUIUtils;
import es.osalguero.tiendaelect.gui.view.VistaAbstractaGUI;
import es.osalguero.tiendaelect.gui.view.busqueda.ModeloBusqueda;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;

public abstract class VistaAbstractaListadoGUI<T extends VistaAbstractaListadoGUIController<?, S, V>,
	S extends ElementoTiendaGenerico, V extends ModeloBusqueda> extends VistaAbstractaGUI<VistaAbstractaListadoGUIController<?, S, V>>
	implements ActionListener, ListSelectionListener
{
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
    protected JButton backButton;
    protected JButton detailElementButton;
    protected JButton newElementButton;
    protected JButton deleteElementButton;
    protected JButton filterSearchButton;
    protected JButton filterRemoveButton;
    protected JLabel labelListadoElementos;
    protected JTable elementsTable;
    protected JPanel panelLabelListadoElementos;
    protected JPanel panelListadoElementos;
    protected JPanel bottomPane;
    protected JPanel panelBusqueda;
    protected JPanel searchLabelPanel;
    protected JPanel searchElementsPanel;
    protected JPanel filterPanel;
    protected JScrollPane scrollPaneListadoElementos;
    protected List<S> elementos;
    protected int totalElementos;
    protected final String title;
    protected final int searchPanelElementsHeight;
    protected final int listPanelHeight;
    protected ElementoTiendaGenerico elementoPadre;

    private VistaAbstractaListadoGUI(final T controller) {
        super(controller);
        this.elementos = new ArrayList<S>();
        this.totalElementos = 0;
        this.title = this.getListadoTitle();
        this.searchPanelElementsHeight = this.getSearchPanelHeight();
        this.listPanelHeight = 440 - this.searchPanelElementsHeight;
    }
    
    public VistaAbstractaListadoGUI(final T controller, final ElementoTiendaGenerico elementoPadre) {
        super(controller);
        this.elementos = new ArrayList<S>();
        this.totalElementos = 0;
        this.elementoPadre = elementoPadre;
        this.title = this.getListadoTitle();
        this.searchPanelElementsHeight = this.getSearchPanelHeight();
        this.listPanelHeight = 440 - this.searchPanelElementsHeight;
        this.panelLabelListadoElementos = GUIUtils.createOptionsLabelPanel();
        this.labelListadoElementos = GUIUtils.createOptionsPanelLabel(this.title);
        this.panelLabelListadoElementos.add(this.labelListadoElementos);
        (this.panelListadoElementos = new JPanel()).setPreferredSize(new Dimension(this.getContainer().getWidth() - 10, 521));
        this.panelListadoElementos.setOpaque(true);
        (this.bottomPane = new JPanel()).setPreferredSize(new Dimension(this.getContainer().getWidth(), 40));
        this.bottomPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        this.bottomPane.setOpaque(false);
        (this.backButton = GUIUtils.createBackButton()).addActionListener(this);
        (this.newElementButton = GUIUtils.createAddButton()).addActionListener(this);
        (this.deleteElementButton = GUIUtils.createDeleteButton()).addActionListener(this);
        (this.detailElementButton = GUIUtils.createDetailBigButton()).addActionListener(this);
        final SpringLayout layout = new SpringLayout();
        this.bottomPane.add(this.backButton);
        this.bottomPane.add(this.newElementButton);
        this.bottomPane.add(this.deleteElementButton);
        this.bottomPane.add(this.detailElementButton);
        layout.putConstraint(SpringLayout.WEST, this.backButton, 0, SpringLayout.WEST, this.bottomPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.newElementButton, new Double(this.bottomPane.getPreferredSize().getWidth() / 6.0).intValue() * -1, SpringLayout.HORIZONTAL_CENTER, this.bottomPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.deleteElementButton, new Double(this.bottomPane.getPreferredSize().getWidth() / 6.0).intValue(), SpringLayout.HORIZONTAL_CENTER, this.bottomPane);
        layout.putConstraint("East", this.detailElementButton, -2, SpringLayout.EAST, this.bottomPane);
        this.bottomPane.setLayout(layout);
        this.detailElementButton.setEnabled(false);
        this.deleteElementButton.setEnabled(false);
        (this.panelBusqueda = new JPanel()).setPreferredSize(new Dimension(this.getContainer().getWidth() - 12, this.searchPanelElementsHeight + 76));
        this.panelBusqueda.setLayout(new FlowLayout(0));
        (this.searchLabelPanel = new JPanel()).setLayout(new FlowLayout(0));
        this.searchLabelPanel.add(new JLabel("Filtros de búsqueda"));
        this.panelBusqueda.add(this.searchLabelPanel);
        final JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(new Double(this.panelBusqueda.getPreferredSize().getWidth()).intValue() - 12, 1));
        separator.setForeground(Color.BLACK);
        this.panelBusqueda.add(separator);
        (this.searchElementsPanel = new JPanel()).setPreferredSize(new Dimension(this.getContainer().getWidth() - 12, this.searchPanelElementsHeight));
        this.searchElementsPanel.setLayout(new FlowLayout(0));
        this.fillSearchElementsPanel(this.searchElementsPanel);
        this.panelBusqueda.add(this.searchElementsPanel);
        (this.filterPanel = new JPanel()).setPreferredSize(new Dimension(new Double(this.panelBusqueda.getPreferredSize().getWidth()).intValue() - 12, 24));
        this.filterPanel.setLayout(new FlowLayout(2, 0, 0));
        (this.filterSearchButton = GUIUtils.createFilterSearchButton()).addActionListener(this);
        (this.filterRemoveButton = GUIUtils.createFilterRemoveButton()).addActionListener(this);
        this.filterPanel.add(this.filterRemoveButton);
        this.filterPanel.add(this.createSeparator(10));
        this.filterPanel.add(this.filterSearchButton);
        this.panelBusqueda.add(this.filterPanel);
    }
    
    @Override
    public void generaVista() throws Exception {
        this.getContainer().removeAll();
        this.getContainer().setLayout(new FlowLayout(0));
        this.getContainer().add(this.panelLabelListadoElementos);
        if (this.totalElementos == 0) {
            this.panelListadoElementos.add(new JLabel("No existen elementos..."));
            this.getContainer().add(this.panelListadoElementos);
        }
        else {
            this.elementsTable = this.createElementsTable(this.elementos);
            this.elementsTable.getSelectionModel().addListSelectionListener(this);
            ((DefaultTableCellRenderer)this.elementsTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(2);
            this.elementsTable.getTableHeader().setFont(new Font(this.elementsTable.getTableHeader().getFont().getName(), 1, 12));
            this.elementsTable.setIntercellSpacing(new Dimension(6, 1));
            (this.scrollPaneListadoElementos = new JScrollPane(this.elementsTable)).setPreferredSize(new Dimension(this.getContainer().getWidth() - 12, this.listPanelHeight));
            this.scrollPaneListadoElementos.getViewport().setBackground(Color.WHITE);
            this.getContainer().add(this.panelBusqueda);
            this.getContainer().add(this.scrollPaneListadoElementos);
        }
        this.getContainer().add(this.bottomPane);
        this.getContainer().revalidate();
        this.getContainer().repaint();
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource().equals(this.backButton)) {
            this.getController().goToVista(this.getVistaAnterior(), this.getElementoAnterior());
        }
        else if (e.getSource().equals(this.filterRemoveButton)) {
            this.emptySearchFilters();
            final boolean selected = this.elementsTable.getSelectedRow() > -1;
            this.deleteElementButton.setEnabled(selected);
            this.detailElementButton.setEnabled(selected);
        }
        else if (e.getSource().equals(this.filterSearchButton)) {
            this.searchForElements();
            final boolean selected = this.elementsTable.getSelectedRow() > -1;
            this.deleteElementButton.setEnabled(selected);
            this.detailElementButton.setEnabled(selected);
        }
        else if (e.getSource().equals(this.deleteElementButton)) {
            if (JOptionPane.showConfirmDialog(this.getContainer(), this.getDeleteWarningMessage(), "Confirmar acción.", 2) == 0) {
                S elemento = null;
                try {
                    elemento = this.elementos.get(this.elementsTable.convertRowIndexToModel(this.elementsTable.getSelectedRow()));
                    if (elemento == null) {
                        throw new Exception("elemento = null");
                    }
                }
                catch (Exception ex) {
                	logger.log(Level.SEVERE, "Error recuperando elemento del listado para eliminar", ex);
                    this.getController().showError("Se ha producido un error intentando recuperar los datos del elemento a eliminar.\nPor favor, revise los logs de la aplicación");
                }
                try {
                    this.getController().delete(elemento, this.elementoPadre);
                    JOptionPane.showMessageDialog(this.getContainer(), "El elemento se ha eliminado correctamente.\nPor favor, recuerde guardar los cambios a través del menú 'Archivo' -> 'Guardar cambios'\npara que estos sean permanentes.", "Operación realizada", -1);
                }
//                catch (AppException appEx) {
//                    this.getController().showError(appEx.getMessage());
//                }
                catch (Exception ex) {
                	logger.log(Level.SEVERE, "Error en la aplicación", ex);
                    this.getController().showError("Se ha producido un error intentando eliminar el elemento seleccionado.");
                }
                final boolean selected = this.elementsTable.getSelectedRow() > -1 && this.elementos != null && !this.elementos.isEmpty();
                this.deleteElementButton.setEnabled(selected);
                this.detailElementButton.setEnabled(selected);
            }
        }
        else if (e.getSource().equals(this.newElementButton)) {
            this.getController().goToNewElement();
        }
        else if (e.getSource().equals(this.detailElementButton)) {
            S elemento = null;
            try {
                elemento = this.elementos.get(this.elementsTable.convertRowIndexToModel(this.elementsTable.getSelectedRow()));
                if (elemento == null) {
                    throw new Exception("elemento = null");
                }
            }
            catch (Exception ex) {
            	logger.log(Level.SEVERE, "Error recuperando elemento del listado para editar", ex);
                this.getController().showError("Se ha producido un error intentando recuperar los datos del elemento a editar.\nPor favor, revise los logs de la aplicación.");
            }
            this.getController().editarElemento(elemento);
        }
    }
    
    public void setTotalElementos(final int totalElementos) {
        this.totalElementos = totalElementos;
    }
    
    @Override
    public void valueChanged(final ListSelectionEvent e) {
        if (e.getSource().equals(this.elementsTable.getSelectionModel())) {
            final boolean selected = this.elementsTable.getSelectedRow() > -1;
            this.deleteElementButton.setEnabled(selected);
            this.detailElementButton.setEnabled(selected);
        }
    }
    
    public void setElementos(final List<S> elementos) {
        if (elementos != null) {
            this.elementos = elementos;
        }
        else {
            this.elementos = new ArrayList<S>();
        }
    }
    
    private void searchForElements() {
        V elementoBusqueda = null;
        try {
            elementoBusqueda = this.createElementoBusqueda();
        }
        catch (FilterFormatException formatException) {
            this.getController().showError(formatException.getMessage());
        }
        catch (Exception ex) {
        	logger.log(Level.SEVERE, "Error intentando recuperar los parámetros de búsqueda.", ex);
            this.getController().showError("No se han podido obtener los parámetros de búsqueda.\nConsulte el log de la aplicación.");
        }
        if (elementoBusqueda != null) {
            try {
                this.getController().busqueda(elementoBusqueda, this.elementoPadre);
            }
            catch (Exception e) {
            	logger.log(Level.SEVERE, "Error realizando la búsqueda.", e);
                this.getController().showError("Se ha producido un error realizando la búsqueda.");
            }
        }
    }
    
    protected abstract V createElementoBusqueda() throws FilterFormatException, Exception;
    
    protected abstract void emptySearchFilters();
    
    protected abstract void fillSearchElementsPanel(final JPanel p0);
    
    protected abstract JTable createElementsTable(final List<S> p0);
    
    protected abstract String getListadoTitle();
    
    protected abstract int getSearchPanelHeight();
    
    protected String getDeleteWarningMessage() {
        return "¿Está seguro de eliminar el elemento?. No podrá recuperarse.";
    }
    
    protected abstract VistasEnum getVistaAnterior();
    
    protected abstract ElementoTiendaGenerico getElementoAnterior();
}
