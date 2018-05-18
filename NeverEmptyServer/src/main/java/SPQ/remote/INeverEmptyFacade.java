package SPQ.remote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import SPQ.dto.ProductDTO;
import SPQ.dto.UserDTO;



public interface INeverEmptyFacade extends Remote{
	
	public boolean registerGoogle(String username, String email, String password ) throws RemoteException;
	public boolean registerFacebook(String username, String email, String password ) throws RemoteException;
	public boolean login(UserDTO user) throws RemoteException;
	
	public ProductDTO getProducts() throws RemoteException;
	public UserDTO getUser(UserDTO user) throws RemoteException;
	
	public boolean updateShoppingList (String username, String productList) throws RemoteException;
	
	public boolean updateUserPayPalEmail (String username, String payPalEmail) throws RemoteException;
	public boolean updateUserPayPalPassword (String username, String payPalPassword) throws RemoteException;
	public boolean updateUserCardNumber (String username, String cardNumber) throws RemoteException;
}
