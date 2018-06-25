/** @package SPQ.main
 	@brief This is the brief documentation for the java package SPQ.main
 */

/** @class Main class.h "inc/class.h" 
* @brief This is a Main class.
* Some details about the Main class 
*/

package SPQ.main;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

import SPQ.controller.NeverEmptyController;
import SPQ.gui.VentanaInicio;
import SPQ.remote.RMIServiceLocator;

public class Main {
	
	static Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		
		System.out.println(args[0] + args[1] + args[2]);
		if (args.length != 3) {
			logger.error("- NeverEmptyCLient: Wrong number of arguments");
			System.exit(0);
		}
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
			}
		
		try {
			RMIServiceLocator rmi = new RMIServiceLocator();
			rmi.setService(args);
			NeverEmptyController neverEmptyController = new NeverEmptyController(rmi);
			//Inicializo la ventana inicio
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaInicio vInicio = new VentanaInicio(neverEmptyController);
						vInicio.setVisible(true);
					} catch (Exception e) {
						logger.error(e);
//						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}		
	}
}
