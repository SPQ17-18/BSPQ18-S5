package SPQ.remote;

import SPQ.NeverEmptyServer;
import SPQ.dao.IUserDAO;
import SPQ.dao.UserDAO;
import SPQ.data.Message;
import SPQ.data.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NeverEmptyFacade extends UnicastRemoteObject implements INeverEmptyFacade{

	private NeverEmptyServer neverEmptyServer;
	
	private static final long serialVersionUID = 1L;
	private int cont =0;
	IUserDAO dao;
	
	
	
	public NeverEmptyFacade() throws RemoteException {
		super();
		dao = new UserDAO();
	
	}
	public NeverEmptyFacade(IUserDAO udao) throws RemoteException {
		super();
		dao = udao;
	
	}
	
	public NeverEmptyFacade(NeverEmptyServer neverEmptyServer) throws RemoteException {
		super();
		this.neverEmptyServer = neverEmptyServer;
	}
	
	public boolean registerGoogle(String username, String email, String password) throws RemoteException {
		return neverEmptyServer.registerGoogle(username, email, password);
	}
	
	public boolean registerFacebook(String username, String email, String password) throws RemoteException {
		return neverEmptyServer.registerFacebook(username, email, password);
	}

	public boolean login(String username, String password) throws RemoteException {
		return neverEmptyServer.login(username, password);
	}
	
	public String getProducts() throws RemoteException {
		return neverEmptyServer.getProducts();
	}
	//como no devuelve nada no pongo un return no??
	public boolean modifyEmail (String user,String password, String maila, String mailb)throws RemoteException {
		return neverEmptyServer.modifyEmail(user, password, maila, mailb);
	}

	@Override
	public boolean updateShoppingList(String username, String productList) throws RemoteException {
		return neverEmptyServer.updateShoppingList(username, productList);
	}

	@Override
	public boolean updateUserPayPalEmail(String username, String payPalEmail) throws RemoteException {
		return neverEmptyServer.updateUserPayPalEmail(username, payPalEmail);
	}

	@Override
	public boolean updateUserPayPalPassword(String username, String payPalPassword) throws RemoteException {
		return neverEmptyServer.updateUserPayPalPassword(username, payPalPassword);
	}

	@Override
	public boolean updateUserCardNumber(String username, String cardNumber) throws RemoteException {
		return neverEmptyServer.updateUserCardNumber(username, cardNumber);
	}

	@Override
	public boolean registerUser(String username, String password) throws RemoteException {
		return neverEmptyServer.registerUser(username, password);
	}
	
	public String sayMessage(String login, String password, String message) throws RemoteException {

		System.out.println("Retrieving the user: '" + login +"'");
		User user = null;
		try {
			user = dao.retrieveUser(login);
		} catch (Exception  e) {
			System.out.println("Exception launched: " + e.getMessage());
		}
		
		System.out.println("User retrieved: " + user);
		if (user != null)  {
			Message message1 = new Message(message);
			message1.setUser(user);
			user.getMessages().add(message1);
			dao.updateUser(user);	
			cont++;
			System.out.println(" * Client number: " + cont);
			return message;
		}
		else {
			System.out.println("Login details supplied for message delivery are not correct");
			throw new RemoteException("Login details supplied for message delivery are not correct");
		} 
	}
	
	
	/*
	@Override
	public User getUserProducts(String userName) throws RemoteException {
		
		System.out.println("Checking whether the user already exits or not: '" + userName +"'");
		UserDAO userDAO = new UserDAO();
		User user = null;
		try {
			user = userDAO.retrieveUser(userName);
		} catch (Exception  e) {
			System.out.println("Exception launched: " + e.getMessage());
		}
		
		if (user != null) {
			System.out.println("Returning the User and its products to the RMI Client: " + userName);
			return user;
		} else {
			System.out.println("The user does not exist, no possibility of retrieving products ...: " + userName);
			throw new RemoteException("Login details supplied for product retrieval are not correct");
		}
	}
	
	*/


}
