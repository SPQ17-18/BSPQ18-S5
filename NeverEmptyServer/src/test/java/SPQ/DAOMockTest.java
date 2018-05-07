package SPQ;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.rmi.RemoteException;

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


	//Messenger m;
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
	//@Ignore
	public void testRegisterUserCorrectly() {

		// Stubbing - return a given value when a specific method is called
		when( dao.retrieveUser("alberto") ).thenReturn( null );
		
		try {
			neverEmptyFacade.registerUser("alberto", "albertocontra");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//neverEmptyFacade.registerFacebook("alberto", "alberto@gmmail.com", "albertcontra");

		//Use ArgumentCaptor to capture argument values for further assertions.
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );

		// Setting expectations -  the method storeUser() is called once and the argument is intercepted
		verify (dao).storeUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		System.out.println("Registering mock new user: " + newUser.getUsername());

		assertEquals( "alberto", newUser.getUsername());

	}

	@Test
	public void testRegisterUserAlreadyExists() {
		User u = new User("jesus","jesus","jesus@gmail.com","Google");

		when( dao.retrieveUser("jesus") ).thenReturn(u);
		// When the user exist, we update the password
		try {
			neverEmptyFacade.registerUser("jesus", "1234");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (dao).updateUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		System.out.println("Changing password of mock user: " + newUser.getPassword());
		assertEquals( "1234", newUser.getPassword());

	}

	
	
	@Test(expected=RemoteException.class)
	public void testSayMessageUserInvalid() throws RemoteException {

		when( dao.retrieveUser("alberto") ).thenReturn( null );
		System.out.println("Say message and invalid user, testing exception");

		neverEmptyFacade.sayMessage("alberto", "albertcontra", "testing message");

	}

	
	
	/*
	@Test
	public void testSayMessageUserValid() throws RemoteException {
		// Setting up the test data
		User u = new User("jesus","1234","jesus@gmail.com","");
		Message mes = new Message("testing message");
		mes.setUser(u);
		u.addMessage(mes) ;

		//Stubbing
		when( dao.retrieveUser("cortazar") ).thenReturn(u);

		//Calling the method under test

		m.sayMessage("jesus", "jesus", "testing message");

		// Verifying the outcome
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (dao).updateUser(userCaptor.capture());
		User newUser = userCaptor.getValue();

		assertEquals( "jesus", newUser.getMessages().get(0).getUser().getUsername());

	}
	
	*/




}
