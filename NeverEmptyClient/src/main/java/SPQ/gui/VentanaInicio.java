package SPQ.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import SPQ.controller.NeverEmptyController;
import SPQ.remote.RMIServiceLocator;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JTable;

public class VentanaInicio extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUserFinal;
	private JPasswordField passwordFieldFinal;
	private JButton botonLogin;
	private NeverEmptyController neverEmptyController;

	public static void main(String[] args) {
		VentanaInicio vi = new VentanaInicio(null);
		vi.setVisible(true);
	}

	public VentanaInicio(NeverEmptyController neverEmptyController) {
		this.neverEmptyController = neverEmptyController;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		int width = (int)screenWidth/3;
		int height = (int)screenHeight/3;
		setSize(800, 400);
		setLocationRelativeTo(null);

		getContentPane().setBackground(new Color(50, 50, 50));

		setResizable(false);
		setUndecorated(true);

		getContentPane().setLayout(null);
		setContentPane(new JLabel(getImageFromResources("bg-metalic.jpg")));

		Font titleBold = new Font("Arial", Font.BOLD, 55);
		Font textPlain = new Font("Arial", Font.PLAIN, 16);
		this.setTitle("NeverEmpty");
		JLabel lblNeverEmpty = new JLabel("NeverEmpty", SwingConstants.CENTER);
		lblNeverEmpty.setBounds(200, 0, 400, 150);
		lblNeverEmpty.setFont(titleBold);
		lblNeverEmpty.setForeground(new Color(253, 253, 253));

		getContentPane().add(lblNeverEmpty);

		JButton btnSignUp = new JButton("Registro");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registro();
			}
		});
		btnSignUp.setContentAreaFilled(false);
		btnSignUp.setBorder(BorderFactory.createLineBorder(Color.white,2));
		btnSignUp.setForeground(Color.white);
		btnSignUp.setBounds(425, 300, 150, 30);
		btnSignUp.setFont(textPlain);
		getContentPane().add(btnSignUp);

		JButton btnSalir = new JButton();
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(750, 20, 30, 30);
		ImageIcon closeIcon = getImageFromResources("close-white.png");
		btnSalir.setIcon(closeIcon);
		btnSalir.setSize(30, 30);
		btnSalir.setPreferredSize(new Dimension(30, 30));
		btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
		btnSalir.setOpaque(false);
		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.setBorder(null);

		getContentPane().add(btnSalir);

		textFieldUserFinal = new JTextField();
		textFieldUserFinal.setBounds(350, 150, 200, 30);
		textFieldUserFinal.setBorder(null);
		getContentPane().add(textFieldUserFinal);
		textFieldUserFinal.setColumns(10);

		passwordFieldFinal = new JPasswordField();
		passwordFieldFinal.setBorder(null);
		passwordFieldFinal.setBounds(350, 200, 200, 30);
		getContentPane().add(passwordFieldFinal);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(textPlain);
		lblUsuario.setForeground(new Color(253, 253, 253));
		lblUsuario.setBounds(250, 155, 100, 16);
		getContentPane().add(lblUsuario);

		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(textPlain);
		lblPassword.setForeground(new Color(253, 253, 253));
		lblPassword.setBounds(250, 205, 100, 16);
		getContentPane().add(lblPassword);

		JButton botonLogin = new JButton("Entrar");
		botonLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				//Comprobar que los datos insertados en el campo esten en el ArrayList 
				String user = textFieldUserFinal.getText().toString();

				//Creamos un metodo para obtener la passsword no encripada
				String pass = "";
				char [] password= passwordFieldFinal.getPassword();
				for(int x = 0; x < password.length; x++) {
					pass += password[x];
				}

				System.out.println("La password introducida es"+ password);

				//Comprobar que esos datos estan en un la lista de usuario registrados

				VentanaRegistro vRegistro= new VentanaRegistro();
				boolean registered = false;
				try{
					registered = neverEmptyController.login(user, String.valueOf(password));
				}catch (Exception ex) {
					System.out.println(ex);
				}
				if(registered){ 
					iniciarAplicacion();
				}else{
					System.out.println("Tienes que registrarte antes de iniciar sesion");
					JOptionPane.showMessageDialog(null, "ERROR! Tienes que registrarte antes de iniciar sesion", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonLogin.setContentAreaFilled(false);
		botonLogin.setBorder(BorderFactory.createLineBorder(Color.white,2));
		botonLogin.setForeground(Color.white);
		botonLogin.setBounds(225, 300, 150, 30);
		botonLogin.setFont(textPlain);
		getContentPane().add(botonLogin);

	}

	public void iniciarAplicacion() {
		System.out.println("EL USUARIO ESTA REGISTRADO");
		//Abrimos la ventana principal TODO
		VentanaPrincipal vPrincipal = new VentanaPrincipal(this.neverEmptyController);
		vPrincipal.setVisible(true);
		//Y cerramos la ventana de inicio al haber podido iniciar sesion
		dispose();
	}

	public void registro () {
		VentanaRegistro vRegistro = new VentanaRegistro(this.neverEmptyController);
		vRegistro.setVisible(true);
		dispose();
	}
	public VentanaInicio() {

	}

	private ImageIcon getImageFromResources(String filename) {
		try {
			Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/" + filename));
			ImageIcon icon = new ImageIcon(image);
			return icon;
		}catch (Exception e) {
			System.out.println("No se ha podido cargar la imagen " + filename + " : " + e);
			return null;
		}

	}

}
