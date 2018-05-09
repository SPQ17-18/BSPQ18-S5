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

import org.junit.Before;
import org.junit.Test;
//import org.junit.Ignore;

import SPQ.dao.UserDAO;
import SPQ.data.Product;
import SPQ.data.User;
/**
 * @author cortazar
 * Testing of the Service Layer, mocking the DAO layer
 */
@RunWith(MockitoJUnitRunner.class)  
public class DAOMockTest {

	
	@Mock
	UserDAO dao;
		
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DAOMockTest.class);
	}

	@Before
	public void setUp() throws Exception {		
		dao = new UserDAO();

	}

	@Test
	//@Ignore
	public void testRegisterUserCorrectly() {
	
		// Stubbing - return a given value when a specific method is called
		when( dao.getUser("cortazar") ).thenReturn( null );
		User user = new User("cortazar", "cortazar@opendeusto.es", "cortazar", "Google");
		dao.storeUser(user);
		
		//Use ArgumentCaptor to capture argument values for further assertions.
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		
		// Setting expectations -  the method storeUser() is called once and the argument is intercepted
		verify (dao).storeUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		System.out.println("Registering mock new user: " + newUser.getUsername());
	
		assertEquals( "cortazar", newUser.getUsername());
		
	}
	
	@Test
	public void testRegisterUserAlreadyExists() {
		User user = new User("cortazar", "cortazar@opendeusto.es", "cortazar", "Google");
		dao.storeUser(user);
		when( dao.getUser("cortazar") ).thenReturn(user);
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (dao).getUser(userCaptor.capture().getUsername());
		User newUser = userCaptor.getValue();
		System.out.println("Checking if the users exists: " + newUser.getPassword());
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
		User u = new User("cortazar", "cortazar@opendeusto.es", "cortazar", "Google");
		UserDAO dao = new UserDAO();
		Product p = new Product("MockTest1", 0.8, 25);
		Product p1 = new Product("MockTest2", 0.9, 20);
		Product p2 = new Product("MockTest3", 1, 19);
		List<Product> pl = new ArrayList<Product>();
		
		pl.add(p);
		pl.add(p1);
		pl.add(p2);
		
		//Stubbing
		when( dao.getUser("cortazar") ).thenReturn(u);
		
		//Calling the method under test
		dao.updateShoppingList(u);
		
		// Verifying the outcome
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (dao).getUser(userCaptor.capture().getUsername());
		User newUser = userCaptor.getValue();
		
		assertEquals( "MockTest1", newUser.getShoppingList().get(0).getName());
		
	}

}
