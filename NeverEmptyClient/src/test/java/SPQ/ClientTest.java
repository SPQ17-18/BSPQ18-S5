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

public class ClientTest {

	static Logger logger = Logger.getLogger(ClientTest.class.getName());

	private NeverEmptyController nc;

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
	
	@Test
	public void testGetProducts() {
		logger.info("Testing getting products.");
		try {
			assertNotEquals(null, nc.getProducts());
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

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
