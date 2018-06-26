/** 
 * @package SPQ.remote
 * @brief The SPQ.remote contains the RMI service locator.
 */
package SPQ.remote;
import org.apache.log4j.Logger;
import SPQ.remote.INeverEmptyFacade;

/** 
 * @class RMIServiceLocator
 * @brief RMIServiceLocator prepares the connection to the server side by founding the NeverEmptyFacade in the rmi registry.
 */
public class RMIServiceLocator {

	static Logger logger = Logger.getLogger(RMIServiceLocator.class.getName());

	private INeverEmptyFacade iNeverEmptyFacade;

	public INeverEmptyFacade getNeverEmptyServer() {
		return this.iNeverEmptyFacade;
	}

	/**
	 * Prepares the connection to the server side by finding the NeverEmptyFacade in the rmi registry.
	 * @param args
	 */
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
