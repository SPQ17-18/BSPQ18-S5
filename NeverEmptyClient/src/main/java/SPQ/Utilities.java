/** 
 * @package SPQ
 * @brief Client root package.
 */
package SPQ;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
/** 
 * @class Utilities
 * @brief Utilities class.
 * The Utilities class gathers reusable utility methods in different points of the project.
 */
public class Utilities {
	/**
	 * Gets an image from /resource/images/ with the especificated name.
	 * @param filename . Image name and extension.
	 * @return ImageIcon. Returns an image from /resources/images/ as a ImageIcon or null if it can not be found.
	 */
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
