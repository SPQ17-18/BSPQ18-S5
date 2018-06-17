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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import org.apache.log4j.Logger;

public class VentanaPerfil extends JFrame implements ActionListener{

	private NeverEmptyController neverEmptyController;

	private VentanaPrincipal vp;

	private JPanel pTitle, pUser, pVisa, pPaypal, pAddress, pFooter, pUserTitle, pVisaTitle, pPaypalTitle, pAddressTitle;
	private JLabel lTitle, lUserTitle, lUsername, lPassword, lVisaTitle, lPaypalTitle, lAddressTitle, lEmail, lEmail2, lCardnumber, lCardholder, lPaypalEmail, lPayPalpassword, lAddress;
	private JTextField tfUsername, tfCardnumber1, tfCardnumber2, tfCardnumber3, tfCardnumber4, tfCardholder, tfPayPalEmail, tfAddress;
	private JPasswordField pfPassword, pfRepeatPassword, pfPaypalPassword, pfRepeatPaypalPassword;
	private JButton bEdit, bBack;

	private ImageIcon iSave, iEdit;

	private String state = "saved";
	private boolean pModUser, pModPaypal;

	public VentanaPerfil(NeverEmptyController neverEmptyController, VentanaPrincipal vp) {
		this.neverEmptyController = neverEmptyController;
		this.vp = vp;

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
		pTitle.setBackground(new Color(30, 30, 30));
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
		pFooter.setBackground(new Color(30, 30, 30));
		pFooter.setLayout(null);

		bEdit = new JButton("Editar perfil", iEdit );
		bEdit.setBounds(440, 10, 120, 30);
		bEdit.setBackground(new Color(153, 200, 51));
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

		String email = (neverEmptyController.getUserDTO().getEmail() != null) ? neverEmptyController.getUserDTO().getEmail() : "";
		System.out.println("EMAIL " + email);
		lEmail2 = new JLabel(email);
		lEmail2.setBounds(200, 60, 255, 30);


		lUsername = new JLabel("Nombre de usuario: ");
		lUsername.setBounds(30, 105, 150, 30);

		String username = (neverEmptyController.getUserDTO().getUsername() != null) ? neverEmptyController.getUserDTO().getUsername() : "";
		tfUsername = new JTextField(username);
		tfUsername.setEditable(false);
		tfUsername.setBounds(200, 105, 255, 30);

		lPassword = new JLabel("Contraseña NeverEmpty: ");
		lPassword.setBounds(30, 150, 150, 30);

		String password = (neverEmptyController.getUserDTO().getPassword() != null) ? neverEmptyController.getUserDTO().getPassword() : "";
		pfPassword = new JPasswordField(password);
		pfPassword.setEditable(false);
		pfPassword.setBounds(200, 150, 255, 30);
		pfPassword.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				pModUser = true;
				passwordModified();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				pModUser = true;
				passwordModified();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				pModUser = true;
				passwordModified();
			}

		});
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

		String address = (neverEmptyController.getUserDTO().getAddress() != null) ? neverEmptyController.getUserDTO().getAddress() : "";
		tfAddress = new JTextField(address);
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

		String cardnumber = (neverEmptyController.getUserDTO().getCardNumber() != -1) ? Long.toString(neverEmptyController.getUserDTO().getCardNumber()) : "XXXXXXXXXXXXXXXX"; 	
		tfCardnumber1 = new JTextField(cardnumber.substring(0, 4));
		tfCardnumber2 = new JTextField(cardnumber.substring(4, 8));
		tfCardnumber3 = new JTextField(cardnumber.substring(8, 12));
		tfCardnumber4 = new JTextField(cardnumber.substring(12, 16));

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

		String cardholder = (neverEmptyController.getUserDTO().getCardholder() != null) ? neverEmptyController.getUserDTO().getCardholder() : "";
		tfCardholder = new JTextField(cardholder);
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

		String paypalEmail = (neverEmptyController.getUserDTO().getPayPalEmail() != null) ? neverEmptyController.getUserDTO().getPayPalEmail() : "";
		tfPayPalEmail = new JTextField(paypalEmail);
		tfPayPalEmail.setBounds(200, 60, 255, 30);
		tfPayPalEmail.setEditable(false);

		lPayPalpassword = new JLabel("Contraseña Paypal: ");
		lPayPalpassword.setBounds(30, 105, 150, 30);

		String paypalPassword = (neverEmptyController.getUserDTO().getPayPalPassword() != null) ? neverEmptyController.getUserDTO().getPayPalPassword() : "";
		pfPaypalPassword= new JPasswordField(paypalPassword);
		pfPaypalPassword.setEditable(false);
		pfPaypalPassword.setBounds(200, 105, 255, 30);
		pfPaypalPassword.addActionListener(this);
		pfPaypalPassword.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				pModPaypal = true;
				passwordModified();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				pModPaypal = true;
				passwordModified();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				pModPaypal = true;
				passwordModified();

			}

		});


		pfRepeatPaypalPassword= new JPasswordField();
		pfRepeatPaypalPassword.setBounds(200, 145, 255, 30);
		pfRepeatPaypalPassword.setVisible(false);


		pTitle.add(lTitle);
		pTitle.add(bBack);

		pFooter.add(bEdit);

		pUserTitle.add(lUserTitle);
		pUser.add(pUserTitle);
		pUser.add(lEmail);
		pUser.add(lEmail2);
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

	public void passwordModified() {
		if (pModPaypal == true) {
			pfRepeatPaypalPassword.setVisible(true);
		}
		if( pModUser == true) {
			pfRepeatPassword.setVisible(true);
		}
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

				bEdit.setIcon(iSave);
				bEdit.setText(" Guardar cambios");
				bEdit.setBounds(425, 10, 150, 30);
				bEdit.setBackground(new Color (255, 193, 51));

				state = "edit";
			}else if(state.equals("edit")) {

				try {
					if(pModUser == true) {

						if (!String.valueOf(pfPassword.getPassword()).equals(String.valueOf(pfRepeatPassword.getPassword())))
							throw new Exception("Las contraseñas del perfil no coinciden");
					}
					if(pModPaypal == true) {
						if (!String.valueOf(pfPaypalPassword.getPassword()).equals(String.valueOf(pfRepeatPaypalPassword.getPassword())))
							throw new Exception("Las contraseñas de Paypal no coinciden");
					}
					String cardnumber = tfCardnumber1.getText() + tfCardnumber2.getText() + tfCardnumber3.getText() +tfCardnumber4.getText();
					long cardnumberLong = -1;
					if(!cardnumber.equals("XXXXXXXXXXXXXXXX")) {
						cardnumberLong = Long.parseLong(cardnumber);
					}
					String address = null;
					String cardholder = null;
					String paypalPassword = null;
					String paypalEmail = null;
					if(!tfAddress.getText().equals("")) {
						address = tfAddress.getText();
					}
					if(!tfCardholder.getText().equals("")) {
						cardholder = tfCardholder.getText();
					}
					if(!String.valueOf(pfPaypalPassword.getPassword()).equals("")) {
						paypalPassword = String.valueOf(pfPaypalPassword.getPassword());
					}
					if(!tfPayPalEmail.getText().equals("")) {
						paypalEmail = tfPayPalEmail.getText();
					}

					UserDTO userDTO = new UserDTO(
							tfUsername.getText(),
							lEmail2.getText(),
							String.valueOf(pfPassword.getPassword()),
							neverEmptyController.getUserDTO().getRegisterMethod(),
							address,
							paypalEmail,
							paypalPassword,
							cardnumberLong,
							cardholder
							);
					if(neverEmptyController.updateUser(userDTO)) {
						neverEmptyController.setUserDTO(userDTO);
					}

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

					pModUser = false;
					pModPaypal = false;

					pfRepeatPassword.setVisible(false);
					pfRepeatPaypalPassword.setVisible(false);

					bEdit.setIcon(iEdit);
					bEdit.setText("Editar perfil");
					bEdit.setBounds(440, 10, 120, 30);
					bEdit.setBackground(new Color(153, 200, 51));

					state = "saved";

				}catch(NumberFormatException nfex) {
					JOptionPane.showMessageDialog(null, "El número de cuenta debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
				}catch (Exception ex){
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}


			}
		}else if(e.getSource() == bBack) {
			if(state.equals("edit")) {
				int answer = JOptionPane.showConfirmDialog(null, "¿Desea salir sin guardar los cambios?", "¡Alerta!", JOptionPane.YES_NO_OPTION);
				if(answer == 0) {
					vp.setVisible(true);
					dispose();
				}
			}else {
				vp.setVisible(true);
				dispose();
			}
		}



	}
}
