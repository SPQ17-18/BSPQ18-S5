package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame {
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_1;
	public VentanaRegistro() {
		
		setBounds(330, 80, 400, 400);
		getContentPane().setLayout(null);
		
		
		JLabel lblSignUp = DefaultComponentFactory.getInstance().createLabel("SIGN UP ");
		lblSignUp.setBounds(172, 29, 110, 16);
		getContentPane().add(lblSignUp);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(192, 115, 90, 22);
		getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(192, 78, 90, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(192, 150, 90, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
	
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(111, 81, 56, 16);
		getContentPane().add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(111, 118, 69, 16);
		getContentPane().add(lblPassword);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(111, 153, 56, 16);
		getContentPane().add(lblMail);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			VentanaInicio vInicio = new VentanaInicio();
			vInicio.setVisible(true);	
			dispose();
			}
		});
		btnBack.setBounds(167, 203, 97, 25);
		getContentPane().add(btnBack);
		
	}
	
}
