package SPQ.main;

import SPQ.controller.NeverEmptyController;
import SPQ.remote.RMIServiceLocator;

public class Main {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("- NeverEmptyCLient: Wrong number of arguments");
			System.exit(0);
		}
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
			}
		
		try {
			RMIServiceLocator rmi = new RMIServiceLocator();
			rmi.setService(args);
			NeverEmptyController neverEmptyController = new NeverEmptyController(rmi);
			//TODO iniciar ventana
			System.out.println("EL SERVER DICE: " + neverEmptyController.register("enara", "enara.etxaniz@opendeusto.es", "123"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
