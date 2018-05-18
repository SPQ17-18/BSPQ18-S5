package SPQ.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import SPQ.controller.NeverEmptyController;
import SPQ.data.Product;
import SPQ.dto.ProductDTO;
import SPQ.gui.component.ProductLabel;
import SPQ.remote.INeverEmptyFacade;

public class VentanaPrincipal extends JFrame implements ActionListener{
	

	JLabel lblTitulo;
	JButton bPanel1, bPanel2, btnPanelInterno2, btnSales, btnFavorites, btnEliminarProducto, bRecetas , bPerfil;
	//JButton btnRecipes;
	JButton bTicket;
	JTextArea campo;

	JPanel panel1, panel2;
	String texto;

	NeverEmptyController neverEmptyController;


	//Labels de los productos de la BD
	JButton b1, b2, b3, b4, b5;
	JLabel textArea, textArea2, textArea3, textArea4, textArea5;

	List<ProductLabel> productList = new ArrayList<ProductLabel>();
	List<ProductLabel> shoppingList = new ArrayList<ProductLabel>();

	public VentanaPrincipal(NeverEmptyController neverEmptyController) {
		this.neverEmptyController = neverEmptyController;
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setSize(400, 375);
		this.setTitle("Bienvenido a NeverEmpty!"); 
		this.setResizable(false);
		getContentPane().setLayout(null);

		this.setLocationRelativeTo(null);
		inicializarComponentes();   // inicializamos los atributos o componentes                   
	}


	private void inicializarComponentes() {

		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(5,112, 350, 200);
		this.getProducts();
		for(ProductLabel p :this.productList) {
			panel1.add(new ProductLabel(p.getproductName().getText(), p.getPrice().getText(), p.getQuantity().getText()));
		}
		panel1.setVisible(true);

		panel2 = new JPanel();
		panel2.setBackground(Color.RED);
		panel2.setBounds(5,112, 370, 200);
		panel2.setVisible(false);

		lblTitulo = new JLabel ("Seleccione los productos que desea comprar");
		lblTitulo.setBounds(10,14,275,51);

		bPanel1 = new JButton("Catalogo");
		bPanel1.setBounds(10, 76, 95, 23);
		bPanel1.addActionListener(this);

		bPanel2 = new JButton("Lista de Compra");
		bPanel2.setBounds(109, 76, 135, 23);
		bPanel2.addActionListener(this);

		btnSales = new JButton("Ofertas");
		btnSales.setBounds(10, 315, 95, 23);

		btnFavorites = new JButton("Favoritos");
		btnFavorites.setBounds(109, 315, 95, 23);


		bRecetas = new JButton("Recetas");
		bRecetas.setBounds(208, 315, 95, 23);

		cargarComponentesPanel2();

		getContentPane().add(lblTitulo);
		getContentPane().add(bPanel1);
		getContentPane().add(bPanel2);
		getContentPane().add(btnSales);
		getContentPane().add(btnFavorites);
		//add(btnRecipes);
		getContentPane().add(bRecetas);

		getContentPane().add(panel2);
		getContentPane().add(panel1);

//		bPerfil = new JButton("Perfil");
//		bPerfil.setBounds(269, 76, 89, 23);
//		getContentPane().add(bPerfil);
//		bPerfil.addActionListener(this);

	}

	private void cargarComponentesPanel2() {

		campo = new JTextArea();

	}


	public static void main(String[] args) {
		
		VentanaPrincipal v = new VentanaPrincipal(null);
		v.setVisible(true);
	}



	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bPanel1) {
			panel1.removeAll();
			for(ProductLabel p : productList) {
				
				System.out.println(p.getproductName().getText());
				panel1.add(p);
			}
			
			panel1.setVisible(true);
			panel2.setVisible(false);
		}

		if (e.getSource() == bPanel2) {
			System.out.println("Lista de la compra");
			panel1.setVisible(false);
			getShoppingList();
			for(ProductLabel p : this.shoppingList) {
				panel2.add(p);
			}
			panel2.setVisible(true);
		}

		if (e.getSource() == btnPanelInterno2) {
		}

		if (e.getSource() == btnSales) {
			VentanaOfertas o = new VentanaOfertas();
			o.setVisible(true);
			dispose();
		}

		if (e.getSource() == btnFavorites) {
			VentanaFavoritos f = new VentanaFavoritos();
			f.setVisible(true);
		}

		if (e.getSource() == bTicket) {
			VentanaTicket r = new VentanaTicket(this.neverEmptyController);
			r.setVisible(true);
			dispose();
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
	
	private void getShoppingList() {
		this.shoppingList = new ArrayList<ProductLabel>();
		for(Component c: this.panel1.getComponents()) {
			ProductLabel p = (ProductLabel) c;
			if(Integer.parseInt(p.getQuantity().getText()) > 0) {
				this.shoppingList.add(p);
				System.out.println("Añadido a shoppingList: "+ p.getproductName().getText());
			}
		}
		
	}
	
}

