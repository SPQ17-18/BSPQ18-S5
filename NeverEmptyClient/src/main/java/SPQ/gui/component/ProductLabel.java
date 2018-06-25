/** @package SPQ.component
 	@brief This is the brief documentation for the java package SPQ.component
 */

/** @class ProductLabel class.h "inc/class.h" 
* @brief This is a ProductLabel class.
* Some details about the ProductLabel class 
*/

package SPQ.gui.component;


import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import SPQ.Utilities;
import SPQ.gui.VentanaPrincipal;


public class ProductLabel extends JPanel implements ActionListener{
	private JLabel productName;
	private JLabel price;
	private JLabel quantity = new JLabel("0");
	private JLabel sale;
	private JButton bAdd = new JButton();
	private JButton bSub = new JButton();
	private VentanaPrincipal vp;

	public ProductLabel(String productName, String price, String sale, VentanaPrincipal vp) {
		this.vp = vp;
		this.productName = new JLabel(productName);
		this.price = new JLabel(price);
		this.sale = new JLabel(sale);

		FlowLayout flowLayout = new FlowLayout(SwingConstants.LEFT, 0, 0);
		this.setLayout(flowLayout);
		this.setBounds(0, 0, 350, 25);
		this.setBackground(new Color(255, 255, 255));

		inicializarComponentes();
	}

	public ProductLabel(String productName, String price, String quantity, String sale, VentanaPrincipal vp) {
		this.vp = vp;
		this.productName = new JLabel(productName);
		this.quantity = new JLabel(quantity);
		this.price = new JLabel(price);
		this.sale = new JLabel(sale);

		FlowLayout flowLayout = new FlowLayout(SwingConstants.LEFT, 0, 0);
		this.setLayout(flowLayout);
		this.setBounds(0, 0, 485, 25);
		this.setBackground(new Color(255, 255, 255));

		inicializarComponentes();
	}

	private void inicializarComponentes() {
		this.productName.setPreferredSize(new Dimension(145, 25));
		this.productName.setHorizontalAlignment(SwingConstants.CENTER);
		this.productName.setOpaque(true);
		this.productName.setForeground(new Color(255, 255, 255));
		this.productName.setBackground(new Color(100, 100, 100));

		this.add(this.productName);


		if( Double.parseDouble(sale.getText()) > 0) {
			this.price.setPreferredSize(new Dimension(72, 25));
			this.sale.setPreferredSize(new Dimension(73, 25));
			this.sale.setForeground(new Color(255, 255, 255));
			this.sale.setBackground(Color.orange);
			this.sale.setHorizontalAlignment(SwingConstants.CENTER);
			this.sale.setOpaque(true);


		}else {
			this.price.setPreferredSize(new Dimension(145, 25));


		}
		this.price.setForeground(new Color(255, 255, 255));
		this.price.setBackground(new Color(150, 150, 150));
		this.price.setHorizontalAlignment(SwingConstants.CENTER);
		this.price.setOpaque(true);
		this.add(this.price);			
		if(Double.parseDouble(sale.getText()) > 0) {
			sale.setText(sale.getText() +"%");
			this.add(sale);
		}

		this.quantity.setPreferredSize(new Dimension(145, 25));
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
			if(Integer.parseInt(this.quantity.getText()) == 1) {
				vp.pCatalogue.remove(this);
				vp.pShoppingCart.add(this);	
			}
			
			vp.pCatalogue.setVisible(false);
			vp.pShoppingCart.setVisible(false);
			
			vp.pCatalogue.setVisible(true);
			vp.pShoppingCart.setVisible(true);
			

		}
		if (e.getSource() == this.bSub) {
			int quantity = Integer.parseInt(this.quantity.getText());
			if (quantity > 1) {
				quantity = quantity -1;
			}else if(quantity == 1){
				quantity = quantity -1;
				vp.pCatalogue.add(this);
				vp.pShoppingCart.remove(this);
				
				vp.pCatalogue.setVisible(false);
				vp.pShoppingCart.setVisible(false);
				
				vp.pCatalogue.setVisible(true);
				vp.pShoppingCart.setVisible(true);
			}

			this.quantity.setText(
					Integer.toString(
							quantity
							)
					);
		}
	}

	public JLabel getSale() {
		return sale;
	}

	public void setSale(JLabel sale) {
		this.sale = sale;
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
