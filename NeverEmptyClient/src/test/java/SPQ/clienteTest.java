package SPQ;

import static org.junit.Assert.*;

//import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SPQ.data.User;

import org.junit.Test;

public class clienteTest {

	//static Logger logger = Logger.getLogger(DATAUserTest.class.getName());
	
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
	}
	
	@Test
	public void testlogin() {
		//logger.info("Comprobando el usuario de Alvaro");
		assertEquals("alvaro", alvaro.getUsername());
		assertEquals("alvaro@opendeusto.es", alvaro.getEmail());
		assertEquals("alv12", alvaro.getPassword());
	}
	
	
	@Test
	public void testgetProducts() {
		
	}
	
	@Test
	public void testcomprobacionSignup() {
		
	}
	
	@Test
	public void testfinalizarRegistro() {
		
	}
	
	@Test
	public void testresgisterGoogle() {
		
	}
	
	@Test
	public void testregisterFacebook() {
		
	}
	
	@Test
	public void testresgisterNeverEmpty() {
		
	}
	
	@Test
	public void testgetProduct() {
		
	}
	
	@Test
	public void testgetUser() {
		
	}
	
	@Test
	public void testiniciarAplicacion() {
		
	}
	
}
