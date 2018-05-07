package es.osalguero.tiendaelect.gui.controller;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.osalguero.tiendaelect.gui.component.PanelFondo;
import es.osalguero.tiendaelect.gui.view.TiendaElectrodomesticosGUI;
import es.osalguero.tiendaelect.service.TiendaService;

public class TiendaElectrodomesticosGUIController {

	private final int APP_WIDTH = 1024;
	private final int APP_HEIGHT = 672;
	
	private TiendaService tiendaService;
	private JFrame tiendaGUI;
	private JPanel panelPrincipal;
	private JPanel backgroundPanel;
	
	public TiendaElectrodomesticosGUIController(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
		showStore();
	}

	private void showStore() {
		this.tiendaGUI = new TiendaElectrodomesticosGUI();
		this.tiendaGUI.setTitle("Tienda Electrodomesticos POO Oscar Salguero 2017-2018");
		
		double screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		//Panel con la imagen de fondo
		this.backgroundPanel = new PanelFondo(APP_WIDTH, APP_HEIGHT);
		
		//Panel que inserto en el de fondo para que sea el que se use en las pantallas,
		//así el de fondo no se modifica
		this.panelPrincipal = new JPanel();
		
		this.tiendaGUI.setBounds(
				(int)(screenWidth/2 - APP_WIDTH / 2), //Centrado en horizontal
				(int)(screenHeight/2 - APP_HEIGHT / 2), //Centrado en vertical
				APP_WIDTH, APP_HEIGHT);
		this.tiendaGUI.setResizable(false);
		this.tiendaGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		backgroundPanel.setBounds(0, 0,	APP_WIDTH, APP_HEIGHT);
		backgroundPanel.add(this.panelPrincipal);
		this.tiendaGUI.getContentPane().add(backgroundPanel);
		
		this.tiendaGUI.setVisible(true);
	}
	
}
