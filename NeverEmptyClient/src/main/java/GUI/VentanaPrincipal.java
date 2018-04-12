package GUI;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame {
	private JTextField txtEnhorabuenaHasEntrado;
	public VentanaPrincipal() {
		setResizable(false);
		getContentPane().setLayout(null);
		
		txtEnhorabuenaHasEntrado = new JTextField();
		txtEnhorabuenaHasEntrado.setText("ENHORABUENA!! HAS ENTRADO A NEVEREMPTY");
		txtEnhorabuenaHasEntrado.setBounds(74, 111, 294, 22);
		getContentPane().add(txtEnhorabuenaHasEntrado);
		txtEnhorabuenaHasEntrado.setColumns(10);
	}
}
