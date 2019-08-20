package es.osalguero.tiendaelect.gui.controller;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.osalguero.tiendaelect.gui.component.PanelFondo;
import es.osalguero.tiendaelect.gui.service.TiendaAppService;
import es.osalguero.tiendaelect.gui.view.TiendaElectrodomesticosGUI;
import es.osalguero.tiendaelect.gui.view.enumeration.TipoEdicionEnum;
import es.osalguero.tiendaelect.gui.view.enumeration.VistasEnum;
import es.osalguero.tiendaelect.modelo.ElementoTiendaGenerico;
import es.osalguero.tiendaelect.service.TiendaService;

public class TiendaElectrodomesticosGUIController implements ActionListener, WindowListener {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	private static TiendaElectrodomesticosGUIController tiendaControllerInstance;
	private TiendaAppService tiendaAppService;

	private final int APP_WIDTH = 1024;
	private final int APP_HEIGHT = 672;

	private JFrame tiendaGUI;
	private JPanel panelPrincipal, backgroundPanel;
	private JMenuBar menuBar;
	private JMenu menuArchivo, menuEditar, menuAyuda;
	private JMenuItem cerrarSesionMenuItem, salirMenuItem, manualMenuItem, infoMenuItem, guardarMenuItem, editarPropiedadesMenuItem, administrarUsuariosMenuItem;
	private VistasEnum vistaActual;
    private ElementoTiendaGenerico elementoActual;

	protected TiendaElectrodomesticosGUIController() {
	}

	public static TiendaElectrodomesticosGUIController createTiendaGUI(TiendaService tiendaService)
			throws Exception {
		if(tiendaControllerInstance != null) {
			throw new Exception("Ya existe una interfaz en ejecución.");
		}
		if(tiendaService == null) {
			throw new Exception("Debe proporcionarse una interfaz de operaciones válida.");
		}
		tiendaControllerInstance = new TiendaElectrodomesticosGUIController();
		tiendaControllerInstance.tiendaAppService = new TiendaAppService(tiendaService);
		return tiendaControllerInstance;
	}

	//Devuelve la instancia activa de la aplicación
	public static TiendaElectrodomesticosGUIController getInstance() {
		//Por si en algún punto necesito recuperar la instancia
		return tiendaControllerInstance;
	}

	public void showStore() {
		if(this.tiendaGUI == null) {
			this.tiendaGUI = new TiendaElectrodomesticosGUI();
			this.tiendaGUI.setTitle("Tienda Electrodomesticos POO Oscar Salguero 2017-2018");
			this.tiendaGUI.addWindowListener(this);
		}

		if(this.tiendaGUI.getContentPane().getComponents() == null ||
				this.tiendaGUI.getContentPane().getComponents().length == 0 ||
				this.tiendaGUI.getContentPane().getComponents()[0] == null) {

			this.initMenu();

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
		}

		this.tiendaGUI.setVisible(true);

		if(!tiendaAppService.isTiendaInicializada()) {
			this.gestionarCambioVista(VistasEnum.VISTA_CARGA_DATOS, null);
			try {
				tiendaAppService.inicializarTienda();
				//Vista principal de login
				this.gestionarCambioVista(VistasEnum.VISTA_LOGIN, null);
			} catch(Exception e) {
				this.gestionarErrorAplicacion(e);
			}
		} else {
			//Vista principal de login
			this.gestionarCambioVista(VistasEnum.VISTA_LOGIN, null);
		}
	}

	//Método que gestiona los errores de la aplicación, realiza log
	public void gestionarErrorAplicacion(Exception e) {
		logger.log(Level.SEVERE, "Se ha producido un error en la aplicación", e);
		try {
			this.gestionarCambioVista(VistasEnum.VISTA_EXCEPTION, null);
		} catch(Exception ex) {
			logger.log(Level.SEVERE, "Error intentando mostrar la pantalla de error", ex);
			ex.printStackTrace();
			System.exit(0);
		}
	}

	public void gestionarCambioVista(final VistasEnum vista, final ElementoTiendaGenerico elemento) {
        this.gestionarCambioVista(vista, elemento, null);
    }
	
	public void gestionarCambioVista(final VistasEnum vista, final ElementoTiendaGenerico elemento, final TipoEdicionEnum tipoEdicion) {
        this.cleanPanelPrincipal();
        this.checkMenus();
        try {
            final AbstractGUIController<?> vistaController = ControllerFactory.createViewController(
            		vista, this.vistaActual, this.elementoActual, this.tiendaAppService.getEmpleadoLogin(), elemento, tipoEdicion);
            vistaController.generarVista();
            if (!vista.equals(VistasEnum.VISTA_EXCEPTION) && !vista.equals(VistasEnum.VISTA_CARGA_DATOS)) {
                this.vistaActual = vista;
                this.elementoActual = elemento;
            }
        }
        catch (Exception e) {
            this.gestionarErrorAplicacion(e);
        }
        this.tiendaGUI.pack();
    }

	//Cierra los recursos de la aplicación
	protected void finalizarGUITienda() {
		this.tiendaGUI.setVisible(false);
		this.tiendaGUI = null;
	}

	//Devuelve el panel por encima del background
	public JPanel getTiendaFrameContainer() {
		return this.panelPrincipal;
	}

	//Limpia el panel por encima del background para poder generar vistas limpias
	private void cleanPanelPrincipal() {
		this.panelPrincipal.removeAll();
		this.panelPrincipal.setBounds(0, 0, APP_WIDTH, APP_HEIGHT);
		this.panelPrincipal.setPreferredSize(this.panelPrincipal.getSize());
		this.panelPrincipal.setOpaque(false);
		this.panelPrincipal.setLayout(new FlowLayout());
	}

	//Devuelve la interfaz de operaciones propia de la aplicación
	public TiendaAppService getTiendaAppService() {
		return this.tiendaAppService;
	}

	//Crea la barra de menú
	private void initMenu() {
		menuBar = new JMenuBar();

		menuArchivo = new JMenu("Archivo");
		menuEditar  = new JMenu("Editar");
		menuAyuda = new JMenu("Ayuda");

		guardarMenuItem = new JMenuItem("Guardar");
		cerrarSesionMenuItem = new JMenuItem("Cerrar sesión");
		salirMenuItem = new JMenuItem("Salir");
		manualMenuItem = new JMenuItem("Cómo usar la aplicación");
		infoMenuItem = new JMenuItem("Acerca de...");
		editarPropiedadesMenuItem = new JMenuItem("Configuración");
		administrarUsuariosMenuItem = new JMenuItem("Administrar usuarios");

		guardarMenuItem.addActionListener(this);
		cerrarSesionMenuItem.addActionListener(this);
		salirMenuItem.addActionListener(this);
		manualMenuItem.addActionListener(this);
		infoMenuItem.addActionListener(this);
		editarPropiedadesMenuItem.addActionListener(this);
		administrarUsuariosMenuItem.addActionListener(this);

		menuArchivo.add(guardarMenuItem);
		menuArchivo.add(cerrarSesionMenuItem);
		menuArchivo.addSeparator();
		menuArchivo.add(salirMenuItem);
		menuEditar.add(editarPropiedadesMenuItem);
		menuAyuda.add(manualMenuItem);
		menuAyuda.add(infoMenuItem);

		menuBar.add(menuArchivo);
		menuBar.add(menuEditar);
		menuBar.add(menuAyuda);

		this.tiendaGUI.setJMenuBar(menuBar);
	}

	private void checkMenus() {
		if(this.tiendaAppService != null && this.tiendaAppService.getEmpleadoLogin() != null) {
			this.activaMenus();
		} else {
			this.desactivaMenus();
		}
	}

	//Activa algunas opciones del menú
	private void activaMenus() {
		guardarMenuItem.setEnabled(true);
		cerrarSesionMenuItem.setEnabled(true);
		if(this.tiendaAppService.getEmpleadoLogin().esAdministrador()) {
			menuEditar.add(administrarUsuariosMenuItem, 0);
		}
	}

	//Desactiva algunas opciones del menú
	private void desactivaMenus() {
		guardarMenuItem.setEnabled(false);
		cerrarSesionMenuItem.setEnabled(false);
		try {
			menuEditar.remove(administrarUsuariosMenuItem);
		} catch(Exception e) {
			//Nothing
		}
	}

	//Gestiona los componentes del menú
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.salirMenuItem)) {
			if(!this.tiendaAppService.getCambiosSinGuardar() || confirmarSalir() == JOptionPane.OK_OPTION) {
				this.tiendaGUI.setVisible(false);
				this.tiendaGUI.dispose();
				System.exit(0);
			}
		} else if(e.getSource().equals(cerrarSesionMenuItem)) {
			if(!this.tiendaAppService.getCambiosSinGuardar() || confirmarSalir() == JOptionPane.OK_OPTION) {
				this.tiendaAppService.logout();
				this.gestionarCambioVista(VistasEnum.VISTA_LOGIN, null);
			}
		}
		else if (e.getSource().equals(this.administrarUsuariosMenuItem)) {
            this.gestionarCambioVista(VistasEnum.VISTA_LISTADO_EMPLEADOS, null);
        }
		 else if (e.getSource().equals(this.guardarMenuItem)) {
	            try {
	                this.tiendaAppService.guardarDatosTienda();
	                JOptionPane.showMessageDialog(this.getTiendaFrameContainer(), "Los cambios se han guardado correctamente.", "Operación realizada", -1);
	            }
	            catch (Exception ex) {
	            	logger.log(Level.SEVERE, "Error intentnado guardar los datos del taller", ex);
	                JOptionPane.showMessageDialog(this.getTiendaFrameContainer(), "Se ha producido un error guardando los datos de la aplicación.\nPor favor revise los logs de la aplicación.", "Error", 0);
	            }
	        }
	        else if (e.getSource().equals(this.editarPropiedadesMenuItem)) {
	            this.gestionarCambioVista(VistasEnum.VISTA_CONFIGURACION, null);
	        }
	}

	//Dialog para confirmar que se quiere cerrar o salir sin guardar los cambios
	private int confirmarSalir() {
		return JOptionPane.showConfirmDialog(
				this.tiendaGUI, "Atención, si sale de la aplicación no se guardarán los cambios realizados.",
				"Cambios sin guardar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	}

	public void goBack() {
        this.gestionarCambioVista(this.vistaActual, this.elementoActual);
    }
	
	public VistasEnum getVistaActual() {
        return this.vistaActual;
    }
	
	public void setElementoActual(final ElementoTiendaGenerico elementoTienda) {
        this.elementoActual = elementoTienda;
    }
    
    @Override
    public void windowOpened(final WindowEvent e) {
    }
    
    @Override
    public void windowClosing(final WindowEvent e) {
        if (!this.tiendaAppService.getCambiosSinGuardar() || this.confirmarSalir() == 0) {
            this.tiendaGUI.setVisible(false);
            this.tiendaGUI.dispose();
            System.exit(0);
        }
    }

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
