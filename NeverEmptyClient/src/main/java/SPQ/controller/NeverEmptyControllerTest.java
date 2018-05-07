/*
package SPQ.controller;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Logger;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import SPQ.remote.INeverEmptyFacade;
import SPQ.remote.RMIServiceLocator;
import junit.framework.JUnit4TestAdapter;

public class NeverEmptyControllerTest {
	
	private static final Logger logger = Logger.getLogger(NeverEmptyControllerTest.class);

	private static String cwd = NeverEmptyController.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;
	
	private NeverEmptyController controller;
	private INeverEmptyFacade facade;
	private static IUserDAO userDAO;

	

	//This line is for Contiperf tests
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(NeverEmptyControllerTest.class);
	}
	
	@BeforeClass
	public static void setUpClass() {
		// Launch the RMI registry
		class RMIRegistryRunnable implements Runnable {
			public void run() {
				try {
					java.rmi.registry.LocateRegistry.createRegistry(1099);
					logger.info("BeforeClass: RMI registry ready.");
				} catch (Exception e) {
					//logger.error("Exception starting RMI registry:");
					System.err.println(" # Messenger RemoteException: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}

		rmiRegistryThread = new Thread(new RMIRegistryRunnable());
		rmiRegistryThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		// Launch the server
		class RMIServerRunnable implements Runnable {
			public void run() {
				System.setProperty("java.rmi.server.codebase", "file:" + cwd);
				System.setProperty("java.security.policy", "target//test-classes//security//java.policy");

				if (System.getSecurityManager() == null) {
					System.setSecurityManager(new SecurityManager());
				}

				String name = "//127.0.0.1:1099/CinemaManager";
				logger.info("BeforeClass - Setting the server ready name: " + name);

				try {
					//IRemoteFacade server = new Server();
					INeverEmptyFacade server = new Server();
					Naming.rebind(name, server);
				} catch (RemoteException re) {
					//logger.error(" # Server RemoteException: " + re.getMessage());
					System.err.println(" # Messenger RemoteException: " + re.getMessage());
					re.printStackTrace();
					System.exit(-1);
				} catch (MalformedURLException murle) {
					//logger.error(" # Server MalformedURLException: " + murle.getMessage());
					System.err.println(" # Messenger RemoteException: " + murle.getMessage());
					murle.printStackTrace();
					System.exit(-1);
				}
			}
		}
		
		rmiServerThread = new Thread(new RMIServerRunnable());
		rmiServerThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	@Before
	public void setUpClient() {
		// Launch the client
		try {
			System.setProperty("java.security.policy", "target//test-classes//security//java.policy");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			String args[] = new String[3];
			args[0] = "127.0.0.1";
			args[1] = "1099";
			args[2] = "NeverEmpty";
			logger.info("BeforeTest - Setting the client ready for calling name: " + args[2]);
			controller = new NeverEmptyController(args);
		} catch (Exception re) {
			//logger.error(" # Client RemoteException: " + re.getMessage());
			System.err.println(" # Messenger RemoteException: " + re.getMessage());
			System.exit(-1);
		}
	}
	
	@Test
	@Required(throughput = 20)
	public void testInsertProduct() {
		
		logger.info("Test Insert a product - Inserting a product to the DB - Invalid");
		ProductDTO productDTO = new ProductDTO("Palomitas", 3, 2 );
		
		assertEquals(false, controller.insertProduct(productDTO));
	}
	
	@Test
	@Required(percentile90 = 3000)
	public void testInsertProduct2() {
		logger.info("Test Insert a product - Inserting a film to the DB with set methods- Valid");
		ProductDTO productDTO = new ProductDTO();
		ProductDTO.setCountry("Naranjas");
		ProductDTO.setPrecioProduct(10.23);
		ProductDTO.setQuantity(8);
		assertEquals(true, controller.insertProduct(productDTO));
	}
	
	@Test
	@Required(totalTime = 5000)
	public void testRegisterUser() {
	
		logger.info("Test Register a user - Inserting an user to the DB - Valid");
		UserDTO userDTO = new UserDTO("jesus", "jesus@gmail.com", "1234");
		assertEquals(true, controller.registerUser(userDTO));
	}
	

	@Test
	@Required(max = 120, average = 30)
	public void testIdentifyMember() {
		logger.info("Test Identify an user - Logging as an user - Valid");
		assertEquals(false, controller.identifyUser("error1", "wrongpassword"));
		assertEquals(false, controller.identifyUser("error2", "unai"));
		assertEquals(false, controller.identifyUser("error3", "wrongpassword"));
		assertEquals(true, controller.identifyUser("jesus", "1234"));
	}
	

	@Test
	@Required(max = 120, average = 30)
	public void testUpdateProduct() {
		logger.info("Test Update a product - Updating a product from the DB - Valid");
		List<ProductDTO> products = null;
		ProductDTO productDTO = new ProductDTO("Manzanas", 8, 7);
		controller.updateProduct(productDTO);
		products = controller.getAllProducts();
		for (int i=0;i<products.size();i++) {
			if (products.get(i).getNombreProduct.equals("Manzanas")) {
				assertEquals("Manzanas", products.get(i).getName());
			}
		}
	}
	
	//Test para borrar un usuario
	@Test
	@Required(max = 120)
	public void testDeleteUser() {
		logger.info("Test Delete an user - Deleting an user from the DB - Valid");
		List<UserDTO> users = null;
		UserDTO userDTO = new UserDTO();
		UserDTO.setUsername("jesus");
		UserDTO.setEmail("jesus@gmail.com");
		UserDTO.setPassword("123");
		controller.deleteUser(userDTO);
		users = controller.getAllUsers();
		assertEquals(3, users.size()); //Si hay 4 datos en la base de datos y se elimina uno, habria 3
	}
	

	
	
	
	
	

}
*/
