/** @package SPQ.controller

 	@brief This is the brief documentation for the java package SPQ.controller
 */

/** @class >Utilities class.h "inc/class.h" 
* @brief This is a Utilities class.
* Some details about the Utilities class 
*/

package SPQ;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Utilities {
	public Utilities() {
		
	}
	public ImageIcon getImageFromResources(String filename) {
		try {
			Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/" + filename));
			ImageIcon icon = new ImageIcon(image);
			return icon;
		}catch (Exception e) {
			System.out.println("No se ha podido cargar la imagen " + filename + " : " + e);
			return null;
		}

	}
	

}
