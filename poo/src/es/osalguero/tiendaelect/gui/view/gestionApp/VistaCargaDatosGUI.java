package es.osalguero.tiendaelect.gui.view.gestionApp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.osalguero.tiendaelect.gui.controller.gestionApp.VistaCargaDatosGUIController;
import es.osalguero.tiendaelect.gui.view.VistaAbstractaGUI;

public class VistaCargaDatosGUI extends VistaAbstractaGUI<VistaCargaDatosGUIController>
{
    private final String MENSAJE_LOADING_TITLE = "<html><p align=\"center\" style=\"font-size:12px\">Por favor, espere mientas se cargan los datos de la aplicación...</p></html>";
    
    public VistaCargaDatosGUI(final VistaCargaDatosGUIController controller) {
        super(controller);
    }
    
    @Override
    public void generaVista() throws Exception {
        try {
            final BufferedImage bimg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("resources/images/loading.png"));
            final JLabel labelIcon = new JLabel(new ImageIcon(bimg.getScaledInstance(580, 320, 4)));
            final JLabel labelDescripcion = new JLabel(MENSAJE_LOADING_TITLE);
            final Box panelDatosBox = new Box(BoxLayout.Y_AXIS);
            panelDatosBox.setOpaque(true);
            panelDatosBox.setBackground(Color.white);
            panelDatosBox.setPreferredSize(new Dimension(580, 320));
            panelDatosBox.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            final JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEmptyBorder(-9, 0, 0, 0));
            panel.add(labelIcon);
            panel.add(labelDescripcion);
            panel.setOpaque(false);
            panelDatosBox.add(panel);
            final Box verticalBox = new Box(1);
            verticalBox.setAlignmentY(0.5f);
            final Component glue1 = Box.createVerticalGlue();
            glue1.setPreferredSize(new Dimension(0, 120));
            verticalBox.add(glue1);
            verticalBox.add(panelDatosBox);
            verticalBox.add(Box.createVerticalGlue());
            this.getContainer().add(verticalBox);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
