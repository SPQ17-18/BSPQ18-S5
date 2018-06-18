package SPQ;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;  

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.runner.RunWith;  
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;  
import org.mockito.junit.MockitoJUnitRunner;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
//import org.junit.Ignore;

import SPQ.dao.UserDAO;
import SPQ.data.Product;
import SPQ.data.User;
/**
 * @author arosa001
 * Testing of the Service Layer, mocking the DAO layer
 */

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
	//@Ignore
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
		UserDAO mock = org.mockito.Mockito.mock(UserDAO.class);
		mock.storeUser(user);
		when( mock.getUser("cortazar", "1234") ).thenReturn(user);
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (mock).storeUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		logger.info("Checking if the users exists: " + newUser.getPassword());
		assertEquals( "cortazar", newUser.getUsername());
		
	}

//	@Test(expected=RemoteException.class)
//	public void testSayMessageUserInvalid() throws RemoteException {
//		
//		when( dao.retrieveUser("cortazar") ).thenReturn( null );
//		System.out.println("Say message and invalid user, testing exception");
//		
//		m.sayMessage("cortazar", "cortazar", "testing message");
//			
//	}
	
	@Test
	public void testUpdateShoppingList() throws RemoteException {
		// Setting up the test data
		UserDAO mock = org.mockito.Mockito.mock(UserDAO.class);
		Product p = new Product("MockTest1", 0.8, 25);
		Product p1 = new Product("MockTest2", 0.9, 20);
		Product p2 = new Product("MockTest3", 1, 19);
		List<Product> pl = new ArrayList<Product>();
		
		pl.add(p);
		pl.add(p1);
		pl.add(p2);
		
		//Stubbing
		when( mock.getUser("cortazar", "1234") ).thenReturn(user);
		user.setShoppingList(pl);
		//Calling the method under test
		mock.updateShoppingList(user);
		
		// Verifying the outcome
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (mock).updateShoppingList(userCaptor.capture());
		User newUser = userCaptor.getValue();
		
		assertEquals( "MockTest1", newUser.getShoppingList().get(0).getName());
		
	}

}
