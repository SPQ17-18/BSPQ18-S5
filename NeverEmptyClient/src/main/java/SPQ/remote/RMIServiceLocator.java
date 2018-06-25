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
		System.out.println(args[0] + " " + args[1] + " " + args[2]);
		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			this.iNeverEmptyFacade = (INeverEmptyFacade) java.rmi.Naming.lookup(name);

		} catch (Exception e) {
			logger.error("- Exception running NeverEmpty: " + e.getMessage());
		}
}
}
