package SPQ.remote;

import java.util.logging.Logger;

import SPQ.remote.INeverEmptyFacade;

public class RMIServiceLocator {
	private INeverEmptyFacade iNeverEmptyFacade;
	

	// final static Logger logger = Logger.getLogger(RMIServiceLocator.class);

	public INeverEmptyFacade getNeverEmptyServer() {
		return this.iNeverEmptyFacade;
	}

	public RMIServiceLocator() {
	}

	public void setService(String[] args) {
		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			this.iNeverEmptyFacade = (INeverEmptyFacade) java.rmi.Naming.lookup(name);

		} catch (Exception e) {
			System.err.println("- Exception running NeverEmpty: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public INeverEmptyFacade getService() {
		return iNeverEmptyFacade;
	}
	


}
