package SPQ;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SPQ.dao.UserDAO;
import SPQ.data.User;

public class DAOUserTest {

	static Logger logger = Logger.getLogger(DAOUserTest.class.getName());

	private static UserDAO userDAO;
	private User cristian;
	private User alvaro;
	private User enara;
	private User jesus;
	
	private User prueba1;
	
	
	@BeforeClass
	public static void setUpClass() {
		userDAO = new UserDAO();
	}

	@Before
	public void setUp() throws Exception {
		logger.info("Almacenando usuarios");
		//users
		jesus = new User("jesus", "jesus@gmail.com", "1234", "");
		alvaro = new User("alvaro", "alvaro@gmail.com", "alv12", "");
		enara = new User("enara", "enara@gmail.com", "enr23", "");
		cristian = new User("cristian", "cristian@gmail.com", "12cr", "");

		//User for store
		prueba1= new User("nuevo", "nuevo@gmail.com", "nuevo", "");
		
		logger.info("almacenando "+jesus.getUsername());
		userDAO.storeUser(jesus);
		
		logger.info("almacenando "+alvaro.getUsername());		
		userDAO.storeUser(alvaro);
		

		logger.info("almacenando "+enara.getUsername());
		userDAO.storeUser(enara);
		
		logger.info("almacenando "+cristian.getUsername());
		userDAO.storeUser(cristian);
		
	}
	
	@Test
	public void test_1testStoreUsuario() throws Exception{
		logger.info("Test 1 para introducir nuevo usuario");		
		userDAO.storeUser(jesus);
		User prueba1 = userDAO.getUser(jesus);
		assertEquals("jesus",prueba1.getUsername());
		assertEquals("1234",prueba1.getPassword());
		assertEquals("jesus@gmail.com",prueba1.getEmail());
		
	}
	
	/*
	 * Login correcto
	 */
	@Test
	public void test_2loginUserTestOK() throws Exception{
		boolean resul;
		logger.info("Test 2 Logeando el usuario " + jesus.getUsername());
		resul = userDAO.loginUser(jesus.getUsername(),jesus.getPassword());
		assertTrue(resul);
	}
	

	
	@Test
	public void test_3deleteUsuarioTest() throws Exception{
		logger.info("Test 3 para hacer un update de usuario");
		logger.info("Eliminando usuario");
		userDAO.deleteUser(jesus);		
		User prueba2 = userDAO.getUser(jesus);
		logger.info(prueba2.toString());
		assertEquals(null,prueba2.getUsername());

	}
	
	@Test
	public void test_4loginUserTestFAIL() throws Exception{
		boolean resul;
		logger.info("Test 4 Logeando incorrectamente el usuario " + prueba1.getUsername());
		 resul = userDAO.loginUser(prueba1.getUsername(),prueba1.getPassword());
		assertFalse(resul);
	}

	@Test
	public void test_5getUsuarioTest() throws Exception{

		logger.info("Test 5 para obtener un usuario de la base de datos");
		User prueba2 = userDAO.getUser(alvaro);
		assertEquals(alvaro.getUsername(), prueba2.getUsername());
		
	}
	
	/*
	 * Existe usuario
	 */
	@Test
	public void test_6checkUserTestOK() throws Exception{
		boolean resul;
		logger.info("Test 6 para realizar un login correcto");
		 resul = userDAO.checkUser(cristian);
		assertTrue(resul);
	}

	/*
	 * No existe usuario
	 */
	@Test
	public void test_7checkUserTestFAIL() throws Exception{
		boolean resul;
		logger.info("Test 7 para intentar obtener usuario pero da fallo");
		resul = userDAO.checkUser(prueba1);
		assertFalse(resul);
	}
	
	@Test
	public void test_8getUsuariosTest() throws Exception{
		List<User> listUsuarios = new ArrayList<>();
		logger.info("Test 8 para comprobar que obtiene usuarios");
		listUsuarios=userDAO.getUsuarios();
		assertTrue(listUsuarios.size()>4);
	}
	
	@After
	public void deleteAll() throws Exception {
		userDAO.deleteUser(enara);
		userDAO.deleteUser(alvaro);
		userDAO.deleteUser(cristian);
		userDAO.deleteUser(jesus);

	}
	
	

}


