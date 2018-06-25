/** @package SPQ.gui
 	@brief This is the brief documentation for the java package SPQ.gui
 */

/** @class VentanaRegistro class.h "inc/class.h" 
* @brief This is a VentanaRegistro class.
* Some details about the VentanaRegistro class 
*/

package SPQ.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import SPQ.Utilities;
import SPQ.controller.NeverEmptyController;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

public class VentanaRegistro extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel lTitle;
	private JLabel lIcon;

	private JPasswordField passwordField, passwordRepeat;

	private JButton btnBack;

	private JTextField textFieldMail;
	private JTextField textFieldUsername;

	private String registerMethod;

	NeverEmptyController neverEmptyController;
	
	static Logger logger = Logger.getLogger(VentanaRegistro.class.getName());

	public static void main(String[] args) {
		VentanaRegistro vr = new VentanaRegistro(new NeverEmptyController(null), "Google");
		vr.setVisible(true);
	}
	public VentanaRegistro(NeverEmptyController neverEmptyController, String registerMethod) {
		this.registerMethod = registerMethod;
		this.neverEmptyController = neverEmptyController;

		this.setTitle("Seleccionar método de registro");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(50, 50, 50));
		this.getContentPane().setLayout(null);


		this.setTitle("Registro");
		if(!this.registerMethod.equals("NeverEmpty")) {
			ImageIcon iRegisterMethod = null;
			Utilities util = new Utilities();
			if(this.registerMethod.equals("Google")) {
				iRegisterMethod = util.getImageFromResources("google.png");
			}else if(this.registerMethod.equals("Facebook")) {
				iRegisterMethod = util.getImageFromResources("facebook.png");
			}
			this.lIcon = new JLabel(iRegisterMethod);
			this.lIcon.setOpaque(true);
			this.lIcon.setBackground(Color.white);
			this.lIcon.setSize(30, 30);
			this.lIcon.setLocation(80,80);
			this.add(lIcon);

		}

		this.lTitle = new JLabel("Registro con " + registerMethod);
		Font fTitle = new Font("Arial", Font.BOLD, 14);
		this.lTitle.setFont(fTitle);
		this.lTitle.setForeground(Color.white);
		this.lTitle.setSize(250, 50);
		this.lTitle.setLocation(120, 70);

		this.add(lTitle);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(192, 130, 130, 22);
		textFieldUsername.setBorder(null);
		getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(192, 170, 130, 22);
		passwordField.setBorder(null);
		getContentPane().add(passwordField);

		passwordRepeat = new JPasswordField();
		passwordRepeat.setBorder(null);
		passwordRepeat.setBounds(192, 210, 130, 22);
		getContentPane().add(passwordRepeat);

		textFieldMail = new JTextField();
		textFieldMail.setBounds(192, 250, 130, 22);
		textFieldMail.setBorder(null);
		getContentPane().add(textFieldMail);
		textFieldMail.setColumns(10);



		JLabel lblUsuario = new JLabel("Username");
		lblUsuario.setForeground(Color.white);
		lblUsuario.setBounds(73, 130, 80, 16);
		getContentPane().add(lblUsuario);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.white);
		lblPassword.setBounds(73, 170, 69, 16);
		getContentPane().add(lblPassword);


		JLabel lblPasswordRepeat = new JLabel("Repeat Password");
		lblPasswordRepeat.setForeground(Color.white);
		lblPasswordRepeat.setBounds(73, 210, 118, 16);
		getContentPane().add(lblPasswordRepeat);


		JLabel lblMail = new JLabel("Mail");
		lblMail.setForeground(Color.white);
		lblMail.setBounds(73, 250, 56, 16);
		getContentPane().add(lblMail);

		this.btnBack = new JButton("Back");

		this.btnBack = new JButton("<html><u>&#60 atrás</u></html>");
		this.btnBack.setSize(50, 20);
		this.btnBack.setBackground(null);
		this.btnBack.setForeground(Color.white);
		this.btnBack.setMargin(new Insets(0, 0, 0, 0));
		this.btnBack.setBorder(null);
		Font fBack = new Font("Arial", Font.BOLD, 12);
		this.btnBack.setFont(fBack);
		this.btnBack.setLocation(10,10);
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizarRegistro();
			}
		});
		getContentPane().add(this.btnBack);

		JButton btnSignUp = new JButton("Sign up");
		if(registerMethod.equals("Google")) {
			btnSignUp.setBackground(new Color(220, 50, 50));
			btnSignUp.setForeground(Color.white);
		}else if(registerMethod.equals("Facebook")) {
			btnSignUp.setBackground(new Color(60, 70, 170));
			btnSignUp.setForeground(Color.white);
		}else {
			btnSignUp.setBackground(Color.white);
			btnSignUp.setForeground(Color.black);
		}
		btnSignUp.setBorder(null);

		btnSignUp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				//Comprobamos que el usuario introducido introduzca correctamente los datos.
				Boolean SignUp = ComprobacionSignup();
				if(SignUp) { 
					//Registro completado con exito
					JOptionPane.showMessageDialog(null, "Registro completado con exito", "REGISTRADO", JOptionPane.INFORMATION_MESSAGE);
					finalizarRegistro();
				}

			}

		});
		btnSignUp.setBounds(73, 290, 250, 25);
		getContentPane().add(btnSignUp);



	}

	public void finalizarRegistro() {

		//Como ya se ha realizado el registro con exito, vuelvo a la ventana de inicio para poder realizar el login
		dispose();
		VentanaInicio vInicio = new VentanaInicio (this.neverEmptyController);
		vInicio.setVisible(true);
	}

	//Para comprobar que el usuario se pueda registrar correctamente

	public boolean ComprobacionSignup() {

		if (textFieldMail.getText().equals("")) {
			logger.info("El mail no puede estar vacio");
			JOptionPane.showMessageDialog(null, "ERROR! Introduce un mail", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (new String(passwordField.getPassword()).isEmpty()) {
			logger.info("Introduce una contrasenia");
			JOptionPane.showMessageDialog(null, "ERROR! Introduce una cotrasenia", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (new String(passwordRepeat.getPassword()).isEmpty()) {
			logger.info("Confirma la contrasenia");
			JOptionPane.showMessageDialog(null, "ERROR! Introduce una segunda contrasenia", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (!new String(passwordField.getPassword()).equals(new String(passwordRepeat.getPassword()))) {
			logger.info("Las contrasenias han de coincidir");
			JOptionPane.showMessageDialog(null, "ERROR! Las contraseñas han de coincidir", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if(textFieldUsername.getText().equals("")) {
			logger.info("El usuario no puede estar vacio");
			JOptionPane.showMessageDialog(null, "ERROR! Introduce un usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		String user= textFieldUsername.getText();
		char [] password= passwordField.getPassword();
		String mail = textFieldMail.getText();	
		
		boolean registro = false;
		try {
			if(this.registerMethod.equals("Google")) {
				registro = this.neverEmptyController.registerGoogle(user, mail, String.valueOf(password));
			}else if(this.registerMethod.equals("Facebook")) {
				registro = this.neverEmptyController.registerFacebook(user, mail, String.valueOf(password));
			}else {
				registro = this.neverEmptyController.registerNeverEmpty(user, mail, String.valueOf(password));
			}
		}catch (Exception e) {
			logger.error("Error en el registro.");
		}

		return registro;
	}



}