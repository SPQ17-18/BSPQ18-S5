package SPQTest;

import SPQ.Producto;
import junit.framework.JUnit4TestAdapter;

public class ProductTest {

	private Producto p1;
	private Producto p2;
	private Producto p3;
	private Producto p4;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ProductTest.class);
	}
	
	@Before public void setUp() { 
		//Preparacion test
		p1= new Producto("Zanahoria", 3.99);
		p2= new Producto("Platano",1);
		p3= new Producto("Manzana",1.33);
		p4= new Producto("Cacahuetes",1.22);

		//fMB1= MoneyBag.create(f12CHF, f7USD);
		//fMB2= MoneyBag.create(f14CHF, f21USD);
	}
}
