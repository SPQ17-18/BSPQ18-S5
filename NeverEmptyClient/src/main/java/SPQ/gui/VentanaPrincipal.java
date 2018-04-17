package SPQ.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import SPQ.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class VentanaPrincipal extends JFrame {
	VentanaInicio vInicio= new VentanaInicio();
	
	//ArrayList de usuarios registrados
	//private ArrayList<Usuario> UsuarioRegistrados;
	//Ventana de inicio
	//private JFrame ventanaInicio;
	
	//Usuario
	private Usuario u;

	private JTextField textField_usernamelog;
	

    

	
	public VentanaPrincipal() {
		
		setBounds(330, 80, 400, 400);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblEnhorabuenaHasEntrado = DefaultComponentFactory.getInstance().createTitle("ENHORABUENA!!! HAS ENTRADO A NEVEREMPTY!!");
		lblEnhorabuenaHasEntrado.setBounds(62, 172, 332, 16);
		getContentPane().add(lblEnhorabuenaHasEntrado);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInicio vInicio = new VentanaInicio();
				vInicio.setVisible(true);	
				dispose();
			}
		});
		btnLogOut.setBounds(146, 300, 97, 25);
		getContentPane().add(btnLogOut);
		
		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(197, 40, 56, 16);
		getContentPane().add(lblUsuario);
		
		
		textField_usernamelog = new JTextField();
		textField_usernamelog.setBounds(265, 37, 116, 22);
		getContentPane().add(textField_usernamelog);
		textField_usernamelog.setColumns(10);
		
		
		//System.out.println(vInicio.getNombreUsuarioLogueado());


		
		

	}
}
