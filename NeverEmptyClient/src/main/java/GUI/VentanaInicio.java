package GUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import SPQ.Usuario;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JTable;

public class VentanaInicio extends JFrame implements ActionListener {
	private JTextField textFieldUserFinal;
	private JPasswordField passwordFieldFinal;
	private JButton botonLogin;

	
	public VentanaInicio() {
		

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
			
				VentanaRegistro vRegistro= new VentanaRegistro();
				vRegistro.setVisible(true);
				dispose();
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
				// TODO Auto-generated method stub
						
				//Comprobar que los datos insertados en el campo esten en el ArrayList 
				String user= textFieldUserFinal.getText().toString();
				
				//Creamos un metodo para obtener la passsword no encripada
				String pass="";
				char [] password= passwordFieldFinal.getPassword();
				for(int x=0;x<password.length;x++) {
					pass+=password[x];
				}
				System.out.println("LA CONTRASENIA ES"+ pass);
				//String password= passwordFieldFinal.getPassword().toString();
				//String password= passwordFieldFinal.getPassword();
				
				
				System.out.println("La password introducida es"+ password);
				
				//Comprobar que esos datos estan en un la lista de usuario registrados
				
				VentanaRegistro vRegistro= new VentanaRegistro();
				
				
				//Metemos a mano un usuario
				
				Usuario u1= new Usuario();
				u1.setUsername("jesus");
				u1.setPassword("12");
				u1.setMail("jesus@hotmail.com");
				
				//Metemos a mano otro usuario
				
				Usuario u2= new Usuario();
				u2.setUsername("cristian");
				u2.setPassword("13");
				u2.setMail("cristian@hotmail.com");
				
				//Aniadimos estos usuarios a la 
				vRegistro.UsuarioRegistrados.add(u1);
				vRegistro.UsuarioRegistrados.add(u2);
				
				//Mostramos los usuarios registrados en el ArrayList<Usuario> usuarioRegistrados
				
				System.out.println("ENTRA AL LOGIN Y ESTOS SON LOS USUARIOS QUE YA ESTAN"+ vRegistro.getUsuarioRegistrados().toString());
				
				//Comprobamos si el usuario y la contrasenia introducidas se encuentra en el ArrayList de usuarios registrados
				
				ArrayList<Usuario> uRegistrados = vRegistro.getUsuarioRegistrados();
				
				//System.out.println(uRegistrados.size());
				
				int enc= encontrarUsuario(uRegistrados, user, pass);
				
				System.out.println("El valor de enc es =" +enc);
	
				if(enc != -2 && enc != -1){ //Si devuelve -2, significa que no lo ha encontrado. Por lo que si es distinto de -2, lo encuentra
					
					System.out.println("EL USUARIO ESTA REGISTRADO");
					//Abrimos la ventana principal
					VentanaPrincipal vPrincipal = new VentanaPrincipal();
					vPrincipal.setVisible(true);
					//Y cerramos la ventana de inicio al haber podido iniciar sesion
					dispose();
				}else if(enc == -1) {//El nombre de usuario coincide pero la contrasenia NO
					System.out.println("Tienes que introducir bien la password");
					JOptionPane.showMessageDialog(null, "ERROR! Tienes que introducir bien la password", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
					
				else{
					System.out.println("Tienes que registrarte antes de iniciar sesion");
					JOptionPane.showMessageDialog(null, "ERROR! Tienes que registrarte antes de iniciar sesion", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonLogin.setBounds(210, 185, 97, 25);
		getContentPane().add(botonLogin);
		
	}
	
	public static void main(String[] args) {

		
		//Inicializo la ventana inicio
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio vInicio = new VentanaInicio();
					vInicio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

		

	}
	
	//Mostrar usuarios de la lista
	public void mostrarUsuarios(ArrayList<Usuario> aUsuarios){
	
		for(int x=0;x<aUsuarios.size();x++) {
			  System.out.println(aUsuarios.get(x).toString());
			  
			}
		
	}
	
	
	//Funcion encontrar usuario que devuelve TRUE si ha encontrado al usuario en el ArrayList de Usuarios registrados
	//Le pasamos el arraylist de UsuarioRegistrados y el usuario y la contra del textfield
	
	/*
	public boolean encontrarUsuario (ArrayList<Usuario> aUsuarios, String user, String password) {
		boolean enc= false;
		int pos=0;//Posicion del ArrayList
		 
		System.out.println("USUARIOS REGISTRADOS"+aUsuarios.size());
		
		while(!enc && pos < aUsuarios.size())
		{
			Usuario u= aUsuarios.get(pos);
			if(u.getUsername().equalsIgnoreCase(user) && u.getPassword().equals(password)) {
				enc=true;
				
			}else if(u.getUsername().equalsIgnoreCase(user) && !u.getPassword().equals(password)) {
				//Coincide con el nombre del cliente pero no con la contra
				pos++;
		//}else
				//System.out.println("El usuario tiene que registrarse antes de iniciar sesion");
		}
		}
		return enc;
		
}
	*/
	
	/**
	 * Método para buscar usuario (si nombre y contraseña igual a contraseña y nombre ingresado por el usuario entonces usuario encontrado)
	 * @param a
	 * @param nom
	 * @param con
	 * @return
	 */
	public static int encontrarUsuario(ArrayList<Usuario> aUsuarios, String user, String password)
	{
		boolean enc=false;
		int pos=0,resul=-2;
		System.out.println("USUARIOS REGISTRADOS = " +aUsuarios.size());
		
		while(!enc && pos<aUsuarios.size())
		{
			Usuario u = aUsuarios.get(pos);
			if(u.getUsername().equalsIgnoreCase(user) && u.getPassword().equals(password))
			{	
				enc=true;
				resul=pos;
			}
			else if(u.getUsername().equalsIgnoreCase(user) && !u.getPassword().equals(password))
			{	
				resul=-1;
				enc=true;
			}
			else
				pos++;
		}
		if(enc)
			return resul;
		else
			return -2;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {		
	}
	
}
