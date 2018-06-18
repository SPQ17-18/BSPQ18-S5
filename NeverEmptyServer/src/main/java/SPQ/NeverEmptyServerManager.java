package SPQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import SPQ.remote.INeverEmptyFacade;

public class NeverEmptyServerManager  {
	
	static Logger logger = Logger.getLogger(NeverEmptyServerManager.class.getName());
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.exit(0);
		}
		
		Thread rmiRegistryThread = null;
		Thread rmiServerThread = null;

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		final String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		class RMIRegistryRunnable implements Runnable {

			public void run() {
				try {
					java.rmi.registry.LocateRegistry.createRegistry(1099);

					logger.info("RMI registry ready.");
				} catch (Exception e) {
					logger.error("Exception starting RMI registry:"+e);
					//e.printStackTrace();
				}	
			}
		}
		
		rmiRegistryThread = new Thread(new RMIRegistryRunnable());
		rmiRegistryThread.start();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException ie) {
			logger.error("Exception starting RMI registry:"+ie);
			//ie.printStackTrace();
		}
		
		class RMIServerRunnable implements Runnable {

		
			public void run() {
				logger.info("This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
				System.setProperty("java.security.policy", "target\\classes\\security\\java.policy");


				try {
					INeverEmptyFacade neverEmptyServer = new NeverEmptyServer();
					Naming.rebind(name, neverEmptyServer);
					logger.info("Server '" + name + "' active and waiting...");
					InputStreamReader inputStreamReader = new InputStreamReader(System.in);
					BufferedReader stdin = new BufferedReader(inputStreamReader);
			        String line = stdin.readLine();
			       
			       
				} catch (RemoteException re) {
					logger.error(" # Collector RemoteException: " + re.getMessage());
					//re.printStackTrace();
					System.exit(-1);
				} catch (MalformedURLException murle) {
					logger.error(" # Collector MalformedURLException: " + murle.getMessage());
					//murle.printStackTrace();
					System.exit(-1);
				} catch (IOException e) {
					logger.error("Exception starting RMI registry:"+e);
					//e.printStackTrace();
				}

			}
		}
		rmiServerThread = new Thread(new RMIServerRunnable());
		rmiServerThread.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

	}

}
