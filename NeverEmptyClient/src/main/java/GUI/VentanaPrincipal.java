package GUI;

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
	private JTextField txtUsuario;
	
	//ArrayList de usuarios registrados
	private ArrayList<Usuario> UsuarioRegistrados;
	//Ventana de inicio
	private JFrame ventanaInicio;
	//Usuario
	private Usuario u;
	private JTextField textField;
	
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
		lblUsuario.setBounds(228, 40, 56, 16);
		getContentPane().add(lblUsuario);
	
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(291, 37, 78, 22);
		//Muestra el usuario que ha iniciado sesion en el txtUSuario
		//txtUsuario.setText(u.getUsername());
		
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);


		
		

	}

}
