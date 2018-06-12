package SPQ.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import SPQ.controller.NeverEmptyController;
import SPQ.data.Product;
import SPQ.gui.component.ProductLabel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

/*clase que contiene el codigo de la ventana de ticket*/

public class VentanaTicket extends JFrame{
	
	private JTable table;
	private JLabel total;
	private NeverEmptyController neverEmptyController;
	private static final long serialVersionUID = 1L;
	public VentanaTicket(NeverEmptyController neverEmptyController, List<ProductLabel> shoppingList) {
		this.neverEmptyController = neverEmptyController;
		this.setBounds(200, 300, 423, 328);
		
		/*Generar un contenedor donde se introducira todos los elementos de la ventana*/
		this.getContentPane().setLayout(null);
		
		/*Label para contener el texto ticket compra*/
		JLabel lblTicketCompra = new JLabel("Ticket compra");
		lblTicketCompra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTicketCompra.setBounds(0, 0, 94, 14);
		getContentPane().add(lblTicketCompra);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 27, 312, 182);
		getContentPane().add(scrollPane);
		
		this.table = new JTable(new DefaultTableModel());
		DefaultTableModel dmt = (DefaultTableModel) table.getModel();
		dmt.addColumn("Nombre Producto");
		dmt.addColumn("Cantidad");
		dmt.addColumn("Precio/Unidad");
		dmt.addColumn("Precio");

		//listaProducto = p.rellenarArrayProduto(lProducto);
		
		for (ProductLabel p : shoppingList) {
			double productsPrice = Double.parseDouble(p.getPrice().getText()) * Double.parseDouble(p.getQuantity().getText());
			String fila[] = {
					p.getproductName().getText(),
					p.getQuantity().getText(),
					p.getPrice().getText(),
					Double.toString(productsPrice)
					};
			dmt.addRow(fila);
		}
		this.table.setModel(dmt);
		scrollPane.setViewportView(table);
		
		JLabel lblTotal = new JLabel("TOTAL ");
		lblTotal.setBounds(12, 240, 46, 14);
		getContentPane().add(lblTotal);
		
		double d = 0;
		for(int i = 0; i < this.table.getRowCount(); i++) {
			 d = d + Double.parseDouble(this.table.getValueAt(i, 3)+""); 
		}
		
		this.total = new JLabel(Double.toString(d));
		total.setBounds(70, 240, 46, 14);
		getContentPane().add(total);

	
		
		
		
		
		JButton jbbotonVolver = new JButton("Volver");
		jbbotonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		jbbotonVolver.setBounds(12, 267, 89, 23);
		getContentPane().add(jbbotonVolver);
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}

	
	public static void main(String[] args) {
		
		VentanaTicket vTicket = new VentanaTicket(null, null);
		vTicket.setVisible(true);
	}
		
}
