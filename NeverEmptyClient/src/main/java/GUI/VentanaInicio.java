package GUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
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
	
	//Declaro un arrayList de usuarios
	ArrayList<Usuario> UsuarioRegistrados= new ArrayList<Usuario>();
	
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
			
				VentanaRegistro vRegistro = new VentanaRegistro();
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
				String user= textFieldUserFinal.getText();
				String password= passwordFieldFinal.getText();
				
				//Comprobar que esos datos estan en un la lista de usuario registrados
				encontrarUsuario(user, password);
				if(encontrarUsuario(user, password)){
					
					
					//Abrimos la ventana principal
					
					VentanaPrincipal vPrincipal = new VentanaPrincipal();
					vPrincipal.setVisible(true);
					
				}
				else{
					JOptionPane.showMessageDialog(null, "ERROR! Tienes que registrarte antes de logearte", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonLogin.setBounds(210, 185, 97, 25);
		getContentPane().add(botonLogin);
	}
	
	public static void main(String[] args) {
		VentanaInicio vInicio = new VentanaInicio();
		vInicio.setVisible(true);
	}

	//Funcion encontrar usuario que devuelve si ha encontrado al usuario en el ArrayList de Usuarios registrados
	public boolean encontrarUsuario (String user, String password) {
		boolean enc= false;
		int pos=0;
		Usuario u= null;
		
		while(!enc && pos<UsuarioRegistrados.size())
		{
			u= UsuarioRegistrados.get(pos);
		if(u.getUsername().equals(user) && u.getPassword().equals(password)) {
			enc=true;
		}else
			
			pos++;
		}
		
		if(enc)
			return true;
		else return false;
		
		}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}
}
