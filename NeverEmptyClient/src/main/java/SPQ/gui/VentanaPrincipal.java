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

public class VentanaPrincipal extends JFrame implements ActionListener{

	JLabel lblTitulo;
	JButton btnPanel1, btnPanel2, btnPanelInterno2,btnSales, btnFavorites;
	//JButton btnRecipes;
	JButton btnTic;
	JTextArea campo;
	
	JPanel panel1, panel2;
	String texto;
	
	NeverEmptyController neverEmptyController;
	
	//Labels de los productos de la BD
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
	JLabel textArea, textArea2, textArea3, textArea4, textArea5, textArea6, textArea7, textArea8, textArea9;
	

	
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

		b1 = new JButton("Leche");
		b1.setBounds(10, 10, 90, 25);
		panel1.add(b1);
		
		textArea = new JLabel("Cantidad");
		textArea.setBounds(105, 10, 90, 25);
		panel1.add(textArea);
		
		b2 = new JButton("Pan");
		b2.setBounds(10, 40, 90, 25);
		panel1.add(b2);
		
		textArea2 = new JLabel("Cantidad");
		textArea2.setBounds(105, 40, 90, 25);
		panel1.add(textArea2);
		
		b3 = new JButton("Huevos");
		b3.setBounds(10, 70, 90, 25);
		panel1.add(b3);
		
		textArea3 = new JLabel("Cantidad");
		textArea3.setBounds(105, 70, 90, 25);
		panel1.add(textArea3);
		
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

	}


}
