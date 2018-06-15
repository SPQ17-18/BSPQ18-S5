package SPQ.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import SPQ.controller.NeverEmptyController;
import SPQ.dto.PaymentDTO;
import SPQ.dto.UserDTO;
import SPQ.remote.RMIServiceLocator;

public class VentanaPago extends JFrame implements ActionListener{
	static Logger logger = Logger.getLogger(VentanaPago.class.getName());
	private NeverEmptyController neverEmptyController;
	static public VentanaMetodoPago vmp;
	private String paymentMethod;
	double total;

	private JPanel pSavedData, pNewData;

	private JButton bAlt;
	private JButton bPay;
	private JButton bBack;

	//Componentes VISA
	private JLabel cardnumber, cardholder, lExpDate, lCvv;

	private JTextField expDateDay, expDateMonth, cvv;

	private JLabel lAltCardnumber, lAltcardholder;
	private JTextField altCardnumber1, altCardnumber2, altCardnumber3, altCardnumber4;
	private JTextField altcardholder;
	private JLabel lAltExpDate, lAltCvv;
	private JTextField altExpDateDay, altExpDateMonth, altCvv;


	//Componentes PayPal
	private JLabel lPaypalUsername, lPayPalPassword;
	private JPasswordField pfPaypalPassword;

	private JLabel lAltPaypalUsername, lAltPaypalPassword;
	private JTextField altPaypalUsername;
	private JPasswordField pfAltPayPalPassword;

	private Animate slidUp;
	private Animate slidDown;

	private String state = "saved";

	public VentanaPago (NeverEmptyController neverEmptyController , String paymentMethod, double total, VentanaMetodoPago ventanaMetodoPago) {
		this.neverEmptyController = neverEmptyController;
		vmp = ventanaMetodoPago;
		this.paymentMethod = paymentMethod;

		this.setTitle("Seleccionar método de pago");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(50, 50, 50));
		this.getContentPane().setLayout(null);


		this.pSavedData = new JPanel();
		this.pSavedData.setBounds(0, 40, 600, 360);
		this.pSavedData.setBackground(new Color(50, 50, 50));
		this.pSavedData.setLayout(null);

		this.pNewData = new JPanel();
		this.pNewData.setBounds(0, 400, 600, 0);
		this.pNewData.setBackground(new Color(75, 75, 75));
		this.pNewData.setLayout(null);

		//Animation stuff
		Rectangle fromSavedData = new Rectangle(0, 40, 600, 360);
		Rectangle toSavedData = new Rectangle(0, -360, 600, 360);

		Rectangle fromNewData = new Rectangle(0, 400, 600, 0);
		Rectangle toNewData = new Rectangle(0, 40, 600, 360);
		this.slidUp = new Animate(pSavedData, fromSavedData, toSavedData, pNewData, fromNewData, toNewData);

		fromSavedData = new Rectangle(0, -360, 600, 360);
		toSavedData = new Rectangle(0, 40, 600, 360);

		fromNewData = new Rectangle(0, 40, 600, 360);
		toNewData = new Rectangle(0, 400, 600, 360);

		this.slidDown = new Animate(pSavedData, fromSavedData, toSavedData, pNewData, fromNewData, toNewData);


		if(paymentMethod.equals("visa")) {
			this.loadVisaComponents();
		}else if(paymentMethod.equals("paypal")) {
			this.loadPayPalComponents();
		}

		this.bBack = new JButton("<html><u>&#60 atrás</u></html>");
		this.bBack.setSize(50, 20);
		this.bBack.setBackground(null);
		this.bBack.setForeground(Color.white);
		this.bBack.setMargin(new Insets(0, 0, 0, 0));
		this.bBack.setBorder(null);
		Font fBack = new Font("Arial", Font.BOLD, 12);
		this.bBack.setFont(fBack);
		this.bBack.setLocation(10,10);

		this.bAlt = new JButton("Pagar con otra tarjeta");
		this.bAlt.setForeground(Color.WHITE);
		this.bAlt.setBackground(new Color(40, 100, 40));
		this.bAlt.setBorder(null);
		this.bAlt.setBounds(10, 320, 200, 30);

		this.bPay = new JButton("Finalizar pago");
		this.bPay.setForeground(Color.WHITE);
		this.bPay.setBackground(new Color(40, 40, 100));
		this.bPay.setBorder(null);
		this.bPay.setBounds(390, 320, 200, 30);


		this.bBack.addActionListener(this);
		this.bAlt.addActionListener(this);
		this.bPay.addActionListener(this);

		this.pSavedData.add(bAlt);
		this.pSavedData.add(bPay);

		this.getContentPane().add(bBack);
		this.getContentPane().add(pSavedData);
		this.getContentPane().add(pNewData);


	}

	private void loadVisaComponents() {
		UserDTO user = this.neverEmptyController.getUserDTO();

		this.cardnumber = new JLabel("Número de tarjeta: " + Long.toString(user.getCardNumber()));
		this.cardnumber.setBounds(40, 30, 200, 25);
		this.cardnumber.setForeground(Color.white);

		this.cardholder = new JLabel("Titular: " + user.getUsername());
		this.cardholder.setBounds(40, 70, 200, 25);
		this.cardholder.setForeground(Color.white);

		this.lExpDate = new JLabel("Válido hasta: ");
		this.lExpDate.setForeground(Color.white);
		this.lExpDate.setBounds(40, 110, 100, 25);

		this.expDateDay = new JTextField();
		this.expDateDay.setBorder(null);
		this.expDateDay.setBounds(120, 110, 25, 25);

		this.expDateMonth = new JTextField();
		this.expDateMonth.setBorder(null);
		this.expDateMonth.setBounds(150, 110, 25, 25);

		this.lCvv = new JLabel("CVV: ");
		this.lCvv.setForeground(Color.white);
		this.lCvv.setBounds(40, 150, 100, 25);

		this.cvv = new JTextField();
		this.cvv.setBorder(null);
		this.cvv.setBounds(75, 150, 50, 25);

		this.pSavedData.add(cardnumber);
		this.pSavedData.add(cardholder);
		this.pSavedData.add(lExpDate);
		this.pSavedData.add(expDateDay);
		this.pSavedData.add(expDateMonth);
		this.pSavedData.add(lCvv);
		this.pSavedData.add(cvv);

		this.lAltCardnumber = new JLabel("Número de tarjeta: ");
		this.lAltCardnumber.setBounds(40, 50, 200, 25);
		this.lAltCardnumber.setForeground(Color.white);

		this.altCardnumber1 = new JTextField();
		this.altCardnumber1.setBounds(160, 50, 50, 25);
		this.altCardnumber1.setBorder(null);

		this.altCardnumber2 = new JTextField();
		this.altCardnumber2.setBounds(230, 50, 50, 25);
		this.altCardnumber2.setBorder(null);

		this.altCardnumber3 = new JTextField();
		this.altCardnumber3.setBounds(300, 50, 50, 25);
		this.altCardnumber3.setBorder(null);

		this.altCardnumber4 = new JTextField();
		this.altCardnumber4.setBounds(370, 50, 50, 25);
		this.altCardnumber4.setBorder(null);


		this.lAltcardholder = new JLabel("Titular: ");
		this.lAltcardholder.setForeground(Color.white);
		this.lAltcardholder.setBounds(40, 90, 50, 25);

		this.altcardholder = new JTextField();
		this.altcardholder.setBounds(100, 90, 320, 25);
		this.altcardholder.setBorder(null);

		this.lAltExpDate = new JLabel("Válido hasta: ");
		this.lAltExpDate.setForeground(Color.white);
		this.lAltExpDate.setBounds(40, 130, 100, 25);

		this.altExpDateDay = new JTextField();
		this.altExpDateDay.setBorder(null);
		this.altExpDateDay.setBounds(130, 130, 25, 25);

		this.altExpDateMonth = new JTextField();
		this.altExpDateMonth.setBorder(null);
		this.altExpDateMonth.setBounds(160, 130, 25, 25);

		this.lAltCvv = new JLabel("CVV: ");
		this.lAltCvv.setForeground(Color.white);
		this.lAltCvv.setBounds(40, 170, 100, 25);

		this.altCvv = new JTextField();
		this.altCvv.setBorder(null);
		this.altCvv.setBounds(100, 170, 50, 25);

		this.pNewData.add(lAltCardnumber);
		this.pNewData.add(altCardnumber1);
		this.pNewData.add(altCardnumber2);
		this.pNewData.add(altCardnumber3);
		this.pNewData.add(altCardnumber4);
		this.pNewData.add(lAltcardholder);
		this.pNewData.add(altcardholder);
		this.pNewData.add(lAltExpDate);
		this.pNewData.add(altExpDateDay);
		this.pNewData.add(altExpDateMonth);
		this.pNewData.add(lAltCvv);
		this.pNewData.add(altCvv);

	}
	private void loadPayPalComponents() {
		UserDTO user = this.neverEmptyController.getUserDTO();
		this.lPaypalUsername = new JLabel("Usuario:                  " + user.getPayPalEmail());
		this.lPaypalUsername.setBounds(40, 50, 300, 25);
		this.lPaypalUsername.setForeground(Color.white);

		this.lPayPalPassword = new JLabel("Contraseña:        ");
		this.lPayPalPassword.setForeground(Color.white);
		this.lPayPalPassword.setBounds(40, 90, 100, 25);

		this.pfPaypalPassword = new JPasswordField();
		this.pfPaypalPassword.setText(user.getPayPalPassword());
		this.pfPaypalPassword.setEditable(false);
		this.pfPaypalPassword.setBounds(140, 90, 200, 25);

		this.pSavedData.add(lPaypalUsername);
		this.pSavedData.add(lPayPalPassword);
		this.pSavedData.add(pfPaypalPassword);


		this.lAltPaypalUsername = new JLabel("Usuario:        " );
		this.lAltPaypalUsername.setBounds(40, 50, 100, 25);
		this.lAltPaypalUsername.setForeground(Color.white);

		this.altPaypalUsername = new JTextField();
		this.altPaypalUsername.setBounds(140, 50, 200, 25);

		this.lAltPaypalPassword = new JLabel("Contraseña:        ");
		this.lAltPaypalPassword.setForeground(Color.white);
		this.lAltPaypalPassword.setBounds(40, 90, 100, 25);

		this.pfAltPayPalPassword = new JPasswordField();
		this.pfAltPayPalPassword.setEditable(true);
		this.pfAltPayPalPassword.setBounds(140, 90, 200, 25);

		this.pNewData.add(lAltPaypalUsername);
		this.pNewData.add(altPaypalUsername);
		this.pNewData.add(lAltPaypalPassword);
		this.pNewData.add(pfAltPayPalPassword);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bAlt) {
			if (state.equals("saved")) {
				this.slidUp.start();

				this.pSavedData.remove(this.bAlt);
				this.pSavedData.remove(this.bPay);
				this.bAlt.setLocation(10, 10);
				this.bAlt.setText("Pagar con los datos de perfil");
				this.pNewData.add(this.bAlt);
				this.pNewData.add(this.bPay);
				this.state = "new";
			}else if(state.equals("new")) {
				this.slidDown.start();

				this.pNewData.remove(this.bAlt);
				this.pNewData.remove(this.bPay);
				this.bAlt.setLocation(10, 320);
				this.bAlt.setText("Pagar con otra tarjeta");
				this.pSavedData.add(this.bAlt);
				this.pSavedData.add(this.bPay);

				this.state = "saved";
			}
		}
		if (e.getSource() == bPay) {
			boolean paid = false;
			try {
				UserDTO udto = neverEmptyController.getUserDTO();
				
				if(paymentMethod.equals("paypal")) {
					if(state.equals("saved")) {
						PaymentDTO paymentDTO = new PaymentDTO(total, udto.getPayPalEmail(), udto.getPayPalPassword());

						paid = neverEmptyController.payWithPayPal(paymentDTO);

					}else if(state.equals("new")) {
						//Creamos un metodo para obtener la passsword no encripada
						String pass = "";
						char [] password= pfAltPayPalPassword.getPassword();
						for(int x = 0; x < password.length; x++) {
							pass += password[x];
						}
						PaymentDTO paymentDTO = new PaymentDTO(total, altPaypalUsername.getText(), pass);
						paid = neverEmptyController.payWithPayPal(paymentDTO);
					}
				}else if(paymentMethod.equals("visa")) {
					if(state.equals("saved")) {
						PaymentDTO paymentDTO = new PaymentDTO(total, udto.getCardNumber(), udto.getUsername(), expDateDay.getText()+"/"+expDateMonth.getText(), Integer.parseInt(cvv.getText()));
						paid = neverEmptyController.payWithVisa(paymentDTO);
					}else if(state.equals("new")) {
						long tmpCarnumber = Long.parseLong(altCardnumber1.getText()+altCardnumber2.getText()+altCardnumber3.getText()+altCardnumber4.getText());
						String tmpExpDate = altExpDateDay.getText() + "/" + altExpDateMonth.getText();
						PaymentDTO paymentDTO = new PaymentDTO(total, tmpCarnumber, altcardholder.getText(), tmpExpDate, Integer.parseInt(altCvv.getText()));
						paid = neverEmptyController.payWithVisa(paymentDTO);
					
					}
					
				}
			}catch (RemoteException e1) {
				e1.printStackTrace();
			}
			if (paid == false) JOptionPane.showMessageDialog(null, "Error, los datos introducidos son incorrectos.", "ERROR", JOptionPane.ERROR_MESSAGE);
			if (paid == true) {
				// @TODO Mostrar diálogo de pago realizado.
				logger.info("Pago realizado con éxito");
				vmp.vt.vp.setVisible(true);
				dispose();
				
			}
			
		}
		if (e.getSource() == bBack) {
			vmp.setVisible(true);
			dispose();
		}



	}

	public static class Animate {

		public static final int RUN_TIME = 500;

		private JPanel pSavedData;
		private Rectangle fromSavedData;
		private Rectangle toSavedData;

		private JPanel pNewData;
		private Rectangle fromNewData;
		private Rectangle toNewData;

		private long startTime;

		public Animate(JPanel pSavedData, Rectangle fromSavedData, Rectangle toSavedData, JPanel pNewData, Rectangle fromNewData, Rectangle toNewData) {
			this.pSavedData = pSavedData;
			this.fromSavedData = fromSavedData;
			this.toSavedData = toSavedData;

			this.pNewData = pNewData;
			this.fromNewData = fromNewData;
			this.toNewData = toNewData;
		}

		public void start() {
			Timer timer = new Timer(40, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					long duration = System.currentTimeMillis() - startTime;
					double progress = (double)duration / (double)RUN_TIME;
					if (progress > 1f) {
						progress = 1f;
						((Timer)e.getSource()).stop();
					}
					Rectangle target = calculateProgress(fromSavedData, toSavedData, progress);
					Rectangle target2 = calculateProgress(fromNewData, toNewData, progress);
					pSavedData.setBounds(target);
					pNewData.setBounds(target2);
				}
			});
			timer.setRepeats(true);
			timer.setCoalesce(true);
			timer.setInitialDelay(0);
			startTime = System.currentTimeMillis();
			timer.start();
		}

	}

	public static Rectangle calculateProgress(Rectangle startBounds, Rectangle targetBounds, double progress) {

		Rectangle bounds = new Rectangle();

		if (startBounds != null && targetBounds != null) {

			bounds.setLocation(calculateProgress(startBounds.getLocation(), targetBounds.getLocation(), progress));
			bounds.setSize(calculateProgress(startBounds.getSize(), targetBounds.getSize(), progress));

		}

		return bounds;

	}

	public static Point calculateProgress(Point startPoint, Point targetPoint, double progress) {

		Point point = new Point();

		if (startPoint != null && targetPoint != null) {

			point.x = calculateProgress(startPoint.x, targetPoint.x, progress);
			point.y = calculateProgress(startPoint.y, targetPoint.y, progress);

		}

		return point;

	}

	public static int calculateProgress(int startValue, int endValue, double fraction) {

		int value = 0;
		int distance = endValue - startValue;
		value = (int)Math.round((double)distance * fraction);
		value += startValue;

		return value;

	}

	public static Dimension calculateProgress(Dimension startSize, Dimension targetSize, double progress) {

		Dimension size = new Dimension();

		if (startSize != null && targetSize != null) {

			size.width = calculateProgress(startSize.width, targetSize.width, progress);
			size.height = calculateProgress(startSize.height, targetSize.height, progress);

		}

		return size;

	}


}

