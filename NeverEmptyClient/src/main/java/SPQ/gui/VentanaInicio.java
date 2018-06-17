package SPQ.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import SPQ.Utilities;
import SPQ.controller.NeverEmptyController;

import org.apache.log4j.Logger;

public class VentanaInicio extends JFrame{
	static Logger logger = Logger.getLogger(VentanaInicio.class.getName());
	private static final long serialVersionUID = 1L;
	private JTextField textFieldUserFinal;
	private JPasswordField passwordFieldFinal;
	private JButton bLogin;
	private JButton bSignUp;
	private JButton bClose;
	private JLabel lNeverEmpty;
	private JLabel lUsername; 
	private JLabel lPassword;
	private NeverEmptyController neverEmptyController;
	private VentanaInicio ventanaInicio;

	public static void main(String[] args) {
		VentanaInicio vi = new VentanaInicio(null);
		vi.setVisible(true);
	}

	public VentanaInicio(NeverEmptyController neverEmptyController) {
		this.ventanaInicio = this;
		this.neverEmptyController = neverEmptyController;

		setSize(800, 400);
		setLocationRelativeTo(null);

		getContentPane().setBackground(new Color(50, 50, 50));

		setResizable(false);
		setUndecorated(true);

		getContentPane().setLayout(null);
		Utilities util = new Utilities();
		setContentPane(new JLabel(util.getImageFromResources("bg-metalic.jpg")));

		Font titleBold = new Font("Arial", Font.BOLD, 55);
		Font textPlain = new Font("Arial", Font.PLAIN, 16);
		this.setTitle("NeverEmpty");
		lNeverEmpty = new JLabel("NeverEmpty", SwingConstants.CENTER);
		lNeverEmpty.setBounds(200, 0, 400, 150);
		lNeverEmpty.setFont(titleBold);
		lNeverEmpty.setForeground(new Color(253, 253, 253));

		getContentPane().add(lNeverEmpty);

		bSignUp = new JButton("Registro");
		bSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaSeleccionarRegistro vsr = new VentanaSeleccionarRegistro(neverEmptyController, ventanaInicio);
				vsr.setVisible(true);

			}
		});
		bSignUp.setContentAreaFilled(false);
		bSignUp.setBorder(BorderFactory.createLineBorder(Color.white,2));
		bSignUp.setForeground(Color.white);
		bSignUp.setBounds(425, 300, 150, 30);
		bSignUp.setFont(textPlain);
		getContentPane().add(bSignUp);

		bClose = new JButton();
		bClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bClose.setBounds(750, 20, 30, 30);
		ImageIcon closeIcon = util.getImageFromResources("close-white.png");
		bClose.setIcon(closeIcon);
		bClose.setSize(30, 30);
		bClose.setPreferredSize(new Dimension(30, 30));
		bClose.setHorizontalAlignment(SwingConstants.CENTER);
		bClose.setOpaque(false);
		bClose.setBackground(new Color(255, 255, 255));
		bClose.setBorder(null);

		getContentPane().add(bClose);

		textFieldUserFinal = new JTextField();
		textFieldUserFinal.setBounds(350, 150, 200, 30);
		textFieldUserFinal.setBorder(null);
		getContentPane().add(textFieldUserFinal);
		textFieldUserFinal.setColumns(10);

		passwordFieldFinal = new JPasswordField();
		passwordFieldFinal.setBorder(null);
		passwordFieldFinal.setBounds(350, 200, 200, 30);
		getContentPane().add(passwordFieldFinal);

		lUsername = new JLabel("Usuario");
		lUsername.setFont(textPlain);
		lUsername.setForeground(new Color(253, 253, 253));
		lUsername.setBounds(250, 155, 100, 16);
		getContentPane().add(lUsername);

		lPassword = new JLabel("Contraseña");
		lPassword.setFont(textPlain);
		lPassword.setForeground(new Color(253, 253, 253));
		lPassword.setBounds(250, 205, 100, 16);
		getContentPane().add(lPassword);

		bLogin = new JButton("Entrar");
		bLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				//Comprobar que los datos insertados en el campo esten en el ArrayList 
				String user = textFieldUserFinal.getText().toString();
				//Comprobar que esos datos estan en un la lista de usuario registrados

				boolean registered = false;
				try{
					registered = neverEmptyController.login(user, String.valueOf(passwordFieldFinal.getPassword()));
				}catch (Exception ex) {
					System.out.println(ex);
				}
				if(registered){ 
					iniciarAplicacion();
				}else{
					logger.info("Usuario no registrado.");
					JOptionPane.showMessageDialog(null, "Error, los datos introducidos son incorrectos.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		bLogin.setContentAreaFilled(false);
		bLogin.setBorder(BorderFactory.createLineBorder(Color.white,2));
		bLogin.setForeground(Color.white);
		bLogin.setBounds(225, 300, 150, 30);
		bLogin.setFont(textPlain);
		getContentPane().add(bLogin);

	}

	public void iniciarAplicacion() {
		logger.info("Iniciando NeverEmpty...");
		VentanaPrincipal vPrincipal = new VentanaPrincipal(this.neverEmptyController);
		vPrincipal.setVisible(true);
		dispose();
	}


}
