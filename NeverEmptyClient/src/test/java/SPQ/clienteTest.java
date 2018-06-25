package SPQ;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

//import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import SPQ.data.Product;
import SPQ.data.User;
import SPQ.dto.UserDTO;
import SPQ.gui.VentanaInicio;
import SPQ.remote.RMIServiceLocator;
import SPQ.controller.*;

import org.junit.Test;

public class clienteTest {

	//static Logger logger = Logger.getLogger(DATAUserTest.class.getName());
	
	private RMIServiceLocator rmi;
	private User alvaro;
	private User enara;
	private User rebeca;
	
	@Before 
	public void setUp(){

		alvaro = new User("alvaro", "alvaro@gmail.com", "alv12", "Facebook");
		alvaro.setEmail("alvaro@opendeusto.es");
		
		enara = new User("enara", "enara@gmail.com", "enr23", "Facebook");
		
		rebeca = new User("rebeca", "rebeca@gmail.com", "12rc", "Google");
		rebeca.setPayPalEmail("rebecaPaypal@paypal.com");
		
		Product calabacin = new Product ("calabacin", 0.58, 1, 10);
		
		VentanaInicio vi = new VentanaInicio(null);
	}
	
	@Test
	public void testController() {
		RMIServiceLocator rmi = new RMIServiceLocator();
		NeverEmptyController nc = new NeverEmptyController(rmi);
		try {
			assertEquals(true, nc.login("Enara", "1234"));;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
//	
//	
//	@Ignore
//	@Test
//	public void testlogin() {
//		//logger.info("Comprobando el usuario de Alvaro");
//		//login("alvaro", "alv12");
//		assertEquals("alvaro", alvaro.getUsername());
//		assertEquals("alvaro@opendeusto.es", alvaro.getEmail());
//		assertEquals("alv12", alvaro.getPassword());
//	}
//	
//	@Ignore
//	@Test
//	public void testgetProducts() {
//		//assertTrue(true);
//	}
//	
//	@Test
//	public void testcomprobacionSignup() {
//		//assertTrue(true);
//	}
//	
//	@Test
//	public void testfinalizarRegistro() {
//		
//	}
//	
//	@Test
//	public void testresgisterGoogle() {
//		
//	}
//	
//	@Test
//	public void testregisterFacebook() {
//		
//	}
//	
//	@Test
//	public void testresgisterNeverEmpty() {
////		try {
////			assertEquals("alvaro",rmi.getNeverEmptyServer().registerNeverEmpty(new UserDTO("alvaro", "aro34", "arosa001")));
////		} catch (RemoteException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//	}
//	
//	@Test
//	public void testgetProduct() {
//		
//	}
//	
//	@Test
//	public void testgetUser() {
//		
//	}
//	
//	@Test
//	public void testiniciarAplicacion() {
//		
//	}
//	
}
