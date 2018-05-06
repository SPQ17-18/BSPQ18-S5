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
	public boolean registerUser(String username, String password) {
			
			System.out.println("Checking whether the user already exits or not: '" + username +"'");
			UserDAO userDAO = new UserDAO();
			User user = null;
			boolean registrar=false;
			try {
				user = userDAO.retrieveUser(username);
			} catch (Exception  e) {
				System.out.println("Exception launched: " + e.getMessage());
			}
			
			if (user != null) {
				System.out.println("The user exists. So, setting new password for User: " + username);
				user.setPassword(password);
				System.out.println("Password set for User: " + username);
				userDAO.updateUser(user);
				registrar=true;
			} else {
				String mail= "";
				String registerMethod= "";
				System.out.println("Creating user: " + username);
				user = new User(username, password, mail, registerMethod );
				userDAO.storeUser(user);				 
				System.out.println("User created: " + username);
				registrar=true;
			}
			
			return registrar;
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
