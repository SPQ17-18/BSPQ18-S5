package SPQ;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import SPQ.data.User;

public class DATAUserTest {

	static Logger logger = Logger.getLogger(DATAUserTest.class.getName());
	
	private User jesus;
	private User alvaro;
	private User enara;
	private User cristian;
	private User ekaitz;
	
	@Before
	public void setUp() throws Exception {
		jesus = new User("jesus", "jesus@gmail.com", "1234", "Google");
		jesus.setCardNumber(1245);
		
		alvaro = new User("alvaro", "alvaro@gmail.com", "alv12", "Facebook");
		alvaro.setEmail("alvaro@opendeusto.es");
		
		enara = new User("enara", "enara@gmail.com", "enr23", "Facebook");
		
		cristian = new User("cristian", "cristian@gmail.com", "12cr", "Google");
		cristian.setPayPalEmail("cristianPaypal@paypal.com");

		ekaitz= new User("ekaitz", "ekaitz@gmail.com", "we3", "Google");
				
	}
	
	@Test
	public void Jesustest() {
		assertEquals("jesus", jesus.getUsername());
		assertEquals("jesus@gmail.com", jesus.getEmail());
		assertEquals("1234", jesus.getPassword());
		assertEquals("1245",jesus.getCardNumber());
		
	}
	
	@Test
	public void Alvarotest() {
		assertEquals("alvaro", alvaro.getUsername());
		assertEquals("alvaro@gmail.com", alvaro.getEmail());
		assertEquals("alv12", alvaro.getPassword());	
	}
	
	@Test
	public void Enaratest() {
		assertEquals("enara", enara.getUsername());
		assertEquals("enara@gmail.com", enara.getEmail());
		assertEquals("enr23", enara.getPassword());	
	}
	
	@Test
	public void Cristiantest() {
		assertEquals("cristian", cristian.getUsername());
		assertEquals("cristian@gmail.com", cristian.getEmail());
		assertEquals("12cr", cristian.getPassword());
		assertEquals("cristianPaypal@paypal.com",cristian.getPayPalEmail());
	}
	
	@Test
	public void Ekaitztest() {
		assertEquals("ekaitz", ekaitz.getUsername());
		assertEquals("ekaitz@gmail.com", ekaitz.getEmail());
		assertEquals("we3", ekaitz.getPassword());
		
	}
	
	
	
	
	
	
	
	
	
}
