package SPQ.gui;


import java.awt.Color;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.LineBorder;

import SPQ.Utilities;
import SPQ.controller.NeverEmptyController;
import SPQ.data.Product;
import SPQ.dto.ProductDTO;

import SPQ.gui.component.ProductLabel;
import org.apache.log4j.Logger;


public class VentanaPrincipal extends JFrame implements ActionListener{

	private JLabel lTitle, lCatalogue, lShoppingCart, lName, lPrice, lQuantity, lName2, lPrice2, lQuantity2; 
	private JButton bSales, bProfile, bBuy;

	private JPanel pTitle, pFooter, pProfile, pCatalogueTitle, pShoppingCartTitle, pCatalogueHeader, pShoppingCartHeader;

	public JPanel pCatalogue, pShoppingCart;

	private NeverEmptyController neverEmptyController;

	private List<ProductLabel> productList = new ArrayList<ProductLabel>();

	static Logger logger = Logger.getLogger(VentanaInicio.class.getName());

	private String state = "all";

	public VentanaPrincipal(NeverEmptyController neverEmptyController) {
		this.neverEmptyController = neverEmptyController;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(1000, 600);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(50, 50, 50));
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(null);

		this.setTitle("NeverEmpty");

		this.inicializarComponentes();                
	}


	private void inicializarComponentes() {
		
		lName = new JLabel("Nombre");
		lName.setBounds(0, 0, 145, 30);
		lName.setForeground(Color.white);
		lName.setBackground(new Color(150, 150, 150));
		lName.setOpaque(true);
		lName.setHorizontalAlignment(SwingConstants.CENTER);

		lName2 = new JLabel("Nombre");
		lName2.setBounds(0, 0, 145, 30);
		lName2.setForeground(Color.white);
		lName2.setBackground(new Color(150, 150, 150));
		lName2.setOpaque(true);
		lName2.setHorizontalAlignment(SwingConstants.CENTER);

		lPrice = new JLabel("Precio");
		lPrice.setBounds(145, 0, 145, 30);
		lPrice.setForeground(Color.white);
		lPrice.setBackground(new Color(100, 100, 100));
		lPrice.setOpaque(true);
		lPrice.setHorizontalAlignment(SwingConstants.CENTER);

		lPrice2 = new JLabel("Precio");
		lPrice2.setBounds(145, 0, 145, 30);
		lPrice2.setForeground(Color.white);
		lPrice2.setBackground(new Color(100, 100, 100));
		lPrice2.setOpaque(true);
		lPrice2.setHorizontalAlignment(SwingConstants.CENTER);

		lQuantity = new JLabel("Cantidad");
		lQuantity.setBounds(290, 0, 145, 30);
		lQuantity.setForeground(Color.white);
		lQuantity.setBackground(new Color(150, 150, 150));
		lQuantity.setOpaque(true);
		lQuantity.setHorizontalAlignment(SwingConstants.CENTER);

		lQuantity2 = new JLabel("Cantidad");
		lQuantity2.setBounds(290, 0, 145, 30);
		lQuantity2.setForeground(Color.white);
		lQuantity2.setBackground(new Color(150, 150, 150));
		lQuantity2.setOpaque(true);
		lQuantity2.setHorizontalAlignment(SwingConstants.CENTER);

		Font titleBold = new Font("Cantidad", Font.BOLD, 25);

		pCatalogue = new JPanel();
		pCatalogue.setBackground(Color.lightGray);
		pCatalogue.setBounds(10, 120, 485, 420);
		this.getProducts();
		for(ProductLabel p :this.productList) {
			pCatalogue.add(new ProductLabel(p.getproductName().getText(), p.getPrice().getText(), p.getQuantity().getText(),
					p.getSale().getText().replace("%", ""), this));
		}
		pCatalogue.setVisible(true);

		pShoppingCart = new JPanel();
		pShoppingCart.setBackground(Color.lightGray);
		pShoppingCart.setBounds(505, 120, 485, 420);


		pTitle = new JPanel();
		pTitle.setBounds(0, 0, 1000, 50);
		pTitle.setBackground(new Color(30, 30, 30));
		pTitle.setLayout(null);

		lTitle = new JLabel("NeverEmpty", SwingConstants.CENTER);
		lTitle.setBounds(400, 10, 200, 30);
		lTitle.setFont(titleBold);
		lTitle.setForeground(new Color(253, 253, 253));

		pFooter = new JPanel();
		pFooter.setBounds(0, 550, 1000, 50);
		pFooter.setBackground(new Color(30, 30, 30));
		pFooter.setLayout(null);

		pProfile = new JPanel();
		pProfile.setBounds(880, -40, 150, 150);
		pProfile.setBackground(null);
		pProfile.setOpaque(false);
		pProfile.setBorder(new LineBorder(new Color(30, 30, 30), 400, true));


		Utilities util = new Utilities();
		ImageIcon iconProfile = util.getImageFromResources("profile.png");
		ImageIcon iconBuy = util.getImageFromResources("buy.png");
		ImageIcon iconSale = util.getImageFromResources("sale.png");
		

		bProfile = new JButton(iconProfile);
		bProfile.setBounds(925, 25, 50, 50);
		bProfile.setOpaque(false);
		bProfile.setBackground(null);
		bProfile.setBorder(null);
		bProfile.addActionListener(this);

		pCatalogueTitle = new JPanel();
		pCatalogueTitle.setBounds(10, 60, 485, 30);
		pCatalogueTitle.setBorder(null);
		pCatalogueTitle.setBackground(Color.white);
		pCatalogueTitle.setOpaque(true);

		lCatalogue = new JLabel("Catálogo");
		lCatalogue.setBounds(20,0,200,30);

		pCatalogueHeader = new JPanel();
		pCatalogueHeader.setBounds(10, 90, 485, 30);
		pCatalogueHeader.setBorder(null);
		pCatalogueHeader.setBackground(new Color(50, 50, 50));
		pCatalogueHeader.setOpaque(true);
		pCatalogueHeader.setLayout(null);


		pShoppingCartTitle = new JPanel();
		pShoppingCartTitle.setBounds(505, 60, 485, 30);
		pShoppingCartTitle.setBorder(null);
		pShoppingCartTitle.setBackground(Color.white);
		pShoppingCartTitle.setOpaque(true);

		pShoppingCartHeader = new JPanel();
		pShoppingCartHeader.setBounds(505, 90, 485, 30);
		pShoppingCartHeader.setBorder(null);
		pShoppingCartHeader.setBackground(new Color(50, 50, 50));
		pShoppingCartHeader.setOpaque(true);
		pShoppingCartHeader.setLayout(null);


		lShoppingCart = new JLabel("Carrito de la compra");
		lShoppingCart.setBounds(20,0,200,30);

		bSales = new JButton("Filtrar ofertas", iconSale );
		bSales.setBounds(10, 560, 120, 30);
		bSales.setBackground(new Color (255, 193, 51));
		bSales.setBorder(null);
		bSales.addActionListener(this);
		
		
		bBuy = new JButton("Comprar", iconBuy );
		bBuy.setBounds(870, 560, 120, 30);
		bBuy.setBackground(new Color(153, 200, 51));
		bBuy.setBorder(null);
		bBuy.addActionListener(this);



		getContentPane().add(bSales);
		getContentPane().add(bBuy);
		
		getContentPane().add(bProfile);
		getContentPane().add(pProfile);

		pTitle.add(lTitle);
		getContentPane().add(pTitle);
		getContentPane().add(pFooter);

		pCatalogueTitle.add(lCatalogue);
		getContentPane().add(pCatalogueTitle);
		pCatalogueHeader.add(lName);
		pCatalogueHeader.add(lPrice);
		pCatalogueHeader.add(lQuantity);
		getContentPane().add(pCatalogueHeader);

		pShoppingCartTitle.add(lShoppingCart);
		getContentPane().add(pShoppingCartTitle);
		pShoppingCartHeader.add(lName2);
		pShoppingCartHeader.add(lPrice2);
		pShoppingCartHeader.add(lQuantity2);
		getContentPane().add(pShoppingCartHeader);


		getContentPane().add(pShoppingCart);
		getContentPane().add(pCatalogue);




	}

	public static void main(String[] args) {

		VentanaPrincipal v = new VentanaPrincipal(null);
		v.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == pCatalogueTitle) {
			for(Component c : this.pShoppingCart.getComponents()) {
				ProductLabel p = (ProductLabel) c;
				if(p.getQuantity().getText().equals("0")) {
					this.pShoppingCart.remove(c);
					this.pCatalogue.add(p);
				}
			}
			this.pCatalogue.setVisible(true);
			this.pShoppingCart.setVisible(false);
		}

		if (e.getSource() == pShoppingCartTitle) {
			System.out.println("Lista de la compra");
			this.pCatalogue.setVisible(false);

			for(Component c: this.pCatalogue.getComponents()) {
				ProductLabel p = (ProductLabel) c;
				if(!p.getQuantity().getText().equals("0")) {
					this.pCatalogue.remove(c);
					this.pShoppingCart.add(p);
					System.out.println("Añadido a shoppingList: "+ p.getproductName().getText());
				}
			}

			pShoppingCart.setVisible(true);
		}

		if (e.getSource() == bSales) {
			if(state.equals("all")) {

				for(Component c : this.pCatalogue.getComponents()) {
					ProductLabel p = (ProductLabel) c;
					if(Double.parseDouble(p.getSale().getText().replace("%", "")) == 0.0) {
						pCatalogue.remove(c);
					}
				}

				pCatalogue.setVisible(false);
				pCatalogue.setVisible(true);
				state = "sales";
				logger.info("Mostrando ofertas.");
			}else if (state.equals("sales")) {
				pCatalogue.removeAll();
				for(ProductLabel pl : productList ) {
					pCatalogue.add(pl);
				}
				pCatalogue.setVisible(false);
				pCatalogue.setVisible(true);
				state = "all";
				logger.info("Mostrando catalogo.");
			}
		}

		if (e.getSource() == bBuy) {
			List<ProductLabel> shoppingCart = new ArrayList<ProductLabel>();
			if(this.pShoppingCart.getComponents().length != 0) {
				for(Component c : this.pShoppingCart.getComponents()) {
					ProductLabel p = (ProductLabel) c;
					shoppingCart.add(p);
				}
				this.setEnabled(false);
				VentanaTicket vt = new VentanaTicket(this.neverEmptyController, shoppingCart, this);
				vt.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Error, la lista de la compra está vacía.", "ERROR", JOptionPane.ERROR_MESSAGE);
				
			}
		}
			if (e.getSource() == bProfile) {
				VentanaPerfil vp = new VentanaPerfil(this.neverEmptyController, this);
				vp.setVisible(true);
				this.setVisible(false);
			}
		}

		private void getProducts() {
			ProductDTO productDTO = null;
			try {
				productDTO = neverEmptyController.getProducts();
				logger.info("Productos: " + productDTO.getProductList().toString());
				for (Product product : productDTO.getProductList()) {
					logger.info("Producto: " + product.toString());
					ProductLabel pl = new ProductLabel(product.getName(), Double.toString(product.getPrice()), Double.toString(product.getSale()), this);
					this.productList.add(pl);
				}
			}catch(Exception e) {
				System.out.println(e);
			}
		}

	}

