package SPQ.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
import org.apache.log4j.Logger;
/*clase que contiene el codigo de la ventana de ticket*/

public class VentanaTicket extends JFrame implements ActionListener{
	
	private JTable table;
	private JLabel total;
	private JButton bPay, bBack;
	private NeverEmptyController neverEmptyController;
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(VentanaInicio.class.getName());
	
    public VentanaPrincipal vp;
	public VentanaTicket(NeverEmptyController neverEmptyController, List<ProductLabel> shoppingList, VentanaPrincipal ventanaPrincipal) {
		this.neverEmptyController = neverEmptyController;
		vp = ventanaPrincipal;
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
		
		table = new JTable(new DefaultTableModel());
		DefaultTableModel dmt = (DefaultTableModel) table.getModel();
		dmt.addColumn("Nombre Producto");
		dmt.addColumn("Cantidad");
		dmt.addColumn("Precio/Unidad");
		dmt.addColumn("Descuento");
		dmt.addColumn("Precio");
		
		double sum = 0;
		for (ProductLabel p : shoppingList) {
			
			double saleFactor = (Double.parseDouble(p.getSale().getText().replace("%", "")) * (Double.parseDouble(p.getQuantity().getText()) * Double.parseDouble(p.getPrice().getText()))) / 100;
			double productsPrice = (Double.parseDouble(p.getPrice().getText()) * Double.parseDouble(p.getQuantity().getText())) - saleFactor;
			String fila[] = {
					p.getproductName().getText(),
					p.getQuantity().getText(),
					p.getPrice().getText(),
					p.getSale().getText(),
					Double.toString(productsPrice)
					};
			dmt.addRow(fila);
			sum = sum + productsPrice;
		}
		table.setModel(dmt);
		scrollPane.setViewportView(table);
		
		JLabel lblTotal = new JLabel("TOTAL ");
		lblTotal.setBounds(12, 240, 46, 14);
		getContentPane().add(lblTotal);
		
		
		
		logger.info("Total: " + sum);
		total = new JLabel(Double.toString(sum));
		total.setBounds(70, 240, 46, 14);
		getContentPane().add(total);


		bBack = new JButton("Volver");
		bBack.addActionListener(this);
		bBack.setBounds(12, 267, 89, 23);
		getContentPane().add(bBack);
		
		bPay = new JButton("Comprar");
		bPay.setBounds(200, 260, 100, 14);
		getContentPane().add(bPay);
		bPay.addActionListener(this);

		this.setResizable(false);
		this.setVisible(true);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bPay) {
			VentanaMetodoPago vmp = new VentanaMetodoPago(neverEmptyController, Double.parseDouble(total.getText()), this);
			vmp.setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource() == bBack) {
			vp.setVisible(true);
			dispose();
		}
		
	}

	
	public static void main(String[] args) {
		
		VentanaTicket vTicket = new VentanaTicket(null, null,null);
		vTicket.setVisible(true);
	}
		
}
