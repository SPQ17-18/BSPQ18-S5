/** 
 * @package SPQ
 * @brief This SPQ package is the client testing root package. Contains the client tests.
 */
package SPQ;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SPQ.dto.PaymentDTO;
import SPQ.dto.UserDTO;

import SPQ.remote.RMIServiceLocator;
import SPQ.controller.*;
import org.apache.log4j.Logger;

/** 
 * @class ClientTest
 * @brief ClientTest contains the junit tests of the client side.
 */
public class ClientTest {

	static Logger logger = Logger.getLogger(ClientTest.class.getName());

	private NeverEmptyController nc;

	/**
	 * Prepares the class for testing instantiating the NeverEmptyController attribute. 
	 */
	@Before
	public void setUp(){

		String[] args = {"127.0.0.1", "1099", "NeverEmptyServerManager"};
		try {
			System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			RMIServiceLocator rmi = new RMIServiceLocator();
			rmi.setService(args);
			nc = new NeverEmptyController(rmi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Tests the exception control of the setting up from RMIServiceLocator passing wrong arguments.
	 */
	@Test
	public void testSetUpBadArguments() {
		logger.info("Testing setting up with bad arguments.");
		String[] args = {"127.0.0.1"};
		try {
			System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			RMIServiceLocator rmi = new RMIServiceLocator();
			rmi.setService(args);

		} catch (Exception e) {
			logger.info("Throws exception so it works.");
			assertTrue(true);
		}
	
	}
	/**
	 * Tests getting products from the server.
	 */
	@Test
	public void testGetProducts() {
		logger.info("Testing getting products.");
		try {
			assertNotEquals(null, nc.getProducts());
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Tests getting a user from the server.
	 */
	@Test
	public void testGetUser() {
		logger.info("Testing getting user.");
		try {
			UserDTO udto = new UserDTO("Alvaro", "1234", "arosa001@gmail.com");
			assertNotEquals(null, nc.getUser(udto));
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Tests a payment with PayPal
	 */
	@Test
	public void testPayWithPayPal () {
		logger.info("Testing paying with PayPal.");
		try {
			PaymentDTO pdto = new PaymentDTO(10, "enara@paypal.es", "1234");
			assertTrue(nc.payWithPayPal(pdto));
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Tests a payment with VISA
	 */
	@Test
	public void testPayWithVisa () {
		logger.info("Testing paying with PayPal.");
		try {
			PaymentDTO pdto = new PaymentDTO(10, 1111222233334444L, "Enara Etxaniz Ibañez", "05/19", 123);
			assertNotEquals(null, nc.payWithVisa(pdto));
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * Tests the update of an existing user.
	 */
	@Test
	public void testUpdateUser () {
		UserDTO udto = new UserDTO("updated", "arosa001@gmail.com", "updated", "Google", "updated", "updated", "updated", 1111222233334444L, "updated");
		try {
			logger.info("Testing update user.");
			nc.updateUser(udto);
			UserDTO updatedUser = nc.getUser(udto);
			assertEquals("updated", updatedUser.getUsername());
			assertEquals("updated", updatedUser.getPassword());
			assertEquals("updated", updatedUser.getCardholder());
			assertEquals(1111222233334444L, updatedUser.getCardNumber());
			assertEquals("updated", updatedUser.getPayPalEmail());
			assertEquals("updated", updatedUser.getPayPalPassword());
			assertEquals("updated", updatedUser.getAddress());
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	/**
	 * Restores a previous user to maintain the database as it was before the testing.
	 */
	@After
	public void restoreUser () {
		UserDTO udto = new UserDTO("Alvaro", "arosa001@gmail.com", "1234", "Google", null, null, null, -1, null);
		try {
			logger.info("Restoring previous user.");
			nc.updateUser(udto);
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
	}
	/**
	 * Tests a signing up with the NeverEmpty method.
	 */
	@Test
	public void testRegisterUserNeverEmpty() {
		try {
			logger.info("Testing user registration via NeverEmpty.");
			boolean response = nc.registerNeverEmpty("Enara", "enara96etxaniz@gmail.com", "1234");
			assertEquals(true, response);
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Tests a signing up with Google method.
	 */
	@Test
	public void testRegisterUserGoogle() {
		try {
			logger.info("Testing user registration via Google.");
			boolean response = nc.registerGoogle("Beñat", "beñat@gmail.com", "12345");
			assertEquals(true, response);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests a signing up with Facebook method.
	 */
	@Test
	public void testRegisterUserFacebook() {
		try {
			logger.info("Testing user registration via Facebook.");
			boolean response = nc.registerFacebook("Jorge", "jorge@outlook.com", "123456");
			assertEquals(true, response);
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Tests a signing in into the application.
	 */
	@Test
	public void testLogin() {

		boolean response = false;
		try {
			logger.info("Testing user login.");
			response = nc.login("Alvaro", "1234");
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
		assertEquals(true, response);
	}

	/**
	 * Tests a signing in with wrong user data. It is expected to fail.
	 */
	@Test
	public void testLoginFails() {
		boolean response = false;
		try {
			logger.info("Testing user login.");
			response = nc.login("Alvaro", "1111");
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
		assertEquals(false, response);
	}
}
