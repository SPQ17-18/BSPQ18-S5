package test;

import org.junit.Before;

import SPQ.EroskiServer;
import junit.framework.JUnit4TestAdapter;

public class EroskiServiceTest {
	private EroskiServer prueba1;
	private EroskiServer prueba2;
	
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(EroskiServiceTest.class);
		
	}
	
	@Before
	private void setUp() {

	}
	
	
	
}
