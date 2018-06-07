package SPQ.remote;

import SPQ.NeverEmptyServer;
import SPQ.dto.ProductDTO;
import SPQ.dto.UserDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NeverEmptyFacade extends UnicastRemoteObject implements INeverEmptyFacade{
	private static final long serialVersionUID = 1L;
	private NeverEmptyServer neverEmptyServer;
	
	public NeverEmptyFacade(NeverEmptyServer neverEmptyServer) throws RemoteException {
		super();
		this.neverEmptyServer = neverEmptyServer;
	}
	
	public boolean registerGoogle(UserDTO userDTO) throws RemoteException {
		return neverEmptyServer.registerGoogle(userDTO);
	}
	
	public boolean registerFacebook(UserDTO userDTO) throws RemoteException {
		return neverEmptyServer.registerFacebook(userDTO);
	}
	
	public boolean registerNeverEmpty(UserDTO userDTO) throws RemoteException {
		return neverEmptyServer.registerNeverEmpty(userDTO);
	}

	public boolean login(UserDTO user) throws RemoteException {
		return neverEmptyServer.login(user);
	}
	
	public ProductDTO getProducts() throws RemoteException {
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
	
	public UserDTO getUser(UserDTO user) throws RemoteException {
		return neverEmptyServer.getUser(user);
	}

}
