package SPQ.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import SPQ.controller.NeverEmptyController;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JTable;

public class VentanaInicio extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUserFinal;
	private JPasswordField passwordFieldFinal;
	private JButton botonLogin;
	private NeverEmptyController neverEmptyController;

	
	public VentanaInicio(NeverEmptyController neverEmptyController) {
		this.neverEmptyController = neverEmptyController;
		setBounds(330, 80, 400, 400);
		setResizable(false);
		getContentPane().setLayout(null);
		
		this.setTitle("NEVEREMPTY");
		JLabel lblNeverEmpty = DefaultComponentFactory.getInstance().createLabel("NEVER EMPTY");
		lblNeverEmpty.setBounds(160, 23, 110, 16);
		getContentPane().add(lblNeverEmpty);

		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registro();
			}
		});
		btnSignUp.setBounds(89, 185, 97, 25);
		getContentPane().add(btnSignUp);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(157, 234, 97, 25);
		getContentPane().add(btnSalir);

		textFieldUserFinal = new JTextField();
		textFieldUserFinal.setBounds(177, 73, 114, 22);
		getContentPane().add(textFieldUserFinal);
		textFieldUserFinal.setColumns(10);

		passwordFieldFinal = new JPasswordField();
		passwordFieldFinal.setBounds(177, 122, 114, 22);
		getContentPane().add(passwordFieldFinal);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(88, 76, 56, 16);
		getContentPane().add(lblUsuario);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(89, 125, 76, 16);
		getContentPane().add(lblPassword);
		
		JButton botonLogin = new JButton("Log in");
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
				System.out.println("LA CONTRASENIA ES"+ pass);
		
				
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
					System.out.println("EL USUARIO ESTA REGISTRADO");
					//Abrimos la ventana principal TODO
					VentanaPrincipal vPrincipal = new VentanaPrincipal();
					vPrincipal.setVisible(true);
					//Y cerramos la ventana de inicio al haber podido iniciar sesion
					dispose();
				}else{
					System.out.println("Tienes que registrarte antes de iniciar sesion");
					JOptionPane.showMessageDialog(null, "ERROR! Tienes que registrarte antes de iniciar sesion", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonLogin.setBounds(210, 185, 97, 25);
		getContentPane().add(botonLogin);
		
	}

	public void registro () {
		VentanaRegistro vRegistro = new VentanaRegistro(this.neverEmptyController);
		vRegistro.setVisible(true);
		dispose();
	}
	public VentanaInicio() {

	}
}
