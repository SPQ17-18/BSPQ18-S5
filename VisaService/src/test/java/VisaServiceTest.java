import org.apache.log4j.Logger;
import org.junit.BeforeClass;

import SPQ.dao.UserDAO;
import SPQ.data.User;

public class VisaServiceTest {
	
	static Logger logger = Logger.getLogger(VisaServiceTest.class.getName());

	private static UserDAO userDAO;
	private User cristianVisa;
	private User alvaroVisa;
	private User enaraVisa;
	private User jesusVisa;
	
	@BeforeClass
	public static void setUpClass() {
		userDAO = new UserDAO();
	}
	
	public void setUp() throws Exception{
		logger.info("Almacenando usuarios ");
		
		//Usuarios
		cristianVisa = new User("Cristian Visa", 345321, 754, 100);
		alvaroVisa = new User("Alvaro Visa", 761231, 744, 246);
		enaraVisa = new User("Enara Visa", 56322, 123, 200);
		jesusVisa = new User("Jesus Visa", 234522, 021, 500);
		
	}
	
	
}
