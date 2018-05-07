package SPQ.remote;

import SPQ.NeverEmptyServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NeverEmptyFacade extends UnicastRemoteObject implements INeverEmptyFacade{
	private static final long serialVersionUID = 1L;
	private NeverEmptyServer neverEmptyServer;
	
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
	
	public String getUser(String username) throws RemoteException {
		return neverEmptyServer.getUser(username);
	}

}
