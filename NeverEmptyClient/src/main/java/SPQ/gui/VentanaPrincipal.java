package SPQ.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
import SPQ.remote.INeverEmptyFacade;

public class VentanaPrincipal extends JFrame implements ActionListener{

	JLabel lblTitulo;
	JButton btnPanel1, btnPanel2, btnPanelInterno2,btnSales, btnFavorites;
	//JButton btnRecipes;
	JButton btnTic;
	JTextArea campo;
	
	JPanel panel1, panel2;
	String texto;
	
	/*
	String que se le pasa a la BD para añadir los productos 
	a la BD de productos en forma de lista de la compra
	*/
	String listaCompra = "";
	//Contador de cantidad de producto 1
	int i = 0;
	//Contador de cantidad de producto 2
	int i2 = 0;
	//Contador de cantidad de producto 3
	int i3 = 0;
	//Contador de cantidad de producto 4
	int i4 = 0;
	//Contador de cantidad de producto 5
	int i5 = 0;
	
	//String de cantidad de cada producto
	String p1, p2, p3, p4, p5;
	
	NeverEmptyController neverEmptyController;
	
	//Labels de los productos de la BD
	JButton b1, b2, b3, b4, b5;
	JLabel textArea, textArea2, textArea3, textArea4, textArea5;
	
	

	public VentanaPrincipal(NeverEmptyController neverEmptyController) {
		this.neverEmptyController = neverEmptyController;
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setSize(400, 375);
		this.setTitle("Bienvenido a NeverEmpty!"); 
		this.setResizable(false);
		setLayout(null);
		
		this.setLocationRelativeTo(null);
		inicializarComponentes();   // inicializamos los atributos o componentes                   
}

	private void inicializarComponentes() {
		
		panel1 = new JPanel();
		panel1.setBackground(Color.GREEN);
		panel1.setBounds(5,112, 370, 200);
		panel1.setLayout(null);
		panel1.setVisible(true);
		
		panel2 = new JPanel();
		panel2.setBackground(Color.RED);
		panel2.setBounds(5,112, 370, 200);
		panel2.setLayout(null);
		panel2.setVisible(false);
		
		lblTitulo = new JLabel ("Seleccione los productos que desea comprar");
		lblTitulo.setBounds(10,14,275,51);
		
		btnPanel1 = new JButton("Catalogo");
		btnPanel1.setBounds(10, 76, 95, 23);
		btnPanel1.addActionListener(this);
		
		btnPanel2 = new JButton("Lista de Compra");
		btnPanel2.setBounds(109, 76, 135, 23);
		btnPanel2.addActionListener(this);
		
		btnSales = new JButton("Ofertas");
		btnSales.setBounds(10, 315, 95, 23);
		
		btnFavorites = new JButton("Favoritos");
		btnFavorites.setBounds(109, 315, 95, 23);
		
//		btnRecipes = new JButton("Recetas");
//		btnRecipes.setBounds(208, 315, 95, 23);
		
		btnTic = new JButton("Ticket");
		btnTic.setBounds(208, 315, 95, 23);
		
//		textArea = new JTextArea();
//		textArea.setBounds(10,117,350,181);
//		textArea.setText(texto);
		
		cargarComponentesPanel1();
		cargarComponentesPanel2();
		
		add(lblTitulo);
		add(btnPanel1);
		add(btnPanel2);
		add(btnSales);
		add(btnFavorites);
		//add(btnRecipes);
		add(btnTic);
		
		add(panel2);
		add(panel1);
		
	}
	
	private void cargarComponentesPanel2() {
		/*
		 * listaCompra = new String();
		 * lblListaCompra = new JLabel;
		 * */
		campo = new JTextArea();
		//textArea.setBounds(10, 10, 180, 25);
		//		String campo = "\nAlvaro";
		//		textArea.setText(campo);
	}

	private void cargarComponentesPanel1() {
		
		btnPanelInterno2 = new JButton("Comprar");
		btnPanelInterno2.setBounds(200, 10, 90, 25);
		panel1.add(btnPanelInterno2);
		btnPanelInterno2.addActionListener(this);
//		textArea = new JTextArea();
//		textArea.setBounds(10, 10, 350, 181);
//		try {
//		String texto = this.neverEmptyController.getProducts();
//		}catch (Exception e) {
//			System.out.println(e);
//		}
//		textArea.setText(texto);
//		panel1.add(textArea);
		
		
		
		//Pone el nombre del producto a su boton correspondiente
		String productos = "";
		try {
			productos = neverEmptyController.getProducts();
		}catch(Exception e) {
			System.out.println(e);
		}
		String[] parts = productos.split(";");
		
		//[producto, nombre/precio]
		String part11,part12,part21,part22,part31,part32,part41,part42,part51,part52;
		
		//Producto1
		String part1 = parts[0];
		String[] p1 = part1.split(",");
		part11 = p1[1]; //Nombre del producto 1
		part12 = p1[2]; //Precio del producto 1
		
		//Producto2
		String part2 = parts[1]; 
		String[] p2 = part2.split(",");
		part21 = p2[1]; //Nombre del producto 2
		part22 = p2[2]; //Precio del producto 2
		
		//Producto3
		String part3 = parts[2]; 
		String[] p3 = part3.split(",");
		part31 = p3[1]; //Nombre del producto 3
		part32 = p3[2]; //Precio del producto 3
		
		//Producto4
		String part4 = parts[3];
		String[] p4 = part4.split(",");
		part41 = p4[1]; //Nombre del producto 4
		part42 = p4[2]; //Precio del producto 4
		
		//Producto5
		String part5 = parts[4];
		String[] p5 = part5.split(",");
		part51 = p5[1]; //Nombre del producto 5
		part52 = p5[2]; //Precio del porducto 5

		b1 = new JButton();
		b1.setBounds(10, 10, 90, 25);
		b1.setText(part11);
		panel1.add(b1);
		
		textArea = new JLabel("Cantidad");
		textArea.setBounds(105, 10, 90, 25);
		panel1.add(textArea);
		
		b2 = new JButton();
		b2.setBounds(10, 40, 90, 25);
		b2.setText(part21);
		panel1.add(b2);
		
		textArea2 = new JLabel("Cantidad");
		textArea2.setBounds(105, 40, 90, 25);
		panel1.add(textArea2);
		
		b3 = new JButton();
		b3.setBounds(10, 70, 90, 25);
		b3.setText(part31);
		panel1.add(b3);
		
		textArea3 = new JLabel("Cantidad");
		textArea3.setBounds(105, 70, 90, 25);
		panel1.add(textArea3);
		
		b4 = new JButton();
		b4.setBounds(10, 70, 90, 25);
		b4.setText(part41);
		panel1.add(b4);
		
		textArea4 = new JLabel("Cantidad");
		textArea4.setBounds(105, 70, 90, 25);
		panel1.add(textArea4);
		
		b5 = new JButton();
		b5.setBounds(10, 70, 90, 25);
		b5.setText(part51);
		panel1.add(b5);
		
		textArea5 = new JLabel("Cantidad");
		textArea5.setBounds(105, 70, 90, 25);
		panel1.add(textArea5);
		
		btnSales.addActionListener(this);
		btnFavorites.addActionListener(this);
		//btnRecipes.addActionListener(this);
		btnTic.addActionListener(this);
		
	}
	
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VentanaPrincipal v = new VentanaPrincipal(this);
		v.setVisible(true);
	}
	*/

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnPanel1) {
			panel1.setVisible(true);
			panel2.setVisible(false);
		}

		if (e.getSource()==btnPanel2) {
			panel1.setVisible(false);
			panel2.setVisible(true);
		}

		if (e.getSource()==btnPanelInterno2) {


		}
		
		if (e.getSource()==btnSales) {

			VentanaOfertas o = new VentanaOfertas();
			o.setVisible(true);
			dispose();
			
		}
		
		if (e.getSource()==btnFavorites) {

			VentanaFavoritos f = new VentanaFavoritos();
			f.setVisible(true);
			
		}
		
//		if (e.getSource()==btnRecipes) {
//
//			VentanaRecetas r = new VentanaRecetas();
//			r.setVisible(true);
//			
//		}
		
		if (e.getSource()==btnTic) {

			VentanaTicket r = new VentanaTicket(this.neverEmptyController);
			r.setVisible(true);
			dispose();
			
		}
		
		/*
		 * Eventos de boton de producto para aumentar la cantidad de producto que desea añadir
		 */
		if (e.getSource()==b1) {
			i++;
			p1 ="";
			p1 = String.valueOf(i);
			textArea.setText(p1);
		}
		if (e.getSource()==b2) {
			i2++;
			p2 ="";
			p2 = String.valueOf(i2);
		}
		if (e.getSource()==b3) {
			i3++;
			p3 ="";
			p3 = String.valueOf(i3);
		}
		if (e.getSource()==b4) {
			p4 ="";
			p4 = String.valueOf(i4);
			i4++;
		}
		if (e.getSource()==b5) {
			i5++;
			p5 ="";
			p5 = String.valueOf(i5);
		}
		
		/*Evento de boton para añadir producto, precio y cantidad al string que le pasaremos a la BD
		 * Ademas tambien envia el nombre del usuario 
		 * */
		if (e.getSource()==btnPanelInterno2) {
			//Solo se añaden al string aquellos productos cuya cantidad es mayor que 0
			if (i>0) {
				String listaCompra = part11 + "," + part12 + "," + p1 + ";";
			}
			if (i2>0) {
				String listaCompra = part21 + "," + part22 + "," + p2 + ";";
			}
			if (i3>0) {
				String listaCompra = part31 + "," + part32 + "," + p3 + ";";
			}
			if (i4>0) {
				String listaCompra = part41 + "," + part42 + "," + p4 + ";";
			}
			if (i5>0) {
				String listaCompra = part51 + "," + part52 + "," + p5 + ";";
			}
			String name = NeverEmptyController.username;
		}
		

	}


}
