package GUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JTable;

public class VentanaInicio extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	
	public VentanaInicio() {
	
		setBounds(330, 80, 400, 400);
		getContentPane().setLayout(null);
		
		this.setTitle("NEVEREMPTY");;
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
		btnSignUp.setBounds(88, 172, 97, 25);
		getContentPane().add(btnSignUp);

		JButton btnLogIn = new JButton("Log in");
		btnLogIn.setBounds(194, 172, 97, 25);
		getContentPane().add(btnLogIn);

		textField = new JTextField();
		textField.setBounds(177, 73, 114, 22);
		getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(177, 122, 114, 22);
		getContentPane().add(passwordField);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(88, 76, 56, 16);
		getContentPane().add(lblUsuario);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(89, 125, 76, 16);
		getContentPane().add(lblPassword);
	}
	
	public static void main(String[] args) {
		VentanaInicio vInicio = new VentanaInicio();
		vInicio.setVisible(true);
	}
}
