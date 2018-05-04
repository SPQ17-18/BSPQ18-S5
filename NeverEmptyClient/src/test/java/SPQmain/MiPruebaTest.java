package SPQmain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class MiPruebaTest {


	//Orden cronologico y natural de los metodos: BeforeClass, Before, Test1,Test2,Test3, After, AfterClass

	@BeforeClass //Se ejecuta una vez solo vez cuando se carga la clase. // Cuando sea comun a todod los test. 
	//Lanzar el RMIregistry porque solo se ejecuta una vez
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before //A nivel de metodo. Se ejecuta tantas veces como metodos tengamos. Unico para todos los test.
	//Te sirve para elimiar o limpiar a nivel de clase las cosas comunes
	public void setUp() throws Exception {
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@AfterClass //Se ejecuta una vez solo cuando se ejecutan todos los metodos de la clase 
	public static void tearDownAfterClass() throws Exception {
	}


}
