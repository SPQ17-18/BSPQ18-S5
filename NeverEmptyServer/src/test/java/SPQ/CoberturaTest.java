package SPQ;

import static org.junit.Assert.*;



import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.internal.stubbing.answers.ThrowsException;

import SPQ.dao.UserDAO;
import SPQ.data.Product;
import SPQ.data.User;
import SPQ.remote.NeverEmptyFacade;

public class CoberturaTest {
	static Logger logger = Logger.getLogger(DAOMockTest.class.getName());

	@Test
	public void test() {
		User u = new User("Enara", "enara96etxaniz@gmail.com", "1234", "Google");
		u.setUsername("Alvaro");
		u.setEmail("arosa001@opendeusto.es");
		u.setCardNumber(1234);
		u.setRegisterMethod("Facebook");
		u.setPayPalEmail("arosa001@opendeusto.es");
		u.setPayPalPassword("1234p");
		u.getUsername();
		u.getPassword();
		u.getEmail();
		u.getCardNumber();
		u.getPayPalPassword();
		u.getPayPalEmail();
		u.getRegisterMethod();
	}





}
