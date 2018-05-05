package SPQ.client;

import SPQ.data.Message;
import SPQ.data.User;
import SPQ.remote.IMessenger;

public class Client {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			IMessenger objHello = (IMessenger) java.rmi.Naming.lookup(name);
			// Register to be allowed to send messages
			System.out.println("Register a user for the first time: jesus");
			objHello.registerUser("jesus", "jesus");
			System.out.println("Change the password as the user is already registered: cortazar");
			objHello.registerUser("jesus", "1234");
			System.out.println("* Message coming from the server: '" + objHello.sayMessage("jesus", "1234", "This is test 1!") + "'");
			System.out.println("* Message coming from the server: '" + objHello.sayMessage("jesus", "1234", "This is test 2!") + "'");
			User u = objHello.getUserMessages("jesus");
			for (Message m: u.getMessages()) {
				
				System.out.println(m);
				
			}
			
		} catch (Exception e) {
			System.err.println("RMI Example exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
