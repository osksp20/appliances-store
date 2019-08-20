package es.osalguero.tiendaelect.gui.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PanelTransparente extends JPanel {

	private static final long serialVersionUID = 3522369165517996609L;

	public PanelTransparente() {
		super();
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f)); // draw transparent background
		g.setColor(Color.RED);
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
	    ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f)); // turn on opacity
	    super.paintComponent(g);
	}
	
}
