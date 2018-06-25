package SPQ;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import SPQ.data.Product;
import SPQ.data.User;
import SPQ.dto.ProductDTO;

public class DATAProductTest {

	static Logger logger = Logger.getLogger(DATAProductTest.class.getName());
	
	private Product manzana;
	@Before
	public void setUp() throws Exception {
		manzana = new Product("manzana", 1, 1, 10);		
	}
	
	
	@Test
	public void ProductTest() {
		logger.info("Testing product class.");
		manzana.setName("manzana2");
		manzana.setPrice(2);
		manzana.setQuantity(2);
		manzana.setSale(20);
		
		Product p = new Product("pera", 10, 10, 10);
		
		assertEquals("manzana2", manzana.getName());
		assertEquals("2.0", Double.toString(manzana.getPrice()));
		assertEquals(2, manzana.getQuantity());
		assertEquals("20.0", Double.toString(manzana.getSale()));
		assertEquals("pera", p.getName());
	}
	
	@Test
	public void ProductDTOTest () {
		ArrayList<Product> lp = new ArrayList<Product>();
		lp.add(manzana);
		ProductDTO pdto = new ProductDTO(lp);
		assertEquals("manzana", pdto.getProductList().get(0).getName());
	}
	
	
	
}
