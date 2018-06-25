/** @package SPQ.gui
 	@brief This is the brief documentation for the java package SPQ.gui
 */

/** @class VentanaPago class.h "inc/class.h" 
* @brief This is a VentanaPago class.
* Some details about the VentanaPago class 
*/

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
import org.apache.log4j.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

	private JPanel pSavedData, pNewData, pAddress, pTitle, pFooter, pPayTitle, pAddressTitle;
	private JLabel lAddressTitle, lAddress, lTitle, lPayTitle, lTotal;
	private JTextField tfAddress;
	private JButton bAlt, bPay, bBack;

	//Componentes VISA
	private JLabel cardnumber, cardholder, lCardnumber, lCardholder, lExpDate, lCvv, lDay, lMonth;

	private JTextField expDateDay, expDateMonth, cvv;

	private JLabel lAltCardnumber, lAltCardholder;
	private JTextField altCardnumber1, altCardnumber2, altCardnumber3, altCardnumber4;
	private JTextField altCardholder;
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

		this.setTitle("Información de pago");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(50, 50, 50));
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(null);

		pTitle = new JPanel();
		pTitle.setBounds(0, 0, 1000, 50);
		pTitle.setBackground(new Color(30, 30, 30));
		pTitle.setLayout(null);

		Font titleBold = new Font("Arial", Font.BOLD, 25);
		
		lTitle = new JLabel("Información de pago", SwingConstants.CENTER);
		lTitle.setBounds(375, 10, 250, 30);
		lTitle.setFont(titleBold);
		lTitle.setForeground(new Color(253, 253, 253));
		
		pFooter = new JPanel();
		pFooter.setBounds(0, 550, 1000, 50);
		pFooter.setBackground(new Color(30, 30, 30));
		pFooter.setLayout(null);
		
		lPayTitle = new JLabel();
		lPayTitle.setBounds(20, 0, 200, 30);
		if(paymentMethod.equals("visa")) {
			lPayTitle.setText("Datos de VISA del perfil");
		}else if(paymentMethod.equals("paypal")) {
			lPayTitle.setText("Datos de PayPal del perfil");
		}
		
		pPayTitle = new JPanel();
		pPayTitle.setBounds(0, 0, 980, 30);
		pPayTitle.setBackground(Color.white);
		pPayTitle.setLayout(null);
		
		lAddressTitle = new JLabel("Dirección de envío");
		lAddressTitle.setBounds(20, 0, 200, 30);
		
		pAddressTitle = new JPanel();
		pAddressTitle.setBounds(0, 0, 980, 30);
		pAddressTitle.setBackground(Color.white);
		pAddressTitle.setLayout(null);
		
		
		lAddress = new JLabel("Dirección de envío: ");
		lAddress.setBounds(40, 65, 200, 30);
		
		tfAddress = new JTextField(neverEmptyController.getUserDTO().getAddress());
		tfAddress.setBounds(40, 95, 540, 30);
		tfAddress.setEditable(false);
		
		pAddress = new JPanel();
		pAddress.setBounds(10, 60, 980, 170);
		pAddress.setBackground(Color.lightGray);
		pAddress.setLayout(null);
		
		Font totalFont = new Font("Arial", Font.BOLD, 16);
		
		lTotal = new JLabel("TOTAL a pagar: " + total + "€");
		lTotal.setFont(totalFont);
		lTotal.setForeground(Color.white);
		lTotal.setBounds(770, 170, 200, 100);
		
		pSavedData = new JPanel();
		pSavedData.setBounds(10, 240, 980, 300);
		pSavedData.setBackground(new Color(100, 100, 100));
		pSavedData.setLayout(null);

		pNewData = new JPanel();
		pNewData.setBounds(10, 1000, 980, 300);
		pNewData.setBackground(new Color(130, 130, 130));
		pNewData.setLayout(null);

		//Animation stuff
		Rectangle fromSavedData = new Rectangle(10, 240, 980, 300);
		Rectangle toSavedData = new Rectangle(10, 1000, 980, 300);

		Rectangle fromNewData = new Rectangle(10, 1000, 980, 300);
		Rectangle toNewData = new Rectangle(10, 240, 980, 300);
		slidUp = new Animate(pSavedData, fromSavedData, toSavedData, pNewData, fromNewData, toNewData);

		fromSavedData = new Rectangle(10, 1000, 980, 300);
		toSavedData = new Rectangle(10, 240, 980, 300);

		fromNewData = new Rectangle(10, 240, 980, 300);
		toNewData = new Rectangle(10, 1000, 980, 300);

		slidDown = new Animate(pSavedData, fromSavedData, toSavedData, pNewData, fromNewData, toNewData);


		if(paymentMethod.equals("visa")) {
			this.loadVisaComponents();
		}else if(paymentMethod.equals("paypal")) {
			this.loadPayPalComponents();
		}

		bBack = new JButton("<html><u>&#60 atrás</u></html>");
		bBack.setSize(50, 20);
		bBack.setBackground(null);
		bBack.setForeground(Color.white);
		bBack.setMargin(new Insets(0, 0, 0, 0));
		bBack.setBorder(null);
		Font fBack = new Font("Arial", Font.BOLD, 12);
		bBack.setFont(fBack);
		bBack.setLocation(10,10);

		bAlt = new JButton("Pagar con otra tarjeta");
		bAlt.setForeground(Color.WHITE);
		bAlt.setBackground(new Color (255, 193, 51));
		bAlt.setBorder(null);
		bAlt.setBounds(10, 260, 200, 30);

		bPay = new JButton("Finalizar pago");
		bPay.setForeground(Color.WHITE);
		bPay.setBackground(new Color(153, 200, 51));
		bPay.setBorder(null);
		bPay.setBounds(770, 260, 200, 30);


		bBack.addActionListener(this);
		bAlt.addActionListener(this);
		bPay.addActionListener(this);

		pAddressTitle.add(lAddressTitle);
		pAddress.add(pAddressTitle);
		pAddress.add(lAddress);
		pAddress.add(tfAddress);
		getContentPane().add(pAddress);
		getContentPane().add(pSavedData);
		getContentPane().add(pNewData);
		pPayTitle.add(lPayTitle);
		pSavedData.add(pPayTitle);
		pSavedData.add(bAlt);
		pSavedData.add(bPay);
		pSavedData.add(lTotal);

		pTitle.add(bBack);
		pTitle.add(lTitle);
		getContentPane().add(pTitle);
		getContentPane().add(pFooter);

	}

	private void loadVisaComponents() {
		UserDTO user = this.neverEmptyController.getUserDTO();
		
		this.lCardnumber = new JLabel("Número de tarjeta: ");
		this.lCardnumber.setBounds(40, 70, 200, 30);
		this.lCardnumber.setForeground(Color.white);
		String cardText = "XXXX    XXXX    XXXX    XXXX";
		if(user.getCardNumber() != -1) {
			String card = Long.toString(user.getCardNumber());
			cardText = card.substring(0, 4) + "    " + card.substring(4, 8) + "    " + card.substring(8, 12) + "    " + card.substring(12, 16);
					
		}
		String tmpCardnumber = cardText;
		cardnumber = new JLabel(tmpCardnumber);
		cardnumber.setBounds(260, 70, 200, 30 );
		cardnumber.setForeground(Color.white);

		this.lCardholder = new JLabel("Titular: ");
		this.lCardholder.setBounds(40, 105, 200, 30);
		this.lCardholder.setForeground(Color.white);
		
		String tmpCardholder = user.getCardholder();
		cardholder = new JLabel(tmpCardholder);
		cardholder.setBounds(260, 105, 200, 30 );
		cardholder.setForeground(Color.white);
		
		this.lExpDate = new JLabel("Válido hasta: ");
		this.lExpDate.setForeground(Color.white);
		this.lExpDate.setBounds(40, 140, 100, 30);

		lDay = new JLabel("d: ");
		lDay.setBounds(260, 140, 25, 25);
		lDay.setForeground(Color.white);
		
		this.expDateDay = new JTextField();
		this.expDateDay.setBorder(null);
		this.expDateDay.setBounds(285, 140, 25, 30);
		
		lMonth = new JLabel("m: ");
		lMonth.setBounds(320, 140, 25, 25);
		lMonth.setForeground(Color.white);

		this.expDateMonth = new JTextField();
		this.expDateMonth.setBorder(null);
		this.expDateMonth.setBounds(345, 140, 25, 30);

		this.lCvv = new JLabel("CVV: ");
		this.lCvv.setForeground(Color.white);
		this.lCvv.setBounds(40, 175, 100, 30);

		this.cvv = new JTextField();
		this.cvv.setBorder(null);
		this.cvv.setBounds(260, 175, 50, 30);

		this.pSavedData.add(lDay);
		this.pSavedData.add(lMonth);
		this.pSavedData.add(cardnumber);
		this.pSavedData.add(cardholder);
		this.pSavedData.add(lCardnumber);
		this.pSavedData.add(lCardholder);
		this.pSavedData.add(lExpDate);
		this.pSavedData.add(expDateDay);
		this.pSavedData.add(expDateMonth);
		this.pSavedData.add(lCvv);
		this.pSavedData.add(cvv);

		this.lAltCardnumber = new JLabel("Número de tarjeta: ");
		this.lAltCardnumber.setBounds(40, 70, 200, 30);
		this.lAltCardnumber.setForeground(Color.white);

		this.altCardnumber1 = new JTextField();
		this.altCardnumber1.setBounds(260, 70, 50, 30);
		this.altCardnumber1.setBorder(null);

		this.altCardnumber2 = new JTextField();
		this.altCardnumber2.setBounds(320, 70, 50, 30 );
		this.altCardnumber2.setBorder(null);

		this.altCardnumber3 = new JTextField();
		this.altCardnumber3.setBounds(380, 70, 50, 30 );
		this.altCardnumber3.setBorder(null);

		this.altCardnumber4 = new JTextField();
		this.altCardnumber4.setBounds(440, 70, 50, 30);
		this.altCardnumber4.setBorder(null);


		this.lAltCardholder = new JLabel("Titular: ");
		this.lAltCardholder.setForeground(Color.white);
		this.lAltCardholder.setBounds(40, 105, 200, 30);

		this.altCardholder = new JTextField();
		this.altCardholder.setBounds(260, 105, 320, 30);
		this.altCardholder.setBorder(null);

		this.lAltExpDate = new JLabel("Válido hasta: ");
		this.lAltExpDate.setForeground(Color.white);
		this.lAltExpDate.setBounds(40, 140, 100, 30);

		this.altExpDateDay = new JTextField();
		this.altExpDateDay.setBorder(null);
		this.altExpDateDay.setBounds(285, 140, 25, 30);

		this.altExpDateMonth = new JTextField();
		this.altExpDateMonth.setBorder(null);
		this.altExpDateMonth.setBounds(345, 140, 25, 30);

		this.lAltCvv = new JLabel("CVV: ");
		this.lAltCvv.setForeground(Color.white);
		this.lAltCvv.setBounds(40, 175, 100, 30);

		this.altCvv = new JTextField();
		this.altCvv.setBorder(null);
		this.altCvv.setBounds(260, 175, 50, 30);

		
		this.pNewData.add(lAltCardnumber);
		this.pNewData.add(altCardnumber1);
		this.pNewData.add(altCardnumber2);
		this.pNewData.add(altCardnumber3);
		this.pNewData.add(altCardnumber4);
		this.pNewData.add(lAltCardholder);
		this.pNewData.add(altCardholder);
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
				this.pSavedData.remove(this.pPayTitle);
				this.pSavedData.remove(this.lTotal);
				this.bAlt.setText("Pagar con los datos de perfil");
				
				if(paymentMethod.equals("visa")) {
					this.pSavedData.remove(this.lDay);
					this.pSavedData.remove(this.lMonth);
					lPayTitle.setText("Datos de VISA alternativos");
					this.pNewData.add(this.lDay);
					this.pNewData.add(this.lMonth);
				}else if(paymentMethod.equals("paypal")) {
					lPayTitle.setText("Datos de PayPal alternativos");
				}
				
				this.pNewData.add(this.bAlt);
				this.pNewData.add(this.bPay);
				this.pNewData.add(this.pPayTitle);
				this.pNewData.add(this.lTotal);
				
				this.state = "new";
			}else if(state.equals("new")) {
				this.slidDown.start();

				this.pNewData.remove(this.bAlt);
				this.pNewData.remove(this.bPay);
				this.pNewData.remove(this.pPayTitle);
				this.pNewData.remove(this.lTotal);
				
				if(paymentMethod.equals("visa")) {
					this.pNewData.remove(this.lDay);
					this.pNewData.remove(this.lMonth);
					lPayTitle.setText("Datos de VISA del perfil");
					this.pSavedData.add(this.lDay);
					this.pSavedData.add(this.lMonth);
				}else if(paymentMethod.equals("paypal")) {
					lPayTitle.setText("Datos de PayPal del perfil");
				}
				
				this.bAlt.setLocation(10, 260);
				this.bAlt.setText("Pagar con otra tarjeta");
				this.pSavedData.add(this.bAlt);
				this.pSavedData.add(this.bPay);
				this.pSavedData.add(this.pPayTitle);
				this.pSavedData.add(this.lTotal);
				
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
			
						PaymentDTO paymentDTO = new PaymentDTO(total, udto.getCardNumber(), udto.getCardholder(), expDateDay.getText()+"/"+expDateMonth.getText(), Integer.parseInt(cvv.getText()));
						paid = neverEmptyController.payWithVisa(paymentDTO);
					}else if(state.equals("new")) {
						long tmpCarnumber = Long.parseLong(altCardnumber1.getText()+altCardnumber2.getText()+altCardnumber3.getText()+altCardnumber4.getText());
						String tmpExpDate = altExpDateDay.getText() + "/" + altExpDateMonth.getText();
						PaymentDTO paymentDTO = new PaymentDTO(total, tmpCarnumber, altCardholder.getText(), tmpExpDate, Integer.parseInt(altCvv.getText()));
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

