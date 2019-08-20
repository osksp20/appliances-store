package es.osalguero.tiendaelect.gui.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/* Obtained from https://coderanch.com/t/629524/java/set-opacity-JLabel-JLabel-Image */

public class PanelFondo extends JPanel {

	private static final long serialVersionUID = -8646185134893238702L;

	private Image transImage;
	
	public PanelFondo(int width, int height) {
		this.setSize(width, height);
		try {
			//Esto es para leer las imágenes que van a estar contenidas en el jar construido
			InputStream backgroundFile = getClass().getClassLoader().getResourceAsStream("resources/images/fondoPrincipal.png");
			if(backgroundFile != null) {
				this.transImage = ImageIO.read(backgroundFile).getScaledInstance(width, height, Image.SCALE_SMOOTH);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.getWidth(), this.getHeight());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.transImage != null) {
			g.drawImage(transImage, 0, 0, null);
		}
	}
}
