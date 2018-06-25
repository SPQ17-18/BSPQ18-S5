package SPQ;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;  


import junit.framework.JUnit4TestAdapter;

import org.junit.runner.RunWith;  
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;  
import org.mockito.junit.MockitoJUnitRunner;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import SPQ.dao.UserDAO;
import SPQ.data.User;


@RunWith(MockitoJUnitRunner.Silent.class) 
public class DAOMockTest {

	static Logger logger = Logger.getLogger(DAOMockTest.class.getName());

	@Mock
	UserDAO dao;
	@Mock
	User user;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DAOMockTest.class);
	}

	@Before
	public void setUp() throws Exception {		
		dao = new UserDAO();
		user = new User("cortazar", "cortazar@opendeusto.es", "cortazar", "Google");
	}

	@Test
	public void testRegisterUserCorrectly() {
		UserDAO mock = org.mockito.Mockito.mock(UserDAO.class);
		// Stubbing - return a given value when a specific method is called
		when( mock.getUser("cortazar", "1234")).thenReturn( null );
		mock.storeUser(user);

		//Use ArgumentCaptor to capture argument values for further assertions.
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );

		// Setting expectations -  the method storeUser() is called once and the argument is intercepted
		verify (mock).storeUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		logger.info("Registering mock new user: " + newUser.getUsername());

		assertEquals( "cortazar", newUser.getUsername());

	}

	@Test
	public void testRegisterUserAlreadyExists() {
		logger.info("Testing registering a user that already exists.");
		UserDAO mock = org.mockito.Mockito.mock(UserDAO.class);
		
		mock.storeUser(user);
		when( mock.getUser("cortazar", "1234") ).thenReturn(user);
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (mock).storeUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		logger.info("Checking if the users exists: " + newUser.getPassword());
		assertEquals( "cortazar", newUser.getUsername());

	}

}
