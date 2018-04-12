package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import SPQ.Producto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/*clase que contiene el codigo de la ventana de ticket*/

public class VentanaTicket extends JFrame {
	
	private JTable table;
	private double preciototal;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VentanaTicket() {
		setBounds(200, 300, 423, 328);
		
		/*Generar un contenedor donde se introducira todos los elementos de la ventana*/
		getContentPane().setLayout(null);
		
		/*Label para contener el texto ticket compra*/
		JLabel lblTicketCompra = new JLabel("Ticket compra");
		lblTicketCompra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTicketCompra.setBounds(0, 0, 94, 14);
		getContentPane().add(lblTicketCompra);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 47, 312, 182);
		getContentPane().add(scrollPane);
		
		table = new JTable(new DefaultTableModel());
		DefaultTableModel dmt = (DefaultTableModel) table.getModel();
		String titulosColumna [] = {"Nombre Producto","precio unitario"};
		for (int i = 0; i < titulosColumna.length; i++) {
			dmt.addColumn(titulosColumna[i]);
		}
		ArrayList<Producto>lProducto = Producto.getObtenerListaProductos();
		for (int i = 0; i < lProducto.size(); i++) {
			Producto producto = lProducto.get(i);
			String fila[] = {producto.getNombreProducto(),String.valueOf(producto.getPrecioProducto())};
			dmt.addRow(fila);
		}
		table.setModel(dmt);
		scrollPane.setViewportView(table);
		
		JLabel lblTotal = new JLabel("TOTAL ");
		lblTotal.setBounds(54, 240, 46, 14);
		getContentPane().add(lblTotal);
		
		JLabel lblprecioTotal = new JLabel("New label");
		lblprecioTotal.setBounds(320, 240, 46, 14);
		getContentPane().add(lblprecioTotal);
		
		JButton jbbotonVolver = new JButton("Volver");
		jbbotonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				vPrincipal volverVentana = new vPrincipal();
//				volverVentana.setVisible(true);
//				dispose();
			}
		});
		jbbotonVolver.setBounds(168, 255, 89, 23);
		getContentPane().add(jbbotonVolver);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(1200,800);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}




	public static void main(String[] args) {
		new VentanaTicket();
	}
}
