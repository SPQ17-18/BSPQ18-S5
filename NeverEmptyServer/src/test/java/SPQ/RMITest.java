package SPQ;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import org.apache.log4j.Logger; 

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import SPQ.dao.UserDAO;
import SPQ.data.User;
import SPQ.dto.PaymentDTO;
import SPQ.dto.UserDTO;
import SPQ.remote.INeverEmptyFacade;
import junit.framework.JUnit4TestAdapter;

public class RMITest {

	private static String cwd = RMITest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;
	
	private INeverEmptyFacade  NeverEmptyFacade;
	
	static Logger logger = Logger.getLogger(RMITest.class.getName());

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RMITest.class);
	}
	
	@BeforeClass static public void setUp() {
		// Launch the RMI registry
		class RMIRegistryRunnable implements Runnable {

			public void run() {
				try {
					java.rmi.registry.LocateRegistry.createRegistry(1099);// de manera oculta, para que lo haga automaticamente con jenkins
					//lo hacemos de manera autonoma
					logger.info("BeforeClass: RMI registry ready.");
				} catch (Exception e) {
					logger.info("Exception starting RMI registry:");
					e.printStackTrace();
				}	
			}
		}
		
		rmiRegistryThread = new Thread(new RMIRegistryRunnable());
		rmiRegistryThread.start();
		try {
			logger.info("sleeping 4000ms");
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		class RMIServerRunnable implements Runnable {

			public void run() {
				logger.info("This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
				logger.info("**************: " + cwd);
				System.setProperty("java.rmi.server.codebase", "file:" + cwd);
				System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

				if (System.getSecurityManager() == null) {
					System.setSecurityManager(new SecurityManager());
				}

				//String name = "//127.0.0.1:1099/MessengerRMIDAO";
				String name = "//127.0.0.1:1099/NeverEmptyServerRMIDAO";
				logger.info("BeforeClass - Setting the server ready TestServer name: " + name);

				try {
					
					INeverEmptyFacade neverEmptyFacade = new NeverEmptyServer();

					Naming.rebind(name, neverEmptyFacade);
				} catch (RemoteException re) {
					logger.error(" # neverEmptyServer RemoteException: " + re.getMessage());
					re.printStackTrace();
					System.exit(-1);
				} catch (MalformedURLException murle) {
					logger.error(" # neverEmptyServer Messenger MalformedURLException: " + murle.getMessage());
					murle.printStackTrace();
					System.exit(-1);
				}
			}
		}
		rmiServerThread = new Thread(new RMIServerRunnable());
		rmiServerThread.start();
		try {
			logger.info("sleeping 4000ms");
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	
	}
	
	@Before public void setUpClient() {
		try {
		System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//127.0.0.1:1099/NeverEmptyServerRMIDAO";
		logger.info("BeforeTest - Setting the client ready for calling TestServer name: " + name);
		NeverEmptyFacade = (INeverEmptyFacade) java.rmi.Naming.lookup(name);
		}
		catch (Exception re) {
			logger.error(" # Messenger RemoteException: " + re.getMessage());
			System.exit(-1);
		} 
		
	}
	
	
	/*
	 * Test Login con un usuario no registrado
	 */	
	@Test
	@Required(totalTime = 500)
	public void loginNewUserTest() {
		logger.info("loginNewUserTest");
		boolean resul=false;
		try{
			logger.info("Test 1 - Loging new user");
			UserDTO userDTO = new UserDTO("cortazar", "1234");
			resul= NeverEmptyFacade.login(userDTO);//username y password
		}
		catch (Exception re) {
			logger.error(" # NeverEmptyServer RemoteException: " + re.getMessage());
			//System.err.println(" # Messenger RemoteException: " + re.getMessage());
		} 
		/*
		 * Very simple test, inserting a valid new user
		 */
		logger.info("Si es TRUE, se ha logeado");
		assertFalse( resul );
	}
	
	
	
	//Comprobar login con un usuario registrado 
	
	@Test
	@PerfTest(invocations = 100, threads = 10)
	@Required(max = 1000, average = 1000)
	public void loginExistingUserTest() {
		logger.info("loginExistingUserTest");
		boolean resul = false;
		try{
			logger.info("Test 2 - Login existing user.");
			UserDTO userDTO = new UserDTO("cortazar", "1234");
			resul = NeverEmptyFacade.login(userDTO);
		}
		catch (Exception re) {
			logger.error(" # NeverEmptyServer RemoteException: " + re.getMessage());
			//System.err.println(" # Messenger RemoteException: " + re.getMessage());
		} 
		
		logger.info("Si es TRUE, se ha logeado");
		assertFalse( resul);
	}
	
	
	@Test
	@Required(throughput = 20000)
	public void registerGoogleExistingUserTest() {
		logger.info("registerGoogleExistingUserTest");
		boolean resul=false;
		try{
			logger.info("Test 3 - Register existing user. Change password");
			resul = NeverEmptyFacade.registerGoogle(new UserDTO("jesus", "jesus@gmail.com", "12342de"));
	
		}
		catch (Exception re) {
			logger.error(" # NeverEmptyServer RemoteException: " + re.getMessage());
			//System.err.println(" # Messenger RemoteException: " + re.getMessage());
		} 
		
		
		
		logger.info("Si es TRUE, no se ha podido registar");
		assertFalse(resul);
	}
	
	@Test
	@Required(throughput = 30000)
	public void registerFacebookExistingUserTest() {
		logger.info("registerFacebookExistingUserTest");
		boolean resul=false;
		try{
			logger.info("Test 3 - Register existing user. Change password");
			//resul = NeverEmptyFacade.registerUser("jesus", "12342de");
			resul = NeverEmptyFacade.registerGoogle(new UserDTO("jesus", "jesus@gmail.com", "12342de"));
	
		}
		catch (Exception re) {
			logger.error(" # NeverEmptyServer RemoteException: " + re.getMessage());
			//System.err.println(" # Messenger RemoteException: " + re.getMessage());
		} 
		
		logger.info("Si es TRUE, no se ha podido registar");
		assertFalse(resul);
	}

	
	
	//Vamos a suponer que no hay ningun usuario registrado con google
	@Test 
	public void registerUserGoogleTest() {
		logger.info("registerExistingUserGoogleTest");
		boolean resul=false;
		try{
			logger.info("Test 5 - Register new user");
			resul = NeverEmptyFacade.registerGoogle(new UserDTO("cristian", "cristian123@gmail.com", "cr2344"));
		}
		catch (Exception re) {
			logger.error(" # Messenger RemoteException: " + re.getMessage());
		} 
		/*
		 * Very simple test, inserting a valid new user
		 */
		assertFalse( resul );
	}


	
	//Vamos a suponer que no hay ningun usuario registrado con facebook
	@Test 
	public void registerUserFacebookTest() {
		logger.info("registerExistingUserFacebookTest");
		boolean resul = false;
		try{
			logger.info("Test 6 - Register new user");
			resul = NeverEmptyFacade.registerFacebook(new UserDTO("alvaro", "alvaro12@gmail.com", "alv332"));
		}
		catch (Exception re) {
			logger.error(" # Messenger RemoteException: " + re.getMessage());
		} 
		/*
		 * Very simple test, inserting a valid new user
		 */
		assertFalse( resul );
	}
	
	@Test
	public void testPayWithPayPal() {
		logger.info("Testing pay with PayPal.");
		PaymentDTO pdto = new PaymentDTO(10, "enara@paypal.es", "1234");
		try {
			assertTrue(NeverEmptyFacade.payWithPaypal(pdto));
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
	}
	
	@Test
	public void testPayWithVisa() {
		logger.info("Testing pay with Visa.");
		PaymentDTO pdto = new PaymentDTO(10, 1111222233334444L, "Enara Etxaniz Ibañez", "05/19", 123);
		try {
			assertTrue(NeverEmptyFacade.payWithVisa(pdto));
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
	}
	
	@Test
	public void testGetProducts() {
		logger.info("Testing get products from Eroski");

		try {
			assertNotNull(NeverEmptyFacade.getProducts());
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
	}
	
	
	@After public  void deleteDatabase() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
	
            logger.info("Deleting test users from persistence. Cleaning up.");
            Query<User> q1 = pm.newQuery(User.class);
            long numberInstancesDeleted = q1.deletePersistentAll();
            logger.info("Deleted " + numberInstancesDeleted + " user");
			
            tx.commit();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	
	@AfterClass static public void tearDown() {
		try	{
			rmiServerThread.join();
			rmiRegistryThread.join();
			UserDAO udao = new UserDAO();
			User user = new User("Alvaro", "arosa001@gmail.com", "1234", "Google");
			udao.storeUser(user);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		

	} 
	
	
	
	
	
}
