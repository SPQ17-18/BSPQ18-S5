package GUI;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import SPQ.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame implements ActionListener {

	private JPasswordField passwordField, passwordField_1;
	private JButton btnBack,btnSignUp;
	private JTextField textFieldMail;
	private JTextField textFieldUsername;
	
	ArrayList<Usuario> UsuarioRegistrados= new ArrayList<Usuario>();

	
	public ArrayList<Usuario> getUsuarioRegistrados() {
		return UsuarioRegistrados;
	}

	public void setUsuarioRegistrados(ArrayList<Usuario> usuarioRegistrados) {
		UsuarioRegistrados = usuarioRegistrados;
	}

	//La ventana de inicio
	VentanaInicio vInicio= new VentanaInicio();
		
	public VentanaRegistro() {
		
		setBounds(330, 80, 400, 400);
		setResizable(false);
		getContentPane().setLayout(null);
		
		this.setTitle("Ventana de registro");
		JLabel lblSignUp = DefaultComponentFactory.getInstance().createLabel("SIGN UP ");
		lblSignUp.setBounds(163, 29, 110, 16);
		getContentPane().add(lblSignUp);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(192, 99, 90, 22);
		getContentPane().add(passwordField);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(192, 169, 90, 22);
		getContentPane().add(textFieldMail);
		textFieldMail.setColumns(10);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(192, 64, 90, 22);
		getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
	
		JLabel lblUsuario = new JLabel("Username");
		lblUsuario.setBounds(73, 67, 80, 16);
		getContentPane().add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(73, 102, 69, 16);
		getContentPane().add(lblPassword);
		
		
		JLabel lblPasswordRepeat = new JLabel("Repeat Password");
		lblPasswordRepeat.setBounds(73, 137, 118, 16);
		getContentPane().add(lblPasswordRepeat);
		
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(73, 172, 56, 16);
		getContentPane().add(lblMail);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			VentanaInicio vInicio = new VentanaInicio();
			vInicio.setVisible(true);	
			dispose();
			}
		});
		btnBack.setBounds(199, 218, 97, 25);
		getContentPane().add(btnBack);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
						//Comprobamos que el usuario introducido introduzca correctamente los datos.
						Boolean SignUp= ComprobacionSignup();
						
						if(!SignUp) { //Si no esta el usuario en el arraylist de Usuarios Registrados que se registre
							
						System.out.println("Ha llegado");
						vInicio.mostrarUsuarios(UsuarioRegistrados);
						//Obtenemos la contrasenia que ha introducido
						String pass = passwordField.getPassword().toString();
						
						//Obtenemos el usuario y el mail que se ha introducido.
						String user = textFieldUsername.getText();
						String mail = textFieldMail.getText();	
						
						System.out.println(user);
						//Creo un nuevo usuario con los datos y lo aniado al arrayList de usuarios registrados
						
						Usuario nuevo = new Usuario(user, pass,mail);
						UsuarioRegistrados.add(nuevo);  
						
						
						//Registro completado con exito
						JOptionPane.showMessageDialog(null, "Registro completado con exito", "REGISTRADO", JOptionPane.INFORMATION_MESSAGE);
						
						//Como ya se ha realizado el registro con exito, vuelvo a la ventana de inicio para poder realizar el registro
						System.out.println("LLego hasta el final");
						dispose();
											
						VentanaInicio vInicio= new VentanaInicio();
						vInicio.setVisible(true);
						
						

						
						
					}
				
				}
				
		});
		btnSignUp.setBounds(73, 218, 97, 25);
		getContentPane().add(btnSignUp);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(192, 134, 90, 22);
		getContentPane().add(passwordField_1);
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//Para comprobar que el usuario se pueda registrar correctamente
	
	public boolean ComprobacionSignup() {
		
		boolean registrarse = false;
		
		if (textFieldMail.getText().equals("")) {
			System.out.println("El mail no puede estar vacio");
			JOptionPane.showMessageDialog(null, "ERROR! Introduce un mail", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (new String(passwordField.getPassword()).isEmpty()) {
			System.out.println("Introduce una contrasenia");
			JOptionPane.showMessageDialog(null, "ERROR! Introduce una cotrasenia", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (new String(passwordField_1.getPassword()).isEmpty()) {
			System.out.println("Confirma la contrasenia");
			JOptionPane.showMessageDialog(null, "ERROR! Introduce una segunda contrasenia", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (!new String(passwordField.getPassword()).equals(new String(passwordField_1.getPassword()))) {
			System.out.println("Las contrasenias han de coincidir");
			JOptionPane.showMessageDialog(null, "ERROR! Las contrasenias han de coincidir", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if(textFieldUsername.getText().equals("")) {
			System.out.println("El usuario no puede estar vacio");
			JOptionPane.showMessageDialog(null, "ERROR! Introduce un usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
			String user= textFieldUsername.getText();
			String pass= passwordField.getPassword().toString();
			//String mail= textFieldMail.getText();
			
			registrarse= vInicio.encontrarUsuario(UsuarioRegistrados, user, pass);
			
			System.out.println("PASA A REGISTRARSE");
			
			if (!registrarse) {
				System.out.println("ENTRA AL IF");
				//this.dispose();
				//JOptionPane.showMessageDialog(this, "El registro se ha realizado con exito");
				
				//Abro la ventana de inicio.
				
		}
		
		return registrarse;
	}
		
	
	
}