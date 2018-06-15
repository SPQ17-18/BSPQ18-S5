package SPQ.gui;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import SPQ.Utilities;
import SPQ.controller.NeverEmptyController;
import SPQ.dto.UserDTO;
import SPQ.remote.RMIServiceLocator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;

public class VentanaPerfil extends JFrame implements ActionListener{

	private JPanel pTitle, pUser, pVisa, pPaypal, pAddress, pFooter, pUserTitle, pVisaTitle, pPaypalTitle, pAddressTitle;
	private JLabel lTitle, lUserTitle, lUsername, lPassword, lVisaTitle, lPaypalTitle, lAddressTitle, lEmail, lCardnumber, lCardholder, lPaypalEmail, lPayPalpassword, lAddress;
	private JTextField tfUsername, tfEmail, tfCardnumber1, tfCardnumber2, tfCardnumber3, tfCardnumber4, tfCardholder, tfPayPalEmail, tfAddress;
	private JPasswordField pfPassword, pfRepeatPassword, pfPaypalPassword, pfRepeatPaypalPassword;
	private JButton bEdit, bBack;
	
	private ImageIcon iSave, iEdit;
	
	private String state = "saved";
	
	public static void main(String[] args) {
		VentanaPerfil vp = new VentanaPerfil();
		vp.setVisible(true);
	}
	public VentanaPerfil() {
		setSize(1000, 600);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(50, 50, 50));
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(null);

		Font titleBold = new Font("Arial", Font.BOLD, 25);
		Font textPlain = new Font("Arial", Font.PLAIN, 16);
		this.setTitle("Perfil");
		
		Utilities util = new Utilities();
//		ImageIcon iconVisa = util.getImageFromResources("visa.png");
//		ImageIcon iconPaypal = util.getImageFromResources("paypal.png");
		iEdit = util.getImageFromResources("edit.png");
		iSave = util.getImageFromResources("save-white.png");

		pTitle = new JPanel();
		pTitle.setBounds(0, 0, 1000, 50);
		pTitle.setBackground(new Color(20, 20, 20));
		pTitle.setLayout(null);
		
		lTitle = new JLabel("Mi perfil", SwingConstants.CENTER);
		lTitle.setBounds(450, 10, 100, 30);
		lTitle.setFont(titleBold);
		lTitle.setForeground(new Color(253, 253, 253));
		
		bBack = new JButton("<html><u>&#60 atrás</u></html>");
		bBack.setSize(50, 20);
		bBack.setBackground(null);
		bBack.setForeground(Color.white);
		bBack.setMargin(new Insets(0, 0, 0, 0));
		bBack.setBorder(null);
		Font fBack = new Font("Arial", Font.BOLD, 12);
		bBack.setFont(fBack);
		bBack.setLocation(10,10);
		bBack.addActionListener(this);
		
		
		pFooter = new JPanel();
		pFooter.setBounds(0, 550, 1000, 50);
		pFooter.setBackground(new Color(20, 20, 20));
		pFooter.setLayout(null);
		
		bEdit = new JButton("Editar perfil", iEdit );
		bEdit.setBounds(440, 10, 120, 30);
		bEdit.setBackground(Color.lightGray);
		bEdit.setBorder(null);
		bEdit.addActionListener(this);
		
		//USER
		pUser = new JPanel();
		pUser.setBounds(10, 60, 485, 235);
		pUser.setBackground(Color.lightGray);
		pUser.setLayout(null);
		
		pUserTitle = new JPanel();
		pUserTitle.setBounds(0, 0, 485, 30);
		pUserTitle.setBackground(Color.white);
		pUserTitle.setLayout(null);
		pUserTitle.setOpaque(true);
		
		lUserTitle = new JLabel("Información de perfil");
		lUserTitle.setBounds(20,0,200,30);
		
		lEmail = new JLabel("Email: ");
		lEmail.setBounds(30, 60, 150, 30);
		
		
		lUsername = new JLabel("Nombre de usuario: ");
		lUsername.setBounds(30, 105, 150, 30);
	
		tfUsername = new JTextField();
		tfUsername.setEditable(false);
		tfUsername.setBounds(200, 105, 255, 30);
		
		lPassword = new JLabel("Contraseña NeverEmpty: ");
		lPassword.setBounds(30, 150, 150, 30);
		
		pfPassword = new JPasswordField();
		pfPassword.setEditable(false);
		pfPassword.setBounds(200, 150, 255, 30);
		
		pfRepeatPassword = new JPasswordField();
		pfRepeatPassword.setBounds(200, 190, 255, 30);
		pfRepeatPassword.setVisible(false);
	
		//ADDRESS
		pAddress = new JPanel();
		pAddress.setBounds(505, 60, 485, 235);
		pAddress.setBackground(Color.lightGray);
		pAddress.setLayout(null);
		
		pAddressTitle = new JPanel();
		pAddressTitle.setBounds(0, 0, 485, 30);
		pAddressTitle.setBackground(Color.white);
		pAddressTitle.setLayout(null);
		pAddressTitle.setOpaque(true);
		
		lAddressTitle = new JLabel("Dirección de envío");
		lAddressTitle.setBounds(20,0,200,30);
		
		lAddress = new JLabel("Dirección de envío: ");
		lAddress.setBounds(30, 60, 150, 30);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(30, 105, 425, 30);
		tfAddress.setEditable(false);
		
		
		//VISA
		pVisa = new JPanel();
		pVisa.setBounds(10, 305, 485, 235);
		pVisa.setBackground(Color.lightGray);
		pVisa.setLayout(null);
		
		pVisaTitle = new JPanel();
		pVisaTitle.setBounds(0, 0, 485, 30);
		pVisaTitle.setBackground(Color.white);
		pVisaTitle.setLayout(null);
		pVisaTitle.setOpaque(true);
		
		lVisaTitle = new JLabel("Pago con Visa");
		lVisaTitle.setBounds(20,0,200,30);
		
		lCardnumber = new JLabel("Número de tarjeta: ");
		lCardnumber.setBounds(30, 60, 150, 30);
		
		tfCardnumber1 = new JTextField();
		tfCardnumber2 = new JTextField();
		tfCardnumber3 = new JTextField();
		tfCardnumber4 = new JTextField();
		
		tfCardnumber1.setBounds(200, 60, 50, 30);
		tfCardnumber2.setBounds(255, 60, 50, 30);
		tfCardnumber3.setBounds(310, 60, 50, 30);
		tfCardnumber4.setBounds(365, 60, 50, 30);
		
		tfCardnumber1.setEditable(false);
		tfCardnumber2.setEditable(false);
		tfCardnumber3.setEditable(false);
		tfCardnumber4.setEditable(false);
		
		lCardholder = new JLabel("Titular de la tarjeta: ");
		lCardholder.setBounds(30, 105, 150, 30);
		
		tfCardholder = new JTextField();
		tfCardholder.setEditable(false);
		tfCardholder.setBounds(200, 105, 255, 30);
		
		
		
		//PAYPAL
		pPaypal = new JPanel();
		pPaypal.setBounds(505, 305, 485, 235);
		pPaypal.setBackground(Color.lightGray);
		pPaypal.setLayout(null);
		
		pPaypalTitle = new JPanel();
		pPaypalTitle.setBounds(0, 0, 485, 30);
		pPaypalTitle.setBackground(Color.white);
		pPaypalTitle.setLayout(null);
		pPaypalTitle.setOpaque(true);
		
		lPaypalTitle = new JLabel("Pago con Paypal");
		lPaypalTitle.setBounds(20,0,200,30);
		
		lPaypalEmail = new JLabel("Nombre usuario/email: ");
		lPaypalEmail.setBounds(30, 60, 150, 30);
		
		tfPayPalEmail = new JTextField();
		tfPayPalEmail.setBounds(200, 105, 255, 30);
		tfPayPalEmail.setEditable(false);
		
		lPayPalpassword = new JLabel("Contraseña Paypal: ");
		lPayPalpassword.setBounds(30, 105, 150, 30);
		
		pfPaypalPassword= new JPasswordField();
		pfPaypalPassword.setEditable(false);
		pfPaypalPassword.setBounds(200, 105, 255, 30);
		
		pfRepeatPaypalPassword= new JPasswordField();
		pfRepeatPaypalPassword.setBounds(200, 145, 255, 30);
		pfRepeatPaypalPassword.setVisible(false);
		
		
		pTitle.add(lTitle);
		pTitle.add(bBack);
		
		pFooter.add(bEdit);
		
		pUserTitle.add(lUserTitle);
		pUser.add(pUserTitle);
		pUser.add(lEmail);
		pUser.add(lUsername);
		pUser.add(tfUsername);
		pUser.add(lPassword);
		pUser.add(pfPassword);
		pUser.add(pfRepeatPassword);
		
		pAddressTitle.add(lAddressTitle);
		pAddress.add(pAddressTitle);
		pAddress.add(lAddress);
		pAddress.add(tfAddress);
		
		pVisaTitle.add(lVisaTitle);
		pVisa.add(pVisaTitle);
		pVisa.add(lCardnumber);
		pVisa.add(tfCardnumber1);
		pVisa.add(tfCardnumber2);
		pVisa.add(tfCardnumber3);
		pVisa.add(tfCardnumber4);
		pVisa.add(lCardholder);
		pVisa.add(tfCardholder);
		
		pPaypalTitle.add(lPaypalTitle);
		pPaypal.add(pPaypalTitle);
		pPaypal.add(lPaypalEmail);
		pPaypal.add(tfPayPalEmail);
		pPaypal.add(lPayPalpassword);
		pPaypal.add(pfPaypalPassword);
		pPaypal.add(pfRepeatPaypalPassword);
	
		
		getContentPane().add(pTitle);
		getContentPane().add(pFooter);
		getContentPane().add(pUser);
		getContentPane().add(pAddress);
		getContentPane().add(pVisa);
		getContentPane().add(pPaypal);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bEdit) {
			if(state.equals("saved")) {
				tfAddress.setEditable(true);
				tfCardholder.setEditable(true);
				tfCardnumber1.setEditable(true);
				tfCardnumber2.setEditable(true);
				tfCardnumber3.setEditable(true);
				tfCardnumber4.setEditable(true);
				tfPayPalEmail.setEditable(true);
				tfUsername.setEditable(true);
				
				pfPassword.setEditable(true);
				pfPaypalPassword.setEditable(true);
				
				pfRepeatPassword.setVisible(true);
				pfRepeatPaypalPassword.setVisible(true);
				
				bEdit.setIcon(iSave);
				bEdit.setText(" Guardar cambios");
				bEdit.setBounds(425, 10, 150, 30);
				
				state = "edit";
			}else if(state.equals("edit")) {
				tfAddress.setEditable(false);
				tfCardholder.setEditable(false);
				tfCardnumber1.setEditable(false);
				tfCardnumber2.setEditable(false);
				tfCardnumber3.setEditable(false);
				tfCardnumber4.setEditable(false);
				tfPayPalEmail.setEditable(false);
				tfUsername.setEditable(false);
				
				pfPassword.setEditable(false);
				pfPaypalPassword.setEditable(false);
				
				pfRepeatPassword.setVisible(false);
				pfRepeatPaypalPassword.setVisible(false);
				
				bEdit.setIcon(iEdit);
				bEdit.setText("Editar perfil");
				bEdit.setBounds(440, 10, 120, 30);
				
				state = "saved";
			}
		}else if(e.getSource() == bBack) {
			if(state.equals("edit")) {
				int answer = JOptionPane.showConfirmDialog(null, "¿Desea salir sin guardar los cambios?", "¡Alerta!", JOptionPane.YES_NO_OPTION);
				if(answer == 0) {
					// @TODO llamar a ventana principal
					dispose();
				}
			}
		}
		
		
		
	}
}
