package SPQ;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import SPQ.data.Product;
import SPQ.data.User;

public class DATAProductTest {

	static Logger logger = Logger.getLogger(DATAProductTest.class.getName());
	
	private Product producto1;
	private Product producto2;
	private Product producto3;
	private User spq;
	
	@Before
	public void setUp() throws Exception {
		
        
        producto1 = new Product("Fresas", 3.99, 12);
        producto2= new Product("Manzana",2.12,8);
        producto3 = new Product("Hamburguesas", 4.12, 5);
        
	}
	

	@Test
	public void producto2test() {
		assertEquals("Manzana", producto2.getName());
	}
	
	@Test
	public void producto3test() {
		assertEquals("Hamburguesas", producto3.getName());
	}
	
	
	
}
