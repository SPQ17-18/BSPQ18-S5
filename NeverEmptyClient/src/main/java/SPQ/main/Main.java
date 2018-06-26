/** 
 * @package SPQ.main
 * @brief The SPQ.main contains the main class. 
 */
package SPQ.main;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

import SPQ.controller.NeverEmptyController;
import SPQ.gui.VentanaInicio;
import SPQ.remote.RMIServiceLocator;
/** 
 * @class Main
 * @brief The Main class contains the main method of the application, the start point of the client execution.
 */
public class Main {

	static Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {

		if (args.length != 3) {
			logger.error("Wrong number of arguments");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			RMIServiceLocator rmi = new RMIServiceLocator();
			rmi.setService(args);
			NeverEmptyController neverEmptyController = new NeverEmptyController(rmi);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaInicio vInicio = new VentanaInicio(neverEmptyController);
						vInicio.setVisible(true);
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			});
		} catch (Exception e) {
			logger.error(e.getMessage());

		}		
	}
}
