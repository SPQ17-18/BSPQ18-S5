package SPQ.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import SPQ.Producto;
import SPQ.controller.NeverEmptyController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/*clase que contiene el codigo de la ventana de ticket*/

public class VentanaTicket extends JFrame{
	
	private JTable table;
	private JFrame VentanaPago, ventanaTicket;
	private JButton btnPagarVisa, btnPagarPaypal;
	private double preciototal;
	private NeverEmptyController neverEmptyController;
	private static final long serialVersionUID = 1L;
	public VentanaTicket(NeverEmptyController neverEmptyController) {
		this.neverEmptyController = neverEmptyController;
		setBounds(200, 300, 423, 328);
		
		/*Generar un contenedor donde se introducira todos los elementos de la ventana*/
		getContentPane().setLayout(null);
		
		/*Label para contener el texto ticket compra*/
		JLabel lblTicketCompra = new JLabel("Ticket compra");
		lblTicketCompra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTicketCompra.setBounds(0, 0, 94, 14);
		getContentPane().add(lblTicketCompra);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 27, 312, 182);
		getContentPane().add(scrollPane);
		
		table = new JTable(new DefaultTableModel());
		DefaultTableModel dmt = (DefaultTableModel) table.getModel();
		String titulosColumna [] = {"Nombre Producto","precio unitario"};
		for (int i = 0; i < titulosColumna.length; i++) {
			dmt.addColumn(titulosColumna[i]);
		}
		ArrayList<Producto>lProducto = new ArrayList<Producto>();
		ArrayList<Producto>listaProducto = new ArrayList<Producto>();
		Producto p = new Producto();
		listaProducto = p.rellenarArrayProduto(lProducto);
		
		for (int i = 0; i < listaProducto.size(); i++) {
			Producto producto = listaProducto.get(i);
			String fila[] = {producto.getNombreProducto(),String.valueOf(producto.getPrecioProducto())};
			dmt.addRow(fila);
		}
		table.setModel(dmt);
		scrollPane.setViewportView(table);
		
		JLabel lblTotal = new JLabel("TOTAL ");
		lblTotal.setBounds(12, 240, 46, 14);
		getContentPane().add(lblTotal);
		
		JLabel lblprecioTotal = new JLabel();
		lblprecioTotal.setBounds(70, 240, 46, 14);
		getContentPane().add(lblprecioTotal);
		double t = 0;
		double pr = 0;
		if (table.getRowCount()>0) {
			for (int i = 0; i < table.getRowCount(); i++) {
				pr = Double.parseDouble((String) table.getValueAt(i, 1));
				t+=pr;
			}
			lblprecioTotal.setText(t+"�");
		}
		
		JButton jbbotonVolver = new JButton("Volver");
		jbbotonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volverAVentanaPrincipal();
			}
		});
		jbbotonVolver.setBounds(12, 267, 89, 23);
		getContentPane().add(jbbotonVolver);
		
		JButton btnPagarVisa = new JButton("Pagar con Visa");
		btnPagarVisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(PagarVisa()) {
					
					JOptionPane.showMessageDialog(null, "Compra con exito. Su pedido esta en camino!!");
					dispose();
					VentanaPrincipal vPrincipal= new VentanaPrincipal(null);
					vPrincipal.setVisible(true);

				}else {
					
					JOptionPane.showMessageDialog(null, "ERROR! No tienes suficiente dinero en la cuenta de Paypal", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnPagarVisa.setBounds(256, 267, 156, 23);
		getContentPane().add(btnPagarVisa);
		
		JButton btnPagarPaypal = new JButton("Pagar con Paypal");
		btnPagarPaypal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PagarPaypal()) {
					
					JOptionPane.showMessageDialog(null, "Compra con exito. Su pedido esta en camino!!");
					dispose();
					VentanaPrincipal vPrincipal= new VentanaPrincipal(null);
					vPrincipal.setVisible(true);
					
				}else {
					JOptionPane.showMessageDialog(null, "ERROR! No tienes suficiente dinero en la cuenta de Visa", "ERROR", JOptionPane.ERROR_MESSAGE);
					dispose();
				}
				
			}
		});
		btnPagarPaypal.setBounds(256, 242, 156, 23);
		getContentPane().add(btnPagarPaypal);
		
		JLabel lblSeleccionarPago = new JLabel("Seleccionar metodo de pago: ");
		lblSeleccionarPago.setBounds(135, 203, 190, 48);
		getContentPane().add(lblSeleccionarPago);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(1200,800);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}

	public boolean PagarPaypal() {
		//this.neverEmptyController.deleteProduct();
		//@todo
		return true;
	}
	
	public boolean PagarVisa() {
		//@todo
		return true;
	}
	
	public void volverAVentanaPrincipal() {
		VentanaPrincipal volverVentana = new VentanaPrincipal(this.neverEmptyController);
		volverVentana.setVisible(true);
		dispose();
	}
	
	
	public static void main(String[] args) {
		
		VentanaTicket vTicket = new VentanaTicket(null);
		vTicket.setVisible(true);
	}
		
}
