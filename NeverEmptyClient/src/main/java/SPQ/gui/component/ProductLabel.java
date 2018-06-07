package SPQ.gui.component;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import SPQ.Utilities;
import SPQ.controller.NeverEmptyController;
import SPQ.gui.VentanaFavoritos;
import SPQ.gui.VentanaOfertas;
import SPQ.gui.VentanaTicket;

public class ProductLabel extends JPanel  implements ActionListener{
	private JLabel productName;
	private JLabel price;
	private JLabel quantity = new JLabel("0");
	private JButton bAdd = new JButton();
	private JButton bSub = new JButton();


	public ProductLabel(String productName, double price) {
		this.productName = new JLabel(productName);
		this.price = new JLabel(Double.toString(price));
	}

	public ProductLabel(String productName, String price, String quantity) {
		this.productName = new JLabel(productName);
		this.price = new JLabel(price);
		this.quantity = new JLabel(quantity);

		FlowLayout flowLayout = new FlowLayout(SwingConstants.LEFT, 0, 0);
		this.setLayout(flowLayout);
		this.setBounds(0, 0, 350, 25);
		this.setBackground(new Color(255, 255, 255));

		inicializarComponentes();
	}
	private void inicializarComponentes() {
		this.productName.setPreferredSize(new Dimension(100, 25));
		this.productName.setHorizontalAlignment(SwingConstants.CENTER);
		this.productName.setOpaque(true);
		this.productName.setForeground(new Color(255, 255, 255));
		this.productName.setBackground(new Color(100, 100, 100));

		this.add(this.productName);

		this.price.setPreferredSize(new Dimension(100, 25));
		this.price.setHorizontalAlignment(SwingConstants.CENTER);
		this.price.setOpaque(true);
		this.price.setForeground(new Color(255, 255, 255));
		this.price.setBackground(new Color(150, 150, 150));
		this.add(this.price);

		this.quantity.setPreferredSize(new Dimension(100, 25));
		this.quantity.setHorizontalAlignment(SwingConstants.CENTER);
		this.quantity.setOpaque(true);
		this.quantity.setForeground(new Color(255, 255, 255));
		this.quantity.setBackground(new Color(100, 100, 100));
		this.add(this.quantity);
		

		Utilities util = new Utilities();
		ImageIcon plusIcon = util.getImageFromResources("plus-green.png");
		this.bAdd.setIcon(plusIcon);
		this.bAdd.setSize(50, 50);
		this.bAdd.setPreferredSize(new Dimension(25, 25));
		this.bAdd.setHorizontalAlignment(SwingConstants.CENTER);
		this.bAdd.setOpaque(true);
		this.bAdd.setBackground(new Color(150, 150, 150));
		this.bAdd.setBorder(null);
		this.bAdd.addActionListener(this);
		this.add(this.bAdd);

		ImageIcon subIcon = util.getImageFromResources("minus-red.png");
		this.bSub.setIcon(subIcon);
		this.bSub.setSize(50, 50);
		this.bSub.setPreferredSize(new Dimension(25, 25));
		this.bSub.setHorizontalAlignment(SwingConstants.CENTER);
		this.bSub.setOpaque(true);
		this.bSub.setBackground(new Color(150, 150, 150));
		this.bSub.setBorder(null);
		this.bSub.addActionListener(this);
		this.add(this.bSub);


	}



	public void actionPerformed(ActionEvent e) {
		System.out.println("ActionPerformed");
		if (e.getSource() == this.bAdd) {
			this.quantity.setText(
					Integer.toString(
							Integer.parseInt(this.quantity.getText()) + 1
							)
					);
		}
		if (e.getSource() == this.bSub) {
			int quantity = Integer.parseInt(this.quantity.getText());
			if (quantity > 0) {
				quantity = quantity -1;
			}

			this.quantity.setText(
					Integer.toString(
							quantity
							)
					);
		}
	}
	public JLabel getproductName() {
		return productName;
	}
	public void setproductName(JLabel productName) {
		this.productName = productName;
	}

	public JLabel getPrice() {
		return price;
	}

	public void setPrice(JLabel price) {
		this.price = price;
	}

	public JLabel getQuantity() {
		return quantity;
	}

	public void setQuantity(JLabel quantity) {
		this.quantity = quantity;
	}

	public JButton getbAdd() {
		return bAdd;
	}

	public void setbAdd(JButton bAdd) {
		this.bAdd = bAdd;
	}

	public JButton getbSub() {
		return bSub;
	}

	public void setbSub(JButton bSub) {
		this.bSub = bSub;
	}
}
