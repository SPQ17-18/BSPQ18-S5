package SPQ.gui;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import SPQ.controller.NeverEmptyController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class VentanaPerfil extends JFrame {


	private JTextField textFieldEmailPaypal;
	private JTextField textFieldPayPalPass;
	private JTextField textField_1;
	public VentanaPerfil(NeverEmptyController neverEmptyController) {
		getContentPane().setLayout(null);
		setBounds(200, 300, 423, 328);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolver.setBounds(10, 240, 89, 23);
		getContentPane().add(btnVolver);

		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(24, 11, 46, 14);
		getContentPane().add(lblUser);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(24, 36, 46, 14);
		getContentPane().add(lblEmail);

		JLabel lblUserbd = new JLabel(neverEmptyController.getUserDTO().getUsername());
		lblUserbd.setBounds(80, 11, 46, 14);
		getContentPane().add(lblUserbd);

		JLabel lblEmailbd = new JLabel(neverEmptyController.getUserDTO().getEmail());
		lblEmailbd.setBounds(80, 36, 46, 14);
		getContentPane().add(lblEmailbd);

		JButton btnModificarU = new JButton("modificar");
		btnModificarU.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnModificarU.setBounds(250, 92, 69, 23);
		getContentPane().add(btnModificarU);
		btnModificarU.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nuevoCorreoPaypal;
				if (btnModificarU.getText().equals("modificar")) {
					btnModificarU.setText("guardar");
					textFieldEmailPaypal.setEditable(true);
					nuevoCorreoPaypal = textFieldEmailPaypal.getText();

					//codigo enara
				}else if (btnModificarU.getText().equals("guardar")) {

					btnModificarU.setText("modificar");
				}
				//btnModificarU.setactio
				// TODO Auto-generated method stub

			}
		});


		JButton btnModificarE = new JButton("modificar");
		btnModificarE.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnModificarE.setBounds(250, 176, 69, 23);
		getContentPane().add(btnModificarE);

		JLabel lblPaypalData = new JLabel("Datos de paypal");
		lblPaypalData.setBounds(10, 79, 89, 14);
		getContentPane().add(lblPaypalData);

		JLabel lblNewLabel = new JLabel("correo paypal:");
		lblNewLabel.setBounds(35, 101, 78, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblPasswordPaypal = new JLabel("password:");
		lblPasswordPaypal.setBounds(34, 130, 65, 14);
		getContentPane().add(lblPasswordPaypal);

		textFieldEmailPaypal = new JTextField();
		textFieldPayPalPass.setText(neverEmptyController.getUserDTO().getPayPalEmail());
		textFieldEmailPaypal.setEditable(false);
		textFieldEmailPaypal.setBounds(135, 93, 86, 20);
		getContentPane().add(textFieldEmailPaypal);
		textFieldEmailPaypal.setColumns(10);

		textFieldPayPalPass = new JTextField();
		textFieldPayPalPass.setText(neverEmptyController.getUserDTO().getPayPalPassword());
		textFieldPayPalPass.setEditable(false);
		textFieldPayPalPass.setBounds(135, 127, 86, 20);
		getContentPane().add(textFieldPayPalPass);
		textFieldPayPalPass.setColumns(10);

		JLabel lblDatosVisa = new JLabel("Datos Visa:");
		lblDatosVisa.setBounds(10, 155, 75, 14);
		getContentPane().add(lblDatosVisa);

		JLabel lblCardNumber = new JLabel("Card Number:");
		lblCardNumber.setBounds(35, 180, 69, 14);
		getContentPane().add(lblCardNumber);

		textField_1 = new JTextField();
		textField_1.setText(Long.toString(neverEmptyController.getUserDTO().getCardNumber()));
		textField_1.setBounds(135, 177, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnModificarPassword = new JButton("modificar");
		btnModificarPassword.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnModificarPassword.setBounds(250, 126, 69, 23);
		getContentPane().add(btnModificarPassword);
		btnModificarPassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nuevopass;
				if (btnModificarPassword.getText().equals("modificar")) {
					btnModificarPassword.setText("guardar");
					textFieldPayPalPass.setEditable(true);
					nuevopass = textFieldPayPalPass.getText();

				}else if (btnModificarPassword.getText().equals("guardar")) {

					btnModificarPassword.setText("modificar");
				}

			}
		});

	}
}
