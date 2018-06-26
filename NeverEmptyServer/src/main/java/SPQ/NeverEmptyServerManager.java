package SPQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import SPQ.remote.INeverEmptyFacade;

/**
 * @class NeverEmptyServerManager
 * @brief This class contains the main method to execute the server side.
 */
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
					logger.error("Exception starting RMI registry:" + e.getStackTrace());

				}	
			}
		}
		
		rmiRegistryThread = new Thread(new RMIRegistryRunnable());
		rmiRegistryThread.start();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException ie) {
			logger.error("Exception starting RMI registry:" + ie.getStackTrace());
	
		}
		
		class RMIServerRunnable implements Runnable {

		
			public void run() {
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
					System.exit(-1);
				} catch (MalformedURLException murle) {
					logger.error(" # Collector MalformedURLException: " + murle.getMessage());
					System.exit(-1);
				} catch (IOException e) {
					logger.error("Exception starting RMI registry:" + e.getMessage());
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
