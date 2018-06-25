/** @package SPQ.gui
 	@brief This is the brief documentation for the java package SPQ.gui
 */

/** @class VentanaFavoritos class.h "inc/class.h" 
* @brief This is a VentanaFavoritos class.
* Some details about the VentanaFavoritos class 
*/
package SPQ.gui;

import javax.swing.JFrame;

public class VentanaFavoritos extends JFrame{

	public VentanaFavoritos() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setSize(400, 375);
		this.setTitle("Bienvenido a NeverEmpty!"); 
		this.setResizable(false);
		setLayout(null);
		
		this.setLocationRelativeTo(null);
		inicializarComponentes();   // inicializamos los atributos o componentes                   
}
	
	
	private void inicializarComponentes() {
		// TODO Auto-generated method stub
		
	}
















	public static void main(String[] args) {
		

	}

}
