package SPQ.gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SPQ.controller.NeverEmptyController;
import SPQ.data.Product;
import SPQ.dto.ProductDTO;

import SPQ.gui.component.ProductLabel;


public class VentanaPrincipal extends JFrame implements ActionListener{

	JLabel lblTitulo;
	JButton bCatalogue, bShoppingCart, btnSales, bPerfil, bBuy;

	JPanel pCatalogue, pShoppingCart;

	NeverEmptyController neverEmptyController;

	List<ProductLabel> productList = new ArrayList<ProductLabel>();

	public VentanaPrincipal(NeverEmptyController neverEmptyController) {
		this.neverEmptyController = neverEmptyController;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setSize(400, 375);
		this.setTitle("Bienvenido a NeverEmpty!"); 
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		this.inicializarComponentes();                
	}


	private void inicializarComponentes() {

		pCatalogue = new JPanel();
		pCatalogue.setBackground(Color.WHITE);
		pCatalogue.setBounds(5,112, 350, 200);
		this.getProducts();
		for(ProductLabel p :this.productList) {
			pCatalogue.add(new ProductLabel(p.getproductName().getText(), p.getPrice().getText(), p.getQuantity().getText()));
		}
		pCatalogue.setVisible(true);

		pShoppingCart = new JPanel();
		pShoppingCart.setBackground(Color.RED);
		pShoppingCart.setBounds(5,112, 370, 170);
		pShoppingCart.setVisible(false);

		lblTitulo = new JLabel ("Seleccione los productos que desea comprar");
		lblTitulo.setBounds(10,14,275,51);

		bCatalogue = new JButton("Catalogo");
		bCatalogue.setBounds(10, 76, 95, 23);
		bCatalogue.addActionListener(this);

		bShoppingCart = new JButton("Lista de Compra");
		bShoppingCart.setBounds(109, 76, 135, 23);
		bShoppingCart.addActionListener(this);

		btnSales = new JButton("Ofertas");
		btnSales.setBounds(10, 315, 95, 23);

		bBuy = new JButton("Comprar");
		bBuy.setBounds(10,  290, 350, 23);
		bBuy.addActionListener(this);


		this.getContentPane().add(lblTitulo);
		this.getContentPane().add(bCatalogue);
		this.getContentPane().add(bShoppingCart);
		this.getContentPane().add(btnSales);
		this.getContentPane().add(bBuy);

		this.getContentPane().add(pShoppingCart);
		this.getContentPane().add(pCatalogue);

		bPerfil = new JButton("Perfil");
		bPerfil.setBounds(269, 76, 89, 23);
		getContentPane().add(bPerfil);
		bPerfil.addActionListener(this);

	}

	public static void main(String[] args) {

		VentanaPrincipal v = new VentanaPrincipal(null);
		v.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bCatalogue) {
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

		if (e.getSource() == bShoppingCart) {
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

		if (e.getSource() == btnSales) {
			VentanaOfertas o = new VentanaOfertas();
			o.setVisible(true);
			dispose();
		}

		if (e.getSource() == bBuy) {
			List<ProductLabel> shoppingCart = new ArrayList<ProductLabel>();
			for(Component c : this.pShoppingCart.getComponents()) {
				ProductLabel p = (ProductLabel) c;
				shoppingCart.add(p);
			}
			VentanaTicket r = new VentanaTicket(this.neverEmptyController, shoppingCart);
			r.setVisible(true);
		}

		if (e.getSource() == bPerfil) {
			VentanaPerfil r = new VentanaPerfil(this.neverEmptyController);
			r.setVisible(true);
			dispose();
		}
	}

	private void getProducts() {
		ProductDTO productDTO = null;
		try {
			productDTO = neverEmptyController.getProducts();
			System.out.println("Productos: " + productDTO.getProductList().toString());
			for (Product product : productDTO.getProductList()) {
				ProductLabel pl = new ProductLabel(product.getName(), product.getPrice());
				this.productList.add(pl);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}

