package es.osalguero.tiendaelect.gui.component;

import javax.swing.*;
import java.text.*;
import java.util.*;
import java.awt.*;
import java.awt.font.*;
import java.math.*;
import javax.swing.table.*;

public class ElementoTiendaGenericoTable extends JTable
{
    private static final long serialVersionUID = -7369004813937651333L;
    protected DefaultTableCellRenderer leftRenderer;
    protected DefaultTableCellRenderer rightRenderer;
    protected DefaultTableCellRenderer centerRenderer;
    protected TableCellRenderer dateCellRenderer;
    
    public ElementoTiendaGenericoTable(final DefaultTableModel defaultTableModel) {
        super(defaultTableModel);
        this.leftRenderer = new DefaultTableCellRenderer();
        this.rightRenderer = new DefaultTableCellRenderer();
        this.centerRenderer = new DefaultTableCellRenderer();
        this.dateCellRenderer = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 8489802554271022989L;
            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            
            @Override
            public Component getTableCellRendererComponent(final JTable table, Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
                if (value != null && value instanceof Date) {
                    value = this.f.format(value);
                }
                else {
                    value = "-";
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        this.leftRenderer.setHorizontalAlignment(2);
        this.rightRenderer.setHorizontalAlignment(4);
        this.centerRenderer.setHorizontalAlignment(0);
        ((DefaultTableCellRenderer)this.dateCellRenderer).setHorizontalAlignment(4);
    }
    
    @Override
    public Component prepareRenderer(final TableCellRenderer renderer, final int row, final int column) {
        final Component component = super.prepareRenderer(renderer, row, column);
        final int rendererWidth = component.getPreferredSize().width;
        final TableColumn tableColumn = this.getColumnModel().getColumn(column);
        int headerWidth = -1;
        if (tableColumn.getHeaderValue() instanceof String) {
            final Font font = new Font(this.getTableHeader().getFont().getName(), 1, 14);
            headerWidth = new BigDecimal(font.getStringBounds((String)tableColumn.getHeaderValue(), new FontRenderContext(font.getTransform(), false, false)).getBounds().getWidth()).setScale(0, 2).intValue() + 10;
        }
        tableColumn.setPreferredWidth(Math.max(rendererWidth + this.getIntercellSpacing().width, Math.max(tableColumn.getPreferredWidth(), headerWidth)));
        return component;
    }
}
