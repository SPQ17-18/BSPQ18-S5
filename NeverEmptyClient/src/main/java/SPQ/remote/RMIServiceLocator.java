/** @package SPQ.remote
 	@brief This is the brief documentation for the java package SPQ.remote
 */

/** @class RMIServiceLocator class.h "inc/class.h" 
* @brief This is a RMIServiceLocator class.
* Some details about the RMIServiceLocator class 
*/

package SPQ.remote;
import org.apache.log4j.Logger;

//import SPQ.NeverEmptyServer;
import SPQ.remote.INeverEmptyFacade;
public class RMIServiceLocator {
	
	static Logger logger = Logger.getLogger(RMIServiceLocator.class.getName());
	
	private INeverEmptyFacade iNeverEmptyFacade;


	public INeverEmptyFacade getNeverEmptyServer() {
		return this.iNeverEmptyFacade;
	}

	public void setService(String[] args) {
		logger.info(args[0] + " " + args[1] + " " + args[2]);
		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			this.iNeverEmptyFacade = (INeverEmptyFacade) java.rmi.Naming.lookup(name);

		} catch (Exception e) {
			logger.error("setService throwed an exception: " + e.getMessage());
		}
}
}
