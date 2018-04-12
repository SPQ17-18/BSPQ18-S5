package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	//ArrayList de usuarios registrados
	private ArrayList<Usuario> RegistroUsuarios;
	//La ventana de inicio
	private JFrame VentanaInicio;
	
	//ArrayList de usuarios registrados
	private ArrayList<Usuario> UsuarioRegistrados;
	
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
				// TODO Auto-generated method stub
				
					String pass = passwordField.getText();
					String pass1 = passwordField_1.getText();
					
					//Comprueba que no haya ningun campo libre
					if(textFieldMail.getText().equals("") || textFieldUsername.getText().equals("") || passwordField.getText().equals("") || passwordField_1.getText().equals("")){
						JOptionPane.showMessageDialog(null, "ERROR! Falta por rellenar algún campo", "ERROR", JOptionPane.ERROR_MESSAGE);
			
					}
					//Comprueba que la contrasenia coincida
					if (!passwordField.getText().equals(passwordField_1.getText())){
						JOptionPane.showMessageDialog(null, "ERROR! La contrasenia ha de coincidir", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					//Comprobar que en el arrayList de UsuariosRegistrados no se introduza el mismo usuario
					
					
					else{
						String user = textFieldUsername.getText();
						String mail = textFieldMail.getText();	
						
						//Creo un nuevo usuario con los datos y lo aniado al arrayList de usuarios registrados
						
						Usuario nuevo = new Usuario();
						nuevo.setMail(mail);
						nuevo.setPassword(pass);
						nuevo.setUsername(user);
						UsuarioRegistrados.add(nuevo);
						
						//Registro completado con exito
						JOptionPane.showMessageDialog(null, "Registro completado con exito", "REGISTRADO", JOptionPane.INFORMATION_MESSAGE);
						
						//Como ya se ha realizado el registro con exito, vuelvo a poner los campos vacios
						
						textFieldMail.setText("");
						textFieldUsername.setText("");
						passwordField.setText("");
						passwordField_1.setText("");
						
						
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
		
	
	
}