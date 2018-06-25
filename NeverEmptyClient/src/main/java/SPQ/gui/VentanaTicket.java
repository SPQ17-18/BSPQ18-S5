/** @package SPQ.gui
 	@brief This is the brief documentation for the java package SPQ.gui
 */

/** @class VentanaTicket class.h "inc/class.h" 
* @brief This is a VentanaTicket class.
* Some details about the VentanaTicket class 
*/

package SPQ.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import SPQ.controller.NeverEmptyController;
import SPQ.gui.component.ProductLabel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import org.apache.log4j.Logger;

import SPQ.Utilities;
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
		setSize(400, 500);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255,255,230));
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(null);

		this.setTitle("Ticket");
		
		/*Label para contener el texto ticket compra*/
		JLabel lTicketCompra = new JLabel("Ticket de compra");
		lTicketCompra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lTicketCompra.setBounds(150, 10, 100, 15);
		
		getContentPane().add(lTicketCompra);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 30, 350, 400);;
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
		
		JLabel lblTotal = new JLabel("TOTAL: ");
		lblTotal.setBounds(30, 440, 46, 30);
		getContentPane().add(lblTotal);
		
		
		logger.info("Total: " + sum);
		total = new JLabel(Double.toString(Math.round(sum * 1e4) / 1e4));
		total.setBounds(80, 440, 46, 30);
		getContentPane().add(total);


		bBack = new JButton("<html><u>&#60 atrás</u></html>");
		bBack.setSize(50, 20);
		bBack.setBackground(null);
		bBack.setForeground(Color.black);
		bBack.setMargin(new Insets(0, 0, 0, 0));
		bBack.setBorder(null);
		Font fBack = new Font("Arial", Font.BOLD, 12);
		bBack.setFont(fBack);
		bBack.setLocation(10,10);
		bBack.addActionListener(this);
		getContentPane().add(bBack);
		
		
		Utilities util = new Utilities();
		ImageIcon iconPay = util.getImageFromResources("pay.png");
		
		bPay = new JButton("Continuar", iconPay);
		bPay.setBounds(260, 445, 110, 30);
		bPay.setBorder(null);
		bPay.setBackground(new Color(153, 200, 51));
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
			vp.setEnabled(true);
			dispose();
		}
		
	}

	
	public static void main(String[] args) {
		
		VentanaTicket vTicket = new VentanaTicket(null, null,null);
		vTicket.setVisible(true);
	}
		
}
