package es.osalguero.tiendaelect.gui.view.gestionApp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.osalguero.tiendaelect.gui.controller.gestionApp.VistaLoginGUIController;
import es.osalguero.tiendaelect.gui.view.VistaAbstractaGUI;

public class VistaLoginGUI extends VistaAbstractaGUI<VistaLoginGUIController> implements ActionListener {

	public VistaLoginGUI(VistaLoginGUIController controller) {
		super(controller);
	}

	protected final Dimension preferredDimension = new Dimension(400, 140);
	
	private JButton loginButton;
	private JTextField txtUserInput;
	private JLabel loginNoReconocidoLabel;
	
	public void generaVista() {

		//Paneles para organizar (etiquetas, campos de datos, etc...
		JPanel loginHeadPanel = new JPanel();
		loginHeadPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		loginHeadPanel.setOpaque(true);
		loginHeadPanel.setBackground(new Color(92,98,181));
		loginHeadPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 1),
				BorderFactory.createEmptyBorder(0,6,0,6)));
		
		JPanel loginDataPanel = new JPanel();
		loginDataPanel.setLayout(new GridLayout(1,2,0,4));
		loginDataPanel.setOpaque(false);
		loginDataPanel.setBorder(BorderFactory.createEmptyBorder(6,9,0,6));
		
		JPanel loginInfoPanel = new JPanel();
		loginInfoPanel.setLayout(new GridLayout(2,1,0,4));
		loginInfoPanel.setOpaque(false);
		loginInfoPanel.setBorder(BorderFactory.createEmptyBorder(0,6,0,6));
		
		JPanel loginButtonPanel = new JPanel();
		loginButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		loginButtonPanel.setOpaque(false);
		loginButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,6,0,6));
		
		//Etiquetas de campos
		JLabel loginLabel = new JLabel("Login");
		JLabel userLabel = new JLabel("Usuario:");
		JLabel loginHelpLabel = new JLabel("Por favor, indique su nombre de usuario.");
		loginNoReconocidoLabel = new JLabel("Usuario no reconocido.");
		loginNoReconocidoLabel.setVisible(false);
		
		//Fonts de las etiquestas
		loginLabel.setFont(new Font(loginLabel.getFont().getName(), Font.BOLD, 16));
		loginLabel.setForeground(Color.white);
		loginHelpLabel.setFont(new Font(loginHelpLabel.getFont().getName(), Font.BOLD + Font.ITALIC, 12));
		userLabel.setFont(new Font(userLabel.getFont().getName(), Font.BOLD + Font.ITALIC, 16));
		loginNoReconocidoLabel.setForeground(Color.RED);
		loginNoReconocidoLabel.setFont(new Font(loginNoReconocidoLabel.getFont().getName(), Font.ITALIC + Font.BOLD, 12));
		
		//Boton para enviar datos
		loginButton = new JButton("Enviar");
		loginButton.addActionListener(this);
		
		//Campos de inserci�n de datos
		txtUserInput = new JTextField();
		txtUserInput.addActionListener(this);
		txtUserInput.setMinimumSize(new Dimension(160, txtUserInput.getHeight()));
		txtUserInput.requestFocusInWindow();
		
		//Panel loginInfo
		loginHeadPanel.add(loginLabel);
		
		//Panel loginData
		loginDataPanel.add(userLabel);
		loginDataPanel.add(txtUserInput);
		
		//Panel loginInfo
		loginInfoPanel.add(loginHelpLabel);
		loginInfoPanel.add(loginNoReconocidoLabel);
		
		//Panel loginButton
		loginButtonPanel.add(loginButton);
		
		//Panel del centro donde van los data field
		Box panelDatosBox = new Box(BoxLayout.Y_AXIS);
		panelDatosBox.setOpaque(true);
		panelDatosBox.setBackground(Color.white);
		panelDatosBox.setPreferredSize(preferredDimension);
		panelDatosBox.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		//A�ado los paneles m�s peque�os
		panelDatosBox.add(loginHeadPanel);
		panelDatosBox.add(loginDataPanel);
		panelDatosBox.add(loginInfoPanel);
		panelDatosBox.add(loginButtonPanel);
		
		//Panel del frame, alineamiento vertical
		Box verticalBox = new Box(BoxLayout.Y_AXIS);
		verticalBox.setAlignmentY(Box.CENTER_ALIGNMENT);
		
		//Resignacion
		//No consigo centrarlo en vertical de otra forma
		Component glue1 = Box.createVerticalGlue();
		glue1.setPreferredSize(new Dimension(0, 150));
		verticalBox.add(glue1);
		verticalBox.add(panelDatosBox);
		verticalBox.add(Box.createVerticalGlue());
		
		//A�ado el layout vertical al panel principal del frame
		getContainer().add(verticalBox);
	}

	public void addLoginEmpty() {
		this.loginNoReconocidoLabel.setText("Debe especificar un nombre de usuario.");
		this.loginNoReconocidoLabel.setVisible(true);
	}
	
	public void addBadLogin() {
		this.loginNoReconocidoLabel.setText("Usuario no reconocido.");
		this.loginNoReconocidoLabel.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(loginButton) || e.getSource().equals(txtUserInput)) {
			try {
				getController().login(txtUserInput.getText());
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void setDefaultFocus() {
		this.txtUserInput.requestFocusInWindow();		
	}	
}
