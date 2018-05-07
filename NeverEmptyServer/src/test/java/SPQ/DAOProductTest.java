package SPQ;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SPQ.dao.ProductDAO;
import SPQ.data.Product;
import SPQ.data.User;

public class DAOProductTest {

	private static ProductDAO productDAO;
	
	private Product  product1;
	private Product  product2;
	private Product  spq ;
	
	private Product cacahuetes;
	
	private Product  leche; //La cantidad de la leche se cuenta en bricks de leche
	
	private Product prueba1;
	
	private List<User> listUsuarios=new ArrayList<>();
	
	static Logger logger = Logger.getLogger(DAOProductTest.class.getName());
	
	@BeforeClass
	public static void setUpClass() {
		productDAO = new ProductDAO();
	}
	
	@Before
	public void setUp() throws Exception {
		logger.info("Almacenando productos");
		product1= new Product("Lubina", 25, 6);
		product2= new Product("Lomo", 9, 7);

		spq= new Product("prueba", 7, 7);
		//spq = new Pelicula(5, "Prueba", 0, "prueba", 2018, "SPQ", 10, listUsuarios, "10");

		// Product del test StoreProduct
		cacahuetes= new Product("Cacahuetes", 5.5, 50);

		// GetProductTest
		leche = new Product("Leche", 13, 9);

		// UpdateUsuarioTest
		prueba1 = new Product("Patatas", 5.0, 2);

		productDAO.storeProduct(product1);
		productDAO.storeProduct(product2);
		productDAO.storeProduct(spq);

	}
	
	@Test
	public void testStoreProduct() throws Exception {
		logger.info("testStoreProduct()");
	
		Product prueba2 = null;
		productDAO.storeProduct(cacahuetes);
		
		List<Product> lista = productDAO.getProducts(" ",0 ,0 );
		
		for(Product product:lista) {
			if(product.getName().equals("Cacahuetes")) {
				prueba2 =product;
			}
		}
		
		
		logger.info(prueba2.getName());
		assertEquals("Cacahuetes", prueba2.getName());
		assertEquals("5.5", String.valueOf(prueba2.getPrice()));
		assertEquals("50", String.valueOf(prueba2.getQuantity()));
	
	}
	
	@Test
	public void getProductTest() throws Exception{
		logger.info("getProductTest()");
		productDAO.storeProduct(leche);
		
		Product prueba2 = null;
		List<Product> lista = productDAO.getProducts(" ", 0, 0);
			
		for(Product product:lista) {
			if(product.getName().equals("Leche")) {
				prueba2 =product;
			}
		}
		
		 
		assertEquals(leche.getName(), prueba2.getName());
		 
	}
	
	@Test
	public void updateUsuarioTest() throws Exception{
		logger.info("updateUsuarioTest()");
		productDAO.storeProduct(prueba1);
		
		Product prueba2 =null;
		Product prueba3 =null;
		List<Product> lista = productDAO.getProducts(" ",0, 0);
		for(Product product:lista) {
			if(product.getName().equals("Patatas")) {
				prueba2 =product;
			}
		}
		prueba2.setPrice(2.7);
		prueba2.setQuantity(3); //3 kg de patatas
		productDAO.updateProduct(prueba2);
		
		List<Product> lista2 = productDAO.getProducts(" ", 0, 0);
		for(Product product:lista2) {
			if(product.getName().equals("Patatas")) {
				prueba3 =product;
			}
		}
		
		assertEquals("2.7",String.valueOf(prueba3.getPrice()));
		
	}
	
	
}
