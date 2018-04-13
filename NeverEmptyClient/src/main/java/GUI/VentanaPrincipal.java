package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame {

	JLabel lblTitulo;
	JButton btnPanel1, btnPanel2, btnPanelInterno2;
	JTextArea textArea;
	JPanel panel1, panel2;
	JTextArea campo;
	
	Vector vProductos;

	
	public VentanaPrincipal() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setSize(400, 400);
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
		
		panel2 = new JPanel();
		panel2.setBackground(Color.RED);
		panel2.setBounds(5,112, 370, 200);
		panel2.setLayout(null);
		
		lblTitulo = new JLabel ("Bienvenido a NeverEmpty");
		lblTitulo.setBounds(10,14,221,51);
		
		btnPanel1 = new JButton("Catalogo");
		btnPanel1.setBounds(10, 76, 95, 23);
		
		
		btnPanel2 = new JButton("Lista de Compra");
		btnPanel2.setBounds(109, 76, 135, 23);
		
		cargarComponentesPanel1();
		cargarComponentesPanel2();
		
		add(panel1);
		add(lblTitulo);
		add(btnPanel1);
		add(btnPanel2);
		
		//Solucionar boton interno


	}
	
private void cargarComponentesPanel2() {
		btnPanelInterno2 = new JButton("Comprar");
		btnPanelInterno2.setBounds(200, 10, 90, 25);
		panel2.add(btnPanelInterno2);
		
		campo = new JTextArea();
		textArea.setBounds(10, 10, 180, 25);
		String campo = "Miembros:" + "\nAlvaro" + "\nEnara" + "\nCristian" + "\nJesus" + "\nRebeca";
	}

private void cargarComponentesPanel1() {
	textArea = new JTextArea();
	textArea.setBounds(10, 10, 350, 181);
	String texto = "Miembros:" + "\nAlvaro" + "\nEnara" + "\nCristian" + "\nJesus";
	textArea.setText(texto);
	panel1.add(textArea);
	}



//ESTO AUN NO SE ESTA UTILIZANDO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//vProductos = new Vector();
//
//vProductos = new Vector();
//vProductos.add("Filetes de pollo");
//vProductos.add("Dorada");
//vProductos.add("Aceite");
//vProductos.add("Lentejas");
//vProductos.add("Huevos");
//vProductos.add("Leche");
//vProductos.add("Sal");
//vProductos.add("Arroz");
//vProductos.add("Cafe");
//vProductos.add("Pan");
//vProductos.add("Lechuga");
//vProductos.add("Tomate");
//vProductos.add("Viangre");
//vProductos.add("Platanos");
//vProductos.add("Manzana");

//ESTO AUN NO SE ESTA UTILIZANDO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//bLogout = new JButton();
//bRecipes = new JButton();
//bSales = new JButton();
//bFavorites = new JButton();
//bShoppingList = new JButton();





//	Botones 
//	
//	private JButton bProfile;
//	private JButton bLogout;
//	private JButton bRecipes;
//	private JButton bSales;
//	private JButton bFavorites;
//	private JButton bShoppingList;

//	JScrollPane scroll;
//	JList lProductos;
//	JTextField producto;
	
	
//	private void configurarVentana() {
//        this.setBounds((ancho / 2) - (this.getWidth() / 2), (alto / 2) - (this.getHeight() / 2), 500, 500);                                   
//		this.setResizable(false);
//	}
//
//	

	//Aqui crearemos los dos scrolls




	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VentanaPrincipal v = new VentanaPrincipal();
		v.setVisible(true);
	}


}
