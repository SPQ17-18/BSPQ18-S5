package SPQ;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.rmi.RemoteException;
import org.apache.log4j.Logger;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;  
import org.mockito.junit.MockitoJUnitRunner; 

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import SPQ.dao.IUserDAO;
import SPQ.data.User;
import SPQ.remote.NeverEmptyFacade;
import junit.framework.JUnit4TestAdapter;

@RunWith(MockitoJUnitRunner.class)
public class DAOMockTest {

	static Logger logger = Logger.getLogger(RMITest.class.getName());
	
	NeverEmptyFacade neverEmptyFacade;

	@Mock
	IUserDAO dao;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DAOMockTest.class);
	}

	@Before
	public void setUp() throws Exception {		
		neverEmptyFacade = new NeverEmptyFacade(dao);

	}

	@Test
	public void testRegisterUserGoogleCorrectly() {

		// Stubbing - return a given value when a specific method is called
		logger.info("Registrando usuario Google correctamente");
		when( dao.retrieveUser("jesus") ).thenReturn( null );
		
		try {
			logger.info("Test 1");
			
			neverEmptyFacade.registerGoogle("jesus", "jesus@gmail.com", "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			logger.error(" NeverEmptyServer # Messenger RemoteException: " + e.getMessage());
			//e.printStackTrace();
		}
		//Use ArgumentCaptor to capture argument values for further assertions.
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );

		// Setting expectations -  the method storeUser() is called once and the argument is intercepted
		verify (dao).storeUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		logger.info("Registering mock new user: " + newUser.getUsername());

		assertEquals( "jesus", newUser.getUsername());

	}
	
	@Test
	public void testRegisterUserFacebookCorrectly() {

		// Stubbing - return a given value when a specific method is called
		logger.info("Registrando usuario Facebook correctamente");
		when( dao.retrieveUser("cristian") ).thenReturn( null );
		
		try {
			logger.info("Test 2");
			//neverEmptyFacade.registerUser("alberto", "albertocontra");
			neverEmptyFacade.registerFacebook("cristian", "cristian@gmail.com", "123");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			logger.error(" NeverEmptyServer # Messenger RemoteException: " + e.getMessage());
			//e.printStackTrace();
		}
		//Use ArgumentCaptor to capture argument values for further assertions.
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );

		// Setting expectations -  the method storeUser() is called once and the argument is intercepted
		verify (dao).storeUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		logger.info("Registering mock new user: " + newUser.getUsername());

		assertEquals( "cristian", newUser.getUsername());

	}
	
	

	@Test
	public void testRegisterUserGoogleAlreadyExists() {
		
		logger.info("TestRegisterUserGoogleAlreadyExists");
		
		User u = new User("jesus","jesus@gmail.com","1234","Google");

		when( dao.retrieveUser("jesus") ).thenReturn(u);
		// When the user exist, we update the password
		try {
			logger.info("Test 3 - Probando si el usuario ya esta registrado");
			//neverEmptyFacade.registerUser("jesus", "1234");
			neverEmptyFacade.registerGoogle("jesus", "jesus@gmail.com", "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			logger.error(" # Messenger RemoteException: " + e.getMessage());
			
		}

		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (dao).updateUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		logger.info("Changing password of mock user: " + newUser.getPassword());
		assertEquals( "1234", newUser.getPassword());

	}
	
	@Test
	public void testRegisterUserFacebookAlreadyExists() {
		
		logger.info("TestRegisterUserFacebookAlreadyExists");
		
		User u = new User("cristian","cristian@gmail.com","123","Facebook");

		when( dao.retrieveUser("cristian") ).thenReturn(u);
		// When the user exist, we update the password
		try {
			logger.info("Test 4 - Probando si el usuario ya esta registrado");
			//neverEmptyFacade.registerUser("jesus", "1234");
			neverEmptyFacade.registerGoogle("cristian", "cristian@gmail.com", "123");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			logger.error(" # Messenger RemoteException: " + e.getMessage());
			
		}

		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (dao).updateUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		logger.info("Changing password of mock user: " + newUser.getPassword());
		assertEquals( "123", newUser.getPassword());

	}

	/*
	
	@Test(expected=RemoteException.class)
	public void testSayMessageUserInvalid() throws RemoteException {

		when( dao.retrieveUser("alberto") ).thenReturn( null );
		logger.info("Say message and invalid user, testing exception");

		neverEmptyFacade.sayMessage("alberto", "albertcontra", "testing message");

	}
	
	*/

}
