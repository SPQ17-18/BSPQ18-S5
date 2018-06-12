package SPQ.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import SPQ.Utilities;
import SPQ.controller.NeverEmptyController;
import SPQ.gui.component.ProductLabel;
public class VentanaMetodoPago extends JFrame implements ActionListener{
	
	NeverEmptyController neverEmptyController;
	JButton bVisa, bPaypal, bBack;
	
	String selected = "back";
	
	public VentanaMetodoPago (NeverEmptyController neverEmptyController, List<ProductLabel> shoppingList, double total) {
		this.neverEmptyController = neverEmptyController;
		this.setTitle("Seleccionar método de pago");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(50, 50, 50));
		this.getContentPane().setLayout(null);
		
		Utilities util = new Utilities();
		ImageIcon iconVisa = util.getImageFromResources("visa.png");
		ImageIcon iconPaypal = util.getImageFromResources("paypal.png");
		
		this.bVisa = new JButton("Pagar con Visa", iconVisa);
		this.bVisa.setForeground(Color.white);
		this.bVisa.setBorder(null);
		this.bVisa.setIconTextGap(15);
		this.bVisa.setMargin(new Insets(0, 0, 0, 5));
		this.bVisa.setBackground(null);
		this.bVisa.setSize(215, 50);
		this.bVisa.setLocation(90, 90);
		this.bVisa.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		this.bPaypal = new JButton("Pagar con Paypal", iconPaypal);
		this.bPaypal.setForeground(Color.white);
		this.bPaypal.setBorder(null);
		this.bPaypal.setMargin(new Insets(0, 0, 0, 5));
		this.bPaypal.setIconTextGap(15);
		this.bPaypal.setBackground(null);
		this.bPaypal.setHorizontalAlignment(SwingConstants.LEFT);
		this.bPaypal.setSize(215, 50);
		this.bPaypal.setLocation(90, 160);
				
				
		this.bBack = new JButton("<html><u>&#60 atrás</u></html>");
		this.bBack.setSize(50, 20);
		this.bBack.setBackground(null);
		this.bBack.setForeground(Color.white);
		this.bBack.setMargin(new Insets(0, 0, 0, 0));
		this.bBack.setBorder(null);
		Font fBack = new Font("Arial", Font.BOLD, 12);
		this.bBack.setFont(fBack);
		this.bBack.setLocation(10,10);
		
		
		this.bBack.addActionListener(this);
		this.bVisa.addActionListener(this);
		this.bPaypal.addActionListener(this);
		
				
		this.getContentPane().add(bVisa);
		this.getContentPane().add(bPaypal);
		this.getContentPane().add(bBack);


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(!(e.getSource() == bBack)) {
			// Abrir ventana de pago
			dispose();
		}else {
			dispose();
		}
	}

	public static void main(String[] args) {
		VentanaMetodoPago vmp = new VentanaMetodoPago(null, null, 0);
		vmp.setVisible(true);
	}
}
