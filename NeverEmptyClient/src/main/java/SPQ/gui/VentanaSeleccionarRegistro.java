/** @package SPQ.gui
 	@brief This is the brief documentation for the java package SPQ.gui
 */

/** @class VentanaSeleccionarRegistro class.h "inc/class.h" 
* @brief This is a VentanaSeleccionarRegistro class.
* Some details about the VentanaSeleccionarRegistro class 
*/

package SPQ.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import SPQ.Utilities;
import SPQ.controller.NeverEmptyController;

public class VentanaSeleccionarRegistro extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	JLabel lTitle;
	
	JButton bFacebook;
	JButton bGoogle;
	JButton bOther;
	JButton bBack;
	
	private NeverEmptyController neverEmptyController;
	
	private VentanaInicio ventanaInicio;

	
	public VentanaSeleccionarRegistro (NeverEmptyController neverEmptyController, VentanaInicio ventanaInicio) {
		this.neverEmptyController = neverEmptyController;
		this.ventanaInicio = ventanaInicio;
		this.setTitle("Seleccionar método de registro");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(50, 50, 50));
		this.getContentPane().setLayout(null);
		
		
		this.bBack = new JButton("<html><u>&#60 atrás</u></html>");
		this.bBack.setSize(50, 20);
		this.bBack.setBackground(null);
		this.bBack.setForeground(Color.white);
		this.bBack.setMargin(new Insets(0, 0, 0, 0));
		this.bBack.setBorder(null);
		Font fBack = new Font("Arial", Font.BOLD, 12);
		this.bBack.setFont(fBack);
		this.bBack.setLocation(10,10);
		
		
		this.lTitle = new JLabel("Seleccione método de registro: ");
		Font fTitle = new Font("Arial", Font.BOLD, 14);
		this.lTitle.setFont(fTitle);
		this.lTitle.setForeground(Color.white);
		this.lTitle.setSize(250, 50);
		this.lTitle.setLocation(87, 40);
		
		Utilities util = new Utilities();
		ImageIcon iconFacebook = util.getImageFromResources("facebook.png");
		ImageIcon iconGoogle = util.getImageFromResources("google.png");
		
		this.bFacebook = new JButton("Registrarse con Facebook", iconFacebook);
		this.bFacebook.setBorder(null);
		this.bFacebook.setIconTextGap(15);
		this.bFacebook.setMargin(new Insets(0, 0, 0, 5));
		this.bFacebook.setBackground(new Color( 255, 255, 255));
		this.bFacebook.setSize(215, 32);
		this.bFacebook.setLocation(90, 100);
		this.bFacebook.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		this.bGoogle = new JButton("Registrarse con Google", iconGoogle);
		this.bGoogle.setBorder(null);
		this.bGoogle.setMargin(new Insets(0, 0, 0, 5));
		this.bGoogle.setIconTextGap(15);
		this.bGoogle.setBackground(new Color( 255, 255, 255));
		this.bGoogle.setHorizontalAlignment(SwingConstants.LEFT);
		this.bGoogle.setSize(215, 32);
		this.bGoogle.setLocation(90, 150);
		
	
		this.bOther = new JButton("<html><u>No tengo google o facebook, registrar <br />mediante NeverEmpty</u></html>");
		this.bOther.setContentAreaFilled(false);
		this.bOther.setBorder(null);
		this.bOther.setForeground(Color.WHITE);
		this.bOther.setBackground(null);
		this.bOther.setMargin(new Insets(0, 0, 0, 0));
		this.bOther.setSize(245, 32);
		this.bOther.setLocation(75, 200);
		
		this.bBack.setName("bBack");
		this.bBack.addActionListener(this);
		
		this.bGoogle.setName("bGoogle");
		this.bGoogle.addActionListener(this);
		
		this.bFacebook.setName("bFacebook");
		this.bFacebook.addActionListener(this);
		
		this.bOther.setName("bOther");
		this.bOther.addActionListener(this);
		
		this.add(bBack);
		this.add(lTitle);
		this.add(bFacebook);
		this.add(bGoogle);
		this.add(bOther);
		

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String selected = "back";
		if(e.getSource() != bBack) {
			if(e.getSource() == bOther) {
				selected = "NeverEmpty";
				
			}else if (e.getSource() == bGoogle) {
				selected = "Google";
				
			}else if (e.getSource() == bFacebook) {
				selected ="Facebook";	
				
			}
			VentanaRegistro vRegistro = new VentanaRegistro(this.neverEmptyController, selected);
			vRegistro.setVisible(true);
			this.ventanaInicio.dispose();
			
		}else {
			dispose();
		}
		
	}
}
